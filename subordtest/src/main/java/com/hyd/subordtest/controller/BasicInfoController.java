package com.hyd.subordtest.controller;

import com.hyd.subordtest.domain.info.MessageInfo;
import com.hyd.subordtest.listener.FileListenerFactory;
import com.hyd.subordtest.listener.FileListenerRunner;
import com.hyd.subordtest.service.DealCenterService;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BasicInfo")
public class BasicInfoController {
    @Autowired
    private DealCenterService basicInfoService;
    @Autowired
    FileListenerRunner runner;


    @PostMapping("/syncDocumentToXml")
    public String syncDocumentToXml(@RequestBody MessageInfo info){
        switch (info.getMsgtype()){
            case 1:
                basicInfoService.BuildPatInfoMsg(info);
                break;
            case 2:
                basicInfoService.BuildReportCardMsg(info);
                break;
            case 3:
                basicInfoService.BuildLeaveCardMsg(info);
                break;
            case 4:
                basicInfoService.BuildFollowupMsg(info);
                break;
            case 5:
                basicInfoService.BuildEmergacyMsg(info);
                break;
            default:
                break;
        }
        return "success";
    }


    @RequestMapping("/ListenerRun")
    public String listenerRun() {

//        runner.run();
        return "success";
    }




}
