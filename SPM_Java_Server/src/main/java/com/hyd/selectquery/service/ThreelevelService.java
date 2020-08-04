package com.hyd.selectquery.service;

import com.hyd.selectquery.vo.Organ;
import com.hyd.selectquery.vo.Region;
import com.hyd.system.info.ExtInfoObj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThreelevelService<T> {

    //获取下级地区
    List<Region> initProvince(T t);
    //初始化下拉
    List<Region> initAllSelect(ExtInfoObj extInfoObj);
    //获取机构
    List<Organ> initOrgan(T t);

}

