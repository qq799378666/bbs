package com.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dao.ArticleDAO;
import com.po.Article;
import com.po.Module;
@Repository("articleDAO")
public class ArticleDAOImpl extends BaseDAOImpl<Article> implements ArticleDAO {
	public ArticleDAOImpl(){
		this.thisClass = Article.class;
	}

	/*
	 * 根据版块获取版块下的文章的数量,如果传入的module为null,则返回所有文章的数量
	 * (non-Javadoc)
	 * @see com.dao.ArticleDAO#getCount(com.po.Module)
	 */
	@Override
	public int getCount(Module module) {
		String hql = "select count(*) from Article ";
		if(module!=null){
			hql += "where module=?";
		}
		Query query = this.getSession().createQuery(hql);
		if(module!=null){
			query.setParameter(0, module);
		}
		return ((Number)query.uniqueResult()).intValue();
	}

}
