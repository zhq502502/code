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
import org.json.simple.JSONObject;

import com.seegle.data.CompanyOperation;
import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.PhoneOperuserOper;
import com.seegle.data.PhoneUserOperation;
import com.seegle.data.UserOperation;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
	private UserOperation userOper ;
	private PhoneUserOperation phoneUserOper;

    public LoginServlet() {
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
		phoneUserOper = new PhoneUserOperation(orgid);
		int orgtype = -1;
		String path = request.getContextPath().toString();
		//清空菜单Cookie
		for(int i=1;i<7;i++){
			Cookie cookiei = new Cookie("opt_"+i, null); 
			response.addCookie(cookiei);
		}
		for(int j=1;j<5;j++){
			Cookie cookiej = new Cookie("item_"+j, null); 
			response.addCookie(cookiej);
		}
		//连接api获取orgtype
		List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		params1.add(new BasicNameValuePair("orgid", orgid));
		JSONObject json1 = hc.getJObject("orgtype", params1);
		//未知的企业ID
		if(json1==null||json1.get("orgtype")==null){
			res = -3;
		}else{
			orgtype = Integer.parseInt(json1.get("orgtype").toString());
			request.getSession().setAttribute("orgtype", orgtype);
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
				
				
				SeegleLog.getInstance().getLog(this.getClass(), orgid).info("用户登录验证的token值:"+token);
				if(token!=null && token.length()>8){
					CompanyOperation co =new CompanyOperation();
					boolean exist = co.existCompany(orgid); //判断企业是否存在
					if(!exist){
						//连接中心获取企业信息
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("accessKey", token)); 
						params.add(new BasicNameValuePair("orgid", orgid)); 
						JSONObject json = hc.getJObject("companyreg", params);
						
						SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("企业注册：获取企业信息json:"+json);
						boolean addcom = co.addCompany(orgid, json.get("orgname").toString()); //向company表中添加企业信息

						UserOperation uo = new UserOperation(orgid);
						//添加超级管理员
						try{
							uo.addUser(orgid, userid, "Administrator", "", "", "", userpass, "0", "0", "", "0", "", "", "", "", "1");
							//flag = orgname;	
						}catch(Exception e){
							res = -3;
							out.print(res);
						}
					}
					
					//获取用户电话会议权限
					String phonetype = phoneUserOper.getPhonetype(token.toString(), orgid.toString(), userid,orgtype)+"";
					request.getSession().setAttribute("phonetype", phonetype);
					
					String checkAftercenter = PropUtil.getInstance().getValue("check.aftercenter");
					System.out.println(checkAftercenter);
					if(checkAftercenter!=null&&checkAftercenter.equals("0")){//是否进行本地验证
						if(userOper==null){
							userOper = new UserOperation(orgid);
						}
						if(!userOper.login(orgid, userid, userpass1)){//如果本地验证不通过
							res = -10001;
							out.print(res);
							return ;
						}
					}
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
					session.setAttribute("userpassmd5", userpass1);
					session.setAttribute("apiurl", url);
					Cookie cookie = new Cookie(path+"/orgid",orgid);
					cookie.setMaxAge(60*60*24*30); 
					response.addCookie(cookie);					
					res = orgtype;
				} else {
					res = Integer.parseInt(token);
				}			
			}else{ //带协同的用户
				String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
				HttpClient hc1 = new HttpClient(webSiteUrl,request.getParameter("orgid"));
				if(orgid!=null && userid!=null) {
					List<NameValuePair> params4 = new ArrayList<NameValuePair>();
					params4.add(new BasicNameValuePair("orgid", orgid)); 
					params4.add(new BasicNameValuePair("u", userid)); 
					params4.add(new BasicNameValuePair("p", userpass1));
					JSONObject json4 = hc.getJObject("token", params4);	
					String token = json4.get("token").toString();
					SeegleLog.getInstance().getLog(this.getClass(), orgid).info("用户登录验证的token值:"+token);
					if(token!=null && token.length()>8){
						//获取用户电话会议权限
						String phonetype = phoneUserOper.getPhonetype(token.toString(), orgid.toString(), userid,orgtype)+"";
						request.getSession().setAttribute("phonetype", phonetype);
						//是否为客服 TODO
						if(phoneUserOper.isServeruser(token, userid)){ 
							request.getSession().setAttribute("isserveruser",1);
						}
						//获取是否为充值操作员
						PhoneOperuserOper oper = new PhoneOperuserOper(orgid.toString());
				    	JSONObject result = (JSONObject)oper.getOne(token, userid, orgid,true);
				    	System.out.println("*******************getOneresult:"+result);
				    	if(result==null){
				    		request.getSession().setAttribute("isoperator",0);
				    	}else{
				    		request.getSession().setAttribute("isoperator",1);
				    	}
						HttpSession session = request.getSession();
						session.setAttribute("userid", userid);
						session.setAttribute("orgid", orgid);
						session.setAttribute("token", token);
						session.setAttribute("userpass", userpass);
						session.setAttribute("userpassmd5", userpass1);
						session.setAttribute("apiurl", url);
						Cookie cookie = new Cookie(path+"/orgid",orgid);
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
		}
		out.print(res);
	}

	
}
