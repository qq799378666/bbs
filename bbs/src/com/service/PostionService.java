package com.service;

import java.util.List;

import com.dto.ParamDTO;
import com.po.Menu;
import com.po.Postion;
import com.po.User;
import com.vo.PageVO;
import com.vo.PostionVO;

public interface PostionService {
	public Postion add(Postion postion,List<Menu> menus);
	public Postion update(Postion postion,List<Menu> menus);
	public List<PostionVO> getAllByUser(User user);
	public PageVO<PostionVO> getPageVOToVO(List<ParamDTO> pvs);
	public void del(Postion model);
//	List<PostionVO> getPostionVOListByUser(User model);
	public List<PostionVO> getAll();
	
}
