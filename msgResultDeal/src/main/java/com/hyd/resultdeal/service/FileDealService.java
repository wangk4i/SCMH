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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileDealService {

    @Autowired
    MessageCate messageCate;


    private Gson gson = new GsonBuilder().serializeNulls().create();

    public void folderResultDeal(){
        List<String> resultFiles =  FileUtils.getFiles(FolderPathConfig.receivedFolder);
        for (String file: resultFiles){
            this.fileResultDeal(file);
        }
    }


    public void fileResultDeal(String file){
        String regEx = "\\.txt";
        String stream = file.substring(file.lastIndexOf("\\"));
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(stream);
        if(matcher.find()){
            analysisMsg(file);
        }
    }


    private String analysisMsg(String filePath){
        // 序列化响应消息和其中 交换信息json
        String txtContent = TextFileUtils.readFileContent(filePath);
        ReturnMsgDO resultMsg = TextFileUtils.resultMsgAnalysis(txtContent);
        String msgFileNam = FolderPathConfig.waitingFolder + File.separator + resultMsg.getXmlNam()+".json";
        // todo 没找到json时的处理 记日志
        String msgInfoJson = TextFileUtils.readFileContent(msgFileNam);
        MessageInfoDO info = gson.fromJson(msgInfoJson, MessageInfoDO.class);

        // 接收到响应txt后，将xml、json转移到received文件夹
        TextFileUtils.moveToOtherFolders(FolderPathConfig.waitingFolder, resultMsg.getXmlNam()+".xml", FolderPathConfig.receivedFolder);
        TextFileUtils.moveToOtherFolders(FolderPathConfig.waitingFolder, resultMsg.getXmlNam()+".json", FolderPathConfig.receivedFolder);
        // 数据扔到接口 进行处理
        messageCate.ExchangeCate(info, resultMsg);

        return null;

    }

}
