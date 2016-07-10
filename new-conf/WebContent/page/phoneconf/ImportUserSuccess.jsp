<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="background: none">
<head>
<base href="<%=basePath%>">

<title></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="">
<meta http-equiv="description" content="">
<link rel='stylesheet' href='<%=basePath%>css/style_en.css' type='text/css'>
<script type='text/javascript' src='<%=basePath%>js/jquery-1.4.4.min.js' charset='utf-8'></script>
<script type="text/javascript">
var msg = "${msg}";
window.parent.userimporting(0);
if(msg!="导入完成"){
	alert(msg);
	window.parent.close_useradd();
}
function showList(id){
	$("#okList,#errorList").hide();
	$("#"+id).show();
}
//window.parent.close_useradd();
</script>
</head>
<body style="background: none">
<div style="width: 100%;text-align: center">
<a href="javascript:showList('okList')"><%=lu.getLanguage(language,"phone.success","成功") %>${fn:length(okList)} </a>|
<a href="javascript:showList('errorList')"><%=lu.getLanguage(language,"phone.failure","失败") %>${fn:length(errorList)}</a>
</div>
<div id="okList" style="overflow:auto;width: 100% ;text-align: center;display: none;">
<table width="100%" cellpadding="1" cellspacing="1" border="1" >
<c:forEach items="${okList}" var="phone">
	<tr>
		<td><font color="green">${phone.name }</font></td><td><font color="green">${phone.phone }</font></td>
	</tr>
</c:forEach>
</table>
</div>
<div id="errorList" style="overflow:auto;width: 100% ;text-align: center;display: none;">
<table width="100%" cellpadding="1" cellspacing="1" border="1">
<c:forEach items="${errorList}" var="phone">
	<tr>
		<td><font color="red">${phone.name }</font></td><td><font color="red">${phone.phone }</font></td>
	</tr>
</c:forEach>
</table>
</div>
</body>
</html>
