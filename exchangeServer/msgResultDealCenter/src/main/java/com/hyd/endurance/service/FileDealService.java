package com.hyd.endurance.service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyd.endurance.config.FolderPathConfig;
import com.hyd.endurance.distributeService.MessageCate;
import com.hyd.endurance.domain.ActInfoDO;
import com.hyd.endurance.domain.InterchangeDO;
import com.hyd.endurance.domain.MessageInfoDO;
import com.hyd.endurance.domain.ReturnMsgDO;
import com.hyd.endurance.utils.LogUtil;
import com.hyd.endurance.utils.TextFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileDealService {

    @Autowired
    MessageCate messageCate;

    private Gson gson = new GsonBuilder().serializeNulls().create();


    public void receiveXmlIdDeal(String xmlId){
        File file = new File(FolderPathConfig.received + xmlId + ".txt");
        if (!file.exists()){
            LogUtil.info("未找到ID对应的响应文件 "+ xmlId + ".txt");
            return;
        }
        try {
            analysisMsg(xmlId, file);
        }catch (Exception e)
        {
            LogUtil.warn("", e);
        }
    }

    private void analysisMsg(String fileid,File file){
        // 序列化响应消息和其中 交换信息json
        String txtContent = TextFileUtils.readFile(file);
        ReturnMsgDO resultMsg = TextFileUtils.resultMsgAnalysis(txtContent);
        String jsonWaitFileName = FolderPathConfig.waiting + File.separator + fileid+".json";
        String jsonReceiveFileName = FolderPathConfig.received + File.separator + fileid+".json";
        File jsonWaitFile=new File(jsonWaitFileName);
        File jsonReceiveFile=new File(jsonReceiveFileName);
        if(!jsonWaitFile.exists() && !jsonReceiveFile.exists()){
            LogUtil.info("未找到ID对应的消息文件 " + fileid + ".json");
            return;
        }
        String msgInfoJson = "";
        if(jsonWaitFile.exists()) {
            msgInfoJson=TextFileUtils.readFile(jsonWaitFile);
        }else{
            msgInfoJson=TextFileUtils.readFile(jsonReceiveFile);
        }
        MessageInfoDO msgInfo = gson.fromJson(msgInfoJson, MessageInfoDO.class);
        InterchangeDO bizInfo = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        ActInfoDO info = new ActInfoDO(msgInfo, resultMsg, bizInfo);
        // 接收到响应txt后，将xml、json转移到received文件夹
        TextFileUtils.moveTotherFolders(FolderPathConfig.waiting, fileid+".xml", FolderPathConfig.received);
        TextFileUtils.moveTotherFolders(FolderPathConfig.waiting, fileid+".json", FolderPathConfig.received);
        // 数据经过接口进行处理
        try {
            messageCate.ExchangeCate(info);
        }catch (Exception e)
        {
            LogUtil.warn("", e);
            return;
        }
        if(bizInfo.getResult()){
            TextFileUtils.moveTotherFolders(FolderPathConfig.received, fileid+".txt", FolderPathConfig.done);
            TextFileUtils.moveTotherFolders(FolderPathConfig.received, fileid+".xml", FolderPathConfig.done);
            TextFileUtils.moveTotherFolders(FolderPathConfig.received, fileid+".json", FolderPathConfig.done);
        }else{
            TextFileUtils.moveTotherFolders(FolderPathConfig.received, fileid+".txt", FolderPathConfig.failed);
            TextFileUtils.moveTotherFolders(FolderPathConfig.received, fileid+".xml", FolderPathConfig.failed);
            TextFileUtils.moveTotherFolders(FolderPathConfig.received, fileid+".json", FolderPathConfig.failed);
        }
        //todo 持久化消息到消息数据库
    }


}
