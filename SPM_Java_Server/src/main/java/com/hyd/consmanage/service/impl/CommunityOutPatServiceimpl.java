package com.hyd.consmanage.service.impl;


import com.hyd.consmanage.info.QueryCommunityOutPatinfo;
import com.hyd.consmanage.mapper.CommunityOutPatMapper;
import com.hyd.consmanage.service.CommunityOutPatService;
import com.hyd.consmanage.vo.CommunityOutPat;
import com.hyd.consmanage.vo.Organ;
import com.hyd.consmanage.vo.Region;
import com.hyd.consmanage.vo.ViewPat;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.info.ExtInfoObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityOutPatServiceimpl implements CommunityOutPatService {

    @Autowired
    CommunityOutPatMapper communityOutPatMapper;

//    @Override
//    public List<Region> initProvince(QueryCommunityOutPatinfo queryCommunityOutPatinfo) {
//        try {
//            if ("Province".equals(queryCommunityOutPatinfo.getLevel())) {
//                if ("市级共享".equals(queryCommunityOutPatinfo.getExtInfoObj().getShareLev())) {
//                    queryCommunityOutPatinfo.setExpcd(queryCommunityOutPatinfo.getExtInfoObj().getDepCd());
//                }
//                queryCommunityOutPatinfo.setLevel("RegLevel002");
//            } else if ("City".equals(queryCommunityOutPatinfo.getLevel())) {
//                if ((!"市级共享".equals(queryCommunityOutPatinfo.getExtInfoObj().getShareLev()))&&(!"省级共享".equals(queryCommunityOutPatinfo.getExtInfoObj().getShareLev()))) {
//                    queryCommunityOutPatinfo.setExpcd(queryCommunityOutPatinfo.getExtInfoObj().getDepCd());
//                }
//                queryCommunityOutPatinfo.setLevel("RegLevel003");
//            }
//            return communityOutPatMapper.initProvince(queryCommunityOutPatinfo);
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
//                Region parentZone = communityOutPatMapper.getParentZone(extInfoObj);
//                regions.add(parentZone);
//            }else {
//                extInfoObj.setExpcd("RegLevel002");
//                Region parentZone = communityOutPatMapper.getParentZone(extInfoObj);
//                regions.add(parentZone);
//                extInfoObj.setDepCd(parentZone.getCode());
//                extInfoObj.setExpcd("RegLevel001");
//                Region parentZone1 = communityOutPatMapper.getParentZone(extInfoObj);
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
//    public List<Organ> initOrgan(QueryCommunityOutPatinfo queryCommunityOutPatinfo) {
//        try {
//            int i = Integer.parseInt(queryCommunityOutPatinfo.getCode());
//            if (i % 1000000 == 0) {
//                queryCommunityOutPatinfo.setCode(String.valueOf(i / 1000000));
//            } else if (i % 10000 == 0) {
//                queryCommunityOutPatinfo.setCode(String.valueOf(i / 10000));
//            } else if (i % 100 == 0) {
//                queryCommunityOutPatinfo.setCode(String.valueOf(i / 100));
//            }
//            return communityOutPatMapper.initOrgan(queryCommunityOutPatinfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("查询机构失败");
//        }
//    }

    @Override
    public List<CommunityOutPat> queryByUserList(QueryCommunityOutPatinfo queryCommunityOutPatinfo) {
        String errMsg="";
        try {
            int i = Integer.parseInt(queryCommunityOutPatinfo.getZoneCd());
            if (i % 1000000 == 0) {
                queryCommunityOutPatinfo.setZoneCd(String.valueOf(i / 1000000));
            } else if (i % 10000 == 0) {
                queryCommunityOutPatinfo.setZoneCd(String.valueOf(i / 10000));
            } else if (i % 100 == 0) {
                queryCommunityOutPatinfo.setZoneCd(String.valueOf(i / 100));
            }
            queryCommunityOutPatinfo.setStartNum((queryCommunityOutPatinfo.getPage()-1)*queryCommunityOutPatinfo.getLimit()+1);
            queryCommunityOutPatinfo.setEndNum(queryCommunityOutPatinfo.getPage()*queryCommunityOutPatinfo.getLimit());
            List<CommunityOutPat> communityOutPats = communityOutPatMapper.queryByUserList(queryCommunityOutPatinfo);
            errMsg = queryCommunityOutPatinfo.getErrMsg();
            if(!"".equals(errMsg)&&errMsg!=null){
                throw new Exception();
            }
            return communityOutPats;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询列表失败:"+errMsg);
        }
    }

    @Override
    public ViewPat viewOutPat(QueryCommunityOutPatinfo queryCommunityOutPatinfo) {
        String errMsg = "";
        try {
            ViewPat viewPat = communityOutPatMapper.viewOutPat(queryCommunityOutPatinfo);
            errMsg = queryCommunityOutPatinfo.getErrMsg();
            if (!"".equals(errMsg) && errMsg != null) {
                throw new Exception();
            }
            return viewPat;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询患者信息失败:" + errMsg);
        }
    }
}
