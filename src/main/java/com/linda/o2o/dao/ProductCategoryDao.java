package com.linda.o2o.dao;

import com.linda.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductCategoryDao {
    /**
     * 通过shop id查询店铺类别
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    int deleteProductCategory(@Param("shopId") long shopId, @Param("productCategoryId") long productCategoryId);

}
