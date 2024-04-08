package com.example.shedlock.selfLock.dao;

import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;

@Mapper
public interface LockerMapper {

    @Insert("INSERT INTO locker VALUES (#{name}, #{lockUntil}, #{lockedAt}, #{lockedBy})")
    int insert(@Param("name") String name, @Param("lockUntil") Timestamp lockUntil, @Param("lockedAt") Timestamp lockAt, @Param("lockedBy") String lockedBy);

    @Update("UPDATE locker SET lock_until = #{lockUntil}, locked_at = #{now}, locked_by = #{lockedBy} WHERE name = #{name} AND lock_until <= #{now}")
    int updateByCheckLockUntil(@Param("name") String name, @Param("lockUntil") Timestamp lockUntil, @Param("now") Timestamp lockedAt, @Param("lockedBy") String lockedBy);

    @Update("UPDATE locker SET lock_until = #{lockUntil} where name = #{name}")
    int update(@Param("name") String name, @Param("lockUntil") Timestamp lockUntil, @Param("lockedAt") Timestamp lockedAt, @Param("lockedBy") String lockedBy);

    @Select("SELECT count(*) from locker where locker.name = #{name}")
    int query(@Param("name") String name);
}
