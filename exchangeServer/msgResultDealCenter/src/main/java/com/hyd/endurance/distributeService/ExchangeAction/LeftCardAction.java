package com.hyd.endurance.distributeService.ExchangeAction;

import com.hyd.endurance.domain.ActInfoDO;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 18:09
 */
public interface LeftCardAction {
    void LeftCardAdd(ActInfoDO info);

    void LeftCardUpdate(ActInfoDO info);

    void LeftCardDelete(ActInfoDO info);
}
