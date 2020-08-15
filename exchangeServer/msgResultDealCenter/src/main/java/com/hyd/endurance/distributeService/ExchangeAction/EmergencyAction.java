package com.hyd.endurance.distributeService.ExchangeAction;

import com.hyd.endurance.domain.ActInfoDO;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 18:09
 */
public interface EmergencyAction {
    void EmergencyAdd(ActInfoDO info);

    void EmergencyUpdate(ActInfoDO info);

    void EmergencyDelete(ActInfoDO info);
}
