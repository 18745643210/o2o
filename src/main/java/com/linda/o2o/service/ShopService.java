package com.linda.o2o.service;

import com.linda.o2o.dto.ImageHolder;
import com.linda.o2o.dto.ShopExecution;
import com.linda.o2o.entity.Shop;
import com.linda.o2o.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    /**
     * 根据shopCondition分页返回相应列表数据
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
    /**
     * 注册店铺信息，包括图片处理
     * @param shop
     * @return
     * @throws ShopOperationException
     */
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;


    /**
     * 根据shopId获取店铺信息
     */
    Shop getByShopId(long shopId);

    /**
     * 更新店铺信息，包括对店铺处理
     */

    ShopExecution modifyShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException;
}
