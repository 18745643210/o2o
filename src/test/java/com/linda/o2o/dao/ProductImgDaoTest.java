package com.linda.o2o.dao;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.linda.o2o.BaseTest;
import com.linda.o2o.entity.ProductImg;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductImgDaoTest extends BaseTest {
    @Autowired
    ProductImgDao productImgDao;
    @Ignore
    @Test
    public void testBatchInsertProductImg() throws Exception {
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(2L);


        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setImgDesc("测试图片2");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(2L);

        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        System.out.println(productImgDao);

        int effectNum = productImgDao.batchInsertProductImg(productImgList);

        assertEquals(2, effectNum);


    }
    @Ignore
    @Test
    public void testDeleteProductImgByProductId() throws Exception{
        long productId = 2l;
        int effectNum = productImgDao.deleteProductImgByProductId(productId);
        assertEquals(2,effectNum);
    }
    @Ignore
    @Test
    public void testQueryProductImgList(){
        long productId = 4L;
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        assertEquals(2,productImgList.size());

    }
}
