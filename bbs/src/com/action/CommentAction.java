package com.action;

import com.opensymphony.xwork2.ModelDriven;
import com.po.Comment;
import com.vo.CommentVO;
import com.vo.PageVO;

public class CommentAction extends BaseAction implements ModelDriven<Comment> {
	private Comment model;
	//public Article article;
	
	public PageVO<CommentVO> commentPV;

	public String comment() {
		// 页面传递过来文章对象:article.id,这样会被model接收到,从model上取得文章对象(合理性不太符合)
		commentPV = this.commentService.getPageToVO(model.getArticle());
		return SUCCESS;

	}
	
	

	@Override
	public Comment getModel() {
		if (model == null)
			model = new Comment();
		return model;
	}

}
