package com.linda.o2o.dao;

import com.linda.o2o.BaseTest;
import com.linda.o2o.entity.ProductCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    ProductCategoryDao productCategoryDao;

    @Ignore
    @Test
    public void testQueryByShopId() throws Exception {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("该店铺自定义类别数为:" + productCategoryList.size());

    }

    @Ignore
    @Test
    public void testBatchInsertProductCategory() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("商品类别1");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(1L);
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("商品类别2");
        productCategory2.setPriority(2);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(1L);
        List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);
        int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2, effectNum);
    }
    @Ignore
    @Test
    public void testDeleteProductCategory() {
        int effectNum = productCategoryDao.deleteProductCategory(1L, 8L);

        assertEquals(1, effectNum);
    }
}
