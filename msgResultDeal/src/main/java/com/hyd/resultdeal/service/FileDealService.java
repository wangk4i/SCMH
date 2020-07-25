package com.hyd.resultdeal.service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyd.resultdeal.domain.InterchangeDO;
import com.hyd.resultdeal.domain.MessageDO;
import com.hyd.resultdeal.utils.FileUtils;
import com.hyd.resultdeal.utils.TextFileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class FileDealService {
    // 已接收待处理文件夹
    @Value("${mqPath.received}")
    private String receivedFolder;
    // 处理完成文件夹
    @Value("${mqPath.done}")
    private String doneFolder;
    // 操作失败文件夹
    @Value("${mqPath.failed}")
    private String failedFolder;
    // 相应消息txt接收文件夹
    @Value("${mqPath.returnTxt}")
    private String returnTxtFolder;

    // xml持久化文件夹
    @Value("${mqPath.buildxml.sendingxml}")
    private String sendingXmlFolder;
    // 消息持久化文件夹
    @Value("${mqPath.msgPersist}")
    private String msgPersistFolder;

    private Gson gson = new GsonBuilder().serializeNulls().create();

    public void resultDeal(){
        List<String> resultFiles =  FileUtils.getFiles(returnTxtFolder);
        for (String x: resultFiles){
            analysisMsg(x);
        }


    }



    private String analysisMsg(String filePath){
        // 序列化响应消息和其中 交换信息json
        String txtContent = TextFileUtils.readFileContent(filePath);
        MessageDO resultMsg = TextFileUtils.resultMsgAnalysis(txtContent);
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam()+".xml";
        // 返回值为失败时，转移到failed文件夹，返回交换消息体

        //todo 更新失败信息到数据库字段

        if (!exMsg.getResult()){
            TextFileUtils.moveTotherFolders(sendingXmlFolder, docXml, failedFolder);
            return resultMsg.getMsgBody();
        }

        // 根据docId找json 更新json id为省网主键
        // 分发处理：casa json内的操作，更新数据库
        // 找对应xml,合成大xml
        // 把xml 转移到 待处理文件夹


        // 根据docId找.json, 全部更新id
        String msgFilePath = msgPersistFolder + File.separator + resultMsg.getXmlNam()+".json";
        String msgInfoJson = TextFileUtils.readFileContent(msgFilePath);
        String regEx = "\"id\":\"([\\s\\S]*?)\"";
        String oldId = TextFileUtils.matchValue(msgInfoJson,regEx);
        TextFileUtils.alterStringToCreateNewFile(msgFilePath, oldId, exMsg.getId());




        // 返回值为成功，转移到 done/日期/ 文件夹下
        TextFileUtils.moveTotherFolders(sendingXmlFolder, docXml, doneFolder);

        // 分发操作接口 更新
        // add 更新 xml:信息主键 <ID>exMsg.id</ID>,数据库: 对应表的FIELDPK、更新同步状态、时间
        // update 不需更新xml 数据库：更新同步字段
        // delete 更新dellogo 同步字段




        return null;

    }

}
