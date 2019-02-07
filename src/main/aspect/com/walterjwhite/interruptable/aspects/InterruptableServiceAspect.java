package com.walterjwhite.interruptable.aspects;

import com.walterjwhite.interruptable.enumeration.InterruptableDaemonType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class InterruptableServiceAspect {
  /**
   * Register services
   *
   * @param point
   * @return
   * @throws Throwable
   */
  @Before(
      "execution(*.new(..)) && @within(com.walterjwhite.interruptable.annotation.InterruptableService) && !within(com.walterjwhite.interruptable.*)")
  public void doInterruptableService(
      JoinPoint joinPoint /*, InterruptableService interruptableService*/) throws Throwable {
    InterruptableDaemonType.Service.register(joinPoint);
  }
}
