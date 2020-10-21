/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public enum ExceptionCode {

  REMOTE_EXECUTION_EXCEPTION(502),
  REMOTE_UNAVAILABLE_EXCEPTION(503),
  REMOTE_TIMEOUT_EXCEPTION(504),
  ;
  private final int status;

  ExceptionCode(int status) {
    this.status = status;
  }

  public int getStatus() {
    return status;
  }
}
