/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public class DuplicateException extends GenericException {

  private static final long serialVersionUID = 233158677130547527L;

  public DuplicateException() {
    super(ExceptionCode.DUPLICATE_EXCEPTION, "Duplicate resource");
  }

  public DuplicateException(String message) {
    super(ExceptionCode.DUPLICATE_EXCEPTION, message);
  }

  public DuplicateException(String message, Throwable cause) {
    super(ExceptionCode.DUPLICATE_EXCEPTION, message, cause);
  }

  public DuplicateException(Throwable cause) {
    super(ExceptionCode.DUPLICATE_EXCEPTION, cause);
  }
}
