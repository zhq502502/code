<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="com.seegle.util.PropUtil"%>
<%@page import="com.seegle.data.GetCookies"%>
<%@page import="com.seegle.util.LanguageUtil"%>
<html>
<head>
<%
String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		
Cookie cookies[]=request.getCookies(); 
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
<link rel='stylesheet' href='css/style_<%=language %>.css' type='text/css'>
<link rel="stylesheet" href="css/jquery-ui-1.8.10.custom-1.css" type="text/css" media="all" />
<script type='text/javascript' src='js/jquery-1.4.4.min.js' charset='utf-8'></script>
<script type='text/javascript' src='js/jquery.hotkeys.0.8.0.js' charset='utf-8'></script>
<script type='text/javascript' src='js/jquery.cookie.js' charset='utf-8'></script>
<script type="text/javascript" src="js/jquery-ui-1.8.10.custom.min.js"></script>
<script type="text/javascript" src="js/stringRes.js"></script>
<script type="text/javascript" src="js/language.js"></script>
</head>
<body>
<input type='hidden' id='siteurl' value=<%=webSiteUrl %> />
<div id="wrapper">
			<table width="1000" height="620" border="0" align="center" cellpadding="0" cellspacing="0" class="reg_table" style="table-layout:inherit;">
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
					<td width="450" valign="top" align="left">
						<div class="space_030">
						<table width="300" height="185" border="0" cellspacing="0" cellpadding="0" style="float: left">							
							<tr>
								<td colspan="3" height="10"></td>
							</tr>
							<tr>
								<td width="69" height="22" class="font_010"><%=lu.getLanguage(language,"login.html.orgid","单位账号") %>:</td>
								<td width="231" colspan="2"><INPUT type="text" class="input" id="orgid"
									name="orgid" ></td>
							</tr>
							<tr>
								<td colspan="3" height="20"></td>
							</tr>
							<tr>
								<td width="69" height="22" class="font_010"><%=lu.getLanguage(language,"reg.userpass","管理密码") %>:</td>
								<td colspan="2"><INPUT class="input" id="userpass"
									type="password" name="userpass"
									onpaste="return false"></td>
							</tr>
							<tr>
								<td height="18" colspan="3"></td>
							</tr>
							<tr>
								<td colspan="2" class="font_010"></td>
								<td valign="middle" class="font_03">
									<span><a href='#' id="regbutton"></a></span>
								</td>
							</tr>	
						</table>
						</div>											
					</td>
				</tr>
				<tr>
					<td></td>
					<td height="50" style="vertical-align:top;">
					<div id="msg_div"><span id="l_msg" class="error"></span></div>
					</td>
				</tr>
				<tr><td colspan="2" valign="top" height="50"></td></tr>
				<tr>
					<td height="100" colspan="2" align="left" valign="top">
						<table width="100%" border="0">
							<tr>
								<td width="45%"  height="50"></td>
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
			</table>			
	</div>
<script type="text/javascript">
$(function(){
	var siteurl = $('#siteurl').val();
	$('#regbutton').click(function(){
		var orgid = $('#orgid').val();			
		var userpass = $('#userpass').val();
		var numberRegex = /^[+]?\d+$/;
		if(orgid==""){
			$('#l_msg').text(getMsg(ERR_NO_ORGID));
			$('#orgid').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
		}
		if(!orgid.match(numberRegex)){
			$('#l_msg').text(getMsg(ERR_ORGID_IS_NUM));
			$('#orgid').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
		}
		else {
			$("#l_msg").hide();
			$.post(siteurl+"/company/reg", {orgid: orgid, userpass: userpass, _: new Date().getTime() } ,function(rs) {
				if(rs==0){
					alert(getMsg(REG_SUSCESS));
					location.reload();
				}
				else if(rs==-1){
					$('#l_msg').text(getMsg(REG_FAILURE)+getMsg(REG_GET_TOKEN_FAILURE));
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
					return false;
				}
				else if(rs==-3){
					$('#l_msg').text(getMsg(REG_FAILURE)+getMsg(REG_ADD_ADMIN_FAILURE));
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
					return false;
				}
				else if(rs==-4){
					$('#l_msg').text(getMsg(REG_FAILURE)+getMsg(REG_CREATE_TABLE_FAILURE));
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
					return false;
				}
				else if(rs==-5){
					$('#l_msg').text(getMsg(REG_FAILURE)+getMsg(REG_TABLE_EXIST));
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
					return false;
				}
				else if(rs==-6){
					$('#l_msg').text(getMsg(REG_FAILURE)+getMsg(REG_NO_COMPANY_INFORMATION));
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
					return false;
				}
			});
			return false;
		}
		return false;
	});
	
	$("#userpass").bind('keydown', 'Ctrl+a',function (evt){return false; });
	$("#userpass").bind('keydown', 'Ctrl+c',function (evt){return false; });
	$("#userpass").bind('keydown', 'Ctrl+v',function (evt){return false; });
	
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
