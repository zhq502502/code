package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.CompanyOperation;
import com.seegle.util.SeegleLog;

public class GuestLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"

    public GuestLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int res = -2;
		HttpClient hc = new HttpClient(url,request.getParameter("orgid"));
		String orgid = request.getParameter("orgid").trim();
		String userid = request.getParameter("userid").trim();
		String userpass = request.getParameter("userpass");
		String type = request.getParameter("type");
		String Privilege = request.getParameter("Privilege");
		String userpass1 = hc.md5(userpass);
		String alias = request.getParameter("alias").trim();
		
		int orgtype = -1;
		//连接api获取orgtype
		List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		params1.add(new BasicNameValuePair("orgid", orgid));
		JSONObject json1 = hc.getJObject("orgtype", params1);
		orgtype = Integer.parseInt(json1.get("orgtype").toString()); 
		
		if(orgtype==0){ //第三方的用户
			List<NameValuePair> params2 = new ArrayList<NameValuePair>();
			params2.add(new BasicNameValuePair("orgid", orgid)); 
			params2.add(new BasicNameValuePair("u", userid)); 
			params2.add(new BasicNameValuePair("p", userpass1));
			if(Privilege!=null){
				params2.add(new BasicNameValuePair("Privilege", Privilege));
			}
			if(type!=null){
				params2.add(new BasicNameValuePair("type", type));
			}
			JSONObject json2 = hc.getJObject("token", params2);	
			String token = json2.get("token").toString();
			SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("游客用户登录验证的token值:"+token);
			if(token!=null && token.length()>8){
				//获取中心的用户ID
				String uid = userid;
				if(userid.equals("admin")){
					uid = "1000";
				}else{
					List<NameValuePair> params3 = new ArrayList<NameValuePair>();
					params3.add(new BasicNameValuePair("accessKey", token));
					JSONObject json3 = hc.getJObject("currentuser", params3);
					uid = json3.get("userid").toString(); 
					long luid = Integer.parseInt(uid) & 0xFFFFFFFFL;
					uid = Long.toString(luid);
				}
				HttpSession session = request.getSession();
				session.setAttribute("uid", uid);
				session.setAttribute("userid", userid);
				session.setAttribute("orgid", orgid);
				session.setAttribute("token", token);
				session.setAttribute("userpass", userpass);
				session.setAttribute("alias", alias);
				session.setAttribute("apiurl", url);
				Cookie cookie = new Cookie("orgid",orgid);
				cookie.setMaxAge(60*60*24*30); 
				response.addCookie(cookie);					
				res = orgtype;
			} else {
				res = Integer.parseInt(token);
			}			
		}else{ //带协同的用户
			String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			HttpClient hc1 = new HttpClient(webSiteUrl,orgid);
			if(orgid!=null && userid!=null) {
				List<NameValuePair> params4 = new ArrayList<NameValuePair>();
				params4.add(new BasicNameValuePair("orgid", orgid)); 
				params4.add(new BasicNameValuePair("u", userid)); 
				params4.add(new BasicNameValuePair("p", userpass1));
				if(Privilege!=null){
					params4.add(new BasicNameValuePair("Privilege", Privilege));
				}
				if(type!=null){
					params4.add(new BasicNameValuePair("type", type));
				}
				JSONObject json4 = hc.getJObject("token", params4);	
				String token = json4.get("token").toString();
				SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("游客用户登录验证的token值:"+token);
				if(token!=null && token.length()>8){
					List<NameValuePair> params5 = new ArrayList<NameValuePair>();
					params5.add(new BasicNameValuePair("accessKey", token));
					JSONObject json5 = hc.getJObject("currentuser", params5);
					userid = json5.get("userid").toString(); 
					long luid = Integer.parseInt(userid) & 0xFFFFFFFFL;
					userid = Long.toString(luid);
					
					HttpSession session = request.getSession();
					session.setAttribute("userid", userid);
					session.setAttribute("orgid", orgid);
					session.setAttribute("token", token);
					session.setAttribute("upass", userpass);
					session.setAttribute("alias", alias);
					session.setAttribute("apiurl", url);
					Cookie cookie = new Cookie("orgid",orgid);
					cookie.setMaxAge(60*60*24*30); 
					response.addCookie(cookie);
					res = -5;
				} else {
					res = Integer.parseInt(token);
				}	
			} else {
				res = -3;
			}
		}
		out.print(res);
	}

	
}
