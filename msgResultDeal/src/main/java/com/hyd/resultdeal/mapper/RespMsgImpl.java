package com.hyd.resultdeal.mapper;

import com.hyd.resultdeal.domain.ReturnMsgDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Created by xieshuai on 2020/7/2 上午 11:40
 */
@Repository
public class RespMsgImpl implements RespMsgMapper {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存单条数据
     * @param returnMsgDO
     */
    @Override
    public void save(ReturnMsgDO returnMsgDO) {
        mongoTemplate.save(returnMsgDO);
    }

    /**
     * 更新单条数据
     * @param returnMsgDO
     */
    @Override
    public void update(ReturnMsgDO returnMsgDO) {
        //设置修改语句
        Query query = new Query(Criteria.where("xmlId").is(returnMsgDO.getXmlNam()));

        //修改的内容
        Update update = new Update();
        // xmlStream -> msgBody
        update.set("msgBody", returnMsgDO.getMsgBody());

        mongoTemplate.updateFirst(query, update, ReturnMsgDO.class);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public List<ReturnMsgDO> findAll() {
        return mongoTemplate.findAll(ReturnMsgDO.class) ;
    }

    /**
     * 删除单条数据
     * @param messageId
     */
    @Override
    public void delete(Integer messageId) {
        //根据实体删除
        mongoTemplate.remove(Objects.requireNonNull(mongoTemplate.findById(messageId, ReturnMsgDO.class)));
    }
}
