/**
 * 
 */
package hiki.o2o.service.impl;

import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiki.o2o.dao.ShopDao;
import hiki.o2o.dto.ShopExecution;
import hiki.o2o.entity.Shop;
import hiki.o2o.enums.ShopStateEnum;
import hiki.o2o.exception.ShopOperationException;
import hiki.o2o.service.ShopService;
import hiki.o2o.util.ImageUtil;
import hiki.o2o.util.PathUtil;

/**
 * @Hiki msi
 *
 */
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
		// 空值判断
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			// 给店铺信息赋初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			// 添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				// 使用ShopOperationException才能终止事务并回滚
				throw new ShopOperationException("店铺创建失败！");
			} else {
				// 存储图片
				if (shopImgInputStream != null) {
					try {
						addShopImg(shop, shopImgInputStream, fileName);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImg error:" + e.getMessage());
					}
				}
				// 更新信息
				effectedNum = shopDao.updateShop(shop);
				if (effectedNum < 0) {
					throw new ShopOperationException("更新图片地址失败！");
				}
			}
		} catch (Exception e) {
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	/**
	 * @param shop
	 * @param shopImg
	 */
	private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
		shop.setShopImg(shopImgAddr);
	}

}
