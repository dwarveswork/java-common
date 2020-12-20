/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.security;

import com.dwarveswork.exception.UnexpectedException;
import java.io.Serializable;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import org.bouncycastle.util.encoders.Hex;

public class JwtSecurityConfiguration implements Serializable {

  private static final long serialVersionUID = -1748078234080783004L;

  private String issuer = "dwarveswork.com";
  private PrivateKey privateKey;
  private String realm = "dwarveswork.com";
  private Long expireMillis = 3600L;

  public JwtSecurityConfiguration(String privateKeyHex) {
    this.privateKey = parsePrivateKey(privateKeyHex);
  }

  public JwtSecurityConfiguration(String issuer, String privateKeyHex, String realm, Long expireMillis) {
    this.issuer = issuer;
    this.privateKey = parsePrivateKey(privateKeyHex);
    this.realm = realm;
    this.expireMillis = expireMillis;
  }

  public String getIssuer() {
    return issuer;
  }

  public JwtSecurityConfiguration setIssuer(String issuer) {
    this.issuer = issuer;
    return this;
  }

  public PrivateKey getPrivateKey() {
    return privateKey;
  }

  public JwtSecurityConfiguration setPrivateKey(PrivateKey privateKey) {
    this.privateKey = privateKey;
    return this;
  }

  public String getRealm() {
    return realm;
  }

  public JwtSecurityConfiguration setRealm(String realm) {
    this.realm = realm;
    return this;
  }

  public Long getExpireMillis() {
    return expireMillis;
  }

  public JwtSecurityConfiguration setExpireMillis(Long expireMillis) {
    this.expireMillis = expireMillis;
    return this;
  }

  public PrivateKey parsePrivateKey(String hex) {
    try {
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Hex.decode(hex)));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      throw new UnexpectedException(ex);
    }
  }

  @Override
  public String toString() {
    return "JwtSecurityConfiguration{" +
        "issuer='" + issuer + '\'' +
        ", privateKey='" + privateKey + '\'' +
        ", realm='" + realm + '\'' +
        ", expireMillis=" + expireMillis +
        '}';
  }
}
