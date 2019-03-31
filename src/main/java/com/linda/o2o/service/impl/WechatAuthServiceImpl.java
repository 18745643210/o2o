package com.linda.o2o.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.linda.o2o.dao.PersonInfoDao;
import com.linda.o2o.dao.WeChatAuthDao;
import com.linda.o2o.dto.WechatAuthExecution;
import com.linda.o2o.entity.PersonInfo;
import com.linda.o2o.entity.WechatAuth;
import com.linda.o2o.enums.WechatAuthStateEnum;
import com.linda.o2o.service.WechatAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {
    @Autowired
    PersonInfoDao personInfoDao;
    @Autowired
    WeChatAuthDao weChatAuthDao;
    private static Logger log = LoggerFactory
            .getLogger(WechatAuthServiceImpl.class);

    public WechatAuthServiceImpl() {
        super();
    }

    @Override
    public WechatAuth getWechatAuthByOpenId(String openId) {
        return weChatAuthDao.queryWechatInfoByOpenId(openId);
    }

    @Override
    @Transactional
    public WechatAuthExecution register(WechatAuth wechatAuth) throws RuntimeException {
        //空值判断
        if (wechatAuth == null || wechatAuth.getOpenId() == null) {
            return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
        }

        try {//设置创建时间
            wechatAuth.setCreateTime(new Date());
            //如果wechatauth里带着用户信息并且用户id为空，则认为该用户第一次使用平台，且通过微信登陆
            //自动创建用户信息
            if (wechatAuth.getPersonInfo() != null && wechatAuth.getPersonInfo().getUserId() == null) {
                try {
                    wechatAuth.getPersonInfo().setCreateTime(new Date());
                    wechatAuth.getPersonInfo().setEnableStatus(1);
                    PersonInfo personInfo = wechatAuth.getPersonInfo();
                    int effectNum = personInfoDao.insertPersonInfo(personInfo);
                    wechatAuth.setPersonInfo(personInfo);
                    if (effectNum <= 0) {
                        throw new RuntimeException("用户信息添加失败");
                    }
                } catch (Exception e) {
                    log.error("insertPersonInfo error：" + e.toString());
                    throw new RuntimeException("insertPersonInfo error：" + e.toString());
                }
            }
            //创建属于本平台的微信账号
            int effectNum = weChatAuthDao.insertWechatAuth(wechatAuth);
            if (effectNum <= 0) {
                throw new RuntimeException("账号创建失败");
            } else {
                return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);
            }

        } catch (Exception e) {
            log.error("insertPersonInfo error：" + e.toString());
            throw new RuntimeException("insertPersonInfo error：" + e.toString());
        }

    }
}
