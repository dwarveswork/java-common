/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http.exception;

import com.dwarveswork.exception.RemoteExecutionException;

public class HttpStatusException extends RemoteExecutionException {

  public HttpStatusException(String url, int status) {
    super(url, "Http status: " + status);
  }

  public HttpStatusException(String url, String message) {
    super(url, message);
  }

  public HttpStatusException(String url, Throwable cause) {
    super(url, cause);
  }
}
