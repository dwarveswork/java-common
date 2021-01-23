/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FindListRequest implements Serializable {

  private static final long serialVersionUID = 4669659047081877848L;

  protected List<Predicate> predicates = new ArrayList<>();
  protected List<SortOrder> sort = new ArrayList<>();

  public List<Predicate> getPredicates() {
    return predicates;
  }

  public FindListRequest setPredicates(List<Predicate> predicates) {
    this.predicates = predicates;
    return this;
  }

  public List<SortOrder> getSort() {
    return sort;
  }

  public FindListRequest setSort(List<SortOrder> sort) {
    this.sort = sort;
    return this;
  }

  @Override
  public String toString() {
    return "FindListRequest{" +
        "predicates=" + predicates +
        ", sort=" + sort +
        '}';
  }
}
