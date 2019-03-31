package com.linda.o2o.service;

import com.linda.o2o.dto.ImageHolder;
import com.linda.o2o.dto.ProductExecution;
import com.linda.o2o.entity.Product;
import com.linda.o2o.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {
    /**
     *
     * @param product
     * @param thumbnail
     * @param productImgs
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgs) throws ProductOperationException;

    /**
     * 获取商品
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 更新商品
     * @param product
     * @param thumbnail
     * @param productImgs
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgs) throws  ProductOperationException;

    ProductExecution getProductList(Product productCondition,int pageIndex, int pageSize);


}
