package com.action;




import com.opensymphony.xwork2.ModelDriven;
import com.po.Article;
import com.po.User;
import com.vo.PageVO;

public class UserAction extends BaseAction implements ModelDriven<User> {
	
	private User model;
	private PageVO<Article> newestArticlePV;
	
	private String href = "/";
	
	public String toAlter(){
		this.model = this.userService.getCurNeedAlterUser();
		return SUCCESS;
	}
	public String toLogin(){
		
		return SUCCESS;
	}
	public String toFindPwd(){
		return SUCCESS;
	}
	
	
	public String logout(){
		this.userService.logout();
		return SUCCESS;
	}

	public String home(){
		this.model = this.userService.getDetail(model);
		this.newestArticlePV = this.articleService.getPage(null, model);
		return SUCCESS;
	}
	
	public String detail(){
		this.model = this.userService.getDetail(model);
		return SUCCESS;
	}
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}


	public PageVO<Article> getNewestArticlePV() {
		return newestArticlePV;
	}


	public void setNewestArticlePV(PageVO<Article> newestArticlePV) {
		this.newestArticlePV = newestArticlePV;
	}

	@Override
	public User getModel() {
		if(model == null)model = new User();
		return model;
	}

}
