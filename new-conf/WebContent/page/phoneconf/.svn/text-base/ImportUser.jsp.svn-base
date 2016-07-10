<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel='stylesheet' href='<%=basePath%>css/style_zh_cn.css' type='text/css'>
<script type='text/javascript' src='<%=basePath%>js/jquery-1.4.4.min.js' charset='utf-8'></script>
</head>
<body style="background: none">
<form action="<%=basePath%>PhoneUserAction.go?method=importuser&confid=${confid }" method="post"  ENCTYPE="multipart/form-data">
<table width="100%">
	<tr>
		<td width="20%" align="center"></td>
		<td width="80%" align="left">
			<input type="file" id="file_button" name="file" accept="application/msexcel">
		</td>
	</tr>
	<tr>
		<td width="20%" align="center"></td>
		<td width="80%" align="left">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td width="20%" align="center"></td>
		<td width="80%" align="left">
			<%=lu.getLanguage(language,"phone.uploadtype","上传文件格式为excel文件") %>.<a href="<%=basePath%>template/user.xls"><%=lu.getLanguage(language,"phone.downloadtemplate","模版下载") %></a>
		</td>
	</tr>
	<tr>
		<td width="" align="center" colspan="2" style="padding:10px;">
		<input type="submit" class="sbutton" onclick="window.parent.userimporting(1);unclick_but();"value="<%=lu.getLanguage(language,"phone.import","导入") %>"/>
		<input type="button" class="sbutton" onclick="window.parent.close_useradd();" value="<%=lu.getLanguage(language,"button.cancel","取消") %>"/>
		</td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
function unclick_but(){
	$(".sbutton").addClass("unclickbut");
	$(".sbutton").removeClass("sbutton");
	$(".sbutton").attr("disabled","disabled");
	$("#file_button").css({"visibility":"hidden"});
	$("form").submit();
}
</script>
</html>
