package com.walterjwhite.interruptable.enumeration;

import com.walterjwhite.interruptable.InterruptableRegistry;
import org.aspectj.lang.JoinPoint;

// TODO: partially supported, service ordering needs to be respected
// this would be used to effectively terminate services such as the backend datastore, index service, queue service, etc.
// while ensuring the services are shutdown in the proper order
public enum InterruptableDaemonType implements InterruptableTypeDelegate{
    Service;

    private final InterruptableRegistry interruptableRegistry = new InterruptableRegistry();

    public void register(JoinPoint joinPoint) {
        interruptableRegistry.add(joinPoint);
    }

    public void deregister(JoinPoint joinPoint) {
        interruptableRegistry.remove(joinPoint);
    }

    @Override
    public void interrupt() {
        interruptableRegistry.interrupt();
    }
}
