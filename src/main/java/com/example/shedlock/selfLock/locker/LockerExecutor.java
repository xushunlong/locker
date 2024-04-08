package com.example.shedlock.selfLock.locker;

public interface LockerExecutor {
    boolean lock(LockerConfiguration lockConfiguration);

    boolean tryLock(LockerConfiguration lockConfiguration);

    boolean unlock(LockerConfiguration lockConfiguration);
}
