package com.vo;

import java.util.List;

public class ModuleVO {
	private Integer id;
	private String rname;
	private String intro;
	private Integer count;//该板块下的文章数量
	private Integer newestArticleId;
	private String newestArticleTitle;
	//private  List<UserSmartVO> users;//版主
	private List<UserSmartVO> moderators;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getNewestArticleId() {
		return newestArticleId;
	}
	public void setNewestArticleId(Integer newestArticleId) {
		this.newestArticleId = newestArticleId;
	}
	public String getNewestArticleTitle() {
		return newestArticleTitle;
	}
	public void setNewestArticleTitle(String newestArticleTitle) {
		this.newestArticleTitle = newestArticleTitle;
	}
/*	public List<UserSmartVO> getUsers() {
		return users;
	}
	public void setUsers(List<UserSmartVO> users) {
		this.users = users;
	}*/
	public List<UserSmartVO> getModerators() {
		return moderators;
	}
	public void setModerators(List<UserSmartVO> moderators) {
		this.moderators = moderators;
	}

	
}
