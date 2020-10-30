/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

import org.bouncycastle.crypto.generators.SCrypt;

public final class Scrypt {

  // Use recommended parameter according to: https://godoc.org/golang.org/x/crypto/scrypt
  public static byte[] encode(byte[] input, byte[] salt) {
    return encode(input, salt, 15, 8, 1, 32);
  }

  public static byte[] encode(byte[] input, byte[] salt, int round, int blockSize, int parallelization, int length) {
    return SCrypt.generate(input, salt, 1 << round, blockSize, parallelization, length);
  }

  public static String encodeToHex(byte[] input, byte[] salt) {
    return Hex.encode(encode(input, salt));
  }

  public static String encodeToHex(byte[] input, byte[] salt, int round, int blockSize, int parallelization, int length) {
    return Hex.encode(encode(input, salt, round, blockSize, parallelization, length));
  }
}
