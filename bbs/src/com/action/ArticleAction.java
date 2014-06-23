package com.action;


import java.util.ArrayList;
import java.util.List;

import com.dto.ParamDTO;
import com.opensymphony.xwork2.ModelDriven;
import com.po.Article;
import com.po.Comment;
import com.po.Module;
import com.vo.PageVO;

public class ArticleAction extends BaseAction<Article> implements ModelDriven<Article> {
	private Article model;
	
	private PageVO<Article> articlePV;
	private String sw;// 搜索的文本
	private Integer m;//版块id
	private Boolean g;//是否为精华帖
	private List<Module> moduleList;
	private PageVO<Comment> commentPV;
	private String mrname;//版块名称,待完善
	/*
	 * 跳转到添加页面
	 */
	public String toAdd(){
		this.moduleList = this.moduleService.getAll();
		Module module = new Module();
		module.setId(null);
		module.setRname("全部");
		this.moduleList.add(0, module);
		if(m!=null)
		mrname = this.moduleService.findById(m).getRname();
		System.out.println("最终modules的长度为:"+this.moduleList.size());
		return SUCCESS;
	}
	
	/*
	 * 跳转到添加成功页面
	 */
	public String toAddSuccess(){
		
		return SUCCESS;
	}
	
	public String list() throws Exception {
		if(sw!=null)
		sw = new String(sw.getBytes("ISO-8859-1"),"utf-8");//防止乱码
		
		List<ParamDTO> pvs = new ArrayList<ParamDTO>();
		if(g!=null){
			pvs.add(new ParamDTO("good", g, "eq"));
		}
		
		if(sw!=null){
			pvs.add(new ParamDTO("title",sw,"like"));
		}
		
		if(m!=null){
			Module module = null;
			module = new Module();
			module.setId(m);
			pvs.add(new ParamDTO("module", module, "eq"));
		}
		
		this.articlePV = this.articleService.getPage(pvs);
		
		return 	this.toAdd();//........主要是用这里面的构造版块列表树
	}
	
	
	public String detail() {
		this.model = this.articleService.getDetail(model);
		commentPV = this.commentService.getPage(model);
		return SUCCESS;
	}
	
	
	public PageVO<Article> getArticlePV() {
		return articlePV;
	}
	public void setArticlePV(PageVO<Article> articlePV) {
		this.articlePV = articlePV;
	}
	public String getSw() {
		return sw;
	}
	public void setSw(String sw) {
		this.sw = sw;
	}
	public Integer getM() {
		return m;
	}
	public void setM(Integer m) {
		this.m = m;
	}


	public Boolean getG() {
		return g;
	}

	public void setG(Boolean g) {
		this.g = g;
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public String getMrname() {
		return mrname;
	}

	public void setMrname(String mrname) {
		this.mrname = mrname;
	}

	public PageVO<Comment> getCommentPV() {
		return commentPV;
	}
	public void setCommentPV(PageVO<Comment> commentPV) {
		this.commentPV = commentPV;
	}
	
	@Override
	public Article getModel() {
		if(model==null)model = new Article();
		return model;
		
	}
}
