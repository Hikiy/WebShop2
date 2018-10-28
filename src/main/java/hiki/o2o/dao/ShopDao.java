/**
 * 
 */
package hiki.o2o.dao;

import hiki.o2o.entity.Shop;

/**
 * @Hiki msi
 *
 */
public interface ShopDao {
	/**
	 * 新增店铺
	 * 
	 * @param shop
	 * @return 1插入一条，-1插入失败
	 */
	int insertShop(Shop shop);
	
	/**
	 * 通过shopId查询店铺
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);

	/**
	 * 更新店铺信息
	 * 
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
