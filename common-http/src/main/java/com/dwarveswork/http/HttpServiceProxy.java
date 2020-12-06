/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.http;

import com.dwarveswork.exception.NotImplementedException;
import com.dwarveswork.exception.UnexpectedException;
import com.dwarveswork.exception.ValidationException;
import com.dwarveswork.http.annotation.Body;
import com.dwarveswork.http.annotation.GET;
import com.dwarveswork.http.annotation.Header;
import com.dwarveswork.http.annotation.Headers;
import com.dwarveswork.http.annotation.POST;
import com.dwarveswork.http.annotation.Query;
import com.dwarveswork.http.annotation.QueryMap;
import com.dwarveswork.http.annotation.Variable;
import com.dwarveswork.http.annotation.Variables;
import com.dwarveswork.lang.AnnotatedArgument;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpServiceProxy implements InvocationHandler {

  private static final Logger LOGGER = LogManager.getLogger();

  private final String baseUri;
  private final HttpResponseHandler responseHandler;
  private final HttpExecutor httpExecutor;

  public HttpServiceProxy(String baseUri) {
    this(baseUri, new DefaultHttpResponseHandler(), new DefaultHttpExecutor());
  }

  public HttpServiceProxy(String baseUri, HttpResponseHandler responseHandler) {
    this(baseUri, responseHandler, new DefaultHttpExecutor());
  }

  public HttpServiceProxy(String baseUri, HttpExecutor httpExecutor) {
    this(baseUri, new DefaultHttpResponseHandler(), httpExecutor);
  }

  public HttpServiceProxy(String baseUri, HttpResponseHandler responseHandler, HttpExecutor httpExecutor) {
    this.baseUri = baseUri;
    this.responseHandler = responseHandler;
    this.httpExecutor = httpExecutor;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) {
    if (method.getDeclaringClass() == Object.class) {
      return invokeObjectMethods(method, args);
    }
    if (method.isDefault()) {
      return invokeDefaultMethods(method, args);
    }
    return invokeHttpMethods(method, args);
  }

  private Object invokeObjectMethods(Method method, Object[] args) {
    try {
      return method.invoke(this, args);
    } catch (IllegalAccessException | InvocationTargetException ex) {
      throw new UnexpectedException(ex);
    }
  }

  private Object invokeDefaultMethods(Method method, Object[] args) {
    try {
      return MethodHandles.lookup().in(method.getDeclaringClass()).unreflectSpecial(method, method.getDeclaringClass()).bindTo(this).invokeWithArguments(args);
    } catch (Throwable ex) {
      throw new UnexpectedException(ex);
    }
  }

  private Object invokeHttpMethods(Method method, Object[] args) {
    for (Annotation annotation : method.getAnnotations()) {
      if (annotation instanceof GET) {
        return invokeGetRequest((GET) annotation, method, args);
      } else if (annotation instanceof POST) {
        return invokePostRequest((POST) annotation, method, args);
      }
    }
    throw new NotImplementedException("Could not found any valid HTTP annotation");
  }

  private Object invokeGetRequest(GET annotation, Method method, Object[] args) {
    Map<String, List<AnnotatedArgument>> arguments = parseArguments(method, args);
    String uri = generateUri(annotation.value(), arguments);
    Map<String, String> headers = generateHeaders(annotation.headers(), arguments);
    return invokeHttpRequest(uri, "GET", annotation.executeTimeoutMillis(), headers, Void.class, method.getReturnType(), null);
  }

  private Object invokePostRequest(POST annotation, Method method, Object[] args) {
    Map<String, List<AnnotatedArgument>> arguments = parseArguments(method, args);
    String uri = generateUri(annotation.value(), arguments);
    Map<String, String> headers = generateHeaders(annotation.headers(), arguments);
    List<AnnotatedArgument> bodies = arguments.get(Body.class.getSimpleName());
    if (bodies.size() == 0) {
      return invokeHttpRequest(uri, "POST", annotation.executeTimeoutMillis(), headers, Void.class, method.getReturnType(), null);
    } else if (bodies.size() > 1) {
      LOGGER.warn("Http annotation of Body should be unique, but found {}", bodies.size());
    }
    AnnotatedArgument body = bodies.get(0);
    return invokeHttpRequest(uri, "POST", annotation.executeTimeoutMillis(), headers, body.getArgumentType(), method.getReturnType(), body.getArgument());
  }

  private Map<String, List<AnnotatedArgument>> parseArguments(Method method, Object[] args) {
    Map<String, List<AnnotatedArgument>> annotatedArguments = new HashMap<>();
    Parameter[] parameters = method.getParameters();
    Type[] parameterTypes = method.getParameterTypes();
    for (int i = 0; i < parameters.length; ++i) {
      if (parameters[i].getAnnotations().length > 0) {
        for (Annotation annotation : parameters[i].getAnnotations()) {
          String key = annotation.annotationType().getSimpleName();
          List<AnnotatedArgument> arguments = annotatedArguments.getOrDefault(key, new ArrayList<>());
          arguments.add(new AnnotatedArgument(annotation, args[i], parameterTypes[i]));
          annotatedArguments.put(key, arguments);
        }
      } else {
        String key = Body.class.getSimpleName();
        List<AnnotatedArgument> arguments = annotatedArguments.getOrDefault(key, new ArrayList<>());
        arguments.add(new AnnotatedArgument(null, args[i], parameterTypes[i]));
        annotatedArguments.put(key, arguments);
      }
    }
    return annotatedArguments;
  }

  @SuppressWarnings("unchecked")
  private String generateUri(String path, Map<String, List<AnnotatedArgument>> arguments) {
    String uri = baseUri + path;
    Map<String, Object> substitution = new HashMap<>();
    List<AnnotatedArgument> variableArguments = arguments.get(Variable.class.getSimpleName());
    if (variableArguments != null) {
      variableArguments.forEach(a -> substitution.put(((Variable) a.getAnnotation()).value(), a.getArgument()));
    }
    List<AnnotatedArgument> variablesArguments = arguments.get(Variables.class.getSimpleName());
    if (variablesArguments != null) {
      variablesArguments.forEach(a -> substitution.putAll(((Map<String, Object>) a.getArgument())));
    }
    for (Entry<String, Object> item : substitution.entrySet()) {
      uri = uri.replaceAll("\\$\\{" + item.getKey() + "}", item.getValue().toString());
    }
    Map<String, String> queries = new HashMap<>();
    List<AnnotatedArgument> queryArguments = arguments.get(Query.class.getSimpleName());
    if (queryArguments != null) {
      queryArguments.forEach(q -> queries.put(((Query) q.getAnnotation()).value(), q.getArgument().toString()));
    }
    List<AnnotatedArgument> queryMapArguments = arguments.get(QueryMap.class.getSimpleName());
    if (queryMapArguments != null) {
      queryMapArguments.forEach(q -> queries.putAll((Map<String, String>) q.getArgument()));
    }
    if (!queries.isEmpty()) {
      if (!uri.contains("?")) {
        uri = uri + "?";
      }
      uri = uri + queries.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
    }
    return uri;
  }

  @SuppressWarnings("unchecked")
  private Map<String, String> generateHeaders(String[] headers, Map<String, List<AnnotatedArgument>> arguments) {
    Map<String, String> headerMap = new HashMap<>();
    if (headers.length % 2 != 0) {
      throw new ValidationException("Http annotation headers value should be in pair");
    }
    for (int i = 0; i < headers.length; i += 2) {
      headerMap.put(headers[i], headers[i + 1]);
    }
    List<AnnotatedArgument> headerArguments = arguments.get(Header.class.getSimpleName());
    if (headerArguments != null) {
      headerArguments.forEach(a -> headerMap.put(((Header) a.getAnnotation()).value(), a.getArgument().toString()));
    }
    List<AnnotatedArgument> headersArguments = arguments.get(Headers.class.getSimpleName());
    if (headersArguments != null) {
      headersArguments.forEach(a -> headerMap.putAll((Map<String, String>) a.getArgument()));
    }
    return headerMap;
  }

  private Object invokeHttpRequest(String uri, String method, int executeTimeoutMillis, Map<String, String> headers,
                                   Type requestType, Type responseType, Object request) {
    HttpRequest httpRequest;
    switch (method) {
      case "GET":
        httpRequest = new GetRequest(uri, responseType);
        break;
      case "POST":
        httpRequest = new PostRequest(uri, requestType, responseType);
        httpRequest.setBody(request);
        break;
      default:
        throw new NotImplementedException("Http method of " + method + " not implemented");
    }
    if (executeTimeoutMillis > 0) {
      httpRequest.setExecuteTimeout(Duration.ofMillis(executeTimeoutMillis));
    }
    if (!headers.isEmpty()) {
      httpRequest.setHeaders(headers);
    }
    HttpResponse<?> response = httpExecutor.request(httpRequest);
    return responseHandler.parseResponse(response);
  }
}
