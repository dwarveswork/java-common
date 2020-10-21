/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public class RemoteExecutionException extends RemoteException {

  private static final long serialVersionUID = 3947551430749752595L;

  public RemoteExecutionException(String url) {
    super(ExceptionCode.REMOTE_EXECUTION_EXCEPTION, url);
  }

  public RemoteExecutionException(String url, String message) {
    super(ExceptionCode.REMOTE_EXECUTION_EXCEPTION, url, message);
  }

  public RemoteExecutionException(String url, Throwable cause) {
    super(ExceptionCode.REMOTE_EXECUTION_EXCEPTION, url, cause);
  }
}
