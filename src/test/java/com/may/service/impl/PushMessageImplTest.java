package com.may.service.impl;

import com.may.dto.OrderDTO;
import com.may.service.OrderService;
import com.may.service.PushMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageImplTest {
    @Autowired
    private PushMessage pushMessage;
    @Autowired
    private OrderService orderService;
    @Test
    public void orderStatus() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1513671454625703249");
        pushMessage.orderStatus(orderDTO);
    }

}