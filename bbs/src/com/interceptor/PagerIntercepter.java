package com.interceptor;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.share.SystemContext;

public class PagerIntercepter extends AbstractInterceptor {

	private String pageName;
	private String pageSizeName;
	
	
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public void setPageSizeName(String pageSizeName) {
		this.pageSizeName = pageSizeName;
	}
	@Override
	public void init() {
		super.init();
	}
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Integer page = null;
		Integer pageSize = null;
		try{
			 page = Integer.parseInt(ServletActionContext.getRequest()
						.getParameter(pageName));
			 pageSize =  Integer.parseInt(ServletActionContext.getRequest()
					 .getParameter(pageSizeName));
		}catch(Exception e){
			
		}
		
		SystemContext.setPagesize(pageSize);
		SystemContext.setPage(page);
		try {
			return invocation.invoke();
		} finally {
			SystemContext.removePage();
			SystemContext.removePagesize();
		}
	}
}
