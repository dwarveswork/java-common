/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.security;

import com.dwarveswork.security.annotation.AccessControl;

public interface SecurityService {

  String generateToken(UserPrincipal principal);

  UserPrincipal parseToken(String token);

  boolean isAuthorized(AccessControl accessControl, UserPrincipal principal);
}
