/**
 * 
 */
package hiki.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import hiki.o2o.BaseTest;
import hiki.o2o.entity.Product;
import hiki.o2o.entity.ProductCategory;
import hiki.o2o.entity.Shop;

/**
 * @Hiki msi
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest{
	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testAInsertProduct(){
		Shop shop1=new Shop();
		shop1.setShopId(2L);
		ProductCategory productCategory1=new ProductCategory();
		productCategory1.setProductCategoryId(1L);
		Product product1=new Product();
		product1.setProductName("商品1");
		product1.setProductDesc("商品描述1");
		product1.setImgAddr("图片地址1");
		product1.setPriority(1);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(productCategory1);
		
		Shop shop2=new Shop();
		shop2.setShopId(2L);
		ProductCategory productCategory2=new ProductCategory();
		productCategory2.setProductCategoryId(1L);
		Product product2=new Product();
		product2.setProductName("商品2");
		product2.setProductDesc("商品描述2");
		product2.setImgAddr("图片地址2");
		product2.setPriority(2);
		product2.setEnableStatus(1);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop2);
		product2.setProductCategory(productCategory2);
		
		Shop shop3=new Shop();
		shop3.setShopId(2L);
		ProductCategory productCategory3=new ProductCategory();
		productCategory3.setProductCategoryId(1L);
		Product product3=new Product();
		product3.setProductName("商品3");
		product3.setProductDesc("商品描述3");
		product3.setImgAddr("图片地址3");
		product3.setPriority(3);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop2);
		product3.setProductCategory(productCategory3);
		
		int effectedNum1=productDao.insertProduct(product1);
		assertEquals(1,effectedNum1);
		int effectedNum2=productDao.insertProduct(product2);
		assertEquals(1,effectedNum2);
		int effectedNum3=productDao.insertProduct(product3);
		assertEquals(1,effectedNum3);
	}
}
