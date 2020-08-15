package com.hyd.batchprocess.aop;

import com.hyd.batchprocess.utils.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by xieshuai on 2020/8/1 上午 9:41
 */
@Component
public class SystemWriteBatAspect {

    private final static Logger logger = LoggerFactory.getLogger(SystemWriteBatAspect.class);

    @PostConstruct
    public void systemWritePid(){
        String pid = SystemUtils.getSystemPid();
        try {
            String content = "pid:"+pid;
            File file = new File(System.getProperty("user.dir")+"//pid.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(content);
            bw.close();
            logger.info("pid文件写入成功:"+file.getPath());
        }catch (Exception e){
            logger.error("pid文件写入失败", e);
        }
    }


    @PostConstruct
    public void systemWriteBat(){
        String pid = SystemUtils.getSystemPid();
        try {
            String context = "taskkill /f /pid "+ pid;
            File file = new File(System.getProperty("user.dir")+"//closeServer.bat");
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bf = new BufferedWriter(fileWriter);
            bf.write(context);
            bf.close();
            logger.info("bat文件写入成功:"+file.getPath());
        }catch (Exception e){
            logger.error("pid文件写入失败", e);
        }
    }

    @PostConstruct
    public void writeStartSystemPid(){

        BufferedWriter bf = null;
        try {
            String content = "java -jar batchGenerateFile.jar";
            File file = new File(System.getProperty("user.dir")+"//startServer.bat");
            FileWriter fileWriter =new FileWriter(file.getAbsoluteFile());
            bf = new BufferedWriter(fileWriter);
            bf.write(content);
            bf.flush();
            logger.info("bat文件写入成功:{}", file.getPath());
        }catch (Exception e){
            logger.info("启动批处理文件写入失败:{}", e.getMessage());
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
