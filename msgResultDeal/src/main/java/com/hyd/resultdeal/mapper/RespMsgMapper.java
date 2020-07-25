package com.hyd.resultdeal.mapper;

import com.hyd.resultdeal.domain.MessageDO;

import java.util.List;

/**
 * Created by xieshuai on 2020/7/2 上午 11:34
 */
public interface RespMsgMapper {

    void save(MessageDO messageDO);

    void update(MessageDO messageDO);

    List<MessageDO> findAll();

    void delete(Integer messageId);
}
