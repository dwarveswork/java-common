/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.lang;

import java.util.Objects;

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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;
    return Objects.equals(a, tuple3.a) && Objects.equals(b, tuple3.b) && Objects.equals(c, tuple3.c);
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, b, c);
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
