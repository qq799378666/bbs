package com.form;

public class UserAlterPwdForm {

	private String username;
	private String pwd;
	private String newPwd;
	private String verityCode;// 验证码,暂时忽略
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getVerityCode() {
		return verityCode;
	}
	public void setVerityCode(String verityCode) {
		this.verityCode = verityCode;
	}

}
