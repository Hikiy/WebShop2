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

import hiki.o2o.dto.ProductExecution;
import hiki.o2o.entity.Product;
import hiki.o2o.entity.ProductCategory;
import hiki.o2o.entity.Shop;
import hiki.o2o.service.ProductCategoryService;
import hiki.o2o.service.ProductService;
import hiki.o2o.service.ShopService;
import hiki.o2o.util.HttpServletRequestUtil;

/**
 * @Hiki msi
 *
 */
@Controller
@RequestMapping("/frontend")
public class ShopDetailController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;

	/**
	 * 获取店铺信息和商品分类信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listshopdetailpageinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listShopDetailPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取前端shopId
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		Shop shop = null;
		List<ProductCategory> productCategoryList = null;
		try {
			// 获取店铺信息
			shop = shopService.getByShopId(shopId);
			// 获取商品分类信息
			productCategoryList = productCategoryService.getProductCategoryList(shopId);
			modelMap.put("shop", shop);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}

	/**
	 * 根据查询条件分页列出商品
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listproductsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductsByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if ((pageIndex > -1) && (pageSize > -1) && (shopId > -1)) {
			long productCategoryId = HttpServletRequestUtil.getLong(request,
					"productCategoryId");
			String productName = HttpServletRequestUtil.getString(request,
					"productName");
			Product productCondition = compactProductCondition(shopId,
					productCategoryId, productName);
			ProductExecution pe = productService.getProductList(
					productCondition, pageIndex, pageSize);
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}
//	public Map<String, Object> listProductByShop(HttpServletRequest request) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
//		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
//		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
//		if (pageIndex > -1 && pageSize > -1 && shopId > -1) {
//			long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
//			String productName = HttpServletRequestUtil.getString(request, "productName");
//			Product productCondition = compactProductCondition(shopId, productCategoryId, productName);
//			ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
//			modelMap.put("productList", pe.getProductList());
//			modelMap.put("count", pe.getCount());
//			modelMap.put("success", true);
//		} else {
//			modelMap.put("success", false);
//			modelMap.put("success", "empty pageIndex or pageSize or shopId");
//		}
//		return modelMap;
//	}

	/**
	 * 组合商品查询条件
	 * 
	 * @param productCategoryId
	 * @param productName
	 * @return
	 */
	private Product compactProductCondition(long shopId, long productCategoryId, String productName) {
		Shop shop = new Shop();
		shop.setShopId(shopId);
		Product productCondition = new Product();
		//设置shopId
		productCondition.setShop(shop);
		if (productCategoryId != -1L) {
			//包含商品分类ID查询
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		if (productName != null) {
			//包含商品名字查询
			productCondition.setProductName(productName);
		}
		//判断是否为上架商品
		productCondition.setEnableStatus(1);
		return productCondition;
	}
}
