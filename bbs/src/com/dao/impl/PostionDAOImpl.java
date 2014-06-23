package com.dao.impl;

import org.springframework.stereotype.Repository;
import com.dao.PostionDAO;
import com.po.Postion;

@Repository("postionDAO")
public class PostionDAOImpl extends BaseDAOImpl<Postion> implements PostionDAO {
	public PostionDAOImpl(){
		this.thisClass = Postion.class;
	}

}
