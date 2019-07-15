package com.walterjwhite.interruptable.enumeration;

// this is used to control the shutdown order
public interface InterruptableTypeShutdownOrder {
  Enum[] Default =
      new Enum[] {
        InterruptableInvocationType.Task,
        InterruptableDaemonType.Service,
        InterruptableInvocationType.Application
      };
}
