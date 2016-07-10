<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<div class="div_table_top" id="operUser">
	<input onclick="javascript:calluser('${confid }',0);" id="callUser" class="unclickbut" type="button" value="<%=lu.getLanguage(language,"phone.call","呼叫") %>"/>
	<input onclick="javascript:calluser('${confid }',1);" id="uncallUser" class="unclickbut" type="button" value="<%=lu.getLanguage(language,"phone.hangup","挂断") %>"/>
	<input onclick="javascript:muteuser('${confid }',0);" id="muteUser" class="unclickbut" type="button" value="<%=lu.getLanguage(language,"phone.mute","静音") %>"/>
	<input onclick="javascript:muteuser('${confid }',1);" id="unmuteUser" class="unclickbut" type="button" value="<%=lu.getLanguage(language,"phone.cancelmute","取消静音") %>"/>
	<input onclick="javascript:opensendmsg('${confid }');" id="sendmsgUser" class="unclickbut" type="button" value="<%=lu.getLanguage(language,"phone.msgnotice","短信通知") %>"/>
	<input onclick="javascript:goaddPhoneuser('${confid }');" id="addUser" class="sbutton" type="button" value="<%=lu.getLanguage(language,"button.add","添加") %>"/>
	<input onclick="javascript:useredit('${confid }');" id="editUser" class="unclickbut" disabled="disabled" type="button" value="<%=lu.getLanguage(language,"button.modify","修改") %>"/>
	<input onclick="javascript:phoneuserdel('${confid }');" id="delUser" class="unclickbut" type="button" value="<%=lu.getLanguage(language,"button.delete","删除") %>"/>
	<input onclick="javascript:openImportUser('${confid }');" id="importUser" class="sbutton" type="button" value="<%=lu.getLanguage(language,"phone.importuser","导入人员") %>"/>
</div>
<div id="userlistcontent" style="width:100%;float: left ">
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="tabnew" id='tabnew'>
	<thead>
		<tr><td colspan="7"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabtitle" id='tabtitle'>
		<tr>
		    <td width="5%" class="tdTitle"><input type="checkbox" id="usercheckall"/></td>
			<td width="20%" class="tdTitle"><%=lu.getLanguage(language,"phone.name","姓名") %></td>
			<td width="20%" class="tdTitle"><%=lu.getLanguage(language,"phone.phonenumber","电话号码") %></td>
		    <td width="15%" class="tdTitle"><%=lu.getLanguage(language,"phone.calltime","通话时长") %></td>
			<td width="15%" class="tdTitle"><%=lu.getLanguage(language,"phonelog.logintype","参会方式") %></td>
			<td width="13%" class="tdTitle"><%=lu.getLanguage(language,"phone.callstate","通话状态") %></td>
			<td width="12%" class="tdTitle"><%=lu.getLanguage(language,"phone.connectstate","连接状态") %></td>
		</tr>
		</table></td>
		<td class="tabtitle-td">&nbsp;&nbsp;</td>
		</tr> 
	</thead>
	<tbody >
		<tr><td colspan="8"><div style="max-height: 280px;overflow-y: auto;overflow-x:hidden;"><table width="90%" border="0" cellpadding="0" cellspacing="0"
	class="tabcontent" id='table_user' style="">
		<c:if test="${fn:length(list)==0}"><tr><td colspan="7" nuclick="1"><%=lu.getLanguage(language,"list.norecord","没有记录") %>!</td></tr></c:if>							
		<c:forEach var="user" items="${list}" varStatus="id">
		<tr id="${user.phone}" phone="${user.phone}" name="${user.name}">
		    <td width="5%"><input type="checkbox" value="${user.phone}" class="usercheck"/></td>
			<td width="20%" >${user.name}</td>
			<td width="20%" class="phone">${user.phone}</td>
		    <td width="15%" class="time">${user.time}<%=lu.getLanguage(language,"phone.second","秒") %></td>
			<td width="15%" class="joinflag">
				<c:choose>
	               <c:when test="${user.joinflag eq 0}"><%=lu.getLanguage(language,"phonelog.loginbynet","网络邀请") %></c:when>
	               <c:when test="${user.joinflag eq 1}"><%=lu.getLanguage(language,"phonelog.loginbyphone","电话加入") %></c:when>
	               <c:otherwise><%=lu.getLanguage(language,"phone.other","其他") %></c:otherwise>
	            </c:choose> 
			</td>
			<td width="13%" class="muteflag">
				<c:choose>
	               <c:when test="${user.muteflag eq 0}"><%=lu.getLanguage(language,"phone.normal","正常") %></c:when>
	               <c:otherwise><%=lu.getLanguage(language,"phone.mute","静音") %></c:otherwise>
	            </c:choose> 
			</td>
			<td width="12%" class="onlineflag">
				<c:choose>
	               <c:when test="${user.onlineflag eq 0}"><%=lu.getLanguage(language,"phone.noconnected","未连接") %></c:when>
	               <c:otherwise><%=lu.getLanguage(language,"phone.connected","已连接") %></c:otherwise>
	            </c:choose> 
			</td>
		</tr>
                  	</c:forEach>
		</table></div></td></tr>
	</tbody>
</table>
</div>
<script>
/**
 * 全选
 */
$("#usercheckall").click(function(){
	if($("#usercheckall").attr("checked")){
		$(".usercheck").attr("checked",true);
	}else{
		$(".usercheck").attr("checked",false);
	}
});
init_access();
</script>