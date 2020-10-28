/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public class UnexpectedException extends GenericException {

  private static final long serialVersionUID = 4055811382925797893L;

  public UnexpectedException() {
    super(ExceptionCode.UNEXPECTED_EXCEPTION);
  }

  public UnexpectedException(String message) {
    super(ExceptionCode.UNEXPECTED_EXCEPTION, message);
  }

  public UnexpectedException(String message, Throwable cause) {
    super(ExceptionCode.UNEXPECTED_EXCEPTION, message, cause);
  }

  public UnexpectedException(Throwable cause) {
    super(ExceptionCode.UNEXPECTED_EXCEPTION, cause);
  }
}
