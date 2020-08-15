package com.hyd.endurance.distributeService.ExchangeAction;

import com.hyd.endurance.domain.ActInfoDO;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 18:08
 */
public interface ReportCardAction {
    void ReportAdd(ActInfoDO info);

    void ReportUpdate(ActInfoDO info);

    void ReportDelete(ActInfoDO info);
}
