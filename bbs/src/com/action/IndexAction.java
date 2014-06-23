package com.action;

import java.util.List;

import com.po.Article;
import com.po.Module;
import com.vo.ModuleVO;
import com.vo.PageVO;
public class IndexAction extends BaseAction{
	public PageVO<Article> articlePV;
	public List<ModuleVO> moduleList;
	
	public String index(){
		this.articlePV = this.articleService.searchIndex();
		this.moduleList = this.moduleService.getAllToVO();
		return SUCCESS;
	}
	
}