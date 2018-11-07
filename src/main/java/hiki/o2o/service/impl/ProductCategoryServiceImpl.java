/**
 * 
 */
package hiki.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiki.o2o.dao.ProductCategoryDao;
import hiki.o2o.dto.ProductCategoryExecution;
import hiki.o2o.entity.ProductCategory;
import hiki.o2o.enums.ProductCategoryStateEnum;
import hiki.o2o.exception.ProductCategoryOperationException;
import hiki.o2o.service.ProductCategoryService;

/**
 * @Hiki msi
 *
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProdunctCategoryList(shopId);
	}

	@Override
	@Transactional
	public ProductCategoryExecution batchaddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if (effectedNum <= 0) {
					throw new ProductCategoryOperationException("商品类别创建失败");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException("batchInsertProductCategory error:" + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}
}
