package com.example.shedlock.selfLock.aop;

import com.example.shedlock.selfLock.locker.Locker;
import com.example.shedlock.selfLock.locker.LockerConfiguration;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Timestamp;

@Component
public class LockerConfigurationExtractor {
    public LockerConfiguration getLockerConfiguration(ProceedingJoinPoint proceedingJoinPoint) {
        Method method = processLockedMethod(proceedingJoinPoint);

        return processLockerConfiguration(method);
    }

    private Method processLockedMethod(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            Signature sig = proceedingJoinPoint.getSignature();
            MethodSignature methodSignature = null;
            if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("illegal arguement!");
            }
            methodSignature = (MethodSignature) sig;
            Class<?> targetClass = AopUtils.getTargetClass(proceedingJoinPoint.getTarget());

            return targetClass.getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private LockerConfiguration processLockerConfiguration(Method method) {
        Locker targetAnnotation = AnnotatedElementUtils.getMergedAnnotation(method, Locker.class);
        LockerConfiguration lockerConfiguration = new LockerConfiguration();
        lockerConfiguration.setName(targetAnnotation.name());
        lockerConfiguration.setLockUntil(processTime(targetAnnotation.lockMillSeconds()));
        lockerConfiguration.setRetry(targetAnnotation.retry());
        lockerConfiguration.setRetryTimes(targetAnnotation.retryTimes());

        return lockerConfiguration;
    }

    private Timestamp processTime(int timeOffset) {
        long l = System.currentTimeMillis();
        return new Timestamp(l + timeOffset);
    }
}
