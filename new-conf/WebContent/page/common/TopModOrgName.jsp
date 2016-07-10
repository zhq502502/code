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

String currentname = session.getAttribute("currentname").toString();
%>
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001"><%=lu.getLanguage(language,"topmodorgname.modorgname","修改企业名称") %></div>
			</div>
		<div id="uploadTip">
		   <p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
		</div> 	
		<table width="90%" border="0" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD" class="tamnew4">
		<form name="form" id="form" action="#" method="post" autocomplete='off'>
	      <tr>
	      	<td width="10%"></td>
            <td width="15%" style="text-align:right"><label for="alias"><%=lu.getLanguage(language,"topmodorgname.oldorgname","当前名称") %></label></td>
		    <td width="65%"><label id="current_name"><%=currentname %></label></td>
		    <td width="10%"></td>
	      </tr>
	      <tr>
	      	<td></td>
            <td style="text-align:right"><label for="alias"><%=lu.getLanguage(language,"topmodorgname.neworgname","新名称") %></label></td>
		    <td>
			    <input name="newname" id="newname" size="22" type="text" class="Input02_w" onblur="this.value=ignoreSpaces(this.value);"/>
		    </td>
		    <td></td>
	      </tr>
	     <tr>
		    <td colspan="4" valign="middle" style="height:40px; text-align:center;">
			<input type="submit" id="btnSearch" value="<%=lu.getLanguage(language,"button.modify","修改") %>" class="sbutton" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
			$('#org_editname').addClass("sel_tag");
			var siteurl = $('#siteurl').val();
			$('#form').submit(function(){
				var newname = $('#newname').val();		  	    
				if(newname==""){
					$('#l_msg').text(getMsg(ERR_NO_COMPANY_NAME));
					$('#newname').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				else if (newname.length > 32) {
					$('#l_msg').text(getMsg(ERR_COMPANY_NAME_LENGTH));
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					$('#newname').focus();
					return false;
				} 
				else {
					$("#l_msg").hide();
					$.post(siteurl+"/topuser/update", {action:'changeOrgName',newname: newname, _: new Date().getTime() } ,function(rs) {
						if(rs==0){
							alert(getMsg(MOD_SUCESS));
							location.href=siteurl+'/TopConfPage.go?inc=TopModOrgName';
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