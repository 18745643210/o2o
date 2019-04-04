package com.linda.o2o.service;

import com.linda.o2o.BaseTest;
import com.linda.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Autowired
    private CacheService cacheService;
    @Test
    public void testGetArrayList(){
        List<Area> areaList = areaService.getAreaList();
        assertEquals("城南",areaList.get(0).getAreaName());
        cacheService.removeFromCache(areaService.AREALISTKEY);
    }
}
