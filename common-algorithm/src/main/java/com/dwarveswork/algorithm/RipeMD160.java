/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

public final class RipeMD160 {

  public static final String ALGORITHM = "RipeMD160";

  private RipeMD160() {}

  public static byte[] encode(byte[] input) {
    return MD.encode(ALGORITHM, input);
  }

  public static String encodeToHex(byte[] input) {
    return MD.encodeToHex(ALGORITHM, input);
  }
}
