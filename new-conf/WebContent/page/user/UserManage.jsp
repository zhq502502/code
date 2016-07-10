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

String siteurl = request.getSession().getAttribute("siteurl").toString();
%>
	<div id="wpbody-content">
		<div class="wrap">
			<div class="space_03">
				<div id="topLinkL">
					<div class="s_001"><%=lu.getLanguage(language,"usermanage.accountlist","账号列表") %></div>
					<div class="s_002">
						<form action="UserManageAction.go" method='post' autocomplete='off' id="form">
							<input style="float: right" type="submit" value="" class="searchbutton" id='searchbutton' name='searchbutton' onClick="searchUser()" />
							<input style="float: right" type="text" id='searchKey' name="searchKey" maxlength='10' value="${Keyword}" class='Input088'> 
						</form>
					</div>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="tabnew" id='tabnew'>
					<thead>
						<tr><td colspan="4"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabtitle" id='tabtitle'>
						<tr>
							<td width="5%" class="tdTitle"></td>
							<td width="30%" class="tdTitle"><%=lu.getLanguage(language,"confsetmanager.account","账号") %></td>
							<td width="30%" class="tdTitle"><%=lu.getLanguage(language,"useradd.nickname","昵称") %></td>
							<td width="35%" class="tdTitle"><%=lu.getLanguage(language,"operation","操作") %></td>
						</tr>
						</table></td></tr>
					</thead>
					<tbody>
						<tr><td colspan="4"><table width="90%" border="0" cellpadding="0" cellspacing="0"
					class="tabcontent" id='tabcontent'>
						<c:if test="${fn:length(userList)==0}"><tr><td colspan="3"><%=lu.getLanguage(language,"list.norecord","没有记录") %></td></tr></c:if>	
						<c:forEach var="user" items="${userList}" varStatus="id">
						<tr id="${user.userId}">
							<td width="5%"><input name="usernames" type="checkbox" class="checkchil" value="${user.userName}"/></td>
							<td width="30%">${user.userName}</td>
							<td width="30%">${user.alias}</td>
							<td width="35%">
							<img src='images_gb/edt.gif' width='16' height='16' class='img_tab' /><a class='editlink' href="<%=siteurl %>/ConfPage.go?inc=UserEdit&account=${user.userName}&page=${nowPage}"><%=lu.getLanguage(language,"confmanage.edit","编辑") %></a>  
							<img src='images_gb/del.gif' width='16' height='16' class='img_tab' /><a class='delete' href='javascript:void(0);'><%=lu.getLanguage(language,"button.delete","删除") %></a></td>
						</tr>
						</c:forEach>
						</table></td></tr>
					</tbody>
				</table>
				<div class="s_004"><input type="checkbox" id="checkall"/>&nbsp;<span style="color:#717171;"><%=lu.getLanguage(language,"button.selectall","全选") %></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="sbutton" type="button" onclick="delall()" value="<%=lu.getLanguage(language,"button.deletebatch","批量删除") %>"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="sbutton" type="button" onclick="editall()" value="<%=lu.getLanguage(language,"button.modbatch","批量修改") %>"/>
				</div>
				<div class="sabrosus">
                    <c:if test="${nowPage==1||pageNumber==1}">
                                <span class="disabled"> <<</span>
                                <span class="disabled"> <</span>
                    </c:if>
                    <c:if test="${nowPage>1}">
                        <c:choose>
                            <c:when test="${Keyword!=''}">
                                <a href="UserSelectAction.go?page=1&Keyword=${Keyword}"><<</a>
                                <a href="UserSelectAction.go?page=${nowPage-1}&Keyword=${Keyword}"><</a>
                            </c:when>
                            <c:otherwise>
                                <a href="UserManageAction.go?page=1"><<</a>
                                <a href="UserManageAction.go?page=${nowPage-1}"><</a>
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
                                    			out.println("<a href=UserSelectAction.go?Keyword=" + Keyword + "&page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                		else {
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=UserManageAction.go?page=" + i + ">" + i + "</a>");
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
                                    			out.println("<a href=UserSelectAction.go?Keyword=" + Keyword + "&page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                		else {
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=UserManageAction.go?page=" + i + ">" + i + "</a>");
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
                                    			out.println("<a href=UserSelectAction.go?Keyword=" + Keyword + "&page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                		else {
                                			if(i==nowNumber){
                                				out.println("<span class=\'current\'> "+i+"</span>");
                                    		}else{
                                    			out.println("<a href=UserManageAction.go?page=" + i + ">" + i + "</a>");
                                    		}
                                		}
                                	}                                	
                                }
%>
                    <c:if test="${nowPage!=pageNumber}">
                        <c:choose>
                            <c:when test="${Keyword!=''}">
                                <a href="UserSelectAction.go?page=${nowPage+1}&Keyword=${Keyword}">></a>
                                <a href="UserSelectAction.go?page=${pageNumber}&Keyword=${Keyword}">>></a>
                                ${nowPage}/${pageNumber}
                            </c:when>
                            <c:otherwise>
                                <a href="UserManageAction.go?page=${nowPage+1}">></a>
                                <a href="UserManageAction.go?page=${pageNumber}">>></a>
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
                <br>
			</div>
		</div>
		<br class="clear" />
		<div class="clear"></div>
	<div id='dialog_box' style='display: none;height: 330px; outline: 0px none; width: 410px; overflow: visible;background: #F1F6FD;'>
	<div class='message'>
		<div style="width: 360px;height: 260px;">
		<div id="" style="width: 100%">
		   <p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000;margin: 0;border: 0"></p>
		</div>	
			<table border="0" cellpadding="0" cellspacing="0" class="tamnew5" style="margin-right: 0px; margin-top: 0px; margin-bottom: 0px;">
				<tr>
		            <td style="text-align: right;"><label for="userpass"><%=lu.getLanguage(language,"useradd.userpass","账号密码") %></label></td>
				    <td><input name="userpass" id="userpass" size="22" type="password" class="Input02_w"/></td>
			      </tr>
			      <tr>
		            <td style="text-align: right;"><label for="userpass1"><%=lu.getLanguage(language,"useradd.userpass.confirmation","密码确认") %></label></td>
				    <td><input name="userpass1" id="userpass1" size="22" type="password" class="Input02_w"/></td>
			      </tr>
			      <tr>
					<td style="text-align: right;"><label for="proxytype"><%=lu.getLanguage(language,"useradd.setproxytype","设置代理类型") %></label></td>
					<td><select  name="proxytype" id="proxytype" class="select" onchange="changeproxy(this)" >
							<option value="0">--<%=lu.getLanguage(language,"useradd.noproxy","不使用代理") %>--</option>
							<option value="1">SOCKS 4</option>
							<option value="2">SOCKS 5</option>
							<option value="3">HTTP</option>
							</select></td>
				</tr>
				<tr>
					<td style="text-align: right;"><label for="proxyaddr"><%=lu.getLanguage(language,"useradd.proxyaddr","代理服务器地址") %></label></td>
					<td><input name="proxyaddr" id="proxyaddr" size="22" type="text" class="Input02_w" disabled style="background-color:#dddddd"/><font color="#ff0000">&nbsp;&nbsp;</font>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
					<label for="proxyport"><%=lu.getLanguage(language,"useradd.proxyport","代理服务器端口") %></label>
					</td>
					<td><input name="proxyport" id="proxyport" size="22" type="text" class="Input02_w" disabled style="background-color:#dddddd"/><font color="#ff0000">&nbsp;&nbsp;</font></td>
				</tr>
				<tr>
					<td style="text-align: right;"><label for="proxyuser"><%=lu.getLanguage(language,"useradd.proxyuser","代理登录用户名") %></label></td>
					<td><input name="proxyuser" id="proxyuser" size="22" type="text" class="Input02_w" disabled style="background-color:#dddddd"/></td>
				</tr>
				<tr>
					<td style="text-align: right;">
					<label for="proxypass"><%=lu.getLanguage(language,"useradd.proxypass","代理登录用户密码") %></label>
					</td>
					<td><input name="proxypass" id="proxypass" size="22" type="password" class="Input02_w" disabled style="background-color:#dddddd"/></td>
				</tr>
			</table>
		</div>
		<br class="clear" />
		<div align="right">
		<input type="button" value="<%=lu.getLanguage(language,"button.submit","确定") %>" class="sbutton" onclick="saveAll(this)" />
		<input type="button" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" onclick="closeDialog()" />
		</div>
	</div>	
	
	</div>
	<!-- wpbody-content -->
    <script type="text/javascript" src="js/stringRes.js"></script>
    <script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
<script type="text/javascript">
	function searchUser(){
	    var Keyword = document.forms[0].searchKey.value;
	    document.forms[0].action = "UserSelectAction.go?Keyword="+Keyword;
	    document.forms[0].submit();
	}
		$(document).ready(function() {
			$("#searchKey").focus();
	    	$("#searchKey").keydown(function(event){
	        	if(event.keyCode==13){
	        		searchUser();
		        	return false;
	        	}
	        });
	    	
			$('#user_usermanager').addClass("sel_tag");
			var siteurl = $('#siteurl').val();
			
			$('.editlink').click(function(){
				$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
					if(rs==null||rs==""){
						alert(getMsg(ERR_CURRENT_USER_UNEXIST));
						location.href = siteurl+'/logout';
					}
					else if(rs==1 || rs==3){
						return true;	
					} else {
						alert(getMsg(ERR_NO_PERMISSION));
						//location.href = siteurl+'/ConfPage.go?inc=ConfList';
						location.href = siteurl+'/logout';
						return false;
					}
				});
			});
			
			$('.delete').click(function(){
				var account = $(this).parent('td').prev().prev().text();
				if(confirm(getMsg(CONFIRM_DELETE))){
					$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
						if(rs==null||rs==""){
							alert(getMsg(ERR_CURRENT_USER_UNEXIST));
							location.href = siteurl+'/logout';
						}
						else if(rs==1 || rs==3){
							$.post(siteurl+"/user/update", { action:"delete", account: account, _: new Date().getTime() } ,function(rs1) {
								if(rs1==0){
									alert(getMsg(DELETE_SUCESS));
									var page = "${nowPage}";
									location.href=siteurl+'/ConfPage.go?inc=UserManage&page='+page;
								} else {
									alert(getMsg(DELETE_FAILURE));
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
			});
			/*全选框点击*/
			$("#checkall").bind("click",function(){
				if($("#checkall").attr("checked")==true){
					$(":checkbox[name='usernames']").attr("checked",true);
				}else{
					$(":checkbox[name='usernames']").attr("checked",false);
				}
			});
			
		});
		/*批量删除*/
		function delall(){
			var siteurl = $('#siteurl').val();
			var checkeds = $(":checkbox[name='usernames']:checked");
			var usernames="";
			checkeds.each(function(i,n){
				usernames+=","+$(this).val();
			})
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
					else if(rs==1 || rs==3){
						$.post(siteurl+"/user/update", { action:"delall", accounts: usernames, _: new Date().getTime() } ,function(rs1) {
							if(rs1==0){
								alert(getMsg(DELETE_ALL_SUCESS));
								location.href=siteurl+'/ConfPage.go?inc=UserManage';
							} else {
								alert(getMsg(DELETE_ALL_FAILURE));
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

		/*选择用户*/
		function editall(){
			var checkeds = $(":checkbox[name='usernames']:checked");
			var usernames="";
			checkeds.each(function(i,n){
				usernames+=","+$(this).val();
			})
			usernames=usernames.substring(1,usernames.length);
			if(usernames==""){
				alert(getMsg(ERR_NO_USER_SELECTED));
				return false;
			}
			$('#dialog_box').dialog({
				modal:true,
				position:"20%",
				width:455,
				height:330,
				title:"<span style='color:#FFFFFF;'><%=lu.getLanguage(language,"button.modbatch","批量修改") %></span>"
				
			});
			$("#ui-dialog-title-dialog_box").css({"height":"25px","float":"left","padding":"6px 0 0 6px"});
			$(".ui-dialog-titlebar-close").css("float","right");
			$(".ui-dialog-titlebar-close").css({"width":"16px","height":"16px","margin":"6px 6px 0 0"});
			$(".ui-dialog-titlebar").css("background-image","");
			$(".ui-dialog-titlebar").css("background-color","#348CD4");
			$("#dialog_box").css("border","0");
			//关闭弹层
			$('#dialog_cancel1').click(function(){
				$('#dialog_box').dialog("close");
			}); 
		};
		/*关闭dialog*/
		function closeDialog(){
			$('#dialog_box').dialog("close");
		}
		/*代理类型更改*/
		function changeproxy(th) {
		    var proxytype = $(th).val();
		    var table = $(th).parent().parent().parent();
		    if (proxytype == 0){
		    	table.find("#proxyaddr").val("");
		    	table.find("#proxyport").val("");
		    	table.find("#proxyuser").val("");
		    	table.find("#proxypass").val("");
		    	table.find("#proxyaddr").attr("disabled",true);
		    	table.find("#proxyuser").attr("disabled",true);
		    	table.find("#proxyport").attr("disabled",true);
		    	table.find("#proxypass").attr("disabled",true);
		    	table.find("#proxyaddr").css({"backgroundColor":"#dddddd"});
		    	table.find("#proxyuser").css({"backgroundColor":"#dddddd"});
		    	table.find("#proxyport").css({"backgroundColor":"#dddddd"});
		    	table.find("#proxypass").css({"backgroundColor":"#dddddd"});
		    }
		    else{
		    	table.find("#proxyaddr").removeAttr("disabled");
		    	table.find("#proxyuser").removeAttr("disabled");
		    	table.find("#proxyport").removeAttr("disabled");
		    	table.find("#proxypass").removeAttr("disabled");
		    	table.find("#proxyaddr").css({"backgroundColor":"#ffffff"});
		    	table.find("#proxyuser").css({"backgroundColor":"#ffffff"});
		    	table.find("#proxyport").css({"backgroundColor":"#ffffff"});
		    	table.find("#proxypass").css({"backgroundColor":"#ffffff"});
		    }
		}

		/*保存批量更新*/
		function saveAll(th){
			var siteurl = $('#siteurl').val();
			var checkeds = $(":checkbox[name='usernames']:checked");
			var usernames="";
			checkeds.each(function(i,n){
				usernames+=","+$(this).val();
			})
			usernames=usernames.substring(1,usernames.length);
			var table = $(th).parent().parent();
			var userpass = table.find('#userpass').val();	
			var userpass1 = table.find('#userpass1').val();	
		    var proxyaddr = table.find('#proxyaddr').val();
		    var proxytype = table.find('#proxytype').val();
		    var proxyport = table.find('#proxyport').val();
		    var proxyuser = table.find('#proxyuser').val();
		    var proxypass = table.find('#proxypass').val();
		    var accountRegex = /^([1-9]|[1-9][0-9]|[1-9][0-9][0-9]|(999))$/;  //1~999
		    var portRegex = /^(6553[0-5]|655[0-2]\d|65[0-4]\d\d|6[0-4]\d{3}|[1-5]\d{4}|[1-9]\d{0,3}|0)$/;
		    var proxIPRegex = /^\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b$/;
		    if(usernames==""){
		    	$('#l_msg').text(getMsg(ERR_NO_USER_SELECTED));
				$('#account').focus();
				$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
	            return false;
		    }
		    if(userpass!=userpass1){
				$('#l_msg').text(getMsg(REG_PASSWORD_UNPAIR));
				$('#userpass').focus();
				$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
				return false;
			}
		    if (proxytype != '0'&&proxyaddr == "") {
	        	$('#l_msg').text(getMsg(ERR_NO_PROXY_ADDRESS));
				$('#proxyaddr').focus();
				$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
	            return false;
	        }
	        if (proxytype != '0'&&!proxyaddr.match(proxIPRegex)) {
	        	$('#l_msg').text(getMsg(ERR_PROXY_ADDRESS_ERROR));
				$('#proxyaddr').focus();
				$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
	            return false;
	        }
	        if (proxytype != '0'&&proxyport == "") {
	        	$('#l_msg').text(getMsg(ERR_N0_PROXY_PORT));
				$('#proxyport').focus();
				$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
	            return false;
	        }
	        if (proxytype != '0'&&!proxyport.match(portRegex)) {
	        	$('#l_msg').text(getMsg(ERR_PORT_ERROR));
				$('#proxyport').focus();
				$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
	            return false;
	        }
		    else {
		    	$("#l_msg").hide();
				$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
					if(rs==null||rs==""){
						alert(getMsg(ERR_CURRENT_USER_UNEXIST));
						location.href = siteurl+'/logout';
					}
					else if(rs==1 || rs==3){
						$.post(siteurl+"/user/update", {action:'editall',usernames: usernames, userpass: userpass,proxytype: proxytype,proxyaddr: proxyaddr,proxyport: proxyport,proxyuser: proxyuser,proxypass: proxypass, _: new Date().getTime() } ,function(rs1) {
							if(rs1==0){
								alert(getMsg(UPDATE_ALL_SUCESS));
								location.href=siteurl+'/ConfPage.go?inc=UserManage';
							} 
							else {
								alert(getMsg(UPDATE_ALL_FAILURE));
								return false;
							}
						});	
					} else {
						alert(getMsg(ERR_NO_PERMISSION));
						location.href = siteurl+'/logout';
						return false;
					}
				});
				return false;
			}
			return false;
		}
		
</script>