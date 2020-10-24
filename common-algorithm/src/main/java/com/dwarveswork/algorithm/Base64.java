/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

public final class Base64 {

  public static String encode(byte[] input) {
    return org.bouncycastle.util.encoders.Base64.toBase64String(input);
  }

  public static byte[] decode(String input) {
    if (input == null) {
      throw new IllegalArgumentException();
    }
    return org.bouncycastle.util.encoders.Base64.decode(input);
  }
}
