/**
 * 
 */
package hiki.o2o.controller.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Hiki msi
 *
 */
@Controller
@RequestMapping(value = "auth", method = RequestMethod.GET)
public class LocalAuthShowController {
	@RequestMapping(value="/register")
	public String register(){
		return "local/register";
	}
	@RequestMapping(value="/login")
	public String login(){
		return "local/login";
	}
	@RequestMapping(value="/logout")
	public String logout(){
		return "local/logout";
	}
	@RequestMapping(value="/resetpwd")
	public String resetpwd(){
		return "local/resetpwd";
	}
}
