/**
 * 
 */
package hiki.o2o.service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import hiki.o2o.BaseTest;
import hiki.o2o.dto.LocalAuthExecution;
import hiki.o2o.entity.LocalAuth;
import hiki.o2o.entity.PersonInfo;
import hiki.o2o.enums.LocalAuthStateEnum;

/**
 * @Hiki msi
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthServiceTest extends BaseTest{
	@Autowired
	private LocalAuthService localAuthService;
	
	@Test
	@Ignore
	public void testARegister(){
		//使用该测试要先删除数据库中的用户名为testusername的数据
		LocalAuth localAuth=new LocalAuth();
		PersonInfo personInfo=new PersonInfo();
		String username="testusername";
		String password="testpassword";
		personInfo.setUserId(1L);
		localAuth.setPersonInfo(personInfo);
		localAuth.setUserName(username);
		localAuth.setPassword(password);
		CommonsMultipartFile profileImg=null;
		LocalAuthExecution lae=localAuthService.register(localAuth, profileImg);
		assertEquals(LocalAuthStateEnum.SUCCESS.getState(),lae.getState());
		localAuth=localAuthService.getLocalAuthByUserId(personInfo.getUserId());
		System.out.println("用户名："+localAuth.getPersonInfo().getName());
		System.out.println("密码："+localAuth.getPassword());
	}
	@Test
	public void testBModifyLocalAuth(){
		long userId=1;
		String username="testusername";
		String password="testpassword";
		String newPassword="newtestpasssword";
		LocalAuthExecution lae=localAuthService.modifyLocalAuth(userId, username, password, newPassword);
		assertEquals(LocalAuthStateEnum.SUCCESS.getState(),lae.getState());
		LocalAuth localAuth=localAuthService.getLocalAuthByUserNameAndPwd(username, newPassword);
		System.out.println("用户名："+localAuth.getPersonInfo().getName());
		System.out.println("密码："+localAuth.getPassword());
	}
}
