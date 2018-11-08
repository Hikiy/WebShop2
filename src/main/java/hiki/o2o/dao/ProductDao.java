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
}
