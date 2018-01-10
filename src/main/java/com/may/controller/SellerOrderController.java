package com.may.controller;

import com.may.dto.OrderDTO;
import com.may.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page
                              ,  @RequestParam(value = "size",defaultValue = "3")Integer size,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("order/list", map);
    }

    @GetMapping("/cancel")
public ModelAndView cancel(@RequestParam("orderId")String orderId,
                           Map<String,Object> map){
    OrderDTO orderDTO = null;
    try {
        orderDTO = orderService.findOne(orderId);
        OrderDTO cancel = orderService.cancel(orderDTO);
    } catch (Exception e) {
        log.error("[卖家订单取消] 查询不到订单");
        map.put("msg", e.getMessage());
        map.put("url", "/sell/sell/order/list");
        return new ModelAndView("common/error", map);
    }

    return new ModelAndView("/sell/seller/order/list");
}
}
