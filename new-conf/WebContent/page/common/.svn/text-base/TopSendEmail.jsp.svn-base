<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
%>
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001"><%=lu.getLanguage(language,"sendemail.confreserve","会议预约") %></div>
			</div>
		<div id="uploadTip">
		   <p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
		</div>	
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
				bgcolor="#DDDDDD" class="tamnew">
	<tr>
       <td width="15%"><label for="users"><%=lu.getLanguage(language,"topsendemail.recipients","收件人") %></label></td>
       <td width="85%">
			<input id="users1" type="text" class="Input02" style="width: 430px" onfocus="usersfocus(this)" onblur="usersblur(this)"/>
			<font color="#FF0000">  *</font>&nbsp;
			<a href="javascript:clearUsers()"><%=lu.getLanguage(language,"sendemail.clear","清除") %></a>
		</td>
	</tr>
	<tr>
       <td><label for="title"><%=lu.getLanguage(language,"sendemail.emailtitle","邮件标题") %></label></td>
       <td>
			<input name="title" id="title" size="22" type="text" style="width: 430px" class="Input02" value=""/>
			<font color="#FF0000">  *</font>
		</td>
	</tr>
	<tr>
       <td><label for="content"><%=lu.getLanguage(language,"sendemail.emailcontent","内容") %></label></td>
       <td>
			<textarea rows="10" cols="56" name="content" id="content" style="width: 423px">
			
			</textarea>
			<font color="#FF0000">  *</font>
		</td>
	</tr>			
	<tr>
		<td colspan="2" valign="middle" style="height:40px; text-align:center;">
			<form name="form" id="form1" action="#" method="post">
			<input type="button" onclick="btnSearchClick()" id="btnSearch" value="<%=lu.getLanguage(language,"button.send","发送") %>" class="sbutton" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <input type="button" id="reset_btn" onclick="TopconfList()" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" />
		    </form>		
		</td>
	</tr>			
		
			</table>			
		</div>
	</div>
</div>
<style>
#leftUl li,#rightUl li{
	float: left;
	width:100%;
	border-bottom: 1px solid #cccccc;
	cursor: pointer;
	color: black;
}
</style>
<script type="text/javascript" src="js/stringRes.js"></script>
<script src="js/date/WdatePicker.js"></script>
	<script type="text/javascript">
		var siteurl = $('#siteurl').val();
		$(document).ready(function() {
			$('#conf_list').addClass("sel_tag");
			$('#content').val(getMsg(CONF_URL)+siteurl+"/ConfLogin.jsp?orgid=${orgid}&confid=${confid}");
			/*人员选择宽鼠标悬浮样式*/
			$("#leftUl li,#rightUl li").live("mouseover",function (ev) {
				$(this).css("background-color","#cccccc");
				
			});
			$("#leftUl li,#rightUl li").live("mousemove",function (ev) {
				$(this).css("background-color","#cccccc");
				$("#emailalt").html($(this).children("span").attr("id"));
				$("#emailalt").show();
				$("#emailalt").css({left:ev.clientX+15+$(document).scrollLeft()});
				$("#emailalt").css({top:ev.clientY+15+$(document).scrollTop()});
				
			});
			$("#leftUl li,#rightUl li").live("mouseout",function () {
				$(this).css("background-color","");
				$("#emailalt").hide();
			});
			$("#users1").blur();
		});
		/*点击提交*/
		//var emailmes="收件人邮箱以逗号隔开，如：zhang@seegle.com,gong@seegle.com";
		var emailmes=getMsg(ADDRESSEE_EXAMPLE);
		function btnSearchClick(){
			var users = $('#users1').val();
			var title = $('#title').val();
			var content = $('#content').val();
			if(users==emailmes){
				users="";
			}
			if(users==""||users==emailmes){
				$('#l_msg').text(getMsg(ERR_NO_ADDRESSEE));
				$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
				return false;
			}
			var usersarr = users.split(",");
			var emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
			for(var i=0;i<usersarr.length;i++){
				if (usersarr[i] != ""&&!usersarr[i].match(emailRegex)) {
		        	$('#l_msg').text(getMsg(ERR_EMAIL_ADDRESS_ERROR));
					$('#users1').focus();
					$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		            return false;
			    }
			}
			if(title==""){
				$('#l_msg').text(getMsg(ERR_NO_EMAIL_TITLE));
				$('#title').focus();
				$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
				return false;
			}
			if(content==""){
				$('#l_msg').text(getMsg(ERR_NO_EMAIL_CONTENT));
				$('#content').focus();
				$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
				return false;
			}
			$("#l_msg").hide();
			$.get(siteurl+"/MeetingReservationAction.go", {method:"sendemail",emails: users, title: title, content: content,emailtype: 1, _: new Date().getTime() } ,function(rs1) {
				if(rs1==0){
					alert(getMsg(EMAIL_SEND_SUCESS));
					location.href=siteurl+'/TopConfPage.go?inc=TopConfList';
				} 
				else {
					alert(getMsg(EMAIL_SEND_FAILURE));
					return false;
				}
			});
		};	
		/*清除用户选择框*/
		function clearUsers(){
			$("#users1").val("");
			$("#users1").blur();
		}
		/*选择用户*/
		function selectUsers(){
			$("#leftUl").empty();
			$("#rightUl").empty();	
			$.getJSON(siteurl+"/MeetingReservationAction.go", {method:"getemails",top:"${top}", _: new Date().getTime() } ,function(rs1) {
				var json = rs1;
				for(var i = 0;i<json.length;i++){
					var span_html = '<span id="'+json[i].email+'" name="'+json[i].username+'" class="m_text">'+json[i].username+'['+json[i].alias+']'+'</span>';
					var img_html = '<img class="m_img" align="absmiddle" src="'+siteurl+'/images_gb/bot_add.gif">';
					var li_html = "<li onclick='leftClick(this)'>"+span_html+img_html+"</li>";
					$("#leftUl").append(li_html);
				}
			});
		}
		/*人员选择框左边点击*/
		function leftClick(th){
			var img = '<img class="m_img" align="absmiddle" src="'+siteurl+'/images_gb/bot_mov.gif">';
			$(th).children("img").remove();
			$(th).append(img);
			var html = "<li onclick='rightClick(this)'>"+$(th).html()+"</li>";
			$(th).remove();
			$("#rightUl").append(html);
			$("#emailalt").hide();
		}
		/*人员选择框右边点击*/
		function rightClick(th){
			var img = '<img class="m_img" align="absmiddle" src="'+siteurl+'/images_gb/bot_add.gif">';
			$(th).children("img").remove();
			$(th).append(img);
			var html = "<li onclick='leftClick(this)'>"+$(th).html()+"</li>";
			$(th).remove();
			$("#leftUl").append(html);
			$("#emailalt").hide();
		}
		/*确定*/
		function okselect(){
			var rightLi = $("#rightUl span");
			var emails="";
			var names="";
			rightLi.each(function(i,n){
				var email = $(this).attr("id");
				var name = $(this).text();
				var align = $(this).attr("name");
				//alert(i+"--"+name+email);
				names+=","+name;
				emails+=","+email;
			})
			names = names.substring(1,names.length)
			emails = emails.substring(1,emails.length)
			$("#users").val("");
			$("#users1").val("");
			$("#users").val(names);
			$("#users1").val(emails);
			closeDlog();
		}
		/*关闭弹出框*/
		function closeDlog(){
			$.modal.close();
		}
		
		/*全部添加*/
		function addall(){
			$("#leftUl img").attr("src",siteurl+"/images_gb/bot_mov.gif");
			$("#leftUl li").attr("onclick","rightClick(this)");
			var html = $("#leftUl").html();
			$("#rightUl").append(html);
			$("#leftUl").html("");
		}
		
		/*全部删除*/
		function delall(){
			$("#rightUl img").attr("src",siteurl+"/images_gb/bot_add.gif");
			$("#rightUl li").attr("onclick","leftClick(this)");
			var html = $("#rightUl").html();
			$("#leftUl").append(html);
			$("#rightUl").html("");
			
		}
		/*预约人地址*/
		function usersfocus(th){
			if($(th).val()==emailmes||$(th).val()==""){
				$(th).val("");
				$(th).css("color","");
			}
		}
		/*预约人地址*/
		function usersblur(th){
			if($(th).val()==""||$(th).val()==emailmes){
				$(th).css("color","#bbbbbb");
				$(th).val(emailmes);
			}
		}
		function TopconfList(){
			location.href=siteurl+'/TopConfPage.go?inc=TopConfList';
		}
	</script>