package greendog.dwr.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.WebContextFactory.WebContextBuilder;
import org.directwebremoting.proxy.dwr.Util;
import org.directwebremoting.servlet.DwrWebContextFilter;

public class InitDwr extends HttpServlet {

	public InitDwr() {
		new HttpThread().start();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DwrTest.wctx = ServerContextFactory.get(this.getServletContext()); 
		DwrUtil.serverContext = ServerContextFactory.get(this.getServletContext()); 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	public void init() throws ServletException {
		// Put your code here
	}
}
 class HttpThread extends Thread{
	@Override
	public void run(){
		HttpClient client = new HttpClient();
		HostConfiguration host = new HostConfiguration();
		host.setHost("localhost", 8080, "http");
		client.setHostConfiguration(host);
		
		PostMethod method = new PostMethod("/webtask/dwrInit");
		try {
			client.executeMethod(method);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//new Send().start();
	}
}
class Send extends Thread{
	private String userid="123";
	private String mes="我是中国人。我爱自己的祖国";
	@Override
	public void run(){
		for(;DwrTest.num<50;DwrTest.num++){
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
			try {
				this.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
