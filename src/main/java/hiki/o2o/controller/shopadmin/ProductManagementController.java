/**
 * 
 */
package hiki.o2o.controller.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import hiki.o2o.dto.ImageHolder;
import hiki.o2o.dto.ProductExecution;
import hiki.o2o.entity.Product;
import hiki.o2o.entity.ProductCategory;
import hiki.o2o.entity.Shop;
import hiki.o2o.enums.ProductStateEnum;
import hiki.o2o.service.ProductCategoryService;
import hiki.o2o.service.ProductService;
import hiki.o2o.util.CodeUtil;
import hiki.o2o.util.HttpServletRequestUtil;

/**
 * @Hiki msi
 *
 */
@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {
	@Autowired
	ProductService productService;

	@Autowired
	ProductCategoryService productCategoryService;

	// 商品详情图的最大数量
	private final int IMAGEMAXCOUNT = 6;

	/**
	 * 添加商品
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 验证验证码正确性
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入的验证码错误");
			return modelMap;
		}

		// 接收前端传来的变量：商品信息、图片、详情图、缩略图

		// 用于获取JSON的mapper实例
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		String productStr = HttpServletRequestUtil.getString(request, "productStr");
		// 用于处理文件流
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		// 从request中获取文件流
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		try {
			// 判断是否有文件流
			if (multipartResolver.isMultipart(request)) {
				thumbnail = handleImage(request, thumbnail, productImgList);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "图片不能为空");
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		try {
			// 将String流转换成Product实体类
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		if (product != null && thumbnail != null && productImgList != null) {
			// 获取session中的ShopId
			Shop currentshop = (Shop) request.getSession().getAttribute("currentShop");
			product.setShop(currentshop);
			ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
			if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", pe.getStateInfo());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "商品信息不完整");
			return modelMap;
		}

		return modelMap;
	}

	/**
	 * @param request
	 * @param thumbnail
	 * @param productImgList
	 * @return
	 * @throws IOException
	 */
	private ImageHolder handleImage(HttpServletRequest request, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 取缩略图并建立ImageHolder对象
		CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
		if (thumbnailFile != null) {
			thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
		}
		// 取详情图片并添加到List中，最多支持6张图片
		for (int i = 0; i < IMAGEMAXCOUNT; i++) {
			CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);
			if (productImgFile != null) {
				ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),
						productImgFile.getInputStream());
				productImgList.add(productImg);
			} else {
				break;
			}
		}
		return thumbnail;
	}

	/**
	 * 通过productId获得商品信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
	@ResponseBody
	// GET和POST请求传的参数会自动转换赋值到@RequestParam 所注解的变量上
	private Map<String, Object> getProductById(@RequestParam Long productId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (productId > 0) {
			// 获取商品信息
			Product product = productService.getProductById(productId);
			// 获取商品类别信息
			List<ProductCategory> productCategoryList = productCategoryService
					.getProductCategoryList(product.getShop().getShopId());
			modelMap.put("success", true);
			modelMap.put("product", product);
			modelMap.put("productCategoryList", productCategoryList);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty productId");
		}
		return modelMap;
	}

	/**
	 * 更新商品信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyproduct", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 用于判断该操作是商品编辑还是为上/下架操作
		// 若为商品编辑，则要验证码，否贼不需要验证码
		// true为商品编辑,false为上/下架操作
		boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
		if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入的验证码错误");
			return modelMap;
		}
		// 接收前端传来的变量：商品信息、图片、详情图、缩略图
		// 用于获取JSON的mapper实例
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		// 从request中获取文件流
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		try {
			// 判断是否有文件流
			if (multipartResolver.isMultipart(request)) {
				thumbnail = handleImage(request, thumbnail, productImgList);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		try {
			String productStr = HttpServletRequestUtil.getString(request, "productStr");
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		if (product != null) {
			try {
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				product.setShop(currentShop);
				ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
				if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入商品信息");
		}
		return modelMap;
	}
}
