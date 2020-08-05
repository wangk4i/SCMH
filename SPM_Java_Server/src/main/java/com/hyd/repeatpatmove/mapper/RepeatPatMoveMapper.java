package com.hyd.repeatpatmove.mapper;

import com.hyd.repeatpatmove.info.RepeatPatApplyInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/3 17:27
 */
@Mapper
public interface RepeatPatMoveMapper {

    // 查询患者信息
    @Select("select * from SPM_SPMPatInfoMana where FIELDPK =#{Pk}")
    Map<String,String> queryBasicInfoByPk(String Pk);

    // 查询流转信息
    @Select("select * from SPM_RepeatMove where IDNum =#{idcode}")
    Map<String,String> queryRepeatPatInfoByIDcard(String idcode);

    // 查询未完结流转信息
    @Select("select * from SPM_SPMPatMoveOut where PatInfoCd=#{Pk} and State=1 ")//todo 流转状态 未完结
    List<Map<String,String>> queryMoveOutInfoByPk(String Pk);

    // 回写状态
    @Update("update SPM_RepeatMove set App")
    void updateRepeatPatToDb(@Param("RepeatPat") RepeatPatApplyInfo input);

    @Insert("insert into SPM_RepeatMove (Cd.Nam,PatLeftRptCd,ApplyOrg,MoveOutCd,PatInfoCd,ApplyTime,IDNum,MoveState) " +
            "values(#{RepeatPat.Cd},#{RepeatPat.Nam},#{RepeatPat.PatLeftRptCd},#{Repeat.ApplyOrg},#{Repeat.MoveOutCd},#{Repeat.PatInfoCd},#{Repeat.ApplyTime},#{Repeat.IDNum},#{Repeat.MoveState})")
    void insertRepeatPatMoveToDb(@Param("RepeatPat") RepeatPatApplyInfo input);

    @Select("select * from SPM_SPMPatInfoMana where IDNum=#{idcode} and State=1")
    Map<String, String> queryPatInfoByIdcode(String idcode);
}
