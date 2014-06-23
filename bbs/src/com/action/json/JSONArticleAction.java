package com.action.json;


import com.opensymphony.xwork2.ModelDriven;
import com.po.Article;
import com.po.Module;
import com.vo.ArticleSmartVO;
import com.vo.PageVO;

public class JSONArticleAction extends JSONBaseAction<Article> implements ModelDriven<Article>{
	public String add(){
		this.p("id", this.articleService.save(model).getId());
		return SUCCESS;
	}
	
	private PageVO<ArticleSmartVO> articlePV;
	private String sw;
	private Module module;
	private Boolean good;
	
	public String del(){
		this.articleService.del(model);
		return SUCCESS;
	}
	
	public String changeState(){
		this.articleService.changeState(model);
		return SUCCESS;
	}
	
	
	public String getPageVOToSmart(){
		controlList();
		this.articlePV = this.articleService.getPageToSmartVO(pvs);
		this.setRoot(articlePV);
		return SUCCESS;
	}


	public PageVO<ArticleSmartVO> getArticlePV() {
		return articlePV;
	}




	public void setArticlePV(PageVO<ArticleSmartVO> articlePV) {
		this.articlePV = articlePV;
	}






	public String getSw() {
		return sw;
	}


	public void setSw(String sw) {
		this.sw = sw;
	}


	public Module getModule() {
		return module;
	}


	public void setModule(Module module) {
		this.module = module;
	}


	public Boolean getGood() {
		return good;
	}


	public void setGood(Boolean good) {
		this.good = good;
	}


	@Override
	public Article getModel() {
		if(model==null)model = new Article();
		return model;
	}

}
