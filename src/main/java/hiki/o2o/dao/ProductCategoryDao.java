/**
 * 
 */
package hiki.o2o.dao;

import java.util.List;

import hiki.o2o.entity.ProductCategory;

/**
 * @Hiki msi
 *
 */
public interface ProductCategoryDao {
	/**
	 * 通过shopId查询商品分类
	 * 
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProdunctCategoryList(long shopId);
}
