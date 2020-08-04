package com.hyd.village.controller;


import com.hyd.system.factory.ResultFactory;
import com.hyd.system.factory.ResultPageFactory;
import com.hyd.system.vo.ResultPageVO;
import com.hyd.system.vo.ResultVO;
import com.hyd.village.info.VillageUserManageinfo;
import com.hyd.village.service.VillageUserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/VillageManage")
public class VillageUserManageController {

    @Autowired
    VillageUserManageService villageUserManageService;


    //查询列表
    @PostMapping("/VillageUserList")
    ResultPageVO villageUserList(@RequestBody VillageUserManageinfo villageUserManageinfo){
        return ResultPageFactory.success(villageUserManageService.villageUserList(villageUserManageinfo),villageUserManageService.villageUserListSize(villageUserManageinfo));
    }


    //查询县的父级地区
    @PostMapping("/QueryParentZone")
    ResultVO queryParentZone(@RequestBody VillageUserManageinfo villageUserManageinfo){
        return ResultFactory.success(villageUserManageService.queryParentZone(villageUserManageinfo));
    }

    //获取省
    @PostMapping("/Province")
    ResultVO initAllProvince(){
        return ResultFactory.success(villageUserManageService.initAllProvince());
    }

    //获取市县
    @PostMapping("/City")
    ResultVO initAllCity(@RequestBody VillageUserManageinfo villageUserManageinfo){
        return ResultFactory.success(villageUserManageService.initAllCity(villageUserManageinfo));
    }

    //获取机构
    @PostMapping("/Organ")
    ResultVO initOrganList(@RequestBody VillageUserManageinfo villageUserManageinfo){
        return ResultFactory.success(villageUserManageService.initOrganList(villageUserManageinfo));
    }




}
