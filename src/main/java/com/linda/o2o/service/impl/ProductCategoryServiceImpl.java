package com.linda.o2o.service.impl;

import com.linda.o2o.dao.ProductCategoryDao;
import com.linda.o2o.dao.ProductDao;
import com.linda.o2o.dto.ProductCategoryExecution;
import com.linda.o2o.entity.ProductCategory;
import com.linda.o2o.enums.ProductCategoryStateEnum;
import com.linda.o2o.exceptions.ProductCategoryOperationException;
import com.linda.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryDao productCategoryDao;

    @Autowired
    ProductDao productDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategoryList(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);

                if (effectNum <= 0) {
                    throw new ProductCategoryOperationException("店铺商品类别添加失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error:" + e.getMessage());
            }

        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        try{
            int effectNum = productDao.updateProductCategoryToNull(productCategoryId);
            if(effectNum < 0){
                throw  new RuntimeException("商品类别更新失败");
            }
        }catch (Exception e){
            throw  new RuntimeException("删除标签失败"+e.getMessage());
        }
        try{
            int effectNum = productCategoryDao.deleteProductCategory(shopId,productCategoryId);
            if(effectNum<=0){
                throw  new ProductCategoryOperationException("店铺类别删除失败");
            }else{
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        }catch (Exception e){
            throw new ProductCategoryOperationException("deleteProductCategory error:"+e.getMessage());
        }
    }
}
