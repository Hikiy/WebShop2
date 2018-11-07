/**
 * 
 */
package hiki.o2o.service;

import java.util.List;

import hiki.o2o.dto.ProductCategoryExecution;
import hiki.o2o.entity.ProductCategory;
import hiki.o2o.exception.ProductCategoryOperationException;

/**
 * @Hiki msi
 *
 */
public interface ProductCategoryService {
	/**
	 * 查找指定店铺的所有商品分类信息
	 * 
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);

	/**
	 * 批量增加商品类别
	 * 
	 * @param productCategoryList
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution batchaddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException;

	/**
	 * 将此类别下的商品里的类别id置为空，并删除指定商品类别
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException;
}
