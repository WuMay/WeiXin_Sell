package com.may.service;

import com.may.dataobject.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    /*
    查询单个商品
    @return
     */
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
