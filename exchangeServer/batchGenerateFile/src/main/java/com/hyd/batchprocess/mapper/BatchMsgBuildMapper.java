package com.hyd.batchprocess.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BatchMsgBuildMapper {

    @Select("${sqlStr}")
    List<Map<String,Object>> queryBatchDataByConf(@Param(value = "sqlStr") String sqlStr);
}
