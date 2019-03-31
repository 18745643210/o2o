package com.linda.o2o.dao;

import com.linda.o2o.BaseTest;
import com.linda.o2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeadLineDaoTest extends BaseTest {
    @Autowired
    HeadLineDao headLineDao;

    @Test
    public void testQueryHeadLine(){
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        List<HeadLine> headLineList = headLineDao.queryHeadLine(headLineCondition);
        assertEquals (1,headLineList.size());
    }
}
