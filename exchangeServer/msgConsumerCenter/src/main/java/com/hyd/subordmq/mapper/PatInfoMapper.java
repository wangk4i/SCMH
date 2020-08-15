package com.hyd.subordmq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * Created by xieshuai on 2020/6/9 16:34
 */

@Mapper
public interface PatInfoMapper {

    @Select("select * from V_Center2020_BasicInfo where LocalPatID = #{cd} and State=1 ")
    Map<String, Object> buildPatInfo(String cd);

    @Select("select * from V_Center2020_BasicInfo where LocalPatID = #{cd} and DelStatus ='DelLogo002' ")
    Map<String, Object> deletePatInfo(String cd);

    @Select("select * from V_Center2020_BasicInfo where LocalPatID = #{cd} and DelStatus!='DelLogo001'")
    Map<String, Object> restorePatInfo(String cd);

    @Select("select * from V_Center2020_BasicInfo where LocalPatID = #{cd} and DeathDate is not null ")
    Map<String, Object> turnDeathPatInfo(String cd);

    @Update("update SPM_SPMPatInfoMana set SyncStatus=0 where Cd=#{cd}")
    void lockSyncFlag(String cd);

}



