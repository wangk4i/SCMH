package com.hyd.receivecdc.util;



import com.google.common.base.Charsets;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieshuai on 2020/5/29 14:08
 */

public class FileUtils {

    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static void move(String sourcePath, String toPath){
        try {
            Files.move(Paths.get(sourcePath), Paths.get(toPath));
        }catch (IOException e){
            log.error("移动文件失败,文件来源{},文件去向{}", sourcePath, toPath);
        }
    }


    /**
     * 文本文件读取为字符串
     * @param filePath 文件路径
     * @return
     */
    public static String fileToString(String filePath){
        return fileToString(filePath, Charsets.UTF_8);
    }
    public static String fileToString(String filePath, Charset charset){
        File file = new File(filePath);
        return fileToString(file, charset);
    }
    public static String fileToString(File file){
       return fileToString(file, Charsets.UTF_8);
    }
    public static String fileToString(File file, Charset charset){
        //获取文件长度
        Long fileLength = file.length();
        //创建等同于文件长度的数组
        byte[] fileContent = new byte[fileLength.intValue()];
        //创建InputStream并输出
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            inputStream.read(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(fileContent, charset);
    }


    /**
     * 将xml解析为流
     */
    public static String readFileXmlToString(File file){

        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(file);
        }catch (Exception e){

        }
        return document.asXML();
    }

    /**
     * 将xml流写入指定文件夹
     * @param xmlName xml文件名称
     * @param xmlString xml流
     * @param filePath 写入文件路径
     */
    public static void xmlStringToFile(String xmlName, String xmlString, String filePath){
        String fileName =  filePath + "\\"+xmlName+".xml";
        File file = new File(fileName);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(xmlString);
            bw.flush();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
            log.info("消息写入文件失败:{}",xmlName);
        }
    }

    /**
     * 读取本地Json资源文件
     * @param jsonPath
     * @return
     */
    public static String readJsonFile(String jsonPath) {
        return readJsonFile(new File(jsonPath));
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
           return null;
        }
        return fileList;
    }

}
