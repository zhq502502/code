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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.SQLOperation;


public class ConfManagerServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"

    public ConfManagerServerlet() {
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
		String action = request.getParameter("action");
		String account = request.getParameter("account");
		int role = Integer.parseInt(request.getParameter("role"));
		SQLOperation so = new SQLOperation();

		if("addconfmanager".equalsIgnoreCase(action)){	
            String newAccount[] = account.split(",");
            for(int j=0;j<newAccount.length;j++){
        		String userid = "";
        		String flag = "1";
    			//查询中心服务器中是否存在此用户
    			HttpClient hc = new HttpClient(url,orgid.toString());
    			List<NameValuePair> param = new ArrayList<NameValuePair>();
    			param.add(new BasicNameValuePair("accessKey", token.toString()));
    			param.add(new BasicNameValuePair("orgid", orgid.toString()));
    			param.add(new BasicNameValuePair("type", "get")); 
    			param.add(new BasicNameValuePair("useraccount", newAccount[j]));
    			JSONArray jsonarr = hc.getJArray("edituser", param);
    			for(int i=0; i<jsonarr.size();i++){
    				JSONObject json = (JSONObject)jsonarr.get(i);
    				String useraccount = json.get("useraccount").toString();
    				if(useraccount.equalsIgnoreCase(newAccount[j])){
    					userid = json.get("userid").toString();
    				}			
    			}
    			
    			//如果存在此用户，更新用户权限为会议管理员
    			if(!userid.equals("")){
    				List<NameValuePair> param1 = new ArrayList<NameValuePair>();
    				param1.add(new BasicNameValuePair("accessKey", token.toString()));
    				param1.add(new BasicNameValuePair("orgid", orgid.toString()));
    				param1.add(new BasicNameValuePair("type", "set")); 
    				param1.add(new BasicNameValuePair("useraccount", newAccount[j]));
    				param1.add(new BasicNameValuePair("userid", userid));
    				param1.add(new BasicNameValuePair("usertype", "2"));
    				JSONObject json1 = hc.getJObject("edituser", param1);
    				flag = json1.get("msg").toString();	
    			}else{
    				
    				

    				//获取token
    				String passwordmd5 = so.getUserpass(orgid.toString(), newAccount[j]);
    				List<NameValuePair> params3 = new ArrayList<NameValuePair>();
    				params3.add(new BasicNameValuePair("orgid", orgid+"")); 
    				params3.add(new BasicNameValuePair("u", account)); 
    				params3.add(new BasicNameValuePair("p",  HttpClient.md5(passwordmd5)));
    				org.json.simple.JSONObject jsonObject = hc.getJObject("token", params3);	
    				String token3 = jsonObject.get("token").toString();
    				
    				
    				//获取用户信息
    				List<NameValuePair> param4 = new ArrayList<NameValuePair>();
    				param4.add(new BasicNameValuePair("accessKey", token.toString()));
    				param4.add(new BasicNameValuePair("orgid", orgid+""));
    				param4.add(new BasicNameValuePair("type", "get")); 
    				param4.add(new BasicNameValuePair("useraccount", account));
    				JSONArray jsonarr4 = hc.getJArray("edituser", param4);
    				for(int i=0; i<jsonarr4.size();i++){
    					org.json.simple.JSONObject json = (org.json.simple.JSONObject)jsonarr4.get(i);
    					String useraccount = json.get("useraccount").toString();
    					if(useraccount.equalsIgnoreCase(account)){
    						userid = json.get("userid").toString();
    					}			
    				}
    				
    				
    				//再更新
    				List<NameValuePair> param1 = new ArrayList<NameValuePair>();
    				param1.add(new BasicNameValuePair("accessKey", token.toString()));
    				param1.add(new BasicNameValuePair("orgid", orgid+""));
    				param1.add(new BasicNameValuePair("type", "set")); 
    				param1.add(new BasicNameValuePair("useraccount", account));
    				param1.add(new BasicNameValuePair("userid", userid));
    				param1.add(new BasicNameValuePair("usertype", "2"));
    				org.json.simple.JSONObject json1 = hc.getJObject("edituser", param1);
    				flag = json1.get("msg").toString();	
    				
    				
    				
    				//如果不存在此用户，添加用户，且权限为会议管理员
    				/*String alias = so.getUsername(orgid.toString(), newAccount[j]);
    				String passwordmd5 = so.getUserpass(orgid.toString(), newAccount[j]);
    				List<NameValuePair> param2 = new ArrayList<NameValuePair>();
    				param2.add(new BasicNameValuePair("accessKey", token.toString()));
    				param2.add(new BasicNameValuePair("orgid", orgid.toString()));
    				param2.add(new BasicNameValuePair("useraccount", newAccount[j]));
    				param2.add(new BasicNameValuePair("username", newAccount[j]));
    				param2.add(new BasicNameValuePair("alias", alias));
    				param2.add(new BasicNameValuePair("passwordmd5", passwordmd5));
    				param2.add(new BasicNameValuePair("usertype", "2"));
    				JSONObject json2 = hc.getJObject("adduser", param2);
    				flag = json2.get("msg").toString();*/
    			}
    			if(flag.equals("0")){
    				int oldrole = Integer.parseInt(so.getUserrole(orgid.toString(), newAccount[j]));
    				if(oldrole==3||oldrole==4){ //当前用户权限为用户管理员或者普通用户
    					int newrole = (oldrole + role) % 4;
    					boolean a = so.updateUserrole(orgid.toString(),newAccount[j],String.valueOf(newrole));
    					if(a==true){
    						flag = "0";
    					}else{
    						List<NameValuePair> param1 = new ArrayList<NameValuePair>();
    						param1.add(new BasicNameValuePair("accessKey", token.toString()));
    						param1.add(new BasicNameValuePair("orgid", orgid.toString()));
    						param1.add(new BasicNameValuePair("type", "set")); 
    						param1.add(new BasicNameValuePair("useraccount", newAccount[j]));
    						param1.add(new BasicNameValuePair("userid", userid));
    						param1.add(new BasicNameValuePair("usertype", "1"));
    						JSONObject json1 = hc.getJObject("edituser", param1);
    					 	flag = "1";
    					}
    				}
    			}
    			out.print(flag);
            }
		} 
		else if("delconfmanager".equalsIgnoreCase(action)){	
            String newAccount[] = account.split(",");
            for(int j=0;j<newAccount.length;j++){
        	String userid = "";
        	String flag = "1";
			//查询中心服务器中是否存在此用户
			HttpClient hc = new HttpClient(url,orgid.toString());
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("accessKey", token.toString()));
			param.add(new BasicNameValuePair("orgid", orgid.toString()));
			param.add(new BasicNameValuePair("type", "get")); 
			param.add(new BasicNameValuePair("useraccount", newAccount[j]));
			JSONArray jsonarr = hc.getJArray("edituser", param);
			for(int i=0; i<jsonarr.size();i++){
				JSONObject json = (JSONObject)jsonarr.get(i);
				String useraccount = json.get("useraccount").toString();
				if(useraccount.equalsIgnoreCase(newAccount[j])){
					userid = json.get("userid").toString();
				}			
			}
			
			//如果存在此用户，更新用户权限为普通用户
			if(!userid.equals("")){
				List<NameValuePair> param1 = new ArrayList<NameValuePair>();
				param1.add(new BasicNameValuePair("accessKey", token.toString()));
				param1.add(new BasicNameValuePair("orgid", orgid.toString()));
				param1.add(new BasicNameValuePair("type", "set")); 
				param1.add(new BasicNameValuePair("useraccount", newAccount[j]));
				param1.add(new BasicNameValuePair("userid", userid));
				param1.add(new BasicNameValuePair("usertype", "1"));
				JSONObject json1 = hc.getJObject("edituser", param1);
				flag = json1.get("msg").toString();	
				if(flag.equals("0")){
					int oldrole = Integer.parseInt(so.getUserrole(orgid.toString(), newAccount[j]));
					if(oldrole==1||oldrole==2){ //当前用户权限为超级管理员或者会议管理员
						int newrole = oldrole + role;
						boolean a = so.updateUserrole(orgid.toString(),newAccount[j],String.valueOf(newrole));
						if(a==true){
							flag = "0";
						}else{
							List<NameValuePair> param2 = new ArrayList<NameValuePair>();
							param2.add(new BasicNameValuePair("accessKey", token.toString()));
							param2.add(new BasicNameValuePair("orgid", orgid.toString()));
							param2.add(new BasicNameValuePair("type", "set")); 
							param2.add(new BasicNameValuePair("useraccount", newAccount[j]));
							param2.add(new BasicNameValuePair("userid", userid));
							param2.add(new BasicNameValuePair("usertype", "2"));
							JSONObject json2 = hc.getJObject("edituser", param2);
							flag = "1";
						}
					}	
				}
			}else{
				//如果不存在此用户，直接修改web端用户权限
				int oldrole = Integer.parseInt(so.getUserrole(orgid.toString(), newAccount[j]));
				if(oldrole==1||oldrole==2){ //当前用户权限为超级管理员或者会议管理员
					int newrole = oldrole + role;
					boolean a = so.updateUserrole(orgid.toString(),newAccount[j],String.valueOf(newrole));
					if(a==true){
						flag = "0";
					}else{
						flag = "1";
					}
				}
			}

			out.print(flag);
          }
		} 
		else {
			out.print(7000);
		}
	}

}