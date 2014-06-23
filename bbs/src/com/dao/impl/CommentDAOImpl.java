package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.dao.CommentDAO;
import com.po.Comment;
@Repository("commentDAO")
public class CommentDAOImpl extends BaseDAOImpl<Comment> implements CommentDAO {
	public CommentDAOImpl(){
		this.thisClass = Comment.class;
	}

}
