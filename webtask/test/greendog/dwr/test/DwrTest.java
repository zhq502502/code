package greendog.dwr.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;


public class DwrTest {
	public static ServerContext wctx;
	public static int num = 0;
	/**
	 * 发送消息
	 * @author zhangqing
	 * @date 2013-10-17 下午03:14:53
	 * @param msg
	 */
	public void send(String msg) { 
		Collection<ScriptSession> sessionList = wctx.getScriptSessionsByPage("/webtask/page/dwr/dwrTest.jsp");
//		Collection<ScriptSession> sessionList = Browser.getTargetSessions();
		for(ScriptSession myScSession:sessionList){
			ScriptBuffer scriptBuffer = new ScriptBuffer(); //构造js脚本  
			scriptBuffer.appendCall("dwrtest", msg+num);
			myScSession.addScript(scriptBuffer); //向客户端推送消息  
			System.out.println("==========调用了send方法"+num+"次==========");  
		}
		num++;
		//ScriptSession myScSession = webContext.getScriptSession();  
	}  
	/**
	 * 获取数据
	 * @author zhangqing
	 * @date 2013-10-17 下午03:16:54
	 * @return
	 */
	public String getMes(){
		System.out.println("in getMes");
		return "你是一个好人";
	}
	
	public void setUserid(String userid){
		System.out.println("--setuserid---"+userid);
//		DwrScriptSessionManagerUtil dwrScriptSessionManagerUtil = new DwrScriptSessionManagerUtil();
//        try {
//               dwrScriptSessionManagerUtil.init();
//        } catch (ServletException e) {
//               e.printStackTrace();
//        }
		 ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
         scriptSession.setAttribute("userid", userid);
	}
	
	/**
	 * 点对点发送消息
	 * @author zhangqing
	 * @date 2013-10-17 下午03:20:41
	 * @param userid
	 * @param mes
	 */
	public void send2user(final String userid, final String mes) {
		
//		Browser.withAllSessions(new Runnable() {
//			private ScriptBuffer script = new ScriptBuffer();
//
//			public void run() {
//				System.out.println("sendmessage----------userid:"+userid+",message"+mes);
//				script.appendCall("getMeMessage", mes);
//				Collection<ScriptSession> sessions = Browser
//						.getTargetSessions();
//				for (ScriptSession scriptSession : sessions) {
//					scriptSession.addScript(script);
//				}
//			}
//		});
		
//		Browser.withAllSessionsFiltered(ServerContextFactory.get(), new ScriptSessionFilter() {
//			public boolean match(ScriptSession session) {
//				if (session.getAttribute("userid") == null)
//					return false;
//				else
//					return (session.getAttribute("userid")).equals(userid);
//			}
//		}, new Runnable() {
//			private ScriptBuffer script = new ScriptBuffer();
//
//			public void run() {
//				System.out.println("sendmessage----------userid:"+userid+",message"+mes);
//				script.appendCall("getMeMessage", mes);
//				Collection<ScriptSession> sessions = Browser
//						.getTargetSessions();
//				for (ScriptSession scriptSession : sessions) {
//					scriptSession.addScript(script);
//				}
//			}
//		});
		
		Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
			public boolean match(ScriptSession session) {
				if (session.getAttribute("userid") == null)
					return false;
				else
					return (session.getAttribute("userid")).equals(userid);
			}
		}, new Runnable() {
			private ScriptBuffer script = new ScriptBuffer();

			public void run() {
				System.out.println("sendmessage----------userid:"+userid+",message"+mes);
				script.appendCall("getMeMessage", mes);
				Collection<ScriptSession> sessions = Browser
						.getTargetSessions();
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(script);
				}
			}
		});
	}
	
	public void send1(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> message = new HashMap<String, String>();
		message.put("name", "zhangqing");
		Map<String,String> message1 = new HashMap<String, String>();
		message1.put("name", "zhangqing1");
		list.add(message);
		list.add(message1);
		DwrUtil.send2all("showSome", list);
	}
	
	public void send2some(String userid,String message){
		UserDwrParam param = new UserDwrParam();
		param.setUserid(userid);
		DwrUtil.send2some("getMeMessage", message, param);
	}
}
