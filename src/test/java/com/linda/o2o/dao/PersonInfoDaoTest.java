package com.linda.o2o.dao;

import com.linda.o2o.BaseTest;
import com.linda.o2o.entity.PersonInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonInfoDaoTest extends BaseTest {
    @Autowired
    PersonInfoDao personInfoDao;
    @Test
    public void testAInsertPersonInfo ()throws  Exception{
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("测试2");
        personInfo.setGender("女");
        personInfo.setUserType(1);
        personInfo.setCreateTime(new Date());
        personInfo.setLastEditTime(new Date());
        personInfo.setEnableStatus(1);
        assertEquals(1,personInfoDao.insertPersonInfo(personInfo));
    }

    @Test
    public void testBQueryPersonInfoById() throws Exception{
        long userId =3;

        PersonInfo personInfo = personInfoDao.queryPersonInfoById(userId);
        System.out.println(personInfo.getName());
    }
}
