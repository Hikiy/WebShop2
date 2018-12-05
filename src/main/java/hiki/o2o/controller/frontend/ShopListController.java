/**
 * 
 */
package hiki.o2o.controller.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hiki.o2o.dto.ShopExecution;
import hiki.o2o.entity.Area;
import hiki.o2o.entity.Shop;
import hiki.o2o.entity.ShopCategory;
import hiki.o2o.service.AreaService;
import hiki.o2o.service.ShopCategoryService;
import hiki.o2o.service.ShopService;
import hiki.o2o.util.HttpServletRequestUtil;

/**
 * @Hiki msi
 *
 */
@Controller
@RequestMapping("/frontend")
public class ShopListController {
	@Autowired
	private ShopCategoryService shopCategoryService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private AreaService areaService;

	/**
	 * 返回店铺列表信息，和区域信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listshopspageinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listShopsPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		List<ShopCategory> shopCategoryList = null;
		if (parentId != -1) {
			// 如果存在上级店铺类型，则查找下级店铺类型的列表
			try {
				ShopCategory parent = new ShopCategory();
				parent.setShopCategoryId(parentId);
				ShopCategory shopCategoryCondition = new ShopCategory();
				shopCategoryCondition.setParent(parent);
				shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		} else {
			// 没有上级店铺类型，则返回一级店铺类型的列表
			try {
				shopCategoryList = shopCategoryService.getShopCategoryList(null);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		modelMap.put("shopCategoryList", shopCategoryList);
		List<Area> areaList = null;
		try {
			areaList = areaService.getAreaList();
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	/**
	 * 返回指定条件下的店铺列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listshops", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listShops(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if (pageIndex > -1 && pageSize > -1) {
			// 尝试获取一级店铺类别Id
			Long parentId = HttpServletRequestUtil.getLong(request, "parentId");
			// 尝试获取耳机店铺类别Id
			Long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
			// 尝试获取区域Id
			int areaId = HttpServletRequestUtil.getInt(request, "areaId");
			// 尝试获取模糊查询的店铺名称
			String shopName = HttpServletRequestUtil.getString(request, "shopName");
			Shop shopCondition = compactShopCondition(parentId, shopCategoryId, areaId, shopName);
			ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("count", se.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageInde or pageSize");
		}
		return modelMap;
	}

	/**
	 * 将条件组合进一个Shop实体类中
	 * 
	 * @param parentId
	 * @param shopCategoryId
	 * @param areaId
	 * @param shopName
	 * @return
	 */
	private Shop compactShopCondition(Long parentId, Long shopCategoryId, int areaId, String shopName) {
		Shop shopCondition = new Shop();
		if (parentId != -1L) {
			ShopCategory childCategory = new ShopCategory();
			ShopCategory parent = new ShopCategory();
			parent.setShopCategoryId(parentId);
			childCategory.setParent(parent);
			shopCondition.setShopCategory(childCategory);
		}
		if (shopCategoryId != -1L) {
			ShopCategory shopCategory = new ShopCategory();
			shopCategory.setShopCategoryId(shopCategoryId);
			shopCondition.setShopCategory(shopCategory);
		}
		if (areaId != -1) {
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
