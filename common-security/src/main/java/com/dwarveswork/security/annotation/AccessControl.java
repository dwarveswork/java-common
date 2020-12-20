package com.dwarveswork.security.annotation;

import com.dwarveswork.security.Role;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessControl {

  boolean value() default true;

  Role[] roles() default { Role.USER };
}
