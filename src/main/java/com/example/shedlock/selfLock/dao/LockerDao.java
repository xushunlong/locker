package com.example.shedlock.selfLock.dao;

import com.example.shedlock.selfLock.locker.LockerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Slf4j
@Service
public class LockerDao {
    @Autowired
    private LockerMapper lockerMapper;

    public boolean queryLock(String name) {
        int query = lockerMapper.query(name);
        return query > 0;
    }

    public boolean insertLock(LockerConfiguration lockerConfiguration) {
        try {
            int insert = lockerMapper.insert(lockerConfiguration.getName(), lockerConfiguration.getLockUntil(), new Timestamp(System.currentTimeMillis()), "111");
            if (insert > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("add lock failed...");
        }
        return false;
    }

    public boolean updateLock(LockerConfiguration lockerConfiguration) {

        int update = lockerMapper.updateByCheckLockUntil(lockerConfiguration.getName(), lockerConfiguration.getLockUntil(), new Timestamp(System.currentTimeMillis()), "111");
        return update > 0;
    }

    public boolean unLock(LockerConfiguration lockerConfiguration) {
        Timestamp unLockTime = new Timestamp(System.currentTimeMillis());

        int update = lockerMapper.update(lockerConfiguration.getName(), unLockTime, unLockTime, "111");
        return update > 0;
    }

}
