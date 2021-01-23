/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.data.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StringPredicateBuilder extends PredicateBuilder<String> {

  @Override
  public <E> Predicate buildContainsPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<String> predicate) {
    return cb.like(getPath(root, predicate.getProperty()), "%" + predicate.getValue() + "%");
  }

  @Override
  public <E> Predicate buildStartWithPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<String> predicate) {
    return cb.like(getPath(root, predicate.getProperty()), predicate.getValue() + "%");
  }

  @Override
  public <E> Predicate buildEndWithPredicate(Root<E> root, CriteriaBuilder cb, TypedPredicate<String> predicate) {
    return cb.like(getPath(root, predicate.getProperty()), "%" + predicate.getValue());
  }
}
