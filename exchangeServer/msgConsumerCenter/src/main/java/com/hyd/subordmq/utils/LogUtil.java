package com.hyd.subordmq.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class LogUtil {
    private static String logPath ;

    @Value("${config.BuildLogPath}")
    public void setLogPath(String logPath) {
        LogUtil.logPath = logPath;
    }

    /**
     * 创建日志文件
     * @param logStr 日志内容
     * @return*/


    public static void info(String logStr){
        String content = infoLogContent(logStr);
        buildLogFile(content);
    }

    public static void warn(String logStr, Exception e){
         String content = warnLogContent(logStr, e);
         buildLogFile(content);
    }

    public static void sendRabbitErr(String xmlId, String logFolder){
         String content = xmlId + "\n";
         buildLogFile(content, logFolder);
    }

    private static void buildLogFile(String content){
        String d = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        String logPath = LogUtil.logPath + "\\log";
        File logFolder = new File(logPath);
        if (!logFolder.exists()){
            logFolder.mkdir();
        }
        File file = new File(logPath + "\\"+d+ ".log");
        RandomAccessFile mm = null;
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(file,true);
            o.write(content.getBytes("utf-8"));
            o.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mm != null) {
                try {
                    mm.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean buildLogFile(String content, String logPath){

        String d = new SimpleDateFormat("yyyyMMddHH").format(new Date());

        File LogFolder = new File(logPath);
        if (!LogFolder.exists()){
            LogFolder.mkdir();
        }

        File file = new File(LogFolder + "\\Mq_"+ d+".log");
        RandomAccessFile mm = null;
        boolean flag = false;

        FileOutputStream o = null;
        try {
            o = new FileOutputStream(file,true);
            o.write(content.getBytes("utf-8"));
            o.close();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mm != null) {
                try {
                    mm.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


    private static String infoLogContent(String str){
        StringBuffer sb = new StringBuffer();
        String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        sb.append(d).append("\t: ").append(str).append("\n");
        return sb.toString();
    }

    private static String warnLogContent(String str, Exception e){
         StringBuffer sb = new StringBuffer();
        String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        sb.append(d).append("\t: ").append(str).append("\t").append(e.getMessage()).append("\n");
        return sb.toString();
    }
}
