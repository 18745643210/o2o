package com.linda.o2o.dao;

import com.linda.o2o.BaseTest;
import com.linda.o2o.entity.PersonInfo;
import com.linda.o2o.entity.WechatAuth;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WechatAuthDaoTest extends BaseTest {
    @Autowired
    WeChatAuthDao weChatAuthDao;

    @Test
    public void testAInsertWeChatAuth() throws Exception{
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(3L);
        wechatAuth.setCreateTime(new Date());
        wechatAuth.setOpenId("1122");
        wechatAuth.setPersonInfo(personInfo);
        int effectNum = weChatAuthDao.insertWechatAuth(wechatAuth);
        assertEquals(1,effectNum);
    }

    @Test
    public void testBQueryWechatById(){
        String openId = "1122";
        WechatAuth wechatAuth = weChatAuthDao.queryWechatInfoByOpenId(openId);
        System.out.println(wechatAuth.getPersonInfo().getName());
    }
}
