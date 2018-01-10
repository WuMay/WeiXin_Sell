package com.may.service;

import com.may.dto.OrderDTO;

public interface PushMessage {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
