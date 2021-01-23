/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.data.criteria;

import com.dwarveswork.exception.NotImplementedException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class PredicateBuilder<T> {

  protected <E, P> Path<P> getPath(Path<E> parent, String property) {
    int index = property.indexOf(".");
    if (index < 0) {
      return parent.get(property);
    }
    return getPath(parent.get(property.substring(0, index)), property.substring(index + 1));
  }

  public <E> Predicate buildEqualsPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<T> predicate) {
    if (predicate.getValue() == null) {
      return cb.isNull(getPath(root, predicate.getProperty()));
    }
    return cb.equal(getPath(root, predicate.getProperty()), predicate.getValue());
  }

  public <E> Predicate buildContainsPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<T> predicate) {
    throw new NotImplementedException();
  }

  public <E> Predicate buildStartWithPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<T> predicate) {
    throw new NotImplementedException();
  }

  public <E> Predicate buildEndWithPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<T> predicate) {
    throw new NotImplementedException();
  }

  public <E> Predicate buildGreaterThanPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<T> predicate) {
    throw new NotImplementedException();
  }

  public <E> Predicate buildGreaterThanOrEqualToPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<T> predicate) {
    throw new NotImplementedException();
  }

  public <E> Predicate buildLessThanPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<T> predicate) {
    throw new NotImplementedException();
  }

  public <E> Predicate buildLessThanOrEqualToPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<T> predicate) {
    throw new NotImplementedException();
  }
}
