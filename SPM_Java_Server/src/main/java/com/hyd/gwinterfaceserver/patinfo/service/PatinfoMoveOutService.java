package com.hyd.gwinterfaceserver.patinfo.service;

import com.alibaba.fastjson.JSONArray;
import com.hyd.gwinterfaceserver.patinfo.info.PatInfoMoveOutRequest;
import com.hyd.gwinterfaceserver.patinfo.service.impl.PatinfoInAndOutService2Serviceimpl;
import com.hyd.gwinterfaceserver.patinfo.validate.InfoQueryValid;
import com.hyd.gwinterfaceserver.patinfo.vo.Followup2;
import com.hyd.gwinterfaceserver.patinfo.vo.MoveOutInfoGW;
import com.hyd.gwinterfaceserver.patinfo.vo.PatInfoIsSyn;
import com.hyd.gwinterfaceserver.util.GWUrl;
import com.hyd.gwinterfaceserver.util.HttpUtils;
import com.hyd.gwinterfaceserver.util.ResultInfo;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.factory.ResultFactory;
import com.hyd.system.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieshuai on 2020/8/1 下午 3:37
 * 患者基本信息迁出Service  Invalid method declaration; return type required
 */
@Service
public class PatinfoMoveOutService {

    @Value("${config.AccessToken}")
    private String accessToken;

    @Value("${config.OrgCode}")
    private String orgCode;

    @Autowired
    private InfoQueryValid valid;

    @Autowired
    private PatinfoInAndOutService2Serviceimpl outToService;


    /**
     * 患者信息迁出外省（直报1）
     *
     * @param request
     * @return
     */
    public ResultVO patinfoMoveOutByExternal(PatInfoMoveOutRequest request) {
        //参数校验
        if (valid.PatInfoMoveOutRequestValid(request).isError()) {
            return ResultFactory.error(valid.PatInfoMoveOutRequestValid(request).getErrMessage());
        }
        //入参数据业务检查
        if (outToService.getInZoneLv(request.getMoveOutInfo().getInZoneCd())) {
            return ResultFactory.error("迁入地区必须为区县");
        }
        //患者基本信息检查
        if (outToService.getPatInfoIsA(request.moveOutInfo.patInfoCd)) {
            return ResultFactory.error("患者基本信息不存在，无法迁出");
        }
        //迁入机构为空，查询国网机构编码
        if (request.moveOutInfo.inOrgCd.isEmpty()) {
            //获取机构国网编号
            String orgCode = outToService.getOrgCode(request.moveOutInfo.inOrgCd);
            //获取机构下有无基层直报
            HashMap<String, String> map = new HashMap<>();
            map.put("access_token", accessToken);
            map.put("orgcode", orgCode);
            map.put("apptype", "05");
            map.put("org_code", orgCode);
            Map resultMap = HttpUtils.get(
                    GWUrl.mentalhealthbusinesssynergy + "getUserInformationByOrgcode",
                    map,
                    Map.class);
            if(resultMap.isEmpty()){
                return ResultFactory.error("查询失败,无法返回值信息");
            }
            Map keyMessage = (Map)resultMap.get("keyMessage");
            if (!(boolean)resultMap.get("result")) {
                return ResultFactory.error("查询失败：" + keyMessage.get("desc"));
            }

        } else {
            request.moveOutInfo.inOrgCd = "000000000";
        }
        //获取患者信息是否同步国网
        PatInfoIsSyn patInfoIsSync = outToService.getPatInfoIsSync(request.moveOutInfo.patInfoCd);
        if (patInfoIsSync.fIELDPK.isEmpty() || "0".equals(patInfoIsSync.syncStatus)) {
            return ResultFactory.error("患者信息未同步国网，请同步后进行操作" + patInfoIsSync.syncError);
        }

        //同步患者随访
        List<Followup2> followList = outToService.getPatFollow(request.moveOutInfo.patInfoCd);
        for (Followup2 item : followList) {
            if (item.followUpInformationId.isEmpty() || !"1".equals(item.syncStatus)) {
                return ResultFactory.error("随访信息同步失败" + item.syncError);
            }
        }

        String InOrgCd;
        if ("000000000".equals(request.moveOutInfo.inOrgCd)) {
            InOrgCd = "000000000";
        } else {
            InOrgCd = outToService.getOrgCode(request.moveOutInfo.inOrgCd);
        }
        // 请求json化
        StringBuffer buffer = new StringBuffer();
        buffer.append("{")
                //TODO 访问国网API 是复杂JSON格式，需要加上headToken信息
                .append("{" +
                        "\"AccessToken\":"+accessToken+", " +
                        "\"OrgCode\":"+orgCode+", " +
                        "\"AppType\":\"05\", " +
                        "MoveInAndOutInfo:{")
                .append("\"MoveInAndOutId\":\"").append(" ")
                .append("\",\"MoveInAndOutType\":\"").append("1")
                .append("\",\"ReportID\":\"").append(" ")
                .append("\",\"PersonID\":\"").append(patInfoIsSync.fIELDPK) //pk值
                .append("\",\"OutZoneCode\":\"").append(request.moveOutInfo.outZoneCd)
                .append("\",\"OutOrgCode\":\"").append(outToService.getOrgCode(request.moveOutInfo.outOrgCd))
                .append("\",\"MoveOutTime\":\"").append(new Date())
                .append("\",\"InZoneCode\":\"").append(request.moveOutInfo.inZoneCd)
                .append("\",\"InOrgCode\":\"").append(InOrgCd)
                .append("\",\"MoveInTime\":\"").append(" ")
                .append("\",\"MoveOutCause\":\"").append(request.moveOutInfo.moveOutCause)
                .append("\",\"SignedInformedConsentDate\":\"").append(patInfoIsSync.iCSignDate)
                .append("\",\"RefuseCause\":\"").append(" ")
                .append("\"}}");
        Map reusltMap = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "moveOutInformation", buffer.toString(), Map.class);
        if(reusltMap.isEmpty()){
            return ResultFactory.error("迁出失败: 国网无任何返回值信息");
        }
        Map keyMessage = (Map)reusltMap.get("keyMessage");
        if (!(boolean)reusltMap.get("result")) {
            return ResultFactory.error("迁出失败:" + keyMessage.get("desc"));
        }
        //回写迁出
        request.moveOutInfo.fIELDPK = (String) keyMessage.get("id");
        try {
            if (request.moveOutInfo.cd.isEmpty()) {
                //迁出患者信息(省网回写)
                outToService.PatInfoMoveOut(request.moveOutInfo, request.extInfoObj);
            } else {
                //迁出患者信息被拒后再次迁出(省网回写)
                outToService.UpdatePatInfoOutRefuse(request.moveOutInfo, request.extInfoObj);
            }
        } catch (Exception ex) {
            SqlException sqlException = (SqlException) ex;
            //收回
            //TODO 2020-8-1 18:10:46 此处API文档返回值为 {ResultInfo:{result: true/false, keyMessage: { id : ""//desc:""}}}
            //TODO                   和此处定义ResultInfo格式不同，待进行需求确认
            HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "moveOutInformation", buffer.toString(), ResultInfo.class);
            ResultFactory.error(sqlException.getMsg());
        }
        return ResultFactory.success();
    }
}


