package greendog.dwr.test;

import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.ServerContext;
/**
 * Dwr消息推送类
 * @author zhangqing
 * @date 2013-10-18 上午11:32:37
 */
public class DwrUtil {
	/**服务器端dwr上下文*/
	public static ServerContext serverContext ;
	
	/**
	 * 发送消息到所有用户
	 * @author zhangqing
	 * @date 2013-10-18 上午11:13:47
	 * @param javascriptMethod 页面javascript回调方法，
	 * @param message 发送的消息，该消息将自己放回个javascript方法
	 */
	@SuppressWarnings("deprecation")
	public static void send2all(String javascriptMethod,Object message){
		Collection<ScriptSession> sessionList = serverContext.getAllScriptSessions();
		for(ScriptSession myScSession:sessionList){
			ScriptBuffer scriptBuffer = new ScriptBuffer(); //构造js脚本  
			scriptBuffer.appendCall(javascriptMethod, message);//添加javascript方法和参数
			myScSession.addScript(scriptBuffer); //向客户端推送消息  
		}
	}
	/**
	 * 发送消息到一些浏览器
	 * @author zhangqing
	 * @date 2013-10-18 上午11:31:19
	 * @param javascriptMethod 页面javascript回调方法，
	 * @param message 发送的消息，该消息将自己放回个javascript方法
	 * @param param 消息筛选类，筛选出需要发送的用户，通过ScriptSession
	 */
	public static void send2some(final String javascriptMethod,final Object message,final DwrParam param){
		Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
			public boolean match(ScriptSession session) {
				param.session = session;
				return param.validate();
			}
		}, new Runnable() {
			private ScriptBuffer script = new ScriptBuffer();
			public void run() {
				script.appendCall(javascriptMethod, message);
				Collection<ScriptSession> sessions = Browser
						.getTargetSessions();
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(script);
				}
			}
		});
	}
}
