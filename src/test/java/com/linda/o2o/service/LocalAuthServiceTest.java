package com.linda.o2o.service;

import com.linda.o2o.BaseTest;
import com.linda.o2o.dto.LocalAuthExecution;
import com.linda.o2o.entity.LocalAuth;
import com.linda.o2o.entity.PersonInfo;
import com.linda.o2o.enums.LocalAuthStateEnum;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class LocalAuthServiceTest extends BaseTest {
    @Autowired
    LocalAuthService localAuthService;
    @Ignore
    @Test
    public void testBindLocalAuth(){
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(3L);
        String username = "username";
        String password = "password";
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        localAuth.setPersonInfo(personInfo);
        LocalAuthExecution lae = localAuthService.bindLocalAuth(localAuth);
        assertEquals(LocalAuthStateEnum.SUCCESS.getState(),lae.getState());
        localAuth = localAuthService.getLocalAuthByUserId(personInfo.getUserId());
        System.out.println(localAuth.getUsername());
        System.out.println(localAuth.getPassword());

    }

    @Test
    public void testModifyLocalAuth(){
        long userId = 3L;
        String username ="username";
        String password = "password";
        String newpassword = "newpassword";
        LocalAuthExecution lae = localAuthService.modifyLocalAuth(userId,username,password,newpassword);
        assertEquals(LocalAuthStateEnum.SUCCESS.getState(),lae.getState());
        LocalAuth localAuth = localAuthService.getLocalAuthByUserNameAndPwd(username,newpassword);
        System.out.println(localAuth.getUsername());
    }
}
