package com.hyd.endurance.distributeService.ExchangeAction;

import com.hyd.endurance.domain.ActInfoDO;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 18:08
 */
public interface PatInfoAction {
    void PatInfoAdd(ActInfoDO info);

    void PatInfoUpdate(ActInfoDO info);

    void PatInfoDelete(ActInfoDO info);

    void PatInfoInsepct(ActInfoDO info);

    void PatInfoTurnDeath(ActInfoDO info);

    void PatInfoRestore(ActInfoDO info);
}
