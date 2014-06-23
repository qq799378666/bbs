package com.action.json;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.po.Menu;
import com.po.Postion;
import com.util.MyArrays;
import com.vo.MenuVO;

public class JSONMenuAction extends JSONBaseAction implements ModelDriven<Menu>{
	private Menu model;
	private Postion postion;
	
	
	/*
	 * 根据职位获取菜单列表
	 */
	public String getMenusByPostion(){
		List<MenuVO> menus = this.menuService.getMenusByPostion(postion);
		this.setRoot(menus);
		return SUCCESS;
	}
	
	
	public String getAllMenu(){
		List<MenuVO> menus = this.menuService.getAll();
		this.setRoot(menus);
		return SUCCESS;
	}
	
	/*
	 * 获取当前用户的菜单
	 */
	public String getMenusByCurUser(){
		List<MenuVO> menus = this.menuService.getCurUserMenuList();
		this.setRoot(menus);
		return SUCCESS;
	}
	
	
	
	public Postion getPostion() {
		return postion;
	}



	public void setPostion(Postion postion) {
		this.postion = postion;
	}



	@Override
	public Menu getModel() {
		if(this.model == null)model = new Menu();
		return this.model;
	}

	

}
