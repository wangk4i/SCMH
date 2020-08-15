package com.hyd.requestxml.controller;

import com.hyd.requestxml.domain.XmlInfo;
import com.hyd.requestxml.service.RestBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xieshuai on 2020/6/16 15:12
 */

@RestController
public class RestBaseController {

    @Autowired
    private RestBaseService service;

    @RequestMapping("/receiveXml")
    public String receiveXml(@RequestBody XmlInfo xmlInfo) {
        service.createXml(xmlInfo);
        return "1";
    }
}
