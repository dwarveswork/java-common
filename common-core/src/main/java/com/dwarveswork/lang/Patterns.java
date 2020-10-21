/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.lang;

import java.util.regex.Pattern;

public final class Patterns {

  private Patterns() {}

  public static final Pattern EMAIL_ADDRESS = Pattern.compile(
      "[a-zA-Z0-9+._%\\-]{1,256}" +
          "@" +
          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
          "(" +
          "\\." +
          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
          ")+"
  );

  public static final Pattern PHONE_NUMBER = Pattern.compile(
      "(\\+[0-9]+[\\- .]*)?"
          + "(\\([0-9]+\\)[\\- .]*)?"
          + "([0-9][0-9\\- .]+[0-9])");

  public static final Pattern PHONE_NUMBER_WITH_CODE = Pattern.compile(
      "(\\+[0-9]+[\\- .]*)"
          + "(\\([0-9]+\\)[\\- .]*)?"
          + "([0-9][0-9\\- .]+[0-9])");

  public static boolean isEmailAddress(String value) {
    if (Strings.isBlank(value)) {
      return false;
    }
    return EMAIL_ADDRESS.matcher(value).matches();
  }

  public static boolean isPhoneNumber(String value) {
    if (Strings.isBlank(value)) {
      return false;
    }
    return PHONE_NUMBER.matcher(value).matches();
  }

  public static boolean isPhoneNumberWithCode(String value) {
    if (Strings.isBlank(value)) {
      return false;
    }
    return PHONE_NUMBER_WITH_CODE.matcher(value).matches();
  }
}
