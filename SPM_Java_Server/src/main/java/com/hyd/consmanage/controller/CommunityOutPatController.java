package com.hyd.consmanage.controller;


import com.hyd.consmanage.info.QueryCommunityOutPatinfo;
import com.hyd.consmanage.service.CommunityOutPatService;
import com.hyd.consmanage.vo.CommunityOutPat;
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
@RequestMapping("/Patientflow")
public class CommunityOutPatController {

    @Autowired
    CommunityOutPatService communityOutPatService;

    @Autowired
    ThreelevelService<QueryCommunityOutPatinfo> threelevelService;

    /**
     * 获取下级地区
     * @return
     */
    @PostMapping("/Province")
    ResultVO initProvince(@RequestBody QueryCommunityOutPatinfo queryCommunityOutPatinfo){
        return ResultFactory.success(threelevelService.initProvince(queryCommunityOutPatinfo));
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
    ResultVO initOrgan(@RequestBody QueryCommunityOutPatinfo queryCommunityOutPatinfo){
        return ResultFactory.success(threelevelService.initOrgan(queryCommunityOutPatinfo));
    };


    /**
     * 查询列表
     */
    @PostMapping("/QueryByUserList")
    ResultPageVO queryByUserList(@RequestBody QueryCommunityOutPatinfo queryCommunityOutPatinfo){
        return ResultPageFactory.success(communityOutPatService.queryByUserList(queryCommunityOutPatinfo),queryCommunityOutPatinfo.getCount());
    };

    /**
     * 患者信息详情
     */
    @PostMapping("/ViewOutPat")
    ResultVO ViewOutPat(@RequestBody QueryCommunityOutPatinfo queryCommunityOutPatinfo){
        return ResultFactory.success(communityOutPatService.viewOutPat(queryCommunityOutPatinfo));
    };






}
