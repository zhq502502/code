<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="seegle"  uri="/WEB-INF/taglib/seegle.tld" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="com.seegle.util.PropUtil"%>
<%@page import="com.seegle.util.LanguageUtil"%>
<%@page import="com.seegle.data.GetCookies"%>
<% 
String siteurl = request.getSession().getAttribute("siteurl").toString();
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
String aaa = "123";
%>
<script src="js/date/WdatePicker.js"></script>
<script> 
$(document).ready(function() {
	$('#phoneconf_chargehistory').addClass("sel_tag");
	
	$("#begintime").keydown(function(event){
    	if(event.keyCode==13){
    		selectPayHistory();
        	return false;
    	}
    });
	$("#endtime").keydown(function(event){
    	if(event.keyCode==13){
    		selectPayHistory();
        	return false;
    	}
    });
	$("#searchorgid").keydown(function(event){
    	if(event.keyCode==13){
    		selectPayHistory();
        	return false;
    	}
    });
    $(".chargedetail").each(function(){
        var title = $(this).attr("title");
        title = title.replace("<countdata>",getMsg(PHONE_CHARGE_COUNTDATA));
        title = title.replace("<chargingtime>",getMsg(PHONE_CHARGE_COUNTTIME));
        title = title.replace("<begintime>",getMsg(PHONE_CHARGE_BEGINTIME));
        title = title.replace("<endtime>",getMsg(PHONE_CHARGE_ENDTIME));
		$(this).attr("title",title);
    })
});
   function selectPayHistory(){
      var btime = document.getElementById("begintime").value;
      var etime = document.getElementById("endtime").value;
      var sorgid = $('#searchorgid').val();
      if(sorgid==null||sorgid==""||sorgid=="undifined")
      sorgid = "";
      var top = $('#top').val();
      if(top=="top"){
			document.forms[0].action = "PhonePayHistoryAction.go?begintime="+btime+"&endtime="+etime+"&sorgid="+sorgid+"&top=top"+"&recordType=${recordType}";
		}else{
			document.forms[0].action = "PhonePayHistoryAction.go?begintime="+btime+"&endtime="+etime+"&sorgid="+sorgid+"&recordType=${recordType}";
		}
      document.forms[0].submit();
   }
   function detail_charge(orgid,param){
		var params = param.split(";");
		if(params.length==5){
			var btime = params[2].replace('<begintime>','');
			var etime = params[3].replace('<endtime>','');
			var url = "<%=siteurl %>/PhoneConsumeAction.go?btime="+btime+"&etime="+etime+"&method=phoneDetail&loginFlag=&org="+orgid;
			window.location = url;
		}
   }
</script>
<input name="top" id="top" type="hidden" value="${top}"/>
<input type="hidden" id="sorgid" name="sorgid" value="${searchorgid}"/>
	<div id="wpbody-content">
		<div class="wrap">
			<div class="space_03">
				<div id="topLinkL">
					<div class="s_001"><%=lu.getLanguage(language,"confpage.menu.phoneconf.chargehistory","扣费记录") %></div>
					<div class="s_002">
						<form id="form" method="post" autocomplete='off'>
							<input type="text" name="begintime" id="begintime" size="20" value='${begintime}' readonly class='Wdate Input_2' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime\')}',isShowClear:false,readOnly:true,lang:'<%=datelang%>'})" />
							<%=lu.getLanguage(language,"phonelog.reach","至") %>
							<input type="text" name="endtime" id="endtime" size="20" value='${endtime }' readonly class='Wdate Input_2' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'begintime\')}',maxDate:'%y-%M-%d %H:%m:%s',isShowClear:false,readOnly:true,lang:'<%=datelang%>'})" />
							<c:if test="${orgid==90000}"><%=lu.getLanguage(language,"phonelog.orgid","企业ID") %>:<input name="searchorgid" id="searchorgid" style="width:80px"  maxlength="10" type="text" value="${searchorgid}"/></c:if>
							<input type="button" value="" class="searchbutton-log" onClick="selectPayHistory()" />
						</form>
					</div>
					<div id="uploadTip"><font style="line-height:25px; font-size:14px; font-weight:bold;height:25px;">
					<%=lu.getLanguage(language,"phonelog.orgid","企业ID") %>:${accountinfo.orgid},
					<%=lu.getLanguage(language,"phonecharge.remainmoney","余额") %>:${accountinfo.remainingsum} <%=lu.getLanguage(language,"phonelog.yuan","元") %></font></div>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabnew" id="tabnew">
					<thead>
						<tr><td colspan="9"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabtitle" id='tabtitle'>
						<tr>
							<td width="7%" class="tdTitle"><%=lu.getLanguage(language,"phonelog.orgid","企业ID") %></td>
							<td width="20%" class="tdTitle"><%=lu.getLanguage(language,"phonecharge.chargepaytime","扣费时间") %></td>
							<td width="12%" class="tdTitle"><%=lu.getLanguage(language,"phonecharge.chargepaymoney","扣费金额") %>(<%=lu.getLanguage(language,"phonelog.yuan","元") %>)</td>
							<td width="15%" class="tdTitle"><%=lu.getLanguage(language,"phonecharge.chargebeforemoney","扣费前金额") %>(<%=lu.getLanguage(language,"phonelog.yuan","元") %>)</td>
							<td width="15%" class="tdTitle"><%=lu.getLanguage(language,"phonecharge.chargeaftermoney","扣费后金额") %>(<%=lu.getLanguage(language,"phonelog.yuan","元") %>)</td>
							<td width="15%" class="tdTitle"><%=lu.getLanguage(language,"phonecharge.chargeprice","扣费单价") %>(<%=lu.getLanguage(language,"phonecharge.yuanfen","元/分钟") %>)</td>
							<td width="10%" class="tdTitle"><%=lu.getLanguage(language,"phonecharge.chargeoperateuser","扣费人员") %></td>
							<td width="6%" class="tdTitle"><%=lu.getLanguage(language,"operation","操作") %></td>
						</tr>
						</table></td></tr>
					</thead>
					<tbody>
					   <tr><td colspan="9"><table width="90%" border="0" cellpadding="0" cellspacing="0" class="tabcontent" id='tabcontent'>
					   <c:if test="${fn:length(phList)==0}"><tr><td colspan="7"><%=lu.getLanguage(language,"list.norecord","没有记录") %></td></tr></c:if>	
					   <c:forEach var="phf" items="${phList}" varStatus="id">
                         <!-- <tr bgcolor="" id="${phf.id}" title="${fn:replace(fn:replace(fn:replace(fn:replace(phf.param,'<countdata>','扣费数据条数：'),'<chargingtime>','计费时长：'),'<begintime>','扣费时间段开始:'),'<endtime>','扣费时间段结束:')}"> -->
                         <tr bgcolor="" id="${phf.id}" title="${phf.param}" class="chargedetail">
							<td width="7%">${phf.orgid}</td>
							<td width="20%">${phf.operatetime}</td>
							<td width="12%">${phf.realmoney}</td>
							<td width="15%">${phf.beforpay}</td>
							<td width="15%">${phf.afterpay}</td>
							<td width="15%">${phf.price}</td>
							<td width="10%">${phf.operateuser}</td>
							<td width="6%"><a href="javascript:void(0)" onclick="detail_charge(${phf.orgid},'${phf.param}')"><%=lu.getLanguage(language,"phonelog.detail","详情") %></a></td>
						</tr>
						</c:forEach>
						</table></td></tr>
					</tbody>
				</table>
				<seegle:paging action="PhonePayHistoryAction.go?begintime=${begintime}&endtime=${endtime}&sorgid=${searchorgid}&top=${top}&recordType=${recordType}"/>
				<div align="center">
					<a id="btndivSubmit" class="divbutton"  href="<%=siteurl %>/PhonePayHistoryExportServlet?btime=${begintime}&etime=${endtime}&t=3&c=${recordType}&id=${searchorgid}&language=<%=language %>"><span><%=lu.getLanguage(language,"button.export","导出") %></span></a>
				</div>
			</div>
		</div>
	</div>
