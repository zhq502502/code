package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.PhoneUserOperation;
import com.seegle.data.UserOperation;
import com.seegle.util.CenterUtil;
import com.seegle.util.Des;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

public class TelLoginServlet extends HttpServlet {
	private static final String key = "ytsgmeet";
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
	private UserOperation userOper ;
	private PhoneUserOperation phoneUserOper;

    public TelLoginServlet() {
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
		String path = request.getContextPath().toString();
		HttpClient hc = new HttpClient(url,request.getParameter("orgid"));
		String orgid = null ;//= request.getParameter("orgid").trim();
		String userid = null;//= request.getParameter("userid").trim();
		String userpass = null;// = request.getParameter("userpass");
		String type = null;//= request.getParameter("type");
		String Privilege = null;// = request.getParameter("Privilege");
		String userpass1 = null;// = hc.md5(userpass);
		String queryStrng = request.getQueryString();
		if(queryStrng==null){
			out.println("参数有误");
			return ;
		}else{
			Map<String,String> map = new HashMap<String, String>();
			String queryTrue = "";
			try {
				queryTrue = Des.decrypt(queryStrng, key);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String querys [] = queryTrue.split("&");
			for(String a:querys){
				map.put(a.split("=")[0], a.split("=")[1]);
			}
			orgid = map.get("orgid");
			userid = map.get("userid");
			if(orgid==null){
				out.println("orgid未设置");
				return ;
			}
			if(userid==null){
				out.println("userid未设置");
				return ;
			}
			try {
				userpass = Des.decrypt(map.get("userpass"), key);
				System.out.println(userpass);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			phoneUserOper = new PhoneUserOperation(orgid);
			userpass1 = hc.md5(userpass);
			String timestamp = map.get("timestamp");
			String authid = map.get("authid");
			String authid1 = hc.md5(key+ orgid + userid + map.get("userpass") + timestamp);
			SeegleLog.getInstance().getLog(this.getClass(), "teLmeetingLogin").error(queryTrue);
			SeegleLog.getInstance().getLog(this.getClass(), "teLmeetingLogin").error(key+ orgid + userid + map.get("userpass") + timestamp);
			if(!authid.equals(authid1.toUpperCase())){
				out.println("本次请求不合法");
				return ;
			}
		}
		request.getSession().setAttribute("telLogin", "telLogin");
		int orgtype = -1;
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
					//获取用户电话会议权限
					phoneUserOper.getPhonetype(token, orgid, userid,orgtype);
					String phonetype = phoneUserOper.getPhonetype(token, orgid, userid,orgtype)+"";
					request.getSession().setAttribute("phonetype", phonetype);
					
					//通过认证获取到token
					String centeruser = PropUtil.getInstance().getValue("centerserver.user","");
					System.out.println("#是否从中心获取用户信息，0是，1否；centerserver.user="+centeruser);
					if(centeruser!=null&&centeruser.equals("0")){//用户信息从中心获取
						//不需要本地认证
					}else{ //用户信息从数据库获取
						String checkAftercenter = PropUtil.getInstance().getValue("check.aftercenter");
						System.out.println("#中心认证通过后是否继续进行本地认证,0是，1否.check.aftercenter="+checkAftercenter);
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
					Cookie cookie1 = new Cookie(path+"/SGlanguage","zh_cn");
					cookie1.setMaxAge(60*60*24*30); 
					response.addCookie(cookie1);
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
						phoneUserOper.getPhonetype(token, orgid, userid,orgtype);
						String phonetype = phoneUserOper.getPhonetype(token, orgid, userid,orgtype)+"";
						request.getSession().setAttribute("phonetype", phonetype);
						
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
						Cookie cookie1 = new Cookie(path+"/SGlanguage","zh_cn");
						cookie1.setMaxAge(60*60*24*30); 
						response.addCookie(cookie1);
						res = -5;
					} else {
						res = Integer.parseInt(token);
					}
					
				} else {
					res = -3;
				}
			}
		}
		if(res==0){
			//request.getRequestDispatcher("ConfPage.go?inc=ConfList").forward(request, response);
			response.sendRedirect("ConfPage.go?inc=ConfList");
		}else if(res==-5){
			//request.getRequestDispatcher("TopConfPage.go?inc=TopConfList").forward(request, response);
			response.sendRedirect("TopConfPage.go?inc=TopConfList");
		}else{
			out.println(CenterUtil.TransCenterError(res));
		}
		//out.print(res);
	}

	
}
