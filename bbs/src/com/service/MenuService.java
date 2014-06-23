package com.service;

import java.util.List;

import com.po.Postion;
import com.vo.MenuVO;

public interface MenuService {
	public List<MenuVO> getAll();
	public List<MenuVO> getCurUserMenuList();
	public List<MenuVO> getMenusByPostion(Postion postion);
}