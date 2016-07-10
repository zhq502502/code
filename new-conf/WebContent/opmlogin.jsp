<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Random"%>
<%@page import="com.seegle.util.CenterUtil"%>
<%@page import="com.seegle.util.PropUtil"%>
<%@page import="com.seegle.data.GetCookies"%>
<html>
<head>
<%
String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

String orgid = "";
String userid = "";
String userpass = "";
String type = "";
String alias = "";
if(request.getParameter("orgid")!=null){
	orgid = request.getParameter("orgid");
}
if(request.getParameter("userid")!=null){
	userid = request.getParameter("userid");
}
if(request.getParameter("userpass")!=null){
	userpass = request.getParameter("userpass");
}
if(request.getParameter("type")!=null){
	type = request.getParameter("type");
}
if(request.getParameter("alias")!=null){
	alias = request.getParameter("alias");
}
Random random = new Random();
int s = random.nextInt(99999) % (99999 - 10000 + 1) + 10000;
String guestid = "SG" + s;
//获取语言
Cookie cookies[]=request.getCookies(); 
String language=""; 
GetCookies getCK = new GetCookies();
language = getCK.getCookie(cookies, "SGlanguage");  //获取cookie中的SGlanguage，zh_cn 简体中文； zh_tw 繁体中文；en 英文
if(language==null || "".equals(language)){ //如果cookie中没有SGlanguage，从配置中读取默认语言
	if(PropUtil.getInstance().getValue("language")!=null){
		language=PropUtil.getInstance().getValue("language"); //zh_cn 简体中文； zh_tw 繁体中文；en 英文	
	}
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
<link rel='stylesheet' href='css/style_<%=language %>.css' type='text/css'>
<link rel="stylesheet" href="css/jquery-ui-1.8.10.custom-1.css" type="text/css" media="all" />
<script type='text/javascript' src='js/jquery-1.4.4.min.js' charset='utf-8'></script>
<script type='text/javascript' src='js/jquery.hotkeys.0.8.0.js' charset='utf-8'></script>
<script type='text/javascript' src='js/jquery.cookie.js' charset='utf-8'></script>
<script type="text/javascript" src="js/jquery-ui-1.8.10.custom.min.js"></script>
<script type="text/javascript" src="js/stringRes.js"></script>
<script type="text/javascript" src="js/map.js"></script>
<script type="text/javascript">
var errorMap = new Map();
</script>
<%
if(language.equals("zh_tw")){
%>
<c:forEach var="list_tw" items="${errorCodeList_zh_tw}">
<script>
errorMap.put("${list_tw.key}","${list_tw.value}");
</script>
</c:forEach>
<%
}else if(language.equals("en")){
%>
<c:forEach var="list_en" items="${errorCodeList_en}">
<script>
errorMap.put("${list_en.key}","${list_en.value}");
</script>
</c:forEach>
<%
}else{
%>
<c:forEach var="list" items="${errorCodeList}">
<script>
errorMap.put("${list.key}","${list.value}");
</script>
</c:forEach>
<%
}
%>
</head>
<body>
<input type='hidden' id='siteurl' value=<%=webSiteUrl %> />
<input type='hidden' id='orgid' name='orgid' value='<%=orgid%>' />
<input type="hidden" id="userid" name="userid" value="<%=userid%>" />
<input type="hidden" id="userpass" name="userpass" value="<%=userpass%>" />
<input type="hidden" id="type" name="type" value="<%=type%>" />
<input type="hidden" id="alias"  name="alias" value="<%=alias%>" />
<input type="hidden" id="guestid"  name="guestid" value="<%=guestid%>" />
<script type="text/javascript">
$(document).ready(function(){
	var siteurl = $('#siteurl').val();
	var orgid = $('#orgid').val();
	var userid = $('#userid').val();
	var userpass = $('#userpass').val();
	var type = $('#type').val();
	var alias = $('#alias').val();
	var guestid = $('#guestid').val();
	if(type==2){
		$.post(siteurl+"/GuestLoginServlet",{ orgid:orgid,userid:guestid,userpass:"",type:type,rand:Math.random(),alias:alias } ,function(msg) {
			if(msg==0) {			
				location.href=siteurl+'/GuestConfPage.go?inc=GuestConflist';
			}
			else if(msg == '-5' ) {
				//alert("是带协同的用户");
				location.href=siteurl+'/TopGuestConfPage.go?inc=TopGuestConflist';
			}else if(errorMap.get(msg+"")==null){
				alert(getMsg(NO_DIFINE_ERROR_MSG)+"("+getMsg(ERROR_CODE)+":"+msg+")");
			}	
			else {
				alert(errorMap.get(msg+"")+"("+getMsg(ERROR_CODE)+":"+msg+")");
			}	
		});
	}else{
		$.post(siteurl+"/LoginServlet",{ orgid:orgid,userid:userid,userpass:userpass,rand:Math.random() } ,function(msg) {				
			if(msg==0) {			
				location.href=siteurl+'/ConfPage.go?inc=ConfList';
			}
			else if(msg == '-5' ) {
				location.href=siteurl+'/TopConfPage.go?inc=TopConfList';
			}else if(errorMap.get(msg+"")==null){
				alert(getMsg(NO_DIFINE_ERROR_MSG)+"("+getMsg(ERROR_CODE)+":"+msg+")");
			}
			else {
				alert(errorMap.get(msg+"")+"("+getMsg(ERROR_CODE)+":"+msg+")");
			}
		});	
	}

});
</script>
</body>
</html>
