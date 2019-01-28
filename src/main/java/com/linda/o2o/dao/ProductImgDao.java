package com.linda.o2o.dao;

import com.linda.o2o.entity.ProductImg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImgDao {
    /**
     * 批量添加商品详情图片
     */
    int batchInsertProductImg(List<ProductImg> productImgList);
}
