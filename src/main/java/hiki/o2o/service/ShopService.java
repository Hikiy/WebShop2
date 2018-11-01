/**
 * 
 */
package hiki.o2o.service;

import java.io.InputStream;

import hiki.o2o.dto.ShopExecution;
import hiki.o2o.entity.Shop;
import hiki.o2o.exception.ShopOperationException;

/**
 * @Hiki msi
 *
 */
public interface ShopService {
	/**
	 * 注册店铺，包括图片处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName)
			throws ShopOperationException;
	
	/**
	 * 通过shopId获取店铺信息
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(Long shopId);
	
	/**
	 * 更新店铺信息，包括图片处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 */
	ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream, String fileName)throws ShopOperationException;
	
	
}
