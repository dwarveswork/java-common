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

class Base64Test {

  @ParameterizedTest
  @ValueSource(strings = {
      "f:Zg==",
      "fo:Zm8=",
      "foo:Zm9v",
      "foob:Zm9vYg==",
      "fooba:Zm9vYmE=",
      "foobar:Zm9vYmFy"
  })
  void testCodec(String value) {
    String[] parts = value.split(":");
    assertEquals(Base64.encode(parts[0].getBytes(UTF_8)), parts[1]);
    assertEquals(new String(Base64.decode(parts[1]), UTF_8), parts[0]);
  }

  @Test
  void testEmptyValueCodec() {
    assertEquals(Base64.encode("".getBytes(UTF_8)), "");
    assertEquals(new String(Base64.decode(""), UTF_8), "");
  }

  @Test
  void testNullValueDecode() {
    assertThrows(IllegalArgumentException.class, () -> Base64.decode(null));
  }
}