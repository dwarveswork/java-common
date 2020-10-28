/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public class NotFoundException extends GenericException {

  private static final long serialVersionUID = -898185322807211897L;

  public NotFoundException() {
    super(ExceptionCode.NOT_FOUND_EXCEPTION, "Not found");
  }

  public NotFoundException(String message) {
    super(ExceptionCode.NOT_FOUND_EXCEPTION, message);
  }

  public NotFoundException(String message, Throwable cause) {
    super(ExceptionCode.NOT_FOUND_EXCEPTION, message, cause);
  }

  public NotFoundException(Throwable cause) {
    super(ExceptionCode.NOT_FOUND_EXCEPTION, cause);
  }
}
