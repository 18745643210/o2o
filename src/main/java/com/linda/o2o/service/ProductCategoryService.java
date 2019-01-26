package com.linda.o2o.service;

import com.linda.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    /**
     * 查询指定店铺下所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

}
