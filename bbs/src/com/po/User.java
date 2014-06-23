package com.po;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String pwd;
	private String nickname;
	private String sex;
	private String email;
	private Integer score;
	private Integer money;
	private Integer rank;
	private Set modules = new HashSet(0);
	private Set comments = new HashSet(0);
	private Set articles = new HashSet(0);
	private Set postions = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String pwd, String nickname, String sex,
			String email, Integer score, Integer money, Integer rank) {
		this.username = username;
		this.pwd = pwd;
		this.nickname = nickname;
		this.sex = sex;
		this.email = email;
		this.score = score;
		this.money = money;
		this.rank = rank;
	}

	/** full constructor */
	public User(String username, String pwd, String nickname, String sex,
			String email, Integer score, Integer money, Integer rank,
			Set modules, Set comments, Set articles, Set postions) {
		this.username = username;
		this.pwd = pwd;
		this.nickname = nickname;
		this.sex = sex;
		this.email = email;
		this.score = score;
		this.money = money;
		this.rank = rank;
		this.modules = modules;
		this.comments = comments;
		this.articles = articles;
		this.postions = postions;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getMoney() {
		return this.money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Set getModules() {
		return this.modules;
	}

	public void setModules(Set modules) {
		this.modules = modules;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getArticles() {
		return this.articles;
	}

	public void setArticles(Set articles) {
		this.articles = articles;
	}

	public Set getPostions() {
		return this.postions;
	}

	public void setPostions(Set postions) {
		this.postions = postions;
	}

}