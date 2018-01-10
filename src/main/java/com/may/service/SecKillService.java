package com.may.service;

public interface SecKillService {
    public String querySecKillProductInfo(String productId);
    public void orderProductMockDiffUser(String productId);

    String findProductId(String productId);
}
