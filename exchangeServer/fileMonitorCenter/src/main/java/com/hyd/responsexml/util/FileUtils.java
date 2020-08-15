package com.hyd.responsexml.util;


import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by xieshuai on 2020/5/29 14:08
 */
@Slf4j
public class FileUtils {

    @Value("${config.listener-folder}")
    private String listenerPath;


    public static void move(String sourcePath, String toPath){
        try {
            Files.move(Paths.get(sourcePath), Paths.get(toPath));
        }catch (IOException e){
            log.error("文件移动失败,来源路径{},去向路径{},异常信息{}", sourcePath, toPath, e.getMessage());
        }
    }

    /**
     * 读取本地Json资源文件
     * @param jsonPath
     * @return
     */
    public static String readJsonFile(String jsonPath) {
        File jsonFile = null;
        try {
            jsonFile = new File(jsonPath);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            log.info(jsonFile.getPath() + "文件解析异常");
            e.printStackTrace();
            return null;
        }
    }
    public static String readJsonFile(File jsonFile){
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1){
                sb.append((char)ch);
            }
            fileReader.close();
            reader.close();
            return sb.toString();
        }catch (Exception e){
            log.info(jsonFile.getPath()+ "文件解析异常");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据文件路径删除文件
     * @param filePath
     * @return
     */
    public static boolean delectFile(String filePath){
        File file = new File(filePath);
        return file.delete();
    }
    public static boolean delectFile(File file){
        return file.delete();
    }

    /**
     * 根据文件路径删除指定文件前缀的文件
     * @param filePath
     * @param prefix
     * @return
     */
    public static boolean delectFileByPrefix(String filePath, String prefix){
        if(!filePath.endsWith("\\")){
            filePath = filePath + "\\";
        }
        File file = new File(filePath);
        if (!file.isDirectory()) {
            return false;
        }
        String[] filelist = file.list();
        assert filelist != null;
        for (String fileName : filelist) {
            if (StrUtil.startWith(fileName, prefix)) {

                FileUtils.delectFile(filePath + fileName);
            }
        }
        return true;
    }

    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public static String readToString(File file){
        String encoding = "UTF-8";
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 读取指定文件夹下所有文件的全路径
     * @param path 文件夹路径
     * @return 文件资源路径集合
     */
    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
            }
        }
        return files;
    }

    public static List<String> getFilesPrefix(String filePath, String prefix){
        List<String> list = new Vector<>();
        if(!filePath.endsWith("\\")){
            filePath = filePath + "\\";
        }
        File file = new File(filePath);
        if (!file.isDirectory()) {
            return null;
        }
        String[] filelist = file.list();
        assert filelist != null;
        for (String fileName : filelist) {
            if (StrUtil.startWith(fileName, prefix)) {
                list.add(filePath + fileName);
            }
        }
        return list;
    }

    /**
     * 传入文件夹路径，返回当前文件夹下所有文件
     * @param path
     * @return
     */
    public static List<File> traverseFolder(String path) {
        List<File> fileList = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                log.info("当前文件夹为空");
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        fileList.add(file2);
                    }
                }
            }
        } else {
           log.info("文件不存在");
        }
        return fileList;
    }

    /**
     * 传入path,获取文件创建时间
     */
    public static String getFileCreateTime(String filePath){
        String strTime = null;
        try {
            Process p = Runtime.getRuntime().exec("cmd /C dir "
                    + filePath
                    + "/tc" );
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while((line = br.readLine()) != null){
                if(line.endsWith(".txt")){
                    strTime = line.substring(0,17);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strTime;
    }

    /**
     * 获取文件最后修改时间
     */
    public static String getFileModifiedTime(String filePath){
        File f = new File(filePath);
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        cal.setTimeInMillis(time);
        return cal.getTime().toLocaleString();
    }


    public static void xmlStringToFile(Document doc, String xmlPath){
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer;
        try {
            writer = new XMLWriter(new FileWriter(new File(xmlPath)), format);
            writer.write(doc);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 传入文件流和路径，写入到指定路径中
     * @param fileString
     * @param filePath
     */
    public static void stringToFile(String fileString, String filePath){
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        FileWriter writer = null ;
        try {
            writer = new FileWriter(new File(filePath));
            writer.write(fileString);
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


}
