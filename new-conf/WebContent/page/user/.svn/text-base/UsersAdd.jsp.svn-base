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
%>
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001"><%=lu.getLanguage(language,"confpage.menu.usersadd","批量添加") %></div>
			</div>
		<div id="uploadTip">
		   <p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
		</div>		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD" class="tamnew">
		<form name="useraddform" id="useraddform" action="#" method="post" autocomplete='off'>
	      <tr>
            <td style="text-align: right;"><label for="account"><%=lu.getLanguage(language,"log.conf.usernum","用户数量") %></label></td>
		    <td colspan="3"><input name="account" id="account" size="22" type="text" class="Input02_w"/><font color="#FF0000">  *  </font>(<%=lu.getLanguage(language,"usersadd.usernumtips","请填写需要批量添加的用户数量,范围在1~999") %>)</td>
	      </tr>
	      <tr>
            <td style="text-align: right;"><label for="userpass"><%=lu.getLanguage(language,"useradd.userpass","账号密码") %></label></td>
		    <td><input name="userpass" id="userpass" size="22" type="password" class="Input02_w"/></td>
            <td style="text-align: right;"><label for="userpass1"><%=lu.getLanguage(language,"useradd.userpass.confirmation","密码确认") %></label></td>
		    <td><input name="userpass1" id="userpass1" size="22" type="password" class="Input02_w"/></td>
	      </tr>
	      <tr>
			<td style="text-align: right;"><label for="proxytype"><%=lu.getLanguage(language,"useradd.setproxytype","设置代理类型") %></label></td>
			<td><select  name="proxytype" id="proxytype" class="select" onchange="changeproxy()" >
					<option selected="" value="0">--<%=lu.getLanguage(language,"useradd.noproxy","不使用代理") %>--</option>
					<option value="1">SOCKS 4</option>
					<option value="2">SOCKS 5</option>
					<option value="3">HTTP</option>
					</select></td>
		</tr>
		<tr>
			<td style="text-align: right;"><label for="proxyaddr"><%=lu.getLanguage(language,"useradd.proxyaddr","代理服务器地址") %></label></td>
			<td><input name="proxyaddr" id="proxyaddr" size="22" type="text" class="Input02_w" disabled style="background-color:#dddddd"/><font color="#ff0000">&nbsp;&nbsp;</font>
			</td>
			<td style="text-align: right;">
			<label for="proxyport"><%=lu.getLanguage(language,"useradd.proxyport","代理服务器端口") %></label>
			</td>
			<td><input name="proxyport" id="proxyport" size="22" type="text" class="Input02_w" disabled style="background-color:#dddddd"/><font color="#ff0000">&nbsp;&nbsp;</font></td>
		</tr>
		<tr>
			<td style="text-align: right;"><label for="proxyuser"><%=lu.getLanguage(language,"useradd.proxyuser","代理登录用户名") %></label></td>
			<td><input name="proxyuser" id="proxyuser" size="22" type="text" class="Input02_w" disabled style="background-color:#dddddd"/></td>
			<td style="text-align: right;">
			<label for="proxypass"><%=lu.getLanguage(language,"useradd.proxypass","代理登录用户密码") %></label>
			</td>
			<td><input name="proxypass" id="proxypass" size="22" type="password" class="Input02_w" disabled style="background-color:#dddddd"/></td>
		</tr>
	     <tr>
		    <td colspan="4" valign="middle" style="height:40px; text-align:center;">
			<input type="submit" id="btnSearch" value="<%=lu.getLanguage(language,"button.addnew","新建") %>" class="sbutton" />
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
			$('#user_addusers').addClass("sel_tag");
			var siteurl = $('#siteurl').val();
			$('#useraddform').submit(function(){
				var account = $('#account').val();
				var userpass = $('#userpass').val();	
				var userpass1 = $('#userpass1').val();	
			    var proxyaddr = $('#proxyaddr').val();
			    var proxytype = $('#proxytype').val();
			    var proxyport = $('#proxyport').val();
			    var proxyuser = $('#proxyuser').val();
			    var proxypass = $('#proxypass').val();
			    var accountRegex = /^([1-9]|[1-9][0-9]|[1-9][0-9][0-9]|(999))$/;  //1~999
			    var portRegex = /^(6553[0-5]|655[0-2]\d|65[0-4]\d\d|6[0-4]\d{3}|[1-5]\d{4}|[1-9]\d{0,3}|0)$/;
			    var proxIPRegex = /^\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b$/;
			    if(account==""){
			    	$('#l_msg').text(getMsg(ERR_NO_USER_NUMBER));
					$('#account').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
			    }
			    if(!account.match(accountRegex)){
			    	$('#l_msg').text(getMsg(ERR_USER_NUMBER_ERROR));
					$('#account').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
			    }
			    if(userpass!=userpass1){
					$('#l_msg').text(getMsg(REG_PASSWORD_UNPAIR));
					$('#userpass').focus();
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
							$.post(siteurl+"/user/update", {action:'mutiluseradd',account: account, userpass: userpass,proxytype: proxytype,proxyaddr: proxyaddr,proxyport: proxyport,proxyuser: proxyuser,proxypass: proxypass, _: new Date().getTime() } ,function(rs1) {
								if(rs1==0){
									alert(getMsg(ADD_SUCESS));
									location.href=siteurl+'/ConfPage.go?inc=UserManage';
								} 
								else {
									alert(getMsg(ADD_FAILURE));
									return false;
								}
							});	
						} else {
							alert(getMsg(ERR_NO_PERMISSION));
							location.href = siteurl+'/logout';
							return false;
						}
					});
					return false;
				}
				return false;
			});	
		});
		
		function changeproxy() {
		    var proxytype = document.useraddform.proxytype.value;
		    if (proxytype == 0){
		    	document.useraddform.proxyaddr.value = "";
		    	document.useraddform.proxyuser.value = "";
		    	document.useraddform.proxyport.value = "";
		    	document.useraddform.proxypass.value = "";
		        document.useraddform.proxyaddr.disabled=true;
		        document.useraddform.proxyuser.disabled=true;
		        document.useraddform.proxyport.disabled=true;
		        document.useraddform.proxypass.disabled=true;
		        document.useraddform.proxyaddr.style.backgroundColor='#dddddd';
		        document.useraddform.proxyuser.style.backgroundColor='#dddddd';
		        document.useraddform.proxyport.style.backgroundColor='#dddddd';
		        document.useraddform.proxypass.style.backgroundColor='#dddddd';
		    }
		    else{
		    	document.useraddform.proxyaddr.disabled=false;
		        document.useraddform.proxyuser.disabled=false;
		        document.useraddform.proxyport.disabled=false;
		        document.useraddform.proxypass.disabled=false;
		        document.useraddform.proxyaddr.style.backgroundColor='#ffffff';
		        document.useraddform.proxyuser.style.backgroundColor='#ffffff';
		        document.useraddform.proxyport.style.backgroundColor='#ffffff';
		        document.useraddform.proxypass.style.backgroundColor='#ffffff';
		    }
		}
	</script>