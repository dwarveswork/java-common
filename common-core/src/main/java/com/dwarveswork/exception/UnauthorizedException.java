/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public class UnauthorizedException extends GenericException {

  private static final long serialVersionUID = -2616449342522035928L;

  public UnauthorizedException() {
    super(ExceptionCode.UNAUTHORIZED_EXCEPTION, "Unauthorized");
  }

  public UnauthorizedException(String message) {
    super(ExceptionCode.UNAUTHORIZED_EXCEPTION, message);
  }

  public UnauthorizedException(String message, Throwable cause) {
    super(ExceptionCode.UNAUTHORIZED_EXCEPTION, message, cause);
  }

  public UnauthorizedException(Throwable cause) {
    super(ExceptionCode.UNAUTHORIZED_EXCEPTION, cause);
  }
}
