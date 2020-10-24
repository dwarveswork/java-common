/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;

public final class HmacSHA256 {

  private static final Digest DIGEST = new SHA256Digest();

  private HmacSHA256() {}

  public static byte[] encode(byte[] input, byte[] key) {
    return HmacDigest.encode(DIGEST, input, key);
  }

  public static String encodeToHex(byte[] input, byte[] key) {
    return HmacDigest.encodeToHex(DIGEST, input, key);
  }
}
