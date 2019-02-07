package com.walterjwhite.interruptable;

import java.lang.ref.WeakReference;
import org.aspectj.lang.JoinPoint;

public class InterruptableWeakReference {
  protected final WeakReference<JoinPoint> joinPointWeakReference;
  protected final WeakReference<Thread> threadWeakReference;

  public InterruptableWeakReference(
      WeakReference<JoinPoint> joinPointWeakReference, WeakReference<Thread> threadWeakReference) {
    this.joinPointWeakReference = joinPointWeakReference;
    this.threadWeakReference = threadWeakReference;
  }

  public WeakReference<JoinPoint> getJoinPointWeakReference() {
    return joinPointWeakReference;
  }

  public WeakReference<Thread> getThreadWeakReference() {
    return threadWeakReference;
  }
}
