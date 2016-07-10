<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="seegle"  uri="/WEB-INF/taglib/seegle.tld" %>
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
				<div class="s_001"><%=lu.getLanguage(language,"phone.operuserlist","操作员列表") %></div>
			</div>			
			<table  width="100%" border="0" cellpadding="0" cellspacing="0" class="tabnew" id='tabnew'>
				<tr class='tabtitle'>
					<td width="5%"></td>
					<td width="20%"><%=lu.getLanguage(language,"confsetmanager.account","账号") %></td>
					<td width="20%"><%=lu.getLanguage(language,"useradd.nickname","昵称") %></td>
					<td width="20%"><%=lu.getLanguage(language,"topuserinfo.telephone","电话") %></td>
					<td width="20%"><%=lu.getLanguage(language,"topuserinfo.email","邮箱") %></td>
					<td width="15%"><%=lu.getLanguage(language,"operation","操作") %></td>
				</tr>	
				<tbody class="tabcontent" >
				<c:forEach items="${list}" var="user" >
					<tr>
						<td><input type="checkbox" class="checkchild" value="${user.account }"/></td>
						<td>${user.account }</td>
						<td>${user.alias }</td>
						<td>${user.phone }</td>
						<td>${user.email }</td>
						<td>
						<img src='images_gb/edt.gif' width='16' height='16' class='img_tab' /><a href="javascript:void(0)" onclick="edit(${user.account })"><%=lu.getLanguage(language,"confmanage.edit","编辑") %></a>
						<img src='images_gb/del.gif' width='16' height='16' class='img_tab' /><a href="javascript:void(0)" onclick="del(${user.account })"><%=lu.getLanguage(language,"button.delete","删除") %></a>
						</td>
					</tr>	
				</c:forEach>
				</tbody>
			</table>
			<div class="div_table_top" id="operPhone">
				<input id="usercheckall" type="checkbox">
				<span style="color:#717171;"><%=lu.getLanguage(language,"phone.selectall","全选") %></span>
				<input class="sbutton" value="<%=lu.getLanguage(language,"phone.multidel","批量上传") %>" id="delall"  onclick="delall()" type="button" />
				<input class="sbutton" value="<%=lu.getLanguage(language,"phone.add","新建") %>" id="addPhone"  onclick="openadd(0)" type="button" />
			</div>	
			<seegle:paging action="TopOperuserAction.go?method=list"/>
		</div>
	</div>
</div>

<div id='dialog_box' style='display: none;height: 300px; outline: 0px none; width: 450px; overflow: visible;background: url("images_gb/table_bg.jpg") repeat-x'>
<div class='message'>
	<div style="width: 400px;height: 200px; ">
	<p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000;margin-top: 0">error</p>
	<table id="formdata" width="93%" cellspacing="0" cellpadding="5" border="0" align="center" style=" table-layout:fixed" >
		
		<tr>
			<td width="20%" align="right"> <%=lu.getLanguage(language,"confsetmanager.account","账号") %></td><td width="80%"> 
			<input class="" name="type" value="0" type="hidden"/>
			<input class="" name="param" value="" type="hidden"/>
			<input class="" id="update" value="" type="hidden"/>
			<input class="Input02" name="account"  style="width: 200px" id="account" /><font color="#FF0000">  *</font>输入后回车
			</td>
		</tr>
		<tr>
			<td align="right"> <%=lu.getLanguage(language,"useradd.nickname","昵称") %></td><td> <input class="Input02" name="alias" style="width: 200px"/><font color="#FF0000">  *</font></td>
		</tr>
		<tr>
			<td align="right"> <%=lu.getLanguage(language,"topuserinfo.telephone","电话") %></td><td> <input class="Input02" name="phone" style="width: 200px"/><font color="#FF0000">  *</font></td>
		</tr>
		<tr>
			<td align="right"> <%=lu.getLanguage(language,"topuserinfo.email","邮箱") %></td><td> <input class="Input02" name="email" style="width: 200px"/><font color="#FF0000">  *</font></td>
		</tr>
	</table>
	</div>
	<div align="center">
	<input type="button" value="<%=lu.getLanguage(language,"button.submit","确定") %>" id="submit" class="sbutton" onclick="save()" />
	<input type="button" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" onclick="closeDlog()" />
	</div>
</div>
</div> 

<script type="text/javascript" src="js/stringRes.js"></script>
<script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
<script src="js/date/WdatePicker.js"></script>
<script type="text/javascript">
/*选择用户*/
function openadd(){
	$("#l_msg").hide();
	$("#update").val(0);
	$("#formdata input[name='param']").val("");
	$("#formdata input[name='phone']").val("");
	$("#formdata input[name='alias']").val("");
	$("#formdata input[name='email']").val("");
	$("#formdata input[name='account']").val("");
	$("#formdata input[name='account']").attr("disabled",false);
	$("#formdata input[name='phone']").attr("disabled",true);
	$("#formdata input[name='alias']").attr("disabled",true);
	$("#formdata input[name='email']").attr("disabled",true);
	$("#submit").attr("disabled",true);
	$("#submit").addClass("unclickbut");
	$('#dialog_box').dialog({
		modal:true,
		position:"20%",
		width:455,
		height:300,
		title:"<span style='color:#FFFFFF;'>"+getMsg(OPERUSER_TITLE)+"</span>"
		
	});
	$("#ui-dialog-title-dialog_box").css({"height":"25px","float":"left","padding":"6px 0 0 6px"});
	$(".ui-dialog-titlebar-close").css("float","right");
	$(".ui-dialog-titlebar-close").css({"width":"16px","height":"16px","margin":"6px 6px 0 0"});
	$(".ui-dialog-titlebar").css("background-image","");
	$(".ui-dialog-titlebar").css("background-color","#348CD4");
	$("#dialog_box").css("border","0");
};
//关闭弹层
$('#dialog_box').dialog("close");
/*关闭弹出框*/
function closeDlog(){
	$('#dialog_box').dialog("close");
}
/**
 * 保存操作员
 */
function save(){
	var url = "${siteurl}/TopOperuserAction.go?method=save&";
	if($("#update").val()==1){
		url = "${siteurl}/TopOperuserAction.go?method=update&";
	}
	$("#formdata input[name]").each(function(i,n){
		url+="&"+$(this).attr("name")+"="+encodeURIComponent($(this).val());
	})
	
	var account = $("#formdata input[name='account']").val();
	var phone = $("#formdata input[name='phone']").val();
	var alias = $("#formdata input[name='alias']").val();
	var email = $("#formdata input[name='email']").val();
	var emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
	var accountRegex = /^[a-zA-Z0-9\u0391-\uFFE5]+(([\-\_ ][a-zA-Z0-9\u0391-\uFFE5 ])?[a-zA-Z0-9\u0391-\uFFE5]*)*$/;
	var phoneRegex = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
	if(!account.match(accountRegex)){
		$('#l_msg').text(getMsg(ERR_OPERUSER_ACCOUNT));
		$("#formdata input[name='account']").focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
        return false;
	}else if(!alias.match(accountRegex)){
		$('#l_msg').text(getMsg(ERR_OPERUSER_ALIAS));
		$("#formdata input[name='alias']").focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
        return false;
	}else if(!phone.match(phoneRegex)){
		$('#l_msg').text(getMsg(ERR_OPERUSER_PHONE));
		$("#formdata input[name='phone']").focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
        return false;
	}else if(!email.match(emailRegex)){
		$('#l_msg').text(getMsg(ERR_EMAIL_ADDRESS_ERROR));
		$("#formdata input[name='email']").focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
        return false;
	}
	
	var url1 = "${siteurl}/TopOperuserAction.go?method=search&orgid=90000&account="+$("#account").val();
	$.getJSON(url1, { _: new Date().getTime() } ,function(rs1) {
		if(rs1.userid>0){
			$.post(url, { _: new Date().getTime() } ,function(rs) {
				alert(errorMap.get(rs+""));
				if(rs==0){
					closeDlog();
					location.href='${siteurl}/TopConfPage.go?inc=TopOperuser';
				}
				$("#submit").attr("disabled",false);
				$("#submit").removeClass("unclickbut");
			});
		}else{
			alert(getMsg(ERR_OPERUSER_ACCOUNT));
		}
	});
	$("#submit").attr("disabled",true);
	$("#submit").addClass("unclickbut");
	
}
function update(){
	
}
/**
 * 删除操作员
 */
function del(accounts){
	if(!confirm(getMsg(CONFIRM_DELETE))){
		return ;
	}
	var url = "${siteurl}/TopOperuserAction.go?method=del&orgid=90000&accounts="+accounts;
	$.post(url, { _: new Date().getTime() } ,function(rs) {
		alert(errorMap.get(rs+""));
		if(rs==0){
			location.href='${siteurl}/TopConfPage.go?inc=TopOperuser';
		}
	});
}
/**
 * 编辑操作员
 */
function edit(account){
	var url = "${siteurl}/TopOperuserAction.go?method=get&orgid=90000&account="+account;
	$.getJSON(url, { _: new Date().getTime() } ,function(rs) {
		openadd();
		$("#update").val(1);
		$("#formdata input[name='param']").val(rs.param);
		$("#formdata input[name='phone']").val(rs.phone);
		$("#formdata input[name='alias']").val(rs.alias);
		$("#formdata input[name='email']").val(rs.email);
		$("#formdata input[name='account']").val(rs.account);
		$("#formdata input[name='phone']").attr("disabled",false);
		$("#formdata input[name='alias']").attr("disabled",false);
		$("#formdata input[name='email']").attr("disabled",false);
		$("#formdata input[name='account']").attr("disabled",true);
		$("#submit").attr("disabled",false);
		$("#submit").removeClass("unclickbut");
		
	});
}
/**
 * 查询协同用户是否存在
 */
function searchuser(){
	var account = $("#account").val();
	var url = "${siteurl}/TopOperuserAction.go?method=search&orgid=90000&account="+account;
	$.getJSON(url, { _: new Date().getTime() } ,function(rs) {
		if(rs.userid>0){
			$("#formdata input[name='phone']").attr("disabled",false);
			$("#formdata input[name='alias']").attr("disabled",false);
			$("#formdata input[name='email']").attr("disabled",false);
			$("#submit").attr("disabled",false);
			$("#submit").removeClass("unclickbut");
		}else{
			alert(getMsg(ERR_OPERUSER_ACCOUNT));
			$("#formdata input[name='phone']").attr("disabled",true);
			$("#formdata input[name='alias']").attr("disabled",true);
			$("#formdata input[name='email']").attr("disabled",true);
			$("#submit").attr("disabled",true);
			$("#submit").addClass("unclickbut");
		}
	});
}
/**
 * 帐号输入框回车
 */
$("#account").keydown(function(e){
	var curKey = e.which;
	if(curKey == 13){
		searchuser();
	}
})
$("#usercheckall").bind("click",function(){
	if($(this).attr("checked")){
		$(".checkchild").attr("checked",true);
	}else{
		$(".checkchild").attr("checked",false);
	}
})
/**
 * 删除全部
 */
function delall(){
	if($(".checkchild:checked").length==0){
		alert(getMsg(ERR_NO_USER_SELECTED));
		return ;
	}
	var accounts = "";
	$(".checkchild:checked").each(function(){
		accounts+=$(this).val()+",";
	})
	accounts = accounts.substring(0,accounts.length-1);
	del(accounts);
}
</script>