package com.hyd.batchprocess.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * Created by xieshuai on 2020/6/9 16:34
 */

@Mapper
public interface BasicInfoMapper {

    @Select("select * from V_Center2020_BasicInfo where LocalPatID = #{cd} and State=1 ")
    Map<String, Object> queryBasicInfoViewByCd(String cd);

    @Select("select * from V_Center2020_BasicInfo where LocalPatID = #{cd} and DelStatus ='DelLogo002' ")
    Map<String, Object> queryBasicInfoViewOfDelete(String cd);

    @Select("select * from V_Center2020_BasicInfo where LocalPatID = #{cd} and DelStatus!='DelLogo001'")
    Map<String, Object> queryBasicInfoViewOfUndelete(String cd);

    @Select("select * from V_Center2020_BasicInfo where LocalPatID = #{cd} and DeathDate is not null ")
    Map<String, Object> queryBasicInfoViewOfTrundeath(String cd);

    @Update("update SPM_SPMPatInfoMana set SyncStatus=0 where Cd=#{cd}")
    void setSyncStatus(String cd);



}



