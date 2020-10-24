package com.dwarveswork.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SHA256Test {

  @ParameterizedTest
  @ValueSource(strings = {
      ":e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
      "abc:ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad",
      "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq:248d6a61d20638b8e5c026930c3e6039a33ce45964ff2167f6ecedd419db06c1",
      "abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmnhijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu:cf5b16a778af8380036ce59e7b0492370b249b11e8f07a51afac45037afee9d1",
  })
  void testEncode(String value) {
    String[] parts = value.split(":");
    assertEquals(SHA256.encodeToHex(parts[0].getBytes(StandardCharsets.UTF_8)), parts[1]);
  }
}