package com.hyd.repeatpatmove.service.impl;

import com.google.gson.reflect.TypeToken;
import com.hyd.gwinterfaceserver.util.GWUrl;
import com.hyd.repeatpatmove.info.RepeatPatApplyInfo;
import com.hyd.repeatpatmove.info.RepeatPatMoveInfo;
import com.hyd.repeatpatmove.mapper.RepeatPatMoveMapper;
import com.hyd.repeatpatmove.service.RepeatPatMoveService;
import com.hyd.repeatpatmove.util.HttpUtil;
import com.hyd.repeatpatmove.util.Tools;
import com.hyd.repeatpatmove.vo.GWResultVO;
import com.hyd.repeatpatmove.vo.RepeatPatInfo;
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
        param.put("idcard_code", input.getIDCard());
        String body = Tools.getMapToJson(param);
        String url = GWUrl.mentalhealthbusinesssynergy + "getRepeatInfoByIdcode";

        GWResultVO gwResult =  HttpUtil.post(url, body, new TypeToken<GWResultVO<List<RepeatPatInfo>>>(){}.getType());







        return null;
    }
}
