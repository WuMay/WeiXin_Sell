package com.may.service.impl;

import com.may.service.SecKillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SecKillServiceImplTest {
    @Autowired
    private SecKillService secKillService;
    @Test
    public void querySecKillProductInfo() throws Exception {
        secKillService.querySecKillProductInfo("123456");
    }

}