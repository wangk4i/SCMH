package com.hyd.gwinterfaceserver.patinfo.service;


import com.hyd.gwinterfaceserver.patinfo.info.ApplyRepeatInfoRequest;
import com.hyd.gwinterfaceserver.patinfo.info.RepeatCallBackApplyRequest;
import com.hyd.gwinterfaceserver.patinfo.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatinfoInAndOutService2Service {



    basicInfo GetOrgan(String orgcode);

    basicInfo GetZone(String orgcode);

    //检验迁入地区是否是区县级
    Boolean getInZoneLv(String inZoneCd);

    //获取患者是否存在
    Boolean getPatInfoIsA(String PatCode);

    //获取机构国网编号
    String getOrgCode(String inOrgCd);

    //获取患者信息 是否同步国网
    PatInfoIsSyn getPatInfoIsSync(String cd);

    //获取流转信息
    MoveOutInfo getMoveOutInfo(String moveCode);

    //获取患者信息
    PatInfo2 getPatInfo(String patInfoCd);

    //查询该患者是否有流转记录未终结
    Boolean getRepeattMoveOutInfoByIDCode(String idCode);

    //获取患者信息
    PatInfoMana getPatInfoByIDCode(String idCode);

    //查询该患者是否有流转记录未终结
    Boolean getMoveOutInfoByPatInfoCd(String cd);

    //重复患者收回 回写
    void CallBackApply(RepeatCallBackApplyRequest repeatCallBackApplyRequest);

    //获取重复患者申请记录
    QueryRepeatInfoApplyResponse GetRepeatInfo(String repeatInfoId);

    //获取患者信息
    PatInfoMana getPatInfoByFIELDPK(String dischargeInformationId);

    //重复患者通过回写
    void ThroughApply(ApplyRepeatInfoRequest applyRepeatInfoRequest, MoveOutInfo moveInfo);

    //重复患者驳回回写
    void RejectedApply(ApplyRepeatInfoRequest applyRepeatInfoRequest);

    //随访信息
    List<Followup2> getPatFollow(String patInfoCd);

    //迁出患者信息(省网回写)
    void PatInfoMoveOut(MoveOutInfo moveOutInfo, ExtInfo extInfoObj);

    //迁出患者信息被拒后再次迁出(省网回写)
    void UpdatePatInfoOutRefuse(MoveOutInfo moveOutInfo, ExtInfo extInfoObj);
}
