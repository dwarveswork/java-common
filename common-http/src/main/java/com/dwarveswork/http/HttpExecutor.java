/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

import java.lang.reflect.Type;
import java.util.Map;

public abstract class HttpExecutor {

  private final HttpExecutorConfiguration globalConfiguration;

  public HttpExecutor() {
    this.globalConfiguration = new HttpExecutorConfiguration();
  }

  public HttpExecutor(HttpExecutorConfiguration configuration) {
    this.globalConfiguration = configuration == null ? new HttpExecutorConfiguration() : configuration;
  }

  public <T> HttpResponse<T> get(String uri, Type responseType) {
    GetRequest request = new GetRequest(uri, responseType);
    return get(request);
  }

  public <T> HttpResponse<T> get(GetRequest request) {
    return request(request);
  }

  public <T> HttpResponse<T> post(String uri, Type responseType) {
    PostRequest request = new PostRequest(uri, Void.class, responseType);
    return post(request);
  }

  public <T> HttpResponse<T> post(String uri, String contentType, Type responseType) {
    PostRequest request = new PostRequest(uri, Void.class, responseType);
    request.addHeader("Content-Type", contentType);
    return post(request);
  }

  public <T> HttpResponse<T> post(String uri, String body, String contentType, Type responseType) {
    PostRequest request = new PostRequest(uri, String.class, responseType);
    request.setBody(body).addHeader("Content-Type", contentType);
    return post(request);
  }

  public <T> HttpResponse<T> post(String uri, Map<String, Object> body, String contentType, Type responseType) {
    if (body == null || body.isEmpty()) {
      return post(uri, contentType, responseType);
    } else {
      StringBuilder builder = new StringBuilder();
      body.forEach((key, value) -> {
        builder.append('&');
        builder.append(key);
        builder.append('=');
        builder.append(value);
      });
      return post(uri, builder.substring(1), contentType, responseType);
    }
  }

  public <T> HttpResponse<T> post(String uri, byte[] body, String contentType, Type responseType) {
    PostRequest request = new PostRequest(uri, byte[].class, responseType);
    request.setBody(body).addHeader("Content-Type", contentType);
    return post(request);
  }

  public <T> HttpResponse<T> post(PostRequest request) {
    return request(request);
  }

  public <T> HttpResponse<T> request(HttpRequest request) {
    request.merge(globalConfiguration);
    HttpResponse<T> response = execute(request);
    return response.setRequest(request);
  }

  protected abstract <T> HttpResponse<T> execute(HttpRequest request);
}
