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

            Date DT = new Date();
            SimpleDateFormat SimDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String StartDate = SimDF.format(new Date(DT.getTime() - 60 * 1000));  //提前一分钟,解决新建会议室提示“会议尚未开始”
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, 1);
            long date = cal.getTimeInMillis();
            String endDate = SimDF.format(date);
            String soft_version = request.getAttribute("soft_version").toString();
String datelang="";
datelang=language.replace('_', '-');
%>
<script type="text/javascript" src="help/js/jtip.js"></script>  
<script type="text/javascript" src="help/js/jquery.js"></script> 
<script type="text/javascript" src="js/changeType.js"></script> 
<link type="text/css" rel="stylesheet" href="help/css/global.css"/>
<body onload="JT_init()">
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001"><%=lu.getLanguage(language,"confadd.confadd","新建会议室") %></div>
			</div>
		<div id="uploadTip">
		   <p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
		</div>	
		<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD" class="tamnew4">
	<form name="form" id="form" action="#" method="post" autocomplete='off'>
	<input type="hidden" name="lock_flag" id="lock_flag" value="${conf_lock_flag}" />
	<input type="hidden" id="auto_clear_flag" name="auto_clear_flag" value="${conf_auto_clear_flag}" />
	<input type="hidden" name="download_mode" id="download_mode" value="${conf_download_mode}" />
	<input type="hidden" name="open_audit" id="open_audit" value="${conf_open_audit}" />
	<input type="hidden" name="video_quality" id="video_quality" value="${conf_video_quality}" />
	<input type="hidden" name="VRenderer" id="VRenderer" value="${conf_vrenderer}" />
	<tr>
       <td width="18%" style="text-align: right;"><label for="confname"><%=lu.getLanguage(language,"confadd.confname","会议名称") %></label></td>
       <td width="28%">
			<input name="confname" id="confname" size="22" type="text" class="Input02_w" /><font color="#FF0000">  *</font>
		</td>
        <td width="14%" style="text-align: right;"><label for="btime"><%=lu.getLanguage(language,"confadd.btime","开始时间") %></label></td>
       <td width="40%">
       		<input value='${btime}' name="btime" type="text" id="btime" size="22" class="Input02_w" readonly onclick="selectBtime()"/>
			<font color="#FF0000">  *</font>
		</td>
	</tr>
	<tr>
       <td style="text-align: right;"><label for="confpass"><%=lu.getLanguage(language,"confadd.confpass","会议密码") %></label></td>
       <td>
			<input name="confpass" id="confpass" size="22" type="password" class="Input02_w" value=""/>
		</td>
        <td style="text-align: right;"><label for="etime"><%=lu.getLanguage(language,"confadd.etime","结束时间") %></label></td>
       <td>
            <input type="hidden"  name="nowtime" id="nowtime" value='${btime}'/>
            <input type="hidden"  name="mintime" id="mintime" value='${btime}'/>
       		<input value='${etime1}' name="etime" type="text" id="etime" size="22" class="Input02_w" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'mintime\')}',readOnly:true,lang:'<%=datelang%>'})"/>
			<font color="#FF0000">  *</font>
		</td>
	</tr>
	<tr>
       <td style="text-align: right;"><label for="confpass1"><%=lu.getLanguage(language,"confadd.confpass.confirmation","确认会议密码") %></label></td>
       <td>
			<input name="confpass1" id="confpass1" size="22" type="password" class="Input02_w" />
		</td>
       <td style="text-align: right;"><label for="maxconfuser"><%=lu.getLanguage(language,"confadd.maxconfuser","最大与会人数") %></label></td>
       <td>
			<input name="maxconfuser" id="maxconfuser" size="22" type="text" class="Input02_w" value="100"/><font color="#FF0000">  *</font>
		</td>
	</tr>	
	<tr>
       <td style="text-align: right;"><label for="managepass"><%=lu.getLanguage(language,"confadd.managepass","会议管理密码") %></label></td>
       <td>
			<input name="managepass" id="managepass" size="22" type="password" class="Input02_w" />
		</td>
       <td style="text-align: right;"><label for="maxconfspokesman"><%=lu.getLanguage(language,"confadd.maxconfspokesman","最大主席人数") %></label></td>
       <td>
			<input name="maxconfspokesman" id="maxconfspokesman" size="22" type="text" class="Input02_w" value="10"/><font color="#FF0000">  *</font>
		</td>
	</tr>	
	<tr>
       <td style="text-align: right;"><label for="managepass1"><%=lu.getLanguage(language,"confadd.managepass.confirmation","确认管理密码") %></label></td>
       <td>
			<input name="managepass1" id="managepass1" size="22" type="password" class="Input02_w"/>
		</td>
       <td style="text-align: right;"><label for="maxconftourist"><%=lu.getLanguage(language,"confadd.maxconftourist","最大游客人数") %></label></td>
       <td>
			<input name="maxconftourist" id="maxconftourist" size="22" type="text" class="Input02_w" value="0"/><font color="#FF0000">  *</font>
		</td>
	</tr>
	<tr>
       <td style="text-align: right;"><label for="confgrouptype"><%=lu.getLanguage(language,"confadd.confgrouptype","会议集群类型") %></label></td>
       <td>
			 <select id="confgrouptype" name="confgrouptype" class="Input02_w">
			     <c:forEach var="ip" items="${groupList}">
                     <option value="${ip.id}">${ip.name}</option>
                 </c:forEach>
			 </select><font color="#FF0000">  *</font>
		</td>
	   <td style="text-align: right;display:none " ><label for="captchatype"><%=lu.getLanguage(language,"phone.addvcode","添加验证码") %></label></td>
       <td style="display: none"><input type="radio"  name="captcha_flag" value="1" onclick="createCaptcha()" /><%=lu.getLanguage(language,"button.yes","是") %>
           <input type="radio"  name="captcha_flag" value="0" onclick="createCaptcha()" checked=checked /><%=lu.getLanguage(language,"button.no","否") %>
           </td>
	</tr>	
	<tr id="captcha2" style="display:none">
	    <td style="text-align: right;" ><label for=""><%=lu.getLanguage(language,"confadd.valid","有效期") %></label></td>
	    <td colspan="3">
	        <input value='${btime}' name="c_btime" type="text" id="c_btime" size="22" class="Input02_w" readonly onclick="selectBtime1()"/>-
            <input type="hidden"  name="c_nowtime" id="c_nowtime" value='${btime}'/>
            <input type="hidden"  name="c_mintime" id="c_mintime" value='${btime}'/>
       		<input value='${etime}' name="c_etime" type="text" id="c_etime" size="22" class="Input02_w" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'c_mintime\')}',readOnly:true,lang:'<%=datelang%>'})"/>			
		</td>
	</tr>
	<tr>
       <td style="text-align: right;"><label><%=lu.getLanguage(language,"confadd.openflag","允许任何人参加") %></label></td>
       <td><input type="radio"  name="open_flag" value="1" checked=checked /><%=lu.getLanguage(language,"button.yes","是") %>
           <input type="radio"  name="open_flag" value="0" /><%=lu.getLanguage(language,"button.no","否") %>
           <span class="formInfo"><a href="help/Comein_<%=language %>.htm?width=350" class="jTip" id="one" name="<%=lu.getLanguage(language,"confadd.comein.illustration","允许任何人参加设置说明") %>">?</a></span>
           </td>
       <td style="text-align: right;"><label for="all_can_visible"><%=lu.getLanguage(language,"confadd.allcanvisible","所有人可见") %></label></td>
       <td><input type="radio" id="all_can_visible" name="all_can_visible" value="0" checked=checked /><%=lu.getLanguage(language,"button.yes","是") %>
           <input type="radio" id="all_can_visible" name="all_can_visible" value="1" /><%=lu.getLanguage(language,"button.no","否") %>
           <span class="formInfo"><a href="help/See_<%=language %>.htm?width=350" class="jTip" id="two" name="<%=lu.getLanguage(language,"confadd.see.illustration","所有人可见设置说明") %>">?</a></span>
           </td>
	</tr>
	
   <%if(soft_version.equals("购买版")){%>
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
<%} %>

    <tr>
         <td style="text-align: right;"><%=lu.getLanguage(language,"confadd.description","会议描述") %></td>
         <td colspan="3" style="text-align:left; padding-left:6px;"><textarea name="description" cols="60" rows="5" id="description"></textarea><font color="#FF0000">&nbsp;&nbsp;</font></td>
    </tr>					
	<tr>
		<td colspan="4" valign="middle" style="height:40px; text-align:center;">
			<input type="submit" id="btnSearch" value="<%=lu.getLanguage(language,"button.add","添加") %>" class="sbutton" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <input type="button" id="reset_btn" onclick="reset()" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" />
		</td>
	</tr>			
		</form>	
			</table>			
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="js/stringRes.js"></script>
<script src="js/date/WdatePicker.js"></script>
	<script type="text/javascript">
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
	function selectBtime1(){
		WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'c_etime\')}',isShowClear:false,readOnly:true,lang:'<%=datelang%>'});
		changeTime1();		
		}
	function changeTime1(){
		var btime1 = $('#c_btime').val();
		var nowtime = $('#c_nowtime').val();
		var date=Date.parse(btime1.replace(/-/g,"/"));
		var now=Date.parse(nowtime.replace(/-/g,"/"));
		if(date>now){
			$('#c_mintime').val(btime1);
		}else{
			$('#c_mintime').val(nowtime);
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
	
	
		$(document).ready(function() {
			$('#conf_addconf').addClass("sel_tag");
			var siteurl = $('#siteurl').val();
			jQuery('#form').submit(function(){
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
				var grouptype = $('#confgrouptype').val();
			    var numberRegex = /^[+]?\d+$/;
			    var cnameRegex = /^[a-zA-Z0-9\u0391-\uFFE5]+(([\-\_ ][a-zA-Z0-9\u0391-\uFFE5 ])?[a-zA-Z0-9\u0391-\uFFE5]*)*$/; 
			    var open_flag = getValue("open_flag");			    			   
			    var lock_flag = $('#lock_flag').val();
			    var auto_clear_flag = $('#auto_clear_flag').val();
			    var all_can_visible = getValue("all_can_visible");	    
			    var description = $('#description').val();			   
			    var download_mode = $('#download_mode').val();
			    var open_audit = $('#open_audit').val();
			    var captcha_flag = getValue("captcha_flag");
				var c_btime = $('#c_btime').val();
				var c_etime = $('#c_etime').val();
								
			    //confsetting参数组合
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
			    var video_quality = $('#video_quality').val();
			    var VRenderer = $('#VRenderer').val();
			    var confsetting = "<H264QP>="+video_quality+";<VRenderer>="+VRenderer;					    
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
				if(grouptype==null||grouptype==""){
					jQuery('#l_msg').text(getMsg(ERR_NO_CONFGROUP));
					jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
					return false;
				}else {
					jQuery("#l_msg").hide();
					$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
						if(rs==null||rs==""){
							alert(getMsg(ERR_CURRENT_USER_UNEXIST));
							location.href = siteurl+'/logout';
						}
						else if(rs==1 || rs==2){
							jQuery.getJSON(siteurl+"/conf/concurrent", {btime: btime, etime: etime, maxconfuser: maxconfuser, _: new Date().getTime() } ,function(rs2) {
								if(rs2.tip==0||rs2.flag==0){
									$.post(siteurl+"/conf/add", {confname: confname, btime: btime, etime: etime, confpass: confpass, managepass: managepass, maxconfuser: maxconfuser, maxconftourist: maxconftourist, maxconfspokesman: maxconfspokesman, grouptype: grouptype, confpass: confpass, open_flag: open_flag, lock_flag: lock_flag, auto_clear_flag: auto_clear_flag, all_can_visible: all_can_visible,  description: description,auto_record: auto_record, record_module: record_module, download_mode: download_mode, open_audit: open_audit, confsetting: confsetting, captcha_flag:captcha_flag, c_btime: c_btime, c_etime: c_etime, _: new Date().getTime() } ,function(rs1) {
										if(rs1==0){
											alert(getMsg(ADD_SUCESS));
											location.href=siteurl+'/ConfPage.go?inc=ConfManage';
										}
										else if(rs1=='-10002'){
											alert(errorMap.get(rs1+""));
											location.href=siteurl+'/ConfPage.go?inc=ConfManage';
										}
										else {
											alert(getMsg(ADD_FAILURE));
											return false;
										}
									});
								}else if(rs2.flag==1&&rs2.tip==1){
									 if(confirm(getMsg(ERR_CONFIRM_ADD))){
											$.post(siteurl+"/conf/add", {confname: confname, btime: btime, etime: etime, confpass: confpass, managepass: managepass, maxconfuser: maxconfuser, maxconftourist: maxconftourist, maxconfspokesman: maxconfspokesman, grouptype: grouptype, confpass: confpass, open_flag: open_flag, lock_flag: lock_flag, auto_clear_flag: auto_clear_flag, all_can_visible: all_can_visible,  description: description,auto_record: auto_record, record_module: record_module, download_mode: download_mode, open_audit: open_audit, confsetting: confsetting, captcha_flag:captcha_flag, c_btime: c_btime, c_etime: c_etime, _: new Date().getTime() } ,function(rs1) {
												if(rs1==0){
													alert(getMsg(ADD_SUCESS));
													location.href=siteurl+'/ConfPage.go?inc=ConfManage';
												} 
												else if(rs1=='-10002'){
													alert(errorMap.get(rs1+""));
													location.href=siteurl+'/ConfPage.go?inc=ConfManage';
												}
												else {
													alert(getMsg(ADD_FAILURE));
													return false;
												}
											});
									 }else{
										 return false;
									}
								}else{
									alert(getMsg(ERR_CONFADD_REFUSED));
									return false;
								}
							});							
						} 
						else {
							alert(getMsg(ERR_NO_PERMISSION));
							//location.href = siteurl+'/conf/ConfList';
							location.href = siteurl+'/logout';
							return false;
						}
					});
					
					return false;
				}
				return false;
			});	
		});
</script>