package com.hyd.patrptcard.service.impl;

import com.hyd.patrptcard.info.PatRptCardListinfo;
import com.hyd.patrptcard.mapper.PatRptCardListMapper;
import com.hyd.patrptcard.service.PatRptCardListService;
import com.hyd.patrptcard.vo.MoveReport;
import com.hyd.patrptcard.vo.PatRptCardVo;

import com.hyd.patrptcard.vo.ViewPat;
import com.hyd.system.exceptclass.SqlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatRptCardListServiceImpl implements PatRptCardListService {

    @Autowired
    PatRptCardListMapper patRptCardListMapper;



    @Override
    public List<PatRptCardVo> patRptCardListZB(PatRptCardListinfo patRptCardListinfo) {
        String errMsg = "";
        try {
            patRptCardListinfo.setStartNum((patRptCardListinfo.getPage() - 1) * patRptCardListinfo.getLimit()+1);
            patRptCardListinfo.setEndNum(patRptCardListinfo.getPage()*patRptCardListinfo.getLimit());
            List<PatRptCardVo> reportcardsQueryVos = patRptCardListMapper.patRptCardListZB(patRptCardListinfo);
            errMsg=patRptCardListinfo.getErrMsg();
            if (!"".equals(errMsg) && errMsg != null) {
                throw new Exception();
            }
            return reportcardsQueryVos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询患者报告卡失败:" + errMsg);
        }

    }

    @Override
    public List<MoveReport> moverecard(PatRptCardListinfo patRptCardListinfo) {
        try {
            patRptCardListinfo.setStartNum(patRptCardListinfo.getLimit()*(patRptCardListinfo.getPage()-1));
            return patRptCardListMapper.moverecard(patRptCardListinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询报告卡流转失败:");
        }
    }

    @Override
    public Integer moverecardCount(PatRptCardListinfo patRptCardListinfo) {
        try {
            return patRptCardListMapper.moverecardCount(patRptCardListinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询报告卡流转总条数失败:");
        }
    }

    @Override
    public ViewPat viewPat(PatRptCardListinfo patRptCardListinfo) {
        String errMsg = "";
        try {
            ViewPat viewInPat = patRptCardListMapper.viewPat(patRptCardListinfo);
            errMsg = patRptCardListinfo.getErrMsg();
            if (!"".equals(errMsg) && errMsg != null) {
                throw new Exception();
            }
            viewInPat.setMedList(patRptCardListMapper.patRptCardMedList(patRptCardListinfo));
            return viewInPat;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询患者报告卡详情失败:" + errMsg);
        }
    }


}
