package com.dao;

import com.po.Article;
import com.po.Module;

public interface ArticleDAO extends BaseDAO<Article> {
	public int getCount(Module module);
}
