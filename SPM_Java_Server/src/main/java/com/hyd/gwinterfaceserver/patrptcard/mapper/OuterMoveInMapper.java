package com.hyd.gwinterfaceserver.patrptcard.mapper;


import com.hyd.gwinterfaceserver.patrptcard.vo.PatInfo;
import com.hyd.gwinterfaceserver.patrptcard.vo.ExtInfo;
import com.hyd.gwinterfaceserver.patrptcard.vo.MoveOutInfo;
import com.hyd.gwinterfaceserver.patrptcard.vo.MoveOutReprotInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OuterMoveInMapper {

    @Select("select FIELDPK from SPM_SPMPatMoveOut(nolock) where Cd=#{cd}")
    String getMoveGWFIELDPK(String cd);

    @Select("select * from SPM_SPMPatMoveOut(nolock) where Cd= #{cd} ")
    MoveOutInfo getMoveOutInfo(String cd);


    //获取国网编号
    @Select("select OrgCode from SPM_SPMOrgan(nolock) where Cd = #{inOrgCd}  ")
    String getOrgCode(String inOrgCd);


    //迁出患者报告卡（省网回写）本级1
    @Select("{call MoveInOutService2_MoveOutBJ1(" +
            "#{ExtInfo.from,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.to,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.state,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{MoveOutReprotInfo.totalNum,mode=OUT,jdbcType=INTEGER}," +
            "#{MoveOutReprotInfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{MoveOutReprotInfo.cd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.zoneCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.organCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.moveOutCause,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.fIELDPK,mode=IN,jdbcType=VARCHAR}"+
            ")}")
    void MoveOutBJ1(@Param("MoveOutReprotInfo") MoveOutReprotInfo moveOutReprotInfo,@Param("ExtInfo") ExtInfo extInfoObj);


    @Select("select * from V_Center_BasicInfo2(nolock) where ID=#{rptCardID}")
    PatInfo getPatInfo(String rptCardID);
}
