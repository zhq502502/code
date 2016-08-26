package greendog.privilege;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestProxy implements InvocationHandler{
	private Object sup;
	public TestProxy(Object sup){
		this.sup = sup;
	}
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before calling " + method.toGenericString());
        method.invoke(sup,args);
        System.out.println("after calling " + method);
        return null; 
	}

}
 