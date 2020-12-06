/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class HttpResponse<T> implements Serializable {

  private String version;
  private String uri;
  private int statusCode;
  private Map<String, List<String>> headers;
  private T body;
  private HttpRequest request;

  public String getVersion() {
    return version;
  }

  public HttpResponse<T> setVersion(String version) {
    this.version = version;
    return this;
  }

  public String getUri() {
    return uri;
  }

  public HttpResponse<T> setUri(String uri) {
    this.uri = uri;
    return this;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public HttpResponse<T> setStatusCode(int statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public Map<String, List<String>> getHeaders() {
    return headers;
  }

  public HttpResponse<T> setHeaders(Map<String, List<String>> headers) {
    this.headers = headers;
    return this;
  }

  public T getBody() {
    return body;
  }

  public HttpResponse<T> setBody(T body) {
    this.body = body;
    return this;
  }

  public HttpRequest getRequest() {
    return request;
  }

  public HttpResponse<T> setRequest(HttpRequest request) {
    this.request = request;
    return this;
  }

  @Override
  public String toString() {
    return "HttpResponse{" +
        "version='" + version + '\'' +
        ", uri='" + uri + '\'' +
        ", statusCode=" + statusCode +
        ", headers=" + headers +
        ", body=" + body +
        ", request=" + request +
        '}';
  }
}
