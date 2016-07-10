<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Random"%>
<%@page import="com.seegle.util.PropUtil"%>
<%@page import="com.seegle.util.LanguageUtil"%>
<%@page import="com.seegle.data.GetCookies"%>
<html>
<head>
<%
String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		
String orgid = "";
Cookie cookies[]=request.getCookies(); 
if(cookies!=null){
    for(int i=0;i<cookies.length;i++){
    	if(cookies[i]!=null){
    		if(cookies[i].getName().equals("orgid")){
    			orgid = cookies[i].getValue();
    		}
    	}
    }
}
String orgid1 = "";
if(orgid!=null&&!orgid.equals("")){
	orgid1 = orgid.toString();
}else{
	orgid1 = "90000";
}

String orgid11="";
if(PropUtil.getInstance().getValue("orgid")!=null){
	orgid11=PropUtil.getInstance().getValue("orgid");
}

Random random = new Random();
int s = random.nextInt(99999) % (99999 - 10000 + 1) + 10000;
String guestid = "SG" + s;

int type = 2;

//获取语言
String language=""; 
GetCookies getCK = new GetCookies();
language = getCK.getCookie(cookies, "SGlanguage");  //获取cookie中的SGlanguage，zh_cn 简体中文； zh_tw 繁体中文；en 英文
if(language==null || "".equals(language)){ //如果cookie中没有SGlanguage，从配置中读取默认语言
	if(PropUtil.getInstance().getValue("language")!=null){
		language=PropUtil.getInstance().getValue("language"); //zh_cn 简体中文； zh_tw 繁体中文；en 英文	
	}
}
LanguageUtil lu = new LanguageUtil();
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
<link rel='stylesheet' href='<%=webSiteUrl %>/css/style_<%=language %>.css' type='text/css'>
<link rel="stylesheet" href="<%=webSiteUrl %>/css/jquery-ui-1.8.10.custom-1.css" type="text/css" media="all" />
<script type='text/javascript' src='<%=webSiteUrl %>/js/jquery-1.4.4.min.js' charset='utf-8'></script>
<script type='text/javascript' src='<%=webSiteUrl %>/js/jquery.hotkeys.0.8.0.js' charset='utf-8'></script>
<script type='text/javascript' src='<%=webSiteUrl %>/js/jquery.cookie.js' charset='utf-8'></script>
<script type="text/javascript" src="<%=webSiteUrl %>/js/jquery-ui-1.8.10.custom.min.js"></script>
<script type="text/javascript" src="<%=webSiteUrl %>/js/map.js"></script>
<script type="text/javascript" src="<%=webSiteUrl %>/js/stringRes.js"></script>
<script type="text/javascript" src="<%=webSiteUrl %>/js/language.js"></script>
<script type="text/javascript">
var errorMap = new Map();
$(document).ready(function() {
	var orgid11 = $("#orgid11").val();
	if(/^\s*$/.test(orgid11)){
		//alert("未取到");
		document.getElementById("tr1").style.display="";
	}else{
		//alert("取到："+orgid11);
		$("#orgid").val(orgid11);
		document.getElementById("tr1").style.display="none";
		}
});
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
<input type='hidden' id='siteurl' value="<%=webSiteUrl %>" />
<INPUT type="hidden" class="input" id="guestid" name="guestid" value="<%=guestid %>">
<INPUT type="hidden" class="input" id="type" name="type" value="<%=type %>">
<input type='hidden' id='orgid11' name='orgid11' value='<%=orgid11 %>' />
<input type='hidden' id='language' name='language' value='<%=language %>' />
	<div id="wrapper">
	<form id="loginform" name="loginform" action="ConfPage.go">
			<table width="1000" height="620" border="0" align="center" cellpadding="0" cellspacing="0" class="login_table" style="table-layout:inherit;">
				<tr>
					<td height="200" colspan="2" align="left" valign="top" class="login_top">
						<table width="100%" border="0">
							<tr>
								<td width="5%"></td>
								<td width="90%"></td>
								<td width="5%"><div class="lang_select" style="width:110px; padding-right:0px;">
							<img src="images/top_arrow.gif" width="10" height="10" class="top_arrow">
							<span onclick="showSelect(this,'sub_list')" ><%=lu.getLanguage(language,"language.select","语言选择") %>&nbsp;▼</span>
							<div id="sub_list" class="hidden" onclick="this.style.display = 'none'">
							<ul>
										<li><a href="javascript:void(0)" onclick='changeLanguageLogin("zh_cn")'>中文简体</a></li>
										<li><a href="javascript:void(0)" onclick='changeLanguageLogin("zh_tw")'>中文繁體</a></li>
										<li><a href="javascript:void(0)" onclick='changeLanguageLogin("en")'>English</a></li>
							</ul>
							</div></div></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="550" height="185"></td>
					<td width="450" valign="top">
						<div class="space_030">
						<table width="300" height="185" border="0" cellspacing="0" cellpadding="0" style="float:left">							
							<tr>
								<td colspan="3" height="10"></td>
							</tr>
							<tr id="tr1">
								<td width="79" height="22" class="font_010"><%=lu.getLanguage(language,"login.html.orgid","单位账号") %>:</td>
								<td width="231" colspan="2"><INPUT type="text" class="input" id="orgid"
									name="orgid" value=<%=orgid1 %>></td>
							</tr>
							<tr>
								<td colspan="3" height="18"></td>
							</tr>
							<tr>
								<td width="79" height="22" class="font_010"><%=lu.getLanguage(language,"topuserinfo.nickname","用户昵称") %>:</td>
								<td width="221" colspan="2">
								<INPUT type="text" class="input" id="alias" name="alias">
								</td>
							</tr>
							<tr>
								<td colspan="3" height="18"></td>
							</tr>
							<tr>
								<td class="font_010"></td>
								<td colspan="2" width="121" class="font_03"><span><a href='#' id="top-search-button"></a></span></td>
							</tr>
						</table>
						</div>											
					</td>
				</tr>
				<tr>
					<td width="580"></td>
					<td height="50" style="vertical-align:top;">
					<div id="msg_div"><span id="l_msg" class="error"></span></div>
					</td>
				</tr>
				<tr><td colspan="2" valign="top"><table width="100%" border="0">
				<tr>
					<td width="6%"></td>
					<td width="88%">
					<div style="font-size:12px"><font color="#056fb9" >
					</font></div>
					</td>
					<td width="6%"></td>
				</tr>
			</table></td></tr>
				<tr>
					<td height="100" colspan="2" align="left" valign="top">
						<table width="100%" border="0">
							<tr>
								<td width="45%" height="50"></td>
								<td width="55%" class="login_footer"></td>
							</tr>
							<tr>
								<td width="45%"></td>
								<td width="55%" class="login_footer"><%
				if(language.equals("zh_tw")){
			%>
				${applicationScope.footcontent_zh_tw}
			<%
				}else if(language.equals("en")){
			%>
				${applicationScope.footcontent_en}
			<%
				}else{
			%>
				${applicationScope.footcontent}
			<%
				}
			%></td>
							</tr>
						</table>
					</td>
				</tr>
			</table></form>			
	</div>
<script type="text/javascript">
$(function(){
	var siteurl = $('#siteurl').val();	
	var language = $('#language').val();
	//查看设置语言cookie
	var cookexist = getCookie("SGlanguage");
	if (cookexist == null){  
	    setCookie("SGlanguage", language);
	    location.reload();
	}
	
	function authcheck(){
		var orgid = $.trim($('#orgid').val());
		var alias = $.trim($('#alias').val());
 		var guestid = $.trim($("#guestid").val());
 		var type = $('#type').val();
 		var isUInt = /^[1-9]\d+|(admin)$/;
		var aliasRegex = /^(?!_)(?!-)(?!.*?_$)(?!.*?-$)[a-zA-Z0-9-_\u4e00-\u9fa5]+$/;
 		if ($.trim(orgid) == "") {
			$('#l_msg').text('单位账号不能为空!');
			$('#orgid').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
 		} else if($.trim(alias) == ""){
 			$('#l_msg').text('用户昵称不能为空!');
			$('#alias').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
 		} 
		else if (alias.length > 32) {
					$('#l_msg').text('昵称不能超过32位!');
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					$('#alias').focus();
					return false;
		} 
		else if (alias!=""&&!alias.match(aliasRegex)) {
					$('#l_msg').text('昵称中包含非法字符！');
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					$('#alias').focus();
					return false;
		} 		
 		else {
 			$("#l_msg").hide();
			$.post(siteurl+"/GuestLoginServlet",{ orgid:orgid,userid:guestid,userpass:"",type:type,rand:Math.random(),alias:alias } ,function(msg) {
				if(msg==0) {
					location.href=siteurl+'/GuestConfPage.go?inc=GuestConflist';
				}
				else if(msg == '-5' ) {
					//alert("是带协同的用户");
					location.href=siteurl+'/TopGuestConfPage.go?inc=TopGuestConfList';
				}else if(errorMap.get(msg+"")==null){
					$('#l_msg').text("未定义的错误信息(错误代码:"+msg+")");
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
				}	
				else {
					$('#l_msg').text(errorMap.get(msg+"")+"(错误代码:"+msg+")");
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
				}	
			});
 		}
	} 
 	$("#top-search-button").click(function() {
		authcheck();
		return false;
	});
	$("input").bind('keydown', 'return',function (evt){ authcheck(); return false; });
});

	function ignoreSpaces(string) {

		var temp = "";

		string = '' + string;

		splitstring = string.split(" ");

		for (i = 0; i < splitstring.length; i++)

			temp += splitstring[i];

		return temp;

	}
</script>
<div style="visibility:hidden;">
</div>
</body>
</html>
