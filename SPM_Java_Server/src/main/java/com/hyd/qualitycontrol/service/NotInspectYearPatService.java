package com.hyd.qualitycontrol.service;


import com.hyd.qualitycontrol.info.NotInspectYearPatinfo;
import com.hyd.qualitycontrol.vo.NotInspectYearPat;
import com.hyd.qualitycontrol.vo.Organ;
import com.hyd.qualitycontrol.vo.Region;
import com.hyd.system.info.ExtInfoObj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotInspectYearPatService {

//    //获取下级地区
//    List<Region> initProvince(NotInspectYearPatinfo notInspectYearPatinfo);
//    //初始化下拉
//    List<Region> initAllSelect(ExtInfoObj extInfoObj);
//    //获取机构
//    List<Organ> initOrgan(NotInspectYearPatinfo notInspectYearPatinfo);
    //获取列表
    List<NotInspectYearPat> queryByUserList(NotInspectYearPatinfo notInspectYearPatinfo);


}
