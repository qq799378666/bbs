package com.vo;

import java.sql.Timestamp;

public class ArticleSmartVO {
	private Integer id;
	private String title;
	private Timestamp time;
	private Boolean good;
	private Integer userId;
	private String userNickname;
	private Integer moduleId;
	private String moduleRname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Boolean getGood() {
		return good;
	}
	public void setGood(Boolean good) {
		this.good = good;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleRname() {
		return moduleRname;
	}
	public void setModuleRname(String moduleRname) {
		this.moduleRname = moduleRname;
	}
}
