package com.may.respository;

import com.may.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String>{
    SellerInfo findByOpenid(String openid);
}
