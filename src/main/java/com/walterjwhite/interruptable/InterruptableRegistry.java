package com.walterjwhite.interruptable;

// import com.walterjwhite.logging.annotation.LogStackTrace;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.aspectj.lang.JoinPoint;

public class InterruptableRegistry {
  // during initialization, only a single thread should be creating these instances
  // during shutdown, only a single thread should be destroying these instances
  protected final Set<InterruptableWeakReference> interruptableReferences = new HashSet<>();

  static {
    registerShutdownHook();
  }

  private static void registerShutdownHook() {
    Runtime.getRuntime().addShutdownHook(new InterruptableShutdownHook());
  }

  public void add(final JoinPoint joinPoint) {
    interruptableReferences.add(
        new InterruptableWeakReference(
            new WeakReference<>(joinPoint), new WeakReference<>(Thread.currentThread())));
  }

  public void remove(final JoinPoint joinPoint) {
    interruptableReferences.removeIf(
        interruptableWeakReference ->
            joinPoint.equals(interruptableWeakReference.getJoinPointWeakReference().get()));
  }

  public void interrupt() {
    final InterruptableWeakReference[] orderedServices = getOrderedServices();
    Arrays.stream(orderedServices)
        .forEach(interruptableReference -> interrupt(interruptableReference));
  }

  // placeholder until dependency resolution is implemented
  protected InterruptableWeakReference[] getOrderedServices() {
    return interruptableReferences.toArray(
        new InterruptableWeakReference[interruptableReferences.size()]);
  }

  protected void interrupt(InterruptableWeakReference interruptableWeakReference) {
    if (!canInterrupt(interruptableWeakReference)) return;

    final Interruptable interruptable =
        (Interruptable) interruptableWeakReference.getJoinPointWeakReference().get().getTarget();
    final Thread thread = interruptableWeakReference.getThreadWeakReference().get();
    if (interruptable != null) doInterrupt(interruptable, thread);
  }

  protected boolean canInterrupt(InterruptableWeakReference interruptableWeakReference) {
    return interruptableWeakReference != null
        && interruptableWeakReference.getJoinPointWeakReference() != null
        && interruptableWeakReference.getJoinPointWeakReference().get() != null
        && interruptableWeakReference.getJoinPointWeakReference().get().getTarget() != null
        && Interruptable.class.isAssignableFrom(
            interruptableWeakReference.getJoinPointWeakReference().get().getTarget().getClass());
  }

  protected void doInterrupt(Interruptable interruptable, Thread thread) {
    interruptable.interrupt();
    provideGracePeriod(interruptable, thread);
  }

  protected void provideGracePeriod(Interruptable interruptable, Thread thread) {
    try {
      if (isThreadTerminatable(thread))
        thread.join(interruptable.getInterruptGracePeriodTimeout().toMillis());
    } catch (InterruptedException e) {
      handleInterruptException(e);
    }
  }

  protected boolean isThreadTerminatable(final Thread thread) {
    return isDifferentThread(thread) && thread.isAlive();
  }

  protected boolean isDifferentThread(final Thread thread) {
    return Thread.currentThread().getId() != thread.getId();
  }

  // TODO: fix lombok ordering here ...
  // @LogStackTrace
  protected void handleInterruptException(InterruptedException interruptedException) {}

  public void interrupt(Interruptable interruptable) {}
}
