package hiki.o2o.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import hiki.o2o.dto.LocalAuthExecution;
import hiki.o2o.entity.LocalAuth;

public interface LocalAuthService {
	/**
	 * 通过账号密码获取账号信息
	 * @param userName
	 * @return
	 */
	LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password);

	/**
	 * 通过userId获取账号信息
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(long userId);

	/**
	 * 注册账号
	 * @param localAuth
	 * @param profileImg
	 * @return
	 * @throws RuntimeException
	 */
	LocalAuthExecution register(LocalAuth localAuth,
			CommonsMultipartFile profileImg) throws RuntimeException;


	/**
	 * 修改账号密码
	 * @param localAuthId
	 * @param userName
	 * @param password
	 * @param newPassword
	 * @param lastEditTime
	 * @return
	 */
	LocalAuthExecution modifyLocalAuth(Long userId, String userName,
			String password, String newPassword);
	
	
//	/**
//	 * 
//	 * @param localAuth
//	 * @return
//	 * @throws RuntimeException
//	 */
//	LocalAuthExecution bindLocalAuth(LocalAuth localAuth)
//			throws RuntimeException;
}
