package com.linda.o2o.service;

import com.linda.o2o.dto.ProductExecution;
import com.linda.o2o.entity.Product;
import com.linda.o2o.exceptions.ProductOperationException;

public interface ProductService {
    /**
     * 添加商品inxi以及图片处理
     * @param product
     * @return
     */
    ProductExecution addProduct(Product product) throws ProductOperationException;
}
