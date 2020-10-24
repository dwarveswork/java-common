/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

final class MD {

  private static final Provider PROVIDER = new BouncyCastleProvider();

  private MD() {}

  static byte[] encode(String algorithm, byte[] input) {
    try {
      MessageDigest md = MessageDigest.getInstance(algorithm, PROVIDER);
      md.update(input);
      return md.digest();
    } catch (NoSuchAlgorithmException ex) {
      throw new IllegalArgumentException(ex);
    }
  }

  static String encodeToHex(String algorithm, byte[] input) {
    return Hex.encode(encode(algorithm, input));
  }
}
