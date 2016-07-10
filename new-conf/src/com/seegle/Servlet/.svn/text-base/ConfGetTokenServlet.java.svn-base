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
import org.json.simple.JSONObject;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.SQLOperation;
import com.seegle.util.SeegleLog;

public class ConfGetTokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConfGetTokenServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
//		SeegleLog.getInstance().getLog(this.getClass(), request.getParameter("orgid").toString()).debug("ConfGetTokenServlet");
		GetConfigFile gcf = new GetConfigFile();//读取配置
		String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
		HttpSession session = request.getSession();
		HttpClient hc = new HttpClient(url,request.getParameter("orgid"));
		String orgid = "";
		String account = "";
		String userpass = "";
		String rs = "0";
		if(request.getParameter("orgid")!=null){
			orgid = request.getParameter("orgid").trim();
		}	
		if(request.getParameter("userid")!=null){
			account = request.getParameter("userid").trim();
		}		
		if(request.getParameter("userpass")!=null){
			userpass = request.getParameter("userpass");
		}	
		String type = request.getParameter("type");
		String userpassmd5 = hc.md5(userpass);
		if(request.getParameter("orgid")==null||request.getParameter("userid")==null){
		}else{
			//调用API获取token
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("orgid", orgid)); 
			params.add(new BasicNameValuePair("u", account)); 
			params.add(new BasicNameValuePair("p", userpassmd5));
			if(type!=null){
				params.add(new BasicNameValuePair("type", type));
			}
			JSONObject jsonObject = hc.getJObject("token", params);	
			String token = jsonObject.get("token").toString();
			
			if(token==null || token.length()<8){
				SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("#################"+token);
				rs = "-1"; //token获取失败
			}else{
				session.setAttribute("token", token);
				session.setAttribute("orgid", orgid);
				//获取用户昵称		
				SQLOperation so = new SQLOperation();
				rs = so.getUsername(orgid, account);
				if(rs==null||"".equals(rs)){
					String home_username = account;
					List<NameValuePair> params1 = new ArrayList<NameValuePair>();
					params1.add(new BasicNameValuePair("accessKey", token.toString())); 
					JSONObject json1 = hc.getJObject("currentuser", params1);
					if(json1!=null){	
						if(json1.get("username")!=null&&!"".equals(json1.get("username"))){
							home_username = json1.get("username").toString();
						}
						if(json1.get("nickname")!=null&&!"".equals(json1.get("nickname"))){
							home_username = json1.get("nickname").toString();
						}
					}
					SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("home_username:"+home_username);
					if(!"".equals(home_username)&&home_username!=null&&home_username!=account){
						rs=home_username;
					}
				}
			}
			out.println(rs);
		}	
	}

}
