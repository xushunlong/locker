package com.example.shedlock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.example.shedlock.selfLock.dao")
@SpringBootApplication
public class ShedlockDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShedlockDemoApplication.class, args);
    }


}


