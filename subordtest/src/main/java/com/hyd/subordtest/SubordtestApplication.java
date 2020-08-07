package com.hyd.subordtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SubordtestApplication {


    public static void main(String[] args) {
        SpringApplication.run(SubordtestApplication.class, args);
        //SpringApplication sa = new SpringApplication(SubordtestApplication.class);

    }

}
