package com.example.shedlock.selfLock.dao;

import lombok.Data;

@Data
public class LockerEntity {
    private String name;

    private int lockUntil;

    private int lockedAt;

    private String lockedBy;
}
