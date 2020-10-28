/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public abstract class GenericException extends RuntimeException {

  protected final ExceptionCode code;

  public GenericException(ExceptionCode code) {
    this.code = code;
  }

  public GenericException(ExceptionCode code, String message) {
    super(code.getStatus() + " - " + message);
    this.code = code;
  }

  public GenericException(ExceptionCode code, String message, Throwable cause) {
    super(code.getStatus() + " - " + message, cause);
    this.code = code;
  }

  public GenericException(ExceptionCode code, Throwable cause) {
    super(code.getStatus() + " - " + cause.getLocalizedMessage(), cause);
    this.code = code;
  }

  public ExceptionCode getCode() {
    return code;
  }

  public int getStatus() {
    return code.getStatus();
  }
}
