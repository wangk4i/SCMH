package com.hyd.gwinterfaceserver.patinfo.service.impl;


import com.hyd.gwinterfaceserver.patinfo.info.ApplyRepeatInfoRequest;
import com.hyd.gwinterfaceserver.patinfo.info.RepeatCallBackApplyRequest;
import com.hyd.gwinterfaceserver.patinfo.mapper.PatinfoInAndOutService2Mapper;
import com.hyd.gwinterfaceserver.patinfo.service.PatinfoInAndOutService2Service;
import com.hyd.gwinterfaceserver.patinfo.vo.*;
import com.hyd.system.exceptclass.SqlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class PatinfoInAndOutService2Serviceimpl implements PatinfoInAndOutService2Service {

    @Autowired
    PatinfoInAndOutService2Mapper patinfoInAndOutService2Mapper;


    @Override
    public basicInfo GetOrgan(String orgcode) {
        try {
            return patinfoInAndOutService2Mapper.GetOrgan(orgcode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询失败");
        }
    }

    @Override
    public basicInfo GetZone(String orgcode) {
        try {
            return patinfoInAndOutService2Mapper.GetZone(orgcode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询失败");
        }
    }


    @Override
    public Boolean getInZoneLv(String inZoneCd) {
        try {
            return patinfoInAndOutService2Mapper.getInZoneLv(inZoneCd) == 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("检验迁入地区是否是区县级失败");
        }
    }

    @Override
    public Boolean getPatInfoIsA(String PatCode) {
        try {
            return patinfoInAndOutService2Mapper.getPatInfoIsA(PatCode) == 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("检验患者信息是否存在失败");
        }
    }


    @Override
    public String getOrgCode(String inOrgCd) {
        try {
            return patinfoInAndOutService2Mapper.getOrgCode(inOrgCd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询机构国网编号失败");
        }
    }

    @Override
    public PatInfoIsSyn getPatInfoIsSync(String cd) {
        try {
            return patinfoInAndOutService2Mapper.getPatInfoIsSync(cd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询患者信息失败");
        }
    }

    @Override
    public MoveOutInfo getMoveOutInfo(String moveCode) {
        try {
            return patinfoInAndOutService2Mapper.getMoveOutInfo(moveCode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取流转信息失败");
        }

    }

    @Override
    public PatInfo2 getPatInfo(String patInfoCd) {
        try {
            return patinfoInAndOutService2Mapper.getPatInfo(patInfoCd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取患者信息失败");
        }
    }

    @Override
    public Boolean getRepeattMoveOutInfoByIDCode(String idCode) {
        try {
            return patinfoInAndOutService2Mapper.getRepeattMoveOutInfoByIDCode(idCode) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询患者是否有流转记录未终结失败");
        }
    }

    @Override
    public PatInfoMana getPatInfoByIDCode(String idCode) {
        try {
            return patinfoInAndOutService2Mapper.getPatInfoByIDCode(idCode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取患者信息失败");
        }
    }

    @Override
    public Boolean getMoveOutInfoByPatInfoCd(String cd) {
        try {
            return patinfoInAndOutService2Mapper.getMoveOutInfoByPatInfoCd(cd) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询该患者是否有流转记录未终结失败");
        }
    }

    @Override
    public void CallBackApply(RepeatCallBackApplyRequest repeatCallBackApplyRequest) {
        try {
            patinfoInAndOutService2Mapper.CallBackApply(repeatCallBackApplyRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("回写患者信息失败");
        }
    }

    @Override
    public QueryRepeatInfoApplyResponse GetRepeatInfo(String repeatInfoId) {
        try {
            return patinfoInAndOutService2Mapper.GetRepeatInfo(repeatInfoId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取重复患者申请记录失败");
        }
    }

    @Override
    public PatInfoMana getPatInfoByFIELDPK(String dischargeInformationId) {
        try {
            return patinfoInAndOutService2Mapper.getPatInfoByFIELDPK(dischargeInformationId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取重复患者申请记录失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void ThroughApply(ApplyRepeatInfoRequest applyRepeatInfoRequest, MoveOutInfo moveInfo) {
        try {
            //省网回写 实现
//            patinfoInAndOutService2Mapper.PatInfoMoveOut(moveInfo,applyRepeatInfoRequest.extInfoObj);
            //重复患者通过回写
            patinfoInAndOutService2Mapper.ThroughApply(applyRepeatInfoRequest);
            //DLK新增同时修改患者档案管理地区和管理机构
            //重复患者通过回写患者信息
            patinfoInAndOutService2Mapper.ThroughApplyPatInfo(moveInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取重复患者申请记录失败");
        }
    }

    @Override
    public void RejectedApply(ApplyRepeatInfoRequest applyRepeatInfoRequest) {
        try {
            patinfoInAndOutService2Mapper.RejectedApply(applyRepeatInfoRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取重复患者申请记录失败");
        }
    }

    @Override
    public List<Followup2> getPatFollow(String patInfoCd) {
        try {
            return patinfoInAndOutService2Mapper.getPatFollow(patInfoCd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询随访记录列表失败");
        }
    }

    @Override
    public void PatInfoMoveOut(MoveOutInfo moveOutInfo, ExtInfo extInfoObj) {
        try {
            patinfoInAndOutService2Mapper.PatInfoMoveOut(moveOutInfo,extInfoObj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("回写省网患者信息失败");
        }
    }

    @Override
    public void UpdatePatInfoOutRefuse(MoveOutInfo moveOutInfo, ExtInfo extInfoObj) {
        try {
            patinfoInAndOutService2Mapper.UpdatePatInfoOutRefuse(moveOutInfo,extInfoObj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("回写省网患者信息失败");
        }
    }


}
