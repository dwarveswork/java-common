/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

public abstract class HttpResponseHandler {

  public abstract <T> T parseResponse(HttpResponse<T> response);
}
