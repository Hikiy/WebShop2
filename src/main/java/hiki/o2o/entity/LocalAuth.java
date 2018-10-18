/**
 * 
 */
package hiki.o2o.entity;

import java.util.Date;

/**
 * @Hiki msi
 *
 */
public class LocalAuth {
	private Long localAuthId;
	private String username;
	private String psaaword;
	private Date createTime;
	private Date lastEditTime;
	private PersonInfo personInfo;
	public Long getLocalAuthId() {
		return localAuthId;
	}
	public void setLocalAuthId(Long localAuthId) {
		this.localAuthId = localAuthId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPsaaword() {
		return psaaword;
	}
	public void setPsaaword(String psaaword) {
		this.psaaword = psaaword;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	
}
