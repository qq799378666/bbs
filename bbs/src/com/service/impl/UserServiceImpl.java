package com.service.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dto.ParamDTO;
import com.form.UserLoginForm;
import com.form.UserRegisterForm;
import com.opensymphony.xwork2.ActionContext;
import com.po.Menu;
import com.po.Postion;
import com.po.User;
import com.service.UserService;
import com.util.MD5;
import com.vo.PageVO;
import com.vo.UserVO;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	public UserServiceImpl(){
		this.thisClass = User.class;
	}
	
	private PageVO<UserVO> dealToVo(PageVO<User> pv){
		Collection<User> users = pv.getRows();
		List<UserVO> vList = new ArrayList<UserVO>();
		for(User u:users){
			UserVO uv = new UserVO();
			BeanUtils.copyProperties(u, uv);
			vList.add(uv);
		}
		PageVO<UserVO> pvv = new PageVO<UserVO>();
		BeanUtils.copyProperties(pv,pvv);
		pvv.setRows(vList);
		return pvv;
	}
	
	
	/*
	 * 登录,登录失败会抛出响应的异常,登录成功会将user设置到session中
	 * (non-Javadoc)
	 * @see com.service.UserService#login(com.form.UserLoginForm)
	 */
	@Override
	public User login(UserLoginForm u) {
		User user = null;
		try{
			user = userDAO.findByUsername(u.getUsername());
		}catch(Exception e){
			throw new RuntimeException("账号不存在");
		}
		if(!user.getPwd().equals(MD5.toMD5(u.getPwd()))){
			throw new RuntimeException("密码错误");
		}
		Hibernate.initialize(user.getPostions());
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.put("user", user);
		
		
		//存储用户权限
		Set<String> roles = new HashSet<String>();
		
		Set<Postion> pset = user.getPostions();
		for(Postion p:pset){
			Set<Menu> mset = p.getMenus();
			for(Menu menu:mset){
				String mRoles = menu.getRoles();
				String[] arr = mRoles.split(",");
				for (String r : arr) {
					roles.add(r);
				}
				
			}
		}
		
		session.put("roles", roles);
		
		return user;
	}

	/*
	 * 注册用户,传入注册表单
	 * ,注册成功后将user存入到session,表示登录
	 * (non-Javadoc)
	 * @see com.service.UserService#register(com.form.UserRegisterForm)
	 */
	@Override
	public User register(UserRegisterForm userRegisterForm) {
		this.checkVerityCode(userRegisterForm.getVerityCode());
		
		boolean uexist = this.userDAO.checkUsername(userRegisterForm.getUsername());
		if(uexist)throw new RuntimeException("用户名已存在");
		
		User user = new User();
		user.setUsername(userRegisterForm.getUsername());
		user.setPwd(MD5.toMD5(userRegisterForm.getPwd()));
		user.setSex(userRegisterForm.getSex());
		user.setEmail(userRegisterForm.getEmail());
		user.setNickname("新兵");
		user.setScore(0);
		user.setMoney(0);
		user.setRank(0);
		this.userDAO.save(user);
		//自动登录
		ActionContext.getContext().getSession().put("user", user);
		return user;
	}

	@Override
	public void logout() {
		ActionContext.getContext().getSession().remove("user");
	}

	@Override
	public User getDetail(User m) {
		return this.userDAO.findById(m.getId());
	}

	/*
	 * 根据User里面的用户名判断用户是否存在
	 * (non-Javadoc)
	 * @see com.service.UserService#isexist(com.po.User)
	 */
	@Override
	public boolean isexist(User user) {
		return this.userDAO.checkUsername(user.getUsername());
	}

	/*
	 * 修改当前用户信息,暂时只能修改性别,昵称
	 * user中只需要提供所需要更改的项
	 * ,修改后同步到session里面的用户
	 * (non-Javadoc)
	 * @see com.service.UserService#alter(com.po.User)
	 */
	@Override
	public void alterCurUser(User model) {
		User u = this.getCurUserBySessionAndSql();
		u.setSex(model.getSex());
		u.setNickname(model.getNickname());
		
		//将存入到session里面的用户也同步改一下
		User user = this.getCurUser();
		user.setSex(model.getSex());
		user.setNickname(model.getNickname());
		ActionContext.getContext().getSession().put("user", user);
		
		
	}

	@Override
	public User getCurNeedAlterUser() {
		return this.getCurUserBySessionAndSql();
	}

	@Override
	public PageVO<UserVO> getPageVOToVO(List<ParamDTO> pvs) {
		DetachedCriteria dc = this.createDetachedCriteria(pvs);
		return this.dealToVo(this.userDAO.getPageVO(dc));
	}

	@Override
	public void addPostion(User model, Postion postion) {
		model = this.userDAO.findById(model.getId());
		postion = this.postionDAO.findById(postion.getId());
		model.getPostions().add(postion);
	}
	
	@Override
	public void delPostion(User model, Postion postion) {
		model = this.userDAO.findById(model.getId());
		if(model.getId().equals(this.getCurUser().getId())){
			throw new RuntimeException("系统不接受你的辞职");
		}
		
		postion = this.postionDAO.findById(postion.getId());
		System.out.println("删除了职位:"+postion.getRname());
		model.getPostions().remove(postion);
	}

}
