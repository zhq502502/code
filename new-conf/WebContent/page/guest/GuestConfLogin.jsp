<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.seegle.data.GetConfigFile"%>
<%@page import="com.seegle.util.PropUtil"%>
<%@page import="java.util.Random"%>
<%@page import="com.seegle.util.PropUtil"%>
<%@page import="com.seegle.util.LanguageUtil"%>
<%@page import="com.seegle.data.GetCookies"%>
<% 
//获取语言
String language=""; 
Cookie cookies[]=request.getCookies(); 
GetCookies getCK = new GetCookies();
language = getCK.getCookie(cookies, "SGlanguage");  //获取cookie中的SGlanguage，zh_cn 简体中文； zh_tw 繁体中文；en 英文
if(language==null || "".equals(language)){ //如果cookie中没有SGlanguage，从配置中读取默认语言
	if(PropUtil.getInstance().getValue("language")!=null){
		language=PropUtil.getInstance().getValue("language"); //zh_cn 简体中文； zh_tw 繁体中文；en 英文	
	}
}
LanguageUtil lu = new LanguageUtil();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
GetConfigFile gcf = new GetConfigFile();//读取配置
String apiurl = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
request.setAttribute("client_registry", PropUtil.getInstance().getValue("client.registry"));
request.setAttribute("client_confname", PropUtil.getInstance().getValue("client.confname"));
request.setAttribute("client_sgplayname", PropUtil.getInstance().getValue("client.sgplayname"));

Random random = new Random();
int s = random.nextInt(99999) % (99999 - 10000 + 1) + 10000;
String userid = "SG" + s;
String nickname = userid;
String orgid = "";
String userpass = "";
String confid = "";
int type = 2;
if(request.getParameter("orgid")!=null){
	orgid = request.getParameter("orgid");
}
if(request.getParameter("confid")!=null){
	confid = request.getParameter("confid");
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%
if(language.equals("zh_tw")){
%>
${applicationScope.titlecontent_zh_tw}
<%
}else if(language.equals("en")){
%>
${applicationScope.titlecontent_en}
<%
}else{
%>
${applicationScope.titlecontent}
<%
}
%></title>
<link rel='stylesheet' href='css/style_gb.css' type='text/css'>
<link rel="stylesheet" href="css/jquery-ui-1.8.10.custom.css" type="text/css" media="all" />
<script type="text/javascript" src="js/map.js"></script>
<script type="text/javascript">
var client_registry = "${client_registry}";
var client_confname = "${client_confname}";
var client_sgplayname = "${client_sgplayname}";
var errorMap = new Map();
</script>
<c:forEach var="list" items="${errorCodeList}">
<script>errorMap.put("${list.key}","${list.value}");</script>
</c:forEach>
<script type='text/javascript' src='js/jquery-1.4.4.min.js' charset='utf-8'></script>
<script type='text/javascript' src='js/jquery.ba-dotimeout.js' charset='utf-8'></script>
<script type='text/javascript' src='js/jquery.hiAlerts-min.js' charset='utf-8'></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>	
<script type="text/javascript" src="js/date/WdatePicker.js" charset='utf-8'></script>
<script type="text/javascript" src="js/conf.js"></script>
<script type="text/javascript" src="js/conf.login.js"></script>
<script type="text/javascript" src="js/base64.js"></script>
<script type="text/javascript">
if (g_installFlag == '0' || g_installFlag == '1')
{
	if(browser.versions.iPad){
		g_installFlag = "4";
	}
	else if(browser.versions.iPhone){
		g_installFlag = "5";
	}
	else if(browser.versions.android){
		g_installFlag = "3";
	}
	else{
		Seegle.postsgsvr(9090, 1, Seegle.client_registry, "InstallPath");	
	}	
}
$(document).ready(function(){
	$("#login").click();
});
</script>
</head>
<body>
	<span style='display: none;'> 
		<input type='hidden' id='siteurl' value='<%=webSiteUrl %>' />
		<input type='hidden' id='apiurl' value='<%=apiurl %>'/>
		<input type="hidden" id="orgid" name="orgid" value='<%=orgid %>' />	
		<input type="hidden" id="confid" name="confid" value='<%=confid %>'/>
		<input type="hidden" id="nickname" name="nickname" value="<%=nickname %>" />
		<INPUT type="hidden" id="userid" name="userid" value='<%=userid %>' >
		<INPUT type="hidden" id="userpass" name="userpass" value='<%=userpass %>' >
		<INPUT type="hidden" id="type" name="type" value='<%=type %>' >
		<input type="hidden" name="login" id="login" value=""/>
		<input type='hidden' id='download_meetUnOnline' value="${applicationScope.online_install_seegleconf_url }" />	
	</span>
<br>
<h1><%=lu.getLanguage(language,"guest.guestconflogin","正在打开客户端") %></h1>
</body>
</html>
