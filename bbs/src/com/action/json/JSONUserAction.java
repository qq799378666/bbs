package com.action.json;

import java.util.HashMap;
import java.util.Map;

import com.form.UserLoginForm;
import com.form.UserRegisterForm;
import com.opensymphony.xwork2.ModelDriven;
import com.po.Postion;
import com.po.User;
import com.vo.PageVO;
import com.vo.UserVO;


public class JSONUserAction extends JSONBaseAction implements ModelDriven<User>{

	private UserLoginForm userLoginForm;
	private UserRegisterForm userRegisterForm;
	private String username;
	private User model;
	private Postion postion;
	
	
	public String getPageVO(){
		controlList();
		PageVO<UserVO> pv = this.userService.getPageVOToVO(pvs);
		this.setRoot(pv);
		return SUCCESS;
	}
	
	
	/*
	 * 给用户添加职位
	 */
	public String addPostion(){
		this.userService.addPostion(model,postion);
		return SUCCESS;
	}
	
	/*
	 * 给用户删除职位
	 */
	
	public String delPostion(){
		this.userService.delPostion(model,postion);
		return SUCCESS;
	}
	
	//无权限限制
	public String check(){
		Map<String,Object> map = new HashMap<String, Object>();
		boolean exist = this.userService.isexist(model);
		map.put("exist", exist);
		this.setRoot(map);
		return SUCCESS;
	}
	//无权限限制
	public String register(){
		this.userService.register(userRegisterForm);
		
		return SUCCESS;
	}
	//无权限限制
	public String login(){
		this.userService.login(userLoginForm);
		return SUCCESS;
	}
	//无权限限制
	public String alter(){
		this.userService.alterCurUser(model);
		return SUCCESS;
	}
	


	public UserLoginForm getUserLoginForm() {
		return userLoginForm;
	}


	public void setUserLoginForm(UserLoginForm userLoginForm) {
		this.userLoginForm = userLoginForm;
	}


	public UserRegisterForm getUserRegisterForm() {
		return userRegisterForm;
	}


	public void setUserRegisterForm(UserRegisterForm userRegisterForm) {
		this.userRegisterForm = userRegisterForm;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Postion getPostion() {
		return postion;
	}


	public void setPostion(Postion postion) {
		this.postion = postion;
	}


	@Override
	public User getModel() {
		if(model==null)model = new User();
		return model;
	}
	
	
	

}
