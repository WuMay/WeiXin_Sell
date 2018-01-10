package com.may.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /*
    加锁
    key
    value 当前时间+超时时间
     */

    public void add(String value,String key){
        redisTemplate.opsForValue().set(value,key);
        redisTemplate.opsForHash().put("Hash","Hash","001");
        redisTemplate.opsForHash().put("Hash","Hash2","0013");
        redisTemplate.opsForSet().add("Set", "001");
        redisTemplate.opsForSet().add("Set", "002");
        redisTemplate.opsForSet().add("Set", "001");
        //redisTemplate.delete("Hash");
        redisTemplate.opsForSet().add("Set2", "001");
    }

    public boolean lock(String key,String value){
        //能成功设置锁则true,
        if (redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }
        redisTemplate.opsForSet().add("Set", "001");

        String currentValue = redisTemplate.opsForValue().get(key);
        //判断锁是否过期
        if (!StringUtils.isEmpty(currentValue)
                &&Long.parseLong(currentValue)<System.currentTimeMillis()){
            //获取上一个锁时间getAndSet设置新锁(只会让一个线程拿到锁)
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue)&&oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }
    //解锁
    public void unlock(String key,String value){
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue)&&currentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("[redis]分布式锁异常,{}",e);
        }
    }
}
