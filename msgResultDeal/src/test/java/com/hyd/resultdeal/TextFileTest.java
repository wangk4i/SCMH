package com.hyd.resultdeal;

import com.hyd.resultdeal.utils.TextFileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TextFileTest {
    @Test
    void contextLoads() {
        String path = "D:\\mq\\exchange\\07returntxt\\05-140106027-20200724032635097.txt";

        String jsonstr = TextFileUtils.readFileContent(path);
        System.out.println(jsonstr);

        /*String regEx = "\"id\":\"([\\s\\S]*?)\"";
        String oldId = TextFileUtils.matchValue(jsonstr,regEx);

        TextFileUtils.alterStringToCreateNewFile(path, oldId, "ImPatDischarge000000123");*/

    }
}
