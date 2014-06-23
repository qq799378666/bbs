package com.service.impl;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.po.Article;
import com.po.Comment;
import com.po.User;
import com.service.CommentService;
import com.vo.CommentVO;
import com.vo.PageVO;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

	public CommentServiceImpl(){
		this.thisClass = Comment.class;
	}
	
	/*
	 * 保存评论,缺少字符串安全验证
	 * 将评论的用户设置为当前用户,时间设置为当前时间
	 * (non-Javadoc)
	 * @see com.service.CommentService#save(com.po.Comment)
	 */
	@Override
	public Comment add(Comment comment,String verityCode) {
		this.checkVerityCode(verityCode);
		comment.setUser(getCurUser());
		comment.setTime(new Timestamp(System.currentTimeMillis()));
		this.commentDAO.save(comment);
		return comment;
	}

	@Override
	public PageVO<CommentVO> getPageToVO(Article article) {
		DetachedCriteria dc = DetachedCriteria.forClass(Comment.class);
		dc.add(Restrictions.eq("article", article));
		return this.dealToVO(this.commentDAO.getPageVO(dc));
	}
	
	@Override
	public PageVO<Comment> getPage(Article article) {
		DetachedCriteria dc = DetachedCriteria.forClass(Comment.class);
		dc.add(Restrictions.eq("article", article));
		return this.commentDAO.getPageVO(dc);
	}
	
	
	private PageVO<CommentVO> dealToVO(PageVO<Comment> pv){
		List<CommentVO> list = new ArrayList<CommentVO>();
		for(Comment c:pv.getRows()){
			CommentVO cv = new CommentVO();
			BeanUtils.copyProperties(c, cv);
			User u = c.getUser();
			if(u!=null){
				cv.setUserId(u.getId());
				cv.setUserNickname(u.getNickname());
			}
			list.add(cv);
		}
		PageVO<CommentVO> pvv = new PageVO<CommentVO>();
		BeanUtils.copyProperties(pv, pvv);
		pvv.setRows(list);
		return pvv;
	}


	
	
}
