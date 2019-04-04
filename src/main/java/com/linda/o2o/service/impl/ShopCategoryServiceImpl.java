package com.linda.o2o.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linda.o2o.cache.JedisUtil;
import com.linda.o2o.dao.ShopCategoryDao;
import com.linda.o2o.entity.HeadLine;
import com.linda.o2o.entity.ShopCategory;
import com.linda.o2o.service.ShopCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private JedisUtil.Strings jedisStrings;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    private static Logger logger=LoggerFactory.getLogger(ShopCategoryServiceImpl.class);


    @Transactional
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        String key = SCLISTKEY;
        List<ShopCategory>shopCategoryList = null;
        ObjectMapper mapper = new ObjectMapper();
        if(shopCategoryCondition==null){
            key = key+ "_allfirstlevel";
        }else if(shopCategoryCondition!=null &&shopCategoryCondition.getParent()!=null &&
                shopCategoryCondition.getParent().getShopCategoryId()!=null){
            //若parentId非空，列出parentId下的所有子类别
            key = key +"_parent" +shopCategoryCondition.getParent().getShopCategoryId();
        }else if(shopCategoryCondition!=null){
            //列出所有子类别，不管属于哪个类
            key = key +"_allsecondlevel";
        }

        if(!jedisKeys.exists(SCLISTKEY)){
            shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
            String jsonString;
            try{
                jsonString = mapper.writeValueAsString(shopCategoryList);
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
            jedisStrings.set(key,jsonString);
        }else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType =  mapper.getTypeFactory().constructParametricType(ArrayList.class,ShopCategory.class);
            try {
                shopCategoryList = mapper.readValue(jsonString,javaType);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
        return shopCategoryList;
    }
}
