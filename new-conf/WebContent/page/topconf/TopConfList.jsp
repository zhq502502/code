<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="js/conf.js"></script>
<script type="text/javascript" src="js/conf.index1.js"></script>
<script type="text/javascript" src="js/base64.js"></script>
<script type="text/javascript"> 
$(document).ready(function() {
	if (g_installFlag == '0' || g_installFlag == '1')
    {
		if(browser.versions.iPad){
			g_installFlag = "4";
		}
		else if(browser.versions.iPhone){
			g_installFlag = "5";
		}
		else if(browser.versions.android){
			g_installFlag = "3";
		}
		else{
			Seegle.postsgsvr(9090, 1, Seegle.client_registry, "InstallPath");	
		}	
    }
	$('#conf_list').addClass("sel_tag");
    $("#searchKey").focus();
    $("#searchKey").keydown(function(event){
    	if(event.keyCode==13){
        	selectConf();
        	return false;
    	}
    });
});
function selectConf(){
    var Keyword = document.forms[0].searchKey.value;
    document.forms[0].action = "TopMeetingSelectAction.go?Keyword="+Keyword;
    document.forms[0].submit();
}
</script>
	<div id="wpbody-content">
	 <form name="form" id="form" action="TopConfListAction.go" method="get">   					
		<div class="wrap">
			<div class="space_03">
				<div id="topLinkL">
					<div class="s_001"><%=lu.getLanguage(language,"conflist.conflist","会议室列表") %></div>
					<div class="s_002">
							<input type="text" id='searchKey' name="Keyword" maxlength='10' value="${Keyword}" class='Input08'>
							<input type="button" id='searchbutton' name='' value="" class="searchbutton" onClick="selectConf()" />						
					</div>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="tabnew" id='tabnew'>
					<thead>
						<tr><td colspan="5"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabtitle" id='tabtitle'>
						<tr>
							<td width="10%" class="tdTitle"><%=lu.getLanguage(language,"conflist.confid","会议ID") %></td>
							<td width="30%" class="tdTitle"><%=lu.getLanguage(language,"confadd.confname","会议名称") %></td>
							<td width="10%" class="tdTitle"><%=lu.getLanguage(language,"conflist.online","在线人数") %></td>
							<td width="20%" class="tdTitle"><%=lu.getLanguage(language,"confadd.etime","结束时间") %></td>
							<td width="30%" class="tdTitle"><%=lu.getLanguage(language,"operation","操作") %></td>
						</tr>
						</table></td></tr>
					</thead>
					<tbody>				
					<tr><td colspan="5"><table width="90%" border="0" cellpadding="0" cellspacing="0" class="tabcontent" id='tabcontent'>
					   <c:if test="${fn:length(meetingList)==0}"><tr><td colspan="5"><%=lu.getLanguage(language,"list.norecord","没有记录") %></td></tr></c:if>	
					   <c:forEach var="meeting" items="${meetingList}" varStatus="id">
                         <tr bgcolor="#FFFFFF" id="${meeting.id}">
                            <td width="10%"><c:if test="${fn:length(meeting.confExtra)==3}"><img src='images_gb/arrow_up.png' width='16' height='16' class='img_tab' /></c:if>${meeting.id}</td>
                            <c:choose>
                               <c:when test="${fn:length(meeting.confName)>6}"><td title="${meeting.confName}" width="30%">${fn:substring(meeting.confName, 0, 6)}...</td></c:when>
                               <c:otherwise><td title="${meeting.confName}" width="30%">${meeting.confName}</td></c:otherwise>
                            </c:choose> 
                            <td width="10%" class='online'>${meeting.confOnlineNumber}</td>
                            <td width="20%">${meeting.confEndTime}</td>
							<td width="30%"><img src='images_gb/add.gif' width='16' height='16' class='img_tab' /><a class='join' href='javascript:void(0);'><%=lu.getLanguage(language,"conflist.startconf","参加") %></a>&nbsp;
							<img src='images_gb/telephone.gif' width='16' height='16' class='img_tab' /><a href='TOMeetingReservationAction.go?method=goto&top=top&confid=${meeting.id}'><%=lu.getLanguage(language,"conflist.reservation","预约") %></a>&nbsp;
							<img src='images_gb/d_sign.gif' width='16' height='16' class='img_tab' /><a class='geturl' href='#'><%=lu.getLanguage(language,"conflist.geturl","获取URL") %></a>&nbsp;</td>
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
                        <c:choose>
                            <c:when test="${Keyword!=''}">
                                <a href="TopMeetingSelectAction.go?page=1&Keyword=${Keyword}"><<</a>
                                <a href="TopMeetingSelectAction.go?page=${nowPage-1}&Keyword=${Keyword}"><</a>
                            </c:when>
                            <c:otherwise>
                                <a href="TopConfListAction.go?page=1"><<</a>
                                <a href="TopConfListAction.go?page=${nowPage-1}"><</a>
                            </c:otherwise>
                        </c:choose>
                    </c:if>

                    <%           //根据关键字查询列表
                                String Keyword = request.getParameter("Keyword");
                                /*if (Keyword != null) {
                                    Keyword = new String(Keyword.getBytes("ISO-8859-1"), "UTF-8");  //中文转码
                                }*/

                                //总页数
                                String str = request.getAttribute("pageNumber").toString();
                                int pageNumber = Integer.parseInt(str);

                                //当前页
                                String now = request.getAttribute("nowPage").toString();
                                int nowNumber = Integer.parseInt(now);

                                if(nowNumber <= 6 || pageNumber <= 10){
                                	for(int i=1;i<=10 && i<= pageNumber;i++){
                                		if(Keyword != null && Keyword != "") {
                                			if(i==nowNumber){
                                    			out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=TopMeetingSelectAction.go?Keyword=" + Keyword + "&page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                		else {
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=TopConfListAction.go?page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                	}
                                }
                                
                                else if((pageNumber-nowNumber)<5){
                                	for(int i=(pageNumber-9);i<= pageNumber;i++){
                                		if(Keyword != null & Keyword != "") {
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=TopMeetingSelectAction.go?Keyword=" + Keyword + "&page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                		else {
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=TopConfListAction.go?page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                	}
                                }
                                
                                else{
                                	for(int i=(nowNumber-5);i<(nowNumber+5) && i<= pageNumber;i++){
                                		if(Keyword != null & Keyword != "") {
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=TopMeetingSelectAction.go?Keyword=" + Keyword + "&page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                		else {
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=TopConfListAction.go?page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                	}                                	
                                }
%>
                    <c:if test="${nowPage!=pageNumber}">
                        <c:choose>
                            <c:when test="${Keyword!=''}">
                                <a href="TopMeetingSelectAction.go?page=${nowPage+1}&Keyword=${Keyword}">></a>
                                <a href="TopMeetingSelectAction.go?page=${pageNumber}&Keyword=${Keyword}">>></a>
                                ${nowPage}/${pageNumber}
                            </c:when>
                            <c:otherwise>
                                <a href="TopConfListAction.go?page=${nowPage+1}">></a>
                                <a href="TopConfListAction.go?page=${pageNumber}">>></a>
                                ${nowPage}/${pageNumber} 
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    
                    <c:if test="${nowPage==pageNumber}">
                                <span class="disabled"> ></span>
                                <span class="disabled"> >></span>
                                ${pageNumber}/${pageNumber}
                    </c:if>

                </div>
			</div>
		</div>
		<div id='dialog_box1' style='display: none;height: 150px; outline: 0px none; width: 550px; overflow: visible;background: #F1F6FD;text-align: center;border: 0px'>
			<div class='message'>
			<%=lu.getLanguage(language,"conflist.confurl","会议链接") %>：<label id="confurl1"></label><input id="confurl" type="hidden" name="confurl"/>
			<br class="clear" />
			<br class="clear" />
			<input type="button" value="<%=lu.getLanguage(language,"button.copy","复制") %>" class="sbutton" id="copy"  onclick="copyToClipBoard()" />
		</div>
		</div>
		<div class="clear"></div>
	  </form>
	</div>