/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

import java.lang.reflect.Type;

public class PostRequest extends HttpRequest {

  private static final long serialVersionUID = 1296677367477734273L;

  public PostRequest(String uri, Type requestType, Type responseType) {
    super(uri, "POST", requestType, responseType);
  }
}
