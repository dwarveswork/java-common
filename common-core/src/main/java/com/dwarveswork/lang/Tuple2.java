/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.lang;

import java.util.Objects;

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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
    return Objects.equals(a, tuple2.a) && Objects.equals(b, tuple2.b);
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, b);
  }

  @Override
  public String toString() {
    return "Tuple2{" +
        "a=" + a +
        ", b=" + b +
        '}';
  }
}
