package com.example.shedlock;

import com.example.shedlock.selfLock.locker.Locker;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SchedulerTaskTrigger {

    @Locker(name = "test")
    @Scheduled(cron = "*/2 * * * * *")
    public void scheduledTask1() {
        System.out.println("end application" + Thread.currentThread().getName());
    }

}
