/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.lang;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PatternsTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "dev@dwarveswork.com",
      "dev+1@dwarveswork.com",
      "1+dev@dwarveswork.com.cn",
      "dev.1@d.c",
      "dev.1@1.c",
      "1@1.1",
      "DEV+1@D.C",
      ".@1.1"
  })
  void testValidEmailAddress(String value) {
    assertTrue(Patterns.isEmailAddress(value));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      "d$@d.c",
      "1@1.",
      "@1.1",
      "@.1"
  })
  void testInvalidEmailAddress(String value) {
    assertFalse(Patterns.isEmailAddress(value));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "010-110",
      "(010) 110",
      "13000000000",
      "+11234567890",
      "+1123456-9",
      "+1123456-9-110",
      "100 0000 0000"
  })
  void testValidPhoneNumber(String value) {
    assertTrue(Patterns.isPhoneNumber(value));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      "134$2123",
      "1",
      "abc",
      "100 0000_0000"
  })
  void testInvalidPhoneNumber(String value) {
    assertFalse(Patterns.isPhoneNumber(value));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "+123456789",
      "+1 233456789",
      "+1 2345 6789",
      "+1 2345 6789 - 0",
      "+1 2345 6789 - 0 - 1",
      "+1 2345 6789 - 0 - 1 - 2"
  })
  void testValidPhoneNumberWithCode(String value) {
    assertTrue(Patterns.isPhoneNumberWithCode(value));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      "123456789",
      "1+23456789",
      "1234 5678 - 9 - 0",
      "001 1234 5678"
  })
  void testInvalidPhoneNumberWithCode(String value) {
    assertFalse(Patterns.isPhoneNumberWithCode(value));
  }
}