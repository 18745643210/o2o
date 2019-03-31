package com.linda.o2o.dao;

import com.linda.o2o.entity.WechatAuth;
import org.springframework.stereotype.Repository;

@Repository
public interface WeChatAuthDao {
    /**
     * 通过openId查询对应平台的微信账号
     * @param openId
     * @return
     */
    WechatAuth queryWechatInfoByOpenId(String openId);

    /**
     * 通过平台添加微信账号
     * @param wechatAuth
     * @return
     */
    int insertWechatAuth(WechatAuth wechatAuth);
}
