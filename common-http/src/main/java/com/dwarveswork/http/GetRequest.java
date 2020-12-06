/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

import java.lang.reflect.Type;

public class GetRequest extends HttpRequest {

  private static final long serialVersionUID = -7159782498442293646L;

  public GetRequest(String uri, Type responseType) {
    super(uri, "GET", Void.class, responseType);
  }
}
