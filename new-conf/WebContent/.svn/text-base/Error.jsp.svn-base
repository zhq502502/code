<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
   
<title>出错信息</title>
   
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
<style type="text/css">
#main{
	margin: 20px auto;
	border: 1px solid #cccccc;
	width: 700px;
	overflow: hidden;
}
#errorTitle{
	text-align: center;
	font-weight: bold;
	font-size: 20px;
	background-color: #eeeeee;
	padding: 3px;
	border-bottom: 1px solid #cccccc;
}
#errorMsg{
	margin:0px auto;
	width:80%;
	padding: 5px;
}
#errorContent{
	margin:0px auto;
	width:90%;
	padding: 5px;
}
</style>
</head>

<body>
	<div id="main">
		<div id="errorTitle">访问异常。错误代码：${error.code }</div>
		<div id="errorMsg">
		<c:if test="${error.code eq 404}">请求页面不存在&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="goback()">返回</a></c:if>
		<c:if test="${error.code eq 500}">服务器内部异常&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="show500()">错误详情</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="goback()">返回</a></c:if>
		</div>
		<div id="errorContent" style="display: none;overflow: auto">
			${error.errormsg }<br />
			${error.stack }<br/>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
function show500(){
	$("#errorContent").toggle();
}
function goback(){
	window.history.go(-1);
}
</script>
</html>
