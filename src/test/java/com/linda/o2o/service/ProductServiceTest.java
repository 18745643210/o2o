package com.linda.o2o.service;

import com.linda.o2o.BaseTest;
import com.linda.o2o.dto.ImageHolder;
import com.linda.o2o.dto.ProductExecution;
import com.linda.o2o.entity.Product;
import com.linda.o2o.entity.ProductCategory;
import com.linda.o2o.entity.Shop;
import com.linda.o2o.enums.ProductStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;
    @Test
    public void testAddProduct() throws FileNotFoundException {
        Product p = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(1L);
        p.setProductCategory(pc);
        p.setShop(shop);
        p.setProductName("测试商品");
        p.setProductDesc("测试");
        p.setPriority(10);
        p.setCreateTime(new Date());
        p.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        //创建缩略图文件流
        File thumbnailFile = new File("F:/test.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder imageHolder = new ImageHolder(thumbnailFile.getName(),is);

        //创建两个商品详情图片
        File thumbnailFile1 = new File("F:/test.jpg");
        InputStream is1 = new FileInputStream(thumbnailFile1);
        File thumbnailFile2 = new File("F:/test0.jpg");
        InputStream is2 = new FileInputStream(thumbnailFile2);
        ImageHolder imageHolder1 = new ImageHolder(thumbnailFile1.getName(),is1);
        ImageHolder imageHolder2 = new ImageHolder(thumbnailFile2.getName(),is2);
        List<ImageHolder>imageHolderList = new ArrayList<ImageHolder>();
        imageHolderList.add(imageHolder1);
        imageHolderList.add(imageHolder2);


        ProductExecution pe = productService.addProduct(p,imageHolder,imageHolderList);
        assertEquals(pe.getState(),ProductStateEnum.SUCCESS.getState());



    }
 }
