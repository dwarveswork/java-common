/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

import com.dwarveswork.exception.RemoteExecutionException;

public class DefaultHttpResponseHandler extends HttpResponseHandler {

  @Override
  public <T> T parseResponse(HttpResponse<T> response) {
    if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
      return response.getBody();
    }
    throw new RemoteExecutionException(response.getRequest().getUri(), "Status=" + response.getStatusCode());
  }
}
