/**
 * 
 */
package hiki.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

	/**
	 * 批量新增商品类别
	 * 
	 * @param productCategoryList
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);

	/**
	 * 删除指定商品类别
	 * 
	 * @param productCategoryId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
