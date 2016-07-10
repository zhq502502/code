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
				<div class="s_001"><%=lu.getLanguage(language,"modpass.modpass","修改密码") %></div>
			</div>
		<div id="uploadTip">
		   <p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
		</div> 				
		<table border="0" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD" class="tamnew">
		<form name="form" id="form" action="#" method="post" autocomplete='off'>
	      <tr>
	      	<td width="10%"></td>
            <td width="20%" style="text-align: right;"><label for="opass"><%=lu.getLanguage(language,"modpass.oldpass","旧密码") %></label></td>
		    <td width="60%"><input name="opass" id="opass" size="22" type="password" class="Input02_w"/></td>
		    <input name="account" id="account" size="22" type="hidden" id="account" value="${userid }" />
		    <input name="oldpass" id="oldpass" size="22" type="hidden" id="oldpass" value="${userpass}"/>
		    <td width="10%"></td>
	      </tr>
	      <tr>
	      	<td></td>
            <td style="text-align: right;"><label for="npass"><%=lu.getLanguage(language,"modpass.newpass","新密码") %></label></td>
		    <td><input name="npass" id="npass" size="22" type="password" class="Input02_w" onblur="this.value=ignoreSpaces(this.value);"/></td>
		    <td></td>
	      </tr>
	      <tr>
	      	<td></td>
            <td style="text-align: right;"><label for="npass1"><%=lu.getLanguage(language,"modpass.newpass.confirmation","新密码确认") %></label></td>
		    <td><input name="npass1" id="npass1" size="22" type="password" class="Input02_w" onblur="this.value=ignoreSpaces(this.value);"/></td>
		    <td></td>
	      </tr>
	     <tr>
		    <td colspan="4" valign="middle" style="height:40px; text-align:center;">
			<input type="submit" id="btnSearch" value="<%=lu.getLanguage(language,"button.modify","修改") %>" class="sbutton" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" id="btnSearch" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" /></td>
	   </tr>
		</form>	
			</table>	
			
		</div>
	</div>
</div>
<script type="text/javascript" src="js/stringRes.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#user_modpass').addClass("sel_tag");
			var siteurl = $('#siteurl').val();
			$("#opass,#npass, #npass1").bind('contextmenu', function(evt) { return false; });
			$("#opass,#npass, #npass1").bind('selectstart', function(evt) { return false; });
			//$("#opass,#npass, #npass1").bind('keydown', 'Ctrl+a',function (evt){return false; });
			//$("#opass,#npass, #npass1").bind('keydown', 'Ctrl+c',function (evt){return false; });
			//$("#opass,#npass, #npass1").bind('keydown', 'Ctrl+v',function (evt){return false; }); 
			$('#form').submit(function(){
				var account = $('#account').val();
				var opass = $('#opass').val();
				var npass = $('#npass').val();	
				var npass1 = $('#npass1').val();
				var oldpass = $('#oldpass').val();			    	    
				if(opass!=oldpass){
					$('#l_msg').text(getMsg(ERR_OLD_PASS_ERROR));
					$('#opass').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				else if(npass!=npass1){
					$('#l_msg').text(getMsg(ERR_NEW_PASS_UNPAIR));
					$('#npass').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				else if(opass==npass){
					$('#l_msg').text(getMsg(ERR_PASS_SAME));
					$('#npass').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				if(npass!=null&&npass.length>12){
					jQuery('#l_msg').text(getMsg(ERR_PASS_LENGTH));
					jQuery('#npass').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				else {
					$("#l_msg").hide();
					$.post(siteurl+"/user/update", {action:'changepass',npass: npass, account: account, _: new Date().getTime() } ,function(rs) {
						if(rs==0){
							alert(getMsg(MOD_SUCESS));
							location.href=siteurl+"/ConfPage.go?inc=ModPass";
						} 
						else {
							alert(getMsg(MOD_FAILURE));
							return false;
						}
					});
					return false;
				}
				return false;
			});	
		});
		
		function ignoreSpaces(string) {
			var temp = "";
			string = '' + string;
			splitstring = string.split(" ");
			for(i = 0; i < splitstring.length; i++)
			temp += splitstring[i];
			return temp;
		} 
	</script>