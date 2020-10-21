/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.exception;

public abstract class RemoteException extends GenericException {

  public RemoteException(ExceptionCode code, String url) {
    this(code, url, "Remote call failed");
  }

  public RemoteException(ExceptionCode code, String url, String message) {
    super(code, String.format("%s [%s]", message, url));
  }

  public RemoteException(ExceptionCode code, String url, Throwable cause) {
    super(code, String.format("%s [%s]", cause.getLocalizedMessage(), url), cause);
  }
}
