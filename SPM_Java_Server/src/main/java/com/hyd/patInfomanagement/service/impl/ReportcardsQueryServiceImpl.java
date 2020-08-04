package com.hyd.patInfomanagement.service.impl;

import com.hyd.patInfomanagement.info.ReportcardsQueryinfo;
import com.hyd.patInfomanagement.mapper.ReportcardsQueryMapper;
import com.hyd.patInfomanagement.service.ReportcardsQueryService;
import com.hyd.patInfomanagement.vo.*;
import com.hyd.system.exceptclass.SqlException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReportcardsQueryServiceImpl  implements ReportcardsQueryService  {

    @Autowired
    ReportcardsQueryMapper reportcardsQueryMapper;




//    @Override
//    public List<Region> initProvince(ReportcardsQueryinfo reportcardsQueryinfo) {
//        try {
//            if ("Province".equals(reportcardsQueryinfo.getLevel())) {
//                if ("市级共享".equals(reportcardsQueryinfo.getExtInfoObj().getShareLev())) {
//                    reportcardsQueryinfo.setExpcd(reportcardsQueryinfo.getExtInfoObj().getDepCd());
//                }
//                reportcardsQueryinfo.setLevel("RegLevel002");
//            } else if ("City".equals(reportcardsQueryinfo.getLevel())) {
//                if ((!"市级共享".equals(reportcardsQueryinfo.getExtInfoObj().getShareLev()))&&(!"省级共享".equals(reportcardsQueryinfo.getExtInfoObj().getShareLev()))) {
//                    reportcardsQueryinfo.setExpcd(reportcardsQueryinfo.getExtInfoObj().getDepCd());
//                }
//                reportcardsQueryinfo.setLevel("RegLevel003");
//            }
//            return reportcardsQueryMapper.initProvince(reportcardsQueryinfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("查询地区失败");
//        }
//    }
//
//    @Override
//    public List<Region> initAllSelect(ExtInfoObj extInfoObj) {
//        try {
//            ArrayList<Region> regions = new ArrayList<>();
//            if ("市级共享".equals(extInfoObj.getShareLev())) {
//                extInfoObj.setExpcd("RegLevel001");
//                Region parentZone = reportcardsQueryMapper.getParentZone(extInfoObj);
//                regions.add(parentZone);
//            }else {
//                extInfoObj.setExpcd("RegLevel002");
//                Region parentZone = reportcardsQueryMapper.getParentZone(extInfoObj);
//                regions.add(parentZone);
//                extInfoObj.setDepCd(parentZone.getCode());
//                extInfoObj.setExpcd("RegLevel001");
//                Region parentZone1 = reportcardsQueryMapper.getParentZone(extInfoObj);
//                regions.add(parentZone1);
//            }
//            return regions;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("查询地区失败");
//        }
//    }

//    @Override
//    public List<Organ> initOrgan(ReportcardsQueryinfo reportcardsQueryinfo) {
//        try {
//            int i = Integer.parseInt(reportcardsQueryinfo.getCode());
//            if (i % 1000000 == 0) {
//                reportcardsQueryinfo.setCode(String.valueOf(i / 1000000));
//            } else if (i % 10000 == 0) {
//                reportcardsQueryinfo.setCode(String.valueOf(i / 10000));
//            } else if (i % 100 == 0) {
//                reportcardsQueryinfo.setCode(String.valueOf(i / 100));
//            }
//            return reportcardsQueryMapper.initOrgan(reportcardsQueryinfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("查询机构失败");
//        }
//    }
   

    @Override
    public List<MoveReport> moverecard(ReportcardsQueryinfo reportcardsQueryinfo) {
        try {
            reportcardsQueryinfo.setStartNum(reportcardsQueryinfo.getLimit()*(reportcardsQueryinfo.getPage()-1));
            return reportcardsQueryMapper.moverecard(reportcardsQueryinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询报告卡流转失败:");
        }
    }

    @Override
    public Integer moverecardCount(ReportcardsQueryinfo reportcardsQueryinfo) {
        try {
            return reportcardsQueryMapper.moverecardCount(reportcardsQueryinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询报告卡流转总条数失败:");
        }
    }

    @Override
    public List<ReportcardsQueryVo> reportcardList(ReportcardsQueryinfo reportcardsQueryinfo) {
        String errMsg = "";
        try {
            reportcardsQueryinfo.setStartNum((reportcardsQueryinfo.getPage() - 1) * reportcardsQueryinfo.getLimit());
            List<ReportcardsQueryVo> reportcardsQueryVos = reportcardsQueryMapper.reportcardList(reportcardsQueryinfo);
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
    public ViewPat viewPat(ReportcardsQueryinfo reportcardsQueryinfo) {
        String errMsg = "";
        try {
            ViewPat viewInPat = reportcardsQueryMapper.viewPat(reportcardsQueryinfo);
            errMsg = reportcardsQueryinfo.getErrMsg();
            if (!"".equals(errMsg) && errMsg != null) {
                throw new Exception();
            }
            viewInPat.setMedList(reportcardsQueryMapper.patRptCardMedList(reportcardsQueryinfo));
            return viewInPat;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询患者报告卡详情失败:" + errMsg);
        }
    }


}
