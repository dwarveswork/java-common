/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;

public class AESCBC {

  private static final PaddedBufferedBlockCipher CIPHER = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));

  private AESCBC() {}

  static byte[] encrypt(byte[] input, byte[] key) {
    return AES.encrypt(CIPHER, input, key, null);
  }

  static byte[] encrypt(byte[] input, byte[] key, byte[] iv) {
    return AES.encrypt(CIPHER, input, key, iv);
  }

  public static byte[] decrypt(byte[] encrypted, byte[] key) {
    return AES.decrypt(CIPHER, encrypted, key, null);
  }

  public static byte[] decrypt(byte[] encrypted, byte[] key, byte[] iv) {
    return AES.decrypt(CIPHER, encrypted, key, iv);
  }
}
