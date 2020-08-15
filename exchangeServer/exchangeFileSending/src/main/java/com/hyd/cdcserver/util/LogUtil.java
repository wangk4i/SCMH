package com.hyd.cdcserver.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/8 11:42
 */
@Component
public class LogUtil {

    private static String sendingPath;

    @Value("${config.sending}")
    public void setSendingPath(String sendingPath){
        LogUtil.sendingPath = sendingPath;
    }


    public static void failSendLog(String xmlId){
        String content = failSendContent(xmlId);
        buildLogFile(content);
    }

    public static void warn(String logStr, Exception e){
        String content = warnLogContent(logStr, e);
        buildLogFile(content);
    }


    private static void buildLogFile(String content){
        String d = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        String logPath = sendingPath + "\\log";
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


    private static String failSendContent(String xmlId){
        return xmlId+"\n";
    }

    private static String warnLogContent(String str, Exception e){
        StringBuffer sb = new StringBuffer();
        String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        sb.append(d).append("\t: ").append(str).append("\t").append(e.getMessage()).append("\n");
        return sb.toString();
    }
}
