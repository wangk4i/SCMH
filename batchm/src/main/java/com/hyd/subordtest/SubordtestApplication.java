package com.hyd.subordtest;

import com.hyd.subordtest.listener.ApplicationStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SubordtestApplication {

    public static void main(String[] args) {
        SpringApplication SubordtestApplication = new SpringApplication(SubordtestApplication.class);
        SubordtestApplication.addListeners(new ApplicationStartup());
        SubordtestApplication.run(args);
        //SpringApplication  SubordtestApplication = new SpringBootApplication(SubordtestApplication.class);
        //SpringApplication.run(SubordtestApplication.class, args);
    }

}
