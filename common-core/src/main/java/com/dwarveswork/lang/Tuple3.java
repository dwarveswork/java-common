/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.lang;

public class Tuple3<A, B, C> {

  private final A a;
  private final B b;
  private final C c;

  public Tuple3(A a, B b, C c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public A getA() {
    return a;
  }

  public B getB() {
    return b;
  }

  public C getC() {
    return c;
  }

  @Override
  public String toString() {
    return "Tuple3{" +
        "a=" + a +
        ", b=" + b +
        ", c=" + c +
        '}';
  }
}
