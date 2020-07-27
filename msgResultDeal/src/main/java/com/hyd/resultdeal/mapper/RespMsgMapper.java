package com.hyd.resultdeal.mapper;

import com.hyd.resultdeal.domain.ReturnMsgDO;

import java.util.List;

/**
 * Created by xieshuai on 2020/7/2 上午 11:34
 */
public interface RespMsgMapper {

    void save(ReturnMsgDO returnMsgDO);

    void update(ReturnMsgDO returnMsgDO);

    List<ReturnMsgDO> findAll();

    void delete(Integer messageId);
}
