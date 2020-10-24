/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

public final class Hex {

  private Hex() {}

  public static String encode(byte[] input) {
    return org.bouncycastle.util.encoders.Hex.toHexString(input);
  }

  public static byte[] decode(String input) {
    if (input == null) {
      throw new IllegalArgumentException();
    }
    return org.bouncycastle.util.encoders.Hex.decode(input);
  }
}
