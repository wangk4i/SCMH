package com.hyd.resultdeal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xieshuai on 2020/7/1 下午 1:41
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String testMethod(){
        return "hello this is EnduranceServer";
    }

}
