/**
 * 
 */
package hiki.o2o.controller.shopadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import hiki.o2o.dto.ImageHolder;
import hiki.o2o.dto.ProductExecution;
import hiki.o2o.entity.Product;
import hiki.o2o.entity.Shop;
import hiki.o2o.enums.ProductStateEnum;
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

	// 商品详情图的最大数量
	private final int IMAGEMAXCOUNT = 6;

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
		MultipartHttpServletRequest multipartRequest = null;
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		// 从request中获取文件流
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		try {
			// 判断是否有文件流
			if (multipartResolver.isMultipart(request)) {
				multipartRequest = (MultipartHttpServletRequest) request;
				// 取缩略图并建立ImageHolder对象
				CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
				thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
				// 取详情图片并添加到List中，最多支持6张图片
				for (int i = 0; i < IMAGEMAXCOUNT; i++) {
					CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest
							.getFile("productImg" + i);
					if (productImgFile != null) {
						ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),
								productImgFile.getInputStream());
						productImgList.add(productImg);
					} else {
						break;
					}
				}
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
			// 讲String流转换成Product实体类
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		if (product != null && thumbnail != null && productImgList != null) {
			// 获取session中的ShopId
			Shop currentshop = (Shop) request.getSession().getAttribute("currentShop");
			product.setShop(currentshop);
			System.out.println(thumbnail.getImageName());
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
}
