package com.hyd.subordtest.controller;

import com.hyd.subordtest.domain.info.MessageInfo;
import com.hyd.subordtest.service.BasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BasicInfo")
public class BasicInfoController {
    @Autowired
    private BasicInfoService basicInfoService;

    @PostMapping("/syncDocumentToXml")
    public String syncDocumentToXml(@RequestBody MessageInfo info){
        switch (info.getMsgtype()){
            case 1:
                basicInfoService.queryDocToXml(info);
                break;
            case 2:
                basicInfoService.queryRepToXml(info);
                break;
            case 3:
                basicInfoService.queryDiscToXml(info);
                break;
            case 4:
                basicInfoService.queryFollToXml(info);
                break;
            case 5:
                basicInfoService.queryEmerToXml(info);
                break;
            default:
                break;
        }
        return "success";
    }


}
