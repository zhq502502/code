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
<div class="div_table_top" id="operVcode">
	<input onclick="javascript:goaddVcode('${confid }');" id="addVcode" class="sbutton" type="button" value="<%=lu.getLanguage(language,"button.add","添加") %>"/>
	<input onclick="javascript:disablevcode('${confid }');" id="disableVcode" class="sbutton" type="button" value="<%=lu.getLanguage(language,"phone.disable","禁用") %>"/>
	<input onclick="javascript:delvcode('${confid }');" id="delVcode" class="sbutton" type="button" value="<%=lu.getLanguage(language,"button.delete","删除") %>"/>
</div>
<div style="width:100%;float: left ">
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="tabnew" id='tabnew'>
	<thead>
		<tr><td colspan="5"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabtitle" id='tabtitle'>
		<tr>
		    <td width="5%" class="tdTitle"><input type="checkbox" id="vcodecheckall"/></td>
			<td width="25%" class="tdTitle"><%=lu.getLanguage(language,"phone.verifycode","验证码") %></td>
			<td width="30%" class="tdTitle"><%=lu.getLanguage(language,"phone.btime","开始时间") %></td>
			<td width="30%" class="tdTitle"><%=lu.getLanguage(language,"phone.etime","结束时间") %></td>
			<td width="13%" class="tdTitle"><%=lu.getLanguage(language,"phone.state","状态") %></td>
		</tr>
		</table></td>
		<td class="tabtitle-td">&nbsp;</td>
		</tr>
	</thead>
	<tbody>
		<tr><td colspan="6"><div style="max-height: 280px;overflow-y: auto;overflow-x:hidden;"><table width="90%" border="0" cellpadding="0" cellspacing="0"
	class="tabcontent" id='table_vcode'>
		<c:if test="${fn:length(list )==0}"><tr><td colspan="5"><%=lu.getLanguage(language,"list.norecord","没有记录") %>!</td></tr></c:if>							
		<c:forEach var="code" items="${list }" varStatus="id">
		<tr id="${code.id}" status="${code.status}" vcode="${code.code}">
		    <td width="5%" class=""><input type="checkbox"  value="${code.id}" class="vcodecheck"/></td>
			<td width="25%" class="">${code.code}</td>
			<td width="30%" class="">${code.begintime}</td>
			<td width="30%" class="">${code.endtime}</td>
			<td width="13%" class="">
			<c:if test="${code.status eq 1}"><%=lu.getLanguage(language,"phone.normal","正常") %></c:if><c:if test="${code.status eq 2}"><%=lu.getLanguage(language,"phone.disable","禁用") %></c:if>
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
$("#vcodecheckall").click(function(){
	if($("#vcodecheckall").attr("checked")){
		$(".vcodecheck").attr("checked",true);
	}else{
		$(".vcodecheck").attr("checked",false);
	}
});
init_access();
</script>