package com.hyd.repeatpatmove.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    // @Update("update ")


}
