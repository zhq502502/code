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
				<div class="s_001"><%=lu.getLanguage(language,"phone.addvcode","添加验证码") %></div>
			</div>
		<div id="uploadTip">
		   <p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
		</div>	
		<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD" class="tamnew4">
		<form name="form" id="form" action="#" method="post" autocomplete='off'>
		<input name="confid" type="hidden" value="${confid }"/>
		<tr>
	       <td width="18%" style="text-align: right;"><label for="confname"><%=lu.getLanguage(language,"confadd.btime","开始时间") %></label></td>
	       <td width="28%">
				<input name="confname" id="btime" size="22" type="text" class="Input02_w" readonly onclick="selectBtime()" /><font color="#FF0000">  *</font>
			</td>
	        <td width="14%" style="text-align: right;"><label for="btime"><%=lu.getLanguage(language,"confadd.etime","结束时间") %></label></td>
	       <td width="40%">
	       		<input value='${btime}' name="etime" type="text" id="etime" size="22" class="Input02_w" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'mintime\')}',readOnly:true,lang:'<%=datelang%>'})"/>
	       		<input type="hidden"  name="nowtime" id="nowtime" value='2013-12-25 00:00:00'/>
            	<input type="hidden"  name="mintime" id="mintime" value='2014-01-01 00:00:00'/>
				<font color="#FF0000">  *</font>
			</td>
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
<script src="js/date/WdatePicker.js"></script>
<script type="text/javascript" >
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
</script>