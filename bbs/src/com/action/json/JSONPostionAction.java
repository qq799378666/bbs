package com.action.json;


import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.po.Menu;
import com.po.Postion;
import com.po.User;
import com.vo.PageVO;
import com.vo.PostionVO;

public class JSONPostionAction extends JSONBaseAction implements ModelDriven<Postion>{
	private Postion model;
	private List<Menu> ms;
	
	private User user;
		public String getPageVO(){
		controlList();
		PageVO<PostionVO> pv = this.postionService.getPageVOToVO(pvs);
		this.setRoot(pv);
		return SUCCESS;
	}
	/*
	public String getPostionListByUser(){
		List<PostionVO> pvList = this.postionService.getPostionVOListByUser(user);
		this.setRoot(pvList);
		return SUCCESS;
	}*/
	
	/*
	 * 添加职位,需要将菜单集合传到ms集合中
	 */
	public String add(){
		this.postionService.add(model, ms);
		return SUCCESS;
	}

	
	public String del(){
		this.postionService.del(model);
		return SUCCESS;
	}
	
	public String update(){
		this.postionService.update(model, ms);
		return SUCCESS;
	}
	

	 
	public String getAll(){
		List<PostionVO> pvList = this.postionService.getAll();
		this.setRoot(pvList);
		return SUCCESS;
	}

	/*
	 * 获取所有部门,传递用户
	 */
	public String getAllByUser(){
		List<PostionVO> pvList = this.postionService.getAllByUser(user);
		this.setRoot(pvList);
		return SUCCESS;
	}
	


	public List<Menu> getMs() {
		return ms;
	}


	public void setMs(List<Menu> ms) {
		this.ms = ms;
	}













	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Postion getModel() {
		if(this.model == null)model = new Postion();
		return this.model;
	}

	

}
