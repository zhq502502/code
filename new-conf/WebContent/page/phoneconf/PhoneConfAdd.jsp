<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="seegle"  uri="/WEB-INF/taglib/seegle.tld" %>
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
String datelang="";
datelang=language.replace('_', '-');
%>
<body >
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001"><span id="pagetitle"><%=lu.getLanguage(language,"phone.addphoneconf","新建电话会议") %></span></div>
			</div>
		<div id="uploadTip">
		   <p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
		</div>	
		<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD" class="tamnew4" id="confinfo_table">
		<form name="form" id="form" action="#" method="post" autocomplete='off'>
		<input name="conftype" type="hidden" value="${conftype}"/>
		<input name="room_password" id="room_password" size="22" type="hidden" class="Input02_w" />
		<input id="room_password1" size="22" type="hidden" class="Input02_w" />
		<input name="manager_password" id="manager_password" size="22" type="hidden" class="Input02_w" />
		<input id="manager_password1" size="22" type="hidden" class="Input02_w" />
		<input id="confid" name="confid" type="hidden" value="${conf.id}">
		<input id="confdesc" name="confdesc" type="hidden" value="${conf.description}">
		<tr>
	       <td width="18%" style="text-align: right;"><label for="confname"><%=lu.getLanguage(language,"confadd.confname","会议名称") %></label></td>
	       <td width="28%">
				<input name="confname" id="confname" size="22" type="text" class="Input02_w" /><font color="#FF0000">  *</font>
			</td>
	        <td width="14%" style="text-align: right;"><label for="btime"><%=lu.getLanguage(language,"confadd.btime","开始时间") %></label></td>
	       <td width="40%">
	       		<input value='${nowtime }' name="begintime" type="text" id="btime" size="22" class="Input02_w" readonly onclick="selectBtime()"/>
				<font color="#FF0000">  *</font>
			</td>
		</tr>
		<tr>
	       <td width="18%" style="text-align: right;"><label for="confname"><%=lu.getLanguage(language,"phone.maxuser","最大人数") %></label></td>
	       <td width="28%">
	       		<input value='' name="max_user_count" type="text" id="max_user_count" size="22" value="10" class="Input02_w" />
				<font color="#FF0000">  *</font>
			</td>
	        <td width="14%" style="text-align: right;"><label for="btime"><%=lu.getLanguage(language,"confadd.etime","结束时间") %></label></td>
	       <td width="40%">
	       		<input value='${nexttime }' name="endtime" type="text" id="etime" size="22" class="Input02_w" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'mintime\')}',readOnly:true,lang:'<%=datelang%>'})"/>
	       		<input type="hidden" id="nowtime" value='${nowtime }'/>
            	<input type="hidden" id="mintime" value='${nowtime }'/>
				<font color="#FF0000">  *</font>
			</td>
		</tr>
		<tr>
			<td width="" style="text-align: right;"><label for="confname"><%=lu.getLanguage(language,"phone.isdisplay","是否显示验证码") %></label></td>
	       <td width="" colspan="1">
				<select class="Input02_w" name="verifycodevisible" />
					<option value="1"><%=lu.getLanguage(language,"phone.notdisplay","不显示") %></option>
					<option value="0"><%=lu.getLanguage(language,"phone.display","显示") %></option>
				</select>
			</td>
	       <td width="" style="text-align: right;"><label for="confname"><c:if test="${conftype==1}"><%=lu.getLanguage(language,"phone.bindconf","绑定视频会议") %></c:if></label></td>
	       <td width="" colspan="1">
				<c:if test="${conftype==1}">
					<select name="vop_conf_id">
						<option value="102760">123</option>
					</select>
				</c:if></label>
			</td>
		</tr>
		<tr>
	       <td width="18%" style="text-align: right;"><label for="confname"><%=lu.getLanguage(language,"phone.logindevice","接入设备") %></label></td>
	       <td width="28%">
				<select id="facility" class="Input02_w" name="srv_id">
				<c:forEach items="${facilityList}" var="fac">
					<option value="${fac.id}" joinnumber="${fac.joinnumber}">${fac.srvname}</option>
				</c:forEach>
				</select>
			</td>
			<td width="14%" style="text-align: right;"><label for="btime"><%=lu.getLanguage(language,"phone.loginnumber","接入号码") %></label></td>
	       <td width="40%">
	       		<select id="joinnumber" class="Input02_w" name="join_number">
				
				</select>
			</td>
		</tr>
		<tr>
	       	<td width="18%" style="text-align: right;"><label for="confname"><%=lu.getLanguage(language,"confadd.description","会议描述") %></label></td>
	       	<td colspan="3" style="text-align:left; padding-left:6px;">
	       		<textarea name="description" cols="60" rows="5" id="description">${conf.description}</textarea><font color="#FF0000">&nbsp;&nbsp;</font>
			</td>
		</tr>
		<tr>
			<td colspan="4" valign="middle" style="height:40px; text-align:center;">
				<input type="button" id="btnSearch" value="<%=lu.getLanguage(language,"button.add","添加") %>" class="sbutton" onclick="sub()"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    <input type="button" id="reset_btn" onclick="javascript:history.go(-1);" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" />
			</td>
		</tr>	
		</form>	
		</table>
		</div>
	</div>
</div>
</body>
<script src="js/date/WdatePicker.js"></script>
<script type="text/javascript" src="js/stringRes.js"></script>
<script type="text/javascript" >
var url_phonetype = "PublicPhoneconf";
var top = "${top}";
$(function(){
	joinChange();
	$("#facility").change(function(){
		joinChange();
	});
	if("${conftype}"=="0"){
		if("${otherorgid}"==""||"${otherorgid}"=="0"){
			$('#phoneconf_public').addClass("sel_tag");
		}else{
			$('#otherphoneconf_public').addClass("sel_tag");
		}
	}else if("${conftype}"=="1"){
		if("${otherorgid}"==""||"${otherorgid}"=="0"){
			$('#phoneconf_bind').addClass("sel_tag");
		}
		url_phonetype = "BindPhoneconf";
	}else if("${conftype}"=="2"){
		if("${otherorgid}"==""||"${otherorgid}"=="0"){
			$('#phoneconf_user').addClass("sel_tag");
		}
		url_phonetype = "UserPhoneconf";
	}else if("${conftype}"=="3"){
		if("${otherorgid}"==""||"${otherorgid}"=="0"){
			$('#phoneconf_alluser').addClass("sel_tag");
		}else{
			$('#otherphoneconf_alluser').addClass("sel_tag");
		}
		url_phonetype = "AllUserPhoneconf";
	}
	url_phonetype+="&top=${top}";
	if(!"${otherorgid}"==""){
		url_phonetype+="&otherorgid=${otherorgid}";
	}
	initValue();
})
function joinChange(){
	var joinnumber = $("#facility option:selected").attr("joinnumber");
	var numbers = joinnumber.split(";");
	$("#joinnumber").html("");
	for(var i =0;i<numbers.length;i++ ){
		var html = "<option value='"+numbers[i]+"'>"+numbers[i]+"</option>";
		$("#joinnumber").append(html);
	}
}
function selectBtime(){
	WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'etime\')}',isShowClear:false,readOnly:true,lang:'<%=datelang%>'});
		changeTime();		
	}
function changeTime(){
	var btime1 = $('#btime').val();
	var nowtime = $('#nowtime').val();
	var date=Date.parse(btime1.replace(/-/g,"/"));
	var now=Date.parse(nowtime.replace(/-/g,"/"));
	if(date>now){
		$('#mintime').val(btime1);
	}else{
		$('#mintime').val(nowtime);
		}		
	}
  function getValue(name){
	    var e=document.getElementsByName(name);
	    var item_id="";
	    for(var i=0;i<e.length;i++){ 
	      if(e[i].checked) 
	      item_id=e[i].value; 
	    }
	    return item_id;
}
function sub(){
	var numberRegex = /^[+]?\d+$/;
    var cnameRegex = /^[a-zA-Z0-9\u0391-\uFFE5]+(([\-\_ ][a-zA-Z0-9\u0391-\uFFE5 ])?[a-zA-Z0-9\u0391-\uFFE5]*)*$/; 
    var confname = $("#confname").val();
    var confpass = $("#room_password").val();
    var confpass1 = $("#room_password1").val();
    var managepass = $("#manager_password").val();
    var managepass1 = $("#manager_password1").val();
    var maxconfuser = $("#max_user_count").val();
    if(confname==""){
		jQuery('#l_msg').text(getMsg(ERR_NO_CONFNAME));
		jQuery('#confname').focus();
		jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		return false;
	}
    /*if (!confname.match(cnameRegex)) {
    	jQuery('#l_msg').text(getMsg(ERR_NON_STANDARD_CHARACTERS));
    	jQuery('#confname').focus();
    	jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
        return false;
    }*/
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
	var url = "PhoneconfAction.go?method=add&top=${top}&r="+Math.random();
	var confid = $("input[name='confid']").val();
	if(!(confid==null||confid=="")){
		url = "PhoneconfAction.go?method=edit&top=${top}&r="+Math.random();
	}
	$("#confinfo_table input[name],textarea[name],select[name]").each(function(n,i){
		url+="&"+$(this).attr("name")+"="+encodeURIComponent($(this).val());
	})
	$.get(url,function(msg){
		alert(errorMap.get(msg+""));
		if(msg==0){
			if(top=="top"){
				location.href="TopConfPage.go?inc="+url_phonetype;
			}else{
				location.href="ConfPage.go?inc="+url_phonetype;
			}
		}
	})
}
/**
 * 更新操作初始化值
 */
function initValue(){
	var confid = $("input[name='confid']").val();
	if(!(confid==null||confid=="")){
		$("#pagetitle").html(getMsg(EDIT_PHONE_CONF));  
		$("input[name='confname']").val("${conf.confName}");
		$("input[name='begintime']").val("${conf.beginTime}");
		$("input[name='room_password']").val("password");
		$("input[id='room_password1']").val("password");
		$("input[id='room_password1']").after("<input name='room_password2' type='hidden' value='${conf.roomPassword}' />")
		$("input[name='endtime']").val("${conf.endTime}");
		$("input[name='max_user_count']").val("${conf.maxUserCount}");
		$("input[name='manager_password']").val("password");
		$("input[id='manager_password1']").val("password");
		$("input[id='manager_password1']").after("<input name='manager_password2' type='hidden' value='${conf.managerPassword}' />")
		$("select[name='verifycodevisible']").val("${conf.verifyCodeVisible}");
		$("select[name='vop_conf_id']").val("${conf.vopConfId}");
		$("select[name='srv_id']").val("${conf.srvId}");
		$("select[name='srv_id'] option[value='${conf.srvId}']").attr("selected","selected");
		joinChange();
		$("select[name='join_number']").val("${conf.joinNumber}");
		$("textarea[name='description']").val($("input[name='confdesc']").val());
		$("#btnSearch").val(getMsg(UPDATE));
	}
}
</script>