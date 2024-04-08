package com.example.shedlock.selfLock.locker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockerConfiguration {
    private String name;

    private Timestamp lockUntil;

    private boolean retry;

    private int retryTimes;
}
