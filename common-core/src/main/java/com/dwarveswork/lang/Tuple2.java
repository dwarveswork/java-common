/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.lang;

public class Tuple2<A, B> {

  private final A a;
  private final B b;

  public Tuple2(A a, B b) {
    this.a = a;
    this.b = b;
  }

  public A getA() {
    return a;
  }

  public B getB() {
    return b;
  }

  @Override
  public String toString() {
    return "Tuple2{" +
        "a=" + a +
        ", b=" + b +
        '}';
  }
}
