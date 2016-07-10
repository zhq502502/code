<%@ page language="java" pageEncoding="UTF-8"%>
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
%> 
	<div id="wpbody-content">
		<div class="wrap">
			<div class="space_03">
				<div id="topLinkL">
					<div class="s_001"><%=lu.getLanguage(language,"download.software.down","软件下载") %></div>
				</div>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabnew" id="tabnew">
					  <thead>
						<tr><td colspan="5"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabtitle" id='tabtitle'>
					    <tr>
					      	<td width="20%" class="tdTitle"><%=lu.getLanguage(language,"download.software.system","应用平台") %></td>
					      	<td width="20%" class="tdTitle"><%=lu.getLanguage(language,"download.software.language","软件语言") %></td>
					      	<td width="20%" class="tdTitle"><%=lu.getLanguage(language,"download.software.size","软件大小") %></td>
					       	<td width="20%" class="tdTitle"><%=lu.getLanguage(language,"download.software.releasetime","发布时间") %></td>
					       	<td width="20%" class="tdTitle"><%=lu.getLanguage(language,"download.software.down","软件下载") %></td>
					    </tr>
						</table></td></tr>
					  </thead>
					  <tbody>
					  	<tr><td colspan="5"><table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="tabcontent" id='tabcontent'>
					     <c:forEach items="${downloadList}" var="list">
					    <tr>
					      	<td width="20%">
					      	<%
					      		if(language.equals("zh_tw")){
							%>
								${list.name_zh_tw}
							<%
								}else if(language.equals("en")){
							%>
								${list.name_en}
							<%
								}else{
							%>
								${list.name}
							<%
								}
					      	%>
					      	</td>
					      	<td width="20%">
					      	<%
					      		if(language.equals("zh_tw")){
							%>
								${list.language_zh_tw}
							<%
								}else if(language.equals("en")){
							%>
								${list.language_en}
							<%
								}else{
							%>
								${list.language}
							<%
								}
					      	%>
					      	</td>
						    <td width="20%">${list.size}</td>
						    <td width="20%">${list.time}</td>
					      <td width="20%"><img src='images_gb/download.gif' width='16' height='16' class='img_tab' /><a class="download" href="${list.url}" target="_blank"><%=lu.getLanguage(language,"download.button.down","下载") %></a></td>
					    </tr>
					    </c:forEach>
						</table></td></tr>
					  </tbody>
					</table>		
			</div>		

		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#conf_download').addClass("sel_tag");
			completeDownload();
		});
		function completeDownload(){
			$(".download").each(function(){
				var url = $(this).attr("href");
				if(!(url.indexOf("http://")==0)){
					 $(this).attr("href",$("#siteurl").val()+$(this).attr("href"));
				}
			});
		}
	</script>