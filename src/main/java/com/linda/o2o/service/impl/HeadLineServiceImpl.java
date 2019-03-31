package com.linda.o2o.service.impl;

import com.linda.o2o.dao.HeadLineDao;
import com.linda.o2o.entity.HeadLine;
import com.linda.o2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    HeadLineDao headLineDao;
    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        return headLineDao.queryHeadLine(headLineCondition);
    }
}
