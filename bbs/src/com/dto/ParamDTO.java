package com.dto;

public class ParamDTO {
	private String key;
	private Object p1;
	private Object p2;
	private String bridge;
	
	public ParamDTO(){
	}
	public ParamDTO(String key,String bridge){
		this.key = key;
		this.bridge = bridge;
	}
	
	public ParamDTO(String key,Object value,String bridge){
		this.key = key;
		this.p1 = value;
		this.bridge = bridge;
	}
	
	public ParamDTO(String key,Object p1,Object p2,String bridge){
		this.key = key;
		this.p1 = p1;
		this.p2 = p2;
		this.bridge = bridge;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getP1() {
		return p1;
	}

	public void setP1(Object p1) {
		this.p1 = p1;
	}

	public Object getP2() {
		return p2;
	}

	public void setP2(Object p2) {
		this.p2 = p2;
	}

	public String getBridge() {
		return bridge;
	}

	public void setBridge(String bridge) {
		this.bridge = bridge;
	}


}
