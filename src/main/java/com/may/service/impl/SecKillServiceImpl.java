package com.may.service.impl;

import com.may.exception.SellException;
import com.may.service.RedisLock;
import com.may.service.SecKillService;
import com.may.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class SecKillServiceImpl implements SecKillService {
    @Autowired
    private RedisLock redisLock;
    @Autowired
    private RedisTemplate redisTemplate;
    //设置商品
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;
    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 10000);
        stock.put("123456", 10000);
    }
    @Override
    public String findProductId(String productId) {
        return queryMap(productId);
    }
    private String queryMap(String productId){
        return "国庆活动皮蛋粥" + products.get(productId) + "还有" + stock.get(productId) +
                "份,成功下单人数:" + orders.size() + "人";
    }

    @Override
    public String querySecKillProductInfo(String productId) {return this.queryMap(productId);
    }

    private static final int TIMEOUT = 10000;

    @Override
    public   void orderProductMockDiffUser(String productId) {
        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId, String.valueOf(time))) {
            //加锁失败
            throw new SellException(101, "人也太多了");
        }
        //1.查询该商品库存,为0则活动结束
        Integer stckNum = stock.get(productId);
        if (stckNum==0){
            throw new SellException(100, "活动结束");
        }else {
            //下单模拟
            orders.put(KeyUtil.genUniqueKey(), productId);

            //减少库存
            stckNum = stckNum - 1;
            try {
                redisTemplate.opsForHash().put(productId,orders.values(),"person_id");
                Thread.sleep( 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stckNum);
        }
        //解锁
        redisLock.unlock(productId,String.valueOf(time));
    }


}
