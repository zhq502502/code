package greendog.privilege;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class Test {
	public static void main(String [] a) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//test1();
		test2();
	}
	public static void test1(){
		UserOperimpl oper = new UserOperimpl();
		InvocationHandler hander = new TestProxy(oper);
		Class implClass = oper.getClass();
		/*Class p = Proxy.getProxyClass(implClass.getClassLoader(), implClass.getInterfaces());
		Constructor ct = p.getConstructor(new Class[]{InvocationHandler.class});*/
		UserOper o =(UserOper) Proxy.newProxyInstance(implClass.getClassLoader(),implClass.getInterfaces(),hander);
		o.save("11",11);
	}
	public static void test2(){
		PrivilegeUtil<UserOperimpl> priUtil = new PrivilegeUtil<UserOperimpl>("111", new UserOperimpl());
		UserOper aa = priUtil.getInstace();
		aa.save("11",11);
	}
}
