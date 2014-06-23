package com.service;


import java.util.List;

import com.dto.ParamDTO;
import com.form.UserLoginForm;
import com.form.UserRegisterForm;
import com.po.Postion;
import com.po.User;
import com.vo.PageVO;
import com.vo.PostionVO;
import com.vo.UserVO;

public interface UserService {
	User login(UserLoginForm u);
	User register(UserRegisterForm userRegisterForm);
	void logout();
	boolean isexist(User user);
	User getDetail(User m);
	void alterCurUser(User model);
	//User getCurUser();
	User getCurNeedAlterUser();
	public PageVO<UserVO> getPageVOToVO(List<ParamDTO> pvs);
	void addPostion(User model, Postion postion);
	void delPostion(User model, Postion postion);
	
	
}
