/**
 * 
 */
package hiki.o2o.dao;

import java.util.List;

import hiki.o2o.entity.ProductImg;

/**
 * @Hiki msi
 *
 */
public interface ProductImgDao {
	/**
	 * 批量添加商品详情图片
	 * 
	 * @param productImgList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);
}
