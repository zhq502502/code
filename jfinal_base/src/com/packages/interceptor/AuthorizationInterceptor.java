package com.packages.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class AuthorizationInterceptor implements Interceptor{

	@Override
	public void intercept(ActionInvocation ai) {
		Controller c = ai.getController();
		/*LoginUser loginuser = c.getSessionAttr("loginuser");
		if(loginuser==null||loginuser.getId()<=0){
			c.render("/login");
		}else{
			long userauth = loginuser.getAuthcode();
			if(loginuser.getAccount().equals("admin")){
				ai.invoke();
			}else{
				String url = ai.getViewPath().replace("/", "");
				if(url.equals("")){
					ai.invoke();
				}else{
					long authcode = SystemInfo.getInstance().getUrlAuth(url);
					if((authcode&userauth)==authcode){
						ai.invoke();
					}else{
						c.render("/403.html");
					}
				}
			}
		}*/
		ai.invoke();
	}

}
