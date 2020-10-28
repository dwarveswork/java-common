/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public class UnauthenticatedException extends GenericException {

  private static final long serialVersionUID = 275557149729496074L;

  public UnauthenticatedException() {
    super(ExceptionCode.UNAUTHENTICATED_EXCEPTION, "Unauthenticated");
  }

  public UnauthenticatedException(String message) {
    super(ExceptionCode.UNAUTHENTICATED_EXCEPTION, message);
  }

  public UnauthenticatedException(String message, Throwable cause) {
    super(ExceptionCode.UNAUTHENTICATED_EXCEPTION, message, cause);
  }

  public UnauthenticatedException(Throwable cause) {
    super(ExceptionCode.UNAUTHENTICATED_EXCEPTION, cause);
  }
}
