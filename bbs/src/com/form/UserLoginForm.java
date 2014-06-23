package com.form;


public class UserLoginForm {

	private String username;
	private String pwd;
	private Integer branchId;
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
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public String getVerityCode() {
		return verityCode;
	}
	public void setVerityCode(String verityCode) {
		this.verityCode = verityCode;
	}
	

}
