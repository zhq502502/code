package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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



public class TopUserManagerServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"

    public TopUserManagerServerlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		if(request.getSession().getAttribute("token")==null){
			response.sendRedirect(webSiteUrl+"/Login.jsp");
		}
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Object token = session.getAttribute("token");
		Object orgid = session.getAttribute("orgid");
		Object upass = session.getAttribute("upass");
		Object useraccount = session.getAttribute("userid");
		String action = request.getParameter("action");
		String usertype = request.getParameter("type");

		if("update".equalsIgnoreCase(action)){
			String userid = request.getParameter("uid");	
			String nickname = request.getParameter("alias");
			String userpass = request.getParameter("newpassword");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");		
			HttpClient hc = new HttpClient(url,orgid.toString());
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("accessKey", token.toString())); 
			param.add(new BasicNameValuePair("orgid", orgid.toString()));
			param.add(new BasicNameValuePair("type", "set")); 
			param.add(new BasicNameValuePair("usertype", usertype));
			param.add(new BasicNameValuePair("userid", userid));
			param.add(new BasicNameValuePair("alias", nickname));
			param.add(new BasicNameValuePair("passwordmd5", userpass));
			param.add(new BasicNameValuePair("phone", phone));
			param.add(new BasicNameValuePair("email", email));

			JSONObject json = hc.getJObject("edituser", param);
			String flag = json.get("msg").toString();		
			out.print(flag);
		} 
		else if("vopupdate".equalsIgnoreCase(action)){
			String userid = request.getParameter("userid");	
			String nickname = request.getParameter("alias");
			String userpass = request.getParameter("newpassword");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String type = request.getParameter("usertype");
			HttpClient hc = new HttpClient(url,orgid.toString());
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("accessKey", token.toString())); 
			param.add(new BasicNameValuePair("orgid", orgid.toString()));
			param.add(new BasicNameValuePair("usertype", type));
			param.add(new BasicNameValuePair("userid", userid));
			param.add(new BasicNameValuePair("useraccount", useraccount.toString()));
			param.add(new BasicNameValuePair("username", useraccount.toString()));
			param.add(new BasicNameValuePair("alias", nickname));
			param.add(new BasicNameValuePair("passwordmd5", userpass));
			param.add(new BasicNameValuePair("phone", phone));
			param.add(new BasicNameValuePair("email", email));
			JSONObject json = hc.getJObject("vopusermod", param);
			String flag = json.get("msg").toString();		
			out.print(flag);
		} 
		else if("create".equalsIgnoreCase(action)){
			String username = request.getParameter("name");
			/*调用API*/
			HttpClient hc = new HttpClient(url,orgid.toString());
			List<NameValuePair> params2 = new ArrayList<NameValuePair>();
			params2.add(new BasicNameValuePair("orgid",orgid.toString())); 
			params2.add(new BasicNameValuePair("accessKey", token.toString())); 
			params2.add(new BasicNameValuePair("usertype", usertype)); 
		 	params2.add(new BasicNameValuePair("useraccount", username)); 
		 	params2.add(new BasicNameValuePair("username", username));
//		 	params2.add(new BasicNameValuePair("alias", username)); 
//		 	params2.add(new BasicNameValuePair("passwordmd5", "")); 
//		 	params2.add(new BasicNameValuePair("phone", request.getParameter("phone"))); 
//		 	params2.add(new BasicNameValuePair("email", request.getParameter("email"))); 			 	
		 	JSONObject json = hc.getJObject("adduser", params2);
		 	String flag = json.get("msg").toString();
		 	out.print(flag);
		} 
		else if("changepass".equalsIgnoreCase(action)){			
			String oldpassword = request.getParameter("oldpassword");
			String newpassword = request.getParameter("newpassword");
			oldpassword = HttpClient.md5(oldpassword);
			newpassword = HttpClient.md5(newpassword);
			System.out.println("--------oldpassword:"+oldpassword+"-----------upass:"+upass);
			if(!(oldpassword).equalsIgnoreCase(upass.toString())){
				System.out.println("---++++-----oldpassword:"+oldpassword+"-----------upass:"+upass);
				out.print(8000);
			} else {				
				HttpClient hc = new HttpClient(url,orgid.toString());
				List<NameValuePair> param = new ArrayList<NameValuePair>();
				param.add(new BasicNameValuePair("accessKey", token.toString())); 
				param.add(new BasicNameValuePair("orgid", orgid.toString()));
				param.add(new BasicNameValuePair("type", "set")); 
				param.add(new BasicNameValuePair("usertype", "3")); 
				param.add(new BasicNameValuePair("userid", "1000")); 		
			 	param.add(new BasicNameValuePair("useraccount", "admin")); 			 	
				param.add(new BasicNameValuePair("username", "admin"));
				param.add(new BasicNameValuePair("passwordmd5", newpassword));
			 	param.add(new BasicNameValuePair("alias","")); 
			 	param.add(new BasicNameValuePair("phone", "")); 
			 	param.add(new BasicNameValuePair("email", "")); 	
				JSONObject json =  hc.getJObject("edituser", param);
				System.out.println("***************"+json.get("msg").toString());
				int flag = Integer.parseInt(json.get("msg").toString());	
				out.print(flag);
			}
		}
		else if("changeOrgName".equalsIgnoreCase(action)){			
			String orgname = request.getParameter("newname");
			/*调用API*/
			HttpClient hc = new HttpClient(url,orgid.toString());
			List<NameValuePair> params2 = new ArrayList<NameValuePair>();
			params2.add(new BasicNameValuePair("orgid",orgid.toString())); 
			params2.add(new BasicNameValuePair("accessKey", token.toString())); 
		 	params2.add(new BasicNameValuePair("orgname", orgname)); 			 	
		 	JSONObject json = hc.getJObject("orgnamemod", params2);
		 	String flag = json.get("msg").toString();
		 	out.print(flag);
		}
		
		else {
			out.print(7000);
		}
	}

}
