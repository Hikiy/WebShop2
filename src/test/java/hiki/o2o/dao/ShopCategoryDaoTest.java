/**
 * 
 */
package hiki.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hiki.o2o.BaseTest;
import hiki.o2o.entity.ShopCategory;

/**
 * @Hiki msi
 *
 */
public class ShopCategoryDaoTest extends BaseTest {
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Test
	public void testQueryShopCategory() {
		List<ShopCategory> shopCategoryList1 = shopCategoryDao.queryShopCategory(null);
		assertEquals(1,shopCategoryList1.size());
		List<ShopCategory> shopCategoryList2 = shopCategoryDao.queryShopCategory(new ShopCategory());
		assertEquals(1,shopCategoryList2.size());
	}

}
