package com.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dto.ParamDTO;
import com.po.Article;
import com.po.Module;
import com.po.User;
import com.service.ModuleService;
import com.util.MyArrays;
import com.vo.ModuleTree;
import com.vo.ModuleVO;
import com.vo.PageVO;
import com.vo.UserSmartVO;

@Service("moduleService")
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements ModuleService {
	public ModuleServiceImpl(){
		this.thisClass = Module.class;
	}
	
	private PageVO<ModuleVO> dealToVo(PageVO<Module> pv){
		Collection<Module> list = pv.getRows();
		List<ModuleVO> vList = new ArrayList<ModuleVO>();
		for(Module m:list){
			ModuleVO mv = new ModuleVO();
			BeanUtils.copyProperties(m, mv);
			Article article = m.getArticle();
			if(article!=null){
				mv.setNewestArticleId(article.getId());
				mv.setNewestArticleTitle(article.getTitle());
			}
			Set<User> uSet = m.getUsers();
			List<UserSmartVO> moderators = new ArrayList<UserSmartVO>();
			for(User u:uSet){
				UserSmartVO usv = new UserSmartVO();
				BeanUtils.copyProperties(u, usv);
				moderators.add(usv);
			}
			mv.setModerators(moderators);
			vList.add(mv);
		}
		PageVO<ModuleVO> pvv = new PageVO<ModuleVO>();
		BeanUtils.copyProperties(pv, pvv);
		pvv.setRows(vList);
		return pvv;
	}
	
	@Override
	public List<Module> getAll() {
		return this.moduleDAO.getAll();
	}

	@Override
	public Module findById(Integer m) {
		return this.moduleDAO.findById(m);
	}

	@Override
	public List<ModuleVO> getAllToVO() {
		List<ModuleVO> voAll = new ArrayList<ModuleVO>();
		List<Module> all = this.moduleDAO.getAll();
		for(Module m:all){
			ModuleVO mv = new ModuleVO();
			BeanUtils.copyProperties(m, mv);
			Article newestArticle = m.getArticle();
			if(newestArticle!=null){
				mv.setNewestArticleId(newestArticle.getId());
				mv.setNewestArticleTitle(newestArticle.getTitle());
			}
			Set<User> uSet = m.getUsers();
			List<UserSmartVO> moderators = new ArrayList<UserSmartVO>();
			for(User u:uSet){
				UserSmartVO usv = new UserSmartVO();
				BeanUtils.copyProperties(u, usv);
				moderators.add(usv);
			}
			mv.setModerators(MyArrays.sort(moderators));
			voAll.add(mv);
		}
		
		return voAll;
	}

	
	
	@Override
	public PageVO<ModuleVO> getPageVOToVO(List<ParamDTO> pvs) {
		DetachedCriteria dc = this.createDetachedCriteria(pvs);
		return this.dealToVo(this.moduleDAO.getPageVO(dc));
	}

	@Override
	public List<ModuleTree> getAllToTreeByUser(User user) {
		System.out.println("执行");
		user = this.userDAO.findById(user.getId());
		List<ModuleTree> treeList = new ArrayList<ModuleTree>();
		for(Module m:this.moduleDAO.getAll()){
			ModuleTree mt = new ModuleTree();
			mt.setId(m.getId());
			mt.setText(m.getRname());
			if(user.getModules().contains(m)){
				mt.setChecked(true);
			}else{
				mt.setChecked(false);
			}
			treeList.add(mt);
		}
		return treeList;
	}

	/*
	 * 获取到当前用户所能管理的版块列表(non-Javadoc)
	 * @see com.service.ModuleService#getCurUserAuthorityTree()
	 */
	@Override
	public List<ModuleTree> getCurUserAuthorityTree() {
		List<ModuleTree> treeList = new ArrayList<ModuleTree>();
		User curUser = this.getCurUserBySessionAndSql();
		Set<Module> mSet = curUser.getModules();
		// TODO Auto-generated method stub
		for(Module m:mSet){
			ModuleTree mt = new ModuleTree();
			mt.setId(m.getId());
			mt.setText(m.getRname());
			treeList.add(mt);
		}
		return MyArrays.sort(treeList);
	}
	
	@Override
	public void appointModerator(User user, Module model) {
		// TODO Auto-generated method stub
		model = this.moduleDAO.findById(model.getId());
		user = this.userDAO.findById(user.getId());
		user.getModules().add(model);
//		model.getUsers().add(user);//为什么model.getUser().add(user)无效
		
		
	}

	@Override
	public void dismissModerator(User user, Module model) {
		// TODO Auto-generated method stub
		model = this.moduleDAO.findById(model.getId());
		user = this.userDAO.findById(user.getId());
//		model.getUsers().remove(user);//为什么无效........
		user.getModules().remove(model);
	}

	@Override
	public void add(Module model) {
		// TODO Auto-generated method stub
		model.setCount(0);
		this.moduleDAO.save(model);
	}

	@Override
	public void del(Module model) {
		// TODO Auto-generated method stub
//		this.moduleDAO.del(model);
		this.moduleDAO.delToSql(model.getId());
	}

	@Override
	public void update(Module model) {
		// TODO Auto-generated method stub
		Module m = this.moduleDAO.findById(model.getId());
		m.setRname(model.getRname());
		m.setIntro(model.getIntro());
	}


	
	
	
/*	private List<ModuleVO> dealToVO(List<Module> modules){
		List<ModuleVO> list = new ArrayList<ModuleVO>();
		for(Module module:modules){
			ModuleVO mv = new ModuleVO();
			BeanUtils.copyProperties(module, mv);
			list.add(mv);
		}
		return list;
	}

	@Override
	public Module findById(Integer id) {
		return this.moduleDAO.findById(id);
	}*/

}
