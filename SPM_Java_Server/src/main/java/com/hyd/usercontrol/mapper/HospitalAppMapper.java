package com.hyd.usercontrol.mapper;


import com.hyd.system.factory.ResultFactory;
import com.hyd.usercontrol.info.HospitalAppinfo;
import com.hyd.usercontrol.vo.Hospital;
import com.hyd.usercontrol.vo.HospitalSing;
import com.hyd.usercontrol.vo.Organ;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface HospitalAppMapper {


    @Select("select count(*) from SPM_SPMUser where Cd=#{HospitalAppinfo.pcd} and AppAuth='1' ")
    Long isZb1AppAuthed(@Param("HospitalAppinfo") HospitalAppinfo hospitalAppinfo);

    //基层直报列表
    @Select("select Cd,Nam from SPM_SPMOrgan where state='1' and OrgCode like CONCAT(#{HospitalAppinfo.zoneCd},\'%\')  and IfPerform='1' and IfDirect='1'")
    List<Organ> hospitalZbList(@Param("HospitalAppinfo") HospitalAppinfo hospitalAppinfo);

    //医院列表
    @Select(" select Cd,Nam from SPM_SPMOrgan where IsHos='1' ")
    List<Organ> hospitalYYList();

    //医院签约列表
    @Select("select distinct(a.OrgCd) signorgCd,a.OrgNam signorgNam,a.CreaTime signTime from " +
            "App_HosSign a," +
            "SPM_SPMOrgan b " +
            "where a.OrgCd = b.Cd and b.OrgCode like CONCAT(#{HospitalAppinfo.zoneCd},\'%\') order by a.CreaTime desc,a.OrgCd")
    List<HospitalSing> hospitalSingList(@Param("HospitalAppinfo") HospitalAppinfo hospitalAppinfo);


    @Select(" select HosCd hospitalCd,HosNam hospitalNam from App_HosSign where OrgCd=#{orgCd} ")
    List<Hospital> hospitalSing(String orgCd);

    @Select("<script> insert into App_HosSign(OrgCd,OrgNam,HosCd,HosNam,CreaTime,State) values" +
            " <foreach item='item'  collection='HospitalSing.hospital'  separator=',' > "+
            " <if test='item.hospitalCd!=\"\" and item.hospitalCd!=null '> (#{HospitalSing.signorgCd},#{HospitalSing.signorgNam},#{item.hospitalCd},#{item.hospitalNam},convert(varchar,getdate(),120),1)  </if>"+
            " </foreach> "+
            "</script>")
    void hospitalAdd(@Param("HospitalSing")HospitalSing hospitalSing);


    @Select("delete from App_HosSign where OrgCd=#{HospitalSing.signorgCd} ")
    void hospitaldel(@Param("HospitalSing")HospitalSing hospitalSing);


    //签约是否存在
    @Select(" select count(1) from App_HosSign where OrgCd=#{signorgCd} ")
    Long ishospitalByCd(String signorgCd);
}
