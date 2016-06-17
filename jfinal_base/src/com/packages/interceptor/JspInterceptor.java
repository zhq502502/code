package com.packages.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class JspInterceptor implements Interceptor  {

	@Override
	public void intercept(ActionInvocation ai) {
		System.out.println("befor");
		ai.invoke();
		System.out.println("after");
	}

}
