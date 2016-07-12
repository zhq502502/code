<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import=" com.seegle.form.ConfRoomActionForm"%>
<script type="text/javascript" src="help/js/jtip.js"></script>  
<script type="text/javascript" src="help/js/jquery.js"></script> 
<script type="text/javascript" src="js/changeType.js"></script> 
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.10.custom.min.js"></script>
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

String soft_version = request.getAttribute("soft_version").toString();
String siteurl = request.getSession().getAttribute("siteurl").toString();
ConfRoomActionForm MInfo = (ConfRoomActionForm) request.getAttribute("crf");
int islock = MInfo.getLockFlag();
int isclean = MInfo.getAutoClearFlag();
String isopen0 = MInfo.getOpenFlag() == 0 ? "checked" : "";
String isopen1 = MInfo.getOpenFlag() == 1 ? "checked" : "";
String issee0 = MInfo.getAll_can_visible() == 0 ? "checked" : "";
String issee1 = MInfo.getAll_can_visible() == 1 ? "checked" : "";
int ismod = MInfo.getDownload_mode();
int isaudit = MInfo.getOpen_audit();

String chkVideo = "";
String chkWbd = "";
String chkAppShare = "";
String chkMedia = "";
String chkText = "";
if(MInfo.getRecordMode()!=0){
	long numCheck0 = MInfo.getRecordMode()+2*(1<<30);
	int numCheck = (int)numCheck0;
	int i = numCheck&(1<<1);
	chkVideo = i==2 ? "checked" : "";
	i = numCheck&(1<<2);
	chkWbd = i==4 ? "checked" : "";
	i = numCheck&(1<<3);
	chkAppShare = i==8 ? "checked" : "";
	i = numCheck&(1<<7);
	chkMedia = i==128 ? "checked" : "";
	i = numCheck&(1<<8);
	chkText = i==256 ? "checked" : "";
}
                       
String confsetting = MInfo.getConfExtra();  
String datelang="";
datelang=language.replace('_', '-');
%>

<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001"><%=lu.getLanguage(language,"confedit.modifyconf","修改会议室信息") %></div>
			</div>
		<div id="tabs" class="tab_m">
			<ul style='background: #F1F6FD; border: 0'>
			<li><a href="#tabs-1"><%=lu.getLanguage(language,"confedit.confroominfo","会议室信息") %></a></li>
			<li onclick="return;"><a href="#" onclick="p_confcommon()"><%=lu.getLanguage(language,"confedit.confcommon","默认与会者") %></a></li>
			<li onclick="return;"><a href="#" onclick="p_confadmin()"><%=lu.getLanguage(language,"confedit.confadmin","视频会议管理员") %></a></li>
			</ul>
			<div style="border-bottom:1px #348CD4 solid;"></div>
			<div id="tabs-1" style="padding: 0px;">
				<p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>	
				<table width="100%"  cellpadding="0" cellspacing="0" class="tamnew3">
				<form name="form" id="form" action="#" method="post" autocomplete='off'> 
					<input type="hidden" name="lock_flag" id="lock_flag" value="<%=islock%>" />
					<input type="hidden" id="auto_clear_flag" name="auto_clear_flag" value="<%=isclean%>" />
					<input type="hidden" name="download_mode" id="download_mode" value="<%=ismod%>" />
					<input type="hidden" name="open_audit" id="open_audit" value="<%=isaudit%>" />
					<input type="hidden" name="confsetting" id="confsetting" value="<%=confsetting%>" />
					<tr>
				        <td width="18%" style="text-align: right;"><label for="confname"><%=lu.getLanguage(language,"confadd.confname","会议名称") %></label></td>
				       <td width="32%">
							<input name="confname" id="confname" size="22" type="text" class="Input02_w" value="${crf.confName}"/><font color="#FF0000">  *</font>
							<input name="cid" id="cid" size="22" type="hidden"  value="${crf.id}"/>
						</td>
				
				        <td width="18%" style="text-align: right;"><label for="btime"><%=lu.getLanguage(language,"confadd.btime","开始时间") %></label></td>
				       <td width="32%">
							<input name="btime" id="btime" size="22" type="text" class="Input02_w" value="${crf.confBeginTime}" readonly onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'etime\')}',isShowClear:false,readOnly:true,lang:'<%=datelang%>'})"/>
							<font color="#FF0000">  *</font>
						</td>
					</tr>
					<tr>
				       <td style="text-align: right;"><label for="confpass"><%=lu.getLanguage(language,"confadd.confpass","会议密码") %></label></td>
				       <td>
							<input name="confpass" id="confpass" size="22" type="password" class="Input02_w" value="${crf.confPasswordMd5}"/>
						</td>
				        <td style="text-align: right;"><label for="etime"><%=lu.getLanguage(language,"confadd.etime","结束时间") %></label></td>
				       <td>
							<input name="etime" id="etime" size="22" type="text" class="Input02_w" value="${crf.confEndTime}" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'btime\')}',readOnly:true,lang:'<%=datelang%>'})"/>
							<font color="#FF0000">  *</font>
						</td>
					</tr>
					<tr>
				       <td style="text-align: right;"><label for="confpass1"><%=lu.getLanguage(language,"confadd.confpass.confirmation","确认会议密码") %></label></td>
				       <td>
							<input name="confpass1" id="confpass1" size="22" type="password" class="Input02_w" value="${crf.confPasswordMd5}"/>
						</td>
				       <td style="text-align: right;"><label for="maxconfuser"><%=lu.getLanguage(language,"confadd.maxconfuser","最大与会人数") %></label></td>
				       <td>
							<input name="maxconfuser" id="maxconfuser" size="22" type="text" class="Input02_w"  value="${crf.maxConfUser}"/><font color="#FF0000">  *</font>
						</td>
					</tr>	
					<tr>
				       <td style="text-align: right;"><label for="managepass"><%=lu.getLanguage(language,"confadd.managepass","会议管理密码") %></label></td>
				       <td>
							<input name="managepass" id="managepass" size="22" type="password" class="Input02_w" value="${crf.managePasswordMd5}"/>
						</td>
				       <td style="text-align: right;"><label for="maxconfspokesman"><%=lu.getLanguage(language,"confadd.maxconfspokesman","最大主席人数") %></label></td>
				       <td>
							<input name="maxconfspokesman" id="maxconfspokesman" size="22" type="text" class="Input02_w"  value="${crf.maxConfSpokesman}"/><font color="#FF0000">  *</font>
						</td>
					</tr>	
					<tr>
				       <td style="text-align: right;"><label for="managepass1"><%=lu.getLanguage(language,"confadd.managepass.confirmation","确认管理密码") %></label></td>
				       <td>
							<input name="managepass1" id="managepass1" size="22" type="password" class="Input02_w" value="${crf.managePasswordMd5}"/>
						</td>
				       <td style="text-align: right;"><label for="maxconftourist"><%=lu.getLanguage(language,"confadd.maxconftourist","最大游客人数") %></label></td>
				       <td>
							<input name="maxconftourist" id="maxconftourist" size="22" type="text" class="Input02_w"  value="${crf.maxConfTourist}"/><font color="#FF0000">  *</font>
						</td>
					</tr>
					<tr>
				       <td style="text-align: right;"><label for="confgrouptype"><%=lu.getLanguage(language,"confadd.confgrouptype","会议集群类型") %></label></td>
                       <td>
			              <select id="grouptype" name="grouptype" class="Input02_w">
			                  <c:forEach var="ip" items="${groupList}">
			                      <c:if test="${ip.id==crf.conf_server_ip}">
                                    <option value="${ip.id}" selected>${ip.name}</option>
                                  </c:if>
                                  <c:if test="${ip.id!=crf.conf_server_ip}">
                                    <option value="${ip.id}">${ip.name}</option> 
                                  </c:if>	                                                      
                              </c:forEach>
			              </select><font color="#FF0000">  *</font>
		               </td>
		               <td style="text-align: right;"><label for="captcha"><%=lu.getLanguage(language,"phone.verifycode","验证码") %></label></td>
				       <td> 
				            <input type="hidden" name="c_btime" id="c_btime" value="${btime}"/>
				            <input type="hidden" name="c_etime" id="c_etime" value="${etime}"/>
				            <input type="hidden" name="codeid" id="codeid" value="${code.id}"/>
							<input name="captcha" id="captcha" size="15" type="text" class="Input02_d"  value="${code.code}" readonly="readonly" style="background-color:#dddddd"/><a href='#' id="rebuild"><%=lu.getLanguage(language,"confedit.rebuild","重新生成") %></a>
					   </td>
					</tr>
					<tr>
				       <td style="text-align: right;"><label><%=lu.getLanguage(language,"confadd.openflag","允许任何人参加") %></label></td>
				       <td><input type="radio"  name="open_flag" value="1"  <%=isopen1 %>/><%=lu.getLanguage(language,"button.yes","是") %>
				           <input type="radio"  name="open_flag" value="0"  <%=isopen0 %>/><%=lu.getLanguage(language,"button.no","否") %>
				           <span class="formInfo"><a href="help/Comein_<%=language %>.htm?width=350" class="jTip" id="one" name="<%=lu.getLanguage(language,"confadd.comein.illustration","允许任何人参加设置说明") %>">?</a></span>
				           </td>
					   <td style="text-align: right;"><label for="all_can_visible"><%=lu.getLanguage(language,"confadd.allcanvisible","所有人可见") %></label></td>
				       <td><input type="radio" id="all_can_visible" name="all_can_visible" value="0" <%=issee0 %> /><%=lu.getLanguage(language,"button.yes","是") %>
				           <input type="radio" id="all_can_visible" name="all_can_visible" value="1" <%=issee1 %>/><%=lu.getLanguage(language,"button.no","否") %>
				           <span class="formInfo"><a href="help/See_<%=language %>.htm?width=350" class="jTip" id="two" name="<%=lu.getLanguage(language,"confadd.see.illustration","所有人可见设置说明") %>">?</a></span>
				           </td>
					</tr>		

	<%
	if(soft_version.equals("购买版")){
	if(MInfo.getRecordMode()==0){
	%>
					<tr>
						<td style="text-align: right;"><%=lu.getLanguage(language,"confadd.autorecord","服务器自动录制") %></td>
				        <td colspan="3"><input type="radio" id="auto_record" name="auto_record"  onclick="autoRecord()" value="1"  /><%=lu.getLanguage(language,"button.yes","是") %>
				            <input type="radio" id="auto_record" name="auto_record" onclick="autoRecord()" value="0" checked=checked/><%=lu.getLanguage(language,"button.no","否") %></td>
				    </tr>
				    <tr>
						<td style="text-align: right;"><%=lu.getLanguage(language,"confadd.recordmodule","录制区域") %></td>
						<td colspan="3">
							<input type="checkbox" name="recordMode" id="sgRecordAudio" value="<%=1<<0 %>" checked disabled='disabled' style="background-color:#dddddd"/><%=lu.getLanguage(language,"confadd.recordaudio","音频") %>
							<input type="checkbox" name="recordMode" id="sgRecordVideo" value="<%=1<<1 %>" checked disabled style="background-color:#dddddd" /><%=lu.getLanguage(language,"confadd.recordvideo","视频") %>
							<input type="checkbox" name="recordMode" id="sgRecordWbd" value="<%=1<<2 %>" checked disabled style="background-color:#dddddd" /><%=lu.getLanguage(language,"confadd.recordwbd","电子白板") %>
							<input type="checkbox" name="recordMode" id="sgRecordAppShare" value="<%=1<<3 %>" checked disabled style="background-color:#dddddd" /><%=lu.getLanguage(language,"confadd.recordappshare","屏幕共享") %>
							<input type="checkbox" name="recordMode" id="sgRecordMedia" value="<%=1<<7 %>" checked disabled style="background-color:#dddddd" /><%=lu.getLanguage(language,"confadd.recordmedia","媒体播放") %>
							<input type="checkbox" name="recordMode" id="sgRecordText" value="<%=1<<8 %>" checked disabled style="background-color:#dddddd" /><%=lu.getLanguage(language,"confadd.recordtext","文字聊天") %></td>		           
					</tr>
	<%}else{ %>
					<tr>
						<td style="text-align: right;"><%=lu.getLanguage(language,"confadd.autorecord","服务器自动录制") %></td>
				        <td colspan="3"><input type="radio" id="auto_record" name="auto_record"  onclick="autoRecord()" value="1"  checked=checked/><%=lu.getLanguage(language,"button.yes","是") %>
				            <input type="radio" id="auto_record" name="auto_record" onclick="autoRecord()" value="0" /><%=lu.getLanguage(language,"button.no","否") %></td>
				    </tr>
				    <tr>
						<td style="text-align: right;"><%=lu.getLanguage(language,"confadd.recordmodule","录制区域") %></td>
						<td colspan="3">
							<input type="checkbox" name="recordMode" id="sgRecordAudio" value="<%=1<<0 %>" checked disabled='disabled' style="background-color:#dddddd"/><%=lu.getLanguage(language,"confadd.recordaudio","音频") %>
							<input type="checkbox" name="recordMode" id="sgRecordVideo" value="<%=1<<1 %>" <%=chkVideo %> /><%=lu.getLanguage(language,"confadd.recordvideo","视频") %>
							<input type="checkbox" name="recordMode" id="sgRecordWbd" value="<%=1<<2 %>" <%=chkWbd  %>  /><%=lu.getLanguage(language,"confadd.recordwbd","电子白板") %>
							<input type="checkbox" name="recordMode" id="sgRecordAppShare" value="<%=1<<3 %>" <%=chkAppShare %>  /><%=lu.getLanguage(language,"confadd.recordappshare","屏幕共享") %>
							<input type="checkbox" name="recordMode" id="sgRecordMedia" value="<%=1<<7 %>" <%=chkMedia %>  /><%=lu.getLanguage(language,"confadd.recordmedia","媒体播放") %>
							<input type="checkbox" name="recordMode" id="sgRecordText" value="<%=1<<8 %>" <%=chkText %>  /><%=lu.getLanguage(language,"confadd.recordtext","文字聊天") %></td>		           
					</tr>
	<%} }%>	
	
				    <tr>
				         <td style="text-align: right;"><%=lu.getLanguage(language,"confadd.description","会议描述") %></td>
				         <td colspan="3" style="text-align:left; padding-left:6px;"><textarea name="description" cols="60" rows="5" id="description" >${crf.description }</textarea><font color="#FF0000">&nbsp;&nbsp;</font></td>
				    </tr>								
					<tr>
						<td colspan="8" valign="middle" style="height:40px; text-align:center;">
							<input type="submit" id="btnSearch" value="<%=lu.getLanguage(language,"button.modify","修改") %>" class="sbutton" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    <input type="button" id="reset_btn" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" />
						</td>
					</tr>
					</form>	
					</table>
				</div><!--tab1完结-->
						
			</div><!--tabs完结-->
		</div><!--space_03完结-->
	</div><!--wrap完结-->
</div><!--wpbody-content完结-->
<script type="text/javascript" src="js/stringRes.js"></script>
	<script type="text/javascript">
	jQuery( "#tabs" ).tabs({cookie:{ expires: 1}});	
    function getValue(name){
	    var e=document.getElementsByName(name);
	    var item_id="";
	    for(var i=0;i<e.length;i++){ 
	      if(e[i].checked) 
	      item_id=e[i].value; 
	    }
	    return item_id;
  }
		$(document).ready(function() {
			//jQuery(".ui-tabs-selected a").click();
			$('#conf_manage').addClass("sel_tag");
			var siteurl = $('#siteurl').val();
			$('#form').submit(function(){
				var cid = $('#cid').val();
				var confname = $('#confname').val();
				var btime = $('#btime').val();
				var etime = $('#etime').val();
				var confpass = $('#confpass').val();
				var confpass1 = $('#confpass1').val();
				var maxconfuser = $('#maxconfuser').val();
				var managepass = $('#managepass').val();
				var managepass1 = $('#managepass1').val();
				var maxconfspokesman = $('#maxconfspokesman').val();
				var maxconftourist = $('#maxconftourist').val();
				var grouptype = $('#grouptype').val();
			    var numberRegex = /^[+]?\d+$/;
			    var cnameRegex = /^[a-zA-Z0-9\u0391-\uFFE5]+(([\-\_ ][a-zA-Z0-9\u0391-\uFFE5 ])?[a-zA-Z0-9\u0391-\uFFE5]*)*$/; 	
			    var open_flag = getValue("open_flag");			    			   
			    var all_can_visible = getValue("all_can_visible");	    
			    var description = $('#description').val();		    
			    var lock_flag = $('#lock_flag').val();
			    var auto_clear_flag = $('#auto_clear_flag').val();	   
			    var download_mode = $('#download_mode').val();
			    var open_audit = $('#open_audit').val();

		        var record_module = "data:";
		        var auto_record = getValue("auto_record");
		        var checkbox = document.getElementsByName("recordMode");
		        if(auto_record==1){
		            if(checkbox!=null){
		            	for (var i=0;i<checkbox.length;i++){
		            		if(checkbox[i].checked){
		            			record_module = record_module+checkbox[i].value+",";
		            		}
		            	}
	                } 
		        }
		        
                var confsetting = $('#confsetting').val();
			    	    
				if(confname==""){
					jQuery('#l_msg').text(getMsg(ERR_NO_CONFNAME));
					jQuery('#confname').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
			    if (!confname.match(cnameRegex)) {
			    	jQuery('#l_msg').text(getMsg(ERR_NON_STANDARD_CHARACTERS));
			    	jQuery('#confname').focus();
			    	jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
			        return false;
			    }
				if(confpass!=confpass1){
					jQuery('#l_msg').text(getMsg(ERR_CONFPASS_UNPAIR));
					jQuery('#confpass').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				} 
				if(managepass!=managepass1){
					jQuery('#l_msg').text(getMsg(ERR_MANAGEPASS_UNPAIR));
					jQuery('#managepass').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				if(maxconfuser==""){
					jQuery('#l_msg').text(getMsg(ERR_NO_MAXCONFUSER));
					jQuery('#maxconfuser').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				if(!maxconfuser.match(numberRegex)){
					jQuery('#l_msg').text(getMsg(ERR_MAXCONFUSER_ERROR));
					jQuery('#maxconfuser').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
			    if (!(maxconfuser>=1 & maxconfuser<=1000)){
			    	jQuery('#l_msg').text(getMsg(ERR_MAXCONFUSER_RENGE_ERROR));
			    	jQuery('#maxconfuser').focus();
			    	jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
			        return false;
			    }
				if(maxconfspokesman==""){
					jQuery('#l_msg').text(getMsg(ERR_NO_SPOKESMAN));
					jQuery('#maxconfspokesman').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				if(!maxconfspokesman.match(numberRegex)){
					jQuery('#l_msg').text(getMsg(ERR_SPOKESMAN_ERROR));
					jQuery('#maxconfspokesman').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
			    if (!(maxconfspokesman>=1 & maxconfspokesman<=20)){
			    	jQuery('#l_msg').text(getMsg(ERR_SPOKESMAN_RENGE_ERROR));
					jQuery('#maxconfspokesman').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
			        return false;
			    }
				if(Number(maxconfspokesman) > Number(maxconfuser)){
					jQuery('#l_msg').text(getMsg(ERR_SPOKESMAN_BIGER));
					jQuery('#maxconfspokesman').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				if(maxconftourist==""){
					jQuery('#l_msg').text(getMsg(ERR_NO_TOURIST));
					jQuery('#maxconftourist').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				if(!maxconftourist.match(numberRegex)){
					jQuery('#l_msg').text(getMsg(ERR_TOURIST_ERROR));
					jQuery('#maxconftourist').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
			    if (!(maxconftourist>=0 & maxconftourist<=1000)){
			    	jQuery('#l_msg').text(getMsg(ERR_TOURIST_RENGE_ERROR));
			    	jQuery('#maxconftourist').focus();
			    	jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
			        return false;
			    }
				if(Number(maxconftourist) > Number(maxconfuser)){
					jQuery('#l_msg').text(getMsg(ERR_TOURIST_BIGER));
					jQuery('#maxconftourist').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				if(description!=null&&description.length>100){
					jQuery('#l_msg').text(getMsg(ERR_DESCRIPTION_LENGTH));
					jQuery('#description').focus();
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}
				else {
					$("#l_msg").hide();
					$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
						if(rs==null||rs==""){
							alert(getMsg(ERR_CURRENT_USER_UNEXIST));
							location.href = siteurl+'/logout';
						}
						else if(rs==1 || rs==2){
							$.post(siteurl+"/conf/mod", {cid: cid, confname: confname, btime: btime, etime: etime, confpass: confpass, managepass: managepass, maxconfuser: maxconfuser, maxconftourist: maxconftourist, maxconfspokesman: maxconfspokesman, grouptype: grouptype, confpass: confpass, open_flag: open_flag, lock_flag: lock_flag, auto_clear_flag: auto_clear_flag, all_can_visible: all_can_visible, description: description,auto_record: auto_record, record_module: record_module, download_mode: download_mode, open_audit: open_audit, confsetting: confsetting, _: new Date().getTime() } ,function(rs) {
								if(rs==0){
									alert(getMsg(MOD_SUCESS));
									var page = "${nowPage}";
									location.href=siteurl+'/ConfPage.go?inc=ConfEdit&confid='+cid+'&page='+page;
								} 
								else {
									alert(getMsg(MOD_FAILURE));
									return false;
								}
							});
						} 
						else {
							alert(getMsg(ERR_NO_PERMISSION));
							//location.href = siteurl+'/ConfPage.go?inc=ConfList';
							location.href = siteurl+'/logout';
							return false;
						}
					});
					
					return false;
				}
				return false;
			});	
			
		 	$("#rebuild").click(function() {
				var cid = $('#cid').val();
				var codeid = $('#codeid').val();
				var c_btime = $('#c_btime').val();
				var c_etime = $('#c_etime').val();
				if(!(codeid==null||codeid=="")){
					var url = "PhoneVerifycodeAction.go?method=del"+"&r="+Math.random()+"&confid="+cid+"&codes="+codeid;
					//TODO 删除验证码代码
					$.get(url,function(msg){
						flag = msg;
						if(msg!=0){
							jQuery('#l_msg').text(getMsg(ERR_REBUILD_FAILURE));
							jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
							return false;
						}else{
							var url = "PhoneVerifycodeAction.go?method=add"+"&r="+Math.random()+"&confid="+cid+"&begintime="+c_btime+"&endtime="+c_etime+"&captchaString&type=1";
							//TODO 添加验证码代码
							$.get(url,function(msg){
								if(msg!=0){
									$('#codeid').val("");
									$('#captcha').val("");
									jQuery('#l_msg').text(getMsg(ERR_REBUILD_FAILURE));
									jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
									return false;
								}else{
									var url = "PhoneVerifycodeAction.go?method=list1"+"&r="+Math.random()+"&confid="+cid;
									//TODO 获取新添加验证码代码
									$.get(url,function(msg){
										var arr = new Array();
										arr = msg.split(";");
										$('#codeid').val(arr[0].substr(7));
										$('#captcha').val(arr[1].substr(5));
										document.getElementById("btnSearch").click();
									});
								}					
							});
						}
					});
				}else{
					var url = "PhoneVerifycodeAction.go?method=add"+"&r="+Math.random()+"&confid="+cid+"&begintime="+c_btime+"&endtime="+c_etime+"&captchaString&type=1";
					//TODO 添加验证码代码
					$.get(url,function(msg){
						if(msg!=0){
							$('#codeid').val("");
							$('#captcha').val("");
							jQuery('#l_msg').text(getMsg(ERR_REBUILD_FAILURE));
							jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
							return false;
						}else{
							var url = "PhoneVerifycodeAction.go?method=list1"+"&r="+Math.random()+"&confid="+cid;
							//TODO 获取新添加验证码代码
							$.get(url,function(msg){
								var arr = new Array();
								arr = msg.split(";");
								$('#codeid').val(arr[0].substr(7));
								$('#captcha').val(arr[1].substr(5));
								document.getElementById("btnSearch").click();
							});
						}					
					});
				}
			});
			
			$('#reset_btn').click(function(){ //取消按钮
				var page = "${nowPage}";
				location.href=siteurl+'/ConfPage.go?inc=ConfManage&page='+page;
			});
		});		
		
		
		function confcommon(){
			var siteurl = $('#siteurl').val();
			var cid = $('#cid').val();
			var page = "${nowPage}";
			location.href=siteurl+'/ConfPage.go?inc=ConfCommon&confid='+cid+'&page='+page;
		}
		function confadmin(){
			var siteurl = $('#siteurl').val();
			var cid = $('#cid').val();
			var page = "${nowPage}";
			location.href=siteurl+'/ConfPage.go?inc=ConfAdmin&confid='+cid+'&page='+page;
		}
	</script>
<%@include file="../plugin/depart/confuser.jsp" %>
