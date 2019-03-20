package hiki.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;

import hiki.o2o.dao.LocalAuthDao;
import hiki.o2o.dao.PersonInfoDao;
import hiki.o2o.dto.LocalAuthExecution;
import hiki.o2o.entity.LocalAuth;
import hiki.o2o.entity.PersonInfo;
import hiki.o2o.enums.LocalAuthStateEnum;
import hiki.o2o.service.LocalAuthService;
import hiki.o2o.util.MD5;


/**
 * 
 * @Hiki msi
 *
 */
@Service
public class LocalAuthServiceImpl implements LocalAuthService {

	@Autowired
	private LocalAuthDao localAuthDao;
	
	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public LocalAuth getLocalAuthByUserNameAndPwd(String userName,
			String password) {
		return localAuthDao.queryLocalByUserNameAndPwd(userName, MD5.getMd5(password));
	}

	@Override
	public LocalAuth getLocalAuthByUserId(long userId) {
		return localAuthDao.queryLocalByUserId(userId);
	}

	@Override
	@Transactional
	public LocalAuthExecution register(LocalAuth localAuth) throws RuntimeException {
		if (localAuth == null || localAuth.getPassword() == null
				|| localAuth.getUserName() == null) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		try {
			localAuth.setCreateTime(new Date());
			localAuth.setLastEditTime(new Date());
			localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
			if (localAuth.getPersonInfo() != null
					&& localAuth.getPersonInfo().getUserId() == null) {
				//if (profileImg != null) {
					localAuth.getPersonInfo().setCreateTime(new Date());
					localAuth.getPersonInfo().setLastEditTime(new Date());
					localAuth.getPersonInfo().setEnableStatus(1);
//					try {
//						addProfileImg(localAuth, profileImg);
//					} catch (Exception e) {
//						throw new RuntimeException("addUserProfileImg error: "
//								+ e.getMessage());
//					}
				//}
				try {
					PersonInfo personInfo = localAuth.getPersonInfo();
					int effectedNum = personInfoDao
							.insertPersonInfo(personInfo);
					localAuth.setUserId(personInfo.getUserId());
					if (effectedNum <= 0) {
						throw new RuntimeException("添加用户信息失败");
					}
				} catch (Exception e) {
					throw new RuntimeException("insertPersonInfo error: "
							+ e.getMessage());
				}
			}
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			if (effectedNum <= 0) {
				throw new RuntimeException("帐号创建失败");
			} else {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS,
						localAuth);
			}
		} catch (Exception e) {
			throw new RuntimeException("insertLocalAuth error: "
					+ e.getMessage());
		}
	}


	@Override
	@Transactional
	public LocalAuthExecution modifyLocalAuth(Long userId, String userName,
			String password, String newPassword) {
		//非空判断，新旧密码是否相同，若条件不满足则返回错误信息
		if (userId != null && userName != null && password != null
				&& newPassword != null && !password.equals(newPassword)) {
			try {
				int effectedNum = localAuthDao.updateLocalAuth(userId,
						userName, MD5.getMd5(password),
						MD5.getMd5(newPassword), new Date());
				if (effectedNum <= 0) {
					throw new RuntimeException("重置密码失败");
				}
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
			} catch (Exception e) {
				throw new RuntimeException("重置密码失败:" + e.toString());
			}
		} else {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}
	
	/**
	private void addProfileImg(LocalAuth localAuth,
			CommonsMultipartFile profileImg) {
		String dest = FileUtil.getPersonInfoImagePath();
		String profileImgAddr = ImageUtil.generateThumbnail(profileImg, dest);
		localAuth.getPersonInfo().setProfileImg(profileImgAddr);
	}
	**/

}
