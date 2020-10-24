/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

import java.security.SecureRandom;
import java.util.Arrays;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;

final class AES {

  private AES() {}

  static byte[] encrypt(PaddedBufferedBlockCipher cipher, byte[] input, byte[] key, byte[] iv) {
    return process(cipher, true, input, key, iv);
  }

  public static byte[] decrypt(PaddedBufferedBlockCipher cipher, byte[] encrypted, byte[] key, byte[] iv) {
    return process(cipher, false, encrypted, key, iv);
  }

  private static byte[] process(PaddedBufferedBlockCipher cipher, boolean encrypt, byte[] input, byte[] key, byte[] iv) {
    CipherParameters parameters;
    if (iv == null) {
      parameters = new ParametersWithRandom(new KeyParameter(key), new SecureRandom());
    } else {
      parameters = new ParametersWithIV(new KeyParameter(key), iv);
    }
    try {
      cipher.init(encrypt, parameters);
      byte[] output = new byte[cipher.getOutputSize(input.length)];
      int size = cipher.processBytes(input, 0, input.length, output, 0);
      size += cipher.doFinal(output, size);
      return Arrays.copyOf(output, size);
    } catch (InvalidCipherTextException | IllegalArgumentException ex) {
      throw new IllegalArgumentException("Illegal BouncyCastle AES Arguments", ex);
    }
  }
}
