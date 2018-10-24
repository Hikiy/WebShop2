/**
 * 
 */
package hiki.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hiki.o2o.BaseTest;
import hiki.o2o.dto.ShopExecution;
import hiki.o2o.entity.Area;
import hiki.o2o.entity.PersonInfo;
import hiki.o2o.entity.Shop;
import hiki.o2o.entity.ShopCategory;
import hiki.o2o.enums.ShopStateEnum;

/**
 * @Hiki msi
 *
 */
public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;

	@Test
	public void testAddShop() {
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
		shop.setShopName("测试的店铺1");
		shop.setShopDesc("test1");
		shop.setShopAddr("test1");
		shop.setPhone("test1");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("E:/java/WebShop/test/picture/human.jpg");
		ShopExecution se = shopService.addShop(shop, shopImg);
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
}
