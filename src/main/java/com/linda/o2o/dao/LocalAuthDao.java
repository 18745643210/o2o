package com.linda.o2o.dao;

import com.linda.o2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public interface LocalAuthDao {
    /**
     * 查询，做登陆
     * @param userName
     * @param password
     * @return
     */
    LocalAuth queryLocalByUserNameAndPwd(@Param("userName") String userName,
                                         @Param("password") String password);

    /**
     * 通过id查询localauth
     */
    LocalAuth queryLocalByUserId(@Param("userId") long userId);

    /**
     * 注册
     * @param localAuth
     * @return
     */
    int insertLocalAuth(LocalAuth localAuth);

    /**
     * 更新，更改密码
     * @param userId
     * @param userName
     * @param password
     * @param newPassword
     * @param lastEditTime
     * @return
     */
    int updateLocalAuth(@Param("userId") Long userId,
                        @Param("userName") String userName,
                        @Param("password") String password,
                        @Param("newPassword") String newPassword,
                        @Param("lastEditTime") Date lastEditTime);
}
