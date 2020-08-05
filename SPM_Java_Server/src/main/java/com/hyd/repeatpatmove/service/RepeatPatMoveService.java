package com.hyd.repeatpatmove.service;

import com.hyd.repeatpatmove.info.RepeatPatApplyInfo;
import com.hyd.repeatpatmove.info.RepeatPatMoveInfo;
import com.hyd.repeatpatmove.vo.GWResultVO;
import com.hyd.system.vo.ResultVO;

import java.util.List;
import java.util.Map;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/3 17:25
 */
public interface RepeatPatMoveService {

    // 查询患者信息
    List<Map<String, String>> getRepeatInfoByIdcode(String idCode);

    // 查询患者流转记录

    // 构建迁移患者数据
    RepeatPatMoveInfo buildPatMoveInfo();

    // 发起申请
    GWResultVO applyRepeatPatMove(Map<String, String> param);

    // 回写状态
    void syncMoveStaus();

    /**
     * 重复患者迁出申请
     * @param input
     * @return
     */
    ResultVO RepeatPatMoveOutApply(RepeatPatApplyInfo input);

    /**
     * 重复患者迁出申请收回
     * @return
     */
    ResultVO RepeatPatMoveCallBackApply(String repeatId);

}
