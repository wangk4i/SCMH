package com.hyd.patrptcard.controller;


import com.hyd.patrptcard.info.PatRptCardListinfo;
import com.hyd.patrptcard.service.PatRptCardListService;
import com.hyd.selectquery.service.ThreelevelService;
import com.hyd.system.factory.ResultFactory;
import com.hyd.system.factory.ResultPageFactory;
import com.hyd.system.info.ExtInfoObj;
import com.hyd.system.vo.ResultPageVO;
import com.hyd.system.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PatRptCard")
public class PatRptCardListController {

    @Autowired
    PatRptCardListService patRptCardListService;

    @Autowired
    ThreelevelService threelevelService;

    /**
     * 获取下级地区
     * @return
     */
    @PostMapping("/Province")
    ResultVO initProvince(@RequestBody PatRptCardListinfo reportcardsQueryinfo){
        return ResultFactory.success(threelevelService.initProvince(reportcardsQueryinfo));
    };

    /**
     * 初始化下拉
     * @return
     */
    @PostMapping("/InitSelect")
    ResultVO initAllSelect(@RequestBody ExtInfoObj extInfoObj){
        return ResultFactory.success(threelevelService.initAllSelect(extInfoObj));
    };

    /**
     * 获取机构
     */
    @PostMapping("/Organ")
    ResultVO initOrgan(@RequestBody PatRptCardListinfo reportcardsQueryinfo){
        return ResultFactory.success(threelevelService.initOrgan(reportcardsQueryinfo));
    }


    //查询列表
    @PostMapping("/PatRptCardListZB")
    ResultPageVO patRptCardListZB(@RequestBody PatRptCardListinfo patRptCardListinfo){
        return ResultPageFactory.success(patRptCardListService.patRptCardListZB(patRptCardListinfo),patRptCardListinfo.getCount());
    }


    //查询流转记录
    @PostMapping("/Moverecard")
    ResultPageVO moverecard(@RequestBody PatRptCardListinfo patRptCardListinfo){
        return ResultPageFactory.success(patRptCardListService.moverecard(patRptCardListinfo),patRptCardListService.moverecardCount(patRptCardListinfo));
    }


    //患者报告卡详情
    @PostMapping("/ViewPat")
    ResultPageVO viewPat(@RequestBody PatRptCardListinfo patRptCardListinfo){
        return ResultPageFactory.success(patRptCardListService.viewPat(patRptCardListinfo),patRptCardListService.moverecardCount(patRptCardListinfo));
    }




}
