/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.lang;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class StringsTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = { "  ", " " })
  void isBlank(String string) {
    assertTrue(Strings.isBlank(string));
    assertFalse(Strings.isNotBlank(string));
  }

  @ParameterizedTest
  @ValueSource(strings = { "a", "a ", " b", "!" })
  void isNotBlank(String string) {
    assertTrue(Strings.isNotBlank(string));
    assertFalse(Strings.isBlank(string));
  }

  @ParameterizedTest
  @ValueSource(strings = { "a!b$", "a!B$", "A!b$", "A!B$" })
  void equalsIgnoreCase(String string) {
    assertTrue(Strings.equalsIgnoreCase(string, "A!b$"));
    assertTrue(Strings.equalsIgnoreCase("A!b$", string));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = { "A!$", "AB$", "!b$", "!B$" })
  void notEqualsIgnoreCase(String string) {
    assertFalse(Strings.equalsIgnoreCase(string, "A!b$"));
    assertFalse(Strings.equalsIgnoreCase("A!b$", string));
  }

  @Test
  void testUTF8Bytes() {
    String string = "AêñüC";
    byte[] bytes = new byte[] { (byte) 0x41, (byte) 0xc3, (byte) 0xaa, (byte) 0xc3, (byte) 0xb1, (byte) 0xc3, (byte) 0xbc, (byte) 0x43 };
    assertArrayEquals(bytes, Strings.toUTF8Bytes(string));
    assertEquals(string, Strings.fromUTF8Bytes(bytes));
    assertEquals(0, Strings.toUTF8Bytes(null).length);
    assertEquals("", Strings.fromUTF8Bytes(new byte[0]));
    assertNull(Strings.fromUTF8Bytes(null));
  }

  @ParameterizedTest
  @ValueSource(strings = { "abc", "acd", "aCD", "Abc", "ABC" })
  void upperCaseFirstLetter(String string) {
    assertEquals(Strings.upperCaseFirstLetter(string).charAt(0), 'A');
  }

  @ParameterizedTest
  @NullAndEmptySource
  void blankUpperCaseFirstLetter(String string) {
    assertEquals(Strings.upperCaseFirstLetter(string), string);
  }

  @ParameterizedTest
  @ValueSource(strings = { "abc", "acd", "aCD", "Abc", "ABC" })
  void lowerCaseFirstLetter(String string) {
    assertEquals(Strings.lowerCaseFirstLetter(string).charAt(0), 'a');
  }

  @ParameterizedTest
  @NullAndEmptySource
  void blankLowerCaseFirstLetter(String string) {
    assertEquals(Strings.lowerCaseFirstLetter(string), string);
  }
}