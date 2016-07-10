<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	pagename="TopConfList.jsp";
}
String siteurl = request.getSession().getAttribute("siteurl").toString();
String userid = request.getSession().getAttribute("userid").toString();
String alias = request.getSession().getAttribute("alias").toString();
String orgname = request.getSession().getAttribute("orgname").toString();
String nbsp_left = "";
String nbsp_right = "&nbsp;&nbsp;&nbsp;";
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
<script type="text/javascript" src="js/stringRes.js"></script>
<script type="text/javascript" src="js/language.js"></script>
<script type="text/javascript" src="js/map.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript">
var errorMap = new Map();
</script>
<c:forEach var="list" items="${errorCodeList}">
<script>errorMap.put("${list.key}","${list.value}");</script>
</c:forEach>
<script type="text/javascript">
var client_registry = "${client_registry}";
var client_confname = "${client_confname}";
var client_sgplayname = "${client_sgplayname}";
var showitem = "${top_menu_default}";
</script>
</head>
<body>
	<div id="wpwrap">
		<span style='display: none;'> 
			<input type='hidden' id='siteurl' name='siteurl' value="<%=siteurl%>" /> 
			<input type='hidden' id='uid' value="<%=request.getSession().getAttribute("uid") %>" />
			<input type='hidden' id='userid' value="<%=userid %>" />
			<input type='hidden' id='upassword' value="<%=request.getSession().getAttribute("userpass") %>" />
			<input type='hidden' id='ualias' value="<%=request.getSession().getAttribute("alias") %>" />
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
				<div class="top12">		
					<div class="top11">
						<span class="navtop">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="home_nowtime"></label></span>
					</div>			
					<div class="top">
						<table width="" border="0">
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
									<td width="260"><span class="navtop" id="navtop_logout"><img src="images_gb/pic.png" width="14" height="15" class="img_exit"><%=lu.getLanguage(language,"confpage.currentuser","当前用户") %>：<span title="<%=alias%>"><%
										if (alias.length()>8){
											out.print(alias.substring(0, 8)+"...");
										}else{
											out.print(alias);
										}
									%>
		                            </span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout">[<%=lu.getLanguage(language,"confpage.logout","退出") %>]</a></span></td>
								</tr>
						</table>
					</div>
				</div>								
			</div>
		</div>
		<!-- 头部HRADER 结束 -->
		<div id="wpcontent">
			<div id="wpbody">
				<ul id="adminmenu" style="display:inline; margin-left: -236px;">
					<div id="left">		
						<!--left-2-->
						<div class="left-1" style="float: left;">
							<div class="left-11"></div>
							<div class="left-12">
							<div class="left-menu"  style="width: 194px;margin-right: 0px;">							
								<div class="space_02">
									<div class="title" id="1"><%=lu.getLanguage(language,"topconfpage.menu.basicfuction","基本功能") %></div>
									<div class="menu" id="item_1">
										<ul>
											<li><span id='conf_personalinfo'><span class=""><%=nbsp_left%></span><a id="conf_personalinfo" href="<%=siteurl %>/TopConfPage.go?inc=TopUserInfo"><%=lu.getLanguage(language,"confpage.menu.userinfo","个人信息") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>
										</ul>
									</div>
								</div>
		
								<div class="space_02">
									<div class="title" id="2"><%=lu.getLanguage(language,"confpage.menu.myconf","我的会议") %></div>
									<div class="menu" id="item_2">
										<ul>
											<li><span id='conf_list'><span class=""><%=nbsp_left%></span><a id="conf_list" href="<%=siteurl%>/TopConfPage.go?inc=TopConfList"><%=lu.getLanguage(language,"confpage.menu.conflist","会议列表") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>									
											<li><span id='conf_logconf'><span class=""><%=nbsp_left%></span><a id="conf_logconf" href="<%=siteurl%>/TopConfPage.go?inc=TopLogConf"><%=lu.getLanguage(language,"confpage.menu.conflog","会议统计") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<li><span id='conf_loguser'><span class=""><%=nbsp_left%></span><a id="conf_loguser" href="<%=siteurl%>/TopConfPage.go?inc=TopLogUser"><%=lu.getLanguage(language,"confpage.menu.userlog","用户统计") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<li><span id='conf_download'><span class=""><%=nbsp_left%></span><a id="conf_download" href="<%=siteurl %>/TopConfPage.go?inc=TopDownload"><%=lu.getLanguage(language,"confpage.menu.download","软件下载") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>
										</ul>
									</div>
								</div>
								
								<c:if test="${sessionScope.userid eq 1000||sessionScope.userid eq applicationScope.account_admin}">
								<div class="space_02" id="sys_mod">
									<div class="title" id="3"><%=lu.getLanguage(language,"confpage.menu.systemsetting","系统设置") %></div>
									<div class="menu" id="item_3">
										<ul>
											<li><span id='org_editname'><span class=""><%=nbsp_left%></span><a id="org_editname" href="<%=siteurl %>/TopConfPage.go?inc=TopModOrgName"><%=lu.getLanguage(language,"topconfpage.menu.modorgname","修改企业名称") %></a><span class="sel-div">&nbsp;&nbsp;&nbsp;</span></span><div class="left-sep"></div></li>	
											<li><span id='emailconfg'><span class=""><%=nbsp_left%></span><a class="emailconfg" href="<%=siteurl %>/TopConfPage.go?inc=TopEmailConfig"><%=lu.getLanguage(language,"emailconfig.emailconfig","邮箱配置") %></a><span class="sel-div">&nbsp;&nbsp;&nbsp;</span></span><div class="left-sep"></div></li>		
											<li><span id='confmanage'><span class=""><%=nbsp_left%></span><a class="confmanage" href="<%=siteurl %>/TopConfPage.go?inc=TopConfManage"><%=lu.getLanguage(language,"confpage.menu.confmanage","会议管理") %></a><span class="sel-div">&nbsp;&nbsp;&nbsp;</span></span></li>						
										</ul>
									</div>
								</div>
								</c:if>
								
								<c:if test="$userid eq applicationScope.account_admin}">
								<div class="space_02" id="user_wmc">
									<div class="title" id="4"><%=lu.getLanguage(language,"confpage.menu.usermange","用户管理") %></div>
									<div class="menu" id="item_4">
										<ul>
											<li><span id='user_addmanager'><span class=""><%=nbsp_left%></span><a id="user_addmanager" href="<%=siteurl %>/TopConfPage.go?inc=TopUserSetManager"><%=lu.getLanguage(language,"topconfpage.menu.setmanager","设置管理员") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>
										</ul>
									</div>
								</div>
								</c:if>
								
								<div class="space_02">
									<div class="title" id="5"><%=lu.getLanguage(language,"confpage.menu.phoneconf","电话会议") %></div>
									<div class="menu" id="item_5">
										<ul>
											<li><span id='phoneconf_public'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/TopConfPage.go?inc=PublicPhoneconf&removeother=1"><%=lu.getLanguage(language,"confpage.menu.phoneconf.public","公共电话会议") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											
											<c:if test="${phonetype ==applicationScope.phonetype_public ||phonetype ==applicationScope.phonetype_user || userid eq 1000||userid eq applicationScope.account_admin}">
											<li><span id='phoneconf_user'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/TopConfPage.go?inc=UserPhoneconf&removeother=1"><%=lu.getLanguage(language,"confpage.menu.phoneconf.personal","个人电话会议") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											</c:if>
											<c:if test="${phonetype ==applicationScope.phonetype_public ||userid eq 1000||userid eq applicationScope.account_admin}">
											<li><span id='phoneconf_alluser'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/TopConfPage.go?inc=AllUserPhoneconf&removeother=1"><%=lu.getLanguage(language,"confpage.menu.phoneconf.allpersonal","所有个人电话") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											</c:if>
											<c:if test="${userid eq 1000&&orgid eq 90000}">
											<li><span id='operuser'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/TopConfPage.go?inc=TopOperuser&removeother=1"><%=lu.getLanguage(language,"confpage.menu.phoneconf.operuser","操作员设置") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											</c:if>
											<c:if test="${isoperator eq 1 ||(userid eq 1000 &&orgid eq 90000) }">
											<li><span id='phoneconf_chargingedit'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/TopConfPage.go?inc=ChargingSetting"><%=lu.getLanguage(language,"confpage.menu.phoneconf.chargingsetting","计费管理") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											</c:if>	
											<li><span id='phoneconf_payhistory'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/TopConfPage.go?inc=PayHistory"><%=lu.getLanguage(language,"confpage.menu.phoneconf.payhistory","充值记录") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<li><span id='phoneconf_chargehistory'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/TopConfPage.go?inc=ChargeHistory"><%=lu.getLanguage(language,"confpage.menu.phoneconf.chargehistory","扣费记录") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
										</ul>
									</div>
								</div>
								<c:if test="${sessionScope.userid eq 1000||sessionScope.userid eq applicationScope.account_admin || phonetype ==applicationScope.phonetype_public||true}">
								<div class="space_02">
									<div class="title" id="6"><%=lu.getLanguage(language,"confpage.menu.phoneconsume","通话记录") %></div>
									<div class="menu" id="item_6">
										<ul>
											<li><span id='consume_conf'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/TopConfPage.go?inc=PhoneConsumeConf"><%=lu.getLanguage(language,"confpage.menu.phoneconsume.conf","按会议查") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>										
											<li><span id='consume_phone'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/TopConfPage.go?inc=PhoneConsumePhone"><%=lu.getLanguage(language,"confpage.menu.phoneconsume.phone","按电话查") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>										
											<c:if test="${orgid eq 90000}">
											<li><span id='consume_org'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/TopConfPage.go?inc=PhoneConsumeOrg"><%=lu.getLanguage(language,"confpage.menu.phoneconsume.org","按企业查") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>
											</c:if>
										</ul>
									</div>
								</div>
								</c:if>
								<c:if test="${(sessionScope.userid eq 1000 ||isserveruser eq 1)&&orgid eq 90000}">
								<div class="space_02">
									<div class="title" id="7"><%=lu.getLanguage(language,"confpage.menu.otherphone","外企电话") %></div>
									<div class="menu" id="item_7">
										<ul>
											<%--<li><span id='otherorgid'><span class=""><%=nbsp_left%></span><%=lu.getLanguage(language,"phonelog.orgid","企业ID") %>:<input style="width: 50px" maxlength="6"/><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											--%><li><span id='otherphoneconf_public'><span class=""><%=nbsp_left%></span><a class="user_exist" href="javascript:void(0)" onclick="otherorgclick('<%=siteurl %>/TopConfPage.go?inc=PublicPhoneconf')"><%=lu.getLanguage(language,"confpage.menu.phoneconf.public","公共电话会议") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<li><span id='otherphoneconf_alluser'><span class=""><%=nbsp_left%></span><a class="user_exist" href="javascript:void(0)" onclick="otherorgclick('<%=siteurl %>/TopConfPage.go?inc=AllUserPhoneconf')"><%=lu.getLanguage(language,"confpage.menu.phoneconf.allpersonal","所有个人电话") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>
										</ul>
									</div>
								</div>
								</c:if>
								<div class="left-12-1"></div>								
							</div>
							</div>
							<div class="left-13">							
							<div class="left-13-2"></div>
							</div>
						</div>
						<!--left-1-->
						
					</div>
					<!--left-->
				</ul>
				<jsp:include page="<%=pagename%>"/>			
			</div>
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
	document.getElementsByClassName = function(cl) {
		var retnode = [];
		var myclass = new RegExp('\^'+cl+'\$');
		var elem = this.getElementsByTagName('*');
		for (var j = 0; j < elem.length; j++) {
		var classes = elem[j].className;
		if (myclass.test(classes)) retnode.push(elem[j]);
		}
		return retnode;
	}

	//--- 隐藏所有
	function HideAll() {
		var items = document.getElementsByClassName("menu");
		for (var j=0; j<items.length; j++) {
			items[j].style.display = "none";
		}
	}

	$(document).ready(function() {
		//默认菜单状态
		show = showitem.split(",");
		for(var s=0; s<=show.length; s++){
			if(document.getElementById("item_"+show[s]) && getCookie("item_"+show[s]) != "block"){   //此菜单存在;并且没有设置过cookie
					setCookie("item_"+show[s],"none");
			}
		}
		
		//每次刷新时，
		for(var i=1; i<=7; i++) {
			if(document.getElementById("item_"+i)){
				if(getCookie("item_"+i) == "none"){
					document.getElementById("item_"+i).style.display = "none";
				}else{
					document.getElementById("item_"+i).style.display = "block";
				}
			}
		}
		var items = document.getElementsByClassName("title");
		for (var j=0; j<items.length; j++) {
			items[j].onclick = function() { //点击title
				var o = document.getElementById("item_" + this.id);
				if (o.style.display == "none") {
					o.style.display = "block";
					setCookie("item_"+this.id,"block");
				}
				else {
					o.style.display = "none";
					setCookie("item_"+this.id,"none");
				}
			}
		}	

	});
	function otherorgclick(url){
		var otherorgid_cookie = $.cookie("otherorgid_cookie");
		if(otherorgid_cookie==null||otherorgid_cookie==""||otherorgid_cookie=="0"){
			otherorgid_cookie="${orgid}";
		}
		location.href=url+"&otherorgid="+otherorgid_cookie;
	}
	
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