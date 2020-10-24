/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AESCBCTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "Single block msg:06a9214036b8a15b512e03d534120006:3dafba429d9eb430b422da802c9fac41:e353779c1079aeb82708942dbe77181ab97c825e1c785146542d396941bce55d",
      "This is a 48-byte message (exactly 3 AES blocks):6c3ea0477630ce21a2ce334aa746c2cd:c782dc4c098c66cbd9cd27d825682c81:d0a02b3836451753d493665d33f0e8862dea54cdb293abc7506939276772f8d5021c19216bad525c8579695d83ba2684d248b3e0f2388c137102846eb06272ff"
  })
  void testWithIv(String value) {
    String[] parts = value.split(":");
    assertEquals(Hex.encode(AESCBC.encrypt(parts[0].getBytes(UTF_8), Hex.decode(parts[1]), Hex.decode(parts[2]))), parts[3]);
    assertEquals(new String(AESCBC.decrypt(Hex.decode(parts[3]), Hex.decode(parts[1]), Hex.decode(parts[2])), UTF_8), parts[0]);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "Single block msg:06a9214036b8a15b512e03d534120006",
      "This is a 48-byte message (exactly 3 AES blocks):6c3ea0477630ce21a2ce334aa746c2cd"
  })
  void testWithoutIv(String value) {
    String[] parts = value.split(":");
    byte[] encrypted = AESCBC.encrypt(parts[0].getBytes(UTF_8), Hex.decode(parts[1]));
    String source = new String(AESCBC.decrypt(encrypted, Hex.decode(parts[1])), UTF_8);
    assertEquals(source, parts[0]);
  }
}