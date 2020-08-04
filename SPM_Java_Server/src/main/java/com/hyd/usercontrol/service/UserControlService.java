package com.hyd.usercontrol.service;



import com.hyd.system.factory.ResultFactory;
import com.hyd.system.factory.ResultsFactory;
import com.hyd.usercontrol.info.OperateQueryUserinfo;
import com.hyd.usercontrol.info.QueryUserControlinfo;
import com.hyd.usercontrol.info.QueryUserinfo;
import com.hyd.usercontrol.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserControlService {


    //根据Cd查询用户
    User initUserByCd(QueryUserControlinfo queryUserControlinfo);

    //修改用户个人信息
    ResultFactory modifyinformation(User user);

    //获取省
    ProvinceVO initAllProvince();

    //获取机构
    List<Organ> initOrganList(QueryUserControlinfo queryUserControlinfo);

    //获取市，县，乡镇
    List<ProvinceVO> foundZoneByParCode(QueryUserinfo queryUserinfo);

    //根据Cd获取地区
    ProvinceVO getZoneByCd(QueryUserinfo queryUserinfo);

    //判断登录名是否存在
    Boolean isAccount(QueryUserinfo queryUserinfo);

    //判断相同角色的身份证是否重复
    Boolean isidentity(QueryUserinfo queryUserinfo);

    //修改用户时 判断相同角色的身份证是否重复
    Boolean isidentitys(QueryUserinfo queryUserinfo);

    //判断县本级用户是否存在
    Boolean isUserRepetition(QueryUserinfo queryUserinfo);
//
//    //判断是否已经启用
//    Boolean isstartusing(QueryUserinfo queryUserinfo);


    //添加用户
    ResultFactory foundUser(User user);

    //查询用户列表
    List<AddUser> queryByUserList(QueryUserinfo queryUserinfo);

    //查询县的父级地区
    ProvinceVO queryParentZone(QueryUserinfo queryUserinfo);

    //获取用户类型
    List<UserSelectRoleVO> userGenreClassify(Organ organ);



    //查询操作用户列表
    List<OperateUser> queryByUserOperateList(OperateQueryUserinfo operateQueryUserinfo);




    //删除操作用户
    ResultFactory delOperate(QueryUserinfo queryUserinfo);


    //根据CD获取用户信息
    OperateUser queryByOperateById(QueryUserinfo queryUserinfo);

    //修改用户信息
    ResultFactory updateOperateById(OperateQueryUserinfo operateQueryUserinfo);

    //用户重置密码
    ResultFactory restUserPwd(QueryUserinfo queryUserinfo);

    //改变用户启用状态
    ResultFactory activeStatusUser(QueryUserinfo queryUserinfo);

    //改变APP启用状态
    ResultFactory appAuthUser(QueryUserinfo queryUserinfo);

    //设备授权码
    ResultFactory deviceIDUser(QueryUserinfo queryUserinfo);

    //验证账号
    ResultsFactory initUserverify(QueryUserinfo queryUserinfo);
    //验证账号
    ResultsFactory initUpdateUserverify(QueryUserinfo queryUserinfo);

    //验证管理员人数
    Boolean verificationRole(QueryUserinfo queryUserinfo);

}
