/**
 * 
 */
package hiki.o2o.entity;

import java.util.Date;

/**
 * @Hiki msi
 *
 */
public class PersonInfo {
	private Long userId;
	private String name;
	//头像地址
	private String profileImg;
	private String email;
	private String gender;
	//用户状态 ，是否被封禁。0位禁止，1可用
	private Integer enableStatus;
	//用户身份标识  1.顾客  2.店家  3.超级管理员
	private Integer userType;
	private Date createTime;
	private Date lastEditTime;
	/**
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId 瑕佽缃殑 userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 瑕佽缃殑 name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return profilImg
	 */
	public String getProfileImg() {
		return profileImg;
	}
	/**
	 * @param profilImg 瑕佽缃殑 profilImg
	 */
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email 瑕佽缃殑 email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender 瑕佽缃殑 gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return enableStatus
	 */
	public Integer getEnableStatus() {
		return enableStatus;
	}
	/**
	 * @param enableStatus 瑕佽缃殑 enableStatus
	 */
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	/**
	 * @return userType
	 */
	public Integer getUserType() {
		return userType;
	}
	/**
	 * @param userType 瑕佽缃殑 userType
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	/**
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime 瑕佽缃殑 createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return lastEditTime
	 */
	public Date getLastEditTime() {
		return lastEditTime;
	}
	/**
	 * @param lastEditTime 瑕佽缃殑 lastEditTime
	 */
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	
}
