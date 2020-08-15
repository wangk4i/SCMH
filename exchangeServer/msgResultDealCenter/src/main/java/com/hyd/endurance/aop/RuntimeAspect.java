package com.hyd.endurance.aop;

import com.hyd.endurance.utils.SystemUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by xieshuai on 2020/7/1 下午 1:48
 * 系统运行时在当前jar包下生成 快速关闭和启动的.bat
 */
public class RuntimeAspect {


        @PostConstruct
        public void createCloseSystemBat(){
            try {
                String pid = SystemUtils.getSystemPid();
                String context = "taskkill /f/pid"+ pid;
                //获取当前jar包所在的文件夹路径
                File file = new File(System.getProperty("user.dir")+"//closeServer.bat");
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bf = new BufferedWriter(fileWriter);
                bf.write(context);
                bf.flush();
                bf.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @PostConstruct
        public void createStartSystemBat(){
            try {
                String context = "java -jar msgResultDealCenter.jar";
                File file = new File(System.getProperty("user.dir")+"//startServer.bat");
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bf = new BufferedWriter(fileWriter);
                bf.write(context);
                bf.flush();
                bf.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
}
