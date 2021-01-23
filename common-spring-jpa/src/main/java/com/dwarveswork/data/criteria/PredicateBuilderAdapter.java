/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.data.criteria;

import com.dwarveswork.data.Predicate;
import com.dwarveswork.exception.NotImplementedException;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

public class PredicateBuilderAdapter<T> {

  private final PredicateBuilder<T> builder;
  private final TypedPredicate<T> predicate;

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public PredicateBuilderAdapter(Predicate predicate) {
    if (predicate.getValue() == null || predicate.getValue() instanceof String) {
      this.builder = (PredicateBuilder) new StringPredicateBuilder();
      this.predicate = (TypedPredicate) new TypedPredicate<>(predicate.getProperty(), predicate.getMatcher(), (String) predicate.getValue());
    } else if (predicate.getValue() instanceof Integer) {
      this.builder = (PredicateBuilder) new IntegerPredicateBuilder();
      this.predicate = (TypedPredicate) new TypedPredicate<>(predicate.getProperty(), predicate.getMatcher(), (Integer) predicate.getValue());
    } else if (predicate.getValue() instanceof Long) {
      this.builder = (PredicateBuilder) new LongPredicateBuilder();
      this.predicate = (TypedPredicate) new TypedPredicate<>(predicate.getProperty(), predicate.getMatcher(), (Long) predicate.getValue());
    } else if (predicate.getValue() instanceof Date) {
      this.builder = (PredicateBuilder) new DatePredicateBuilder();
      this.predicate = (TypedPredicate) new TypedPredicate<>(predicate.getProperty(), predicate.getMatcher(), (Date) predicate.getValue());
    } else {
      this.builder = (PredicateBuilder) new DefaultPredicateBuilder();
      this.predicate = (TypedPredicate) new TypedPredicate<>(predicate.getProperty(), predicate.getMatcher(), predicate.getValue());
    }
  }

  public <E> javax.persistence.criteria.Predicate build(Root<E> root, CriteriaBuilder cb) {
    switch (predicate.getMatcher()) {
      case EQUALS:
        return builder.buildEqualsPredicate(root, cb, predicate);
      case CONTAINS:
        return builder.buildContainsPredicate(root, cb, predicate);
      case START_WITH:
        return builder.buildStartWithPredicate(root, cb, predicate);
      case END_WITH:
        return builder.buildEndWithPredicate(root, cb, predicate);
      case GT:
        return builder.buildGreaterThanPredicate(root, cb, predicate);
      case GTE:
        return builder.buildGreaterThanOrEqualToPredicate(root, cb, predicate);
      case LT:
        return builder.buildLessThanPredicate(root, cb, predicate);
      case LTE:
        return builder.buildLessThanOrEqualToPredicate(root, cb, predicate);
      default:
        throw new NotImplementedException();
    }
  }
}
