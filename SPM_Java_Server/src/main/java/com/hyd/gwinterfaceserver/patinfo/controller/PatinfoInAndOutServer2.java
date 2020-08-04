//package com.hyd.gwinterfaceserver.patinfo.controller;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.hyd.gwinterfaceserver.patinfo.info.*;
//import com.hyd.gwinterfaceserver.patinfo.service.PatinfoInAndOutService2Service;
//import com.hyd.gwinterfaceserver.util.HttpUtils;
//import com.hyd.gwinterfaceserver.patinfo.validate.InfoQueryValid;
//import com.hyd.gwinterfaceserver.patinfo.vo.*;
//import com.hyd.gwinterfaceserver.util.GWUrl;
//import com.hyd.gwinterfaceserver.util.ResultInfo;
//import com.hyd.gwinterfaceserver.util.ResultInfos;
//import com.hyd.system.exceptclass.SqlException;
//import com.hyd.system.factory.ResultFactorys;
//import com.hyd.system.vo.ResultInfo;
//import com.hyd.system.vo.ValidateVO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//@RestController
//@RequestMapping("/PatinfoInAndOut2")
//public class PatinfoInAndOutServer2 {
//
//    @Autowired
//    InfoQueryValid valider;
//
//    @Autowired
//    PatinfoInAndOutService2Service patinfoInAndOutService2Service;
//
//    /**
//     * 重复患者查询（直报1）
//     */
//    @PostMapping("/GetRepeatInfoByIdcode")
//    ResultInfo GetRepeatInfoByIdcode(@RequestBody getRepeatInfoByIdcodeRequest getRepeatInfoByIdcodeRequest) {
//        ValidateVO validresult = valider.getRepeatInfoByIdcodeRequestValid(getRepeatInfoByIdcodeRequest);
//        if (validresult.isError()) {
//            return ResultFactorys.error(validresult.getErrMessage());
//        }
//        HashMap<String, String> map = new HashMap<>();
//        map.put("idcard_type", "1");
//        map.put("idcard_code", getRepeatInfoByIdcodeRequest.idcode);
//        //获取精神卫生重复患者信息
//        ResultInfos resultInfo = HttpUtils.get(GWUrl.mentalhealthbusinesssynergy + "getRepeatInfoByIdcode", map, ResultInfos.class);
//        if ("-1".equals(resultInfo.code)) {
//            return ResultFactorys.error("查询失败：" + resultInfo.message);
//        }
//        resultInfo.data.forEach(item -> {
//            if (patinfoInAndOutService2Service.GetOrgan(item.orgCode) == null) {
//                item.orgNam = item.orgCode;
//            } else {
//                item.orgNam = patinfoInAndOutService2Service.GetOrgan(item.orgCode).nam;
//            }
//
//            if (patinfoInAndOutService2Service.GetZone(item.zoneCode) == null) {
//                item.zoneNam = item.zoneCode;
//            } else {
//                item.zoneNam = patinfoInAndOutService2Service.GetZone(item.zoneCode).nam;
//            }
//        });
//        return ResultFactorys.success(resultInfo.data);
//    }
//
//
//    /**
//     * 患者信息迁出外省（直报1）
//     */
//    @PostMapping("/PatInfoMoveOut")
//    ResultInfo PatInfoMoveOut(@RequestBody PatInfoMoveOutRequest patInfoMoveOutRequest) {
//        ResultInfo resultVO = new ResultInfo();
//        resultVO.setCode(-1);
//        Object retdata = null;
//        //入参数据检查
//        ValidateVO validresult = valider.PatInfoMoveOutRequestValid(patInfoMoveOutRequest);
//        if (validresult.isError()) {
//            return ResultFactorys.error(validresult.getErrMessage());
//        }
//        //入参数据业务检查
//        if (patinfoInAndOutService2Service.getInZoneLv(patInfoMoveOutRequest.moveOutInfo.inZoneCd)) {
//            return ResultFactorys.error("迁入地区必须为区县");
//        }
//
//        if (patinfoInAndOutService2Service.getPatInfoIsA(patInfoMoveOutRequest.moveOutInfo.patInfoCd)) {
//            return ResultFactorys.error("患者基本信息不存在，无法迁出");
//        }
//
//        if (patInfoMoveOutRequest.moveOutInfo.inOrgCd.isEmpty() || patInfoMoveOutRequest.moveOutInfo.inOrgCd == null) {
//            //获取机构国网编号
//            String orgCode = patinfoInAndOutService2Service.getOrgCode(patInfoMoveOutRequest.moveOutInfo.inOrgCd);
//            //获取机构下有无基层直报
//            HashMap<String, String> map = new HashMap<>();
//            map.put("org_code", orgCode);
//            ResultInfo resultInfo = HttpUtils.get(GWUrl.mentalhealthbusinesssynergy + "getUserInformationByOrgcode", map, ResultInfo.class);
//            if ("-1".equals(resultInfo.code)) {
//                return ResultFactorys.error("查询失败：" + resultInfo.message);
//            }
//            retdata = resultInfo.data.contactInformation;
//
//        } else {
//            patInfoMoveOutRequest.moveOutInfo.inOrgCd = "000000000";
//        }
//        //获取患者信息是否同步国网
//        PatInfoIsSyn patInfoIsSync = patinfoInAndOutService2Service.getPatInfoIsSync(patInfoMoveOutRequest.moveOutInfo.patInfoCd);
//        if (patInfoIsSync.fIELDPK.isEmpty() || patInfoIsSync.fIELDPK == null || "0".equals(patInfoIsSync.syncStatus)) {
//            return ResultFactorys.error("患者信息未同步国网，请同步后进行操作" + patInfoIsSync.syncError);
//        }
//
//        //同步患者随访
//        List<Followup2> followList = patinfoInAndOutService2Service.getPatFollow(patInfoMoveOutRequest.moveOutInfo.patInfoCd);
//        for (Followup2 item : followList) {
//            if (item.followUpInformationId.isEmpty() || item.followUpInformationId == null || !"1".equals(item.syncStatus)) {
//                return ResultFactorys.error("随访信息同步失败" + item.syncError);
//            }
//        }
//
//        String InOrgCd = "";
//        if ("000000000".equals(patInfoMoveOutRequest.moveOutInfo.inOrgCd)) {
//            InOrgCd = "000000000";
//        } else {
//            InOrgCd = patinfoInAndOutService2Service.getOrgCode(patInfoMoveOutRequest.moveOutInfo.inOrgCd);
//        }
//
//        //迁出
//        MoveOutInfoGW moveOutInfoGW = new MoveOutInfoGW();
////        {
////            MoveInAndOutId = "",
////            MoveInAndOutType = "1",
////            NewCaseReportId = "",
////            BasicInformationId = isSync,
////            OoutOrgCountyCode = input.MoveOutInfo.OutZoneCd,
////            OutOrgCode = OutOrgCd,
////            MoveOutTime = now,
////            InOrgCountyCode = input.MoveOutInfo.InZoneCd,
////            InOrgCode = InOrgCd,
////            MoveInTime = "",
////            MoveOutCause = input.MoveOutInfo.MoveOutCause,
////            SignedInformedConsentDate = PatInfo.SignedInformedConsentDate,
////            RefuseCause = "",
////        };
//        // 请求json化
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("{")
//                .append("\"MoveInAndOutId\":\"").append("")
//                .append("\",\"MoveInAndOutType\":\"").append("1")
//                .append("\",\"ReportID\":\"").append("")
//                .append("\",\"PersonID\":\"").append(patInfoIsSync.fIELDPK) //pk值
//                .append("\",\"OutZoneCode\":\"").append(patInfoMoveOutRequest.moveOutInfo.outZoneCd)
//                .append("\",\"OutOrgCode\":\"").append(patinfoInAndOutService2Service.getOrgCode(patInfoMoveOutRequest.moveOutInfo.outOrgCd))
//                .append("\",\"MoveOutTime\":\"").append(new Date())
//                .append("\",\"InZoneCode\":\"").append(patInfoMoveOutRequest.moveOutInfo.inZoneCd)
//                .append("\",\"InOrgCode\":\"").append(InOrgCd)
//                .append("\",\"MoveInTime\":\"").append("")
//                .append("\",\"MoveOutCause\":\"").append(patInfoMoveOutRequest.moveOutInfo.moveOutCause)
//                .append("\",\"SignedInformedConsentDate\":\"").append(patInfoIsSync.iCSignDate)
//                .append("\",\"RefuseCause\":\"").append("")
//                .append("\"}");
//        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "moveOutInformation", buffer.toString(), ResultInfo.class);
//        if ("-1".equals(resultInfo.code)) {
//            return ResultFactorys.error("迁出失败:" + resultInfo.message);
//        }
//        //回写迁出
//        patInfoMoveOutRequest.moveOutInfo.fIELDPK = resultInfo.data.iD;
//        try {
//            if (patInfoMoveOutRequest.moveOutInfo.cd.isEmpty() || patInfoMoveOutRequest.moveOutInfo.cd == null) {
//                //迁出患者信息(省网回写)
//                patinfoInAndOutService2Service.PatInfoMoveOut(patInfoMoveOutRequest.moveOutInfo, patInfoMoveOutRequest.extInfoObj);
//            } else {
//                //迁出患者信息被拒后再次迁出(省网回写)
//                patinfoInAndOutService2Service.UpdatePatInfoOutRefuse(patInfoMoveOutRequest.moveOutInfo, patInfoMoveOutRequest.extInfoObj);
//            }
//        } catch (Exception ex) {
//            SqlException sqlException=(SqlException)ex;
//            //收回
//            HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "moveOutInformation", resultInfo.message,ResultInfo.class);
//            ResultFactorys.error(sqlException.getMsg());
//        }
//        return ResultFactorys.success();
//    }
//
//    /**
//     * 跨省迁入患者 响应方法（直报1）
//     */
//    @PostMapping("/responseMoveIn")
//    ResultInfo responseMoveIn(@RequestBody responseMoveInInput responseMoveInInput) {
//        if (responseMoveInInput == null || responseMoveInInput.moveInAndOutId.isEmpty()) {
//            return ResultFactorys.error("入参不能为空");
//        }
//
//        try {
//            //实现 迁入响应
////            result =  patinfoInAndOutService2Service.responseMoveIn(input);
//        } catch (Exception ex) {
////            result.code = -99;
////            result.message = Messages.APP_EXECERROR;
//        }
//
//
//        return null;
//    }
//
//
//    /**
//     * 患者信息外省迁入接收(直报1)
//     */
//    @PostMapping("/PatInfoMoveIn")
//    ResultInfo PatInfoMoveIn(@RequestBody PatInfoMoveInRequest patInfoMoveInRequest) {
//        ValidateVO validresult = valider.PatInfoMoveInRequestValid(patInfoMoveInRequest);
//        if (validresult.isError()) {
//            return ResultFactorys.error(validresult.getErrMessage());
//        }
//        //获取流转信息
//        MoveOutInfo moveOutInfo = patinfoInAndOutService2Service.getMoveOutInfo(patInfoMoveInRequest.moveCode);
//        if (moveOutInfo == null || moveOutInfo.patInfoCd.isEmpty() || moveOutInfo.patInfoCd == null) {
//            return ResultFactorys.error("接收失败：获取患者编号失败");
//        }
//
//        //获取本地患者信息
//        PatInfo2 PatInfo = patinfoInAndOutService2Service.getPatInfo(moveOutInfo.patInfoCd);
//
//        //修改患者管理地区和机构
//        PatInfo.orgCountyCode = moveOutInfo.inZoneCd;
//        PatInfo.orgCode = patinfoInAndOutService2Service.getOrgCode(moveOutInfo.inOrgCd);
//        PatInfo.basicInformationNumber = patInfoMoveInRequest.patCode;
//
////        byte[] patInfoXml = Encoding.UTF8.GetBytes(XmlHelper.SerialXml<PatInfo2>(PatInfo, "BasicInformation"));
//        BasicInformation basicInformation = new BasicInformation(); // moveOutInfo.fiELDPK+PatInfo
////        //接收
//        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "acceptMoveIn", JSONObject.toJSONString(basicInformation),ResultInfo.class);
//        if ("-1".equals(resultInfo.code)) {
//            return ResultFactorys.error("接收失败:" + resultInfo.message);
//        }
//        PatInfo.dischargeInformationId = resultInfo.message;
//        //回写流转记录 实现
////        patinfoInAndOutService2Service.AcceptMoveInPatInfo(moveOutInfo.Cd, moveOutInfo.InZoneCd, moveOutInfo.InOrgCd, PatInfo, moveOutInfo.PatInfoCd, input.ExtInfoObj);
//        return null;
//    }
//
//
//    /**
//     * 患者信息外省迁入(接收)到区县的迁入 分发 （本级1）
//     */
//    @PostMapping("/PatInfoMoveInBJ")
//    ResultInfo PatInfoMoveInBJ(@RequestBody PatInfoMoveInRequest patInfoMoveInRequest) {
//        ValidateVO validresult = valider.PatInfoMoveInBJValid(patInfoMoveInRequest);
//        if (validresult.isError()) {
//            return ResultFactorys.error(validresult.getErrMessage());
//        }
//        //获取流转信息
//        MoveOutInfo moveOutInfo = patinfoInAndOutService2Service.getMoveOutInfo(patInfoMoveInRequest.moveCode);
//        if (moveOutInfo == null || moveOutInfo.patInfoCd.isEmpty() || moveOutInfo.patInfoCd == null) {
//            return ResultFactorys.error("接收失败：获取患者编号失败");
//        }
//
//        //获取本地患者信息
//        PatInfo2 PatInfo = patinfoInAndOutService2Service.getPatInfo(moveOutInfo.patInfoCd);
//
//        //修改患者管理地区和机构
//        PatInfo.orgCountyCode = moveOutInfo.inZoneCd;
//        PatInfo.orgCode = patinfoInAndOutService2Service.getOrgCode(moveOutInfo.inOrgCd);
//        PatInfo.basicInformationNumber = patInfoMoveInRequest.patCode;
//
////        byte[] patInfoXml = Encoding.UTF8.GetBytes(XmlHelper.SerialXml<PatInfo2>(PatInfo, "BasicInformation"));
//        BasicInformation basicInformation = new BasicInformation(); // moveOutInfo.fiELDPK+PatInfo
////        //接收
//        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "acceptMoveIn", JSONObject.toJSONString(basicInformation));
//        if ("-1".equals(resultInfo.result)) {
//            //如果该流转单已经失效了，就修改该流转记录的流转状态为FlowState003. arenxl added in 20191230
//            if (resultInfo.keyMessage.contains("流转单并未处于迁出中")) {
//                //将流转记录的流转状态设置为已完成。FlowState003拒绝
//                //实现
//                //patinfoInAndOutService2Service.refuseMoveRec(input.MoveCode, "迁入的流转单已经失效");
//                return ResultFactorys.error("接收失败：迁入的流转单已经失效，请联系迁出单位重新迁出。");
//            }
//            return ResultFactorys.error("接收失败:" + resultInfo.keyMessage);
//        }
//        PatInfo.dischargeInformationId = resultInfo.keyMessage;
//
//        basicInfo organInfo = patinfoInAndOutService2Service.GetOrgan(PatInfo.orgCode);
//        //修改流转记录迁入机构（迁入到区县本级用户分发至社区时回写）实现
////        patinfoInAndOutService2Service.AcceptMoveInPatInfoBJ(moveOutInfo.Cd, organInfo.Cd, organInfo.Nam);
//        //回写流转记录 实现
////        patinfoInAndOutService2Service.AcceptMoveInPatInfo(moveOutInfo.Cd, moveOutInfo.InZoneCd, moveOutInfo.InOrgCd, PatInfo, moveOutInfo.PatInfoCd, input.ExtInfoObj);
//        return null;
//    }
//
//
//    /**
//     * 患者信息迁入拒绝（直报1，本级1）
//     */
//    @PostMapping("/PatInfoRefuseMoveIn")
//    ResultInfo PatInfoRefuseMoveIn(@RequestBody PatInfoRefuseMoveInRequest patInfoMoveInRequest) {
//        ValidateVO validresult = valider.PatInfoRefuseMoveInRequestValid(patInfoMoveInRequest);
//        if (validresult.isError()) {
//            return ResultFactorys.error(validresult.getErrMessage());
//        }
//        //接收拒绝接口
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("{")
//                .append("\"MoveInAndOutId\":\"").append(patInfoMoveInRequest.moveCode)
//                .append("\",\"RefusedCause\":\"").append(patInfoMoveInRequest.refusedCause)
//                .append("\"}");
//        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "refuseMoveIn", buffer.toString());
//        if ("-1".equals(resultInfo.result)) {
//            return ResultFactorys.error("拒绝失败:" + resultInfo.keyMessage);
//        }
//
//        //回写省网 实现
////        patinfoInAndOutService2Service.RefusePatIn(input.MoveCode, null, null, input.RefusedCause, input.ExtInfoObj);
//        return null;
//    }
//
//
//    /**
//     * 迁出
//     * 重复患者外省迁移申请（直报1）
//     */
//    @PostMapping("/RepeatInfoApply")
//    ResultInfo RepeatInfoApply(@RequestBody repeatInfoApplyRequest repeatInfoApplyRequest) {
//        ValidateVO validresult = valider.repeatInfoApplyRequestValid(repeatInfoApplyRequest);
//        if (validresult.isError()) {
//            return ResultFactorys.error(validresult.getErrMessage());
//        }
//        basicInfo auditOrgan = patinfoInAndOutService2Service.GetOrgan(repeatInfoApplyRequest.auditOrgan);
//        if (auditOrgan == null) {
//            return ResultFactorys.error("国家机构不存在");
//        }
//        basicInfo auditZone = patinfoInAndOutService2Service.GetZone(repeatInfoApplyRequest.auditZone);
//        if (auditZone == null) {
//            return ResultFactorys.error("国家地区不存在");
//        }
//
//        repeatInfoApplyRequest.auditZone = auditZone.cd;
//
//
//        //查询是否有未终结的重复患者流转记录
//        if (patinfoInAndOutService2Service.getRepeattMoveOutInfoByIDCode(repeatInfoApplyRequest.idCode)) {
//            return ResultFactorys.error("该患者还有未终结的重复迁移记录，无法发起重复迁移");
//        }
//
//        RepeatInfoApplyInfo repeatInfoApplyInfo = new RepeatInfoApplyInfo();
//        //获取患者信息
//        PatInfoMana patInfoByIDCode = patinfoInAndOutService2Service.getPatInfoByIDCode(repeatInfoApplyRequest.idCode);
//        // 精神卫生重复患者迁移申请
//        if (patInfoByIDCode == null) {
//            //发起申请
//            ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "repeatInfoApply", JSONObject.toJSONString(repeatInfoApplyInfo));
//            if ("-1".equals(resultInfo.result)) {
//                return ResultFactorys.error("国家申请失败:" + resultInfo.keyMessage);
//            }
//            //回写
//            repeatInfoApplyRequest.repeatInfoId = resultInfo.keyMessage;
//            //实现
////            patinfoInAndOutService2Service.RepeatInfoApply(repeatInfoApplyRequest);
//        } else {
//            //查询该患者是否有流转记录未终结;
//            if (patinfoInAndOutService2Service.getMoveOutInfoByPatInfoCd(patInfoByIDCode.cd)) {
//                return ResultFactorys.error("该患者还有未终结的重复迁移记录，无法发起重复迁移");
//            }
//        }
//
//        //是否当前管理机构
//        if (patInfoByIDCode.organCd != repeatInfoApplyRequest.extInfoObj.organCode && !"51".equals(patInfoByIDCode.zoneCd.substring(0, 2))) {
//            if (patInfoByIDCode.fiELDPK.isEmpty() || patInfoByIDCode.fiELDPK == null || !"1".equals(patInfoByIDCode.syncStatus)) {
//                return ResultFactorys.error("患者信息未同步至国家，无法发起重复迁移");
//            } else {
//                if (patInfoByIDCode.fiELDPK.equals(repeatInfoApplyRequest.dischargeInformationId)) {
//                    //发起申请
//                    ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "repeatInfoApply", JSONObject.toJSONString(repeatInfoApplyInfo));
//                    if ("-1".equals(resultInfo.result)) {
//                        return ResultFactorys.error("国家申请失败:" + resultInfo.keyMessage);
//                    }
//                    //回写
//                    repeatInfoApplyRequest.repeatInfoId = resultInfo.keyMessage;
//                    //实现
////            patinfoInAndOutService2Service.RepeatInfoApply(repeatInfoApplyRequest);
//                } else {
//                    return ResultFactorys.error("患者基本信息不符，无法发起重复迁移");
//                }
//            }
//
//        } else {
//            return ResultFactorys.error("该患者已在本省管理，无法发起重复迁移");
//        }
//        return null;
//    }
//
//    /**
//     * 回收重复患者外省迁移申请(直报1)
//     */
//    @PostMapping("/RepeatCallBackApply")
//    ResultInfo RepeatCallBackApply(@RequestBody RepeatCallBackApplyRequest repeatCallBackApplyRequest) {
//        ValidateVO validresult = valider.RepeatCallBackApplyRequestValid(repeatCallBackApplyRequest);
//        if (validresult.isError()) {
//            return ResultFactorys.error(validresult.getErrMessage());
//        }
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("{")
//                .append("\"RepeatInfoId\":\"").append(repeatCallBackApplyRequest.repeatInfoId)
//                .append("\"}");
//        //收回
//        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "callBackApply", buffer.toString());
//        if ("-1".equals(resultInfo.result)) {
//            return ResultFactorys.error("国家收回失败:" + resultInfo.keyMessage);
//        }
//        //回写
//        repeatCallBackApplyRequest.repeatInfoId = resultInfo.keyMessage;
//        patinfoInAndOutService2Service.CallBackApply(repeatCallBackApplyRequest);
//        return null;
//    }
//
//
//    /**
//     * 迁入
//     * 重复患者迁移申请审核(直报1)
//     */
//    @PostMapping("/ApplyRepeatInfo")
//    ResultInfo ApplyRepeatInfo(@RequestBody ApplyRepeatInfoRequest applyRepeatInfoRequest) {
//        ValidateVO validresult = valider.ApplyRepeatInfoRequestValid(applyRepeatInfoRequest);
//        if (validresult.isError()) {
//            return ResultFactorys.error(validresult.getErrMessage());
//        }
//
//        //获取重复患者申请记录
//        QueryRepeatInfoApplyResponse queryRepeatInfoApplyResponse = patinfoInAndOutService2Service.GetRepeatInfo(applyRepeatInfoRequest.repeatInfoId);
//        if (queryRepeatInfoApplyResponse == null) {
//            return ResultFactorys.error("重复患者记录不存在，无法操作");
//        }
//        //获取患者信息
//        PatInfoMana patInfoMana = patinfoInAndOutService2Service.getPatInfoByFIELDPK(queryRepeatInfoApplyResponse.dischargeInformationId);
//        if (patInfoMana == null) {
//            return ResultFactorys.error(" 患者信息不存在，无法操作");
//        }
//
//        if (patinfoInAndOutService2Service.getMoveOutInfoByPatInfoCd(patInfoMana.cd)) {
//            return ResultFactorys.error("该患者还有未完结的流转记录，无法操作");
//        }
//
//        if (!applyRepeatInfoRequest.extInfoObj.organCode.equals(patInfoMana.organCd)) {
//            return ResultFactorys.error("机构于患者管理机构不符,无法操作");
//        }
//
//        MoveOutInfo moveInfo = new MoveOutInfo();
////        moveInfo.InOrgCd = applyInfo.ApplyOrgan;
////        moveInfo.InOrgNam = applyInfo.ApplyOrganName;
////        moveInfo.InZoneCd = applyInfo.ApplyZone;
////        moveInfo.InZoneNam = applyInfo.ApplyZoneName;
////        moveInfo.OutOrgCd = applyInfo.AuditOrgan;
////        moveInfo.OutOrgNam = applyInfo.AuditOrganName;
////        moveInfo.OutZoneCd = applyInfo.AuditZone;
////        moveInfo.OutZoneNam = applyInfo.AuditZoneName;
////        moveInfo.PatInfoCd = patInfo.Cd;
////        moveInfo.MoveOutCd = "OutType001";
////        moveInfo.OutDate = DateTime.Now.ToString("yyyy-MM-dd");
////        moveInfo.MoveOutCause = "重复患者迁移申请";
////        moveInfo.DelStatus = "DelLogo001";
////        moveInfo.MoveStatusCd = "FlowState001";
////        moveInfo.State = "1";
//
//        //重复患者迁移申请审核
//        //审核
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("{")
//                .append("\"RepeatInfoId\":\"").append(applyRepeatInfoRequest.repeatInfoId)
//                .append("\",\"State\":\"").append("2")
//                .append("\"}");
//        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "applyRepeatInfo", buffer.toString());
//        if ("-1".equals(resultInfo.result)) {
//            return ResultFactorys.error("国家审核失败:" + resultInfo.keyMessage);
//        }
//        //通过回写
//        if ("2".equals(applyRepeatInfoRequest.state)) { //重复患者通过回写
//            applyRepeatInfoRequest.moveCode = resultInfo.keyMessage;
//            patinfoInAndOutService2Service.ThroughApply(applyRepeatInfoRequest, moveInfo);
//        }
//        //驳回回写
//        if ("3".equals(applyRepeatInfoRequest.state) || "5".equals(applyRepeatInfoRequest.state)) {
//            patinfoInAndOutService2Service.RejectedApply(applyRepeatInfoRequest);
//        }
//        return null;
//    }
//
//
//}
