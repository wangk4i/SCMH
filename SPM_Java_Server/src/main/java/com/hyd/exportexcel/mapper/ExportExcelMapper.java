package com.hyd.exportexcel.mapper;

import com.hyd.exportexcel.info.Annualorganauditinfo;
import com.hyd.exportexcel.vo.AnnualorganauditVo;
import com.hyd.orgmaintain.info.QueryOrganinfo;
import com.hyd.orgmaintain.vo.CityVo;
import com.hyd.orgmaintain.vo.OrgUser;
import com.hyd.orgmaintain.vo.Organ;
import com.hyd.orgmaintain.vo.ProvinceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;


@Mapper
public interface ExportExcelMapper {
    @Select("{call WorkReporService_QueryAnnualOrganAuditBJListExport(" +
            "#{Annualorganauditinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{Annualorganauditinfo.zoneCd,mode=IN,jdbcType=VARCHAR}," +
            "#{Annualorganauditinfo.yearDateStart,mode=IN,jdbcType=VARCHAR}," +
            "#{Annualorganauditinfo.yearDateEnd,mode=IN,jdbcType=VARCHAR}," +
            "#{Annualorganauditinfo.organCd,mode=IN,jdbcType=VARCHAR}"+
            ")}")
    @Options(statementType= StatementType.CALLABLE)
    List<AnnualorganauditVo> annualorganauditList(@Param("Annualorganauditinfo") Annualorganauditinfo annualorganauditinfo);
}
