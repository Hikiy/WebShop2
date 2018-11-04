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
		PersonInfo owner=new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
//		List<Shop> shopList=shopDao.queryShopList(shopCondition, 0, 3);
//		int count=shopDao.queryShopCount(shopCondition);
//		System.out.println("店铺列表的大小="+shopList.size());
//		System.out.println("店铺总大小="+count);
		//ShopCategory shopCategory=new ShopCategory();
		//shopCategory.setShopCategoryId(1L);
		//shopCondition.setShopCategory(shopCategory);
		//shopList=shopDao.queryShopList(shopCondition, 0, 2);
		//count=shopDao.queryShopCount(shopCondition);
		//System.out.println("店铺列表的大小="+shopList.size());
		///System.out.println("店铺总大小="+count);
		//Shop shopCondition2=new Shop();
		//shopCondition.setShopName("咖啡");
		//shopCondition.setEnableStatus(1);
		Area area=new Area();
		area.setAreaId(1);
		shopCondition.setArea(area);
		String name="咖啡";
		shopCondition.setShopName(name);
		List<Shop> shopList2=shopDao.queryShopList(shopCondition, 0, 1);
		int count2=shopDao.queryShopCount(shopCondition);
		System.out.println("店铺列表2的大小="+shopList2.size());
		System.out.println("店铺2总大小="+count2);
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
