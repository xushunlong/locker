package com.example.shedlock.selfLock.aop;

import com.example.shedlock.selfLock.locker.LockerConfiguration;
import com.example.shedlock.selfLock.locker.LockerExecutor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(1)
public class LockerResolver {

    @Autowired
    private LockerConfigurationExtractor lockerConfigurationExtractor;

    @Autowired
    private LockerExecutor lockerExecutor;

    @Around("@annotation(com.example.shedlock.selfLock.aop.Locker)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws InterruptedException {
        LockerConfiguration lockerConfiguration = lockerConfigurationExtractor.getLockerConfiguration(proceedingJoinPoint);

        for (int i = 0; i < lockerConfiguration.getRetryTimes(); i++) {
            if (lockerExecutor.lock(lockerConfiguration)) {
                try {
                    log.info("success get the lock, process do the execute logic");

                    return proceedingJoinPoint.proceed();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                } finally {
                    lockerExecutor.unlock(lockerConfiguration);
                }
            }
            Thread.sleep(500);
        }

        log.info("get distributed lock failed, locker name is: {}", lockerConfiguration.getName());
        return null;
    }

}
