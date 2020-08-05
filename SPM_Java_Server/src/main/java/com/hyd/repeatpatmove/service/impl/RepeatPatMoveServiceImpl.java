package com.hyd.repeatpatmove.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyd.gwinterfaceserver.util.GWUrl;
import com.hyd.repeatpatmove.info.RepeatPatApplyInfo;
import com.hyd.repeatpatmove.info.RepeatPatMoveInfo;
import com.hyd.repeatpatmove.mapper.RepeatPatMoveMapper;
import com.hyd.repeatpatmove.service.RepeatPatMoveService;
import com.hyd.repeatpatmove.util.HttpUtil;
import com.hyd.repeatpatmove.util.Tools;
import com.hyd.repeatpatmove.vo.GWResultVO;
import com.hyd.repeatpatmove.vo.GWSuccessIdVO;
import com.hyd.repeatpatmove.vo.RepeatPatInfo;
import com.hyd.system.factory.ResultFactory;
import com.hyd.system.util.SystemUtils;
import com.hyd.system.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/3 17:26
 */
@Service
public class RepeatPatMoveServiceImpl implements RepeatPatMoveService {
    @Autowired(required = false)
    RepeatPatMoveMapper mapper;

    private Gson gson = new Gson();

    @Override
    public List<Map<String, String>> getRepeatInfoByIdcode(String idCode) {



        return null;
    }

    @Override
    public RepeatPatMoveInfo buildPatMoveInfo() {
        return null;
    }

    @Override
    public GWResultVO applyRepeatPatMove(Map<String, String> param) {
        return null;
    }

    @Override
    public void syncMoveStaus() {

    }

    @Override
    public ResultVO RepeatPatMoveOutApply(RepeatPatApplyInfo input) {

        Map<String, String> param = new LinkedHashMap<>();
        param.put("idcard_code", input.getIDNum());
        String body = Tools.getMapToJson(param);
        String url = GWUrl.mentalhealthbusinesssynergy + "getRepeatInfoByIdcode";
        // 查询重复患者基本信息
        GWResultVO gwResult =  HttpUtil.post(url, body, new TypeToken<GWResultVO<List<RepeatPatInfo>>>(){}.getType());
        // todo 参数校验
        // 判断患者国网信息状态
        if (gwResult.getCode()==-1){
            url = GWUrl.mentalhealthbusinesssynergy + "repeatInfoApply";
            body = gson.toJson(input);
            gwResult = HttpUtil.post(url, body, new TypeToken<GWResultVO<GWSuccessIdVO>>(){}.getType());

            ResultFactory.error("患者未同步国网信息");
        }
        // todo 查询未完结的流转记录
        // todo 身份证查数据库患者信息
        // 不在数据库中，发起申请，回写到数据库
        // 在数据库，判断未完结的流转记录 有则 return
        // 判断管理机构是否为我省 是 则 无法发起重复迁移
        // 不是我省管理，判断国网id与本地id是否相同， 不同 则提示患者信息有误 无法发起申请
        // 相同则发起申请  回写到数据库
        Map<String, String> patInfo = mapper.queryRepeatPatInfoByIDcard(input.getIDNum());
        if (patInfo == null || patInfo.size() == 0){
            url = GWUrl.mentalhealthbusinesssynergy + "repeatInfoApply";
            body = gson.toJson(input);
            gwResult = HttpUtil.post(url, body, new TypeToken<GWResultVO<GWSuccessIdVO>>(){}.getType());
        }
        //

        return ResultFactory.success(gwResult.getData());
    }

    /**
     * 重复患者迁出申请收回
     * @param repeatId
     * @return
     */
    @Override
    public ResultVO RepeatPatMoveCallBackApply(String repeatId) {
        // 调用协同6.1.17 回写DB


        return null;
    }

    private <T> GWResultVO callGWinterface (String methodNam, String body, Type type){

    }
}
