/**
 * 
 */
package hiki.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hiki.o2o.dao.ProductCategoryDao;
import hiki.o2o.entity.ProductCategory;
import hiki.o2o.service.ProductCategoryService;

/**
 * @Hiki msi
 *
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProdunctCategoryList(shopId);
	}

}
