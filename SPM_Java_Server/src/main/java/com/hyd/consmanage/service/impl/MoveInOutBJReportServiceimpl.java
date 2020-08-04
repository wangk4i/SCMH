package com.hyd.consmanage.service.impl;


import com.hyd.consmanage.info.QueryCommunityOutPatinfo;
import com.hyd.consmanage.info.QueryMoveInOutBJReportinfo;
import com.hyd.consmanage.mapper.CommunityOutPatMapper;
import com.hyd.consmanage.mapper.MoveInOutBJReportMapper;
import com.hyd.consmanage.service.CommunityOutPatService;
import com.hyd.consmanage.service.MoveInOutBJReportService;
import com.hyd.consmanage.vo.*;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.info.ExtInfoObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoveInOutBJReportServiceimpl implements MoveInOutBJReportService {

    @Autowired
    MoveInOutBJReportMapper moveInOutBJReportMapper;

//    @Override
//    public List<Region> initProvince(QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo) {
//        try {
//            if ("Province".equals(queryMoveInOutBJReportinfo.getLevel())) {
//                if ("市级共享".equals(queryMoveInOutBJReportinfo.getExtInfoObj().getShareLev())) {
//                    queryMoveInOutBJReportinfo.setExpcd(queryMoveInOutBJReportinfo.getExtInfoObj().getDepCd());
//                }
//                queryMoveInOutBJReportinfo.setLevel("RegLevel002");
//            } else if ("City".equals(queryMoveInOutBJReportinfo.getLevel())) {
//                if ((!"市级共享".equals(queryMoveInOutBJReportinfo.getExtInfoObj().getShareLev()))&&(!"省级共享".equals(queryMoveInOutBJReportinfo.getExtInfoObj().getShareLev()))) {
//                    queryMoveInOutBJReportinfo.setExpcd(queryMoveInOutBJReportinfo.getExtInfoObj().getDepCd());
//                }
//                queryMoveInOutBJReportinfo.setLevel("RegLevel003");
//            }
//            return moveInOutBJReportMapper.initProvince(queryMoveInOutBJReportinfo);
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
//                Region parentZone = moveInOutBJReportMapper.getParentZone(extInfoObj);
//                regions.add(parentZone);
//            }else {
//                extInfoObj.setExpcd("RegLevel002");
//                Region parentZone = moveInOutBJReportMapper.getParentZone(extInfoObj);
//                regions.add(parentZone);
//                extInfoObj.setDepCd(parentZone.getCode());
//                extInfoObj.setExpcd("RegLevel001");
//                Region parentZone1 = moveInOutBJReportMapper.getParentZone(extInfoObj);
//                regions.add(parentZone1);
//            }
//            return regions;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("查询地区失败");
//        }
//    }
//
//    @Override
//    public List<Organ> initOrgan(QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo) {
//        try {
//            int i = Integer.parseInt(queryMoveInOutBJReportinfo.getCode());
//            if (i % 1000000 == 0) {
//                queryMoveInOutBJReportinfo.setCode(String.valueOf(i / 1000000));
//            } else if (i % 10000 == 0) {
//                queryMoveInOutBJReportinfo.setCode(String.valueOf(i / 10000));
//            } else if (i % 100 == 0) {
//                queryMoveInOutBJReportinfo.setCode(String.valueOf(i / 100));
//            }
//            return moveInOutBJReportMapper.initOrgan(queryMoveInOutBJReportinfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("查询机构失败");
//        }
//    }

    @Override
    public List<MoveInOutBJReport> queryByInUserList(QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo) {
        String errMsg="";
        try {
            queryMoveInOutBJReportinfo.setStartNum((queryMoveInOutBJReportinfo.getPage()-1)*queryMoveInOutBJReportinfo.getLimit()+1);
            queryMoveInOutBJReportinfo.setEndNum(queryMoveInOutBJReportinfo.getPage()*queryMoveInOutBJReportinfo.getLimit());
            List<MoveInOutBJReport> moveInOutBJReport = moveInOutBJReportMapper.queryByInUserList(queryMoveInOutBJReportinfo);
            errMsg = queryMoveInOutBJReportinfo.getErrMsg();
            if(!"".equals(errMsg)&&errMsg!=null){
                throw new Exception();
            }
            return moveInOutBJReport;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询报告卡迁入列表失败:"+errMsg);
        }
    }



    @Override
    public List<MoveInOutBJReport> queryByOutUserList(QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo) {
        String errMsg="";
        try {
            queryMoveInOutBJReportinfo.setStartNum((queryMoveInOutBJReportinfo.getPage()-1)*queryMoveInOutBJReportinfo.getLimit()+1);
            queryMoveInOutBJReportinfo.setEndNum(queryMoveInOutBJReportinfo.getPage()*queryMoveInOutBJReportinfo.getLimit());
            List<MoveInOutBJReport> moveInOutBJReport = moveInOutBJReportMapper.queryByOutUserList(queryMoveInOutBJReportinfo);
            errMsg = queryMoveInOutBJReportinfo.getErrMsg();
            if(!"".equals(errMsg)&&errMsg!=null){
                throw new Exception();
            }
            return moveInOutBJReport;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询报告卡迁出列表失败:"+errMsg);
        }
    }

    @Override
    public ViewInPat viewInPat(QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo) {
        String errMsg = "";
        try {
            ViewInPat viewInPat = moveInOutBJReportMapper.viewInPat(queryMoveInOutBJReportinfo);
            errMsg = queryMoveInOutBJReportinfo.getErrMsg();
            if (!"".equals(errMsg) && errMsg != null) {
                throw new Exception();
            }
            viewInPat.setMedList(moveInOutBJReportMapper.patRptCardMedList(queryMoveInOutBJReportinfo));
            return viewInPat;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询患者报告卡失败:" + errMsg);
        }
    }


    @Override
    public ViewOutPat viewOutPat(QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo) {
        String errMsg = "";
        try {
            ViewOutPat viewOutPat = moveInOutBJReportMapper.viewOutPat(queryMoveInOutBJReportinfo);
            errMsg = queryMoveInOutBJReportinfo.getErrMsg();
            if (!"".equals(errMsg) && errMsg != null) {
                throw new Exception();
            }
            viewOutPat.setMedList(moveInOutBJReportMapper.patRptCardMedList(queryMoveInOutBJReportinfo));
            return viewOutPat;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询患者报告卡失败:" + errMsg);
        }
    }
}
