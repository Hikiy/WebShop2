/**
 * 
 */
package hiki.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hiki.o2o.dao.ShopCategoryDao;
import hiki.o2o.entity.ShopCategory;
import hiki.o2o.service.ShopCategoryService;

/**
 * @Hiki msi
 *
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}
}
