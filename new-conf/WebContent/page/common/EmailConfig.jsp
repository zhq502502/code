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
				<div class="s_001"><%=lu.getLanguage(language,"emailconfig.emailconfig","邮箱配置") %></div>
			</div>
		<div id="uploadTip">
		   <p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
		</div>		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD" class="tamnew">
		<form name="form" id="form" action="#" method="post" autocomplete='off'>
	      <tr>
            <td style="text-align: right;"><label for="emailhost"><%=lu.getLanguage(language,"emailconfig.emailhost","邮箱认证服务器") %></label></td>
		    <td><input value="${emailConfig.emailhost }" name="emailhost" id="emailhost" size="22" type="text" class="Input02" style="width: 200px"/>
		    <font color="#FF0000">  *</font>
		    </td>
            <td style="text-align: right;"><label for="emailport"><%=lu.getLanguage(language,"emailconfig.emailport","认证服务器端口") %></label></td>
		    <td><input value="${emailConfig.emailport }" name="emailport" id="emailport" size="22" type="text" class="Input02" style="width: 200px"/>
		    <font color="#FF0000">  *</font>
		    </td>
	      </tr>
	      <tr>
            <td style="text-align: right;"><label for="emailloginname"><%=lu.getLanguage(language,"emailconfig.emailloginname","邮箱登录账户") %></label></td>
		    <td><input value="${emailConfig.emailloginname }" name="emailloginname" id="emailloginname" size="22" type="text" class="Input02" style="width: 200px"/>
		    <font color="#FF0000">  *</font>
		    </td>
            <td style="text-align: right;"><label for="emailalias"><%=lu.getLanguage(language,"emailconfig.emailalias","发件人昵称") %></label></td>
		    <td><input value="${emailConfig.emailalias }" name="emailalias" id="emailalias" size="22" type="text" class="Input02" style="width: 200px"/>
		    <font color="#FF0000">  *</font>
		    </td>
	      </tr>
	      <tr>
            <td style="text-align: right;"><label for="emailloginpwd"><%=lu.getLanguage(language,"emailconfig.emailloginpwd","邮箱密码") %></label></td>
		    <td><input value="${emailConfig.emailloginpwd }" name="emailloginpwd" id="emailloginpwd" size="22" type="password" class="Input02" style="width: 200px"/>
		    <font color="#FF0000">  *</font>
		    </td>
            <td style="text-align: right;"><label for="emailloginpwd1"><%=lu.getLanguage(language,"emailconfig.emailloginpwd.confirmation","密码确认") %></label></td>
		    <td><input value="${emailConfig.emailloginpwd }" name="emailloginpwd1" id="emailloginpwd1" size="22" type="password" class="Input02" style="width: 200px"/>
		    <font color="#FF0000">  *</font>
		    </td>
	      </tr>	      
	     <tr>
		    <td colspan="4" valign="middle" style="height:40px; text-align:center;">
			<input type="submit" id="btnSearch" value="<%=lu.getLanguage(language,"button.submit","确定") %>" class="sbutton" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" id="btnSearch" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" />
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
			$('#emailconfg').addClass("sel_tag");
			var siteurl = $('#siteurl').val();
			$('#form').submit(function(){
				var emailhost = $('#emailhost').val();
				var emailport = $('#emailport').val();
				var emailalias = $('#emailalias').val();
				var emailloginname = $('#emailloginname').val();
				var emailloginpwd = $('#emailloginpwd').val();	
				var emailloginpwd1 = $('#emailloginpwd1').val();	
			    var emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
			    var portRegex = /^(6553[0-5]|655[0-2]\d|65[0-4]\d\d|6[0-4]\d{3}|[1-5]\d{4}|[1-9]\d{0,3}|0)$/;
			    if (emailhost == "") {
		        	$('#l_msg').text(getMsg(ERR_NO_EMAIL_HOST));
					$('#emailhost').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
		        }
			    if (emailalias == "") {
		        	$('#l_msg').text(getMsg(ERR_NO_SENDER_ALIAS));
					$('#emailalias').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
		        }
			    if (emailport == "") {
		        	$('#l_msg').text(getMsg(ERR_NO_HOST_PORT));
					$('#emailport').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
		        }
			    if (emailloginname == "") {
		        	$('#l_msg').text(getMsg(ERR_NO_EMAIL_ACCOUNT));
					$('#emailloginname').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
		        }
			    if (emailloginpwd == "") {
		        	$('#l_msg').text(getMsg(ERR_NO_PASSWORD));
					$('#emailloginpwd').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
		        }
			    if(emailloginpwd!=emailloginpwd1){
					$('#l_msg').text(getMsg(REG_PASSWORD_UNPAIR));
					$('#emailloginpwd').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
		        if (emailport != '0'&&!emailport.match(portRegex)) {
		        	$('#l_msg').text(getMsg(ERR_PORT_ERROR));
					$('#emailport').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
		        }
			    if (emailloginname != ""&&!emailloginname.match(emailRegex)) {
		        	$('#l_msg').text(getMsg(ERR_EMAIL_ADDRESS_ERROR));
					$('#emailloginname').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
			    }
			    else {
			    	$("#l_msg").hide();
				    if("${top}"!="top"){
						$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
							if(rs==null||rs==""){
								alert(getMsg(ERR_CURRENT_USER_UNEXIST));
								location.href = siteurl+'/logout';
							}
							else if(rs==1 || rs==3){
								emailport = $('#emailport').val();
								$.post(siteurl+"/MeetingReservationAction.go", {method:'editemaiconfig',emailhost: emailhost, emailport: emailport,emailloginname: emailloginname,emailloginpwd:emailloginpwd ,emailalias:emailalias, _: new Date().getTime() } ,function(rs1) {
									if(rs1==0){
										alert(getMsg(OPERATION_SUCESS));
										location.href=siteurl+'/ConfPage.go?inc=EmailConfig';
									} 
									else if(rs1==-2){
										alert(getMsg(ERR_EMAIL_CONFIG_ERROR));
										return false;
									}else{
										alert(getMsg(EMAIL_CONFIG_SAVE_FAILURE));
										return false;
									}
								});	
							} else {
								alert(getMsg(ERR_NO_PERMISSION));
								location.href = siteurl+'/logout';
								return false;
							}
						});
					}
				    else{
				    	$.post(siteurl+"/MeetingReservationAction.go", {method:'editemaiconfig',top:"${top}",emailhost: emailhost, emailport: emailport,emailloginname: emailloginname,emailloginpwd:emailloginpwd ,emailalias:emailalias, _: new Date().getTime() } ,function(rs1) {
							if(rs1==0){
								alert(getMsg(OPERATION_SUCESS));
								location.href=siteurl+'/TopConfPage.go?inc=TopEmailConfig';
							} 
							else {
								alert(getMsg(OPERATION_FAILURE));
								return false;
							}
						});	
				    }
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