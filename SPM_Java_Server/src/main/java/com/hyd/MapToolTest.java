package com.hyd;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hyd.repeatpatmove.util.Tools;
import com.hyd.repeatpatmove.vo.GWResultVO;
import com.hyd.repeatpatmove.vo.RepeatPatInfo;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/4 11:24
 */

public class MapToolTest {
    public static void main(String[] args){

        String json = "{\n" +
                "    \"code\": 1,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "\t\t\t\t\"ID\":\"xxxxxx\",\n" +
                "\t\t\t\t\"PersonNo\":\"xxxxxx\", \n" +
                "\t\t\t\t\"ZoneCode\":\"110101000\", \n" +
                "\t\t\t\t\"OrgCode\":\"110101009\", \n" +
                "\t\t\t\t\"PatientName\":\"张三\", \n" +
                "\t\t\t\t\"IdCardTypeCode\":\"1\", \n" +
                "\t\t\t\t\"IdCard\":\"110111xxxxxxxxxxxx\", \n" +
                "\t\t\t\t\"GenderCode\":\"1\", \n" +
                "\t\t\t\t\"BirthDate\":\"19950407\", \n" +
                "\t\t\t\t\"CurrentAddrDetail\":\"北京市东城区东华门街道XX号\",\n" +
                "\t\t\t\t\"CurrentAddrCode\":\"110101003\", \n" +
                "\t\t\t\t\"DiseaseCode\":\"F20\",\n" +
                "\t\t\t\t\"ContactInformation\":\"李老师，手机：13912345678\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"ID\":\"xxxxxx\",\n" +
                "\t\t\t\t\"PersonNo\":\"xxxxxx\", \n" +
                "\t\t\t\t\"ZoneCode\":\"110101000\", \n" +
                "\t\t\t\t\"OrgCode\":\"110101009\", \n" +
                "\t\t\t\t\"PatientName\":\"张三\", \n" +
                "\t\t\t\t\"IdCardTypeCode\":\"1\", \n" +
                "\t\t\t\t\"IdCard\":\"110111xxxxxxxxxxxx\", \n" +
                "\t\t\t\t\"GenderCode\":\"1\", \n" +
                "\t\t\t\t\"BirthDate\":\"19950407\", \n" +
                "\t\t\t\t\"CurrentAddrDetail\":\"北京市东城区东华门街道XX号\",\n" +
                "\t\t\t\t\"CurrentAddrCode\":\"110101003\", \n" +
                "\t\t\t\t\"DiseaseCode\":\"F20\",\n" +
                "\t\t\t\t\"ContactInformation\":\"李老师，手机：13912345678\"\n" +
                "\t\t\t}\n" +
                "    ]\n" +
                "}\n";
        /*String json = "{\n" +
                "    \"code\": -1,\n" +
                "    \"message\": \"查询失败的原因\"\n" +
                "}\n";*/
        Type type = new TypeToken<GWResultVO<List<RepeatPatInfo>>>(){}.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        GWResultVO<List<RepeatPatInfo>> gwResult = gson.fromJson(json, type);
        System.out.println(gwResult);
        if (gwResult.getData()!=null){

        }


    }


}
