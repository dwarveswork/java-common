/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

import com.dwarveswork.exception.RemoteExecutionException;
import com.dwarveswork.exception.RemoteTimeoutException;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpTemplate {

  private static final Logger LOGGER = LogManager.getLogger();

  private final HttpClient client;

  public HttpTemplate() {
    CookieManager cookieManager = new CookieManager();
    cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    this.client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).cookieHandler(cookieManager).build();
  }

  public HttpTemplate(HttpClient client) {
    this.client = client;
  }

  public HttpResponse<String> getAsString(String url) {
    HttpRequest request = HttpRequest.newBuilder().timeout(Duration.ofSeconds(10)).uri(URI.create(url)).GET().build();
    return execute(request, HttpResponse.BodyHandlers.ofString());
  }

  public HttpResponse<String> postAsString(String url, Map<String, Object> form) {
    HttpRequest request = HttpRequest.newBuilder().timeout(Duration.ofSeconds(10)).uri(URI.create(url))
                                     .POST(formBodyPublisher(form)).header("Content-Type", "application/x-www-form-urlencoded").build();
    return execute(request, HttpResponse.BodyHandlers.ofString());
  }

  protected <T> HttpResponse<T> execute(HttpRequest request, BodyHandler<T> handler) {
    try {
      return client.send(request, handler);
    } catch (HttpConnectTimeoutException ex) {
      throw new RemoteTimeoutException(request.uri().toString(), ex);
    } catch (IOException | InterruptedException ex) {
      throw new RemoteExecutionException(request.uri().toString(), ex);
    }
  }

  public BodyPublisher formBodyPublisher(Map<String, Object> form) {
    if (form == null || form.isEmpty()) {
      return BodyPublishers.noBody();
    }
    StringBuilder builder = new StringBuilder();
    for (Entry<String, Object> entry : form.entrySet()) {
      builder.append('&');
      builder.append(entry.getKey());
      builder.append('=');
      builder.append(entry.getValue());
    }
    return BodyPublishers.ofString(builder.substring(1), StandardCharsets.UTF_8);
  }
}
