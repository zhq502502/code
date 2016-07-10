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
    <script type="text/javascript" src="js/stringRes.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.10.custom.min.js"></script>
	<script type="text/javascript">
	function selectConf(){
	    var Keyword = document.forms[0].searchKey.value;
	    document.forms[0].action = "TopMeetingManageSelectAction.go?Keyword="+Keyword;
	    document.forms[0].submit();
	}	
		$(document).ready(function() {
			$("#tabs" ).tabs(1);
			$("#searchKey").focus();
	    	$("#searchKey").keydown(function(event){
	        	if(event.keyCode==13){
	        		selectConf();
		        	return false;
	        	}
	        });
			var siteurl = $('#siteurl').val();
			$('#confmanage').addClass("sel_tag");
			$('.canceltop').click(function(){
				var cid = $(this).parent().parent().children("td:first-child").text();
				if(confirm(getMsg(CONFIRM_CANCEL_TOP))){
					$.post(siteurl+"/conf/settop", { cid: cid,topValue: '0', _: new Date().getTime() } ,function(rs) {
						if(rs==8){
							alert(getMsg(CANCEL_TOP_SUCESS));
							var page = "${nowPage}";
							location.href=siteurl+'/TopConfPage.go?inc=TopConfManage&page='+page;
						} else {
							alert(getMsg(CANCEL_TOP_FAILURE));
							return false;
						}
					});						
				}
				return false;
			});	

			$('.settop').click(function(){
				var cid = $(this).parent().parent().children("td:first-child").text();
				var topValue = "${topValue}";
				if(confirm(getMsg(CONFIRM_SET_TOP))){
					$.post(siteurl+"/conf/settop", {cid: cid, topValue: topValue, _: new Date().getTime() } ,function(rs) {
						if(rs==0){
							alert(getMsg(SET_TOP_SUCESS));
							var page = "${nowPage}";
							location.href=siteurl+'/TopConfPage.go?inc=TopConfManage&page='+page;
						} else {
							alert(getMsg(SET_TOP_FAILURE));
							return false;
						}
					});
				}	
				return false;
			});	
		});
	</script>
	<div id="wpbody-content">	
	  			
		<div class="wrap">
			<div class="space_03">			
				<div id="topLinkL">
					<div class="s_001"><%=lu.getLanguage(language,"confmanage.confmanage","会议管理") %></div>					
				</div>
				<div id="tabs" class="tab_m ui-tabs ui-widget ui-widget-content ui-corner-all">
					<ul style='background: #F1F6FD; border: 0'>
						<li><a href="#tabs-1"><%=lu.getLanguage(language,"confmanage.confroommanage","会议室管理") %></a></li>
						<li><a href="#tabs-2"><%=lu.getLanguage(language,"confmanage.othersetting","其它设置") %></a></li>
					</ul>
					<div style="border-bottom:1px #348CD4 solid;"></div>
					<div id="tabs-1">
						<form name="form" id="form" action="MeetingManageAction.go" method="post">					
						<div class="s_002">
								<input type="text" id='searchKey' name="searchKey" maxlength='10' value="${Keyword}" class='Input08'>
								<input type="submit" id='searchbutton' name='searchbutton' value="" class="searchbutton" onClick="selectConf()" />						
						</div>	
						<br/>								
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="tabnew" id='tabnew'>
							<thead>
								<tr><td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabtitle" id='tabtitle'>
								<tr>
									<td width="10%" class="tdTitle"><%=lu.getLanguage(language,"conflist.confid","会议ID") %></td>
									<td width="60%" class="tdTitle"><%=lu.getLanguage(language,"confadd.confname","会议名称") %></td>
									<td width="30%" class="tdTitle"><%=lu.getLanguage(language,"operation","操作") %></td>
								</tr>
								</table></td></tr>
							</thead>
							<tbody>				
							<tr><td colspan="3"><table width="90%" border="0" cellpadding="0" cellspacing="0" class="tabcontent" id='tabcontent'>
							   <c:if test="${fn:length(meetingList)==0}"><tr><td colspan="5"><%=lu.getLanguage(language,"list.norecord","没有记录") %></td></tr></c:if>							   
							   <c:forEach var="meeting" items="${meetingList}" varStatus="id">
		                         <tr bgcolor="#FFFFFF" id="${meeting.id}">
		                            <td width="10%">${meeting.id}</td>
		                            <c:choose>
		                               <c:when test="${fn:length(meeting.confName)>16}"><td title="${meeting.confName}" width="60%">${fn:substring(meeting.confName, 0, 16)}...</td></c:when>
		                               <c:otherwise><td title="${meeting.confName}" width="60%">${meeting.confName}</td></c:otherwise>
		                            </c:choose> 
									<td width="30%">
									<c:choose>
									    <c:when test="${fn:length(meeting.confExtra)==3}"><img src='images_gb/arrow_up.png' width='16' height='16' class='img_tab' /><a class='canceltop' href='javascript:void(0);'><%=lu.getLanguage(language,"confmanage.canceltop","取消置顶") %></a></c:when>
									    <c:otherwise><img src='images_gb/arrow_up.png' width='16' height='16' class='img_tab' /><a class='settop' href='javascript:void(0);'><%=lu.getLanguage(language,"confmanage.settop","置顶") %></a></c:otherwise>
									</c:choose></td>								
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
	                                <a href="MeetingManageSelectAction.go?page=1&Keyword=${Keyword}"><<</a>
	                                <a href="MeetingManageSelectAction.go?page=${nowPage-1}&Keyword=${Keyword}"><</a>
	                            </c:when>
	                            <c:otherwise>
	                                <a href="MeetingManageAction.go?page=1"><<</a>
	                                <a href="MeetingManageAction.go?page=${nowPage-1}"><</a>
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
	                                    			out.println("<a href=TopMeetingManageSelectAction.go?Keyword=" + Keyword + "&page=" + i + ">" + i + "</a>");
	                                    		}
	                                		}
	                                		else {
	                                			if(i==nowNumber){
	                                				out.println("<span class=\'current\'> "+i+"</span>");
	                                    		}else{
	                                    			out.println("<a href=TopMeetingManageAction.go?page=" + i + ">" + i + "</a>");
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
	                                    			out.println("<a href=TopMeetingManageSelectAction.go?Keyword=" + Keyword + "&page=" + i + ">" + i + "</a>");
	                                    		}
	                                		}
	                                		else {
	                                			if(i==nowNumber){
	                                				out.println("<span class=\'current\'> "+i+"</span>");
	                                    		}else{
	                                    			out.println("<a href=TopMeetingManageAction.go?page=" + i + ">" + i + "</a>");
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
	                                    			out.println("<a href=TopMeetingManageSelectAction.go?Keyword=" + Keyword + "&page=" + i + ">" + i + "</a>");
	                                    		}
	                                		}
	                                		else {
	                                			if(i==nowNumber){
	                                				out.println("<span class=\'current\'> "+i+"</span>");
	                                    		}else{
	                                    			out.println("<a href=TopMeetingManageAction.go?page=" + i + ">" + i + "</a>");
	                                    		}
	                                		}
	                                	}                                	
	                                }
	%>
	                    <c:if test="${nowPage!=pageNumber}">
	                        <c:choose>
	                            <c:when test="${Keyword!=''}">
	                                <a href="TopMeetingManageSelectAction.go?page=${nowPage+1}&Keyword=${Keyword}">></a>
	                                <a href="TopMeetingManageSelectAction.go?page=${pageNumber}&Keyword=${Keyword}">>></a>
	                                ${nowPage}/${pageNumber}
	                            </c:when>
	                            <c:otherwise>
	                                <a href="TopMeetingManageAction.go?page=${nowPage+1}">></a>
	                                <a href="TopMeetingManageAction.go?page=${pageNumber}">>></a>
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
                    </form>
					</div>
					<div id="tabs-2"  style="padding: 0px;">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD" class="tamnew2">
		      <tr>
	            <td width="20%"><%=lu.getLanguage(language,"confmanage.confordersetting","会议室排序设置") %></td>
			    <td><a id="order_name" value="id">
			   		<select id="conf_order_name">
						<option value="id">ID</option>
						<option value="conf_name"><%=lu.getLanguage(language,"confadd.confname","会议名称") %></option>
						<option value="conf_begin_time"><%=lu.getLanguage(language,"confmanage.confbegintime","会议开始时间") %></option>
						<option value="conf_end_time"><%=lu.getLanguage(language,"confmanage.confendtime","会议结束时间") %></option>
					</select></a>
					 | 
					<select id="conf_order_type">
					<option value="asc"><%=lu.getLanguage(language,"confmanage.orderbyasc","升序") %></option>
					<option value="desc"><%=lu.getLanguage(language,"confmanage.orderbydesc","降序") %></option>
					</select>
		      </tr>
		     <tr>
			    <td colspan="2" valign="middle" style="height:40px; text-align:center;">
					<input type="submit" id="order_button" value="<%=lu.getLanguage(language,"button.save","保存") %>" class="sbutton" />
				</td>
			  </tr> 
			</table>
					</div>
					
				</div>	
					              						
			</div>						
		</div>
		<div class="clear"></div>
	  
	</div>
<script type="text/javascript">
var siteurl = $('#siteurl').val();
var order_name = "${confOrder.ordername}";
var order_type = "${confOrder.ordertype}";
var tipValue = "${maxuser.tip}";
$(function(){
	$("#conf_order_name").val(order_name);
	$("#conf_order_type").val(order_type);
	$(':radio[name=tip]').eq(tipValue).attr('checked',true);
	$("#order_button").click(function(){
		$.getJSON(siteurl+"/MeetingOrderAction.go",{ordername:$("#conf_order_name").val(),ordertype:$("#conf_order_type").val(),tip:$('input:radio[name="tip"]:checked').val()},function(data){
			if(data==0){
				alert(getMsg(SAVE_SUCESS));
				//save_after();
			}else{
				alert(getMsg(SAVE_FAILURE));
			}
		});
	});
});
</script>