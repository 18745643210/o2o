package com.linda.o2o.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linda.o2o.cache.JedisUtil;
import com.linda.o2o.dao.HeadLineDao;
import com.linda.o2o.entity.HeadLine;
import com.linda.o2o.service.HeadLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private JedisUtil.Strings jedisStrings;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    HeadLineDao headLineDao;

    private static Logger logger=LoggerFactory.getLogger(HeadLineServiceImpl.class);
    @Override
    @Transactional
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        //定义redis的key前缀
        String key = HLLISTKEY;
        //定义接收对象
        List<HeadLine> headLineList = null;
        //定义对象转换类
        ObjectMapper mapper = new ObjectMapper();
        //拼接出redis的key
        if(headLineCondition!=null && headLineCondition.getEnableStatus()!=null){
            key = key + "_"+headLineCondition.getEnableStatus();
        }

        //判断redis是否存在key
        if(!jedisKeys.exists(HLLISTKEY)){
            headLineList = headLineDao.queryHeadLine(headLineCondition);
            String jsonString;
            try{
                jsonString = mapper.writeValueAsString(headLineList);
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
            jedisStrings.set(key,jsonString);
        }else{
            String jsonString = jedisStrings.get(key);
            JavaType javaType =  mapper.getTypeFactory().constructParametricType(ArrayList.class,HeadLine.class);
            try {
                headLineList = mapper.readValue(jsonString,javaType);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
        return headLineList;
    }
}
