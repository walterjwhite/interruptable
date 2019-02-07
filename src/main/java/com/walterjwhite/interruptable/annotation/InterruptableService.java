package com.walterjwhite.interruptable.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InterruptableService {
  Class[] dependencies() default {};
}
