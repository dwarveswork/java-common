/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

import com.dwarveswork.exception.RemoteExecutionException;
import com.dwarveswork.exception.RemoteTimeoutException;
import com.dwarveswork.lang.NamedThreadFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.ResponseInfo;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DefaultHttpExecutor extends HttpExecutor {

  private static final String DEFAULT_EXECUTOR_THREAD_NAME = "HttpExecutor";
  private static final int DEFAULT_EXECUTOR_THREADS = 10;
  private static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(3);

  private final HttpClient client;
  private final Gson gson;

  public DefaultHttpExecutor() {
    super();
    CookieManager cookieManager = new CookieManager();
    cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    ThreadFactory threadFactory = new NamedThreadFactory(DEFAULT_EXECUTOR_THREAD_NAME);
    Executor executor = Executors.newFixedThreadPool(DEFAULT_EXECUTOR_THREADS, threadFactory);
    this.client = HttpClient.newBuilder().connectTimeout(DEFAULT_CONNECT_TIMEOUT).cookieHandler(cookieManager).executor(executor).build();
    this.gson = new GsonBuilder().create();
  }

  public DefaultHttpExecutor(String name, int threadCount, Duration connectTimeout, HttpExecutorConfiguration configuration) {
    super(configuration);
    CookieManager cookieManager = new CookieManager();
    cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    ThreadFactory threadFactory = new NamedThreadFactory(name);
    Executor executor = Executors.newFixedThreadPool(threadCount, threadFactory);
    this.client = HttpClient.newBuilder().connectTimeout(connectTimeout).cookieHandler(cookieManager).executor(executor).build();
    this.gson = new GsonBuilder().create();
  }

  public DefaultHttpExecutor(HttpClient client, Gson gson) {
    super();
    this.client = client;
    this.gson = gson;
  }

  public DefaultHttpExecutor(HttpClient client, Gson gson, HttpExecutorConfiguration configuration) {
    super(configuration);
    this.client = client;
    this.gson = gson;
  }

  @Override
  protected <T> HttpResponse<T> execute(HttpRequest request) {
    BodyPublisher bodyPublisher = getBodyPublisher(request);
    java.net.http.HttpRequest.Builder builder = java.net.http.HttpRequest.newBuilder(URI.create(request.getUri())).timeout(request.getExecuteTimeout())
                                                                         .method(request.getMethod(), bodyPublisher);
    request.getHeaders().forEach(builder::header);
    try {
      java.net.http.HttpResponse<?> response = client.send(builder.build(), responseInfo -> getResponseBodyHandler(responseInfo, request.getResponseType()));
      HttpResponse<T> httpResponse = new HttpResponse<>();
      httpResponse.setVersion(response.version().name()).setUri(response.uri().toString()).setStatusCode(response.statusCode())
                  .setHeaders(response.headers().map()).setBody(parseBody(response.body(), request.getResponseType()));
      return httpResponse;
    } catch (HttpConnectTimeoutException ex) {
      throw new RemoteTimeoutException(request.getUri(), ex);
    } catch (IOException | InterruptedException ex) {
      throw new RemoteExecutionException(request.getUri(), ex);
    }
  }

  private BodyPublisher getBodyPublisher(HttpRequest request) {
    if (request.getBody() == null) {
      return BodyPublishers.noBody();
    }
    if (request.getRequestType() == byte[].class) {
      return BodyPublishers.ofByteArray((byte[]) request.getBody());
    } else if (request.getRequestType() == String.class) {
      return BodyPublishers.ofString((String) request.getBody());
    } else {
      return BodyPublishers.ofString(gson.toJson(request.getBody(), request.getRequestType()));
    }
  }

  private BodySubscriber<?> getResponseBodyHandler(ResponseInfo responseInfo, Type responseType) {
    if (responseType == byte[].class) {
      return BodyHandlers.ofByteArray().apply(responseInfo);
    } else {
      return BodyHandlers.ofString().apply(responseInfo);
    }
  }

  @SuppressWarnings("unchecked")
  protected <T, U> U parseBody(T body, Type responseType) {
    if (responseType == byte[].class) {
      return (U) body;
    } else if (responseType == String.class) {
      return (U) body;
    } else {
      return gson.fromJson(body.toString(), responseType);
    }
  }
}
