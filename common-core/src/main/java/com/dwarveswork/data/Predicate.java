/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.data;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Predicate implements Serializable {

  private static final long serialVersionUID = -1699175034536233435L;

  @NotBlank
  private String property;
  @NotNull
  private PredicateMatcher matcher;
  private Object value;

  public String getProperty() {
    return property;
  }

  public Predicate setProperty(String property) {
    this.property = property;
    return this;
  }

  public PredicateMatcher getMatcher() {
    return matcher;
  }

  public Predicate setMatcher(PredicateMatcher matcher) {
    this.matcher = matcher;
    return this;
  }

  public Object getValue() {
    return value;
  }

  public Predicate setValue(Object value) {
    this.value = value;
    return this;
  }

  @Override
  public String toString() {
    return "Predicate{" +
        "property='" + property + '\'' +
        ", matcher=" + matcher +
        ", value=" + value +
        '}';
  }
}
