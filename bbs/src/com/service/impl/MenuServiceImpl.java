package com.service.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.po.Menu;
import com.po.Postion;
import com.po.User;
import com.service.MenuService;
import com.util.MyArrays;
import com.vo.MenuVO;

@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {
	public MenuServiceImpl(){
		this.thisClass = Menu.class;
	}
	/*
	 *将Menu的集合更改为MenuVO的集合
	 */
	private List<MenuVO> dealToVO(Collection<Menu> mList) {
		List<MenuVO> voList = new ArrayList<MenuVO>();
		for (Menu m : mList) {
			MenuVO mvo = new MenuVO();
			BeanUtils.copyProperties(m, mvo);
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("url", m.getUrl());
			mvo.setAttributes(attributes);
			mvo.setPid(m.getMenu()==null?null:m.getMenu().getId());
			voList.add(mvo);
		}
		return voList;
	}

	/*
	 * 获取所有的菜单
	 * (non-Javadoc)
	 * @see com.service.MenuService#getAll()
	 */
	
	@Override
	public List<MenuVO> getAll() {
		List<Menu> mList = 	this.menuDAO.getAll();
		return this.dealToVO(mList);
	}
	//................................
	/*
	 * 获取当前用户的菜单,方法效率不高,待改进
	 * (non-Javadoc)
	 * @see com.service.MenuService#getCurUserMenuList()
	 */
	@Override
	public List<MenuVO> getCurUserMenuList() {
		User curUser = this.getCurUserBySessionAndSql();
		Set<Menu> mList = new HashSet<Menu>();//用户set去除重复
		Set<Postion> pSet = curUser.getPostions();
		for(Postion p:pSet){
			mList.addAll(p.getMenus());
		}
		return MyArrays.sort(this.dealToVO(mList));
	}

	/*
	 * 通过职位获取菜单,没有排序,待改进
	 * (non-Javadoc)
	 * @see com.service.MenuService#getMenusByPostion(com.po.Postion)
	 */
	@Override
	public List<MenuVO> getMenusByPostion(Postion postion) {
		postion = this.postionDAO.findById(postion.getId());
		Set<Menu> mList = postion.getMenus();
		return this.dealToVO(mList);
	}
}
