package com.example.shedlock.test;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LockerTest {
    @Autowired
    private ProductService productService;

    /**
     * 多线程并行处理500个请求，扣库存
     */
    @Test
    public void testDistributionLock() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2000);

        List<task> taskList = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            taskList.add(new task());
        }

        List<Future<Boolean>> futures = executor.invokeAll(taskList);

        int successCount = 0;
        for (int i = 0; i < futures.size(); i++) {
            Future<Boolean> booleanFuture = futures.get(i);
            if (null != booleanFuture.get() && booleanFuture.get()) {
                successCount++;
            }
        }
        log.info("成功数:{}", successCount);
        log.info("剩余库存:{}", productService.get());
    }

    class task implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            return productService.increment();
        }
    }
}
