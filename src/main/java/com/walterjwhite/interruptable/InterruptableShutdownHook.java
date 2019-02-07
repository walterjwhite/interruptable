package com.walterjwhite.interruptable;

import com.walterjwhite.interruptable.enumeration.InterruptableTypeShutdownOrder;
import com.walterjwhite.interruptable.enumeration.InterruptableTypeDelegate;

import java.util.Arrays;

public class InterruptableShutdownHook extends Thread {
  @Override
  public void run() {
    doInterrupt();
  }

  protected void doInterrupt() {
    Arrays.stream(InterruptableTypeShutdownOrder.Default)
        .forEach(interruptableInvocationType -> doInterruptType(interruptableInvocationType));
  }

  protected void doInterruptType(Enum interruptableEnum) {
    ((InterruptableTypeDelegate) interruptableEnum).interrupt();
  }
}
