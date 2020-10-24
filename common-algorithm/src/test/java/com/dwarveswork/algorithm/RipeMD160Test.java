package com.dwarveswork.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RipeMD160Test {

  @ParameterizedTest
  @ValueSource(strings = {
      ":9c1185a5c5e9fc54612808977ee8f548b2258d31",
      "a:0bdc9d2d256b3ee9daae347be6f4dc835a467ffe",
      "abc:8eb208f7e05d987a9b044a8e98c6b087f15a0bfc",
      "message digest:5d0689ef49d2fae572b881b123a85ffa21595f36",
      "abcdefghijklmnopqrstuvwxyz:f71c27109c692c1b56bbdceb5b9d2865b3708dbc",
      "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq:12a053384a9c0c88e405a06c27dcf49ada62eb2b",
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789:b0e20b6e3116640286ed3a87a5713079b21f5189"
  })
  void testEncode(String value) {
    String[] parts = value.split(":");
    assertEquals(RipeMD160.encodeToHex(parts[0].getBytes(StandardCharsets.UTF_8)), parts[1]);
  }
}