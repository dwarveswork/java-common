/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

public final class MD5 {

  public static final String ALGORITHM = "MD5";

  private MD5() {}

  public static byte[] encode(byte[] input) {
    return MD.encode(ALGORITHM, input);
  }

  public static String encodeToHex(byte[] input) {
    return MD.encodeToHex(ALGORITHM, input);
  }
}
