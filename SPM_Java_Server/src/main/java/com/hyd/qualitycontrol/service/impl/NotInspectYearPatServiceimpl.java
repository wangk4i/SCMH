package com.hyd.qualitycontrol.service.impl;


import com.hyd.qualitycontrol.info.NotInspectYearPatinfo;
import com.hyd.qualitycontrol.mapper.NotInspectYearPatMapper;
import com.hyd.qualitycontrol.service.NotInspectYearPatService;
import com.hyd.qualitycontrol.vo.NotInspectYearPat;
import com.hyd.qualitycontrol.vo.Organ;
import com.hyd.qualitycontrol.vo.Region;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.info.ExtInfoObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotInspectYearPatServiceimpl implements NotInspectYearPatService {

    @Autowired
    NotInspectYearPatMapper notInspectYearPatMapper;

//    @Override
//    public List<Region> initProvince(NotInspectYearPatinfo notInspectYearPatinfo) {
//        try {
//            if ("Province".equals(notInspectYearPatinfo.getLevel())) {
//                if ("市级共享".equals(notInspectYearPatinfo.getExtInfoObj().getShareLev())) {
//                    notInspectYearPatinfo.setExpcd(notInspectYearPatinfo.getExtInfoObj().getDepCd());
//                }
//                notInspectYearPatinfo.setLevel("RegLevel002");
//            } else if ("City".equals(notInspectYearPatinfo.getLevel())) {
//                if ((!"市级共享".equals(notInspectYearPatinfo.getExtInfoObj().getShareLev()))&&(!"省级共享".equals(notInspectYearPatinfo.getExtInfoObj().getShareLev()))) {
//                    notInspectYearPatinfo.setExpcd(notInspectYearPatinfo.getExtInfoObj().getDepCd());
//                }
//                notInspectYearPatinfo.setLevel("RegLevel003");
//            }
//            return notInspectYearPatMapper.initProvince(notInspectYearPatinfo);
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
//                Region parentZone = notInspectYearPatMapper.getParentZone(extInfoObj);
//                regions.add(parentZone);
//            }else {
//                extInfoObj.setExpcd("RegLevel002");
//                Region parentZone = notInspectYearPatMapper.getParentZone(extInfoObj);
//                regions.add(parentZone);
//                extInfoObj.setDepCd(parentZone.getCode());
//                extInfoObj.setExpcd("RegLevel001");
//                Region parentZone1 = notInspectYearPatMapper.getParentZone(extInfoObj);
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
//    public List<Organ> initOrgan(NotInspectYearPatinfo notInspectYearPatinfo) {
//        try {
//            int i = Integer.parseInt(notInspectYearPatinfo.getCode());
//            if (i % 1000000 == 0) {
//                notInspectYearPatinfo.setCode(String.valueOf(i / 1000000));
//            } else if (i % 10000 == 0) {
//                notInspectYearPatinfo.setCode(String.valueOf(i / 10000));
//            } else if (i % 100 == 0) {
//                notInspectYearPatinfo.setCode(String.valueOf(i / 100));
//            }
//            return notInspectYearPatMapper.initOrgan(notInspectYearPatinfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("查询机构失败");
//        }
//    }


    @Override
    public List<NotInspectYearPat> queryByUserList(NotInspectYearPatinfo notInspectYearPatinfo) {
        String errMsg="";
        try {
            notInspectYearPatinfo.setStartNum((notInspectYearPatinfo.getPage()-1)*notInspectYearPatinfo.getLimit());
            notInspectYearPatinfo.setEndNum(notInspectYearPatinfo.getLimit());
            List<NotInspectYearPat> communityOutPats = notInspectYearPatMapper.queryByUserList(notInspectYearPatinfo);
            errMsg = notInspectYearPatinfo.getErrMsg();
            if(!"".equals(errMsg)&&errMsg!=null){
                throw new Exception();
            }
            return communityOutPats;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询列表失败:"+errMsg);
        }
    }


}
