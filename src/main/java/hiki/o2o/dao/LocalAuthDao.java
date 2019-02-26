/**
 * 
 */
package hiki.o2o.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import hiki.o2o.entity.LocalAuth;

/**
 * @Hiki msi
 *
 */
public interface LocalAuthDao {
	/**
	 * 用于登录使用，通过账号密码查询信息
	 * @param userName
	 * @param password
	 * @return
	 */
	LocalAuth queryLocalByUserNameAndPwd(@Param("userName") String userName,
			@Param("password") String password);

	/**
	 * 使用userId查询账号信息
	 * @param userId
	 * @return
	 */
	LocalAuth queryLocalByUserId(@Param("userId") long userId);

	/**
	 * 添加账号
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);

	/**
	 * 通过账号密码更改密码
	 * @param localAuth
	 * @return
	 */
	int updateLocalAuth(@Param("userId") Long userId,
			@Param("userName") String userName,
			@Param("password") String password,
			@Param("newPassword") String newPassword,
			@Param("lastEditTime") Date lastEditTime);
}
