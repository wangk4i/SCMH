package com.hyd.subordtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface DictInfoMapper {

    @Select("select * from SPM_SPMDict where Cd=#{cd}")
    Map<String,Object> queryDictForNam(String cd);
}
