package com.action.json;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.po.Module;
import com.po.User;
import com.vo.ModuleTree;
import com.vo.ModuleVO;
import com.vo.PageVO;



public class JSONModuleAction extends JSONBaseAction<Module> implements ModelDriven<Module> {

	private List<ModuleVO> modules;

	/*public String getListToTree(){
		this.moduleService.getAllToVO();
		
		return SUCCESS;
	}*/
	
	private User user;
	
	public String add(){
		this.moduleService.add(model);
		
		return SUCCESS;
	}
	public String del(){
		this.moduleService.del(model);
		
		return SUCCESS;
	}
	public String update(){
		this.moduleService.update(model);
		return SUCCESS;
	}
	
	
	public String getAllToTreeByUser(){
		List<ModuleTree> list = this.moduleService.getAllToTreeByUser(user);
		this.setRoot(list);
		return SUCCESS;
	}
	/*
	 * 获取当前用户权限下的版块列表
	 */
	public String getCurUserAuthorityTree(){
		List<ModuleTree> list = this.moduleService.getCurUserAuthorityTree();
		this.setRoot(list);
		return SUCCESS;
	}
	
	
	
	public String addModerator(){//任命版主,传递user,传递部门model
		this.moduleService.appointModerator(user,model);
		return SUCCESS;
	}
	
	public String delModerator(){
		this.moduleService.dismissModerator(user,model);
		return SUCCESS;
	}
	
	public String getPageVO(){
		controlList();
		PageVO<ModuleVO> pvv = this.moduleService.getPageVOToVO(pvs);
		this.setRoot(pvv);
		return SUCCESS;
	}
	
	public List<ModuleVO> getModules() {
		return modules;
	}

	public void setModules(List<ModuleVO> modules) {
		this.modules = modules;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public Module getModel() {
		if(model==null)model = new Module();
		return model;
	}

}
