package com.hyd.usercontrol.service;


import com.hyd.system.factory.ResultFactory;
import com.hyd.usercontrol.info.HospitalAppinfo;
import com.hyd.usercontrol.vo.Hospital;
import com.hyd.usercontrol.vo.HospitalSing;
import com.hyd.usercontrol.vo.Organ;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HospitalAppService {

    //判断是否开启App权限
    Boolean isZb1AppAuthed(HospitalAppinfo hospitalAppinfo);

    //基层直报列表
    List<Organ> hospitalZbList(HospitalAppinfo hospitalAppinfo);

    //医院列表
    List<Organ> hospitalYYList();

    //医院签约列表
    List<HospitalSing> hospitalSingList(HospitalAppinfo hospitalAppinfo);


    //修改签约
    ResultFactory hospitalUpdate(HospitalSing hospitalSing);

    //查询签约
    List<Hospital> hospitalSingByCd(HospitalAppinfo hospitalAppinfo);

    //删除签约
    ResultFactory hospitaldel(HospitalSing hospitalSing);
}
