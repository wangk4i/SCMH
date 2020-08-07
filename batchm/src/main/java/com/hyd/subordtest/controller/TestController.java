package com.hyd.subordtest.controller;

import com.alibaba.fastjson.JSONArray;
import com.hyd.subordtest.mapper.BasicInfoMapper;
import com.hyd.subordtest.service.TestRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestRedisService testService;

    @Autowired(required = false)
    private BasicInfoMapper mapper;


    @RequestMapping("/TestRedis")
    public String testRedis(){
        return testService.getRedisNum();
    }




}
