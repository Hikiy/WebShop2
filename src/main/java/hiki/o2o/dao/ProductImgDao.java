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
	
	/**
	 * 通过productId删除所有商品详情图片
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);
	
	/**
	 * 通过productId获得商品详情图列表
	 * @param productId
	 * @return
	 */
	List<ProductImg> queryProductImgList(long productId);
}
