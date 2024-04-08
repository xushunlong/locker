package com.example.shedlock.selfLock.locker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Locker {
    String name() default "";

    int lockMillSeconds() default 100;

    boolean retry() default true;

    int retryTimes() default 3;

    String desc() default "locked status, try again...";
}
