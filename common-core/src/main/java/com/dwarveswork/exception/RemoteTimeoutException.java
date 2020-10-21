/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public class RemoteTimeoutException extends RemoteException {

  private static final long serialVersionUID = 1454084230630368835L;

  public RemoteTimeoutException(String url) {
    super(ExceptionCode.REMOTE_TIMEOUT_EXCEPTION, url);
  }

  public RemoteTimeoutException(String url, String message) {
    super(ExceptionCode.REMOTE_TIMEOUT_EXCEPTION, url, message);
  }

  public RemoteTimeoutException(String url, Throwable cause) {
    super(ExceptionCode.REMOTE_TIMEOUT_EXCEPTION, url, cause);
  }
}
