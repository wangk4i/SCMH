package com.hyd.resultdeal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.hyd.resultdeal.mapper")
public class ResultdealApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResultdealApplication.class, args);
    }

}
