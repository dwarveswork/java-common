/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

final class HmacDigest {

  static byte[] encode(Digest digest, byte[] data, byte[] key) {
    HMac hMac = new HMac(digest);
    hMac.init(new KeyParameter(key));
    hMac.reset();
    hMac.update(data, 0, data.length);
    byte[] out = new byte[digest.getDigestSize()];
    hMac.doFinal(out, 0);
    return out;
  }

  static String encodeToHex(Digest digest, byte[] input, byte[] key) {
    return Hex.encode(encode(digest, input, key));
  }
}
