package com.may.service.impl;

import com.may.dto.OrderDTO;
import com.may.service.PushMessage;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushMessageImpl implements PushMessage {
    @Autowired
    private WxMpService wxMpService;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId("yuc4FQSwHKhXS-Ij5ViJ_QGJpE-3qz4Z2P8TMk6rs88");
        templateMessage.setToUser("oW6Ch08tWOQPkSYvKcOdtKNX3-L0");
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "记得收货"),
        new WxMpTemplateData("keyword1", "微信点餐"),
        new WxMpTemplateData("keyword2", "1886569225"),
        new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
        new WxMpTemplateData("keyword4", "麻辣香锅"),
        new WxMpTemplateData("keyword5", "$" + orderDTO.getOrderAmount()),
        new WxMpTemplateData("remark", "欢迎下次光临")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("[微信模板]发送失败,{}",e);
        }
    }
}
