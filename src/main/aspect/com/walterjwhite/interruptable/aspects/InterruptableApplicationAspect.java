package com.walterjwhite.interruptable.aspects;

import com.walterjwhite.interruptable.enumeration.InterruptableInvocationType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class InterruptableApplicationAspect {
  /**
   * Wrap all InterruptableApplication invocations
   *
   * @param point the method to interrupt if the JVM shuts down.
   * @return
   * @throws Throwable
   */
  @Around(
      "execution(* *(..)) && @annotation(com.walterjwhite.interruptable.annotation.InterruptableApplication) && !within(com.walterjwhite.interruptable.*)")
  public Object doInterruptableApplication(
      ProceedingJoinPoint
          proceedingJoinPoint /*, InterruptableApplication interruptableApplication*/)
      throws Throwable {
    return InterruptableInvocationType.Application.wrap(proceedingJoinPoint);
  }
}
