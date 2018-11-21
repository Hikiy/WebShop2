/**
 * 
 */
package hiki.o2o.service;

import java.util.List;

import hiki.o2o.dto.ImageHolder;
import hiki.o2o.dto.ProductExecution;
import hiki.o2o.entity.Product;
import hiki.o2o.exception.ProductOperationException;

/**
 * @Hiki msi
 *
 */

public interface ProductService {
	/**
	 * 添加商品信息和图片处理
	 * 
	 * @param product
	 * @param thumbnail
	 *            缩略图
	 * @param productImgList
	 *            详情图
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException;

	/**
	 * 通过productId获得商品信息
	 * 
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);
	
	/**
	 * 修改商品信息
	 * @param product
	 * @param thumbnail
	 * @param productImgList
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException;
}
