package com.may.service.impl;

import com.may.dataobject.OrderDetail;
import com.may.dto.OrderDTO;
import com.may.enums.OrderStatusEnum;
import com.may.enums.PayStatusEnum;
import com.may.respository.OrderMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    private final String BUYER_OPENID = "100101";
    @Test
    public void create() throws Exception {
        //订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("廖师兄");
        orderDTO.setBuyerAddress("幕课网");
        orderDTO.setBuyerPhone("123456789012");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

//购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123457");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123456");
        o2.setProductQuantity(2);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result={}", result);
        //Assert.assertNotNull(result);
    }

    private final String ORDER_ID = "1513671307511742612";
    @Test
    public void findOne() throws Exception {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}", result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }
    //订单查询订单详情
    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> list = orderService.findList(ORDER_ID,pageRequest);
        Assert.assertNotEquals(0,list.getSize());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO result = orderService.findOne(ORDER_ID);
        OrderDTO resultCancel = orderService.cancel(result);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),resultCancel.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO result = orderService.findOne(ORDER_ID);
        OrderDTO finish = orderService.finish(result);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),finish.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDTO one = orderService.findOne(ORDER_ID);
        OrderDTO paid = orderService.paid(one);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),paid.getPayStatus());
    }

    @Test
    public void findList1() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> list = orderService.findList(pageRequest);
        Assert.assertNotEquals(0,list.getSize());
    }

}