/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.data;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;

public class SortOrder implements Serializable {

  private static final long serialVersionUID = -9165154007060660945L;

  @NotBlank
  private String property;
  private SortDirection direction = SortDirection.ASC;

  public String getProperty() {
    return property;
  }

  public SortOrder setProperty(String property) {
    this.property = property;
    return this;
  }

  public SortDirection getDirection() {
    return direction;
  }

  public SortOrder setDirection(SortDirection direction) {
    this.direction = direction;
    return this;
  }

  @Override
  public String toString() {
    return "SortOrder{" +
        "property='" + property + '\'' +
        ", direction=" + direction +
        '}';
  }
}
