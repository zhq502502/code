<%@ page language="java" pageEncoding="UTF-8"%>
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

String usertype = request.getAttribute("usertype").toString();
String userid = request.getAttribute("userid").toString();
String username = request.getAttribute("username").toString();
String email = request.getAttribute("email").toString();
String phone = request.getAttribute("phone").toString();
%>
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001"><%=lu.getLanguage(language,"topuserinfo.moduserinfo","修改个人信息") %></div>
			</div>			
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				bgcolor="#DDDDDD" class="tamnew">
	<form name="form" id="form" action="#" method="post" autocomplete='off'>
	<tr>
       <td width="80px" style="text-align:right"><label for="alias"><%=lu.getLanguage(language,"topuserinfo.nickname","用户昵称") %></label></td>
       <td  colspan="3">
			<input name="alias" id="alias" size="22" type="text" class="Input02_w" id="alias" value="<%=username %>" onblur="this.value=ignoreSpaces(this.value);"/>
			<input name="userid" id="userid" size="22" type="hidden" id="userid" value="<%=userid %>"/>
			<input name="usertype" id="usertype" size="22" type="hidden" id="usertype"  value="<%=usertype %>"/>
			<label class="error1"></label>
		</td>	
	</tr>
	<tr>
       <td style="text-align:right"><label for="email"><%=lu.getLanguage(language,"topuserinfo.email","邮箱") %></label></td>
       <td>
			<input name="email" id="email" size="22" type="text" class="Input02_w" id="email" value="<%=email %>"/>
			<label class="error1"></label>
		</td>
       <td style="text-align:right"><label for="phone"><%=lu.getLanguage(language,"topuserinfo.telephone","电话") %></label></td>
       <td>
			<input name="phone" id="phone" size="22" type="text" class="Input02_w" id="phone" value="<%=phone %>"/>
			<label class="error1"></label>
		</td>
	</tr>
	<tr>
		<td colspan="4" valign="middle" style="height:40px; text-align:center;">
			<input type="submit" id="btnSearch" value="<%=lu.getLanguage(language,"button.modify","修改") %>" class="sbutton" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <input type="button" id="reset_btn" onclick="reset()" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" />
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
			$('#conf_personalinfo').addClass("sel_tag");
			var siteurl = $('#siteurl').val();
			var aliasRegex = /^(?!_)(?!-)(?!.*?_$)(?!.*?-$)[a-zA-Z0-9-_\u4e00-\u9fa5]+$/;
			var emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
			var phoneRegex = /^\d[\d-]*\d$/; 
			$('#form').submit(function(){
				$alias = $('#alias');
				$userid = $('#userid');
				$phone = $('#phone');
				$email = $('#email');
				$usertype = $('#usertype');
				$newpassword = $('#newpassword');
				$newpassword2 = $('#newpassword2');				
				$newpassword.siblings('label').text('');
				if($alias.val()==""){
					$alias.siblings('label').text(getMsg(ERR_NO_USERALIAS));
					$alias.focus();
					$alias.siblings('label').fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				else if ($alias.val().length > 32) {
					$alias.siblings('label').text(getMsg(ERR_USERALIAS_LENGTH_ERROR));
					$alias.focus();
					$alias.siblings('label').fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				} 
				else if (!$alias.val().match(aliasRegex)) {
					$alias.siblings('label').text(getMsg(ERR_USERALIAS_STANDARD));
					$alias.focus();
					$alias.siblings('label').fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				} 
				else if ($email.val().length > 254) {
					$email.siblings('label').text(getMsg(ERR_EMAIL_LENGTH));
					$email.focus();
					$email.siblings('label').fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				else if ($email.val()!=null&&$email.val()!=""&&!$email.val().match(emailRegex)) {
					$email.siblings('label').text(getMsg(ERR_EMAIL_ERROR));
					$email.focus();
					$email.siblings('label').fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				else if($phone.val()!=null&&$phone.val()!=""&&!$phone.val().match(phoneRegex)){
					$phone.siblings('label').text(getMsg(ERR_PHONENUMBER_ERROR));
					$phone.focus();
					$phone.siblings('label').fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				else {
					$("#l_msg").hide();
					$.post(siteurl+"/topuser/update", { action:'vopupdate', userid: $userid.val(), alias: $alias.val(), phone: $phone.val(), email: $email.val(), usertype: $usertype.val(), newpassword: $newpassword.val(), _: new Date().getTime() } ,function(rs) {
						if(rs==0){
							alert(getMsg(MOD_SUCESS));
							location.href=siteurl+'/TopConfPage.go?inc=TopUserInfo';
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