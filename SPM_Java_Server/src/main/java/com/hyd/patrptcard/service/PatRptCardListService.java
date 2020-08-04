package com.hyd.patrptcard.service;

import com.hyd.patrptcard.info.PatRptCardListinfo;
import com.hyd.patrptcard.vo.MoveReport;
import com.hyd.patrptcard.vo.PatRptCardVo;
import com.hyd.patrptcard.vo.ViewPat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatRptCardListService {


    //查询列表
    List<PatRptCardVo> patRptCardListZB(PatRptCardListinfo patRptCardListinfo);

    //查询流转记录
    List<MoveReport> moverecard(PatRptCardListinfo patRptCardListinfo);
    //查询流转记录条数
    Integer moverecardCount(PatRptCardListinfo patRptCardListinfo);
    //患者报告卡详情
    ViewPat viewPat(PatRptCardListinfo patRptCardListinfo);
}

