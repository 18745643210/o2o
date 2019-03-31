package com.linda.o2o.dao;

import com.linda.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);


    /**
     * 根据productId获取商品
     */
    Product queryProductByProductId(Long productId);

    /**
     * 更新商品信息
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     *查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺Id,商品类别
     * @param productCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition,@Param("rowIndex") int rowIndex,
                                   @Param("pageSize") int pageSize);

    /**
     * 查询对应条件的商品总数
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * 删除某个商品标签时，批量将该商品标签下的商品的商品标签id置空
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNull(long productCategoryId);

}
