/**
 * 
 */
package hiki.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	/**
	 * 查询商品列表并进行分页，可用搜索条件：模糊商品名、商品状态、商品类别、店铺ID
	 * @param productCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> queryProductList(@Param("productCondition")Product productCondition,
			@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);
	
	/**
	 * 查询商品总数
	 * @param productCondition
	 * @return
	 */
	int queryProductCount(@Param("productCondition")Product productCondition);
}
