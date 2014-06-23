package com.service;


import java.util.List;

import com.dto.ParamDTO;
import com.po.Article;
import com.po.Module;
import com.po.User;
import com.vo.ArticleSmartVO;
import com.vo.PageVO;

public interface ArticleService {
	Article getDetail(Article article);
	public Article save(Article article);
	
	PageVO<Article> getPage(String sw,User m);
	PageVO<Article> getPage(List<ParamDTO> pvs);
	
	PageVO<Article> searchIndex();
	
	public PageVO<ArticleSmartVO> getPageToSmartVO(List<ParamDTO> pvs);
	void del(Article model);
	void changeState(Article model);
	
}
