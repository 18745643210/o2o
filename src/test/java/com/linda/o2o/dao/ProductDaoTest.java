package com.linda.o2o.dao;

import com.linda.o2o.BaseTest;
import com.linda.o2o.entity.Product;
import com.linda.o2o.entity.ProductCategory;
import com.linda.o2o.entity.Shop;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest extends BaseTest {
    @Autowired
    ProductDao productDao;
    @Ignore
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

    @Ignore
    @Test
    public void testQueryById(){
        Product p = productDao.queryProductByProductId(2L);
        System.out.println(p.getProductName());
    }

    @Ignore
    @Test
    public void testUpdateProduct(){
        Product product = productDao.queryProductByProductId(2L);

        product.setProductName("大茶杯");
        product.setProductDesc("贵");

        int effectNum = productDao.updateProduct(product);

        assertEquals(1,effectNum);


    }
   @Ignore
   @Test
   public void testQueryProductList (){
        Product productCondition = new Product();
        //预期查出来3个
       List<Product> productList = productDao.queryProductList(productCondition,0,3);
       assertEquals(3,productList.size());
       //查询名称为测试的商品总数
       int count = productDao.queryProductCount(productCondition);
       assertEquals(8,count);
       //模糊查询
       productCondition.setProductName("测试");
       count = productDao.queryProductCount(productCondition);
       assertEquals(6,count);

       productList = productDao.queryProductList(productCondition,0,3);
       assertEquals(3,productList.size());
   }
   @Ignore
   @Test
    public void testUpdateProductCategoryToNull(){
        int effectNum = productDao.updateProductCategoryToNull(2);
        assertEquals(1,effectNum);
   }

}
