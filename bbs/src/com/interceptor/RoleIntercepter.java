package com.interceptor;



import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class RoleIntercepter extends AbstractInterceptor {
	@Override
	public void init() {
		System.out.println("权限过滤器");
		super.init();
	}
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("权限过滤");
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		try{
			if(session.get("user")==null){
				System.out.println("没有登录");
			}
			
		Set<String> roles = (Set<String>)session.get("roles");
		String aName = invocation.getInvocationContext().getName();
		
		if(!roles.contains(aName)){
			System.out.println("没有权限:"+aName);
			throw new RuntimeException("not power");
		}
		}catch(Exception e){
			System.out.println("权限检查是异常");
		}
		
		return invocation.invoke();
		
	}
}
