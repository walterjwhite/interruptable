package com.walterjwhite.interruptable;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public interface Interruptable {
  void interrupt();

  default Duration getInterruptGracePeriodTimeout() {
    return Duration.of(1000L, ChronoUnit.MILLIS);
  }
}
