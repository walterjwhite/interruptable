package com.walterjwhite.interruptable.enumeration;

import com.walterjwhite.interruptable.InterruptableRegistry;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

// aspectj runs before, check if I need to swap the maven config
// @Getter
public enum InterruptableInvocationType implements InterruptableTypeDelegate{
  Task,
  Application;

  private final InterruptableRegistry interruptableRegistry = new InterruptableRegistry();

  public Object wrap(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    try {
      interruptableRegistry.add(proceedingJoinPoint);
      return proceedingJoinPoint.proceed();
    } finally {
      interruptableRegistry.remove(proceedingJoinPoint);
    }
  }

  @Override
  public void interrupt() {
    interruptableRegistry.interrupt();
  }
}
