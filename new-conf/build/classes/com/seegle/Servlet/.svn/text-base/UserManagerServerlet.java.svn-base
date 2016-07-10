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
import org.json.simple.JSONObject;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.SQLOperation;
import com.seegle.data.UserOperation;
import com.seegle.form.UserInfoActionForm;

public class UserManagerServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"

    public UserManagerServerlet() {
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
		String orgid = session.getAttribute("orgid").toString();
		String token = session.getAttribute("token").toString();
		//String uid = session.getAttribute("uid").toString();
		String action = request.getParameter("action");
		if("changeName".equalsIgnoreCase(action)){
			int flag = 1;
			String account = request.getParameter("account");	
			String alias = request.getParameter("alias");
			SQLOperation so = new SQLOperation();
			boolean a = so.updateUsername(orgid,alias,account);
			if(a==true){
				flag = 0;
			}
			out.print(flag);
		} 
		else if("changeUserrole".equalsIgnoreCase(action)){
			int flag = 1;
			String account = request.getParameter("account");	
			String operation = request.getParameter("operation");
			SQLOperation so = new SQLOperation();
			boolean a = so.changeUserrole(orgid,account,operation);
			if(a==true){
				flag = 0;
			}
			out.print(flag);
		}		
		else if("useradd".equalsIgnoreCase(action)){
			int flag = 1;
			String account = request.getParameter("account");	
			String alias = request.getParameter("alias");
			String userpass = request.getParameter("userpass");
			String email = request.getParameter("email");
			String proxytype = request.getParameter("proxytype");
			String proxyaddr = request.getParameter("proxyaddr");
			String proxyport = request.getParameter("proxyport");
			String proxyuser = request.getParameter("proxyuser");
			String proxypass = request.getParameter("proxypass");
			UserOperation uo = new UserOperation(orgid);
			UserInfoActionForm uif = uo.getSingleUser(orgid, account);
			if(uif.getUserName()==null){
				System.out.println("tianjia");
				boolean a = uo.addUser(orgid, alias, account, userpass, email, proxytype, proxyaddr, proxyport, proxyuser, proxypass);
				if(a==true){
					flag = 0;
				}
			}else{
				flag = 2;
			}
			out.print(flag);
		} 
		else if("mutiluseradd".equalsIgnoreCase(action)){ //添加多个用户
			int flag = 1;
			int addSum = Integer.parseInt(request.getParameter("account"));
			String userpass = request.getParameter("userpass");
			String proxytype = request.getParameter("proxytype");
			String proxyaddr = request.getParameter("proxyaddr");
			String proxyport = request.getParameter("proxyport");
			String proxyuser = request.getParameter("proxyuser");
			String proxypass = request.getParameter("proxypass");
			UserOperation uo = new UserOperation(orgid);
			int lastId = uo.getLastId(orgid);
			int j = 0;
			for (int i = 0; i < addSum; i++) {
				boolean a = uo.addUser(orgid, String.valueOf(lastId+1+i), String.valueOf(lastId+1+i), userpass, "", proxytype, proxyaddr, proxyport, proxyuser, proxypass);
				if(a==true){
					j++;
				}
            }
			if(j==addSum){
				flag = 0;
			}
			out.print(flag);
		}
		else if("delete".equalsIgnoreCase(action)){
			int flag = 1;
			String account = request.getParameter("account");	
			SQLOperation so = new SQLOperation();
			boolean a = so.userDel(orgid,account);
			if(a==true){
				flag = 0;
			}
			out.print(flag);
		} 
		else if("delall".equalsIgnoreCase(action)){
			int flag = 1;
			String accounts = request.getParameter("accounts");	
			SQLOperation so = new SQLOperation();
			boolean a = so.userDelall(orgid,accounts);
			if(a==true){
				flag = 0;
			}
			out.print(flag);
		} 
		else if("editall".equals(action)){
			String usernames = request.getParameter("usernames");
			String userpass = request.getParameter("userpass");
			String proxytype = request.getParameter("proxytype");
			String proxyaddr = request.getParameter("proxyaddr");
			String proxyport = request.getParameter("proxyport");
			String proxyuser = request.getParameter("proxyuser");
			String proxypass = request.getParameter("proxypass");
			UserOperation uo = new UserOperation(orgid);
			boolean op = uo.editall(orgid, usernames, userpass, proxytype, proxyaddr, proxyport, proxyuser, proxypass);
			out.print(op?"0":"-1");
		}
		else if("usermod".equalsIgnoreCase(action)){
			int flag = 1;
			String account = request.getParameter("account");	
			String alias = request.getParameter("alias");
			String userpass = request.getParameter("userpass");
			String email = request.getParameter("email");
			String proxytype = request.getParameter("proxytype");
			String proxyaddr = request.getParameter("proxyaddr");
			String proxyport = request.getParameter("proxyport");
			String proxyuser = request.getParameter("proxyuser");
			String proxypass = request.getParameter("proxypass");
			UserOperation uo = new UserOperation(orgid);
			boolean a = uo.modUser(orgid, alias, account, userpass, email, proxytype, proxyaddr, proxyport, proxyuser, proxypass);
			if(a==true){
				flag = 0;
			}
			out.print(flag);
		}
		else if("changepass".equalsIgnoreCase(action)){			
			int flag = 1;
			String account = request.getParameter("account");	
			String npass = request.getParameter("npass");
			SQLOperation so = new SQLOperation();
			String oldpass = so.getUserpass(orgid, account);
//			if(!npass.equals(oldpass)){  
				boolean a = so.updateUserpass(orgid,npass,account);
				if(a==true){
					if(account.equals("admin")){
						/*使用API调用*/
						HttpClient hc = new HttpClient(url,orgid.toString());
						List<NameValuePair> param = new ArrayList<NameValuePair>();
						param.add(new BasicNameValuePair("accessKey", token)); 
						param.add(new BasicNameValuePair("orgid",orgid));
						param.add(new BasicNameValuePair("password",npass));
						JSONObject json = hc.getJObject("changeadmin", param); 	
						flag = Integer.parseInt(json.get("msg").toString());
					}else{
						HttpClient hc = new HttpClient(url,orgid.toString());
						List<NameValuePair> param = new ArrayList<NameValuePair>();
						param.add(new BasicNameValuePair("accessKey", token)); 
						param.add(new BasicNameValuePair("orgid",orgid));
						//param.add(new BasicNameValuePair("userid",uid));
						param.add(new BasicNameValuePair("useraccount",account));
						param.add(new BasicNameValuePair("passwordmd5",npass));
						JSONObject json = hc.getJObject("currentusermod", param); 	
						flag = Integer.parseInt(json.get("msg").toString());
					}
					if(flag!=0){
						boolean b = so.updateUserpass(orgid, oldpass, account);
					}
				}
//			}
			out.print(flag);
		}
		
		else {
			out.print(7000);
		}
	}

}
