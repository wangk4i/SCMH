package com.hyd.village.service;

import com.hyd.village.info.VillageUserManageinfo;
import com.hyd.village.vo.CityVO;
import com.hyd.village.vo.Organ;
import com.hyd.village.vo.ProvinceVO;
import com.hyd.village.vo.VillageUserManageVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VillageUserManageService {

    //查询列表
    List<VillageUserManageVo> villageUserList(VillageUserManageinfo villageUserManageinfo);
    //查询总条数
    Integer villageUserListSize(VillageUserManageinfo villageUserManageinfo);


    //查询县的父级地区
    CityVO queryParentZone(VillageUserManageinfo villageUserManageinfo);

    //获取省
    ProvinceVO initAllProvince();

    //获取市县
    List<CityVO> initAllCity(VillageUserManageinfo villageUserManageinfo);

    //获取机构
    List<Organ> initOrganList(VillageUserManageinfo villageUserManageinfo);



}

