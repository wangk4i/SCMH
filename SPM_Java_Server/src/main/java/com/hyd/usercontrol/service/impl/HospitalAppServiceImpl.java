package com.hyd.usercontrol.service.impl;

import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.factory.ResultFactory;
import com.hyd.usercontrol.info.HospitalAppinfo;
import com.hyd.usercontrol.mapper.HospitalAppMapper;
import com.hyd.usercontrol.service.HospitalAppService;
import com.hyd.usercontrol.vo.Hospital;
import com.hyd.usercontrol.vo.HospitalSing;
import com.hyd.usercontrol.vo.Organ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.util.IterableUtil.iterator;


@Service
@Transactional(propagation= Propagation.REQUIRED)
public class HospitalAppServiceImpl implements HospitalAppService {

    @Autowired
    private HospitalAppMapper hospitalAppMapper;


    @Override
    public Boolean isZb1AppAuthed(HospitalAppinfo hospitalAppinfo) {
        try {
            return hospitalAppMapper.isZb1AppAuthed(hospitalAppinfo) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询是否开启App失败");
        }
    }

    @Override
    public List<Organ> hospitalZbList(HospitalAppinfo hospitalAppinfo) {
        try {
            hospitalAppinfo.setZoneCd(hospitalAppinfo.getZoneCd().substring(0, 6));
            return hospitalAppMapper.hospitalZbList(hospitalAppinfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询直报列表失败");
        }
    }

    @Override
    public List<Organ> hospitalYYList() {
        try {
            return hospitalAppMapper.hospitalYYList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询医院列表失败");
        }
    }



    @Override
    public List<HospitalSing> hospitalSingList(HospitalAppinfo hospitalAppinfo) {
        try {
            hospitalAppinfo.setZoneCd(hospitalAppinfo.getZoneCd().substring(0, 6));
            List<HospitalSing> hospitalSings = hospitalAppMapper.hospitalSingList(hospitalAppinfo);
            Iterator<HospitalSing> iterator = hospitalSings.iterator();
            while (iterator.hasNext()){
                HospitalSing next = iterator.next();
                List<Hospital> hospitals = hospitalAppMapper.hospitalSing(next.getSignorgCd());
                next.setHospital(hospitals);
            }
            return hospitalSings;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询签约列表失败！");
        }
    }


    @Override
    public ResultFactory hospitalUpdate(HospitalSing hospitalSing) {
        try {
            //如果存在签约 进行修改
            if(hospitalAppMapper.ishospitalByCd(hospitalSing.getSignorgCd())>0){
                hospitalAppMapper.hospitaldel(hospitalSing);
                hospitalAppMapper.hospitalAdd(hospitalSing);
            }else {//不存在进行新增
                hospitalAppMapper.hospitalAdd(hospitalSing);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("医院签约失败");
        }
    }



    @Override
    public List<Hospital> hospitalSingByCd(HospitalAppinfo hospitalAppinfo) {
        try {
            return hospitalAppMapper.hospitalSing(hospitalAppinfo.getPcd());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询签约失败");
        }
    }

    @Override
    public ResultFactory hospitaldel(HospitalSing hospitalSing) {
        try {
            hospitalAppMapper.hospitaldel(hospitalSing);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("删除失败");
        }
    }
}
