package com.hyd.selectquery.mapper;


import com.hyd.selectquery.info.Threelevelinfo;
import com.hyd.selectquery.vo.Organ;
import com.hyd.selectquery.vo.Region;
import com.hyd.system.info.ExtInfoObj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ThreelevelMapper {

    @Select("<script> select Cd Code,Nam Name from SPM_SPMZone where    State='1' " +

            "<choose> " +
            "<when test='Threelevelinfo.expcd!=\"\" and Threelevelinfo.expcd!=null ' >   and Code=#{Threelevelinfo.extInfoObj.depCd} </when>" +
            "<otherwise> and ParCd=#{Threelevelinfo.code} and LevCd=#{Threelevelinfo.level}  </otherwise> " +
            "</choose>" +

            "</script>")
    List<Region> initProvince(@Param("Threelevelinfo") Threelevelinfo threelevelinfo);


    @Select(" select Cd Code,Nam Name from  SPM_SPMZone  where Code=(select ParCd from SPM_SPMZone where Cd=#{ExtInfoObj.depCd}) AND LevCd=#{ExtInfoObj.expcd} ")
    Region getParentZone(@Param("ExtInfoObj") ExtInfoObj extInfoObj);


    //获取机构
    @Select("<script>" +

            "select Cd,Nam from SPM_SPMOrgan" +

            " where  ZoneCd like CONCAT(#{Threelevelinfo.code},\'%\')  and IsVillage is null and State='1' " +

            "<choose> " +
            " <when test='Threelevelinfo.level==\"Province\" '> and LevCd='InsLevel002'  </when>" +
            " <when test='Threelevelinfo.level==\"City\" '> and LevCd='InsLevel003'  </when>" +
            "</choose>" +

            "</script>")
    List<Organ> initOrgan(@Param("Threelevelinfo") Threelevelinfo threelevelinfo);
}
