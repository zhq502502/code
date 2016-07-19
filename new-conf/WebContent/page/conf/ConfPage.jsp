<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.seegle.util.PropUtil"%>
<%@page import="com.seegle.util.LanguageUtil"%>
<%@page import="com.seegle.data.GetCookies"%>
<% 
String pagename = request.getParameter("inc"); 
if(pagename==null){
	pagename="ConfList.jsp";
}
String siteurl = request.getSession().getAttribute("siteurl").toString();
String userrole = request.getSession().getAttribute("userrole").toString();
String userid = request.getSession().getAttribute("userid").toString();
String userManage = request.getSession().getAttribute("userManage").toString();
String soft_version = request.getSession().getAttribute("soft_version").toString();
String nbsp_left = "";
String nbsp_right = "&nbsp;&nbsp;&nbsp;";
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
String currentalias=request.getSession().getAttribute("alias").toString();
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
<script type="text/javascript">
var client_registry = "${client_registry}";
var client_confname = "${client_confname}";
var client_sgplayname = "${client_sgplayname}";
var showitem = "${menu_default}";
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
			<input type='hidden' id='logopath' value="<%=request.getSession().getAttribute("logopath") %>" />
			<input type='hidden' id='orgid' value="<%=request.getSession().getAttribute("orgid") %>" />
			<input type='hidden' id='ptype' value="<%=request.getSession().getAttribute("proxytype") %>" />
			<input type='hidden' id='paddr' value="<%=request.getSession().getAttribute("proxyaddr") %>" />
			<input type='hidden' id='pport' value="<%=request.getSession().getAttribute("proxyport") %>" />
			<input type='hidden' id='puser' value="<%=request.getSession().getAttribute("proxyuser") %>" />
			<input type='hidden' id='ppass' value="<%=request.getSession().getAttribute("proxypass") %>" />
			<input type='hidden' id='download_meetUnOnline' value="${applicationScope.online_install_seegleconf_url }" />			
		</span>
		<!-- 头部HRADER 开始 -->
		<div id="header">
			<div class="head1" id="head">
				<div class="top2">
					<span class="navtop"><img width="1101" id="image" height="128" class="img_head"></span>
				</div>							
			</div>
			<div class="head2">
				<div class="top1">
					<span class="navtop">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="home_nowtime"></label></span>
				</div>
				<div class="top4">
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
								<td><span class="navtop" ><img src="images_gb/pic.png" width="14" id="image" height="15" class="img_exit"><%=lu.getLanguage(language,"confpage.currentuser","当前用户") %>：<span title="<%=currentalias%>"><%
									if (currentalias.length()>8){
										out.print(currentalias.substring(0, 8)+"...");
									}else{
										out.print(currentalias);
									}
								%>
	                            </span><label></label>&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout">[<%=lu.getLanguage(language,"confpage.logout","退出") %>]</a></span></td>
							</tr>
						</table>		
				</div>				
			</div>
		</div>
		<!-- 头部HRADER 结束 -->
		<div id="wpcontent">
			<div id="wpbody">
				<ul id="adminmenu" style="display:inline; margin-left: -236px;">
					<div id="left">		
						<!-- <div class="left-2" style="float: right"></div> -->
						<!--left-2-->
						<div class="left-1" style="float: left;">
							<div class="left-11"></div>
							<div class="left-12">
							<div class="left-menu"  style="width: 194px;margin-right: 0px;">
								<div class="space_02">
									<div class="title" id="1"><%=lu.getLanguage(language,"confpage.menu.myconf","我的会议") %></div>
									<div class="menu" id="opt_1">
										<ul>
											<li><span id='conf_list'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=ConfList"><%=lu.getLanguage(language,"confpage.menu.conflist","会议列表") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<li><span id='conf_logconf'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=LogConf"><%=lu.getLanguage(language,"confpage.menu.conflog","会议统计") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<li><span id='conf_loguser'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=LogUser"><%=lu.getLanguage(language,"confpage.menu.userlog","用户统计") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<li><span id='conf_download'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=Download"><%=lu.getLanguage(language,"confpage.menu.download","软件下载") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>
										</ul>
									</div>
								</div>
								<!-- 显示用户管理相关 -->
									
								<c:if test="${userManage eq showUsermanager}">										
								<div class="space_02">
								   <div class="title" id="2"><%=lu.getLanguage(language,"confpage.menu.myprofile","个人资料") %></div>
									<div class="menu" id="opt_2">
										<ul>
											<li><span id='user_userinfo'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=UserInfo"><%=lu.getLanguage(language,"confpage.menu.userinfo","个人信息") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>									
		                                    <li><span id='user_modpass'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=ModPass"><%=lu.getLanguage(language,"confpage.menu.modpass","修改密码") %></a><span class="sel-div"><%=nbsp_right%></span></span></li> 
										</ul>
									</div>
								</div>

								<c:if test="${userrole eq applicationScope.role_systemManager ||userrole eq applicationScope.role_userManager}">	
								<div class="space_02">
									<div class="title" id="3"><%=lu.getLanguage(language,"confpage.menu.usermange","用户管理") %></div>
									<div class="menu" id="opt_3">
										<ul>
											<c:if test="${userid eq applicationScope.account_admin}">
											<li><span id='pow_manager'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=UserSetManager"><%=lu.getLanguage(language,"confpage.menu.usersetmanager","权限设置") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											</c:if>
											<li><span id='user_adduser'><span class=""><%=nbsp_left%></span><a class="user_manage" href="<%=siteurl %>/ConfPage.go?inc=UserAdd"><%=lu.getLanguage(language,"confpage.menu.useradd","添加用户") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<li><span id='user_usermanager'><span class=""><%=nbsp_left%></span><a class="user_manage" href="<%=siteurl %>/ConfPage.go?inc=UserManage"><%=lu.getLanguage(language,"confpage.menu.useraccountmanage","账号管理") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<li><span id='user_addusers'><span class=""><%=nbsp_left%></span><a class="user_manage" href="<%=siteurl %>/ConfPage.go?inc=UsersAdd"><%=lu.getLanguage(language,"confpage.menu.usersadd","批量添加") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>
										</ul>
									</div>
								</div>
								</c:if>
								</c:if>

								<c:if test="${userrole eq applicationScope.role_systemManager ||userrole eq applicationScope.role_confManager}">
								<div class="space_02">
									<div class="title" id="4"><%=lu.getLanguage(language,"confpage.menu.confmanage","会议管理") %></div>
									<div class="menu" id="opt_4">
										<ul>
											<li><span id='conf_addconf'><span class=""><%=nbsp_left%></span><a class="conf_manage" href="<%=siteurl %>/ConfPage.go?inc=ConfAdd"><%=lu.getLanguage(language,"confpage.menu.confadd","新建会议") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<li><span id='conf_manage'><span class=""><%=nbsp_left%></span><a class="conf_manage" href="<%=siteurl %>/ConfPage.go?inc=ConfManage"><%=lu.getLanguage(language,"confpage.menu.confmanage","会议管理") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>
										</ul>
									</div>
								</div>
								</c:if>
								<%if(soft_version.equals("购买版")){ %>
								<div class="space_02">
									<div class="title" id="5"><%=lu.getLanguage(language,"confpage.menu.videomanage","录像管理") %></div>
									<div class="menu" id="opt_5">
										<ul>
											<li style="display: none"><span id='server_video_list'><span class=""><%=nbsp_left%></span><a href="<%=siteurl %>/ConfPage.go?inc=ServerVideoList"><%=lu.getLanguage(language,"confpage.menu.servervideolist","服务器录像列表") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>																					   
											<li><span id='video_list'><span class=""><%=nbsp_left%></span><a href="<%=siteurl %>/ConfPage.go?inc=VideoList"><%=lu.getLanguage(language,"confpage.menu.videolist","录像列表") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>	
											<c:if test="${userid eq applicationScope.account_admin}">
											<li><div class="left-sep"><span class=""><%=nbsp_left%></span></div><span id='video_upload'><a href="<%=siteurl %>/ConfPage.go?inc=VideoUpload"><%=lu.getLanguage(language,"confpage.menu.videoupload","上传录像") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>		
											</c:if>	
										</ul>
									</div>
								</div>
								<%} %>
								<c:if test="${sessionScope.userid eq applicationScope.account_admin}">
								<div class="space_02">
									<div class="title" id="6"><%=lu.getLanguage(language,"confpage.menu.systemsetting","系统设置") %></div>
									<div class="menu" id="opt_6">
										<ul>
											<li><span id='orglogo'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=ChangeLogo"><%=lu.getLanguage(language,"changelogo.upload","上传logo") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>										
											<li><span id='emailconfg'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=EmailConfig"><%=lu.getLanguage(language,"emailconfig.emailconfig","邮箱配置") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>										
											<%if(soft_version.equals("购买版")){ %>
											<li  style="display: none"><span id='backup'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=Backup"><%=lu.getLanguage(language,"backup.backuporrecovery","备份/还原") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>										
											<%} %>
											<!-- 
											<c:if test="${0 eq applicationScope.test_open}">
											<li><span id='memumanager'><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=MenuManager">菜单配置</a></span></li>
											</c:if>	 
											-->									
										</ul>
									</div>
								</div>
								</c:if>
								<div class="space_02"  style="display: none">
									<div class="title" id="7"><%=lu.getLanguage(language,"confpage.menu.phoneconf","电话会议") %></div>
									<div class="menu" id="opt_7">
										<ul>
											<li><span id='phoneconf_public'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=PublicPhoneconf"><%=lu.getLanguage(language,"confpage.menu.phoneconf.public","公共电话会议") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<c:if test="${phonetype ==applicationScope.phonetype_public ||phonetype ==applicationScope.phonetype_user || userid eq applicationScope.account_admin}">
											<li><span id='phoneconf_user'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=UserPhoneconf"><%=lu.getLanguage(language,"confpage.menu.phoneconf.personal","个人电话会议") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											</c:if>
											<c:if test="${phonetype ==applicationScope.phonetype_public ||userid eq applicationScope.account_admin}">
											<li><span id='phoneconf_alluser'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=AllUserPhoneconf"><%=lu.getLanguage(language,"confpage.menu.phoneconf.allpersonal","所有个人电话") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											</c:if>
											<c:if test="${userid eq applicationScope.account_admin}">
											<li><span id='phoneconf_power'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=SetPublicManager"><%=lu.getLanguage(language,"confpage.menu.usersetmanager","权限设置") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>
											</c:if>
											<li><span id='phoneconf_payhistory'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=PayHistory"><%=lu.getLanguage(language,"confpage.menu.phoneconf.payhistory","充值记录") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<li><span id='phoneconf_chargehistory'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=ChargeHistory"><%=lu.getLanguage(language,"confpage.menu.phoneconf.chargehistory","扣费记录") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>
											<c:if test="${userid eq applicationScope.account_admin}">
											<!--<li><span id='phoneconf_chargingedit'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=ChargingSetting"><%=lu.getLanguage(language,"confpage.menu.phoneconf.chargingsetting","计费管理") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>-->
											</c:if>
											<!-- <li><span id='phoneconf_bind'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=BindPhoneconf"><%=lu.getLanguage(language,"confpage.menu.phoneconf.binding","绑定电话会议") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li> -->								
										</ul>
									</div>
								</div>
								<c:if test="${sessionScope.userid eq applicationScope.account_admin || phonetype ==applicationScope.phonetype_public}">
								<div class="space_02"  style="display: none">
									<div class="title" id="8"><%=lu.getLanguage(language,"confpage.menu.phoneconsume","通话记录") %></div>
									<div class="menu" id="opt_8">
										<ul>
											<li><span id='consume_conf'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=PhoneConsumeConf"><%=lu.getLanguage(language,"confpage.menu.phoneconsume.conf","按会议查") %></a><span class="sel-div"><%=nbsp_right%></span></span><div class="left-sep"></div></li>										
											<li><span id='consume_phone'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=PhoneConsumePhone"><%=lu.getLanguage(language,"confpage.menu.phoneconsume.phone","按电话查") %></a><span class="sel-div"><%=nbsp_right%></span></span></li>										
											<!-- <li><span id='consume_org'><span class=""><%=nbsp_left%></span><a class="user_exist" href="<%=siteurl %>/ConfPage.go?inc=PhoneConsumeOrg"><%=lu.getLanguage(language,"confpage.menu.phoneconsume.org","按企业查") %></a><span class="sel-div"><%=nbsp_right%></span></span></li> -->										
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
	<div id="footer">
		<br>
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
			%>
		<br>
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
			if(document.getElementById("opt_"+show[s]) && getCookie("opt_"+show[s]) != "block"){   //此菜单存在;并且没有设置过cookie
				setCookie("opt_"+show[s],"none");
			}
		}
		
		//每次刷新时，
 		for(var i=1; i<=8; i++) {
 			if(document.getElementById("opt_"+i)){
	 			if(getCookie("opt_"+i) == "none"){
	 				document.getElementById("opt_"+i).style.display = "none";
	 			}else{
	 				document.getElementById("opt_"+i).style.display = "block";
	 			}
 			}
 		}
		
		var items = document.getElementsByClassName("title");
		for (var j=0; j<items.length; j++) {
			items[j].onclick = function() { //点击title
				var o = document.getElementById("opt_" + this.id);
				if (o.style.display == "none") {
					o.style.display = "block";
					setCookie("opt_"+this.id,"block");
				}
				else {
					o.style.display = "none";
					setCookie("opt_"+this.id,"none");
				}
			}
		}
		
		var siteurl = $('#siteurl').val();		
		$('.user_exist').click(function(){
			$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
				if(rs==null||rs==""){
					alert("当前用户不存在,请重新登录!");
					location.href = siteurl+'/logout';
				}
				else {
					return true;	
				}
			});
		});
		
		$('.user_manage').click(function(){
			$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
				if(rs==null||rs==""){
					alert(getMsg(ERR_CURRENT_USER_UNEXIST));
					location.href = siteurl+'/logout';
				}
				else if(rs==1 || rs==3){
					return true;	
				} else {
					alert(getMsg(ERR_NO_PERMISSION));
					location.href = siteurl+'/logout';
					return false;
				}
			});
		});
				
		$('.conf_manage').click(function(){
			$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
				if(rs==null||rs==""){
					alert(getMsg(ERR_CURRENT_USER_UNEXIST));
					location.href = siteurl+'/logout';
				}
				else if(rs==1 || rs==2){
					return true;	
				} 
				else {
					alert(getMsg(ERR_NO_PERMISSION));
					//location.href = siteurl+'/conf/ConfList';
					location.href = siteurl+'/logout';
					return false;
				}
			});
		});
		
	});

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