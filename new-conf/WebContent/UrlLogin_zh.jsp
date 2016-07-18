<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.seegle.data.HttpClient"%>
<%@ page language="java" import="com.seegle.data.GetConfigFile"%>
<%@ page language="java" import="org.apache.http.NameValuePair"%>
<%@ page language="java" import="org.apache.http.message.BasicNameValuePair"%>
<%@ page language="java" import="org.json.simple.JSONObject"%>
<%@ page language="java" import="com.seegle.data.PhoneUserOperation"%>
<%@ page language="java" import="com.seegle.data.UserOperation"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.Calendar"%>
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
String u = request.getParameter("u");
String p = "";
String k = request.getParameter("k");
String state = request.getParameter("state");
String urlalias = request.getParameter("alias");

//验证密钥
String key = "whzh!@#";
Calendar cal = Calendar.getInstance();
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
cal.set(Calendar.SECOND, 0);
cal.set(Calendar.MILLISECOND, 0);
int minute = cal.get(Calendar.MINUTE);
if(minute>30){
	minute = 30;
}else{
	minute=0;
}
cal.set(Calendar.MINUTE, minute);
Calendar c1 = Calendar.getInstance();
Calendar c3 = Calendar.getInstance();
c1.set(Calendar.SECOND, 0);
c1.set(Calendar.MILLISECOND, 0);
c3.set(Calendar.SECOND, 0);
c3.set(Calendar.MILLISECOND, 0);
c1.set(Calendar.MINUTE, c1.get(Calendar.MINUTE)-30);
c3.set(Calendar.MINUTE, c3.get(Calendar.MINUTE)+30);
String key1 = c1.getTimeInMillis()+key;
String key2 = cal.getTimeInMillis()+key;
String key3 = c3.getTimeInMillis()+key;

System.out.println(key);
System.out.println(key1);
System.out.println(key2);
System.out.println(key3);
if(k==null){
	out.write("<script>alert('密钥不存在!');</script>");
	out.flush();
	return;
}else if(!(k.equals(HttpClient.md5(key1))||k.equals(HttpClient.md5(key2))||k.equals(HttpClient.md5(key3)))){
	out.write("<script>alert('密钥不正确!');</script>");
	out.flush();
	return;
}



session.setAttribute("urlalias",urlalias);
String url_path = request.getParameter("path");
HttpClient hc = new HttpClient(url,orgid);
List<NameValuePair> params1 = new ArrayList<NameValuePair>();
params1.add(new BasicNameValuePair("orgid", orgid));
JSONObject json1 = hc.getJObject("orgtype", params1);
if(json1==null||json1.get("orgtype")==null){
	out.write("<script>alert('不存在的企业ID!');</script>");
	out.flush();
}else{
	
	int orgtype = Integer.parseInt(json1.get("orgtype").toString());
	session.setAttribute("orgtype", orgtype);
	List<NameValuePair> params2 = new ArrayList<NameValuePair>();
	params2.add(new BasicNameValuePair("orgid", orgid)); 
	params2.add(new BasicNameValuePair("u", u)); 
	params2.add(new BasicNameValuePair("p", hc.md5(p)));
	JSONObject json2 = hc.getJObject("token", params2);	
	String token = json2.get("token").toString();
	if(token!=null && token.length()>8){
		session.setAttribute("token", token);
		session.setAttribute("userid", u);
		session.setAttribute("orgid", orgid);
		UserOperation useroper = new UserOperation(orgid);
		if(useroper.getSingleUser(orgid,u).getUserId()==0){
			useroper.addUser(orgid,urlalias==null?"":urlalias,u,"","","0","","","","");
		}
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
		if(url_path==null||url_path.equals("")){
			//展开新建会议标签
			Cookie cookiei = new Cookie(progpath+"opt_1", "block"); 
			response.addCookie(cookiei);
			out.write("<script>location.href='"+basePath+"ConfPage.go?inc=ConfList';</script>");
		}else if(url_path.equals("user")){
			//展开新建会议标签
			Cookie cookiei = new Cookie(progpath+"opt_3", "block"); 
			response.addCookie(cookiei);
			out.write("<script>location.href='"+basePath+"ConfPage.go?inc=UserAdd';</script>");
		}else{
			//展开新建会议标签
			Cookie cookiei = new Cookie(progpath+"opt_4", "block"); 
			response.addCookie(cookiei);
			out.write("<script>location.href='"+basePath+"ConfPage.go?inc=ConfAdd';</script>");
		}
	}else{
		out.write("<script>alert('用户名或密码不正确,登录失败!');</script>");
	}
	out.flush();
}
%>
