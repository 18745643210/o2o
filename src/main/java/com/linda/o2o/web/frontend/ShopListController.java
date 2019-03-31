package com.linda.o2o.web.frontend;

import com.linda.o2o.dto.ShopExecution;
import com.linda.o2o.entity.Area;
import com.linda.o2o.entity.Shop;
import com.linda.o2o.entity.ShopCategory;
import com.linda.o2o.service.AreaService;
import com.linda.o2o.service.ShopCategoryService;
import com.linda.o2o.service.ShopService;
import com.linda.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class ShopListController {
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopService shopService;
    @RequestMapping(value = "/listshoppageinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listShopPageInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        //试着从前端请求中获取parentId
        long parentId = HttpServletRequestUtil.getLong(request,"parentId");

        List<ShopCategory> shopCategoryList = null;
        if(parentId != -1){
            //如果parentId存在，则取出该parentId下面的二级shopCategory列表
            try{
                ShopCategory shopCategoryCondition = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId(parentId);
                shopCategoryCondition.setParent(parent);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }else{
            //如果parentId不存在，则取出所有一级shopCategory
            try{
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }
        modelMap.put("shopCategoryList",shopCategoryList);

        List<Area> areaList = null;
        try{
            areaList = areaService.getAreaList();
            modelMap.put("success",true);
            modelMap.put("areaList",areaList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return  modelMap;
    }


    @RequestMapping(value = "/listshops",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listShops(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        //获取页码
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        //获取每页显示的数据条数
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //非空判断
        if ((pageIndex > -1) && (pageSize > -1)) {
            //试着获取一级类别id
            long parentId = HttpServletRequestUtil.getLong(request, "parentId");
            //试着获取二级类别id
            long shopCategoryId = HttpServletRequestUtil.getLong(request,
                    "shopCategoryId");
            //试着获取区域id
            long areaId = HttpServletRequestUtil.getLong(request, "areaId");
            //试着获取模糊查询的名字
            String shopName = HttpServletRequestUtil.getString(request,
                    "shopName");
            //创建组合查询的查询条件
            Shop shopCondition = compactShopCondition4Search(parentId,
                    shopCategoryId, areaId, shopName);
            ShopExecution se = shopService.getShopList(shopCondition,
                    pageIndex, pageSize);
            modelMap.put("shopList", se.getShopList());
            modelMap.put("count", se.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex");
        }

        return modelMap;

    }

    private Shop compactShopCondition4Search(long parentId,
                                             long shopCategoryId, long areaId, String shopName) {
        Shop shopCondition = new Shop();
        if (parentId != -1L) {
            ShopCategory parentCategory = new ShopCategory();
            parentCategory.setShopCategoryId(parentId);
            ShopCategory childCategory = new ShopCategory();
            childCategory.setParent(parentCategory);
            shopCondition.setShopCategory(childCategory);
        }
        if (shopCategoryId != -1L) {
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondition.setShopCategory(shopCategory);
        }
        if (areaId != -1L) {
            Area area = new Area();
            area.setAreaId(areaId);
            shopCondition.setArea(area);
        }

        if (shopName != null) {
            shopCondition.setShopName(shopName);
        }
        shopCondition.setEnableStatus(1);
        return shopCondition;
    }
}
