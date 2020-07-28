/*
package com.hyd.resultdeal.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {

    private static String rootPath = System.getProperty("user.dir")+"/log/";
    */
/**
     * 带异常的日志输出
     * @param bizLog
     * @return false 日志输出失败
     *//*

    public static boolean errorLogTemplate(BussinessLog bizLog){
        String content = bizExLogContents(bizLog);
        return buildLogFile(content, bizLog.getMethodName(), "error");
    }



    */
/**
     * 未知异常 日志
     * @param methodName 调用方法名
     * @param unkown
     * @return
     *//*

    public static boolean unkownExLogTemplate(String methodName, Exception unkown){
        String content = unkownExLogContents(methodName, unkown);
        return buildLogFile(content, methodName, "error");
    }

    */
/**
     * 创建日志文件
     * @param content 日志内容
     * @param methodName 方法名作为日志文件名
     * @param folderNam 日志文件夹
     * @return
     *//*

    private static boolean buildLogFile(String content, String methodName, String folderNam){
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHH");
        // todo 日志文件夹修改

        String folderPath = rootPath + folderNam;
        // 文件夹路径
        File rootfolder = new File(rootPath);
        if(!rootfolder.exists()){ // 如果文件夹不存在
            rootfolder.mkdir(); // 创建文件夹
        }
        File filefolder = new File(folderPath);
        if(!filefolder.exists()){ // 如果文件夹不存在
            filefolder.mkdir(); // 创建文件夹
        }
        // 日志路径  :   根/log/ /接口方法名_yyyyMMddHH.log
        String filePath = methodName + "_" + sd.format(date)+".log";
        File fileName = new File(folderPath+"/"+filePath);
        RandomAccessFile mm = null;
        boolean flag = false;
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(fileName,true);
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

    */
/**
     * 创建留痕日志文件
     * @param content 日志内容
     * @param folderNam 文件夹名
     * @return
     *//*

    private static boolean buildMarkLogFile(String content, String folderNam){
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHH");

        String folderPath = rootPath + folderNam;
        // 文件夹路径
        File rootfolder = new File(rootPath);
        if(!rootfolder.exists()){ // 如果文件夹不存在
            rootfolder.mkdir(); // 创建文件夹
        }
        File filefolder = new File(folderPath);
        if(!filefolder.exists()){ // 如果文件夹不存在
            filefolder.mkdir(); // 创建文件夹
        }
        // 日志路径  :   根/log/ /接口方法名_yyyyMMddHH.log
        String filePath = sd.format(date)+".log";
        File fileName = new File(folderPath+"/"+filePath);
        RandomAccessFile mm = null;
        boolean flag = false;
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(fileName,true);
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



    */
/**
     * 通信留痕日志
     * @param cltLog
     * @return
     *//*

    private static String markLogContents(ClientLog cltLog){
        long endTime = System.currentTimeMillis();
        Long total = endTime - cltLog.getStartTime();
        StringBuffer buffer=new StringBuffer();
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = sd.format(date);
        buffer.append("###################"+d+"###################\n");
        buffer.append("Url: ").append(cltLog.getUrl()).append("\n");
        buffer.append("耗时: ").append(total).append(" ms").append("\n");
        buffer.append("Method: POST").append("\n");
        buffer.append("数据:\n");
        buffer.append(cltLog.getInputString()).append("\n\n");
        buffer.append("返回数据:\n").append(cltLog.getOutputString()).append("\n\n");
        return buffer.toString();
    }


    */
/**
     * 未知异常日志
     *//*

    private static  String unkownExLogContents(String methodName,Exception unkown){
        StringBuffer buffer=new StringBuffer();
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = sd.format(date);
        buffer.append("###################"+d+"###################\n");
        buffer.append("未知异常: ").append(unkown.getMessage()).append("\n");
        buffer.append("接口: ").append(methodName).append("\n");
        buffer.append("Trace: \n").append(getStackTrace(unkown)).append("\n\n");
        return buffer.toString();
    }

    */
/**
     * 完整的堆栈信息
     * @param e Exception
     * @return Full StackTrace
     *//*

    private static String getStackTrace(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
*/
