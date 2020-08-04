package com.hyd.usercontrol.controller;



import com.hyd.system.factory.ResultFactory;
import com.hyd.system.factory.ResultsFactory;
import com.hyd.system.vo.ResultVO;
import com.hyd.usercontrol.info.OperateQueryUserinfo;
import com.hyd.usercontrol.info.QueryUserControlinfo;
import com.hyd.usercontrol.info.QueryUserinfo;
import com.hyd.usercontrol.service.UserControlService;
import com.hyd.usercontrol.vo.OperateUser;
import com.hyd.usercontrol.vo.Organ;
import com.hyd.usercontrol.vo.User;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/UserControl")
public class UserController {

    @Autowired
    private UserControlService userControlService;


    /**
     * 获取省
     * @return
     */
    @PostMapping("/Province")
    ResultVO initAllProvince(){
        return ResultFactory.success(userControlService.initAllProvince());
    };

    /**
     *  获取市 区 乡镇
     */
    @PostMapping("/City")
   ResultVO foundZoneByParCode(@RequestBody QueryUserinfo queryUserinfo){
       return ResultFactory.success(userControlService.foundZoneByParCode(queryUserinfo));
   }

    /**
     * 获取机构
     * @param queryUserControlinfo
     * @return
     */
    @PostMapping("/Organ")
    ResultVO initOrganList(@RequestBody QueryUserControlinfo queryUserControlinfo){
        return ResultFactory.success(userControlService.initOrganList(queryUserControlinfo));
    }


    /**
     * 根据Cd获取地区
     */
    @PostMapping("/ZoneByCd")
    ResultVO  getZoneByCd(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(userControlService.getZoneByCd(queryUserinfo));
    }
    /**
     * 根据Cd查询用户
     * @param queryUserControlinfo
     * @return
     */
    @PostMapping("/ViewSPMUser")
    ResultVO initUserByCd(@RequestBody QueryUserControlinfo queryUserControlinfo){
        return ResultFactory.success(userControlService.initUserByCd(queryUserControlinfo));
    }

    /**
     * 修改用户个人信息
     * @return
     */
    @PostMapping("/updateUserInfo")
    ResultVO modifyinformation(@RequestBody User user){
        return ResultFactory.success(userControlService.modifyinformation(user));
    }

    /**
     * 验证账号
     * @return
     */
    @PostMapping("/initUserverify")
    ResultVO InitUserverify(@RequestBody QueryUserinfo queryUserinfo){
        return ResultsFactory.success(userControlService.initUserverify(queryUserinfo),queryUserinfo.getReminder());
    }
    /**
     * 验证账号
     * @return
     */
    @PostMapping("/initUpdateUserverify")
    ResultVO initUpdateUserverify(@RequestBody QueryUserinfo queryUserinfo){
        return ResultsFactory.success(userControlService.initUpdateUserverify(queryUserinfo),queryUserinfo.getReminder());
    }


    /**
     * 验证身份证是否存在
     * @return
     */
    @PostMapping("/Isidentity")
    ResultVO isidentity(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(userControlService.isidentity(queryUserinfo));
    }


    /**
     * 验证身份证是否存在
     * @return
     */
    @PostMapping("/Isidentitys")
    ResultVO isidentitys(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(userControlService.isidentitys(queryUserinfo));
    }

    /**
     * 验证登录名是否存在
     * @return
     */
    @PostMapping("/Account")
    ResultVO isAccount(@RequestBody QueryUserinfo queryUserinfo){
        System.out.println(queryUserinfo);
        return ResultFactory.success(userControlService.isAccount(queryUserinfo));
    }

//
//    /**
//     * 判断是否已经启用
//     * @return
//     */
//    @PostMapping("/Isstartusing")
//    ResultInfo isstartusing(@RequestBody QueryUserinfo queryUserinfo){
//        return ResultFactory.success(userControlService.isstartusing(queryUserinfo));
//    }
//
//
    /**
     * 判断 县本1用户是否存在
     */
    @PostMapping("/IsUserRepetition")
    ResultVO IsUserRepetition(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(userControlService.isUserRepetition(queryUserinfo));
    }

    /**
     * 添加
     * @return
     */
    @PostMapping("/FoundUser")
    ResultVO foundUser(@RequestBody User user){
        System.out.println(user);
        return ResultFactory.success(userControlService.foundUser(user));
    }

    /**
     * 查询用户列表
     */
    @PostMapping("/QueryByUserList")
    ResultVO queryByUserList(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(userControlService.queryByUserList(queryUserinfo));
    }

    /**
     * 查询县的父级地区
     * @param queryUserinfo
     * @return
     */
    @PostMapping("/QueryParentZone")
    ResultVO queryParentZone(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(userControlService.queryParentZone(queryUserinfo));
    }


    /**
     *  获取用户类型
     */
    @PostMapping("/UserGenreClassify")
    ResultVO UserGenreClassify(@RequestBody Organ organ){
        return ResultFactory.success(userControlService.userGenreClassify(organ));
    }


    /**
     * 查询操作用户列表
     */
    @PostMapping("/QueryByUserOperateList")
    ResultVO queryByUserOperateList(@RequestBody OperateQueryUserinfo operateQueryUserinfo){
        return ResultFactory.success(userControlService.queryByUserOperateList(operateQueryUserinfo));
    }


    /**
     * 获取用户操作机构
     * @param queryUserControlinfo
     * @return
     */
    @PostMapping("/OperateOrgan")
    ResultVO initOrganOperateList(@RequestBody QueryUserControlinfo queryUserControlinfo){
        return ResultFactory.success(userControlService.initOrganList(queryUserControlinfo));
    }



    /**
     * 删除操作用户
     */
    @PostMapping("/DelOperate")
    ResultVO delOperate(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(userControlService.delOperate(queryUserinfo));
    }


    /**
     * 根据Cd查询用户信息
     */
    @PostMapping("/QueryByOperateById")
    ResultVO queryByOperateById(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(userControlService.queryByOperateById(queryUserinfo));
    }

    /**
     * 修改用户信息
     */
    @PostMapping("/UpdateOperateById")
    ResultVO updateOperateById(@RequestBody OperateQueryUserinfo operateQueryUserinfo){
        return ResultFactory.success(userControlService.updateOperateById(operateQueryUserinfo));
    }

    /**
     * 修改用户信息
     */
    @PostMapping("/RestUserPwd")
    ResultVO restUserPwd(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(userControlService.restUserPwd(queryUserinfo));
    }


    /**
     * 改变用户启用状态
     */
    @PostMapping("/ActiveStatusUser")
    ResultVO activeStatusUser(@RequestBody QueryUserinfo queryUserinfo){
        ArrayList<Object> objects = new ArrayList<>();

        objects.forEach(System.out::println);


        return ResultFactory.success(userControlService.activeStatusUser(queryUserinfo));

    }


    /**
     * 改变App启用状态
     */
    @PostMapping("/AppAuthUser")
    ResultVO appAuthUser(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(userControlService.appAuthUser(queryUserinfo));
    }

    /**
     * 输入设备授权码
     */
    @PostMapping("/DeviceIDUser")
    ResultVO deviceIDUser(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(userControlService.deviceIDUser(queryUserinfo));
    }

    /**
     * 验证管理员人数
     */
    @PostMapping("/VerificationRole")
    ResultVO verificationRole(@RequestBody QueryUserinfo queryUserinfo){
        return ResultFactory.success(userControlService.verificationRole(queryUserinfo));
    }



}
