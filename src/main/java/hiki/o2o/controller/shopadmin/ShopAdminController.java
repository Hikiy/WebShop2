/**
 * 
 */
package hiki.o2o.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Hiki msi
 *
 */
@Controller
@RequestMapping(value = "shopadmin", method = RequestMethod.GET)
public class ShopAdminController {
	@RequestMapping(value="/shopoperation")
	public String shopOperation(){
		return "shop/shopoperation";
	}
}