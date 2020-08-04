package com.hyd.exportexcel.controller;


import com.hyd.exportexcel.info.Annualorganauditinfo;
import com.hyd.exportexcel.mapper.ExportExcelMapper;
import com.hyd.exportexcel.service.AnnualorganauditExportExcelUtil;
import com.hyd.exportexcel.vo.AnnualorganauditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/ExportExcel")
public class AnnualorganauditController {

    @Autowired
    AnnualorganauditExportExcelUtil annualorganauditExportExcelUtil;

    @Autowired
    ExportExcelMapper exportExcelMapper;

    @GetMapping("/Annualorganaudit")
        //  @RequestMapping(value = "/Annualorganaudit",method = RequestMethod.POST)
    void initAllProvince(@RequestParam String zoneCd,
                         @RequestParam String organCd,
                         @RequestParam String yearDateStart,
                         @RequestParam String yearDateEnd,
                         HttpServletResponse response) throws Exception {

        Annualorganauditinfo annualorganauditinfo = new Annualorganauditinfo(zoneCd, organCd, yearDateStart, yearDateEnd);

        List<AnnualorganauditVo> addUsers = exportExcelMapper.annualorganauditList(annualorganauditinfo);

        if (addUsers.size() == 0) throw new RuntimeException("导出年份数据为空");

//        String srcFilePath = getClass().getClassLoader().getResource("exportexcel/Annualorganaudit.xlsx").getPath();
        String desFilePath = yearDateStart + "专业机构年报" + System.currentTimeMillis() + ".xlsx";
//        annualorganauditExportExcelUtil.exportExcel(srcFilePath, desFilePath, addUsers,response);

        /***
         * 使用注解配置类
         */
        //2.加载模板
        // Resource resource = new ClassPathResource("exportexcel/Annualorganaudits.xlsx");
        // FileInputStream fis = new FileInputStream(resource.getFile());
        ClassPathResource resource = new ClassPathResource("exportexcel/Annualorganaudits.xlsx");

        /**
         *
         */
        annualorganauditExportExcelUtil.exportExcel(desFilePath,resource.getInputStream(),yearDateStart+"年四川省精神卫生机构调查表汇总",addUsers,response,yearDateStart);

        //3.通过工具类完成下载
//        new ExcelExportUtil(AnnualorganauditVo.class, 2, 1).export(response, resource.getInputStream(), addUsers, desFilePath, yearDateStart + "年四川省精神卫生机构调查表汇总",arr);


    }
}
