<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

<script type='text/javascript' src='<%=basePath%>dwr/engine.js'></script>  
<script type='text/javascript' src='<%=basePath%>dwr/util.js'></script>  
<script type='text/javascript' src='<%=basePath%>dwr/interface/DwrTest.js'></script>  
<script type='text/javascript' src='<%=basePath%>js/jquery-1.4.4.min.js'></script>  

 
<title>第一个dwr推程序</title>  
</head>  
<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);">  
<input type="button" value="点击我" onclick="hello();" />  
<input type="button" value="showSome" onclick="send_1();" />  
<input type="button" value="获取服务器数据" onclick="getMes();" />  
<br/>
userid<input id="userid1" type="text"><input  type="button" value="设置" onclick="setUserid();" >
<br/>
userid<input id="userid" type="text">--
message<input id="msg" type="text"><input  type="button" value="定向发送消息" onclick="sendMessage2User();" >
</body>  
<script type="text/javascript">  
function hello(){  
	DwrTest.send("消息推送");  
}  
/**由dwr在后台调用这个方法**/  
function dwrtest(data){  
	$("body").append("<br/>"+data);
}  
function getMes(){
	DwrTest.getMes(back);
}
function back(data){
	alert(data);
}

function sendMessage2User(){
	var userid = $("#userid").val();
	var mes = $("#msg").val();
	DwrTest.send2some(userid,mes);
}
function getMeMessage(mes){
	alert(mes);
}
function setUserid(){
	DwrTest.setUserid($("#userid1").val());
}
function send_1(){
	DwrTest.send1();
}
function showSome(data){
	alert(data[1].name);
}
</script>  
</html>  