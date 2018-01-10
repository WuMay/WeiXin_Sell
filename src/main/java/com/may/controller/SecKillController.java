package com.may.controller;

import com.may.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/skill")
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    @RequestMapping(value = "/query", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String seckill(@RequestParam String productId) {
        secKillService.orderProductMockDiffUser(productId);
        return secKillService.querySecKillProductInfo(productId);
    }
    @RequestMapping("/see")
    public @ResponseBody
    String see(@RequestParam String productId){
        return secKillService.findProductId(productId);
    }
    @RequestMapping("/test")
    public @ResponseBody  String tes(){

        int i = 1;
        i = i + 1;
        return String.valueOf(i);
    }
}
