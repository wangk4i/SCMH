package com.hyd.consmanage.service;


import com.hyd.consmanage.info.QueryCommunityOutPatinfo;
import com.hyd.consmanage.info.QueryMoveInOutBJReportinfo;
import com.hyd.consmanage.vo.*;
import com.hyd.system.info.ExtInfoObj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MoveInOutBJReportService {

//    //获取下级地区
//    List<Region> initProvince(QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);
//    //初始化下拉
//    List<Region> initAllSelect(ExtInfoObj extInfoObj);
//    //获取机构
//    List<Organ> initOrgan(QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);

    //获取报告卡迁入列表
    List<MoveInOutBJReport> queryByInUserList(QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);

    //获取报告卡迁出列表
    List<MoveInOutBJReport> queryByOutUserList(QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);

    //迁入患者报告卡详情
    ViewInPat viewInPat(QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);

    //迁出患者报告卡详情
    ViewOutPat viewOutPat(QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);
}
