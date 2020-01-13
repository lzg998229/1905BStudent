package com.lzg.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lzg.api.dao")
public class SpringbootApplicationtest {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplicationtest.class,args);
    }
}
