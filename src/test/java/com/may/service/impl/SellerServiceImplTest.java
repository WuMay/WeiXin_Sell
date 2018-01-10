package com.may.service.impl;

import com.may.dataobject.SellerInfo;
import com.may.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerServiceImplTest {
    private static final String openid = null;
    @Autowired
    private SellerService sellerService;
    @Test
    public void findSellerInfoByOpenid() throws Exception {
        SellerInfo sellerInfoByOpenid = sellerService.findSellerInfoByOpenid(openid);
        Assert.assertNotNull(sellerInfoByOpenid);
    }
//Hibernate: select sellerinfo0_.seller_id as seller_i1_4_, sellerinfo0_.openid as openid2_4_, sellerinfo0_.password as password3_4_, sellerinfo0_.username as username4_4_ from seller_info sellerinfo0_ where sellerinfo0_.openid=?

}