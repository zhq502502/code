<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试主页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		li{
			list-style:none;
			 
		}
	</style>
  </head>
  <body>
  	<ul>
    <li><a href="page/applet/StartPageForApp.html">applet</a></li>
    <li><a href="page/dwr/dwrTest.jsp">DWR登录</a></li>
    <li><a href="page/zngj/test.jsp">智能公交</a></li>
    <li><a class="ww-inline ww-online" target="_blank" href="http://www.taobao.com/webww/?ver=1&&touid=cntaobaoinstudio1973%E5%A5%B3%E8%A3%85%E6%97%97%E8%88%B0%E5%BA%97&siteid=cntaobao&status=2&portalId=&gid=37402679402&itemsId=" title="点此可以直接和卖家交流选好的宝贝，或相互交流网购体验，还支持语音视频噢。">123123</a></li>
    </ul>
  </body>  
</html>
