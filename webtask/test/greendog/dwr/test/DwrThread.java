package greendog.dwr.test;

import java.util.Collection;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

public class DwrThread extends Thread {
	public static int num = 0;
	@Override
	public void run() {
		for(;num<=20;){
			System.out.println("==========调用了send方法"+num+"次==========");  
			ScriptBuffer scriptBuffer = new ScriptBuffer(); //构造js脚本  
			WebContext webContext=WebContextFactory.get();  
			Collection<ScriptSession> sessionList = webContext.getAllScriptSessions();
			for(ScriptSession myScSession:sessionList){
				scriptBuffer.appendScript("dwrtest(");  
				scriptBuffer.appendData("发送消息"+(num++));  
				scriptBuffer.appendScript(")");  
				Util util = new Util(myScSession);  
				util.addScript(scriptBuffer); //向客户端推送消息  
			}
			try {
				this.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
