package com.hyd.patInfomanagement.service;

import com.hyd.patInfomanagement.info.ReportcardsQueryinfo;
import com.hyd.patInfomanagement.vo.*;
import com.hyd.system.info.ExtInfoObj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportcardsQueryService {



//    //获取下级地区
//    List<Region> initProvince(ReportcardsQueryinfo reportcardsQueryinfo);
//    //初始化下拉
//    List<Region> initAllSelect(ExtInfoObj extInfoObj);
//    //获取机构
//    List<Organ> initOrgan(ReportcardsQueryinfo reportcardsQueryinfo);

    //查询流转记录
    List<MoveReport> moverecard(ReportcardsQueryinfo reportcardsQueryinfo);
    //查询流转记录条数
    Integer moverecardCount(ReportcardsQueryinfo reportcardsQueryinfo);
    //查询列表
    List<ReportcardsQueryVo> reportcardList(ReportcardsQueryinfo reportcardsQueryinfo);

    //患者报告卡详情
    ViewPat viewPat(ReportcardsQueryinfo reportcardsQueryinfo);



}

