/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest implements Serializable {

  private static final long serialVersionUID = -3831580380915256251L;

  private final Type requestType;
  private final Type responseType;
  private final String uri;
  private final String method;
  private Duration executeTimeout;
  private Map<String, String> headers;
  private Object body;

  public HttpRequest(String uri, String method, Type requestType, Type responseType) {
    this.uri = uri;
    this.method = method;
    this.requestType = requestType;
    this.responseType = responseType;
  }

  public Type getRequestType() {
    return requestType;
  }

  public Type getResponseType() {
    return responseType;
  }

  public String getUri() {
    return uri;
  }

  public String getMethod() {
    return method;
  }

  public Duration getExecuteTimeout() {
    return executeTimeout;
  }

  public HttpRequest setExecuteTimeout(Duration executeTimeout) {
    this.executeTimeout = executeTimeout;
    return this;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public HttpRequest setHeaders(Map<String, String> headers) {
    this.headers = headers;
    return this;
  }

  public Object getBody() {
    return body;
  }

  public HttpRequest setBody(Object body) {
    this.body = body;
    return this;
  }

  public void addHeader(String key, String value) {
    if (headers == null) {
      headers = new HashMap<>();
    }
    headers.put(key, value);
  }

  public void merge(HttpExecutorConfiguration configuration) {
    if (executeTimeout == null) {
      executeTimeout = configuration.getExecuteTimeout();
    }
    if (headers == null) {
      headers = configuration.getHeaders();
    } else {
      headers.putAll(configuration.getHeaders());
    }
  }

  @Override
  public String toString() {
    return "HttpRequest{" +
        "requestType=" + requestType +
        ", responseType=" + responseType +
        ", uri='" + uri + '\'' +
        ", method='" + method + '\'' +
        ", executeTimeout=" + executeTimeout +
        ", headers=" + headers +
        ", body=" + body +
        '}';
  }
}
