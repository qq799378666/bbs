package com.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dto.ParamDTO;
import com.po.Menu;
import com.po.Postion;
import com.po.User;
import com.service.PostionService;
import com.vo.PageVO;
import com.vo.PostionVO;

@Service("postionService")
public class PostionServiceImpl extends BaseServiceImpl<Postion> implements
		PostionService {

	public PostionServiceImpl(){
		this.thisClass = Postion.class;
	}
	
	
/*	private List<PostionVO> dealToVO(Collection<Postion> list){
		List<PostionVO> vList = new ArrayList<PostionVO>();
		for(Postion l:list){
			PostionVO lv = new PostionVO();
			BeanUtils.copyProperties(l, lv);
			vList.add(lv);
		}
		return vList;
	}
	
	private PageVO<PostionVO> dealToVO(PageVO<Postion> pv){
		PageVO<PostionVO> pvv = new PageVO<PostionVO>();
		BeanUtils.copyProperties(pv, pvv);
		pvv.setRows(dealToVO(pv.getRows()));
		return pvv;
	}
	
	*/
	/*
	private List<PostionVO> dealToVO(Collection<Postion> postions){
		List<PostionVO> pvs = new ArrayList<PostionVO>();
		for(Postion p:postions){
			PostionVO pv = new PostionVO();
			BeanUtils.copyProperties(p, pv);
			pvs.add(pv);
		}
		return pvs;
	}
	*/
	
	/*
	 * 添加职位，传入职位Postion对象已经关联的菜单Menu列表职位的roles设置为menu列表里面的所有roles集合方法不够有效率
	 * (non-Javadoc)
	 * 
	 * @see com.service.PostionService#add(com.po.Postion, java.util.List)
	 */
	@Override
	public Postion add(Postion postion, List<Menu> menus) {
		Set<String> rolesSet = new HashSet<String>();
		Set<Menu> mSet = new HashSet<Menu>();
		if (menus != null) {
			for (Menu m : menus) {
				m = this.menuDAO.findById(m.getId());
				if (m == null)
					continue;
				//如果菜单有父类,这将父类也添加进去,
				if(m.getMenu()!=null){
					mSet.add(m.getMenu());
				}
				String mRoles = m.getRoles();
				String[] arr = mRoles.split(",");
				for (String r : arr) {
					System.out.println("权限:"+r);
					rolesSet.add(r);
				}
//				mSet.add(m);
			}
			mSet.addAll(menus);
		}
		postion.setRoles(rolesSet.toString());
		this.postionDAO.save(postion);
		return postion;
	}
	/*
	 * 修改职位，传入职位对象Postion（需要包含id），新关联的菜单列表（旧关联的菜单列表将被废弃）、
	 * (non-Javadoc)
	 * @see com.service.PostionService#update(com.po.Postion, java.util.List)
	 */
	@Override
	public Postion update(Postion postion, List<Menu> menus) {
		Set<String> rolesSet = new HashSet<String>();
		Set<Menu> mSet = new HashSet<Menu>();
		if (menus != null) {
			for (Menu m : menus) {
				m = this.menuDAO.findById(m.getId());
				if (m == null)
					continue;
				//如果菜单有父类,这将父类也添加进去,
				if(m.getMenu()!=null){
					mSet.add(m.getMenu());
				}
				String mRoles = m.getRoles();
				String[] arr = mRoles.split(",");
				for (String r : arr) {
					System.out.println("权限:"+r);
					rolesSet.add(r);
				}
//				mSet.add(m);
			}
			mSet.addAll(menus);
		
		}
		postion.setRoles(rolesSet.toString());
		postion.setMenus(mSet);
		this.postionDAO.update(postion);
		return postion;
	}
	@Override
	public List<PostionVO> getAllByUser(User user) {
		List<Postion> pList = this.postionDAO.getAll();
		user = this.userDAO.findById(user.getId());
		Set<Postion> uPostions = user.getPostions();
		List<PostionVO> vList = new ArrayList<PostionVO>();
		for(Postion l:pList){
			PostionVO lv = new PostionVO();
			BeanUtils.copyProperties(l, lv);
			if(uPostions.contains(l)){
				lv.setChecked(true);
			}else{
				lv.setChecked(false);
			}
			vList.add(lv);
		}
		
		return vList;
	}

	@Override
	public List<PostionVO> getAll() {
		List<Postion> pList = this.postionDAO.getAll();
		List<PostionVO> vList = new ArrayList<PostionVO>();
		for(Postion l:pList){
			PostionVO lv = new PostionVO();
			BeanUtils.copyProperties(l, lv);
			vList.add(lv);
		}
		return vList;
	}
	
	@Override
	public PageVO<PostionVO> getPageVOToVO(List<ParamDTO> pvs) {
		DetachedCriteria dc = this.createDetachedCriteria(pvs);
		PageVO<Postion> pv = this.postionDAO.getPageVO(dc);
		List<Postion> pList = pv.getRows();
		List<PostionVO> vList = new ArrayList<PostionVO>();
		for(Postion l:pList){
			PostionVO lv = new PostionVO();
			BeanUtils.copyProperties(l, lv);
			vList.add(lv);
		}
		PageVO<PostionVO> pvv = new PageVO<PostionVO>();
		BeanUtils.copyProperties(pv, pvv);
		pvv.setRows(vList);
		return pvv;
	}

	@Override
	public void del(Postion model) {
//		this.postionDAO.del(model);
		this.postionDAO.delToSql(model.getId());
		// TODO Auto-generated method stub
	}
/*
	@Override
	public List<PostionVO> getPostionVOListByUser(User model) {
		model = this.userDAO.findById(model.getId());
		return this.dealToVO(model.getPostions());
	}*/



}
