/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public class RemoteUnavailableException extends RemoteException {

  private static final long serialVersionUID = 8812782810356746839L;

  public RemoteUnavailableException(String url) {
    super(ExceptionCode.REMOTE_UNAVAILABLE_EXCEPTION, url);
  }

  public RemoteUnavailableException(String url, String message) {
    super(ExceptionCode.REMOTE_UNAVAILABLE_EXCEPTION, url, message);
  }

  public RemoteUnavailableException(String url, Throwable cause) {
    super(ExceptionCode.REMOTE_UNAVAILABLE_EXCEPTION, url, cause);
  }
}
