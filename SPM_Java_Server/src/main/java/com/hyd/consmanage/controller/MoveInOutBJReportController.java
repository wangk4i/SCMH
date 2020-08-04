package com.hyd.consmanage.controller;


import com.hyd.consmanage.info.QueryMoveInOutBJReportinfo;
import com.hyd.consmanage.service.CommunityOutPatService;
import com.hyd.consmanage.service.MoveInOutBJReportService;
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
@RequestMapping("/PRCMoveInOut")
public class MoveInOutBJReportController {


    @Autowired
    MoveInOutBJReportService moveInOutBJReportService;

    @Autowired
    ThreelevelService<QueryMoveInOutBJReportinfo> threelevelService;

    /**
     * 获取下级地区
     * @return
     */
    @PostMapping("/Province")
    ResultVO initProvince(@RequestBody QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo){
        return ResultFactory.success(threelevelService.initProvince(queryMoveInOutBJReportinfo));
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
    ResultVO initOrgan(@RequestBody QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo){
        return ResultFactory.success(threelevelService.initOrgan(queryMoveInOutBJReportinfo));
    }


    /**
     * 查询报告卡迁入列表
     */
    @PostMapping("/QueryByInUserList")
    ResultPageVO queryByInUserList(@RequestBody QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo){
        return ResultPageFactory.success(moveInOutBJReportService.queryByInUserList(queryMoveInOutBJReportinfo),queryMoveInOutBJReportinfo.getCount());
    };


    /**
     * 查询报告卡迁入列表
     */
    @PostMapping("/QueryByOutUserList")
    ResultPageVO queryByOutUserList(@RequestBody QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo){
        return ResultPageFactory.success(moveInOutBJReportService.queryByOutUserList(queryMoveInOutBJReportinfo),queryMoveInOutBJReportinfo.getCount());
    };


    /**
     * 患者信息详情
     */
    @PostMapping("/ViewInPat")
    ResultVO viewInPat(@RequestBody QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo){
        return ResultFactory.success(moveInOutBJReportService.viewInPat(queryMoveInOutBJReportinfo));
    };

    /**
     * 患者信息详情
     */
    @PostMapping("/ViewOutPat")
    ResultVO viewOutPat(@RequestBody QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo){
        return ResultFactory.success(moveInOutBJReportService.viewOutPat(queryMoveInOutBJReportinfo));
    };





}
