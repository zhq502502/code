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
<script type="text/javascript" src="js/stringRes.js"></script>
<script type="text/javascript"> 
	$(document).ready(function() {
		if (g_installFlag == '0' || g_installFlag == '1')
	    {
			//alert(g_installFlag);
	    	Seegle.postsgsvr(9090, 1, client_registry, "InstallPath");
	    	//alert("!!!!");
	    }
		chechPalyer(9090);
		$('#video_list').addClass("sel_tag");
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
	    document.forms[0].action = "TopVideoListAction.go?Keyword="+Keyword;
	    document.forms[0].submit();
	}
    function delVideo(id){
    	var siteurl = $('#siteurl').val();
	    	if(confirm(getMsg(CONFIRM_DELETE))){ 
	            $.post(siteurl+"/video/update", {action:'delete',id: id, _: new Date().getTime() } ,function(rs) {
	            	if(rs==0){
	             	alert(getMsg(DELETE_SUCESS));
	             	location.href=siteurl+'/TopConfPage.go?inc=TopVideoList';
	            	}
	            	else {
	             	alert(getMsg(DELETE_FAILURE));
	             	return false;
	            	}
	            }); 
	        }else{
	            return false;
	        }
        }           	
	function checkBrowser(downloadurl,filename,realname){
	    var cb = "Unknown";
	    if(window.ActiveXObject){
	        cb = "IE";
	    }else if(navigator.userAgent.toLowerCase().indexOf("firefox") != -1){
	        cb = "Firefox";
	    }else if((typeof document.implementation != "undefined") && (typeof document.implementation.createDocument != "undefined") && (typeof HTMLDocument != "undefined")){
	        cb = "Mozilla";
	    }else if(navigator.userAgent.toLowerCase().indexOf("opera") != -1){
	        cb = "Opera";
	    }
	    window.location.href="VideoDownloadAction.go?downloadurl="+downloadurl+"&filename="+filename+"&realname="+realname+"&browser="+cb;
	    return cb;
	}
</script>
<script type="text/javascript" src="js/conf.index.js"></script>
<script type="text/javascript" src="js/conf.js"></script>
<OBJECT
	CODEBASE='http://www.seegle.cn/RunSgplayer.cab#version=1,0,0,5'
	CLASSID='CLSID:DC61AC79-C88C-42B3-87CC-41CC3AC92F4C' id='testocx'
	height='0' width='0' hspace='0'>
	<param name='_Version' value='65536' />
	<param name='_ExtentX' value='19844' />
	<param name='_ExtentY' value='9260' />
	<param name='_StockProps' value='0' />
</OBJECT>
	<div id="wpbody-content">
	 <form name="form" id="form" action="VideoListAction.go" method="post">   					
		<div class="wrap">
			<div class="space_03">
				<div id="topLinkL">
					<div class="s_001"><%=lu.getLanguage(language,"confpage.menu.videolist","录像列表") %></div>
					<div class="s_002">
							<input type="text" id='searchKey' name="searchKey" maxlength='10' value="${Keyword}" class='Input08'>
							<input type="submit" id='searchbutton' name='searchbutton' value="" class="searchbutton" onClick="selectConf()" />						
					</div>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="tabnew" id='tabnew'>
					<thead>
						<tr><td colspan="6"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabtitle" id='tabtitle'>
						<tr>
							<td width="36%" class="tdTitle"><%=lu.getLanguage(language,"videolist.filename","文件名") %></td>
							<td width="12%" class="tdTitle"><%=lu.getLanguage(language,"videolist.filesize","文件大小") %></td>
							<td width="22%" class="tdTitle"><%=lu.getLanguage(language,"videolist.uploadtime","上传时间") %></td>
							<td width="30%" class="tdTitle"><%=lu.getLanguage(language,"operation","操作") %></td>
						</tr>
						</table></td></tr>
					</thead>
					<tbody>				
					<tr><td colspan="6"><table width="90%" border="0" cellpadding="0" cellspacing="0" class="tabcontent" id='tabcontent'>
					   <c:if test="${fn:length(videoList)==0}"><tr><td colspan="5"><%=lu.getLanguage(language,"list.norecord","没有记录") %></td></tr></c:if>	
					   <c:forEach var="video" items="${videoList}" varStatus="id">
                         <tr bgcolor="#FFFFFF" id="${video.id}">
                            <c:choose>
                               <c:when test="${fn:length(video.file_real_name)>19}"><td title="${video.file_real_name}" width="36%">${fn:substring(video.file_real_name, 0, 19)}...</td></c:when>
                               <c:otherwise><td title="${video.file_real_name}" width="36%">${video.file_real_name}</td></c:otherwise>
                            </c:choose> 
                            <td width="12%">${video.file_size}</td>
                            <td width="22%">${video.file_time}</td>
							<td width="30%">
                       <c:if test="${fn:endsWith(video.file_real_name, '.smvx')}"><c:set var="suffix" value="smvx" scope="page"/></c:if>                       
                       <c:if test="${fn:endsWith(video.file_real_name, '.rmvb')}"><c:set var="suffix" value="rmvb" scope="page"/></c:if>                       
                       <c:if test="${fn:endsWith(video.file_real_name, '.wmv')}"><c:set var="suffix" value="wmv" scope="page"/></c:if>                       
								<img src="images_gb/video.gif" width="16" height="16" class="img_tab" /><a href="#" id="play" onclick="startplay('${pageContext.request.scheme}://${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}${video.download_url}/${video.file_hash_name}','${suffix}')"><%=lu.getLanguage(language,"videolist.view","观看") %></a>&nbsp;
								<img src="images_gb/download.gif" width="16" height="16" class="img_tab" /><a href="#" id="download" onclick="checkBrowser('${video.download_url}','${video.file_hash_name}','${video.file_real_name}')"><%=lu.getLanguage(language,"download.button.down","下载") %></a>&nbsp;
								<c:if test="${userid eq '1000'}">
								<img src="images_gb/del.gif" width="16" height="16" class="img_tab" /><a href="#" id="delete" onclick="delVideo(${video.id})"><%=lu.getLanguage(language,"button.delete","删除") %></a>
								</c:if>
								</td>
								
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
                                <a href="TopVideoListAction.go?page=1&Keyword=${Keyword}"><<</a>
                                <a href="TopVideoListAction.go?page=${nowPage-1}&Keyword=${Keyword}"><</a>
                            </c:when>
                            <c:otherwise>
                                <a href="TopVideoListAction.go?page=1"><<</a>
                                <a href="TopVideoListAction.go?page=${nowPage-1}"><</a>
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
                                    			out.println("<a href=TopVideoListAction.go?Keyword=" + Keyword + "&page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                		else {
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=TopVideoListAction.go?page=" + i + ">" + i + "</a>");
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
                                    			out.println("<a href=TopVideoListAction.go?Keyword=" + Keyword + "&page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                		else {
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=TopVideoListAction.go?page=" + i + ">" + i + "</a>");
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
                                    			out.println("<a href=TopVideoListAction.go?Keyword=" + Keyword + "&page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                		else {
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=TopVideoListAction.go?page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                	}                                	
                                }
%>
                    <c:if test="${nowPage!=pageNumber}">
                        <c:choose>
                            <c:when test="${Keyword!=''}">
                                <a href="TopVideoListAction.go?page=${nowPage+1}&Keyword=${Keyword}">></a>
                                <a href="TopVideoListAction.go?page=${pageNumber}&Keyword=${Keyword}">>></a>
                                ${nowPage}/${pageNumber}
                            </c:when>
                            <c:otherwise>
                                <a href="TopVideoListAction.go?page=${nowPage+1}">></a>
                                <a href="TopVideoListAction.go?page=${pageNumber}">>></a>
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
		<div class="clear"></div>
	  </form>
	</div>