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

	ProductCategoryExecution batchaddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException;
}
