package com.linda.o2o.service;

import com.linda.o2o.dto.LocalAuthExecution;
import com.linda.o2o.entity.LocalAuth;

public interface LocalAuthService {
    /**
     * 通过账号密码获取账号信息
     * @param userName
     * @param password
     * @return
     */
    LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password);

    /**
     *通过userId获取账号信息
     * @param userId
     * @return
     */
    LocalAuth getLocalAuthByUserId(long userId);

    /**
     * 绑定微信，生成平台专属的账号
     * @param localAuth
     * @return
     * @throws RuntimeException
     */
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws RuntimeException;

    /**
     * 修改密码
     * @param userId
     * @param userName
     * @param password
     * @param newPassword
     * @return
     */
    LocalAuthExecution modifyLocalAuth(Long userId, String userName,
                                       String password, String newPassword) throws RuntimeException;



}
