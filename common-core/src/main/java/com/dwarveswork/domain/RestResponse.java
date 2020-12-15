/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.domain;

import com.dwarveswork.exception.GenericException;
import java.io.Serializable;

public class RestResponse<T extends Serializable> implements Serializable {

  private static final long serialVersionUID = -6418413447074549769L;

  public static final int OK = 200;

  private final int status;
  private final String message;
  private final T body;

  public RestResponse(int status, String message) {
    this.status = status;
    this.message = message;
    this.body = null;
  }

  public RestResponse(T body) {
    this.status = 200;
    this.message = null;
    this.body = body;
  }

  public RestResponse(GenericException ex) {
    this.status = ex.getStatus();
    this.message = ex.getLocalizedMessage();
    this.body = null;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public T getBody() {
    return body;
  }

  @Override
  public String toString() {
    return "RestResponse{" +
        "status=" + status +
        ", message='" + message + '\'' +
        ", body=" + body +
        '}';
  }
}
