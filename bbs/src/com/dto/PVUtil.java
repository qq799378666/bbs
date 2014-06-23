package com.dto;

import java.util.ArrayList;
import java.util.List;

public class PVUtil {
	private int page;
	private int  pageSize;
	private List<ParamDTO> params = new ArrayList<ParamDTO>(0);
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<ParamDTO> getParams() {
		return params;
	}
	public void setParams(List<ParamDTO> params) {
		this.params = params;
	}
	
}
