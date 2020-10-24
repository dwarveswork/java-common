package com.dwarveswork.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MD5Test {

  @ParameterizedTest
  @ValueSource(strings = {
      ":d41d8cd98f00b204e9800998ecf8427e",
      "The quick brown fox jumps over the lazy dog:9e107d9d372bb6826bd81d3542a419d6",
      "The quick brown fox jumps over the lazy dog.:e4d909c290d0fb1ca068ffaddf22cbd0"
  })
  void testEncode(String value) {
    String[] parts = value.split(":");
    assertEquals(MD5.encodeToHex(parts[0].getBytes(StandardCharsets.UTF_8)), parts[1]);
  }
}