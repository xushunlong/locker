package com.example.shedlock.selfLock.aop;

import com.example.shedlock.selfLock.locker.LockerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
@Order(1)
public class PerformanceResolver {
    @Around("@annotation(com.example.shedlock.selfLock.aop.Performance)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Method method = processLockedMethod(proceedingJoinPoint);
        Performance performance = AnnotatedElementUtils.getMergedAnnotation(method, Performance.class);
        String info = performance.info();

        long start = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("process method time: {}", end - start);

        return proceed;
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

}
