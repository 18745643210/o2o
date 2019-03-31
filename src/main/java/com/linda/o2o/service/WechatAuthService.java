package com.linda.o2o.service;

import com.linda.o2o.dto.WechatAuthExecution;
import com.linda.o2o.entity.WechatAuth;

public interface WechatAuthService {
    /**
     * 根据openId查找微信账号
     * @param openId
     * @return
     */
    WechatAuth getWechatAuthByOpenId(String openId);

    /**
     * 注册本平台的微信账号
     */
    WechatAuthExecution register(WechatAuth wechatAuth) throws  RuntimeException;
}
