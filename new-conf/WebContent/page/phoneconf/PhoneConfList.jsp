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
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/stringRes.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.10.custom.min.js"></script>

<script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="js/base64.js"></script>
<body>
<div id="wpbody-content">
 <form name="form" id="form" action="PhoneconfAction.go" method="post">   					
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001">
				<%
					String phoneTitle = request.getAttribute("phoneTitle").toString();
					if(phoneTitle.equals("个人电话会议")){
						phoneTitle = lu.getLanguage(language,"confpage.menu.phoneconf.personal","个人电话会议");
					}else if(phoneTitle.equals("公共电话会议")){
						phoneTitle = lu.getLanguage(language,"confpage.menu.phoneconf.public","公共电话会议");
					}else if(phoneTitle.equals("绑定电话会议")){
						phoneTitle = lu.getLanguage(language,"confpage.menu.phoneconf.binding","绑定电话会议");
					}else{
						phoneTitle = lu.getLanguage(language,"confpage.menu.phoneconf.allpersonal","所有个人电话");
					}
					out.print(phoneTitle);
				%>
				</div>
				<c:if test="${((sessionScope.userid eq 1000 ||isserveruser eq 1)&&orgid eq 90000)&&(!empty otherorgid&&otherorgid != 0)}">
				<div class="s_002" >
						<%=lu.getLanguage(language,"phonelog.orgid","企业ID") %>:
						<input type="text" id='otherorgid' name="otherorgid" maxlength='10' value="" class='Input09' style="width: 100px;">
						<input type="button" id='otherorg_click' name='otherorg_click' value="" class="searchbutton" onclick="subotherorg()"/>						
				</div>
				</c:if>
			</div>
			
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabnew" id='tabnew'>
				<thead>
					<tr><td colspan="9"><table width="100%" id='tabtitle' class='tabtitle' border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="5%" ></td>
						<td width="10%" ><%=lu.getLanguage(language,"phonelog.confid","会议ID") %></td>
						<td width="10%" ><%=lu.getLanguage(language,"phone.creator","创建人") %></td>
						<td width="15%" ><%=lu.getLanguage(language,"phonelog.confname","会议名称") %></td>
						<td width="20%" ><%=lu.getLanguage(language,"phone.etime","结束时间") %></td>
						<td width="14%" ><%=lu.getLanguage(language,"phone.loginnumber","接入号码") %></td>
						<td width="10%" ><%=lu.getLanguage(language,"phone.verifycode","验证码") %></td>
						<td width="8%" ><%=lu.getLanguage(language,"phone.state","状态") %></td>
						<td width="8%" ><%=lu.getLanguage(language,"operation","操作") %></td>
					</tr>
					</table></td></tr>
				</thead>
				<tbody class="tabcontent" id="content_conflist">	
				   <c:if test="${fn:length(list)==0}"><tr><td colspan="9" nuclick="1"><%=lu.getLanguage(language,"list.norecord","没有记录") %>!</td></tr></c:if>	
				   <c:forEach var="meeting" items="${list}" varStatus="id">
                        <tr bgcolor="#FFFFFF" id="${meeting.id}" joinnumber="${meeting.joinNumber}">
						<td width="5%"> <input class="phonecheck" type="checkbox" value="${meeting.id}"/></td>
						<td width="10%" >${meeting.id}</td>
						<td width="10%" >${meeting.userid}</td>
						<c:choose>
                              <c:when test="${fn:length(meeting.confName)>6}"><td title="${meeting.confName}" width="15%">${fn:substring(meeting.confName, 0, 6)}...</td></c:when>
                              <c:otherwise><td title="${meeting.confName}" width="15%">${meeting.confName}</td></c:otherwise>
                           </c:choose> 
						<td width="20%" >${meeting.endTime}</td>
						<td width="14%" >${meeting.joinNumber}</td>
						<td width="10%" ><span class="verifyCode"<c:if test="${meeting.verifyCodeVisible eq 1}"> style="display:none"</c:if>>${meeting.verifyCode}</span></td>
						<td width="8%" ><c:if test="${meeting.status eq 0}"><%=lu.getLanguage(language,"phone.normal","正常") %></c:if><c:if test="${meeting.status eq 1}"><%=lu.getLanguage(language,"phone.lock","锁定") %></c:if></td>
						<td width="8%" >
						<a class="operPhone" href="PhoneconfAction.go?method=goedit&conftype=${conftype }&confid=${meeting.id}&top=${top}"><%=lu.getLanguage(language,"phone.edit","编辑") %></a>
						</td>
                       </tr>
                     </c:forEach>
				</table>
				<div class="div_table_top" id="operPhone">
					<input id="phonecheckall" type="checkbox">
					<span style="color:#717171;"><%=lu.getLanguage(language,"phone.selectall","全选") %></span>
					<input class="sbutton" value="<%=lu.getLanguage(language,"phone.multidel","批量删除") %>" id="delPhone" type="button" onclick="deletePhone();" />
					<input class="sbutton" value="<%=lu.getLanguage(language,"phone.lock","锁定") %> "  id="cockPhone" type="button" onclick="lockPhone('1')" />
					<input class="sbutton" value="<%=lu.getLanguage(language,"phone.unlock","解除锁定") %>" id="unlockPhone" type="button" onclick="lockPhone('0')" />
					<!-- <a href="javascript:addVerifycode();"><input class="sbutton" value="验证码 " type="button" /></a> -->
					<input class="sbutton" value="<%=lu.getLanguage(language,"phone.add","新建") %>" id="addPhone" onclick="javascript:location.href='PhoneconfAction.go?method=goadd&conftype=${conftype}&top=${top }';" type="button" />
					
				</div>
				<seegle:paging action="PhoneconfAction.go?Keyword=${KeyWord}&conftype=${conftype}&top=${top }"/>
			<div id="tabs" class="tabnew ui-tabs ui-widget ui-widget-content ui-corner-all">
			<ul style='background: #F1F6FD; border: 0'>
			<li><a href="#tabs-1" ><%=lu.getLanguage(language,"phone.userlist","会议人员列表") %></a></li>
			<li><a href="#tabs-2" ><%=lu.getLanguage(language,"phone.vcodelist","会议验证码") %></a></li>
			<!-- <li><a href="#tabs-3" >会议会议记录</a></li> -->
			</ul>
			<div style="border-bottom:1px #348CD4 solid;"></div>
	
				<div id="tabs-1">
					<div id="phoneuserlist" style="overflow:hidden">
					<%=lu.getLanguage(language,"phone.noconfselected","未选中会议室") %>
	            	</div>
	            </div>
				<div id="tabs-2">
					<div id="verifycodelist" style="overflow:hidden">
					<%=lu.getLanguage(language,"phone.noconfselected","未选中会议室") %>
	            	</div>
	            </div>
            </div>
		</div>
	</div>
</form>
</div>
<div id="useradd_dialog" style='width:400px;height:300px;display: none;background: url("images_gb/table_bg.jpg") repeat-x;padding:30px;'>
<input name="confid" type="hidden" value=""/>
<p id="user_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
<table width="100%">
	<tr>
		<td width="30%" align="center"><%=lu.getLanguage(language,"phone.username","用户名称") %></td>
		<td width="70%" align="left"><input name="username" class="Input02_w" value=""/></td>
	</tr>
	<tr>
		<td width="30%" align="center"><%=lu.getLanguage(language,"phone.number","号码") %></td>
		<td width="70%" align="left">
		<input id="userphone" name="userphone" class="Input02_w" value="" style="width: 92px"/>
		<font color="#FF0000">*</font><%=lu.getLanguage(language,"phone.extension","分机号") %>
		<input id="userphone1" name="userphone1" class="Input02_w" style="width: 34px"/>
		<input name="olduserphone" class="Input02_w" type="hidden" value=""/>
		</td>
	</tr>
	<tr>
		<td width="30%" align="center" colspan="2" style="padding:10px;">
		<input type="button" class="sbutton" onclick="useradd();" value="<%=lu.getLanguage(language,"button.submit","确定") %>"/>
		<input type="button" class="sbutton" onclick="close_useradd();" value="<%=lu.getLanguage(language,"button.cancel","取消") %>"/>
		</td>
	</tr>
</table>
</div>
<div id="vcodeadd_dialog" style='width:400px;height:300px;display: none;background: url("images_gb/table_bg.jpg") repeat-x;padding:30px;'>
<p id="vcode_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
<input name="confid" id="confid" type="hidden" value=""/>
<table width="100%">
	<tr>
		<td width="30%" align="center"><%=lu.getLanguage(language,"phone.btime","开始时间") %></td>
		<td width="70%" align="left">
			<input value="${nowtime }" name="begintime" id="btime" size="22" type="text" class="Input02_w" readonly onclick="selectBtime()" />
		</td>
	</tr>
	<tr>
		<td width="30%" align="center"><%=lu.getLanguage(language,"phone.etime","结束时间") %></td>
		<td width="70%" align="left">
		<input value="${nexttime }" name="endtime" type="text" id="etime" size="22" class="Input02_w" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'mintime\')}',readOnly:true,lang:'<%=datelang%>'})"/>
   		<input type="hidden"  id="nowtime" value='${nowtime }'/>
       	<input type="hidden"  id="mintime" value='${nowtime }'/>
       	</td>
	</tr>
	<tr>
		<td width="30%" align="center" colspan="2" style="padding:10px;">
		<input type="button" class="sbutton" onclick="vcodeadd();" value="<%=lu.getLanguage(language,"button.submit","确定") %>"/>
		<input type="button" class="sbutton" onclick="close_useradd();" value="<%=lu.getLanguage(language,"button.cancel","取消") %>"/>
		</td>
	</tr>
</table>
</div>
<div id="sendmsg_dialog" style='width:600px;height:300px;display: none;background: url("images_gb/table_bg.jpg") repeat-x;padding:30px;'>
<p id="sendmsg_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
<input name="confid" id="confid" type="hidden" value=""/>
<table width="100%">
	<tr>
		<td width="20%" align="center"><%=lu.getLanguage(language,"phone.phonelist","电话列表") %></td>
		<td width="80%" align="left">
			<input  name="phones" id="phones" size="22" type="text" class="Input02_w" readonly  style="width: 450px"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="center"><%=lu.getLanguage(language,"phone.invitecontent","邀请内容") %></td>
		<td width="80%" align="left">
		<textarea rows="4" cols="40" id="smsg" style="width: 450px;height:100px" class="Input02_w"><%=lu.getLanguage(language,"phone.invitecontent","邀请内容") %></textarea>
		</td>
	</tr>
	<tr>
		<td width="30%" align="center" colspan="2" style="padding:10px;">
		<input type="button" class="sbutton" onclick="sendmsg();" value="<%=lu.getLanguage(language,"button.submit","确定") %>"/>
		<input type="button" class="sbutton" onclick="close_useradd();" value="<%=lu.getLanguage(language,"button.cancel","取消") %>"/>
		</td>
	</tr>
</table>
</div>
<div id="importUser_dialog" style='width:400px;height:300px;display: none;background: url("images_gb/table_bg.jpg") repeat-x;padding:30px;'>
<iframe width="340" height="160" src="" style="border: 0px"  frameborder="0"></iframe>
</div>
</body>
<script type="text/javascript" src="js/stringRes.js"></script>
<script type="text/javascript"> 
var url_phonetype = "PublicPhoneconf";

var submitFlag = 0;
$(function(){
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
	if(!"${otherorgid}"==""){
		url_phonetype+="&otherorgid=${otherorgid}";
	}
	$("#tabs" ).tabs(1);
	init_access();
	init_line_click();
	getUserlistRepetition();
	tableuserclick();
	$.get("${siteurl}/images_gb/unclick.gif");
	initotherorg();
});
var clickConfid = 0;
var callingPhone = "";
var callSecond = 0;
function getUserlistRepetition(){
	var phoneUserUpdateTime = parseInt("${phoneUserUpdateTime}");
	var flag = ($('#importUser_dialog').parent().css("display")=="block"&&$('#importUser_dialog').css("display")=="block")||($('#useradd_dialog').parent().css("display")=="block"&&$('#useradd_dialog').css("display")=="block")
	//alert((!($('#importUser_dialog').parent().css("display")=="block"||$('#useradd_dialog').parent().css("display")=="block")));
	if(clickConfid>0&&!flag){
		$("#importUser").addClass("sbutton");
		$("#importUser").removeClass("unclickbut");
		$("#importUser").removeAttr("disabled");
		$.getJSON("PhoneUserAction.go",{"confid":clickConfid,"method":"status","r":Math.random()},function(data){
			//alert(data);
			if(data==null){
				return;
			}
			for(var i = 0;i<data.length;i++){
				var lived = false;
				var obj = data[i];
				$("#table_user tr").each(function(){
					var phone = $(this).attr("phone");
					if(phone==obj.phone){
						lived = true;

						var tim = parseInt(obj.time);
						var tim1 = "";
						if(tim1>=3600*24){
							tim1 += parseInt(tim/(3600*24))+getMsg(DAY);
							tim=tim%(3600*24);
						}if(tim>=3600){
							tim1 += parseInt(tim/3600)+getMsg(HOUR);
							tim=tim%3600;
						}if(tim>=60){
							tim1 += parseInt(tim/60)+getMsg(MINUTE);
							tim=tim%60;
						}
						tim1+=tim+getMsg(SECOND);
						
						$(this).children(".time").html(tim1);
						if(obj.joinflag==0){
							$(this).children(".joinflag").html(getMsg(INVITE_BY_INTERNET));
						}else if(obj.joinflag==1){
							$(this).children(".joinflag").html(getMsg(INVITE_BY_PHONE));
						}else{
							$(this).children(".joinflag").html(getMsg(OTHER));
						}
						$(this).children(".muteflag").html(obj.muteflag==0?getMsg(NORMAL):getMsg(MUTE));
						$(this).children(".onlineflag").html(obj.onlineflag==0?getMsg(NO_CONNECTED):getMsg(CONNECTED));
						if(obj.onlineflag==1){
							$(this).children(".onlineflag").attr("onlined","1");
						}
						if(callSecond>0&&obj.onlineflag==0){
							var phons = callingPhone.split(";");
							for(var j=0;j<phons.length;j++){
								if(phons[j]==phone&&$(this).children(".onlineflag").attr("onlined")!="1"){
									$(this).children(".onlineflag").html("<font color='green'>"+getMsg(CALLING)+"</font>");
								}
							}
						}
					}
				});
				//是否变灰会议操作
				//confclick();
				//end是否变灰会议操作
				if(!lived){
					$("#content_conflist .line_clicked td")[1].click();
				}
				
			}
		});
		if(callSecond>0){
			callSecond-=phoneUserUpdateTime;
		}
	}
	setTimeout("getUserlistRepetition()", phoneUserUpdateTime);
}
/**
 * 全选
 */
$("#phonecheckall").click(function(){
	if($("#phonecheckall").attr("checked")){
		$(".phonecheck").attr("checked",true);
	}else{
		$(".phonecheck").attr("checked",false);
	}
});
/**
 * 初始化点击想项
 */
function init_line_click(){
	$("#content_conflist tr td[checkbox!=1][nuclick!=1]").bind("click",function(){
		$("#content_conflist tr").removeClass("line_clicked");
		$(this).parent().addClass("line_clicked");
		//location.href="PhoneVerifycodeAction.go";
		var img_wait="<div style='background-color:#ffffff;border:1px solid #cccccc'><img src='${siteurl}/images/onLoad.gif'>信息加载中</div>";
		//$("#verifycodelist").html(img_wait);
		//$("#phoneuserlist").html(img_wait);
		$.get("PhoneVerifycodeAction.go",{"confid":$(this).parent().attr("id"),"r":Math.random()},function(data){
			$("#verifycodelist").html(data);
		});
		clickConfid = parseInt($(this).parent().attr("id"));
		submitFlag+=1;
		var sbFlag = submitFlag;
		//getUserlistRepetition();
		$.get("PhoneUserAction.go",{"confid":$(this).parent().attr("id"),"r":Math.random()},function(data){
			if(sbFlag==submitFlag){
				$("#phoneuserlist").html(data);
				//confclick();
				tableuserclick();
			}
		});
	});
	$("#table_user tr td[checkbox!=1][nuclick!=1]").live("click",function(){
		$("#table_user tr").removeClass("line_clicked");
		$(this).parent().addClass("line_clicked");
	});
}
function getPoint(id) {  //返回html控件的坐标
    var htmlObj = document.getElementById(id);
    var rd = { x: 0, y: 0 };
    do {
        rd.x += htmlObj.offsetLeft;
        rd.y += htmlObj.offsetTop;
        htmlObj = htmlObj.offsetParent;
    }
    while (htmlObj)
    return rd;
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
/**
 * 获得选中的会议列表
 */
function getCheckedphone(){
	var checkedphone = $(".phonecheck:checked");
	var phones = "";
	for(var i=0;i<checkedphone.length;i++){
		if(i==0){
			phones+=$(checkedphone[i]).val();
		}else{
			phones+=";"+$(checkedphone[i]).val();
		}
	}
	return phones;
}
/**
 * 锁定会议室
 */
function lockPhone(lock){
	var confids = getCheckedphone();
	if(confids==""){
		alert(getMsg(NO_RECORD_SELECTED));
		return;
	}
	var url = "PhoneconfAction.go?method=lock&top=${top}&lock="+lock+"&confids="+confids+"&r="+Math.random();
	$.get(url,function(data){
		alert(errorMap.get(data+""));
		if("top"=="${top}"){
			location.href="TopConfPage.go?inc="+url_phonetype;
		}else{
			location.href="ConfPage.go?inc="+url_phonetype;
		}
	})
}
/**
 * 删除会议室
 */
function deletePhone(){
	var confids = getCheckedphone();
	if(confids==""){
		alert(getMsg(NO_RECORD_SELECTED));
		return;
	}
	var url = "PhoneconfAction.go?method=del&top=${top}"+"&confids="+confids+"&r="+Math.random();
	$.get(url,function(data){
		alert(errorMap.get(data+""));
		if("top"=="${top}"){
			location.href="TopConfPage.go?inc="+url_phonetype;
		}else{
			location.href="ConfPage.go?inc="+url_phonetype;
		}
	})
}

/**
 * 跳转到验证码添加界面
 */
function addVerifycode(){
	var selectTr = $(".line_clicked");
	if(selectTr.length!=0){
		location.href="PhoneVerifycodeAction.go?method=goadd&confid="+selectTr.attr("id")+"&r="+Math.random();
	}else{
		alert("no data selected!");
	}
}
/**
 * 打开用户添加界面
 */
function goaddPhoneuser(confid,name,phone){
	$("#user_msg").stop();	
	$("#user_msg").hide();
	$('#useradd_dialog input[name="confid"]').val(confid);
	$('#useradd_dialog input[name="username"]').val(name);
	if(phone==null){
		phone="";
	}
	var index_ = phone.indexOf("-");
	if(index_>=0){
		$('#useradd_dialog input[name="userphone"]').val(phone.substring(0,index_));
		$('#useradd_dialog input[name="userphone1"]').val(phone.substring(index_+1,phone.length));
	}else{
		$('#useradd_dialog input[name="userphone"]').val(phone);
		$('#useradd_dialog input[name="userphone1"]').val("");
	}
	$('#useradd_dialog input[name="olduserphone"]').val(phone);
	var title = getMsg(ADD_PHONEUSER);
	if(phone!=""&&phone!=null){
		title = getMsg(EDIT_PHONEUSER);
	}
	$('#useradd_dialog').dialog({
		modal:true,
		position:"20%",
		width:400,
		height:250,
		title:"<span style='color:#FFFFFF;'>"+title+"</span>"
	});
	$("#ui-dialog-title-useradd_dialog").css({"height":"25px","float":"left","padding":"6px 0 0 6px"});
	$(".ui-dialog-titlebar-close").css("float","right");
	$(".ui-dialog-titlebar-close").css({"width":"16px","height":"16px","margin":"6px 6px 0 0"});
	$(".ui-dialog-titlebar").css("background-image","");
	$(".ui-dialog-titlebar").css("background-color","#348CD4");
	$("#useradd_dialog").css("border","0");
}
/**
 * 修改电话用户
 */
function useredit(confid){
	var user = $("#table_user :checkbox:checked").parent().parent();
	var username = "";
	var userphone = "";
	if(user.length!=0){
		username = user.attr("name");
		userphone = user.attr("phone");
	}else{
		alert(getMsg(NO_USER_SELECTED));
		return ;
	}
	goaddPhoneuser(confid,username,userphone);
}
$('#useradd_dialog').dialog("close");
/**
 * 关闭用户添加界面
 */
function close_useradd(){
	$('.ui-dialog-titlebar-close').click();
}
/**
 * 添加电话会议用户
 */
function useradd(){
	var userphone = $("#userphone").val();
	if(userphone==""){
		jQuery('#user_msg').text(getMsg(NO_PHONE_NUMBER));
		jQuery('#userphone').focus();
		jQuery("#user_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		return false;
	}
	if($("#userphone1").val()!=""){
		userphone+="-"+$("#userphone1").val();
	}
	close_useradd();
	var url = "PhoneUserAction.go?method=add"+"&r="+Math.random();
	var olduserphone = $("#useradd_dialog input[name='olduserphone']").val();
	if(!(olduserphone==""||olduserphone==null)){
		url = "PhoneUserAction.go?method=edit"+"&r="+Math.random();
	}
	$("#useradd_dialog input[name]").each(function(n,i){
		if($(this).attr("name")=="userphone"){
			url+="&userphone="+userphone;
		}else{
			url+="&"+$(this).attr("name")+"="+encodeURIComponent($(this).val());
		}
	});
	//TODO 添加用户代码
	$.get(url,function(msg){
		alert(errorMap.get(msg+""));
		if(msg==0){
			$.get("PhoneUserAction.go",{"confid":$("#useradd_dialog input[name='confid']").val(),"r":Math.random()},function(data){
				$("#phoneuserlist").html(data);
			});
		}
	});
}
/**
 * 获得选中用户
 */
function getcheckeduser(){
	var checkeduser = $(".usercheck:checked");
	var users = "";
	for(var i=0;i<checkeduser.length;i++){
		if(i==0){
			users+=$(checkeduser[i]).val();
		}else{
			users+=";"+$(checkeduser[i]).val();
		}
	}
	return users;
}
/**
 * 删除电话会议联系人
 */
function phoneuserdel(confid){
	var phones = getcheckeduser();
	if(phones==""){
		alert(getMsg(NO_RECORD_SELECTED));
		return ;
	}
	var url = "PhoneUserAction.go?method=del&confid="+confid+"&phones="+phones+"&r="+Math.random();
	$.get(url,function(msg){
		alert(errorMap.get(msg+""));
		if(msg==0){
			$.get("PhoneUserAction.go",{"confid":confid,"r":Math.random()},function(data){
				$("#phoneuserlist").html(data);
			});
		}
	});
}
/**
 * 禁用电话会议联系人
 */
function muteuser(confid,mute){
	var phones = getcheckeduser();
	if(phones==""){
		alert(getMsg(NO_RECORD_SELECTED));
		return ;
	}
	var url = "PhoneUserAction.go?method=mute&confid="+confid+"&mute="+mute+"&phones="+phones+"&r="+Math.random();
	$.get(url,function(msg){
		//alert(errorMap.get(msg+""));
		if(msg==318){
			/*$.get("PhoneUserAction.go",{"confid":confid},function(data){
				$("#phoneuserlist").html(data);
			});*/
		}
		else{
			if(msg==8){
				alert(errorMap.get("132"));
			}else{
				alert(errorMap.get(msg+""));
			}
		}
	});
}
/**
 * 呼叫用户
 */
function calluser(confid,call){
	var phones = getcheckeduser();
	if(phones==""){
		alert(getMsg(NO_RECORD_SELECTED));
		return ;
	}
	var url = "PhoneUserAction.go?method=call&confid="+confid+"&call="+call+"&phones="+phones+"&r="+Math.random();
	$.get(url,function(msg){
		//alert(errorMap.get(msg+""));
		if(msg==318){
			if(call+""=="0"){
				var phonelist = phones.split(";");
				for(var i=0;i<phonelist.length;i++){
					$("#table_user #"+phonelist[i]+" .onlineflag").attr("onlined","0");
				}
				callSecond = 15000;
				callingPhone = phones;
			}
			/*$.get("PhoneUserAction.go",{"confid":confid},function(data){
				$("#phoneuserlist").html(data);
			});*/
		}else{
			if(msg==8){
				alert(errorMap.get("132"));
			}else{
				alert(errorMap.get(msg+""));
			}
		}
	});
}
/**
 * 发送短消息
 */
function opensendmsg(confid){
	var phones = getcheckeduser();
	if(phones==""){
		alert(getMsg(NO_RECORD_SELECTED));
		return ;
	}
	var joinnumber = $("#content_conflist .line_clicked").attr("joinnumber");
	var vcode = $("#table_vcode tr[status=1]").attr("vcode");
	if(vcode==null||vcode==""){
		alert(getMsg(NO_AVAILABLE_VCODE));
		$("#tabs" ).tabs(2);
		return ;
	}
	var msg = getMsg(SEEGLE_PHONE_CONF)+"\n"+getMsg(DAIL_PHONE)+joinnumber+"\n"+getMsg(INPUT_VCODE)+vcode+getMsg(LOGIN_CONF);
	//iPhone可一键直拨"+joinnumber+","+vcode+"#参会。若您要召开免费电话会议，可随时拨打400-822-6011获取会议室。";
	$("#sendmsg_dialog #phones").val(phones);
	$("#sendmsg_dialog #smsg").val(msg);
	$('#sendmsg_dialog #confid').val(confid);
	var url = "PhoneUserAction.go?method=sendmsg&confid="+confid+"&msg="+msg+"&phones="+phones+"&r="+Math.random();
	$('#sendmsg_dialog').dialog({
		modal:true,
		position:"20%",
		width:600,
		height:250,
		title:"<span style='color:#FFFFFF;'>"+getMsg(INVITE_BY_MSG)+"</span>"
	});
	$("#ui-dialog-title-sendmsg_dialog").css({"height":"25px","float":"left","padding":"6px 0 0 6px"});
	$(".ui-dialog-titlebar-close").css("float","right");
	$(".ui-dialog-titlebar-close").css({"width":"16px","height":"16px","margin":"6px 6px 0 0"});
	$(".ui-dialog-titlebar").css("background-image","");
	$(".ui-dialog-titlebar").css("background-color","#348CD4");
	$("#sendmsg_dialog").css("border","0");
}
var sendmsg_flag = false;
/**
 * 发送短信
 */
function sendmsg(){
	if(sendmsg_flag){
		alert(getMsg(REPEAT_SUBMIT));
		return ;
	}
	sendmsg_flag = true;
	var phones = $("#sendmsg_dialog #phones").val();
	var msg = $("#sendmsg_dialog #smsg").val();
	var confid = $("#sendmsg_dialog #confid").val();
	var url = "PhoneUserAction.go?method=sendmsg&confid="+confid+"&msg="+encodeURIComponent(msg)+"&phones="+phones+"&r="+Math.random();
	$.get(url,function(msg){
		sendmsg_flag = false;
		alert(errorMap.get("dx"+msg)==null?"未获取到对应错误信息，错误代码"+msg:errorMap.get("dx"+msg));
		if(msg==0){
			close_useradd();
		}
	});
}
/**
 * 打开验证码添加界面
 */
function goaddVcode(confid){
	$('#vcodeadd_dialog input[name="confid"]').val(confid);
	$('#vcodeadd_dialog').dialog({
		modal:true,
		position:"20%",
		width:400,
		height:250,
		title:"<span style='color:#FFFFFF;'>"+getMsg(ADD_VCODE)+"</span>"
		
	});
	$("#ui-dialog-title-vcodeadd_dialog").css({"height":"25px","float":"left","padding":"6px 0 0 6px"});
	$(".ui-dialog-titlebar-close").css("float","right");
	$(".ui-dialog-titlebar-close").css({"width":"16px","height":"16px","margin":"6px 6px 0 0"});
	$(".ui-dialog-titlebar").css("background-image","");
	$(".ui-dialog-titlebar").css("background-color","#348CD4");
	$("#vcodeadd_dialog").css("border","0");
}
$('#vcodeadd_dialog').dialog("close");
/**
 * 关闭添加验证码界面
 */
function close_vcodeadd(){
	$('.ui-dialog-titlebar-close').click();
}
/**
 * 添加验证码
 */
function vcodeadd(){
	close_vcodeadd();
	var url = "PhoneVerifycodeAction.go?method=add"+"&r="+Math.random();
	$("#vcodeadd_dialog input[name]").each(function(n,i){
		url+="&"+$(this).attr("name")+"="+$(this).val();
	});
	//TODO 添加验证码代码
	$.get(url,function(msg){
		alert(errorMap.get(msg+""));
		if(msg==0){
			$.get("PhoneVerifycodeAction.go?confid="+$("#vcodeadd_dialog input[name='confid']").val(),{"r":Math.random()},function(data){
				$("#verifycodelist").html(data);
			});
		}
	});
}
/**
 * 获取选中
 */
function getvcodes(){
	var checkedcode = $(".vcodecheck:checked");
	var codes = "";
	for(var i=0;i<checkedcode.length;i++){
		if(i==0){
			codes+=$(checkedcode[i]).val();
		}else{
			codes+=";"+$(checkedcode[i]).val();
		}
	}
	return codes;
}
/**
 * 删除验证码
 */
function delvcode(confid){
	var vcodes = getvcodes();
	if(vcodes==""){
		alert(getMsg(NO_RECORD_SELECTED));
		return ;
	}
	var url = "PhoneVerifycodeAction.go?method=del&confid="+confid+"&codes="+vcodes+"&r="+Math.random();
	$.get(url,function(msg){
		alert(errorMap.get(msg+""));
		if(msg==0){
			$.get("PhoneVerifycodeAction.go?confid="+confid,{"r":Math.random()},function(data){
				$("#verifycodelist").html(data);
			});
		}
	});
}
/**
 * 禁用验证码
 */
function disablevcode(confid){
	var vcodes = getvcodes();
	if(vcodes==""){
		alert(getMsg(NO_RECORD_SELECTED));
		return ;
	}
	var url = "PhoneVerifycodeAction.go?method=disable&confid="+confid+"&codes="+vcodes+"&r="+Math.random();
	$.get(url,function(msg){
		alert(errorMap.get(msg+""));
		if(msg==0){
			$.get("PhoneVerifycodeAction.go?confid="+confid,{"r":Math.random()},function(data){
				$("#verifycodelist").html(data);
			});
		}
	});
}
/**
 * 打开用户导入界面
 */
function openImportUser(confid){
	$('#importUser_dialog iframe').attr("src","PhoneUserAction.go?method=goimport&confid="+confid+"&r="+Math.random());
	$('#importUser_dialog').dialog({
		modal:true,
		position:"20%",
		width:400,
		height:250,
		title:"<span style='color:#FFFFFF;'>"+getMsg(USER_IMPORT)+"</span>"
	});
	$("#ui-dialog-title-importUser_dialog").css({"height":"25px","float":"left","padding":"6px 0 0 6px"});
	$(".ui-dialog-titlebar-close").css("float","right");
	$(".ui-dialog-titlebar-close").css({"width":"16px","height":"16px","margin":"6px 6px 0 0"});
	$(".ui-dialog-titlebar").css("background-image","");
	$(".ui-dialog-titlebar").css("background-color","#348CD4");
	$("#importUser_dialog").css("border","0");

	$("#importUser_dialog").prev().find(".ui-icon-closethick").bind("click",function(){
		$('#importUser_dialog iframe').attr("src","");
	});
	
}
/**
 * 用户正在导入
 */
function userimporting(flag){
	if(flag==1){
		$("#importUser").addClass("unclickbut");
		$("#importUser").removeClass("sbutton");
		$("#importUser").attr("disabled","disabled");
		$("#ui-dialog-title-importUser_dialog span").html("<font color='green'>"+getMsg(USER_IMPORTING)+"</font>");
	}else{
		$("#ui-dialog-title-importUser_dialog span").html(getMsg(USER_IMPORT));
	}
}
/**
 * 用户选择
 */
function tableuserclick(){
	$("#phoneuserlist :checkbox").live("click",function(){
		var checksize = $("#table_user :checkbox:checked").length;
		if(checksize==1){
			$("#editUser").addClass("sbutton");
			$("#editUser").removeClass("unclickbut");
			$("#editUser").removeAttr("disabled");
		}else{
			$("#editUser").addClass("unclickbut");
			$("#editUser").removeClass("sbutton");
			$("#editUser").attr("disabled","disabled");
		}
		
		if(checksize>=1){
			$("#callUser,#uncallUser,#muteUser,#unmuteUser,#sendmsgUser,#delUser").addClass("sbutton");
			$("#callUser,#uncallUser,#muteUser,#unmuteUser,#sendmsgUser,#delUser").removeClass("unclickbut");
			$("#callUser,#uncallUser,#muteUser,#unmuteUser,#sendmsgUser,#delUser").removeAttr("disabled");
		}else{
			$("#callUser,#uncallUser,#muteUser,#unmuteUser,#sendmsgUser,#delUser").addClass("unclickbut");
			$("#callUser,#uncallUser,#muteUser,#unmuteUser,#sendmsgUser,#delUser").removeClass("sbutton");
			$("#callUser,#uncallUser,#muteUser,#unmuteUser,#sendmsgUser,#delUser").attr("disabled","disabled");
		}
	})
}
/**
 * 会议选择
 */
function confclick(){
	var all_no_connect = true;
	$("#table_user .onlineflag").each(function(){
		if($.trim($(this).html())!=getMsg(NO_CONNECTED)){
			all_no_connect = false;
		}
	});
	var now_click = $("#content_conflist .line_clicked :checkbox:checked").length>0;
	if(all_no_connect||!now_click){
		$("#delPhone").addClass("sbutton");
		$("#delPhone").removeClass("unclickbut");
		$("#delPhone").removeAttr("disabled");
	}else{
		$("#delPhone").addClass("unclickbut");
		$("#delPhone").removeClass("sbutton");
		$("#delPhone").attr("disabled","disabled");
	}
}
/**
 * 初始化操作权限
 */
function init_access(){
	//alert(${userid});
	var userPhoneType = "${phonetype}";
	var confType = "${conftype}";
	var isserveruser = "${isserveruser}";
	var oper =true;
	if(userPhoneType=="${applicationScope.phonetype_none}"){
		oper = false;
	}if(userPhoneType=="${applicationScope.phonetype_user}"){
		if(confType=="0"){
			oper = false;
		}if(confType=="3"){
			oper = false;
		}
	}
	if(${userid eq applicationScope.account_admin}||(${userid eq '1000'}&&${top eq 'top'})||(${isserveruser eq 1}&&${otherorgid != null})){
		oper = true;
	}
	if(confType=="3"){
		$("#addPhone").hide();
	}
	if(!oper){
		$("#operPhone").hide();
		$(".operPhone").hide();
		$("#operUser").hide();
		$("#operVcode").hide();
	}else{
		$(".verifyCode").show();
	}
}
function initotherorg(){
	$("form").submit(function (){
		return false;
	})
	var otherorgid_cookie = $.cookie("otherorgid_cookie");
	$("#otherorgid").val(otherorgid_cookie);
	$("#otherorgid").keydown(function(e){
		var curKey = e.which;
		if(curKey == 13){
			subotherorg();
		}
	});
}
function subotherorg(){
	$.cookie("otherorgid_cookie",$("#otherorgid").val(),{expires: 1000});
	if("${conftype}"=="0"){
		location.href="TopConfPage.go?inc=PublicPhoneconf"+"&otherorgid="+$("#otherorgid").val();
	}else if("${conftype}"=="3"){
		location.href="TopConfPage.go?inc=AllUserPhoneconf"+"&otherorgid="+$("#otherorgid").val();
	}
}
</script>