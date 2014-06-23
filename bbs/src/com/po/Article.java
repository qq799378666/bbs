package com.po;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Article entity. @author MyEclipse Persistence Tools
 */

public class Article implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Module module;
	private String title;
	private String content;
	private Timestamp time;
	private Boolean good;
	private Set comments = new HashSet(0);
	private Set modules = new HashSet(0);

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** full constructor */
	public Article(User user, Module module, String title, String content,
			Timestamp time, Boolean good, Set comments, Set modules) {
		this.user = user;
		this.module = module;
		this.title = title;
		this.content = content;
		this.time = time;
		this.good = good;
		this.comments = comments;
		this.modules = modules;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Boolean getGood() {
		return this.good;
	}

	public void setGood(Boolean good) {
		this.good = good;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getModules() {
		return this.modules;
	}

	public void setModules(Set modules) {
		this.modules = modules;
	}

}