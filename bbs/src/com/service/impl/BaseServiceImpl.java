package com.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.dao.ArticleDAO;
import com.dao.CommentDAO;
import com.dao.MenuDAO;
import com.dao.ModuleDAO;
import com.dao.PostionDAO;
import com.dao.UserDAO;
import com.dto.ParamDTO;
import com.opensymphony.xwork2.ActionContext;
import com.po.User;

public abstract class BaseServiceImpl<T>{
	@Resource(name="articleDAO")
	protected ArticleDAO articleDAO;
	@Resource(name="userDAO")
	protected UserDAO userDAO;
	@Resource(name="moduleDAO")
	protected ModuleDAO moduleDAO;
	@Resource(name="commentDAO")
	protected CommentDAO commentDAO;
	@Resource(name="menuDAO")
	protected MenuDAO menuDAO;
	@Resource(name="postionDAO")
	protected PostionDAO postionDAO;
	
	protected Class thisClass;
	
	
	protected void checkVerityCode(String verityCode){
		Map<String,Object> session = ActionContext.getContext().getSession();
		Object yzm = session.get("yzm");
		session.remove("yzm");
		if(yzm==null||!yzm.equals(verityCode)){
			throw new RuntimeException("验证码错误");
		}
	}
	
	protected DetachedCriteria createDetachedCriteria(List<ParamDTO> fs){
		DetachedCriteria c = DetachedCriteria.forClass(thisClass);
		if(fs!=null)
			for(ParamDTO sf:fs){
				if("eq".equals(sf.getBridge())){
					c.add(Restrictions.eq(sf.getKey(), sf.getP1()));
				}
				else if("desc".equals(sf.getBridge())){
					c.addOrder(Order.desc(sf.getKey()));
				}
				else if("between".equals(sf.getBridge())){
					if(sf.getP1()!=null&&sf.getP2()!=null){
						c.add(Restrictions.between(sf.getKey(), sf.getP1(), sf.getP2()));
					}
					else if(sf.getP1()!=null&&sf.getP2()==null){
						//只提供了p1
						c.add(Restrictions.gt(sf.getKey(), sf.getP1()));
					}
					else if(sf.getP1()==null&&sf.getP2()!=null){
						//只提供了p2
						c.add(Restrictions.lt(sf.getKey(), sf.getP2()));
					}
				}
				else if("like".equals(sf.getBridge())){
					c.add(Restrictions.like(sf.getKey(),"%"+sf.getP1()+"%"));
				}else{
					System.out.println("异常:"+sf.getBridge());
				}
			}
		return c;
	}
	
	
	protected User getCurUser(){
		Object uo = ActionContext.getContext().getSession().get("user");
		if(uo==null)throw new RuntimeException("您还没有登陆,请登录");
		User u = (User)uo;
		return u;
	}
	protected User getCurUserBySessionAndSql(){
		User u = this.getCurUser();
		u = this.userDAO.findById(u.getId());
		return u;
	}
	
}
