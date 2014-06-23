package com.service;

import com.po.Article;
import com.po.Comment;
import com.vo.CommentVO;
import com.vo.PageVO;


public interface CommentService {
	Comment add(Comment comment,String verityCode);
	PageVO<CommentVO> getPageToVO(Article article);
	PageVO<Comment> getPage(Article article);
}
