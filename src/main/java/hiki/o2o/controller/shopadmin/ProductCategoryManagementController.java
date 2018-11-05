/**
 * 
 */
package hiki.o2o.controller.shopadmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hiki.o2o.dto.Result;
import hiki.o2o.entity.ProductCategory;
import hiki.o2o.entity.Shop;
import hiki.o2o.service.ProductCategoryService;

/**
 * @Hiki msi
 *
 */

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
	@Autowired
	private ProductCategoryService productCategoryService;

	private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
		// To be removed
		Shop shop = new Shop();
		shop.setShopId(2L);
		return null;
	}
}
