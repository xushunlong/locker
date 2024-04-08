package com.example.shedlock;

import com.example.shedlock.selfLock.aop.Locker;
import com.example.shedlock.selfLock.aop.Performance;
import com.example.shedlock.test.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Slf4j
@Component
@EnableScheduling
public class SchedulerTaskTrigger {

    private static int currentCount = 0;

    private Stack<String> taskList = new Stack<>();

    private int taskExecTime = 200;

    private final static int lockTime = 3000;

    @Autowired
    private ProductService productService;

    @Scheduled(cron = "* * * * * *")
    public void watcher() throws InterruptedException {
        log.info("current count: {}, tasklist: {}", currentCount, taskList.size());
    }

    @Performance
    @Locker(name = "test", lockMillSeconds = lockTime)
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
    @Locker(name = "test", lockMillSeconds = lockTime)
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
    @Locker(name = "test", lockMillSeconds = lockTime)
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
    @Locker(name = "test", lockMillSeconds = lockTime)
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
    @Locker(name = "test", lockMillSeconds = lockTime)
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
    @Locker(name = "test", lockMillSeconds = lockTime)
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
    @Locker(name = "test", lockMillSeconds = lockTime)
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
    @Locker(name = "test", lockMillSeconds = lockTime)
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
