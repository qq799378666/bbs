package com.po;

import java.util.HashSet;
import java.util.Set;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {

	// Fields

	private Integer id;
	private Menu menu;
	private String text;
	private String iconCls;
	private String roles;
	private String url;
	private Set menus = new HashSet(0);
	private Set postions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** minimal constructor */
	public Menu(String text) {
		this.text = text;
	}

	/** full constructor */
	public Menu(Menu menu, String text, String iconCls, String roles,
			String url, Set menus, Set postions) {
		this.menu = menu;
		this.text = text;
		this.iconCls = iconCls;
		this.roles = roles;
		this.url = url;
		this.menus = menus;
		this.postions = postions;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getRoles() {
		return this.roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set getMenus() {
		return this.menus;
	}

	public void setMenus(Set menus) {
		this.menus = menus;
	}

	public Set getPostions() {
		return this.postions;
	}

	public void setPostions(Set postions) {
		this.postions = postions;
	}

}