package com.linda.o2o.dao;

import com.linda.o2o.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {
    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);
}
