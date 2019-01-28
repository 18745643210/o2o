package com.linda.o2o.dao;

import com.linda.o2o.BaseTest;
import com.linda.o2o.entity.Product;
import com.linda.o2o.entity.ProductCategory;
import com.linda.o2o.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest extends BaseTest {
    @Autowired
    ProductDao productDao;

    @Test
    public void testInsertProduct(){
        System.out.println(productDao);
        Product p = new Product();
        p.setProductName("卡布奇诺");
        p.setProductDesc("好喝不贵");
        p.setImgAddr("图片地址");
        p.setNormalPrice("10");
        p.setPromotionPrice("20");
        p.setCreateTime(new Date());
        p.setLastEditTime(new Date());
        p.setPriority(1);
        p.setEnableStatus(1);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(1L);
        p.setProductCategory(pc);
        Shop s = new Shop();
        s.setShopId(1L);
        p.setShop(s);
        int effectNum = productDao.insertProduct(p);
        assertEquals(1,effectNum);
    }
}
