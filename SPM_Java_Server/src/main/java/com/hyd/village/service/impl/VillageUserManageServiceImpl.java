package com.hyd.village.service.impl;

import com.hyd.system.SPMConfig;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.village.info.VillageUserManageinfo;
import com.hyd.village.mapper.VillageUserManageMapper;
import com.hyd.village.service.VillageUserManageService;
import com.hyd.village.vo.CityVO;
import com.hyd.village.vo.Organ;
import com.hyd.village.vo.ProvinceVO;
import com.hyd.village.vo.VillageUserManageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VillageUserManageServiceImpl implements VillageUserManageService {

    @Autowired
    VillageUserManageMapper villageUserManageMapper;

    @Override
    public List<VillageUserManageVo> villageUserList(VillageUserManageinfo villageUserManageinfo) {
        try {
            if (villageUserManageinfo.getZoneCd() != "" && villageUserManageinfo.getZoneCd() != null) {
                int i = Integer.parseInt(villageUserManageinfo.getZoneCd());
                if (i % 1000000 == 0) {
                    villageUserManageinfo.setZoneCd(String.valueOf(i / 1000000));
                } else if (i % 10000 == 0) {
                    villageUserManageinfo.setZoneCd(String.valueOf(i / 10000));
                } else if (i % 100 == 0) {
                    villageUserManageinfo.setZoneCd(String.valueOf(i / 100));
                }
            }
            return villageUserManageMapper.villageUserList(villageUserManageinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取用户信息列表失败");
        }
    }
    @Override
    public Integer villageUserListSize(VillageUserManageinfo villageUserManageinfo) {
        try {
            if (villageUserManageinfo.getZoneCd() != "" && villageUserManageinfo.getZoneCd() != null) {
                int i = Integer.parseInt(villageUserManageinfo.getZoneCd());
                if (i % 1000000 == 0) {
                    villageUserManageinfo.setZoneCd(String.valueOf(i / 1000000));
                } else if (i % 10000 == 0) {
                    villageUserManageinfo.setZoneCd(String.valueOf(i / 10000));
                } else if (i % 100 == 0) {
                    villageUserManageinfo.setZoneCd(String.valueOf(i / 100));
                }
            }
            return villageUserManageMapper.villageUserListSize(villageUserManageinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取用户信息列表失败");
        }
    }


    @Override
    public CityVO queryParentZone(VillageUserManageinfo villageUserManageinfo) {
        try {
            return villageUserManageMapper.queryParentZone(villageUserManageinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取县级父地区失败");
        }
    }


    @Override
    public ProvinceVO initAllProvince() {
        try {
            return villageUserManageMapper.initAllProvince(SPMConfig.getLocalPrev() + "000000");
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取省失败");
        }
    }


    @Override
    public List<CityVO> initAllCity(VillageUserManageinfo villageUserManageinfo) {
        try {
            return villageUserManageMapper.initAllCity(villageUserManageinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取市县失败");
        }
    }

    @Override
    public List<Organ> initOrganList(VillageUserManageinfo villageUserManageinfo) {
        try {
            if (villageUserManageinfo.getZoneCd() != "" && villageUserManageinfo.getZoneCd() != null) {
                int i = Integer.parseInt(villageUserManageinfo.getZoneCd());
                if (i % 1000000 == 0) {
                    villageUserManageinfo.setZoneCd(String.valueOf(i / 1000000));
                } else if (i % 10000 == 0) {
                    villageUserManageinfo.setZoneCd(String.valueOf(i / 10000));
                } else if (i % 100 == 0) {
                    villageUserManageinfo.setZoneCd(String.valueOf(i / 100));
                }
            }
            return villageUserManageMapper.initOrganList(villageUserManageinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取机构失败");
        }
    }

}
