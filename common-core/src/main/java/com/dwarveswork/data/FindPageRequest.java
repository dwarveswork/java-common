/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FindPageRequest extends FindListRequest {

  private static final long serialVersionUID = 7216771532761413107L;

  @NotNull
  @Min(value = 1)
  private Integer pageIndex;
  @NotNull
  private Integer pageSize;

  public Integer getPageIndex() {
    return pageIndex;
  }

  public FindPageRequest setPageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
    return this;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public FindPageRequest setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  @Override
  public String toString() {
    return "FindPageRequest{" +
        "pageIndex=" + pageIndex +
        ", pageSize=" + pageSize +
        ", predicates=" + predicates +
        ", sort=" + sort +
        '}';
  }
}
