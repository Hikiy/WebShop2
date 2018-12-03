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
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "frontend/index";
	}
}
