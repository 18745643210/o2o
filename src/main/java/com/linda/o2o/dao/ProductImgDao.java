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

    /**
     * 删除指定商品下的所有详情图
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);

    /**
     * 根据商品id查询缩略图
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(long productId);
}
