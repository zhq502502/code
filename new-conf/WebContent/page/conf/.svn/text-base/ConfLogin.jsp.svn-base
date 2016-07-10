<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.seegle.data.GetConfigFile"%>
<%@page import="com.seegle.util.PropUtil"%>
<%@page import="com.seegle.data.GetCookies"%>
<%@page import="com.seegle.util.LanguageUtil"%>
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

String orgid = "";
String userid = "";
String userpass = "";
String confid = "";
String skiplogin = "0";
String nickname = "";

if(request.getParameter("orgid")!=null){
	orgid = request.getParameter("orgid");
}
if(request.getParameter("userid")!=null){
	userid = request.getParameter("userid");
}else{
	skiplogin = "1"; //不直接登录
}
if(request.getParameter("userpass")!=null){
	userpass = request.getParameter("userpass");
}else{
	skiplogin = "1";//不直接登录
}
if(request.getParameter("confid")!=null){
	confid = request.getParameter("confid");
}
if(request.getParameter("nickname")!=null){
	nickname = request.getParameter("nickname");
}
if(skiplogin != "1"){
	%>
<jsp:forward page="ConfLoginQ.jsp?orgid=<%=orgid%>&confid=<%=confid%>&userid=<%=userid%>&userpass=<%=userpass%>&nickname=<%=nickname%>" />
	<%
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${applicationScope.titlecontent }</title>
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
<script type="text/javascript" src="js/stringRes.js"></script>
<script type="text/javascript" src="js/language.js"></script>
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

</script>
</head>
<body>
	<div id="wpwrap">
		<span style='display: none;'> 
			<input type='hidden' id='siteurl' value='<%=webSiteUrl %>' />
			<input type='hidden' id='apiurl' value='<%=apiurl %>'/>
			<input type="hidden" id="orgid" name="orgid" value='<%=orgid %>' />	
			<input type="hidden" id="confid" name="confid" value='<%=confid %>'/>
			<input type="hidden" id="skiplogin" name="skiplogin" value='<%=skiplogin %>'/>
			<input type="hidden" id="nickname" name="nickname" value="<%=nickname %>" />
			<input type='hidden' id='download_meetUnOnline' value="${applicationScope.online_install_seegleconf_url }" />	
		</span>
			<table width="1000" height="620" border="0" align="center" cellpadding="0" cellspacing="0" class="login_table">
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
										<li><a href="#" onclick='changeLanguageLogin("zh_cn")'>中文简体</a></li>
										<li><a href="#" onclick='changeLanguageLogin("zh_tw")'>中文繁體</a></li>
										<li><a href="#" onclick='changeLanguageLogin("en")'>English</a></li>
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
								<td width="69" height="22" class="font_010"><%=lu.getLanguage(language,"login.html.userid","用户账号") %>:</td>
								<td width="241" colspan="2"><INPUT type="text" class="input"
									id="userid" name="userid" value='<%=userid %>' ></td>
							</tr>
							<tr>
								<td colspan="3" height="15"></td>
							</tr>
							<tr>
								<td height="22" class="font_010"><%=lu.getLanguage(language,"login.html.userpass","登录密码") %>:</td>
								<td colspan="2"><INPUT class="input" id="userpass"
									type="password" name="userpass"
									onpaste="return false" value='<%=userpass %>' ></td>
							</tr>
							<tr>
								<td height="8" colspan="3" class="font_010"></td>
							</tr>
							<tr>
								<td class="font_01"></td>
								<td colspan="2">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="85" class="font_02" style=" padding-top:3px;"></td>
											<td width="121" class="font_03"><input type="submit" name="login" id="login" class="sbutton" value="<%=lu.getLanguage(language,"button.conflogin","进入会议") %>"/></td>

										</tr>
									</table>
								</td>
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
				<tr><td colspan="2"><table width="100%" border="0">
				<tr>
					<td width="5%"></td>
					<td width="90%"></td>
					<td width="5%"></td>
				</tr>
			</table></td></tr>
				<tr>
					<td height="100" colspan="2" align="left" valign="top">
						<table width="100%" border="0">
							<tr>
								<td width="45%" height="45"></td>
								<td width="55%" class="login_footer"></td>
							</tr>
							<tr>
								<td width="45%"></td>
								<td width="55%" class="login_footer">${applicationScope.footcontent }</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>		
	</div>
</body>
</html>
