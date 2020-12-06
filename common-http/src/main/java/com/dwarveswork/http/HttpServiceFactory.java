/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

import java.lang.reflect.Proxy;

public class HttpServiceFactory {

  private HttpServiceFactory() {}

  public static <T> T createProxy(Class<T> type, String baseUri) {
    return createProxy(type, new HttpServiceProxy(baseUri));
  }

  public static <T> T createProxy(Class<T> type, String baseUri, HttpExecutor httpExecutor) {
    return createProxy(type, new HttpServiceProxy(baseUri, httpExecutor));
  }

  @SuppressWarnings("unchecked")
  public static <T> T createProxy(Class<T> type, HttpServiceProxy proxy) {
    return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[] { type }, proxy);
  }
}
