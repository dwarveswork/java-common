/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.lang;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public final class Strings {

  public static final String ALPHABETIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static final String NUMERIC = "1234567890";
  public static final String ALPHANUMERIC = ALPHABETIC + NUMERIC;

  private Strings() {}

  public static boolean isBlank(final String string) {
    return string == null || string.trim().isEmpty();
  }

  public static boolean isNotBlank(final String string) {
    return !isBlank(string);
  }

  public static boolean equalsIgnoreCase(String str1, String str2) {
    if (str1 == null || str2 == null) {
      return Objects.equals(str1, str2);
    } else {
      return Objects.equals(str1.toUpperCase(Locale.getDefault()), str2.toUpperCase(Locale.getDefault()));
    }
  }

  public static byte[] toUTF8Bytes(String string) {
    return string == null ? new byte[0] : string.getBytes(StandardCharsets.UTF_8);
  }

  public static String fromUTF8Bytes(byte[] utf8Bytes) {
    return utf8Bytes == null ? null : new String(utf8Bytes);
  }

  public static String upperCaseFirstLetter(String string) {
    if (isBlank(string)) {
      return string;
    }
    return string.substring(0, 1).toUpperCase(Locale.getDefault()) + string.substring(1);
  }

  public static String lowerCaseFirstLetter(String string) {
    if (isBlank(string)) {
      return string;
    }
    return string.substring(0, 1).toLowerCase(Locale.getDefault()) + string.substring(1);
  }

  public static String randomString(int length, final char[] chars, Random random) {
    if (length <= 0) {
      return "";
    }
    char[] buf = new char[length];
    for (int i = 0; i < length; ++i) {
      buf[i] = chars[random.nextInt(chars.length)];
    }
    return new String(buf);
  }

  public static String randomNumeric(int length) {
    return randomString(length, NUMERIC.toCharArray(), new SecureRandom());
  }

  public static String randomAlphabetic(int length) {
    return randomString(length, ALPHABETIC.toCharArray(), new SecureRandom());
  }

  public static String randomAlphanumeric(int length) {
    return randomString(length, ALPHANUMERIC.toCharArray(), new SecureRandom());
  }
}
