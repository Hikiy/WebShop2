/**
 * 
 */
package hiki.o2o.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Hiki msi
 *
 */
@Controller
@RequestMapping("/frontend")
public class FrontendController {
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "frontend/index";
	}
	
	/**
	 * 店铺列表
	 * @return
	 */
	@RequestMapping(value="/shoplist",method=RequestMethod.GET)
	public String shopList(){
		return "frontend/shoplist";
	}
}
