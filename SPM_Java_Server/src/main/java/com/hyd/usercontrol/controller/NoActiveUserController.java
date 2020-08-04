package com.hyd.usercontrol.controller;


import com.hyd.system.factory.ResultFactory;
import com.hyd.system.vo.ResultVO;
import com.hyd.usercontrol.info.NoActiveQueryUserinfo;
import com.hyd.usercontrol.info.QueryUserControlinfo;
import com.hyd.usercontrol.info.QueryUserinfo;
import com.hyd.usercontrol.service.NoActiveUserControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/NoActiveUserControl")
public class NoActiveUserController {
    @Autowired
    private NoActiveUserControlService noActiveUserControlService;

    /**
     * 获取机构
     * @param queryUserControlinfo
     * @return
     */
    @PostMapping("/Organ")
    ResultVO initOrganList(@RequestBody QueryUserControlinfo queryUserControlinfo){
        return ResultFactory.success(noActiveUserControlService.initOrganList(queryUserControlinfo));
    }

    /**
     * 查询无活动用户列表
     */
    @PostMapping("/QueryByUserList")
    ResultVO queryByUserList(@RequestBody NoActiveQueryUserinfo noActiveQueryUserinfo){
        return ResultFactory.success(noActiveUserControlService.queryByNoActiveUserList(noActiveQueryUserinfo));
    }


    /**
     * 根据Cd查询无活动用户信息
     */
    @PostMapping("/QueryByNoActiveUserById")
    ResultVO queryByNoActiveUserById(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(noActiveUserControlService.queryByNoActiveUserById(queryUserinfo));
    }

    /**
     * 删除无活动用户
     */
    @PostMapping("/DelNoActive")
    ResultVO delNoActive(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(noActiveUserControlService.delNoActive(queryUserinfo));
    }


    /**
     * 查询县的父级地区
     * @param queryUserinfo
     * @return
     */
    @PostMapping("/QueryParentZone")
    ResultVO queryParentZone(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(noActiveUserControlService.queryParentZone(queryUserinfo));
    }



}
