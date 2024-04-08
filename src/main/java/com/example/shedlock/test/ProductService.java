package com.example.shedlock.test;

import com.example.shedlock.selfLock.locker.Locker;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private int count = 2000;

    @Locker(name = "test", lockMillSeconds = 2000)
    public Boolean increment() throws InterruptedException {
        if (count > 0) {
            Thread.sleep(10);
            count--;

            return true;
        }
        return false;
    }

    public int get() {
        return count;
    }
}
