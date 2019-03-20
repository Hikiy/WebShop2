/**
 * 
 */
package hiki.o2o.controller.local;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hiki.o2o.dto.LocalAuthExecution;
import hiki.o2o.entity.LocalAuth;
import hiki.o2o.entity.PersonInfo;
import hiki.o2o.enums.LocalAuthStateEnum;
import hiki.o2o.service.LocalAuthService;
import hiki.o2o.util.CodeUtil;
import hiki.o2o.util.HttpServletRequestUtil;

/**
 * @Hiki msi
 *
 */
@Controller
@RequestMapping(value="local",method={RequestMethod.GET,RequestMethod.POST})
public class LocalAuthController {
	@Autowired
	LocalAuthService localAuthService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object>register(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errorMsg", "输入了错误的验证码");
			return modelMap;
		}
		
		String userName = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		
		LocalAuth localAuth= new LocalAuth();
		localAuth.setUserName(userName);
		localAuth.setPassword(password);
		PersonInfo personInfo=new PersonInfo();
		personInfo.setName("无");
		personInfo.setUserType(2);
		personInfo.setEnableStatus(1);
		personInfo.setCreateTime(new Date());
		localAuth.setPersonInfo(personInfo);
		try{
			LocalAuthExecution lae=localAuthService.register(localAuth);
			if(lae.getState()==LocalAuthStateEnum.SUCCESS.getState()){
				modelMap.put("success", true);
			}
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errorMsg", "用户名已存在！");
		}
		return modelMap;
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> login(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errorMsg", "输入了错误的验证码");
			return modelMap;
		}
		
		String userName = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		try{
			LocalAuth localAuth=localAuthService.getLocalAuthByUserNameAndPwd(userName,password);
			System.out.println(localAuth.getUserName());
			if(localAuth.getUserName().equals(userName)){
				modelMap.put("success", true);
				PersonInfo user=localAuth.getPersonInfo();
				request.getSession().setAttribute("user", user);
				request.getSession().setMaxInactiveInterval(30*60);
			}else{
				modelMap.put("success", false);
				modelMap.put("errorMsg", "用户名不存在或密码错误！");
			}
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errorMsg", "用户名不存在或密码错误！");
		}
		return modelMap;
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> logout(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try{
			request.getSession().removeAttribute("user");
			modelMap.put("success", true);
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errMsg", "未登录！");
		}
		return modelMap;
	}
}
