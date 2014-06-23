package com.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dto.ParamDTO;
import com.po.Article;
import com.po.Module;
import com.po.User;
import com.service.ArticleService;
import com.share.SystemContext;
import com.vo.ArticleSmartVO;
import com.vo.ArticleVO;
import com.vo.PageVO;
@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {
	public ArticleServiceImpl(){
		this.thisClass = Article.class;
	}
	
	/*
	 * 
	 */
	private PageVO<ArticleSmartVO> dealToSmartVO(PageVO<Article> pv){
		
		Collection<Article> articles = pv.getRows();
		
		List<ArticleSmartVO> vList = new ArrayList<ArticleSmartVO>();
		//效率不够高,会查询很多次文章的版块/所属用户,可以考虑在之前执行一段语句将文章列表所对应的版块/用户一次性查出来
		
		for(Article a:articles){
			ArticleSmartVO asv = new ArticleSmartVO();
			BeanUtils.copyProperties(a, asv);
			Module m = a.getModule();
			if(m!=null){
				asv.setModuleId(m.getId());
				asv.setModuleRname(m.getRname());
			}
			
			User u = a.getUser();
			if(u!=null){
				asv.setUserId(u.getId());
				asv.setUserNickname(u.getNickname());
			}
			
			vList.add(asv);
		}
		PageVO<ArticleSmartVO> svpv = new PageVO<ArticleSmartVO>();
		BeanUtils.copyProperties(pv, svpv);
		svpv.setRows(vList);
		return svpv;
	}
	
	@Override
	public Article getDetail(Article article){
		return this.articleDAO.findById(article.getId());
	}
	
	
/*
 * 保存文章,设置文章的用户为当前用户,时间为当前时间,帖子的good属性默认设置为false
 * (non-Javadoc)
 * @see com.service.ArticleService#save(com.po.Article)
 */
	@Override
	public Article save(Article article) {
		article.setUser(this.getCurUser());
		article.setTime(new Timestamp(System.currentTimeMillis()));
		article.setGood(false);
		this.articleDAO.save(article);
		Module module = article.getModule();
		/*if(module!=null){//更新版块,该操作交给了触发器
			module = this.moduleDAO.findById(module.getId());
			module.setCount(this.articleDAO.getCount(module));
			module.setArticle(article);//设置最新文章为该文章
		}*/
		return article;
	}

/*
 * 查看用户时显示的帖子列表
 * 通过用户,标题搜索,
 * ,如果没有提供页/页大小,则默认为1,15
 * (non-Javadoc)
 * @see com.service.ArticleService#getPage(java.lang.String, com.po.User)
 */
	@Override
	public PageVO<Article> getPage(String sw,User m) {
		DetachedCriteria dc = DetachedCriteria.forClass(Article.class);
		if(SystemContext.getPage()==null)SystemContext.setPage(1);
		if(SystemContext.getPageSize()==null)SystemContext.setPagesize(15);
		if(sw!=null)dc.add(Restrictions.like("title", sw));
		if(m!=null)dc.add(Restrictions.eq("user", m));
		dc.addOrder(Order.desc("time"));
		return this.articleDAO.getPageVO(dc);
	}

	/*
	 * 首页的列表,默认第一页,页大小15,按时间排序
	 * (non-Javadoc)
	 * @see com.service.ArticleService#searchIndex()
	 */
	@Override
	public PageVO<Article> searchIndex() {
		DetachedCriteria dc = DetachedCriteria.forClass(Article.class);
		SystemContext.setPage(1);
		SystemContext.setPagesize(15);
		dc.addOrder(Order.desc("time"));
		return this.articleDAO.getPageVO(dc);
	}
	/*
	 * 获取PageVO<ArticleSmartVO>,根据时间排序
	 * (non-Javadoc)
	 * @see com.service.ArticleService#getPageToSmartVO(java.util.List)
	 */
	@Override
	public PageVO<ArticleSmartVO> getPageToSmartVO(List<ParamDTO> pvs) {
		DetachedCriteria dc = this.createDetachedCriteria(pvs);
		dc.addOrder(Order.desc("time"));
		return this.dealToSmartVO(this.articleDAO.getPageVO(dc));
	}
	/*
	 * 按时间time排序,如果没有设置页码则默认设置为25
	 * (non-Javadoc)
	 * @see com.service.ArticleService#getPage(java.util.List)
	 */
	@Override
	public PageVO<Article> getPage(List<ParamDTO> pvs) {
		DetachedCriteria dc = this.createDetachedCriteria(pvs);
		dc.addOrder(Order.desc("time"));
		if(SystemContext.getPageSize()==null)//如果没有设置页码则默认设置为25
		SystemContext.setPagesize(25);
		return this.articleDAO.getPageVO(dc);
	}

	/*
	 * 删除帖子
	 * (non-Javadoc)
	 * @see com.service.ArticleService#del(com.po.Article)
	 */
	@Override
	public void del(Article model) {
		model = this.articleDAO.findById(model.getId());
		if(model==null){
			throw new RuntimeException("删除失败,该帖不存在或已被删除");
		}
		try{
//			this.articleDAO.del(model);
			this.articleDAO.delToSql(model.getId());
		}catch(Exception e){
			throw new RuntimeException("删除失败:"+e.getMessage());
		}
	}

	/*
	 * 更改帖子的精华/普通状态(non-Javadoc)
	 * @see com.service.ArticleService#changeState(com.po.Article)
	 */
	@Override
	public void changeState(Article model) {
		Article a = this.articleDAO.findById(model.getId());
		a.setGood(model.getGood());
	}
}
