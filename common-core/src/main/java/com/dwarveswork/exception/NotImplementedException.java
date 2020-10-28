/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public class NotImplementedException extends GenericException {

  private static final long serialVersionUID = -7608141870665467458L;

  public NotImplementedException() {
    super(ExceptionCode.NOT_IMPLEMENTED_EXCEPTION, "Not implemented");
  }

  public NotImplementedException(String message) {
    super(ExceptionCode.NOT_IMPLEMENTED_EXCEPTION, message);
  }

  public NotImplementedException(String message, Throwable cause) {
    super(ExceptionCode.NOT_IMPLEMENTED_EXCEPTION, message, cause);
  }

  public NotImplementedException(Throwable cause) {
    super(ExceptionCode.NOT_IMPLEMENTED_EXCEPTION, cause);
  }
}
