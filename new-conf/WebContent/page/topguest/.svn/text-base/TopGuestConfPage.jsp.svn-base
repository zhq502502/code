<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
 
String pagename = request.getParameter("inc"); 
if(pagename==null){
	pagename="TopGuestConfList.jsp";
}
String siteurl = request.getSession().getAttribute("siteurl").toString();
String alias = request.getSession().getAttribute("alias").toString();
String userid = request.getSession().getAttribute("userid").toString();
String orgname = request.getSession().getAttribute("orgname").toString();
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<link rel="stylesheet" href="css/jquery-ui-1.8.10.custom.css" type="text/css" media="all" />
<script type='text/javascript' src='js/jquery-1.4.4.min.js' charset='utf-8'></script>
<script type="text/javascript" src="js/jquery-ui-1.8.10.custom.min.js" charset='utf-8'></script>
<script type='text/javascript' src='js/jquery.simplemodal.js' charset='utf-8'></script>
<script type='text/javascript' src='js/jquery.ba-dotimeout.js' charset='utf-8'></script>
<script type='text/javascript' src='js/jquery.hiAlerts-min.js' charset='utf-8'></script>	
<script type="text/javascript" src="js/date/WdatePicker.js" charset='utf-8'></script>
<script type="text/javascript" src="js/language.js"></script>
<script type="text/javascript" src="js/stringRes.js"></script>
<script type="text/javascript">
var client_registry = "${client_registry}";
var client_confname = "${client_confname}";
var client_sgplayname = "${client_sgplayname}";
</script>
</head>
<body>
	<div id="wpwrap">
		<span style='display: none;'> 
			<input type='hidden' id='siteurl' name='siteurl' value="<%=siteurl%>" /> 
			<input type='hidden' id='uid' value="<%=request.getSession().getAttribute("uid") %>" />
			<input type='hidden' id='userid' value="<%=userid %>" />
			<input type='hidden' id='upassword' value="<%=request.getSession().getAttribute("userpass")==null?"":request.getSession().getAttribute("userpass") %>" />
			<input type='hidden' id='ualias' value="<%=alias %>" />
			<input type='hidden' id='apiurl' value="<%=request.getSession().getAttribute("apiurl") %>" />
			<input type='hidden' id='orgid' value="<%=request.getSession().getAttribute("orgid") %>" />
            <input type='hidden' id='download_seegleOnline' value="${applicationScope.online_install_seegletop_url }" />
		</span>
		<!-- 头部HRADER 开始 -->
		<div id="header">
			<div class="head">
				<div class="top2">
					<span class="navtop"><%=lu.getLanguage(language,"topconfpage.header","欢迎来到云会议租用平台") %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" target="_self" onclick="javascript:var url=document.getElementById('siteurl').value; try{window.external.AddFavorite(url,'云会议租用平台'); } catch(e){ (window.sidebar)?window.sidebar.addPanel('云会议租用平台',url,''):alert('请使用按键 Ctrl+d，收藏云会议租用平台'); }"><%=lu.getLanguage(language,"topconfpage.favorite","收藏") %></a></span>
				</div>	
				
				<div class="top3">
					<span class="navtop" id="navtop_orgname"><label id="org_name"><%=orgname %></label></span>
				</div>
										
				<div class="top11">
					<span class="navtop">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="home_nowtime"></label></span>
				</div>	
				<div class="top12">
				<div class="top">
					<table width="100%" border="0">
							<tr>
								<td width="110"><div class="lang_select" style="width:110px; padding-right:0px;">
							<img src="images/top_arrow.gif" width="10" height="10" class="top_arrow">
							<span onclick="showSelect(this,'sub_list')" ><%=lu.getLanguage(language,"language.select","语言选择") %>&nbsp;▼</span>
							<div id="sub_list" class="hidden" onclick="this.style.display = 'none'">
							<ul>
										<li><a href="javascript:void(0)" onclick='changeLanguageLogin("zh_cn")'>中文简体</a></li>
										<li><a href="javascript:void(0)" onclick='changeLanguageLogin("zh_tw")'>中文繁體</a></li>
										<li><a href="javascript:void(0)" onclick='changeLanguageLogin("en")'>English</a></li>
							</ul>
							</div></div></td>
								<td width="260"><span class="navtop" id="navtop_logout"><img src="images_gb/pic.png" width="14" height="15" class="img_exit"><%=lu.getLanguage(language,"guest.currentguest","当前游客") %>：<%=alias %>&nbsp;&nbsp;&nbsp;&nbsp;<a href="guestlogout">[<%=lu.getLanguage(language,"confpage.logout","退出") %>]</a></span></td>
							</tr>
						</table>
				</div>	
				</div>						
			</div>
		</div>
		<!-- 头部HRADER 结束 -->
		<div id="wpcontent">
				<jsp:include page="<%=pagename%>"/>	
				<div class="clear"></div>
			<!-- wpbody -->
			<div class="clear"></div>
		</div>
		<!-- wpcontent -->
	</div>
	<!-- wpwrap -->
	<!-- footer begin -->
	<div id="footer"><br>
		<br><%
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
			%><br>
		<div class="clear"></div>
	</div>
	<!-- footer end -->
<script type="text/javascript">
	var logopath = document.getElementById("logopath").value;
    document.getElementById("image").src=logopath;
	
	var div=document.getElementById("home_nowtime");
	function aa() 
	{
	var date=new Date()
	var year=date.getFullYear();
	var month=date.getMonth();
	var day=date.getDate();
	var hh=date.getHours();
	var minute=date.getMinutes();
	if(minute<10){
		div.innerHTML=""+(year)+getMsg(YEAR)+(month+1)+getMsg(MONTH)+day+getMsg(DATE)+"      "+hh+":0"+minute
	}else{
		div.innerHTML=""+(year)+getMsg(YEAR)+(month+1)+getMsg(MONTH)+day+getMsg(DATE)+"      "+hh+":"+minute
	}
	}
	window.onload=function(){
	aa();
	setInterval(aa,1000);
	}	
</script>
</body>
</html>