package com.linda.o2o.dao;

import com.linda.o2o.BaseTest;
import com.linda.o2o.entity.LocalAuth;
import com.linda.o2o.entity.PersonInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class LocalAuthDaoTest extends BaseTest {
    @Autowired
    LocalAuthDao localAuthDao;
    private static final String username="testusername";
    private static final String password="testpassword";
    @Ignore
    @Test
    public void testInsertLocalAuth(){
        //新增账号信息
        LocalAuth localAuth = new LocalAuth();
        PersonInfo p = new PersonInfo();
        p.setUserId(1L);
        localAuth.setPersonInfo(p);
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        localAuth.setCreateTime(new Date());
        int effectNum = localAuthDao.insertLocalAuth(localAuth);
        assertEquals(effectNum,1);
    }
    @Ignore
    @Test
    public void testQueryByUserNameAndPassword(){
        LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(username,password);

        System.out.println(localAuth.getUsername() + localAuth.getPassword());
    }
    @Ignore
    @Test
    public void testQueryByUserId(){
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
        System.out.println(localAuth.getUsername() + localAuth.getPassword());
    }

    @Test
    public void testUpdate(){
        Date now = new Date();
        int effectNum = localAuthDao.updateLocalAuth(1L,username,password,password+"new",now);
        assertEquals(1,effectNum);
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
        System.out.println(localAuth.getPassword());
    }


}
