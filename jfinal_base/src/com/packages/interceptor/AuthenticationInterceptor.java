package com.packages.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class AuthenticationInterceptor implements Interceptor{

	@Override
	public void intercept(ActionInvocation ai) {
		Controller controller = ai.getController();
//		System.out.println("zzzzzz"+ai.getMethodName());
//		System.out.println("zzzzzz"+ai.getViewPath());
//		System.out.println("zzzzzz"+ai.getController().getRequest().getRequestURI());
        if(controller.getSessionAttr("user") != null||true){
            ai.invoke();
        }else{
            controller.setAttr("msg", "需要登录才可以进行改操作：）");
            controller.render("/login");
        }
	}

}
