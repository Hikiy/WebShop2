/**
 * 
 */
package hiki.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hiki.o2o.BaseTest;
import hiki.o2o.entity.Area;
import hiki.o2o.entity.PersonInfo;
import hiki.o2o.entity.Shop;
import hiki.o2o.entity.ShopCategory;

/**
 * @Hiki msi
 *
 */
public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;
	
	@Test
	public void testqueryShopListAndCount(){
		Shop shopCondition=new Shop();
		ShopCategory childCategory=new ShopCategory();
		ShopCategory parentCategory=new ShopCategory();
		parentCategory.setShopCategoryId(1L);
		childCategory.setParent(parentCategory);
		shopCondition.setShopCategory(childCategory);
		List<Shop> shopList=shopDao.queryShopList(shopCondition, 0, 1);
		int count=shopDao.queryShopCount(shopCondition);
		System.out.println("店铺大小="+shopList.size());
		System.out.println("店铺总大小="+count);
	}

	@Test
	@Ignore
	public void testqueryByShopId() {
		long shopId = 2;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("area_name=" + shop.getArea().getAreaName());
		System.out.println("area_id=" + shop.getArea().getAreaId());
	}

	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		ShopCategory shopCategory = new ShopCategory();
		Area area = new Area();
		owner.setUserId(1L);
		shopCategory.setShopCategoryId(1L);
		area.setAreaId(1);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}

	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(2L);
		shop.setShopDesc("测试描述");
		shop.setShopAddr("测试地址");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
}
