package com.example.shedlock;

import com.example.shedlock.selfLock.aop.Locker;
import com.example.shedlock.selfLock.aop.Performance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Slf4j
@Component
@EnableScheduling
public class SchedulerTaskTrigger {

    private int currentCount = 0;

    private Stack<String> taskList = new Stack<>();

    private int taskExecTime = 500;

    @Scheduled(cron = "* * * * * *")
    public void watcher() throws InterruptedException {
        log.info("current count: {}, tasklist: {}", currentCount, taskList.size());
    }

    @Performance
    @Locker(name = "test", lockMillSeconds = 1000)
    @Scheduled(cron = "*/2 * * * * *")
    public void scheduledTask() throws InterruptedException {
        currentCount--;
        taskList.push(Thread.currentThread().getName());
        Thread.sleep(taskExecTime);
        taskList.pop();
        log.info("task , thread :{}, current count: {}", Thread.currentThread().getName(), currentCount);
        currentCount++;
    }

    @Performance
    @Locker(name = "test", lockMillSeconds = 1000)
    @Scheduled(cron = "*/2 * * * * *")
    public void scheduledTask1() throws InterruptedException {
        currentCount--;
        taskList.push(Thread.currentThread().getName());
        Thread.sleep(taskExecTime);
        taskList.pop();
        log.info("task1 , thread :{}, current count: {}", Thread.currentThread().getName(), currentCount);
        currentCount++;
    }

    @Performance
    @Locker(name = "test", lockMillSeconds = 1000)
    @Scheduled(cron = "*/2 * * * * *")
    public void scheduledTask2() throws InterruptedException {
        currentCount--;
        taskList.push(Thread.currentThread().getName());
        Thread.sleep(taskExecTime);
        taskList.pop();
        log.info("task2 , thread :{}, current count: {}", Thread.currentThread().getName(), currentCount);
        currentCount++;
    }

    @Performance
    @Locker(name = "test", lockMillSeconds = 1000)
    @Scheduled(cron = "*/2 * * * * *")
    public void scheduledTask3() throws InterruptedException {
        currentCount--;
        taskList.push(Thread.currentThread().getName());
        Thread.sleep(taskExecTime);
        taskList.pop();
        log.info("task3 , thread :{}, current count: {}", Thread.currentThread().getName(), currentCount);
        currentCount++;
    }

    @Performance
    @Locker(name = "test", lockMillSeconds = 1000)
    @Scheduled(cron = "*/2 * * * * *")
    public void scheduledTask4() throws InterruptedException {
        currentCount--;
        taskList.push(Thread.currentThread().getName());
        Thread.sleep(taskExecTime);
        taskList.pop();
        log.info("task4 , thread :{}, current count: {}", Thread.currentThread().getName(), currentCount);
        currentCount++;
    }

    @Performance
    @Locker(name = "test", lockMillSeconds = 1000)
    @Scheduled(cron = "*/2 * * * * *")
    public void scheduledTask5() throws InterruptedException {
        currentCount--;
        taskList.push(Thread.currentThread().getName());
        Thread.sleep(taskExecTime);
        taskList.pop();
        log.info("task5 , thread :{}, current count: {}", Thread.currentThread().getName(), currentCount);
        currentCount++;
    }

    @Performance
    @Locker(name = "test", lockMillSeconds = 1000)
    @Scheduled(cron = "*/2 * * * * *")
    public void scheduledTask6() throws InterruptedException {
        currentCount--;
        taskList.push(Thread.currentThread().getName());

        Thread.sleep(taskExecTime);
        taskList.pop();
        log.info("task6 , thread :{}, current count: {}", Thread.currentThread().getName(), currentCount);
        currentCount++;
    }

    @Performance
    @Locker(name = "test", lockMillSeconds = 1000)
    @Scheduled(cron = "*/2 * * * * *")
    public void scheduledTask7() throws InterruptedException {
        currentCount--;
        taskList.push(Thread.currentThread().getName());
        Thread.sleep(taskExecTime);
        taskList.pop();
        log.info("task7 , thread :{}, current count: {}", Thread.currentThread().getName(), currentCount);
        currentCount++;
    }


}
