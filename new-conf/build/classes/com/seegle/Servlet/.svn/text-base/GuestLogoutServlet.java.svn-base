package com.seegle.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;

public class GuestLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"   

    public GuestLogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object token = session.getAttribute("token");
		Object orgid = session.getAttribute("orgid");
		Object userid = session.getAttribute("userid");
		String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		if(token==null||orgid==null||userid==null){
			response.sendRedirect(webSiteUrl+"/GuestLogin.jsp");
		}else{
			HttpClient hc = new HttpClient(url,orgid.toString());
			List<NameValuePair> params2 = new ArrayList<NameValuePair>();
			params2.add(new BasicNameValuePair("userid", userid.toString())); 
			params2.add(new BasicNameValuePair("orgid", orgid.toString())); 
			params2.add(new BasicNameValuePair("accessKey", token.toString()));
			JSONObject json = hc.getJObject("logout", params2);
			int flag = 0;
			if(json!=null){
				flag = Integer.parseInt(json.get("msg").toString());
			}
			session.invalidate();
			response.sendRedirect(webSiteUrl+"/GuestLogin.jsp");
		}
	}

}
