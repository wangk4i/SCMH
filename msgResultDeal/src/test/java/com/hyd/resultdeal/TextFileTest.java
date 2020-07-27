package com.hyd.resultdeal;

import com.google.gson.Gson;
import com.hyd.resultdeal.domain.MessageInfoDO;
import com.hyd.resultdeal.domain.ReturnMsgDO;
import com.hyd.resultdeal.utils.TextFileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class TextFileTest {
    @Test
    void contextLoads() {
        Gson gson = new Gson();
        String msgFilePath = "D:\\mq\\msgFile\\05-140106027-20200724032635097"+".json";
        String msgInfoJson = TextFileUtils.readFileContent(msgFilePath);
        System.out.println(msgInfoJson);
        MessageInfoDO info = gson.fromJson(msgInfoJson, MessageInfoDO.class);
        System.out.println(info.toString());



    }
}
