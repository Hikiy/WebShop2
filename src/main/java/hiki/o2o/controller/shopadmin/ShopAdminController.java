/**
 * 
 */
package hiki.o2o.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Hiki msi
 *
 */
@Controller
@RequestMapping(value = "shopadmin", method = RequestMethod.GET)
public class ShopAdminController {
	@RequestMapping(value = "/shopoperation")
	public String shopOperation() {
		//店铺编辑页面
		return "shop/shopoperation";
	}

	@RequestMapping(value = "/shoplist")
	public String shopList() {
		//店铺列表页面
		return "shop/shoplist";
	}

	@RequestMapping(value = "/shopmanagement")
	public String shopManagement() {
		//店铺管理页面
		return "shop/shopmanagement";
	}

	@RequestMapping(value = "/productcategorymanagement", method = RequestMethod.GET)
	public String productCategoryManagement() {
		//商品分类管理页面
		return "shop/productcategorymanagement";
	}
	@RequestMapping(value = "/productoperation")
	public String productOperation() {
		//商品操作页面
		return "shop/productoperation";
	}
}
