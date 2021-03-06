/**
 * 
 */
package hiki.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import hiki.o2o.BaseTest;
import hiki.o2o.entity.ProductCategory;

/**
 * @Hiki msi
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Test
	public void testABatchInserProductCategory() {
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryName("商品类别1");
		productCategory1.setPriority(1);
		productCategory1.setCreateTime(new Date());
		productCategory1.setShopId(2L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("商品类别2");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(2L);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory1);
		productCategoryList.add(productCategory2);
		int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		assertEquals(2, effectedNum);
	}

	@Test
	public void testCDeleteProductCategory() throws Exception {
		long shopId = 2;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProdunctCategoryList(shopId);
		for (ProductCategory pc : productCategoryList) {
			if (pc.getProductCategoryName().equals("商品类别1")  || pc.getProductCategoryName().equals("商品类别2")) {
				System.out.println(pc.getProductCategoryName());
				int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
				assertEquals(1, effectedNum);
			}
		}
	}

	@Test
	public void testBQueryByShopId() {
		List<ProductCategory> productCategoryList = productCategoryDao.queryProdunctCategoryList(2L);
		System.out.println("该店铺自定义类别数为：" + productCategoryList.size());
	}
}
