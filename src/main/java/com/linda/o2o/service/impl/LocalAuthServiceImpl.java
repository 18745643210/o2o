package com.linda.o2o.service.impl;

import com.linda.o2o.dao.LocalAuthDao;
import com.linda.o2o.dto.LocalAuthExecution;
import com.linda.o2o.entity.LocalAuth;
import com.linda.o2o.enums.LocalAuthStateEnum;
import com.linda.o2o.service.LocalAuthService;
import com.linda.o2o.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {
    @Autowired
    LocalAuthDao localAuthDao;

    public LocalAuthServiceImpl() {
        super();
    }

    @Override
    public LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password) {
        return localAuthDao.queryLocalByUserNameAndPwd(userName, MD5.getMd5(password));
    }


    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return localAuthDao.queryLocalByUserId(userId);
    }

    @Transactional
    @Override
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws RuntimeException {
        //空值判断
        if (localAuth == null || localAuth.getPassword() == null
                || localAuth.getUsername() == null
                || localAuth.getPersonInfo() == null
                || localAuth.getPersonInfo().getUserId() == null) {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
        //查询此用户是否绑定过平台账号
        LocalAuth tempAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
        if (tempAuth != null) {
            return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
        }
        try {
            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(new Date());
            localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
            int effectNum = localAuthDao.insertLocalAuth(localAuth);
            if (effectNum <= 0) {
                throw new RuntimeException("账号绑定失败");
            } else {
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, localAuth);
            }
        } catch (Exception e) {
            throw new RuntimeException("insert error" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public LocalAuthExecution modifyLocalAuth(Long userId, String userName, String password, String newPassword) throws RuntimeException {
        if (userId != null && userName != null && password != null
                && newPassword != null && !password.equals(newPassword)) {
            try {
                int effectedNum = localAuthDao.updateLocalAuth(userId,
                        userName, MD5.getMd5(password),
                        MD5.getMd5(newPassword), new Date());
                if (effectedNum <= 0) {
                    throw new RuntimeException("更新密码失败");
                }
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new RuntimeException("更新密码失败:" + e.toString());
            }

        } else {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
    }
}
