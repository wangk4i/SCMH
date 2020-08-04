package com.hyd.patInfomanagement.controller;


import com.hyd.patInfomanagement.info.ReportcardsQueryinfo;
import com.hyd.patInfomanagement.service.ReportcardsQueryService;
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
@RequestMapping("/ReportcardsQuery")
public class ReportcardsQueryController {

    @Autowired
    ReportcardsQueryService reportcardsQueryService;

    @Autowired
    ThreelevelService threelevelService;




    /**
     * 获取下级地区
     * @return
     */
    @PostMapping("/Province")
    ResultVO initProvince(@RequestBody ReportcardsQueryinfo reportcardsQueryinfo){
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
    ResultVO initOrgan(@RequestBody ReportcardsQueryinfo reportcardsQueryinfo){
        return ResultFactory.success(threelevelService.initOrgan(reportcardsQueryinfo));

    }


    //查询列表
    @PostMapping("/ReportcardList")
    ResultPageVO reportcardList(@RequestBody ReportcardsQueryinfo reportcardsQueryinfo){
        return ResultPageFactory.success(reportcardsQueryService.reportcardList(reportcardsQueryinfo),reportcardsQueryinfo.getCount());
    }

    //查询流转记录身挂只要今天没睡好的呀
    // 今天查询不大可数根本不可能
    @PostMapping("/Moverecard")
    ResultPageVO moverecard(@RequestBody ReportcardsQueryinfo reportcardsQueryinfo){
        return ResultPageFactory.success(reportcardsQueryService.moverecard(reportcardsQueryinfo),reportcardsQueryService.moverecardCount(reportcardsQueryinfo));
    }

    //患者报告卡详情
    @PostMapping("/ViewPat")
    ResultPageVO viewPat(@RequestBody ReportcardsQueryinfo reportcardsQueryinfo){
        return ResultPageFactory.success(reportcardsQueryService.viewPat(reportcardsQueryinfo),reportcardsQueryService.moverecardCount(reportcardsQueryinfo));
    }






}
