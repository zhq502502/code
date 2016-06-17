package com.packages.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class TempInterceptor implements Interceptor  {

	@Override
	public void intercept(ActionInvocation ai) {
		//Controller c = ai.getController();
		//String parm = c.getPara(0);
		//System.out.println("parm:"+parm);	
		ai.invoke();
		//c.setAttr("empList", Tblemployee_dept.dao.getValidManager(parm));
		System.out.println("---dialog data intercept---");
	}

}
