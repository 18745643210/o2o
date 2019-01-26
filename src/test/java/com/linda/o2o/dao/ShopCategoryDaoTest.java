package com.linda.o2o.dao;

import com.linda.o2o.BaseTest;
import com.linda.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    ShopCategoryDao shopCategoryDao;
    @Test
    public void testQueryShopCategory(){
        ShopCategory testShopCategory = new ShopCategory();
        ShopCategory parentShopCategory =new ShopCategory();
        parentShopCategory.setShopCategoryId(1L);
        testShopCategory.setParent(parentShopCategory);
        List<ShopCategory> shopCategoryList=shopCategoryDao.queryShopCategory(testShopCategory);
        assertEquals(1,shopCategoryList.size());
    }
}
