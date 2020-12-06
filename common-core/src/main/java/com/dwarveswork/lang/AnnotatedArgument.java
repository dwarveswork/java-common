/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.lang;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class AnnotatedArgument {

  private final Annotation annotation;
  private final Type argumentType;
  private final Object argument;

  public AnnotatedArgument(Annotation annotation, Object argument, Type argumentType) {
    this.annotation = annotation;
    this.argument = argument;
    this.argumentType = argumentType;
  }

  public Annotation getAnnotation() {
    return annotation;
  }

  public Object getArgument() {
    return argument;
  }

  public Type getArgumentType() {
    return argumentType;
  }

  @Override
  public String toString() {
    return "AnnotatedArgument{" +
        "annotation=" + annotation +
        ", argumentType=" + argumentType +
        ", argument=" + argument +
        '}';
  }
}
