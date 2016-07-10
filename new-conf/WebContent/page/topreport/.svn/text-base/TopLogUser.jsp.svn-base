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
String datelang="";
datelang=language.replace('_', '-');
%>
<script src="js/date/WdatePicker.js"></script>
<script>
	$(document).ready(function() {
		$('#conf_loguser').addClass("sel_tag");
		$("#begintime").keydown(function(event){
	    	if(event.keyCode==13){
	    		selectUserLog();
	        	return false;
	    	}
	    });
		$("#endtime").keydown(function(event){
	    	if(event.keyCode==13){
	    		selectUserLog();
	        	return false;
	    	}
	    });
	});
    function selectUserLog(){
        var btime = document.getElementById("begintime").value;
        var etime = document.getElementById("endtime").value;
        document.forms[0].action = "TopMeetingCountUserAction.go?begintime="+btime+"&endtime="+etime;
        document.forms[0].submit();
    }         
</script>
	<div id="wpbody-content">
		<div class="wrap">
			<div class="space_03">
				<div id="topLinkL">
					<div class="s_001"><%=lu.getLanguage(language,"confpage.menu.userlog","用户统计") %></div>

					<div class="s_002">
						<form id="form" method="post" autocomplete='off'>
							<label for="begintime"><%=lu.getLanguage(language,"confadd.btime","开始时间") %>:</label><input type="text" name="begintime" id="begintime" size="20" value='${begintime}' readonly class='Wdate Input_2' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'endtime\',{y:-1})}',maxDate:'#F{$dp.$D(\'endtime\')}',isShowClear:false,readOnly:true,lang:'<%=datelang%>'})" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<label for="endtime"><%=lu.getLanguage(language,"confadd.etime","结束时间") %>:</label><input type="text" name="endtime" id="endtime" size="20" value='${endtime }' readonly class='Wdate Input_2' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'begintime\')}',maxDate:'%y-%M-%d %H:%m:%s',isShowClear:false,readOnly:true,lang:'<%=datelang%>'})" />
							<input type="button" value="" class="searchbutton-log" onClick="selectUserLog()" />
						</form>
					</div>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabnew" id="tabnew">
					<thead>
						<tr><td colspan="7"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabtitle" id='tabtitle'>
						<tr>
							<td width="12%" class="tdTitle"><%=lu.getLanguage(language,"login.html.userid","用户账号") %></td>
							<td width="12%" class="tdTitle"><%=lu.getLanguage(language,"log.conf.detail.username","用户名称") %></td>
							<td width="12%" class="tdTitle"><%=lu.getLanguage(language,"log.user.attendtotaltimes","参会总次数") %></td>
							<td width="16%" class="tdTitle"><%=lu.getLanguage(language,"log.user.attendtotaltime","参会总时长") %></td>
							<td width="16%" class="tdTitle"><%=lu.getLanguage(language,"log.user.averageonlinetime","平均参会时长") %></td>
							<td width="24%" class="tdTitle"><%=lu.getLanguage(language,"log.user.lastlogintime","最后登录时间") %></td>
							<td width="8%" class="tdTitle"><%=lu.getLanguage(language,"operation","操作") %></td>
						</tr>
						</table></td></tr>
					</thead>
					<tbody>
						<tr><td colspan="7"><table width="90%" border="0" cellpadding="0" cellspacing="0"
					class="tabcontent" id='tabcontent'>
					   <c:if test="${fn:length(cuList)==0}"><tr><td colspan="5"><%=lu.getLanguage(language,"list.norecord","没有记录") %></td></tr></c:if>	
					   <c:forEach var="mcuf" items="${cuList}" varStatus="id">
                         <tr bgcolor="#FFFFFF" id="${mcuf.userid}">
							<td width="12%">${mcuf.userid}</td>
							<td width="12%">${mcuf.username}</td>
							<td width="12%">${mcuf.useronlinenum}</td>
							<%
							if(language.equals("zh_tw")){
							%>
							<td width="16%">${fn:replace(fn:replace(fn:replace(mcuf.usertotaltime, "分钟", "分鐘"),"秒","秒"),"小时","小時")}</td>
							<td width="16%">${fn:replace(fn:replace(fn:replace(mcuf.avglogintime, "分钟", "分鐘"),"秒","秒"),"小时","小時")}</td>
							<%
							}else if(language.equals("en")){
							%>
							<td width="16%">${fn:replace(fn:replace(fn:replace(mcuf.usertotaltime, "分钟", "min"),"秒","s"),"小时","h")}</td>
							<td width="16%">${fn:replace(fn:replace(fn:replace(mcuf.avglogintime, "分钟", "min"),"秒","s"),"小时","h")}</td>
							<%
							}else{
							%>
							<td width="16%">${mcuf.usertotaltime}</td>
							<td width="16%">${mcuf.avglogintime}</td>
							<%
							}
							%>
							<td width="24%">${mcuf.logintime}</td>
							<td width="8%"><a href="TopCountUserDetailAction.go?begintime=${begintime}&endtime=${endtime}&uid=${mcuf.userid}"><img
									src='images_gb/text.png' width='16' height='16' class='img_tab' /><%=lu.getLanguage(language,"log.detail","详情") %></a></td>
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
                                <a href="TopMeetingCountUserAction.go?begintime=${begintime}&endtime=${endtime}&page=1"><<</a>
                                <a href="TopMeetingCountUserAction.go?begintime=${begintime}&endtime=${endtime}&page=${nowPage-1}"><</a>
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
                                                <a href="TopMeetingCountUserAction.go?begintime=${begintime}&endtime=${endtime}&page=<%=i%>"><%=i %></a>
                                     		<% }
                                		}

                                }
                                
                                else if((pageNumber-nowNumber)<5){
                                	for(int i=(pageNumber-9);i<= pageNumber;i++){
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{%>
                                                <a href="TopMeetingCountUserAction.go?begintime=${begintime}&endtime=${endtime}&page=<%=i%>"><%=i %></a>
                                     		<% }
                                		}
                                }
                                
                                else{
                                	for(int i=(nowNumber-5);i<(nowNumber+5) && i<= pageNumber;i++){
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{%>
                                                <a href="TopMeetingCountUserAction.go?begintime=${begintime}&endtime=${endtime}&page=<%=i%>"><%=i %></a>
                                     		<% }
                                		}                                	
                                }
         

%>
                    <c:if test="${nowPage!=pageNumber}">
                                <a href="TopMeetingCountUserAction.go?begintime=${begintime}&endtime=${endtime}&page=${nowPage+1}">></a>
                                <a href="TopMeetingCountUserAction.go?begintime=${begintime}&endtime=${endtime}&page=${pageNumber}">>></a>
                                ${nowPage}/${pageNumber} 
                    </c:if>
                    
                    <c:if test="${nowPage==pageNumber}">
                                <span class="disabled"> ></span>
                                <span class="disabled"> >></span>
                                ${pageNumber}/${pageNumber}
                    </c:if>
                </div>
				<div align="center">
					<a id="btndivSubmit" class="divbutton" href="<%=siteurl %>/ConfExportServlet?btime=${begintime}&etime=${endtime}&t=2&c=count&language=<%=language %>"><span><%=lu.getLanguage(language,"button.export","导出") %></span></a>
				</div>
			</div>
		</div>
	</div>