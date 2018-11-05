/**
 * 
 */
package hiki.o2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hiki.o2o.BaseTest;
import hiki.o2o.entity.ProductCategory;

/**
 * @Hiki msi
 *
 */
public class ProductCategoryDaoTest extends BaseTest{
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Test
	public void testQueryByShopId() {
		List<ProductCategory> productCategoryList = productCategoryDao.queryProdunctCategoryList(2L);
		System.out.println("该店铺自定义类别数为：" + productCategoryList.size());
	}
}
