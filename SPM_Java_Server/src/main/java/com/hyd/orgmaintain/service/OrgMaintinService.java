package com.hyd.orgmaintain.service;



import com.hyd.orgmaintain.info.QueryOrganinfo;
import com.hyd.orgmaintain.vo.CityVo;
import com.hyd.orgmaintain.vo.OrgUser;
import com.hyd.orgmaintain.vo.Organ;
import com.hyd.orgmaintain.vo.ProvinceVO;
import com.hyd.system.factory.ResultFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface OrgMaintinService {

    //获取省
    ProvinceVO initAllProvince();
    //获取市
    List<CityVo> foundZoneByParCode(QueryOrganinfo queryOrganinfo);

    //获取机构列表
    List<Organ> initOrganList(Organ organ);

    //根据Cd获取机构
    Organ queryOrganByCd(QueryOrganinfo queryOrganinfo);

    //获取机构用户列表
    List<OrgUser> queryOrganUser(QueryOrganinfo queryOrganinfo);

    //判读机构编码重复
    Boolean iSCodeRepetition(QueryOrganinfo queryOrganinfo);

    //添加机构
    ResultFactory addSPMOrgan(Organ organ);
    //修改机构
    ResultFactory updateSPMOrgan(Organ organ);
    //修改状态
    ResultFactory updateState(QueryOrganinfo queryOrganinfo);

    //判断县本级机构是否存在
    Boolean isOrganRepetition(QueryOrganinfo queryOrganinfo);

    //判断机构用户是否有县本级1
    Boolean isOrganUserBJ(QueryOrganinfo queryOrganinfo);
    //判断机构用户是否有直报1
    Boolean isOrganUserZB(QueryOrganinfo queryOrganinfo);
}
