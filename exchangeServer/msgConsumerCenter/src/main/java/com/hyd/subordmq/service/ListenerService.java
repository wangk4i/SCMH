package com.hyd.subordmq.service;

import com.hyd.subordmq.utils.RabbitUtils;
import lombok.extern.slf4j.Slf4j;
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


    public void sendXml(File file){
        String fileName = file.getName();
        String xmlId = fileName.substring(0, fileName.lastIndexOf(".xml"));
        // 发送文件名id
        RabbitUtils.sendXmlId(xmlId);

    }

}
