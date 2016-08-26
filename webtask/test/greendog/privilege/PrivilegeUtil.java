package greendog.privilege;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class PrivilegeUtil<T> {
	String PriviCode = "";
	Object obj = null;
	public PrivilegeUtil(String priviCode,Object obj){
		this.PriviCode = priviCode;
		this.obj = obj;
	}
	public T getInstace(){
		InvocationHandler hander = new TestProxy(obj);
		Class implClass = obj.getClass();
		return (T) Proxy.newProxyInstance(implClass.getClassLoader(),implClass.getInterfaces(),hander);
	}
}
