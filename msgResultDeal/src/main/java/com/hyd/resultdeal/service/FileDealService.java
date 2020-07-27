package com.hyd.resultdeal.service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyd.resultdeal.Config.FolderPathConfig;
import com.hyd.resultdeal.distributeService.MessageCate;
import com.hyd.resultdeal.distributeService.MessageCateImpl;
import com.hyd.resultdeal.domain.MessageInfoDO;
import com.hyd.resultdeal.domain.ReturnMsgDO;
import com.hyd.resultdeal.utils.FileUtils;
import com.hyd.resultdeal.utils.TextFileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class FileDealService {


    private Gson gson = new GsonBuilder().serializeNulls().create();

    public void resultDeal(){
        List<String> resultFiles =  FileUtils.getFiles(FolderPathConfig.returnTxtFolder);
        for (String file: resultFiles){
            analysisMsg(file);
        }


    }



    private String analysisMsg(String filePath){
        // 序列化响应消息和其中 交换信息json
        String txtContent = TextFileUtils.readFileContent(filePath);
        ReturnMsgDO resultMsg = TextFileUtils.resultMsgAnalysis(txtContent);

//        String docXml = resultMsg.getXmlNam()+".xml";
        String msgFilePath = FolderPathConfig.msgPersistFolder + File.separator + resultMsg.getXmlNam()+".json";
        // todo 没找到json时的处理 记日志
        String msgInfoJson = TextFileUtils.readFileContent(msgFilePath);
        MessageInfoDO info = gson.fromJson(msgInfoJson, MessageInfoDO.class);

        // 数据扔到接口 进行处理
        MessageCate messageCate = new MessageCateImpl();
        /*System.out.println("msgPersistFolder:"+ FolderPathConfig.receivedFolder);
        System.out.println("msgPersistFolder:"+ FolderPathConfig.msgPersistFolder);*/

        messageCate.ExchangeCate(info, resultMsg);




/*
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);



        //todo 更新失败信息到数据库字段

        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            TextFileUtils.moveTotherFolders(sendingXmlFolder, docXml, failedFolder);
            TextFileUtils.moveTotherFolders(returnTxtFolder, resultMsg.getXmlNam()+".txt", failedFolder);
            return resultMsg.getMsgBody();
        }

        // 根据docId找json 更新json id为省网主键
        // 分发处理：casa json内的操作，更新数据库
        // 找对应xml,合成大xml
        // 把xml 转移到 待处理文件夹


        // 根据docId找.json, 全部更新id


        String regEx = "\"id\":\"([\\s\\S]*?)\"";
        String oldId = TextFileUtils.matchValue(msgInfoJson,regEx);
        // 主键更新为返回成功 交换消息中的返回值
        TextFileUtils.alterStringToCreateNewFile(msgFilePath, oldId, exMsg.getId());
        JsonObject msgJson = new JsonParser().parse(msgInfoJson).getAsJsonObject();




        // 返回值为成功，转移到 done/日期/ 文件夹下
        TextFileUtils.moveTotherFolders(sendingXmlFolder, docXml, doneFolder);
        TextFileUtils.moveTotherFolders(returnTxtFolder, resultMsg.getXmlNam()+".txt", doneFolder);
*/

        // 分发操作接口 更新
        // add 更新 xml:信息主键 <ID>exMsg.id</ID>,数据库: 对应表的FIELDPK、更新同步状态、时间
        // update 不需更新xml 数据库：更新同步字段
        // delete 更新dellogo 同步字段




        return null;

    }

}
