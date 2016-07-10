<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.seegle.util.PropUtil"%>
<%@page import="com.seegle.util.LanguageUtil"%>
<%@page import="com.seegle.data.GetCookies"%>
<% 
//获取语言
String language=""; 
Cookie cookies[]=request.getCookies(); 
GetCookies getCK = new GetCookies();
language = getCK.getCookie(cookies, "SGlanguage");  //获取cookie中的SGlanguage，zh-cn 简体中文； zh-tw 繁体中文；en 英文
if(language==null || "".equals(language)){ //如果cookie中没有SGlanguage，从配置中读取默认语言
	if(PropUtil.getInstance().getValue("language")!=null){
		language=PropUtil.getInstance().getValue("language"); //zh-cn 简体中文； zh-tw 繁体中文；en 英文	
	}
}
LanguageUtil lu = new LanguageUtil();
%>
<script type="text/javascript" src="js/changeType.js"></script> 
<style>
#tab-recovery li{
	color:#666666;
	padding: 3px;
}
#tab-recovery li span{
	cursor: pointer;
}
</style>
<body >
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001"><%=lu.getLanguage(language,"backup.backuporrecovery","备份/还原") %></div>
			</div>
			
			<div id="tabs" class="tab_m ui-tabs ui-widget ui-widget-content ui-corner-all">
				<ul style='background: #F1F6FD; border: 0'>
				<li><a href="#tabs-21"><%=lu.getLanguage(language,"backup.backuplist","备份列表") %></a></li>
				<li><a href="#tabs-22"><%=lu.getLanguage(language,"backup.backuptitle","数据备份") %></a></li>
				</ul>
				<div style="border-bottom:1px #348CD4 solid;"></div>
			
				
				<div id="tabs-21">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="tabnew" id='tab-recovery' style="max-height: 600px;overflow-y:scroll; ">
						<thead>
						<tr class="tabtitle">
							<td width="40%" align="center"><%=lu.getLanguage(language,"backup.backupname","备份名称") %></td>
							<td width="30%" align="center"><%=lu.getLanguage(language,"backup.filesize","文件大小") %></td>
							<td width="30%" align="center"><%=lu.getLanguage(language,"operation","操作") %></td>
						</tr>
						</thead>
						<c:forEach items="${backuplist}" var="backup" >
						<tr>
							<td width="40%">${backup.backname}</td>
							<td width="30%">${backup.usablespace}</td>
							<td width="30%">
								<img class="img_tab" width="16" height="16" src="images_gb/download.gif">
								<a href="javascript:download('${backup.backname}')"><%=lu.getLanguage(language,"download.button.down","下载") %></a>
								&nbsp;
								<img class="img_tab" width="16" height="16" src="images_gb/back.gif">
								<a href="javascript:recovery('${backup.backname}')"><%=lu.getLanguage(language,"backup.recovery","还原") %></a>
								&nbsp;
								<img class="img_tab" width="16" height="16" src="images_gb/del.gif">
								<a href="javascript:del('${backup.backname}')"><%=lu.getLanguage(language,"button.delete","删除") %></a>
							</td>
						</tr>	
						</c:forEach>
					</table>
				</div>
				
				<div id="tabs-22">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="tamnew" id='tab-backup'>
						<tr>
							<td>
								<input name="backtype" value="0" type="radio" checked="checked" /><%=lu.getLanguage(language,"backup.savetoser","保存到服务器") %>
								<input name="backtype" value="1" type="radio" /><%=lu.getLanguage(language,"backup.savetolocal","保存到本地") %>
							</td>
							<td>
								<input type="button" id="butbackup" onclick="backup();" value="<%=lu.getLanguage(language,"backup.backup","备份") %>" class="sbutton" />
							
							</td>
						</tr>	
					</table>
				</div>
			</div>
			
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="js/stringRes.js"></script>
<script src="js/date/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.10.custom.min.js"></script>
<script type="text/javascript">
$( "#tabs" ).tabs({cookie:{ expires: 21}});
$("#tab-recovery input[name='backupname']").each(function(i,n){
	if(i==0){
		$(this).attr("checked",true);
	}
})
$("#tab-recovery li span").bind("click",function(){
	$(this).prev().attr("checked",true);
})
/**
 * 还原
 */
function recovery(backupname){
	var url = "BackupAction.go?method=recovery&backupname="+backupname;
	$.getJSON(url,function(data){
		alert(errorMap.get(data+""));
		location.href=window.location.href;
	})
}
/**
 * 备份
 */
function backup(){
	var backtype = $("#tab-backup input[name='backtype']:checked").val();
	var url = "BackupAction.go?method=backup&backtype="+backtype;
	$("#butbackup").addClass("unclickbut");
	$("#butbackup").removeClass("sbutton");
	if(backtype+""=="1"){
		window.open(url);
		$("#butbackup").removeClass("unclickbut");
		$("#butbackup").addClass("sbutton");
		return ;
	}
	$.getJSON(url,function(data){
		alert(errorMap.get(data+""));
		$("#butbackup").removeClass("unclickbut");
		$("#butbackup").addClass("sbutton");
		location.href=window.location.href;
	})
}
/**
 * 删除备份文件
 */
function del(backupname){
	var url = "BackupAction.go?method=delete&backupname="+backupname;
	if(confirm(getMsg(CONFIRM_DELETE))){
		$.getJSON(url,function(data){
			alert(errorMap.get(data+""));
			location.href=window.location.href;
		})
	}
}
function download(backupname){
	var url = "BackupAction.go?method=download&backupname="+backupname;
	window.open(url);
}
	
</script>