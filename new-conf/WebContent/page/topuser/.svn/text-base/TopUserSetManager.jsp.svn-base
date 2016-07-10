<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
					<div class="s_001"><%=lu.getLanguage(language,"topconfpage.menu.setmanager","设置管理员") %></div>
					<div class="s_002">
						<input type="text" name="name" class='Input07' id="name" size="20" maxlength="16" /><input type="button" class="sbutton" id="addadmin" value="<%=lu.getLanguage(language,"topusersetmanager.addmanager","添加管理员") %>" />
					</div>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabnew" id='tabnew'>
					<thead>
						<tr><td colspan="4"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabtitle" id='tabtitle'>
						<tr>
							<td width="25%" class="tdTitle"><%=lu.getLanguage(language,"login.html.userid","用户账号") %></td>
							<td width="25%" class="tdTitle"><%=lu.getLanguage(language,"topusersetmanager.username","用户名") %></td>
							<td width="25%" class="tdTitle"><%=lu.getLanguage(language,"topusersetmanager.userrole","用户权限") %></td>
							<td width="25%" class="tdTitle"><%=lu.getLanguage(language,"operation","操作") %></td>
						</tr>
						</table></td></tr>
					</thead>
					<tbody>
						<tr><td colspan="4">
							<table width="90%" border="0" cellpadding="0" cellspacing="0" class="tabcontent" id='tabcontent'>
								<c:if test="${fn:length(userList)==0}"><tr><td colspan="3"><%=lu.getLanguage(language,"list.norecord","没有记录") %></td></tr></c:if>
								<c:forEach var="user" items="${userList}" varStatus="id">
								<c:if test="${user.roleId==1}">
								<tr id="${user.userId}">
									<td width="25%">${user.userId}</td>
									<td width="25%">${user.userName}</td>
									<td width="25%"><%=lu.getLanguage(language,"topusersetmanager.normal","普通用户") %></td>
									<td width="25%">
										<span><img src='images_gb/add.gif' width='16' height='16' class='img_tab' /><a class='setadmin' href='javascript:void(0);'><%=lu.getLanguage(language,"topusersetmanager.setmanager","设为管理员") %></a></span>
									</td>
								</tr>
								</c:if>
								<c:if test="${user.roleId==2}">
								<tr id="${user.userId}">
									<td width="25%">${user.userId}</td>
									<td width="25%">${user.userName}</td>
									<td width="25%"><%=lu.getLanguage(language,"topusersetmanager.manager","管理员") %></td>
									<td width="25%">
										<span><img src='images_gb/del.gif' width='16' height='16' class='img_tab' /><a class='removeadmin' href='javascript:void(0);'><%=lu.getLanguage(language,"topusersetmanager.cancelmanager","取消管理员") %></a></span>
									</td>
								</tr>
								</c:if>
								</c:forEach>
							</table>
						</td></tr>
					</tbody>
				</table>

				<br />
				<div class="sabrosus">
                    <c:if test="${nowPage==1||pageNumber==1}">
                                <span class="disabled"> <<</span>
                                <span class="disabled"> <</span>
                    </c:if>
                    <c:if test="${nowPage>1}">
                       <a href="TopUserSetManagerAction.go?page=1"><<</a>
                       <a href="TopUserSetManagerAction.go?page=${nowPage-1}"><</a>
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
                           			out.println("<a href=TopUserSetManagerAction.go?page=" + i + ">" + i + "</a>");
                           		}
                         	}
                         }
                         
                         else if((pageNumber-nowNumber)<5){
                         	for(int i=(pageNumber-9);i<= pageNumber;i++){
                        			if(i==nowNumber){
                        				out.println("<span class=\'current\'> "+i+"</span>");
                            		}else{
                            			out.println("<a href=TopUserSetManagerAction.go?page=" + i + ">" + i + "</a>");
                            		}
                         	}
                         }                                
                         else{
                         	for(int i=(nowNumber-5);i<(nowNumber+5) && i<= pageNumber;i++){
                        			if(i==nowNumber){
                        				out.println("<span class=\'current\'> "+i+"</span>");
                            		}else{
                            			out.println("<a href=TopUserSetManagerAction.go?page=" + i + ">" + i + "</a>");
                            		}
                         	}                                	
                         }
					%>
                    <c:if test="${nowPage!=pageNumber}">
                        <a href="TopUserSetManagerAction.go?page=${nowPage+1}">></a>
                        <a href="TopUserSetManagerAction.go?page=${pageNumber}">>></a>
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
		<br class="clear" />
		<div class="clear"></div>
	</div>
	<!-- wpbody-content -->
    <script type="text/javascript" src="js/stringRes.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#user_addmanager').addClass("sel_tag");
			var siteurl = $('#siteurl').val();
			$('.removeadmin').click(function(){
				var uid = $(this).parent('span').parent('td').prev().prev().prev().text();
				$.post(siteurl+"/topuser/update", { action:'update', uid: uid, type: '1', _: new Date().getTime() } ,function(rs) {
					if(rs==0){
						alert(getMsg(CANCEL_ADMIN_SUCCESS));
						location.href=siteurl+'/TopConfPage.go?inc=TopUserSetManager';
					} else {
						alert(getMsg(CANCEL_ADMIN_FAILURE));
						return false;
					}
				});
				return false;
			});
			$('.setadmin').click(function(){
				var uid = $(this).parent('span').parent('td').prev().prev().prev().text();
				$.post(siteurl+"/topuser/update", { action:'update', uid: uid, type: '2', _: new Date().getTime() } ,function(rs) {
					if(rs==0){
						alert(getMsg(SET_ADMIN_SUCCESS));
						location.href=siteurl+'/TopConfPage.go?inc=TopUserSetManager';
					} else {
						alert(getMsg(SET_ADMIN_FAILURE));
						return false;
					}
				});
				return false;
			});
			
			$('#addadmin').click(function(){
				var $name = $('#name');
				var isName = /^(?!_)(?!.*?_$)(?!-)(?!.*?-$)[a-zA-Z0-9-_]+$/;
				if($.trim($name.val())==""){
					alert("不能为空!");
					$name.focus();
					return false;
				} else if(!$name.val().match(isName)){
		 			alert('请输入由字母、数字或下划线组合的字符!');
		 			$name.focus();
					return false;
		 		} else {
					$.post(siteurl+"/topuser/update", { action:'create', name: $name.val(), type: '2', _: new Date().getTime() } ,function(rs) {
						if(rs==0){
							alert("添加管理员成功!");
							location.href=siteurl+'/TopConfPage.go?inc=TopUserSetManager';
						} else {
							alert("添加管理员失败!");
							return false;
						}
					});
		 		}
				return false;
			});
		});
	</script>