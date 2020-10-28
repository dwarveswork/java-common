/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public class ValidationException extends GenericException {

  private static final long serialVersionUID = 2230575160284332012L;

  public ValidationException() {
    super(ExceptionCode.VALIDATION_EXCEPTION, "Validation failed");
  }

  public ValidationException(String message) {
    super(ExceptionCode.VALIDATION_EXCEPTION, message);
  }

  public ValidationException(String message, Throwable cause) {
    super(ExceptionCode.VALIDATION_EXCEPTION, message, cause);
  }

  public ValidationException(Throwable cause) {
    super(ExceptionCode.VALIDATION_EXCEPTION, cause);
  }
}
