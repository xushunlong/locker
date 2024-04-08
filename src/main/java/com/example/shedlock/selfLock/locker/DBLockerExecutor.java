package com.example.shedlock.selfLock.locker;

import com.example.shedlock.selfLock.dao.LockerDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DBLockerExecutor implements LockerExecutor {
    @Autowired
    private LockerDao lockerDao;

    @Override
    public boolean lock(LockerConfiguration lockerConfiguration) {
        // 1.judge if there have a lock record
        if (!lockerDao.queryLock(lockerConfiguration.getName())) {

            // 2. if not have lock, then add a lock
            if (lockerDao.insertLock(lockerConfiguration)) {
                return true;
            }
        }

        // 3.if have the lock, try to get the lock
        return lockerDao.updateLock(lockerConfiguration);
    }

    @Override
    public boolean tryLock(LockerConfiguration lockerConfiguration) {

        return true;
    }

    @Override
    public boolean unlock(LockerConfiguration lockerConfiguration) {
        return lockerDao.unLock(lockerConfiguration);
    }
}
