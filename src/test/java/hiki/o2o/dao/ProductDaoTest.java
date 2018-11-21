/**
 * 
 */
package hiki.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import hiki.o2o.BaseTest;
import hiki.o2o.entity.Product;
import hiki.o2o.entity.ProductCategory;
import hiki.o2o.entity.ProductImg;
import hiki.o2o.entity.Shop;

/**
 * @Hiki msi
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductImgDao productImgDao;

	@Test
	@Ignore
	public void testAInsertProduct() {
		Shop shop1 = new Shop();
		shop1.setShopId(2L);
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryId(1L);
		Product product1 = new Product();
		product1.setProductName("商品1");
		product1.setProductDesc("商品描述1");
		product1.setImgAddr("图片地址1");
		product1.setPriority(1);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(productCategory1);

		Shop shop2 = new Shop();
		shop2.setShopId(2L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryId(1L);
		Product product2 = new Product();
		product2.setProductName("商品2");
		product2.setProductDesc("商品描述2");
		product2.setImgAddr("图片地址2");
		product2.setPriority(2);
		product2.setEnableStatus(1);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop2);
		product2.setProductCategory(productCategory2);

		Shop shop3 = new Shop();
		shop3.setShopId(2L);
		ProductCategory productCategory3 = new ProductCategory();
		productCategory3.setProductCategoryId(1L);
		Product product3 = new Product();
		product3.setProductName("商品3");
		product3.setProductDesc("商品描述3");
		product3.setImgAddr("图片地址3");
		product3.setPriority(3);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop2);
		product3.setProductCategory(productCategory3);

		int effectedNum1 = productDao.insertProduct(product1);
		assertEquals(1, effectedNum1);
		int effectedNum2 = productDao.insertProduct(product2);
		assertEquals(1, effectedNum2);
		int effectedNum3 = productDao.insertProduct(product3);
		assertEquals(1, effectedNum3);
	}

	@Test
	public void testCQueryProductByProductId() {
		long productId = 1L;
		// 添加两个商品详情图，模拟原有图片
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("图片地址1");
		productImg1.setImgDesc("图片描述");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(1L);
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("图片地址2");
		productImg2.setImgDesc("图片描述");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(productId);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
		// 查询商品信息，验证详情图信息
		Product product = productDao.queryProductByProductId(productId);
		assertEquals(2, product.getProductImgList().size());
		int effected = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2, effected);
	}

	@Test
	public void testDUpdateProduct() {
		Product product = new Product();
		ProductCategory productCategory = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(2L);
		productCategory.setProductCategoryId(1L);
		product.setProductId(1L);
		product.setProductCategory(productCategory);
		product.setShop(shop);

		product.setProductName("产品1");
		product.setEnableStatus(1);
		product.setLastEditTime(new Date());
		product.setImgAddr("新图片地址1");
		product.setNormalPrice("新原价1");
		product.setPriority(2);
		product.setProductDesc("新描述");
		product.setPromotionPrice("新折扣");
		int effected = productDao.updateProduct(product);
		assertEquals(1, effected);
	}
}
