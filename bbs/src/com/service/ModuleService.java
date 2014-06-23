package com.service;

import java.util.List;

import com.dto.ParamDTO;
import com.po.Module;
import com.po.User;
import com.vo.ModuleTree;
import com.vo.ModuleVO;
import com.vo.PageVO;

public interface ModuleService {
	List<Module> getAll();
	//Module findById(Integer id);
	List<ModuleVO> getAllToVO();
	Module findById(Integer m);
	public PageVO<ModuleVO> getPageVOToVO(List<ParamDTO> pvs);
	List<ModuleTree> getAllToTreeByUser(User user);
	void appointModerator(User user, Module model);
	void dismissModerator(User user, Module model);
	List<ModuleTree> getCurUserAuthorityTree();
	void add(Module model);
	void del(Module model);
	void update(Module model);
}
