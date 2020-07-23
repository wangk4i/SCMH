package com.hyd.subordtest.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TestRedisService {

    @Resource
    RedisTemplate<String,Object> redisTemplate;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> ops;

    private long time = 3;

    public int testRedis(){
        try {
            //此方法会先检查key是否存在，存在+1，不存在先初始化，再+1
            ops.increment("success", 1);

            return (int) ops.get("success");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return -1;
    }

    public String getRedisNum(){
        String key = null;
        try {
            DecimalFormat df=new DecimalFormat("000");
            key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            // 当前数目
            int currNum =  redisTemplate.opsForValue().increment(key).intValue();
            while (currNum >= 1000) {
                Thread.sleep(10);
                // 当前时分秒作为key
                key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                // 当前数目
                currNum =  redisTemplate.opsForValue().increment(key).intValue(); //redisTemplate.opsForValue()~ops
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }

            String numStr = df.format(currNum);
            return key+numStr;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
