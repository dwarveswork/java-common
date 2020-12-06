/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

import static org.junit.jupiter.api.Assertions.*;

import java.io.Serializable;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DefaultHttpExecutorTest {

  private final HttpExecutor httpExecutor = new DefaultHttpExecutor();

  static class HttpBinResponse implements Serializable {

    private static final long serialVersionUID = 9029143768039672737L;

    private Map<String, Object> args;
    private Map<String, String> headers;
    private String origin;
    private String url;

    public Map<String, Object> getArgs() {
      return args;
    }

    public HttpBinResponse setArgs(Map<String, Object> args) {
      this.args = args;
      return this;
    }

    public Map<String, String> getHeaders() {
      return headers;
    }

    public HttpBinResponse setHeaders(Map<String, String> headers) {
      this.headers = headers;
      return this;
    }

    public String getOrigin() {
      return origin;
    }

    public HttpBinResponse setOrigin(String origin) {
      this.origin = origin;
      return this;
    }

    public String getUrl() {
      return url;
    }

    public HttpBinResponse setUrl(String url) {
      this.url = url;
      return this;
    }
  }

  @Test
  void testGet() {
    String url = "https://httpbin.org/get";
    HttpResponse<HttpBinResponse> response = httpExecutor.get(url, String.class);
    System.out.println(response);
  }

  @Test
  void testPost() {
    String url = "https://httpbin.org/post";
    HttpResponse<String> response = httpExecutor.post(url, String.class);
    System.out.println(response);
  }

  @Test
  void testGet2() {
    String url = "https://api.huobi.pro/market/depth?symbol=daiusdt&type=step0";
    HttpResponse<String> response = httpExecutor.get(url, String.class);
    System.out.println(response);
  }
}