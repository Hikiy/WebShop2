/**
 * 
 */
package hiki.o2o.controller.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hiki.o2o.entity.HeadLine;
import hiki.o2o.entity.ShopCategory;
import hiki.o2o.service.HeadLineService;
import hiki.o2o.service.ShopCategoryService;

/**
 * @Hiki msi
 *
 */
@Controller
@RequestMapping("/frontend")
public class MainPageController {
	@Autowired
	ShopCategoryService shopCategoryService;
	@Autowired
	HeadLineService headLineService;
	
	@RequestMapping(value="/listmainpageinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listMainPageInfo(){
		Map<String,Object> modelMap=new HashMap<String,Object>();
		List<ShopCategory> shopCategoryList=new ArrayList<ShopCategory>();
		try{
			shopCategoryList=shopCategoryService.getShopCategoryList(null);
			modelMap.put("shopCategoryList", shopCategoryList);
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		List<HeadLine> headLineList=new ArrayList<HeadLine>();
		try{
			HeadLine headLineCondition=new HeadLine();
			//状态为1表示可用
			headLineCondition.setEnbleStatus(1);
			headLineList=headLineService.getHeadLineList(headLineCondition);
			modelMap.put("headLineList", headLineList);
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		modelMap.put("success", true);
		return modelMap;
	}
}
