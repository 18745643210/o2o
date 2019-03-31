package com.linda.o2o.service;

import com.linda.o2o.BaseTest;
import com.linda.o2o.dto.WechatAuthExecution;
import com.linda.o2o.entity.PersonInfo;
import com.linda.o2o.entity.WechatAuth;
import com.linda.o2o.enums.WechatAuthStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class WechatAuthServiceTest extends BaseTest {
    @Autowired
    WechatAuthService wechatAuthService;

    @Test
    public void testResgister(){
        //新增一条微信账号
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        String openId = "22323";
        personInfo.setCreateTime(new Date());
        personInfo.setName("测试一下");
        personInfo.setUserType(1);
        wechatAuth.setPersonInfo(personInfo);
        wechatAuth.setOpenId(openId);
        wechatAuth.setCreateTime(new Date());
        WechatAuthExecution wechatAuthExecution = wechatAuthService.register(wechatAuth);
        assertEquals(WechatAuthStateEnum.SUCCESS.getState(),wechatAuthExecution.getState());

        wechatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
        System.out.println(wechatAuth.getPersonInfo().getName());
    }
}
