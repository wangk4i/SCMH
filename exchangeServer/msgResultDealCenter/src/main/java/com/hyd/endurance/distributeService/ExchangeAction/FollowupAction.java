package com.hyd.endurance.distributeService.ExchangeAction;

import com.hyd.endurance.domain.ActInfoDO;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 18:09
 */
public interface FollowupAction {
    void FollowupAdd(ActInfoDO info);

    void FollowupUpdate(ActInfoDO info);

    void FollowupDelete(ActInfoDO info);
}
