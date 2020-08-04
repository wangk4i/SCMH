package com.hyd.qualitycontrol.controller;


import com.hyd.qualitycontrol.info.NotInspectYearPatinfo;
import com.hyd.qualitycontrol.service.NotInspectYearPatService;
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
@RequestMapping("/NotInspectYearPat")
public class NotInspectYearPatController {

    @Autowired
    NotInspectYearPatService notInspectYearPatService;

    @Autowired
    ThreelevelService<NotInspectYearPatinfo> threelevelService;

    /**
     * 获取下级地区
     * @return
     */
    @PostMapping("/Province")
    ResultVO initProvince(@RequestBody NotInspectYearPatinfo notInspectYearPatinfo){
        return ResultFactory.success(threelevelService.initProvince(notInspectYearPatinfo));
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
    ResultVO initOrgan(@RequestBody NotInspectYearPatinfo notInspectYearPatinfo){
        return ResultFactory.success(threelevelService.initOrgan(notInspectYearPatinfo));
    };



    /**
     * 查询列表
     */
    @PostMapping("/QueryByUserList")
    ResultPageVO queryByUserList(@RequestBody NotInspectYearPatinfo notInspectYearPatinfo){
        return ResultPageFactory.success(notInspectYearPatService.queryByUserList(notInspectYearPatinfo),notInspectYearPatinfo.getCount());
    };






}
