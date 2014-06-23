package com.po;

import java.util.HashSet;
import java.util.Set;

/**
 * Postion entity. @author MyEclipse Persistence Tools
 */

public class Postion implements java.io.Serializable {

	// Fields

	private Integer id;
	private String rname;
	private String roles;
	private String iconCls;
	private Set users = new HashSet(0);
	private Set menus = new HashSet(0);

	// Constructors

	/** default constructor */
	public Postion() {
	}

	/** minimal constructor */
	public Postion(String rname) {
		this.rname = rname;
	}

	/** full constructor */
	public Postion(String rname, String roles, String iconCls, Set users,
			Set menus) {
		this.rname = rname;
		this.roles = roles;
		this.iconCls = iconCls;
		this.users = users;
		this.menus = menus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRname() {
		return this.rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRoles() {
		return this.roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	public Set getMenus() {
		return this.menus;
	}

	public void setMenus(Set menus) {
		this.menus = menus;
	}

}