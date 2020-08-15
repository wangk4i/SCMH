package com.hyd.subordtest.service;

import com.hyd.subordtest.utils.RabbitUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by xieshuai on 2020/6/12 17:05
 */

@Slf4j
@Component
public class ListenerService {

    @Value("${config.build}")
    private String listenerFolder;

    @Value("${config.queue.xmlfile}")
    private String MQ_SENDING;

    @Autowired
    private RabbitUtils rabbitUtils;

    public void sendXml(File file){
        String fileName = file.getName();
        String xmlId = fileName.substring(0, fileName.lastIndexOf(".xml"));
        // 发送文件名id
        rabbitUtils.sendXmlId(MQ_SENDING, xmlId);

    }

}
