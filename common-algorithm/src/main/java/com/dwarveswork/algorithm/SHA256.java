/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

public final class SHA256 {

  public static final String ALGORITHM = "SHA-256";

  private SHA256() {}

  public static byte[] encode(byte[] input) {
    return MD.encode(ALGORITHM, input);
  }

  public static String encodeToHex(byte[] input) {
    return MD.encodeToHex(ALGORITHM, input);
  }
}
