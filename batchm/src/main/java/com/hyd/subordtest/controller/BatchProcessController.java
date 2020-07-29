package com.hyd.subordtest.controller;

import com.hyd.subordtest.mapper.BatchMsgBuildMapper;
import com.hyd.subordtest.service.BasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/BatchProcess")
public class BatchProcessController {
    @Autowired
    BasicInfoService basicInfoService;
    @Autowired(required = false)
    BatchMsgBuildMapper batchMsgBuildMapper;

    @RequestMapping("/batchDocumentToXml")
    public String batchDocumentToXml(){
        basicInfoService.batchProcessDocument();
        return "success";
    }




    @RequestMapping("/testm")
    public String testmapp(){
        //String sql ="select top 1 Cd,ZoneCd,OrganCd,FIELDPK from SPM_SPMPatInfoMana where State=1 and DelStatus='DelLogo001'and len(isnull(FIELDPK,''))=0";

        String sql = "\n" +
                "\n" +
                "\t   select top 1 Cd,ZoneCd,OrganCd,FIELDPK \n" +
                "\t\tfrom SPM_SPMPatInfoMana \n" +
                "\t\twhere State=1 and DelStatus='DelLogo001'\n" +
                "\t\tand len(isnull(FIELDPK,''))=0\n" +
                "\t\n";
        List<Map<String, Object>> list02 = batchMsgBuildMapper.queryBatchDataByConf(sql);
        System.out.println(list02);

        return "success";
    }

}
