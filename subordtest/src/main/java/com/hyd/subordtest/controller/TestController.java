package com.hyd.subordtest.controller;

import com.alibaba.fastjson.JSONArray;
import com.hyd.subordtest.mapper.BasicInfoMapper;
import com.hyd.subordtest.service.BasicInfoService;
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
    private BasicInfoService basicInfoService;

    @Autowired(required = false)
    private BasicInfoMapper mapper;


    @RequestMapping("/querylist")
    public String queryList() {
        //去数据库查询前1000条  主键
        List<String> list = mapper.queryTestBasicInfoView();
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("id", list.get(i));
            map.put("zone", "testZone" + i);
            map.put("organ", "testOrgan" + i);
            map.put("msgcate", "1");
            map.put("msgtype", "1");
            map.put("msgaction", "1");
            System.out.println(JSONArray.toJSONString(map));
        }
        return "success";
    }

}
