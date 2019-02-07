package com.walterjwhite.interruptable.aspects;

import com.walterjwhite.interruptable.enumeration.InterruptableInvocationType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class InterruptableTaskAspect {
  /**
   * Wrap all InterruptableTask invocations
   *
   * @param point the method to interrupt if the JVM shuts down.
   * @return
   * @throws Throwable
   */
  @Around(
      "execution(* *(..)) && @annotation(com.walterjwhite.interruptable.annotation.InterruptableTask) && !within(com.walterjwhite.interruptable.*)")
  public Object doInterruptableTask(
      ProceedingJoinPoint proceedingJoinPoint /*, InterruptableTask interruptableTask*/)
      throws Throwable {
    return InterruptableInvocationType.Task.wrap(proceedingJoinPoint);
  }
}
