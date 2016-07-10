<%
String siteurl = request.getSession().getAttribute("siteurl").toString();
String confid = request.getAttribute("confid").toString();
%>
<%@ page language="java" pageEncoding="UTF-8"%>
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
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001"><%=lu.getLanguage(language,"confedit.modifyconf","修改会议室信息") %></div>
			</div>
		<div id="tabs" class="tab_m">
			<ul style='background: #F1F6FD; border: 0'>
			<li><a href="#tabs-1" onclick="confedit()"><%=lu.getLanguage(language,"confedit.confroominfo","会议室信息") %></a></li>
			<li><a href="#tabs-2" onclick="confcommon()"><%=lu.getLanguage(language,"confedit.confcommon","默认与会者") %></a></li>
			<li><a href="#tabs-3"><%=lu.getLanguage(language,"confedit.confadmin","视频会议管理员") %></a></li>
			</ul>
			<div style="border-bottom:1px #348CD4 solid;"></div>
				
		<div id="tabs-3">
		<form>		
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabnew" id='tabnew'>
					<thead>
						<tr><td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabtitle" id='tabtitle'>
						<tr>
						    <td width="6%" class="tdTitle"></td>
							<td width="47%" class="tdTitle"><%=lu.getLanguage(language,"confedit.confadmin","视频会议管理员") %></td>
							<td width="47%" class="tdTitle"><%=lu.getLanguage(language,"operation","操作") %></td>
						</tr>
						</table></td></tr>
					</thead>
					<tbody>
						<tr><td colspan="3"><table width="90%" border="0" cellpadding="0" cellspacing="0" class="tabcontent" id='tabcontent'>
					     <c:if test="${fn:length(adminList)==0}"><tr><td colspan="2"><%=lu.getLanguage(language,"list.norecord","没有记录") %></td></tr></c:if>							
					     <c:forEach var="aList" items="${adminList}" varStatus="id">
                         <tr bgcolor="#FFFFFF" id="${aList.userId}">
                         	<td width="6%"><input name="usernames" type="checkbox" class="checkchil" value="${aList.userName}"/></td>                         
							<td width="47%">${aList.userName}</td>
							<td width="47%">						
                               <span><img src='images_gb/del.gif' width='16' height='16' class='img_tab' /><a class='removeconfadmin' href='javascript:void(0);'><%=lu.getLanguage(language,"button.delete","删除") %></a></span>
                           </td>
						</tr>
						</c:forEach>
						</table></td></tr>
					</tbody>				
				</table>
				<div class="s_004"><input type="checkbox" id="checkall"/>&nbsp;<span style="color:#717171;"><%=lu.getLanguage(language,"button.selectall","全选") %></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <input class="sbutton" type="button" onclick="deleteall()" value="<%=lu.getLanguage(language,"button.deletebatch","批量删除") %>"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <input name="cid" id="cid" size="22" type="hidden"  value="${confid}"/>                            
                     <input type="button" name="add" id="add" value="<%=lu.getLanguage(language,"button.add","添加") %>" class="sbutton" onclick="selectUsersOpen()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     <input type="button" id="reset_btn" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" />
				</div>								
				<div id="sabrosus" class="sabrosus">
		                    <c:if test="${nowPage==1||pageNumber==1}">
		                                <span class="disabled"> <<</span>
		                                <span class="disabled"> <</span>
		                    </c:if>
		                    <c:if test="${nowPage>1}">
		                       <a href="MeetingModAdminAction.go?page=1&confid=${confid}"><<</a>
		                       <a href="MeetingModAdminAction.go?page=${nowPage-1}&confid=${confid}"><</a>
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
		                           			out.println("<a href=MeetingModAdminAction.go?page=" + i + "&confid="+confid+">" + i + "</a>");
		                           		}
		                         	}
		                         }
		                         
		                         else if((pageNumber-nowNumber)<5){
		                         	for(int i=(pageNumber-9);i<= pageNumber;i++){
		                        			if(i==nowNumber){
		                        				out.println("<span class=\'current\'> "+i+"</span>");
		                            		}else{
		                            			out.println("<a href=MeetingModAdminAction.go?page=" + i + "&confid="+confid+">" + i + "</a>");
		                            		}
		                         	}
		                         }                                
		                         else{
		                         	for(int i=(nowNumber-5);i<(nowNumber+5) && i<= pageNumber;i++){
		                        			if(i==nowNumber){
		                        				out.println("<span class=\'current\'> "+i+"</span>");
		                            		}else{
		                            			out.println("<a href=MeetingModAdminAction.go?page=" + i + "&confid="+confid+">" + i + "</a>");
		                            		}
		                         	}                                	
		                         }
							%>
		                    <c:if test="${nowPage!=pageNumber}">
		                        <a href="MeetingModAdminAction.go?page=${nowPage+1}&confid=${confid}">></a>
		                        <a href="MeetingModAdminAction.go?page=${pageNumber}&confid=${confid}">>></a>
		                        ${nowPage}/${pageNumber} 
		                    </c:if>
		                    
		                    <c:if test="${nowPage==pageNumber}">
		                        <span class="disabled"> ></span>
		                        <span class="disabled"> >></span>
		                        ${pageNumber}/${pageNumber}
		                    </c:if>
		                </div>
			</form>
				</div><!--tab3完结-->		
			</div><!--tabs完结-->
		</div><!--space_03完结-->
	</div><!--wrap完结-->
</div><!--wpbody-content完结-->
<style>
#leftUl li,#rightUl li{
	float: left;
	width:100%;
	border-bottom: 1px solid #cccccc;
	cursor: pointer;
	color: black;
}
.message{
	padding: 24px 15px 24px 15px;
}
</style>
<div id='dialog_box' style='display: none;height: 430px; outline: 0px none; width: 450px; overflow: visible;background: #F1F6FD'>
<div class='message'>
	<div style="width: 400px;height: 300px;background-color: white; ">
	<table width="93%" cellspacing="0" cellpadding="0" border="0" align="center" style=" table-layout:fixed">
		<tr height="20">
			<td width="190" align="center">
				<a id="addall" href="javascript:addall()"><%=lu.getLanguage(language,"button.addall","全部添加") %></a>
			</td>
			<td width="20">
			</td>
			<td width="190" align="center">
				<a id="delall" href="javascript:delall()"><%=lu.getLanguage(language,"button.delall","全部删除") %></a>
			</td>
		</tr>
		<tr>
			<td width="180" >
				<div id="linkman_left" class="bd_upload" style="border: 1px solid #C6D5E1;width: 100%;height: 280px;overflow: auto;">
					<ul id="leftUl">
					      <c:forEach var="aList1" items="${uadminList}" varStatus="id">                                  
                               <li onclick="leftClick(this)"><span  id="${aList1.userName}" class="m_text">${aList1.userName}[${aList1.alias}]</span><img class="m_img" align="absmiddle" src="images_gb/bot_add.gif"></li>                          
                          </c:forEach>
					</ul>
				</div>
			</td>
			<td width="20" align="center">
				<img src="images_gb/arrow_left.gif">
			</td>
			<td width="180">
				<div id="linkman_right" class="bd_upload" style="border: 1px solid #C6D5E1;width: 100%;height: 280px;overflow: auto;">
					<ul id="rightUl">			
					</ul>
				</div>
			</td>
		</tr>
	</table>
	</div>
	<br class="clear" />
	<div align="right">
	<input type="button" value="<%=lu.getLanguage(language,"button.submit","确定") %>" class="sbutton" onclick="okselect()" />
	<input type="button" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" onclick="closeDlog()" />
	</div>
</div>
</div> 
    <script type="text/javascript" src="js/stringRes.js"></script>
    <script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.10.custom.min.js"></script>
	<script type="text/javascript">
		$( "#tabs" ).tabs({cookie:{ expires: 1}});
		$(document).ready(function() {
			$(".ui-tabs-selected a").click();
			$('#conf_manage').addClass("sel_tag");
			$("#leftUl li,#rightUl li").live("mouseover",function (ev) {
				$(this).css("background-color","#cccccc");
				
			});
			$("#leftUl li,#rightUl li").live("mousemove",function (ev) {
				$(this).css("background-color","#cccccc");
				$("#emailalt").html($(this).children("span").attr("id"));
				$("#emailalt").show();
				$("#emailalt").css({left:ev.clientX+15+$(document).scrollLeft()});
				$("#emailalt").css({top:ev.clientY+15+$(document).scrollTop()});
				
			});
			$("#leftUl li,#rightUl li").live("mouseout",function () {
				$(this).css("background-color","");
				$("#emailalt").hide();
			});
			var siteurl = $('#siteurl').val();						
			$('#reset_btn').click(function(){ //取消按钮
				var page = "${nowPage}";
				location.href=siteurl+'/ConfPage.go?inc=ConfManage&page='+page;
			});
			/*全选框点击*/
			$("#checkall").bind("click",function(){
				if($("#checkall").attr("checked")==true){
					$(":checkbox[name='usernames']").attr("checked",true);
				}else{
					$(":checkbox[name='usernames']").attr("checked",false);
				}
			});
			$('.removeconfadmin').click(function(){
				var cid = $('#cid').val();
				var account = $(this).parent('span').parent('td').prev().text();
				$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
						if(rs==null||rs==""){
							alert(getMsg(ERR_CURRENT_USER_UNEXIST));
							location.href = siteurl+'/logout';
						}
						else if(rs==1 || rs==2){
							$.post(siteurl+"/ConfUserServlet", { action:'confAdmin', account: account, cid: cid, operation: "confuserdelbyname",_: new Date().getTime() } ,function(rs) {
								if(rs==0){
									alert(getMsg(CANCEL_CONFADMIN_SUCESS));
									var page = "${nowPage}";
									location.href=siteurl+'/ConfPage.go?inc=ConfAdmin&confid='+cid+'&page='+page;
								} else {
									alert(getMsg(CANCEL_CONFADMIN_FAILURE));
									return false;
								}
							});
						} 
						else {
							alert(getMsg(ERR_NO_PERMISSION));
							location.href = siteurl+'/logout';
							//location.href = siteurl+'/ConfPage.go?inc=ConfList';
							return false;
						}
					});
				
				return false;
			});
		});
		function confedit(){
			var siteurl = $('#siteurl').val();
			var cid = $('#cid').val();
			var page = "${nowPage}";
			location.href=siteurl+'/ConfPage.go?inc=ConfEdit&confid='+cid+'&page='+page;
		}
		function confcommon(){
			var siteurl = $('#siteurl').val();
			var cid = $('#cid').val();
			var page = "${nowPage}";
			location.href=siteurl+'/ConfPage.go?inc=ConfCommon&confid='+cid+'&page='+page;
		}
		function deleteall(){
			var cid = $('#cid').val();
			var siteurl = $('#siteurl').val();
			var checkeds = $(":checkbox[name='usernames']:checked");
			var usernames="";
			checkeds.each(function(i,n){
				usernames+=","+$(this).val();
			});
			usernames=usernames.substring(1,usernames.length);
			if(usernames==""){
				alert(getMsg(ERR_NO_USER_SELECTED));
				return false;
			}
			if(confirm(getMsg(CONFIRM_DELETE))){
				$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
					if(rs==null||rs==""){
						alert(getMsg(ERR_CURRENT_USER_UNEXIST));
						location.href = siteurl+'/logout';
					}
					else if(rs==1||rs==2){
						$.post(siteurl+"/ConfUserServlet", { action:'confAdmin', account: usernames, cid: cid, operation: "confuserdelbyname",_: new Date().getTime() } ,function(rs) {
							if(rs==0){
								alert(getMsg(CANCEL_CONFADMIN_SUCESS));
								var page = "${nowPage}";
								location.href=siteurl+'/ConfPage.go?inc=ConfAdmin&confid='+cid+'&page='+page;
							} else {
								alert(getMsg(CANCEL_CONFADMIN_FAILURE));
								return false;
							}
						});
					} else {
						alert(getMsg(ERR_NO_PERMISSION));
						location.href = siteurl+'/logout';
						return false;
					}
				});
			}
			return false;
		}
	</script>
	<script type="text/javascript">
		var siteurl = $('#siteurl').val();
		$(document).ready(function() {
			$("#leftUl li,#rightUl li").live("mouseover",function (ev) {
				$(this).css("background-color","#cccccc");
				
			});
			$("#leftUl li,#rightUl li").live("mousemove",function (ev) {
				$(this).css("background-color","#cccccc");
				$("#emailalt").html($(this).children("span").attr("id"));
				$("#emailalt").show();
				$("#emailalt").css({left:ev.clientX+15+$(document).scrollLeft()});
				$("#emailalt").css({top:ev.clientY+15+$(document).scrollTop()});
				
			});
			$("#leftUl li,#rightUl li").live("mouseout",function () {
				$(this).css("background-color","");
				$("#emailalt").hide();
			});
			$("#users2").blur();
		});
		/*选择用户*/
		function selectUsersOpen(){
			$('#dialog_box').dialog({
				modal: true,
				position:"20%",
				width:455,
				height:430,
				title:"<span style='color:#FFFFFF;'>"+getMsg(ERR_NO_CONFADMIN_SELECTED)+"</span>"
				
			});
			$("#ui-dialog-title-dialog_box").css({"height":"25px","float":"left","padding-top":"5px","padding-left":"5px"});
			$(".ui-dialog-titlebar-close").css("float","right");
			$(".ui-dialog-titlebar-close").css("width","30px");
			$(".ui-dialog-titlebar").css("background-image","");
			$(".ui-dialog-titlebar").css("background-color","#348CD4");
			$("#dialog_box").css("border","0");
		};
		//关闭弹层
		$('#dialog_box').dialog("close");
	
		/*清除用户选择框*/
		function clearUsers(){
			$("#users").val("");
			$("#users1").val("");
		}
		/*清除用户选择框*/
		function clearUsers2(){
			$("#users2").val("");
			$("#users2").blur();
		}
		/*人员选择框左边点击*/
		function leftClick(th){
			var img = '<img class="m_img" align="absmiddle" src="'+siteurl+'/images_gb/bot_mov.gif">';
			$(th).children("img").remove();
			$(th).append(img);
			var html = "<li onclick='rightClick(this)'>"+$(th).html()+"</li>";
			$(th).remove();
			$("#rightUl").append(html);
			$("#emailalt").hide();
		}
		/*人员选择框右边点击*/
		function rightClick(th){
			var img = '<img class="m_img" align="absmiddle" src="'+siteurl+'/images_gb/bot_add.gif">';
			$(th).children("img").remove();
			$(th).append(img);
			var html = "<li onclick='leftClick(this)'>"+$(th).html()+"</li>";
			$(th).remove();
			$("#leftUl").append(html);
			$("#emailalt").hide();
		}
		/*确定*/
		function okselect(){
			var rightLi = $("#rightUl span");
			var names="";
			rightLi.each(function(i,n){
				var name = $(this).attr("id");
				names+=","+name;
			})
			names = names.substring(1,names.length)            
			var cid = $('#cid').val();
			$.post(siteurl+"/ConfUserServlet", { action:'confAdmin', account: names, cid: cid, operation: "confuseraddbyname",_: new Date().getTime() } ,function(rs) {
				var page = "${nowPage}";
				location.href=siteurl+'/ConfPage.go?inc=ConfAdmin&confid='+cid+'&page='+page;
            });	            
			closeDlog();
		}
		/*关闭弹出框*/
		function closeDlog(){
			$('#dialog_box').dialog("close");
		}
		
		/*全部添加*/
		function addall(){
			$("#leftUl img").attr("src",siteurl+"/images_gb/bot_mov.gif");
			$("#leftUl li").attr("onclick","rightClick(this)");
			var html = $("#leftUl").html();
			$("#rightUl").append(html);
			$("#leftUl").html("");
		}
		
		/*全部删除*/
		function delall(){
			$("#rightUl img").attr("src",siteurl+"/images_gb/bot_add.gif");
			$("#rightUl li").attr("onclick","leftClick(this)");
			var html = $("#rightUl").html();
			$("#leftUl").append(html);
			$("#rightUl").html("");
			
		}

		function confList(){
			location.href=siteurl+'/ConfPage.go?inc=ConfList';
		}

		/*预约人地址*/
		function usersfocus(th){
			if($(th).val()==emailmes||$(th).val()==""){
				$(th).val("");
				$(th).css("color","");
			}
		}
		/*预约人地址*/
		function usersblur(th){
			if($(th).val()==""||$(th).val()==emailmes){
				$(th).css("color","#bbbbbb");
				$(th).val(emailmes);
			}
		}
		
	</script>	