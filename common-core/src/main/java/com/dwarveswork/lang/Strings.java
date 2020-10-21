/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.lang;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;

public final class Strings {

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
}
