package com.linda.o2o.service.impl;

import com.linda.o2o.dao.ProductDao;
import com.linda.o2o.dao.ProductImgDao;
import com.linda.o2o.dto.ImageHolder;
import com.linda.o2o.dto.ProductExecution;
import com.linda.o2o.entity.Product;
import com.linda.o2o.entity.ProductImg;
import com.linda.o2o.enums.ProductStateEnum;
import com.linda.o2o.exceptions.ProductOperationException;
import com.linda.o2o.service.ProductService;
import com.linda.o2o.util.ImageUtil;
import com.linda.o2o.util.PageCalculator;
import com.linda.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    @Transactional
    //1.处理缩略图，获取缩略图相对路径并赋值给product
    //2.往tb_product里写入商品信息，获取productId
    //3.结合productId批量处理商品详情图
    //4.将商品详情图列表批量插入tb_product_img中
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException {
        //空值处理
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            try {
                int effectNum = productDao.insertProduct(product);
                if (effectNum <= 0) {
                    throw new ProductOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败:" + e.toString());
            }

            //如果商品详情页不为空就添加
            if (productImgList != null && productImgList.size() > 0) {
                addProductImgList(product, productImgList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS);
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductByProductId(productId);
    }

    /**
     * 添加缩略图
     *
     * @param product
     * @param imageHolder
     * @throws UnsupportedEncodingException
     */
    private void addThumbnail(Product product, ImageHolder imageHolder) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = null;
        try {
            thumbnailAddr = ImageUtil.genarateThumbnail(imageHolder, dest);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        product.setImgAddr(thumbnailAddr);
    }


    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
        //获取图片的根路径
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        //遍历图片一次去处理，并添加到productImg实体类里
        for (ImageHolder imageHolder : productImgHolderList) {
            try {
                String imageAddr = ImageUtil.genarateNormalImg(imageHolder, dest);
                ProductImg productImg = new ProductImg();
                productImg.setImgAddr(imageAddr);
                productImg.setProductId(product.getProductId());
                productImg.setCreateTime(new Date());
                productImgList.add(productImg);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (productImgList.size() > 0) {
            try {
                int effectNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectNum <= 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建图片详情页失败：" + e.toString());
            }
        }
    }

    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgs) throws ProductOperationException {
        //判断空值
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            //给商品设置默认属性
            product.setLastEditTime(new Date());
            //若缩略图不为空并且原有缩略图不为空则删除原来的并添加
            if (thumbnail != null) {
                //先获取原来的，因为原来的有图片地址
                Product tempProduct = productDao.queryProductByProductId(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }

            //如果新存入的商品详情图，删除原来的，添加新的
            if (productImgs != null && productImgs.size() > 0) {
                deleteProductImgs(product.getProductId());
                addProductImgList(product, productImgs);
            }
            try {
                //更新商品信息
                int effectNum = productDao.updateProduct(product);
                if (effectNum <= 0) {
                    throw new ProductOperationException("商品信息更新失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new ProductOperationException("商品信息更新失败" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    private void deleteProductImgs(long productId) {
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);
    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        //页码转换为数据库的行码
        int rowIndex =PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Product> productList = productDao.queryProductList(productCondition,rowIndex,pageSize);

        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }
}
