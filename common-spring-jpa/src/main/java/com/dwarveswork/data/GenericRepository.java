/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2021 dwarveswork
 */

package com.dwarveswork.data;

import com.dwarveswork.data.criteria.PredicateBuilderAdapter;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T extends Serializable, ID> extends JpaRepositoryImplementation<T, ID> {

  default DataPage<T> findPage(FindPageRequest request) {
    Specification<T> specification = generateSpecification(request);
    Sort sort = generateSort(request.getSort());
    Pageable pageable = PageRequest.of(request.getPageIndex() - 1, request.getPageSize(), sort);
    Page<T> data = this.findAll(specification, pageable);
    return DataPage.of(data.getNumber() + 1, data.getSize(), data.getTotalElements(), data.getContent());
  }

  default List<T> findList(FindListRequest request) {
    Specification<T> specification = generateSpecification(request);
    Sort sort = generateSort(request.getSort());
    return this.findAll(specification, sort);
  }

  default Specification<T> generateSpecification(FindListRequest request) {
    return (root, cq, cb) -> {
      List<Predicate> predicates = request.getPredicates().stream()
                                          .map(p -> new PredicateBuilderAdapter<>(p).build(root, cb))
                                          .filter(Objects::nonNull)
                                          .collect(Collectors.toList());
      if (predicates.isEmpty()) {
        return cb.isTrue(cb.literal(true));
      }
      if (predicates.size() == 1) {
        return predicates.iterator().next();
      }
      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }

  default Sort generateSort(List<SortOrder> sort) {
    return Sort.by(sort.stream().map(
        o -> new Order(Direction.valueOf(o.getDirection().name()), o.getProperty())
    ).collect(Collectors.toList()));
  }
}
