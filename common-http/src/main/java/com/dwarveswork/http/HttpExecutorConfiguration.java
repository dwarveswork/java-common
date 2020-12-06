/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class HttpExecutorConfiguration {

  private static final Duration DEFAULT_EXECUTE_TIMEOUT = Duration.ofSeconds(3);
  private static final Map<String, String> DEFAULT_HEADERS = new HashMap<>();

  private final Duration executeTimeout;
  private final Map<String, String> headers;

  public HttpExecutorConfiguration() {
    this.executeTimeout = DEFAULT_EXECUTE_TIMEOUT;
    this.headers = DEFAULT_HEADERS;
  }

  public HttpExecutorConfiguration(Duration executeTimeout, Map<String, String> headers) {
    this.executeTimeout = executeTimeout == null ? DEFAULT_EXECUTE_TIMEOUT : executeTimeout;
    this.headers = headers == null ? DEFAULT_HEADERS : headers;
  }

  public Duration getExecuteTimeout() {
    return executeTimeout;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  @Override
  public String toString() {
    return "HttpExecutorConfiguration{" +
        "executeTimeout=" + executeTimeout +
        ", headers=" + headers +
        '}';
  }
}
