/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.data.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LongPredicateBuilder extends PredicateBuilder<Long> {

  @Override
  public <E> Predicate buildGreaterThanPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<Long> predicate) {
    return cb.greaterThanOrEqualTo(getPath(root, predicate.getProperty()), predicate.getValue());
  }

  @Override
  public <E> Predicate buildGreaterThanOrEqualToPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<Long> predicate) {
    return cb.greaterThanOrEqualTo(getPath(root, predicate.getProperty()), predicate.getValue());
  }

  @Override
  public <E> Predicate buildLessThanPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<Long> predicate) {
    return cb.lessThan(getPath(root, predicate.getProperty()), predicate.getValue());
  }

  @Override
  public <E> Predicate buildLessThanOrEqualToPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<Long> predicate) {
    return cb.greaterThanOrEqualTo(getPath(root, predicate.getProperty()), predicate.getValue());
  }
}
