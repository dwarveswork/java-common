/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserPrincipal implements Serializable {

  private static final long serialVersionUID = -4899474267510304785L;

  @NotNull
  private final Long id;
  @NotBlank
  private final String name;
  @NotEmpty
  private final List<Role> roles;

  public UserPrincipal(@NotNull Long id, @NotBlank String name, @NotEmpty List<Role> roles) {
    this.id = id;
    this.name = name;
    this.roles = roles;
  }

  @NotNull
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public boolean hasRole(Role role) {
    if (roles.contains(Role.WILDCARD)) {
      return true;
    }
    return roles.contains(role);
  }

  public boolean hasAnyRole(Collection<Role> roles) {
    if (roles == null || roles.isEmpty()) {
      return true;
    }
    if (this.roles.contains(Role.WILDCARD)) {
      return true;
    }
    return roles.stream().anyMatch(this.roles::contains);
  }

  @Override
  public String toString() {
    return "UserPrincipal{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", roles=" + roles +
        '}';
  }
}
