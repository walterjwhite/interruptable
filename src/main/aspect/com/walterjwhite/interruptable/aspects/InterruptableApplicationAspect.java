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
   * @param proceedingJoinPoint the method to interrupt if the JVM shuts down.
   * @return result of invocation
   * @throws Throwable if invocation fails for any reason
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
