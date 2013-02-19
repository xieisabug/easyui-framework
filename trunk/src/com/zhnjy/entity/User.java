package com.zhnjy.entity;

import com.zhnjy.util.db.AdminDB;
import com.zhnjy.util.db.SiteDB;

public class User {

	private int id;

	private String userName;

	private String password;

	private int level;

	private String remark;

	private int siteId;
	
	private String siteName;

	private String levelName;
	// 空的构造方法；
	public User() {

	}

	
	/**
	 * 数据库字段的构造方法
	 * @param id
	 * @param userName
	 * @param password
	 * @param level
	 * @param remark
	 * @param siteId
	 */
	public User(int id, String userName, String password, int level,
			String remark, int siteId) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.level = level;
		this.remark = remark;
		this.siteId = siteId;
		if(siteId != 0){
			siteName = SiteDB.getSiteNameById(siteId);
		}else{
			siteName = "无";
		}
		//if()
		levelName = AdminDB.getLevelName(level);
	}

 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getLevelName() {
		return levelName;
	}


	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", level=" + level + ", password=" + password
				+ ", remark=" + remark + ", username=" + userName + "]";
	}

}
