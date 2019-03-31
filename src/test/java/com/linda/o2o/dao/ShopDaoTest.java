package com.linda.o2o.dao;

import com.linda.o2o.BaseTest;
import com.linda.o2o.entity.Area;
import com.linda.o2o.entity.PersonInfo;
import com.linda.o2o.entity.Shop;
import com.linda.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;
    @Ignore
    @Test
    public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo owner=new PersonInfo();
        ShopCategory sc=new ShopCategory();
        Area area = new Area();
        owner.setUserId(1L);
        area.setAreaId(2L);
        sc.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(sc);
        shop.setShopName("测试店铺");
        shop.setShopDesc("mytest1");
        shop.setShopAddr("testaddr1");
        shop.setPhone("13810524526");
        shop.setShopImg("test1");
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Ignore
    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("测试描述1");
        shop.setShopAddr("测试地址1");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }

    @Ignore
    @Test
    public void testQueryById(){
        long shopId = 1L;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println(shop.getArea().getAreaName());
        System.out.println(shop.getShopCategory().getShopCategoryName());
    }

    @Test
    public void testQueryShopListAndCount(){
        Shop shopCondition = new Shop();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(3L);
        ShopCategory childCategory =new ShopCategory();
        childCategory.setParent(parentCategory);

        shopCondition.setShopCategory(childCategory);
        List<Shop> shopList = shopDao.queryShopList(shopCondition,0,5);
        System.out.println(shopList.size());
        System.out.println("大小为："+shopDao.queryShopCount(shopCondition));
    }
}
