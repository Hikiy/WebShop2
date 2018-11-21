/**
 * 
 */
package hiki.o2o.dao;

import hiki.o2o.entity.Product;

/**
 * @Hiki msi
 *
 */
public interface ProductDao {
	/**
	 * 插入商品
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);
	
	/**
	 * 通过productId查询商品
	 * @param productId
	 * @return
	 */
	Product queryProductByProductId(long productId);
	
	/**
	 * 更新商品信息
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);
}
