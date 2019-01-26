package com.linda.o2o.service;

import com.linda.o2o.BaseTest;
import com.linda.o2o.dao.ShopDao;
import com.linda.o2o.dto.ShopExecution;
import com.linda.o2o.entity.Area;
import com.linda.o2o.entity.PersonInfo;
import com.linda.o2o.entity.Shop;
import com.linda.o2o.entity.ShopCategory;
import com.linda.o2o.enums.ShopStateEnum;
import com.linda.o2o.exceptions.ShopOperationException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;
    @Ignore
    @Test
    public void testModifyShop() throws ShopOperationException,FileNotFoundException{
        Shop shop = new Shop();
        shop.setShopId(42L);
        shop.setShopName("新修改的店铺名称2");
        File shopImg = new File("D:/timg.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution=shopService.modifyShop(shop,is,"timg.jpg");
        System.out.println("新的图片地址"+shopExecution.getShop().getShopImg());
    }
    @Ignore
    @Test
    public void testAddShop() throws IOException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        ShopCategory sc = new ShopCategory();
        Area area = new Area();
        owner.setUserId(1L);
        area.setAreaId(2);
        sc.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(sc);
        shop.setShopName("测试店铺3");
        shop.setShopDesc("mytest12");
        shop.setShopAddr("testaddr12");
        shop.setPhone("138105245262");
        shop.setShopImg("test1");
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("D:/timg.jpg");
        InputStream is = new FileInputStream(shopImg);
        System.out.println(shopImg.getName());
        ShopExecution se = shopService.addShop(shop, is,shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
    }
    @Ignore
    @Test
    public void testGetShopList(){
        Shop shopcondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(2L);
        Area area =new Area();
        area.setAreaId(2);
        shopcondition.setArea(area);
        shopcondition.setShopCategory(sc);

        shopcondition.setOwner(owner);
        ShopExecution shopList = shopService.getShopList(shopcondition, 1, 3);
        List<Shop> list = shopList.getShopList();
        System.out.println(list.size());
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getShopName());
        }
    }
}
