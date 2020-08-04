package com.hyd.orgmaintain.service.impl;

import com.hyd.orgmaintain.info.QueryOrganinfo;
import com.hyd.orgmaintain.mapper.OrgMaintainMapper;
import com.hyd.orgmaintain.service.OrgMaintinService;
import com.hyd.orgmaintain.vo.CityVo;
import com.hyd.orgmaintain.vo.OrgUser;
import com.hyd.orgmaintain.vo.Organ;
import com.hyd.orgmaintain.vo.ProvinceVO;
import com.hyd.system.SPMConfig;
import com.hyd.system.exceptclass.BusineException;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.factory.ResultFactory;
import com.hyd.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgMaintinServiceImpl implements OrgMaintinService {


    @Autowired
    OrgMaintainMapper orgMaintainMapper;

    @Autowired
    StringUtils stringUtils;

    @Override
    public ProvinceVO initAllProvince() {
        try {
            return orgMaintainMapper.initAllProvince(SPMConfig.getLocalPrev() + "000000");
        }catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询省失败");
        }
    }

    @Override
    public List<CityVo> foundZoneByParCode(QueryOrganinfo queryOrganinfo) {
        try {
            return orgMaintainMapper.foundZoneByParCode(queryOrganinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询失败");
        }
    }

    @Override
    public List<Organ> initOrganList(Organ organ) {
        try {
            if((!"".equals(organ.getProvinceCd()))&&organ.getProvinceCd()!=null) {
                int i = Integer.parseInt(organ.getProvinceCd());
                organ.setProvinceCd(String.valueOf(i/1000000));
            }
            if((!"".equals(organ.getCityCd()))&&organ.getCityCd()!=null) {
                int i = Integer.parseInt(organ.getCityCd());
                organ.setCityCd(String.valueOf(i/10000));
            }
        if (!organ.getStraight().equals("")) {
            System.out.println("有无生效基层直报机构");
            return orgMaintainMapper.queryOrganByHasUserForZB1(organ);
        } else if (!organ.getCountyControl().equals("")) {
            System.out.println("有无生效县本级质控机构");
            return orgMaintainMapper.queryOrganByHasUserForBJ1(organ);
        } else if (!organ.getNoactive().equals("")) {
            System.out.println("无生效用户机构");
            return orgMaintainMapper.queryOrganByNoAciveUser(organ);
        } else {
            System.out.println("所有用户机构");
            return orgMaintainMapper.queryOrganByHasUser(organ);
        }
        }catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询机构列表失败");
        }
    }

    @Override
    public Organ queryOrganByCd(QueryOrganinfo queryOrganinfo) {
        return orgMaintainMapper.queryOrganByCd(queryOrganinfo);
    }

    @Override
    public List<OrgUser> queryOrganUser(QueryOrganinfo queryOrganinfo) {
        try {
            return orgMaintainMapper.queryOrganUser(queryOrganinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取机构用户列表失败");
        }
    }

    @Override
    public Boolean iSCodeRepetition(QueryOrganinfo queryOrganinfo) {
        try {
            return orgMaintainMapper.iSCodeRepetition(queryOrganinfo) == 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusineException("查询机构编码失败");
        }
    }



    @Override
    public ResultFactory addSPMOrgan(Organ organ) {
        try {
            String org = stringUtils.getCd("Org");
            organ.setCd(org.substring(0,3)+ org.substring(5,11)+org.substring(12,15));
            orgMaintainMapper.addSPMOrgan(organ);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusineException("新增机构失败");
        }
    }

    @Override
    public ResultFactory updateSPMOrgan(Organ organ) {
        try {
            orgMaintainMapper.updateSPMOrgan(organ);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusineException("修改机构失败");
        }
    }

    @Override
    public ResultFactory updateState(QueryOrganinfo queryOrganinfo) {
        try {
            if(queryOrganinfo.getPcd().equals("1")) {
                queryOrganinfo.setPcd("-1");
                orgMaintainMapper.updateState(queryOrganinfo);
            }else {
                queryOrganinfo.setPcd("1");
                orgMaintainMapper.updateState(queryOrganinfo);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusineException("修改状态失败");
        }

    }

    @Override
    public Boolean isOrganRepetition(QueryOrganinfo queryOrganinfo) {
        try {
            return orgMaintainMapper.isOrganRepetition(queryOrganinfo)==0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusineException("查询县本级机构重复失败");
        }

    }

    @Override
    public Boolean isOrganUserBJ(QueryOrganinfo queryOrganinfo) {
        try {
            return orgMaintainMapper.isOrganUserBJ(queryOrganinfo)==0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusineException("查询机构是否存在县本1用户失败");
        }

    }

    @Override
    public Boolean isOrganUserZB(QueryOrganinfo queryOrganinfo) {
        try {
            return orgMaintainMapper.isOrganUserZB(queryOrganinfo)==0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusineException("查询机构是否存在基层直报用户失败");
        }

    }

}
