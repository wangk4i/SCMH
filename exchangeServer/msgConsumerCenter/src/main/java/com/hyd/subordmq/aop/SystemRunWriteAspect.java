package com.hyd.subordmq.aop;

import com.hyd.subordmq.utils.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by xieshuai on 2020/5/14 14:12
 */

@Component
public class SystemRunWriteAspect {

    private final static Logger logger = LoggerFactory.getLogger(SystemRunWriteAspect.class);

    @PostConstruct
    public void writeCloseSystemBat(){
        try {
            String pid = SystemUtils.getSystemPig();
            String content = " taskkill /f /pid "+pid;
            File file = new File(System.getProperty("user.dir")+"//closeServer.bat");
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bf = new BufferedWriter(fileWriter);
            bf.write(content);
            bf.close();
            logger.info("bat文件写入成功: "+file.getPath());
        }catch (Exception e){
            logger.info("关闭批处理文件写入失败:"+e.getMessage());
        }
    }

    @PostConstruct
    public void writeStartSystemBat(){
        try {
            String content = "java -jar msgConsumerCenter.jar";
            File file = new File(System.getProperty("user.dir")+"//startServer.bat");
            FileWriter fileWriter =new FileWriter(file.getAbsoluteFile());
            BufferedWriter bf = new BufferedWriter(fileWriter);
            bf.write(content);
            bf.flush();
            bf.close();
            logger.info("bat文件写入成功:"+file.getPath());
        }catch (Exception e){
            logger.info("启动批处理文件写入失败:"+e.getMessage());
        }
    }



}
