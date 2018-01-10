package com.may.service.impl;

import com.may.dataobject.SellerInfo;
import com.may.respository.SellerInfoRepository;
import com.may.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private  SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }

}
