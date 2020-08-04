package com.hyd.consmanage.service;


import com.hyd.consmanage.info.QueryCommunityOutPatinfo;
import com.hyd.consmanage.vo.CommunityOutPat;
import com.hyd.consmanage.vo.Organ;
import com.hyd.consmanage.vo.Region;
import com.hyd.consmanage.vo.ViewPat;
import com.hyd.system.info.ExtInfoObj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommunityOutPatService {

//    //获取下级地区
//    List<Region> initProvince(QueryCommunityOutPatinfo queryCommunityOutPatinfo);
//    //初始化下拉
//    List<Region> initAllSelect(ExtInfoObj extInfoObj);
//    //获取机构
//    List<Organ> initOrgan(QueryCommunityOutPatinfo queryCommunityOutPatinfo);
    //获取列表
    List<CommunityOutPat> queryByUserList(QueryCommunityOutPatinfo queryCommunityOutPatinfo);
    //患者信息详情
    ViewPat viewOutPat(QueryCommunityOutPatinfo queryCommunityOutPatinfo);
}
