package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.dao.ModuleDAO;
import com.po.Module;
@Repository("moduleDAO")
public class ModuleDAOImpl extends BaseDAOImpl<Module> implements ModuleDAO {
	public ModuleDAOImpl(){
		this.thisClass = Module.class;
	}
	
	
}
