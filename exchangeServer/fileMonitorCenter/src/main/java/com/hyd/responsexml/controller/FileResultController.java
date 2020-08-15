package com.hyd.responsexml.controller;

import com.hyd.responsexml.service.GWFileService;
import com.hyd.responsexml.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xieshuai on 2020/6/19 下午 2:04
 */

@RestController
public class FileResultController {


    @Autowired
    private GWFileService fileCenter;

    /**
     * 提供国网文件批量返回结果，去查询Redis中前20条消息数据，响应到请求接口
     */
    @RequestMapping("/queryResultFiles")
    public MessageVO queryResultFiles(){
        return fileCenter.buildFeedbackFiles();
    }

    /**
     * 确认已接收的文件批次
     */
    @RequestMapping("/confirmFilesBatch")
    public String confirmFilesBatch(String uuid){
        fileCenter.confirmBatchFiles(uuid);
        return "1";
    }




}
