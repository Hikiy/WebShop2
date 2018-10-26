/**
 * 
 */
package hiki.o2o.service;

import java.util.List;

import hiki.o2o.entity.ShopCategory;

/**
 * @Hiki msi
 *
 */
public interface ShopCategoryService {
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
