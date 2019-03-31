package com.linda.o2o.dao;

import com.linda.o2o.entity.PersonInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonInfoDao {
    /**
     * 通过用户id查找用户
     * @param userId
     * @return
     */
    PersonInfo queryPersonInfoById(Long userId);


    /**
     * 添加用户信息
     */
    int insertPersonInfo(PersonInfo personInfo);

}
