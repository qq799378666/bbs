package com.action.json;


import com.opensymphony.xwork2.ModelDriven;
import com.po.Comment;
import com.vo.CommentVO;
import com.vo.PageVO;

public class JSONCommentAction extends JSONBaseAction implements ModelDriven<Comment>{
	private Comment model;
	private String verityCode;
	
	
	//不需要任何权限
	public String add(){
		this.commentService.add(model,verityCode);
		return SUCCESS;
	}
	
	
	public String getVerityCode() {
		return verityCode;
	}


	public void setVerityCode(String verityCode) {
		this.verityCode = verityCode;
	}


	@Override
	public Comment getModel() {
		if(model==null)model = new Comment();
		return model;
	}

}
