/**
 * 
 */
package hiki.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiki.o2o.dao.ProductDao;
import hiki.o2o.dao.ProductImgDao;
import hiki.o2o.dto.ImageHolder;
import hiki.o2o.dto.ProductExecution;
import hiki.o2o.entity.Product;
import hiki.o2o.entity.ProductImg;
import hiki.o2o.enums.ProductStateEnum;
import hiki.o2o.exception.ProductOperationException;
import hiki.o2o.service.ProductService;
import hiki.o2o.util.ImageUtil;
import hiki.o2o.util.PathUtil;

/**
 * @Hiki msi
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	@Transactional
	// 1.处理缩略图，获取缩略图相对路径并赋值给product
	// 2.往tb_product写入商品信息，并获取自动生成的productId
	// 3.结合productId批量处理商品详情图
	// 4.将商品详情图列表批量插入tb_product_img中
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductOperationException {
		// 空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			// 给商品设置属性
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			// 默认上架状态
			product.setEnableStatus(1);
			// 若有缩略图则添加
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品失败：" + e.getMessage());
			}
			// 若有详情图则添加
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				addProductImgList(product, productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			// 传值为空
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	/**
	 * 添加缩略图
	 * 
	 * @param product
	 * @param imageHolder
	 */
	private void addThumbnail(Product product, ImageHolder imageHolder) {
		// 获取图片存放路径
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		// 生成缩略图并返回缩略图路径
		String thumbnailAddr = ImageUtil.generateThumbnail(imageHolder, dest);
		product.setImgAddr(thumbnailAddr);
	}

	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		// 获取图片存放路径
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		// 遍历图片进行处理，添加进ProductImg实体类
		for (ImageHolder productImgHolder : productImgHolderList) {
			// 通过工具类ImageUtil生成带有自制水印的图片并返回路径
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		// 如果确实有要添加的详情图片，则批量增加到数据库
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductOperationException("添加商品详情图片失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("添加商品详情图片失败" + e.getMessage());
			}
		}
	}

	@Override
	/**
	 * 通过productId获得商品信息
	 */
	public Product getProductById(long productId) {
		return productDao.queryProductByProductId(productId);
	}

	/**
	 * 修改商品信息
	 */
	// 1.有缩略图改缩略图，若原本有缩略图就先删掉原本的缩略图，然后生成的地址再赋值回product
	// 2.详情图按1的规则一样
	// 3.删除该商品在tb_product_img中的全部记录
	// 4.更新tb_product_img和tb_product
	@Override
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException {
		// 空值判断
		if (product != null && product.getProductId() != null && product.getShop() != null
				&& product.getShop().getShopId() != null) {
			product.setLastEditTime(new Date());
			//有详情图则获取原本商品信息
			if (thumbnail != null) {
				Product temp = productDao.queryProductByProductId(product.getProductId());
				//若原本有缩略图，则删除
				if (temp.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(temp.getImgAddr());
				}
				//添加详情图,并将详情图地址放入product
				addThumbnail(product, thumbnail);
			}
			//有详情图，则删除后添加新的详情图
			if(productImgList!=null&&productImgList.size()>0){
				deleteProductImgList(product.getProductId());
				addProductImgList(product, productImgList);
			}
			try{
				int effected=productDao.updateProduct(product);
				if(effected<=0){
					throw new ProductOperationException("更新店铺信息失败");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			}catch(Exception e){
				throw new ProductOperationException("更新店铺信息失败:"+e.getMessage());
			}
		}else{
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
	
	//根据productId删除详情图
	private void deleteProductImgList(long productId){
		//获取原来的图片,然后遍历，删除原本的图片文件
		List<ProductImg> productImgList=productImgDao.queryProductImgList(productId);
		for(ProductImg productImg:productImgList){
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		//删除数据库中的数据
		productImgDao.deleteProductImgByProductId(productId);
	}
}
