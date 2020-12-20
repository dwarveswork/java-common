/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.security;

import com.dwarveswork.security.annotation.AccessControl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JwtSecurityService implements SecurityService {

  private static final Logger LOGGER = LogManager.getLogger();

  private static final String CLAIM_KEY_REALM = "realm";
  private static final String CLAIM_KEY_NAME = "name";
  private static final String CLAIM_KEY_ROLES = "roles";

  private final JwtSecurityConfiguration configuration;

  public JwtSecurityService(JwtSecurityConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public String generateToken(UserPrincipal principal) {
    Instant now = Instant.now();
    return Jwts.builder().setIssuer(configuration.getIssuer()).setSubject(principal.getId().toString()).setIssuedAt(Date.from(now))
               .setNotBefore(Date.from(now)).setExpiration(Date.from(now.plus(configuration.getExpireMillis(), ChronoUnit.MILLIS)))
               .claim(CLAIM_KEY_REALM, configuration.getRealm()).claim(CLAIM_KEY_NAME, principal.getName())
               .claim(CLAIM_KEY_ROLES, principal.getRoles()).signWith(configuration.getPrivateKey()).compact();
  }

  @Override
  @SuppressWarnings("unchecked")
  public UserPrincipal parseToken(String token) {
    try {
      Claims claims = Jwts.parserBuilder().setSigningKey(configuration.getPrivateKey()).build().parseClaimsJws(token).getBody();
      List<String> roles = claims.get(CLAIM_KEY_ROLES, List.class);
      return new UserPrincipal(Long.parseLong(claims.getSubject()), claims.get(CLAIM_KEY_NAME, String.class),
                               roles.stream().map(Role::valueOf).collect(Collectors.toList()));
    } catch (JwtException ex) {
      LOGGER.warn(ex.getLocalizedMessage(), ex);
      return null;
    }
  }

  @Override
  public boolean isAuthorized(AccessControl accessControl, UserPrincipal principal) {
    if (accessControl != null && !accessControl.value()) {
      return true;
    }
    List<Role> roles = accessControl == null ? List.of(Role.USER) : Arrays.asList(accessControl.roles());
    return principal.hasAnyRole(roles);
  }
}
