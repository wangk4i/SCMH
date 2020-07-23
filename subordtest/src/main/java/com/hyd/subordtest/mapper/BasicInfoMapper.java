package com.hyd.subordtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by xieshuai on 2020/6/9 16:34
 */

@Mapper
public interface BasicInfoMapper {

    @Select("select top 1000 ID FROM V_Center2020_BasicInfo")
    List<String> queryTestBasicInfoView();


    @Select("select * from V_Center2020_BasicInfo where ID = #{cd} and BasicInformationId is null ")
    Map<String, Object> queryBasicInfoViewOfInsert(String cd);

    @Select("select * from V_Center2020_BasicInfo where ID = #{cd} and BasicInformationId is not null ")
    Map<String, Object> queryBasicInfoViewOfUpdate(String cd);

    @Select("select * from V_Center2020_BasicInfo where ID = #{cd} and DelStatus ='DelLogo002' ")
    Map<String, Object> queryBasicInfoViewOfDelete(String cd);

    @Select("select * from V_Center2020_BasicInfo where ID = #{cd} and DelStatus!='DelLogo001'")
    Map<String, Object> queryBasicInfoViewOfUndelete(String cd);

    @Select("select * from V_Center2020_BasicInfo where ID = #{cd} and DeathDate is not null ")
    Map<String, Object> queryBasicInfoViewOfTrundeath(String cd);





}



