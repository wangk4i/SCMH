package com.hyd.resultdeal.service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyd.resultdeal.Config.FolderPathConfig;
import com.hyd.resultdeal.distributeService.MessageCate;
import com.hyd.resultdeal.domain.MessageInfoDO;
import com.hyd.resultdeal.domain.ReturnMsgDO;
import com.hyd.resultdeal.utils.FileUtils;
import com.hyd.resultdeal.utils.TextFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class FileDealService {

    @Autowired
    MessageCate messageCate;


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
        String msgFilePath = FolderPathConfig.msgPersistFolder + File.separator + resultMsg.getXmlNam()+".json";
        // todo 没找到json时的处理 记日志
        String msgInfoJson = TextFileUtils.readFileContent(msgFilePath);
        MessageInfoDO info = gson.fromJson(msgInfoJson, MessageInfoDO.class);

        // 数据扔到接口 进行处理
        messageCate.ExchangeCate(info, resultMsg);

        return null;

    }

}
