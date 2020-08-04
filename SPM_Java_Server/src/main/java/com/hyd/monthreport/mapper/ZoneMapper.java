package com.hyd.monthreport.mapper;

import com.hyd.monthreport.vo.ZoneVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xieshuai on 2020/4/8 15:13
 */

@Mapper
public interface ZoneMapper {

    @ResultType(ZoneVO.class)
    @Select("select Cd id, Nam title, ParCd parentId, LevCd lenIndex From SPM_SPMZone Where Cd = #{cd}")
    ZoneVO queryZone(@Param("cd") String cd);


    @ResultType(ZoneVO.class)
    @Select("select Cd id, Nam title, ParCd parentId, LevCd levIndex FROM SPM_SPMZone WHERE Nam not like '不详%' and ParCd = #{parCd}")
    List<ZoneVO> queryZoneByList(@Param("parCd") String parCd);


    @ResultType(ZoneVO.class)
    @Select("select Cd id, Nam title, ParCd parentId, LevCd levIndex FROM SPM_SPMZone WHERE Nam not like '不详%' and ParCd = #{cd}")
    List<ZoneVO> queryThisZOneByList(@Param("cd") String cd);


    /**
     * 查询所有地区信息 但不包含当前传入的地区编码
     * @param parCd
     * @param cd
     * @return
     */
    @ResultType(ZoneVO.class)
    @Select("select Cd id, Nam title, ParCd parentId, LevCd levIndex FROM SPM_SPMZone WHERE Nam not like '不详%' and ParCd = #{parCd} and Cd !=#{cd} ")
    List<ZoneVO> queryZoneByLists(@Param("parCd") String parCd, @Param("cd") String cd);


}
