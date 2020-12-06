/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.lang;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.validation.constraints.NotNull;

public class NamedThreadFactory implements ThreadFactory {

  private final String name;
  private final ThreadGroup group;
  private final AtomicInteger threadNumber = new AtomicInteger(1);

  public NamedThreadFactory(String name) {
    SecurityManager s = System.getSecurityManager();
    this.group = (s != null) ? s.getThreadGroup() :
        Thread.currentThread().getThreadGroup();
    this.name = name;
  }

  @Override
  public Thread newThread(@NotNull Runnable r) {
    Thread t = new Thread(group, r, name + "-" + threadNumber.getAndIncrement(), 0);
    if (t.isDaemon()) {
      t.setDaemon(false);
    }
    if (t.getPriority() != Thread.NORM_PRIORITY) {
      t.setPriority(Thread.NORM_PRIORITY);
    }
    return t;
  }
}
