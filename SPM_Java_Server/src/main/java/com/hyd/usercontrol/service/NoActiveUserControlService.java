package com.hyd.usercontrol.service;

import com.hyd.system.factory.ResultFactory;
import com.hyd.usercontrol.info.NoActiveQueryUserinfo;
import com.hyd.usercontrol.info.QueryUserControlinfo;
import com.hyd.usercontrol.info.QueryUserinfo;
import com.hyd.usercontrol.vo.NoActiveUser;
import com.hyd.usercontrol.vo.Organ;
import com.hyd.usercontrol.vo.ProvinceVO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface NoActiveUserControlService {

    //获取机构
    List<Organ> initOrganList(QueryUserControlinfo queryUserControlinfo);

    //无活动用户列表
    List<NoActiveUser> queryByNoActiveUserList(NoActiveQueryUserinfo noActiveQueryUserinfo);

    //根据CD获取无活动用户信息
    NoActiveUser queryByNoActiveUserById(QueryUserinfo queryUserinfo);

    //删除无活动用户
    ResultFactory delNoActive(QueryUserinfo queryUserinfo);

    //查询县的父级地区
    ProvinceVO queryParentZone(QueryUserinfo queryUserinfo);
}
