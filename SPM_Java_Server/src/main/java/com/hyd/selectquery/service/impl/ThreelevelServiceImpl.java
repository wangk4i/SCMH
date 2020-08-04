package com.hyd.selectquery.service.impl;

import com.hyd.selectquery.info.Threelevelinfo;
import com.hyd.selectquery.mapper.ThreelevelMapper;
import com.hyd.selectquery.service.ThreelevelService;
import com.hyd.selectquery.vo.Organ;
import com.hyd.selectquery.vo.Region;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.info.ExtInfoObj;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThreelevelServiceImpl<T> implements ThreelevelService<T> {

    @Autowired
    ThreelevelMapper threelevelMapper;

    @Override
    public List<Region> initProvince(T threelevelinfos) {
        try {
            Threelevelinfo threelevelinfo = new Threelevelinfo();
            BeanUtils.copyProperties(threelevelinfos,threelevelinfo);
            if ("Province".equals(threelevelinfo.getLevel())) {
                if ("市级共享".equals(threelevelinfo.getExtInfoObj().getShareLev())) {
                    threelevelinfo.setExpcd(threelevelinfo.getExtInfoObj().getDepCd());
                }
                threelevelinfo.setLevel("RegLevel002");
            } else if ("City".equals(threelevelinfo.getLevel())) {
                if ((!"市级共享".equals(threelevelinfo.getExtInfoObj().getShareLev()))&&(!"省级共享".equals(threelevelinfo.getExtInfoObj().getShareLev()))) {
                    threelevelinfo.setExpcd(threelevelinfo.getExtInfoObj().getDepCd());
                }
                threelevelinfo.setLevel("RegLevel003");
            }
            return threelevelMapper.initProvince(threelevelinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询地区失败");
        }
    }



    @Override
    public List<Region> initAllSelect(ExtInfoObj extInfoObj) {
        try {
            ArrayList<Region> regions = new ArrayList<>();
            if ("市级共享".equals(extInfoObj.getShareLev())) {
                extInfoObj.setExpcd("RegLevel001");
                Region parentZone = threelevelMapper.getParentZone(extInfoObj);
                regions.add(parentZone);
            }else {
                extInfoObj.setExpcd("RegLevel002");
                Region parentZone = threelevelMapper.getParentZone(extInfoObj);
                regions.add(parentZone);
                extInfoObj.setDepCd(parentZone.getCode());
                extInfoObj.setExpcd("RegLevel001");
                Region parentZone1 = threelevelMapper.getParentZone(extInfoObj);
                regions.add(parentZone1);
            }
            return regions;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询地区失败");
        }
    }

    @Override
    public List<Organ> initOrgan(T threelevelinfos) {
        try {
            Threelevelinfo threelevelinfo = new Threelevelinfo();
            BeanUtils.copyProperties(threelevelinfos,threelevelinfo);
            int i = Integer.parseInt(threelevelinfo.getCode());
            if (i % 1000000 == 0) {
                threelevelinfo.setCode(String.valueOf(i / 1000000));
            } else if (i % 10000 == 0) {
                threelevelinfo.setCode(String.valueOf(i / 10000));
            } else if (i % 100 == 0) {
                threelevelinfo.setCode(String.valueOf(i / 100));
            }
            return threelevelMapper.initOrgan(threelevelinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询机构失败");
        }
    }

}
