package com.hyd.cdcserver.aop;

import com.hyd.cdcserver.util.SystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by xieshuai on 2020/6/30 下午 2:16
 */
@Slf4j
@Component
public class SystemRunWriteAspect {

    @PostConstruct
    public void writeCloseSystemPid(){

        BufferedWriter bf = null;
        String pid = SystemUtils.getSystemPig();
        String content = " taskkill /f /pid "+pid;
        File file = new File(System.getProperty("user.dir")+"//closeServer.bat");
        try {
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            bf = new BufferedWriter(fileWriter);
            bf.write(content);
            log.info("bat文件写入成功:{}", file.getPath());
        }catch (Exception e){
            log.info("启动批处理文件写入失败:{}", e.getMessage());
        }finally {
            try {
                assert bf != null;
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @PostConstruct
    public void writeStartSystemPid(){

        BufferedWriter bf = null;
        try {
            String content = "java -jar exchangeFileSending.jar";
            File file = new File(System.getProperty("user.dir")+"//startServer.bat");
            FileWriter fileWriter =new FileWriter(file.getAbsoluteFile());
            bf = new BufferedWriter(fileWriter);
            bf.write(content);
            bf.flush();
            log.info("bat文件写入成功:{}", file.getPath());
        }catch (Exception e){
            log.info("启动批处理文件写入失败:{}", e.getMessage());
        }finally {
            try {
                assert bf != null;
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
