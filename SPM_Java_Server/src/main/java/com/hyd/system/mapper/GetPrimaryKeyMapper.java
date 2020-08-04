package com.hyd.system.mapper;

import com.hyd.system.info.GetCdInfo;
import com.hyd.system.vo.DictVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xieshuai on 2020/4/8 10:56
 */


@Mapper
public interface GetPrimaryKeyMapper {

    //获取Cd
    void getDataCd(GetCdInfo info);

    //查询数据字典
    @Select(" select DictTpCd,STUFF((select ',{Cd:\"' + A.Cd,'\",Nam:\"'+A.Nam,'\",DictTpNam:\"'+A.DictTpNam,'\",DictCode:\"'+A.DictCode,'\"}' from " +
            "SPM_SPMDict(nolock) A where A.DictTpCd=B.DictTpCd for XML PATH('')),1,1,'') AS DictTpToJSON from SPM_SPMDict B where State='1' group by DictTpCd ")
    List<DictVo> dictionaryinfo();


}
