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
	/**
	 * 根据条件查询店铺分类列表
	 * @param shopCategoryCondition
	 * @return
	 */
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
