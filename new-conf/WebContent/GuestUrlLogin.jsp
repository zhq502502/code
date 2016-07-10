<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.seegle.data.HttpClient"%>
<%@ page language="java" import="com.seegle.data.GetConfigFile"%>
<%@ page language="java" import="org.apache.http.NameValuePair"%>
<%@ page language="java" import="org.apache.http.message.BasicNameValuePair"%>
<%@ page language="java" import="org.json.simple.JSONObject"%>
<%@ page language="java" import="com.seegle.data.PhoneUserOperation"%>
<%@page import="com.seegle.util.PropUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String queryString = request.getQueryString();
GetConfigFile gcf = new GetConfigFile();//读取配置
String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
String progpath = gcf.getConfig("path")==null||gcf.getConfig("path").equals("")?"/":gcf.getConfig("path")+"/"; 
String orgid = request.getParameter("orgid");
if(PropUtil.getInstance().getValue("orgid")!=null&&!PropUtil.getInstance().getValue("orgid").equals("")){
	orgid = PropUtil.getInstance().getValue("orgid");
}
String u = ((int)(Math.random()*100000))+"";
String p = "";
String alias = request.getParameter("alias");
session.setAttribute("alias",alias);
HttpClient hc = new HttpClient(url,orgid);


List<NameValuePair> params1 = new ArrayList<NameValuePair>();
params1.add(new BasicNameValuePair("orgid", orgid));
JSONObject json1 = hc.getJObject("orgtype", params1);
if(json1==null||json1.get("orgtype")==null){
	out.write("<script>alert('不存在的企业ID!');</script>");
}else{
	int orgtype = Integer.parseInt(json1.get("orgtype").toString());
	session.setAttribute("orgtype", orgtype);
	List<NameValuePair> params2 = new ArrayList<NameValuePair>();
	params2.add(new BasicNameValuePair("orgid", orgid)); 
	params2.add(new BasicNameValuePair("u", u)); 
	params2.add(new BasicNameValuePair("p", hc.md5(p)));
	params2.add(new BasicNameValuePair("type", "2"));
	JSONObject json2 = hc.getJObject("token", params2);	
	String token = json2.get("token").toString();
	if(token!=null && token.length()>8){
		session.setAttribute("token", token);
		session.setAttribute("userid", u);
		session.setAttribute("orgid", orgid);
		
		//获取中心的用户ID
		String uid = u;
		if(u.equals("admin")){
			uid = "1000";
		}else{
			List<NameValuePair> params3 = new ArrayList<NameValuePair>();
			params3.add(new BasicNameValuePair("accessKey", token));
			JSONObject json3 = hc.getJObject("currentuser", params3);
			uid = json3.get("userid").toString(); 
			long luid = Integer.parseInt(uid) & 0xFFFFFFFFL;
			uid = Long.toString(luid);
		}
		
		session.setAttribute("uid", uid);
		session.setAttribute("userid", u);
		session.setAttribute("orgid", orgid);
		session.setAttribute("token", token);
		session.setAttribute("userpass", p);
		session.setAttribute("userpassmd5", hc.md5(p));
		session.setAttribute("apiurl", url);
		//获取用户电话会议权限
		PhoneUserOperation phoneUserOper = new PhoneUserOperation(orgid);
		phoneUserOper.getPhonetype(token, orgid, u,orgtype);
		String phonetype = phoneUserOper.getPhonetype(token, orgid,u ,orgtype)+"";
		session.setAttribute("phonetype", phonetype);
		out.write("<script>location.href='"+basePath+"GuestConfPage.go?inc=GuestConflist';</script>");
	}else{
		out.write("<script>alert('用户名或密码不正确,登录失败!');</script>");
	}
}
%>
