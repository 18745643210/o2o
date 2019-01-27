package com.linda.o2o.service;

import com.linda.o2o.dto.ProductCategoryExecution;
import com.linda.o2o.entity.ProductCategory;
import com.linda.o2o.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    /**
     * 查询指定店铺下所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    /**
     * 批量插入商品类别
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategoryList(List<ProductCategory> productCategoryList)
        throws ProductCategoryOperationException;

    /**
     * 将此标签类别下的商品的类别标签置为空，再删除该商品
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId)throws ProductCategoryOperationException;
}
