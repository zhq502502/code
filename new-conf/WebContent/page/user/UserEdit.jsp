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

String account = request.getAttribute("account").toString();
String alias = request.getAttribute("alias").toString();
String userpass = request.getAttribute("userpass").toString();
String email = request.getAttribute("email").toString();
String proxytype = request.getAttribute("proxytype").toString();
String proxyaddr = request.getAttribute("proxyaddr").toString();
String proxyport = request.getAttribute("proxyport").toString();
String proxyuser = request.getAttribute("proxyuser").toString();
String proxypass = request.getAttribute("proxypass").toString();
String able = "";
String style = "";
String readonly = "";
if(proxytype=="0"||proxytype==null||"0".equals(proxytype)||"".equals(proxytype)){
	able = "disabled";
    style = "style='background-color:#dddddd'";
    readonly = "readonly";
}else{
	able = "";
	style = "";
	readonly = "";
}
%>
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001"><%=lu.getLanguage(language,"useredit.modinfo","修改信息") %></div>
			</div>
		<div id="uploadTip">
		   <p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
		</div>		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD" class="tamnew">
		<form name="form" id="form" action="#" method="post" autocomplete='off'>
	      <tr>
            <td style="text-align: right;"><label for="account"><%=lu.getLanguage(language,"useradd.loginaccount","登录账号") %></label></td>
		    <td><label for="account1"><%=account %></label></td>
            <td style="text-align: right;"><label for="alias"><%=lu.getLanguage(language,"useradd.nickname","昵称") %></label></td>
		    <td><input name="alias" id="alias" size="22" type="text" class="Input02_w" value="<%=alias %>"/>
		    <input name="account" id="account" size="22" type="hidden" value="<%=account %>"/></td>
	      </tr>
	      <tr>
            <td style="text-align: right;"><label for="userpass"><%=lu.getLanguage(language,"useradd.userpass","账号密码") %></label></td>
		    <td><input name="userpass" id="userpass" size="22" type="password" class="Input02_w" value="<%=userpass %>"/></td>
            <td style="text-align: right;"><label for="userpass1"><%=lu.getLanguage(language,"useradd.userpass.confirmation","密码确认") %></label></td>
		    <td><input name="userpass1" id="userpass1" size="22" type="password" class="Input02_w" value="<%=userpass %>"/></td>
	      </tr>
	      <tr>
            <td style="text-align: right;"><label for="email"><%=lu.getLanguage(language,"useradd.email","邮箱地址") %></label></td>
		    <td colspan="3"><input name="email" id="email" size="22" type="text" class="Input02_w"  value="<%=email %>"/></td>
	      </tr>
	      <tr>
			<td style="text-align: right;"><label for="proxytype"><%=lu.getLanguage(language,"useradd.setproxytype","设置代理类型") %></label></td>
			<td colspan="3"><select name="proxytype" id="proxytype" class="select" onchange="changeproxy()" >
                	<c:choose>
	                       <c:when test="${proxytype == 1}">
	                           <option value="1">SOCKS 4</option>
	                           <option value="0">--<%=lu.getLanguage(language,"useradd.noproxy","不使用代理") %>--</option>
	                           <option value="2">SOCKS 5</option>
	                           <option value="3">HTTP</option>
	                       </c:when>
	                       <c:when test="${proxytype == 2}">
	                           <option value="2">SOCKS 5</option>
	                           <option value="0">--<%=lu.getLanguage(language,"useradd.noproxy","不使用代理") %>--</option>
	                           <option value="1">SOCKS 4</option>
	                           <option value="3">HTTP</option>
	                       </c:when>
	                       <c:when test="${proxytype == 3}">
	                           <option value="3">HTTP</option>
	                           <option value="0">--<%=lu.getLanguage(language,"useradd.noproxy","不使用代理") %>--</option>
	                           <option value="1">SOCKS 4</option>
	                           <option value="2">SOCKS 5</option>
	                       </c:when>
	                       <c:otherwise>
	                           <option value="0">--<%=lu.getLanguage(language,"useradd.noproxy","不使用代理") %>--</option>
	                           <option value="1">SOCKS 4</option>
	                           <option value="2">SOCKS 5</option>
	                           <option value="3">HTTP</option>
	                       </c:otherwise>
	                   </c:choose>
                    </select><font color="#ff0000">&nbsp;&nbsp;</font>
                    <br/>
                </td>
		</tr>
		<tr>
			<td style="text-align: right;"><label for="proxyaddr"><%=lu.getLanguage(language,"useradd.proxyaddr","代理服务器地址") %></label></td>
			<td><input name="proxyaddr" id="proxyaddr" size="22" type="text" class="Input02_w"  value="${proxyaddr}" <%=able%> <%=style%>/><font color="#ff0000">&nbsp;&nbsp;</font>
			</td>
			<td style="text-align: right;">
			<label for="proxyport"><%=lu.getLanguage(language,"useradd.proxyport","代理服务器端口") %></label>
			</td>
			<td><input name="proxyport" id="proxyport" size="22" type="text" class="Input02_w" value="${proxyport}"<%=able%> <%=style%>/><font color="#ff0000">&nbsp;&nbsp;</font></td>
		</tr>
		<tr>
			<td style="text-align: right;"><label for="proxyuser"><%=lu.getLanguage(language,"useradd.proxyuser","代理登录用户名") %></label></td>
			<td><input name="proxyuser" id="proxyuser" size="22" type="text" class="Input02_w" value="${proxyuser}" <%=able%> <%=style%>/></td>
			<td style="text-align: right;">
			<label for="proxypass"><%=lu.getLanguage(language,"useradd.proxypass","代理登录用户密码") %></label>
			</td>
			<td><input name="proxypass" id="proxypass" size="22" type="password" class="Input02_w" value="${proxypass}" <%=able%> <%=readonly%> <%=style%>/>
			<input name="proxypass" id="proxypasstext" size="22" type="text" class="Input02_w" value="" disabled style="display:none; background-color:#dddddd" /></td>
		</tr>
	     <tr>
		    <td colspan="4" valign="middle" style="height:40px; text-align:center;">
			<input type="submit" id="btnSearch" value="<%=lu.getLanguage(language,"button.modify","修改") %>" class="sbutton" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="reset_btn" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" />
		 </td>
	   	 </tr>
		</form>	
			</table>
		</div>
	</div>
</div>
    <script type="text/javascript" src="js/stringRes.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#user_usermanager').addClass("sel_tag");
			var siteurl = $('#siteurl').val();
			$('#form').submit(function(){
				var uid = $('#userid').val();
				var account = $('#account').val();
				var alias = $('#alias').val();
				var userpass = $('#userpass').val();	
				var userpass1 = $('#userpass1').val();	
			    var unameRegex = /^[a-zA-Z0-9\u0391-\uFFE5]+(([\-\_ ][a-zA-Z0-9\u0391-\uFFE5 ])?[a-zA-Z0-9\u0391-\uFFE5]*)*$/; 
			    var email = $('#email').val();
			    var proxyaddr = $('#proxyaddr').val();
			    var proxytype = $('#proxytype').val();
			    var proxyport = $('#proxyport').val();
			    var proxyuser = $('#proxyuser').val();
			    var proxypass = $('#proxypass').val();
			    var emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
			    var portRegex = /^(6553[0-5]|655[0-2]\d|65[0-4]\d\d|6[0-4]\d{3}|[1-5]\d{4}|[1-9]\d{0,3}|0)$/;
			    var proxIPRegex = /^\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b$/;
			    if (!alias.match(unameRegex)) {
					$('#l_msg').text(getMsg(ERR_USERNAME_STANDARD));
					$('#alias').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
			        return false;
			    }
			    if(userpass!=userpass1){
					$('#l_msg').text(getMsg(REG_PASSWORD_UNPAIR));
					$('#userpass').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
			    if (email != ""&&!email.match(emailRegex)) {
		        	$('#l_msg').text(getMsg(ERR_EMAIL_ADDRESS_ERROR));
					$('#email').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
			    }
			    if (proxytype != '0'&&proxyaddr == "") {
		        	$('#l_msg').text(getMsg(ERR_NO_PROXY_ADDRESS));
					$('#proxyaddr').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
		        }
		        if (proxytype != '0'&&!proxyaddr.match(proxIPRegex)) {
		        	$('#l_msg').text(getMsg(ERR_PROXY_ADDRESS_ERROR));
					$('#proxyaddr').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
		        }
		        if (proxytype != '0'&&proxyport == "") {
		        	$('#l_msg').text(getMsg(ERR_N0_PROXY_PORT));
					$('#proxyport').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
		        }
		        if (proxytype != '0'&&!proxyport.match(portRegex)) {
		        	$('#l_msg').text(getMsg(ERR_PORT_ERROR));
					$('#proxyport').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
		        }
			    else {
			    	$("#l_msg").hide();
					$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
						if(rs==null||rs==""){
							alert(getMsg(ERR_CURRENT_USER_UNEXIST));
							location.href = siteurl+'/logout';
						}
						else if(rs==1 || rs==3){
							$.post(siteurl+"/user/update", {action:'usermod',account: account, alias: alias,userpass: userpass,uid:uid,email: email,proxytype: proxytype,proxyaddr: proxyaddr,proxyport: proxyport,proxyuser: proxyuser,proxypass: proxypass, _: new Date().getTime() } ,function(rs) {
								if(rs==0){
									alert(getMsg(MOD_SUCESS));
									var page = "${nowPage}";
									location.href=siteurl+'/ConfPage.go?inc=UserManage&page='+page;
								} 
								else {
									alert(getMsg(MOD_FAILURE));
									return false;
								}
							});
						} 
						else {
							alert(getMsg(ERR_NO_PERMISSION));
							//location.href = siteurl+'/ConfPage.go?inc=ConfList';
							location.href = siteurl+'/logout';
							return false;
						}
					});			    
					return false;
				}
				return false;
			});	
			
			$('#reset_btn').click(function(){ //取消按钮
				var page = "${nowPage}";
				location.href=siteurl+'/ConfPage.go?inc=UserManage&page='+page;
			});
			
		});
		
		function changeproxy() {
		    var proxytype = $('#proxytype').val();
		    if (proxytype == "0"){
		    	document.getElementById("proxyaddr").value = "";
		    	document.getElementById("proxyuser").value = "";
		    	document.getElementById("proxyport").value = "";
		    	document.getElementById("proxypass").value = "";
		        document.getElementById("proxyaddr").disabled=true;
		        document.getElementById("proxyuser").disabled=true;
		        document.getElementById("proxyport").disabled=true;
		        document.getElementById("proxypass").disabled=true;
		        document.getElementById("proxypass").readOnly=true;
		        document.getElementById("proxyaddr").style.backgroundColor='#dddddd';
		        document.getElementById("proxyuser").style.backgroundColor='#dddddd';
		        document.getElementById("proxyport").style.backgroundColor='#dddddd';
		        document.getElementById("proxypass").style.backgroundColor='#dddddd';
		        if(document.getElementById("proxypasstext").style.display == "none"){
		        	document.getElementById("proxypass").style.display = "none";
			        document.getElementById("proxypasstext").style.display = "";
		        }
		    }
		    else{
		    	if(document.getElementById("proxypasstext").style.display == ""){
		        	document.getElementById("proxypass").style.display = "";
			        document.getElementById("proxypasstext").style.display = "none";
		        }
		        document.getElementById("proxyaddr").disabled=false;
		        document.getElementById("proxyuser").disabled=false;
		        document.getElementById("proxyport").disabled=false;
		        document.getElementById("proxypass").disabled=false;
		        document.getElementById("proxypass").readOnly=false;
		        document.getElementById("proxyaddr").style.backgroundColor='#ffffff';
		        document.getElementById("proxyuser").style.backgroundColor='#ffffff';
		        document.getElementById("proxyport").style.backgroundColor='#ffffff';
		        document.getElementById("proxypass").style.backgroundColor='#ffffff';
		    }
		}
	</script>