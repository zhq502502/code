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
<script type="text/javascript" src="js/conf.index.js"></script>
<script type="text/javascript" src="js/conf.js"></script>
<script type="text/javascript"> 
	$(document).ready(function() {
		if (g_installFlag == '0' || g_installFlag == '1')
	    {
			//alert(g_installFlag);
	    	Seegle.postsgsvr(9090, 1, client_registry, "InstallPath");
	    	//alert("!!!!");
	    }
		chechPalyer(9090);
		$('#server_video_list').addClass("sel_tag");
	});
    function delVideo(id){
    	var siteurl = $('#siteurl').val();
	    	if(confirm(getMsg(CONFIRM_DELETE))){ 
	            $.post(siteurl+"/video/update", {action:'delete',id: id, _: new Date().getTime() } ,function(rs) {
	            	if(rs==0){
	             	alert(getMsg(DELETE_SUCESS));
	             	location.href=siteurl+'/ConfPage.go?inc=ServerVideoList';
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
	    window.location.href="ServerVideoDownloadAction.go?downloadurl="+downloadurl+"&filename="+filename+"&realname="+realname+"&browser="+cb;
	    return cb;
	}
</script>
	<div id="wpbody-content">
	 <form name="form" id="form" action="" method="post">   					
		<div class="wrap">
			<div class="space_03">
				<div id="topLinkL">
					<div class="s_001"><%=lu.getLanguage(language,"confpage.menu.servervideolist","服务器录像列表") %></div>
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
								<img src="images_gb/video.gif" width="16" height="16" class="img_tab" /><a href="#" id="play" onclick="startplay('${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}${'/upload/server_upload/'}${video.file_real_name}','smvx')"><%=lu.getLanguage(language,"videolist.view","观看") %></a>&nbsp;
								<img src="images_gb/download.gif" width="16" height="16" class="img_tab" /><a href="#" id="download" onclick="checkBrowser('${'/upload/server_upload'}','${video.file_real_name}','${video.file_real_name}')"><%=lu.getLanguage(language,"download.button.down","下载") %></a><!--&nbsp;
								<c:if test="${userid eq 'admin'}">
								<img src="images_gb/del.gif" width="16" height="16" class="img_tab" /><a href="#" id="delete" onclick="delVideo(${video.id})"><%=lu.getLanguage(language,"button.delete","删除") %></a>
								</c:if>
								--></td>
								
                        </tr>
                      </c:forEach>
						</table></td></tr>
						<tr>
							<th colspan="6"></th>
						</tr>
					</tbody>
				</table>
				<br />
                <div class="sabrosus">
                    <c:if test="${nowPage==1||pageNumber==1}">
                                <span class="disabled"> <<</span>
                                <span class="disabled"> <</span>
                    </c:if>
                    <c:if test="${nowPage>1}">
                                <a href="ServerVideoListAction.go?page=1"><<</a>
                                <a href="ServerVideoListAction.go?page=${nowPage-1}"><</a>
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
                                    		}else{
                                    			out.println("<a href=ServerVideoListAction.go?page=" + i + ">" + i + "</a>");
                                    		}
                                		}

                                }
                                
                                else if((pageNumber-nowNumber)<5){
                                	for(int i=(pageNumber-9);i<= pageNumber;i++){
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=ServerVideoListAction.go?page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                }
                                
                                else{
                                	for(int i=(nowNumber-5);i<(nowNumber+5) && i<= pageNumber;i++){
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=ServerVideoListAction.go?page=" + i + ">" + i + "</a>");
                                    		}
                                		}                                	
                                }
         

%>
                    <c:if test="${nowPage!=pageNumber}">
                                <a href="ServerVideoListAction.go?page=${nowPage+1}">></a>
                                <a href="ServerVideoListAction.go?page=${pageNumber}">>></a>
                                ${nowPage}/${pageNumber} 
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