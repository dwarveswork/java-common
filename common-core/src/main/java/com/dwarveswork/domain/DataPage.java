/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataPage<T extends Serializable> implements Serializable {

  private static final long serialVersionUID = -4281239760136999779L;

  private Integer pageIndex;
  private Integer pageSize;
  private Integer pageCount;
  private Integer totalCount;
  private List<T> data;

  public Integer getPageIndex() {
    return pageIndex;
  }

  public DataPage<T> setPageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
    return this;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public DataPage<T> setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  public Integer getPageCount() {
    return pageCount;
  }

  public DataPage<T> setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
    return this;
  }

  public Integer getTotalCount() {
    return totalCount;
  }

  public DataPage<T> setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  public List<T> getData() {
    return data;
  }

  public DataPage<T> setData(List<T> data) {
    this.data = data;
    return this;
  }

  public DataPage() {}

  public <U extends Serializable> DataPage<U> map(Function<T, U> converter) {
    DataPage<U> result = new DataPage<>();
    result.setPageIndex(this.pageIndex).setPageSize(this.pageSize).setPageCount(this.pageCount).setTotalCount(this.totalCount)
          .setData(this.data.stream().map(converter).collect(Collectors.toList()));
    return result;
  }

  public static <T extends Serializable> DataPage<T> of(int pageIndex, int pageSize, int totalCount, List<T> data) {
    DataPage<T> dataPage = new DataPage<>();
    dataPage.pageIndex = pageIndex <= 0 ? 1 : pageIndex;
    dataPage.pageSize = pageSize <= 0 ? 20 : pageSize;
    dataPage.totalCount = totalCount;
    dataPage.pageCount = totalCount / dataPage.pageSize + (totalCount % dataPage.pageSize == 0 ? 0 : 1);
    dataPage.data = data != null ? data : new ArrayList<>();
    return dataPage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DataPage<?> dataPage = (DataPage<?>) o;
    return Objects.equals(pageIndex, dataPage.pageIndex) &&
        Objects.equals(pageSize, dataPage.pageSize) &&
        Objects.equals(pageCount, dataPage.pageCount) &&
        Objects.equals(totalCount, dataPage.totalCount) &&
        Objects.equals(data, dataPage.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageIndex, pageSize, pageCount, totalCount, data);
  }

  @Override
  public String toString() {
    return "DataPage{" +
        "pageIndex=" + pageIndex +
        ", pageSize=" + pageSize +
        ", pageCount=" + pageCount +
        ", totalCount=" + totalCount +
        ", data=" + data +
        '}';
  }
}
