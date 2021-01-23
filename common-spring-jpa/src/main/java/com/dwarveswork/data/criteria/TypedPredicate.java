/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.data.criteria;

import com.dwarveswork.data.Predicate;
import com.dwarveswork.data.PredicateMatcher;

public class TypedPredicate<T> {

  private final String property;
  private final PredicateMatcher matcher;
  private final T value;

  public TypedPredicate(String property, PredicateMatcher matcher, T value) {
    this.property = property;
    this.matcher = matcher;
    this.value = value;
  }

  @SuppressWarnings("unchecked")
  public static <T> TypedPredicate<T> of(Predicate predicate) {
    return new TypedPredicate<>(predicate.getProperty(), predicate.getMatcher(), (T) predicate.getValue());
  }

  public String getProperty() {
    return property;
  }

  public PredicateMatcher getMatcher() {
    return matcher;
  }

  public T getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "TypedPredicate{" +
        "property='" + property + '\'' +
        ", matcher=" + matcher +
        ", value=" + value +
        '}';
  }
}
