package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.util.SeegleLog;

public class LoginAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"

    public LoginAPI() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.addHeader("Cache-Control", "must-revalidate");//optional
		PrintWriter out = response.getWriter();
		int res = -2;
		System.out.println("#######################");
		String orgid = request.getParameter("orgid").trim();
		String userid = request.getParameter("userid").trim();
		String userpass = request.getParameter("userpass");
		if(orgid!=null && userid!=null) {
			HttpClient hc = new HttpClient(url,orgid);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("orgid", orgid)); 
			params.add(new BasicNameValuePair("u", userid)); 
			params.add(new BasicNameValuePair("p", userpass));
			JSONObject jsonObject = hc.getJObject("token", params);	
			String token = jsonObject.get("token").toString();
			SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("用户登录验证的token值:"+token);
			if(token!=null && token.length()>8){
				//获取中心的用户ID
				String uid = userid;
				if(userid.equals("admin")){
					uid = "1000";
				}else{
					List<NameValuePair> params1 = new ArrayList<NameValuePair>();
					params1.add(new BasicNameValuePair("orgid", orgid));
					params1.add(new BasicNameValuePair("accessKey", token));
					params1.add(new BasicNameValuePair("useraccount", userid));
					params1.add(new BasicNameValuePair("type", "get"));
					JSONArray jsonarr = hc.getJArray("edituser", params1);
					//JSONObject jsonObject1 = hc.getJObject("edituser", params1);	
					for (int i = 0; i < jsonarr.size(); i++) {
			        	JSONObject json1 = (JSONObject)jsonarr.get(i);
			        	uid = json1.get("userid").toString();
			        }					
				}
				HttpSession session = request.getSession();
				session.setAttribute("uid", uid);
				session.setAttribute("userid", userid);
				session.setAttribute("orgid", orgid);
				session.setAttribute("token", token);
				session.setAttribute("userpass", userpass);
				res = 0;
			} else {
				res = Integer.parseInt(token);
			}
		} else {
			res = -3;
		}
		
		String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		if(res==0){
			System.out.println("#######################");
			response.sendRedirect(webSiteUrl+"/conf/ConfList");
		}else{
			out.print(res);
		}
	}

	
}
