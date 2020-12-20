/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.security;

import com.dwarveswork.algorithm.Base64;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.KeyPair;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

class JwtSecurityServiceTest {

  @Test
  void generateRSAKeyPair() {
    KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
    String publicKey = Base64.encode(keyPair.getPublic().getEncoded());
    System.out.printf("-----BEGIN PUBLIC KEY-----%n%s%n-----END PUBLIC KEY-----%n", publicKey);
    String privateKey = Hex.toHexString(keyPair.getPrivate().getEncoded());
    System.out.println(privateKey);
  }
}