<%
String siteurl = request.getSession().getAttribute("siteurl").toString();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<script type="text/javascript">
		$(document).ready(function() {
			$('#conf_loguser').addClass("sel_tag");
		});
	</script>
	<div id="wpbody-content">
		<div class="wrap">
			<div class="space_03">
				<div id="topLinkL">
					<div class="s_001"><%=lu.getLanguage(language,"log.user.detail","用户统计详情") %></div>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="tabnew" id='tabnew'>
					<thead>
						<tr><td colspan="5"><table width="100%" border="0" cellpadding="0" class="tabtitle" cellspacing="0" id='tabtitle'>
						<tr>
							<td width="10%" class="tdTitle"><%=lu.getLanguage(language,"log.conf.confroomid","会议室ID") %></td>
							<td width="20%" class="tdTitle"><%=lu.getLanguage(language,"log.conf.confroomname","会议室名称") %></td>
							<td width="25%" class="tdTitle"><%=lu.getLanguage(language,"log.conf.detail.logintime","进入会议室时间") %></td>
							<td width="25%" class="tdTitle"><%=lu.getLanguage(language,"log.conf.detail.logouttime","退出会议室时间") %></td>
							<td width="20%" class="tdTitle"><%=lu.getLanguage(language,"log.conf.detail.onlinetime","在线时长") %></td>
						</tr>
						</table></td></tr>
					</thead>
					<tbody>
						<tr><td colspan="5"><table width="90%" border="0" cellpadding="0" cellspacing="0"
					class="tabcontent" id='tabcontent'>
					   <c:if test="${fn:length(udList)==0}"><tr><td colspan="5"><%=lu.getLanguage(language,"list.norecord","没有记录") %></td></tr></c:if>	
					   <c:forEach var="mcudf" items="${udList}" varStatus="id">
                         <tr bgcolor="#FFFFFF" id="${mcudf.confid}">
							<td width="10%">${mcudf.confid}</td>
							<td width="20%">${mcudf.confname}</td>
							<td width="25%">${mcudf.logintime}</td>
							<td width="25%">${mcudf.logouttime}</td>
							<%
							if(language.equals("zh_tw")){
							%>
							<td width="20%">${fn:replace(fn:replace(fn:replace(mcudf.onlinetime, "分钟", "分鐘"),"秒","秒"),"小时","小時")}</td>
							<%
							}else if(language.equals("en")){
							%>
							<td width="20%">${fn:replace(fn:replace(fn:replace(mcudf.onlinetime, "分钟", "min"),"秒","s"),"小时","h")}</td>
							<%
							}else{
							%>
							<td width="20%">${mcudf.onlinetime}</td>
							<%
							}
							%>
						</tr>
						</c:forEach>
						</table></td></tr>
					</tbody>
				</table>
				<br />
               <div class="sabrosus">
                    <c:if test="${nowPage==1||pageNumber==1}">
                                <span class="disabled"> <<</span>
                                <span class="disabled"> <</span>
                    </c:if>
                    <c:if test="${nowPage>1}">
                                <a href="TopCountUserDetailAction.go?uid=${userid}&begintime=${begintime}&endtime=${endtime}&page=1"><<</a>
                                <a href="TopCountUserDetailAction.go?uid=${userid}&begintime=${begintime}&endtime=${endtime}&page=${nowPage-1}"><</a>
                    </c:if>

                    <%
                                //总页数
                                String str = request.getAttribute("pageNumber").toString();
                                int pageNumber = Integer.parseInt(str);

                                //当前页
                                String now = request.getAttribute("nowPage").toString();
                                int nowNumber = Integer.parseInt(now);

                                if(nowNumber <= 6 || pageNumber <= 10){
                                	for(int i=1;i<=10 && i<= pageNumber;i++){
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{%>
                                                <a href="TopCountUserDetailAction.go?uid=${userid}&begintime=${begintime}&endtime=${endtime}&page=<%=i%>"><%=i %></a>
                                     		<% }
                                		}

                                }
                                
                                else if((pageNumber-nowNumber)<5){
                                	for(int i=(pageNumber-9);i<= pageNumber;i++){
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{%>
                                                <a href="TopCountUserDetailAction.go?uid=${userid}&begintime=${begintime}&endtime=${endtime}&page=<%=i%>"><%=i %></a>
                                     		<% }
                                		}
                                }
                                
                                else{
                                	for(int i=(nowNumber-5);i<(nowNumber+5) && i<= pageNumber;i++){
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{%>
                                                <a href="TopCountUserDetailAction.go?uid=${userid}&begintime=${begintime}&endtime=${endtime}&page=<%=i%>"><%=i %></a>
                                     		<% }
                                		}                                	
                                }
         

%>
                    <c:if test="${nowPage!=pageNumber}">
                                <a href="TopCountUserDetailAction.go?uid=${userid}&begintime=${begintime}&endtime=${endtime}&page=${nowPage+1}">></a>
                                <a href="TopCountUserDetailAction.go?uid=${userid}&begintime=${begintime}&endtime=${endtime}&page=${pageNumber}">>></a>
                                ${nowPage}/${pageNumber} 
                    </c:if>
                    
                    <c:if test="${nowPage==pageNumber}">
                                <span class="disabled"> ></span>
                                <span class="disabled"> >></span>
                                ${pageNumber}/${pageNumber}
                    </c:if>
                </div>
				<div align="center">
					<a id="btndivSubmit" class="divbutton" href="<%=siteurl %>/ConfExportServlet?btime=${begintime}&etime=${endtime}&id=${userid}&t=2&c=detail&language=<%=language %>"><span><%=lu.getLanguage(language,"button.export","导出") %></span></a>
				</div>

			</div>
		</div>
	</div>