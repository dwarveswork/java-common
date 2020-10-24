/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.algorithm;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HexTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "f:66",
      "fo:666f",
      "foo:666f6f",
      "foob:666f6f62",
      "fooba:666f6f6261",
      "foobar:666f6f626172"
  })
  void testCodec(String value) {
    String[] parts = value.split(":");
    assertEquals(Hex.encode(parts[0].getBytes(UTF_8)), parts[1]);
    assertEquals(new String(Hex.decode(parts[1]), UTF_8), parts[0]);
  }

  @Test
  void testEmptyValueCodec() {
    assertEquals(Hex.encode("".getBytes(UTF_8)), "");
    assertEquals(new String(Hex.decode(""), UTF_8), "");
  }

  @Test
  void testNullValueDecode() {
    assertThrows(IllegalArgumentException.class, () -> Hex.decode(null));
  }
}