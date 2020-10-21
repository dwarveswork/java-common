/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.domain;

import java.io.Serializable;
import java.util.List;

public class PageRequest implements Serializable {

  private static final long serialVersionUID = 7216771532761413107L;

  private Integer pageIndex;
  private Integer pageSize;
  private List<Order> sort;

  public int getPageIndex() {
    return pageIndex;
  }

  public PageRequest setPageIndex(int pageIndex) {
    this.pageIndex = pageIndex;
    return this;
  }

  public int getPageSize() {
    return pageSize;
  }

  public PageRequest setPageSize(int pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  public List<Order> getSort() {
    return sort;
  }

  public PageRequest setSort(List<Order> sort) {
    this.sort = sort;
    return this;
  }

  @Override
  public String toString() {
    return "PageRequest{" +
        "pageIndex=" + pageIndex +
        ", pageSize=" + pageSize +
        ", sort=" + sort +
        '}';
  }

  public static enum Direction {
    ASC, DESC
  }

  public static class Order implements Serializable {

    private static final long serialVersionUID = 7091037785321775212L;

    private String property;
    private Direction direction;

    public String getProperty() {
      return property;
    }

    public Order setProperty(String property) {
      this.property = property;
      return this;
    }

    public Direction getDirection() {
      return direction;
    }

    public Order setDirection(Direction direction) {
      this.direction = direction;
      return this;
    }

    @Override
    public String toString() {
      return "Order{" +
          "property='" + property + '\'' +
          ", direction=" + direction +
          '}';
    }
  }
}
