<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="seegle"  uri="/WEB-INF/taglib/seegle.tld" %>
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

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="wpbody-content">
	 <form name="form" id="form" action="PhoneConsumeAction.go" method="post">   					
		<div class="wrap">
			<div class="space_03">
				<div id="topLinkL">
					<div class="s_001"><%=lu.getLanguage(language,"phonelog.logbyorg","通话记录-企业") %></div>
					<div class="s_002">
						<input type="hidden" name="method" value="org"/>
						<input type="hidden" name="orderName" value="${orderName}"/>
						<input type="hidden" name="orderType" value="${orderType}"/>
						<!-- 参会方式 -->
						<select name="loginFlag" style="margin: 0px;padding: 0px;display: none">
							<c:forEach items="${loginFlags}" var="f">
								<option value="${f.key }" <c:if test="${f.key eq loginFlag }">selected="selected"</c:if>>${f.value }</option>
							</c:forEach>
						</select>
						<input style="width: 160px" value='${nowtime }' name="btime" type="text" id="btime" size="22" class="Wdate Input_2" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'etime\')}',readOnly:true,lang:'zh-cn'})"/>
						至<input style="width: 160px" value='${nexttime }' name="etime" type="text" id="etime" size="22" class="Wdate Input_2" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'btime\')}',readOnly:true,lang:'zh-cn'})"/>
						<input style="width:100px" type="text" id='searchKey' name="org" maxlength='10' value="${org}" class='Input09'>
						<input type="submit" id='searchbutton' name='searchbutton' value="" class="searchbutton-log"/>						
					</div>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabnew" id='tabnew'>
					<thead>
						<tr><td colspan="6"><table width="100%" id='tabtitle' class='tabtitle' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="8%" orderName="orgid"><%=lu.getLanguage(language,"phonelog.orgid","企业ID") %></td>
							<td width="13%" orderName="countConf"><%=lu.getLanguage(language,"phonelog.countconf","参会会议总数") %></td>
							<td width="14%" orderName="countPhonenumber"><%=lu.getLanguage(language,"phonelog.countphone","参会号码总数") %></td>
							<td width="14%" orderName="sumTime"><%=lu.getLanguage(language,"phonelog.sumtime","总时长") %></td>
							<td width="17%" orderName="sumChargingtime"><%=lu.getLanguage(language,"phonelog.sumchargetime","总计费时长") %>(<%=lu.getLanguage(language,"phonelog.minute","分") %>)</td>
							<td width="12%" orderName="sumCost"><%=lu.getLanguage(language,"phonelog.sumcost","总费用") %>(<%=lu.getLanguage(language,"phonelog.yuan","元") %>)</td>
							<td width="12%" orderName="countLogin"><%=lu.getLanguage(language,"phonelog.countlogin","总参会次数") %></td>
							<td width="10%" ><%=lu.getLanguage(language,"operation","操作") %></td>
						</tr>
						</table></td></tr>
					</thead>
					<tbody>				
					<tr><td colspan="6"><table width="90%" border="0" cellpadding="0" cellspacing="0" class="tabcontent" id='tabcontent'>
					   <c:if test="${fn:length(list)==0}"><tr><td colspan="6"><%=lu.getLanguage(language,"list.norecord","没有记录") %>!</td></tr></c:if>	
					   <c:forEach var="conf" items="${list}" varStatus="id">
                         <tr bgcolor="#FFFFFF" id="${conf.orgid }">
                            <td width="8%">${conf.orgid}</td>
                            <td width="13%"><a href="PhoneConsumeAction.go?method=conf&org=${conf.orgid}&loginFlag=${loginFlag}&btime=${btime}&etime=${etime}">${conf.countConf}</a></td>
                            <td width="14%"><a href="PhoneConsumeAction.go?method=phone&org=${conf.orgid}&loginFlag=${loginFlag}&btime=${btime}&etime=${etime}">${conf.countPhonenumber}</a></td>
                           	<%
							if(language.equals("zh_tw")){
							%>
							<td width="14%">${fn:replace(fn:replace(fn:replace(conf.sumFormatCount, "分钟", "分鐘"),"秒","秒"),"小时","小時")}</td>
							<%
							}else if(language.equals("en")){
							%>
							<td width="14%">${fn:replace(fn:replace(fn:replace(conf.sumFormatCount, "分钟", "min"),"秒","s"),"小时","h")}</td>
							<%
							}else{
							%>
							<td width="14%">${conf.sumFormatCount}</td>
							<%
							}
							%>
                            <td width="17%">${conf.sumChargingtime}</td>
                            <td width="12%">${conf.sumCost*1.0/100}</td>
                            <td width="12%">${conf.countLogin}</td>
							<td width="10%">
							<a href="PhoneConsumeAction.go?method=conf&org=${conf.orgid}&loginFlag=${loginFlag}&btime=${btime}&etime=${etime}"><%=lu.getLanguage(language,"phonelog.detail","详情") %></a>
							</td>
                        </tr>
                      </c:forEach>
                      	<tr <c:if test="${fn:length(list)==0}">style="display:none"</c:if>>
                      		<td colspan="8"><%=lu.getLanguage(language,"phonelog.summaryinformation","汇总信息") %>-- 
                      		<%=lu.getLanguage(language,"phonelog.countrecord","总记录数") %>：${count.countData }，
                      		<%=lu.getLanguage(language,"phonelog.countlogintime","总参会时长") %>：
                      		<%
							if(language.equals("zh_tw")){
							%>
							${fn:replace(fn:replace(fn:replace(count.sumFormatCount, "分钟", "分鐘"),"秒","秒"),"小时","小時")}
							<%
							}else if(language.equals("en")){
							%>
							${fn:replace(fn:replace(fn:replace(count.sumFormatCount, "秒","s"),"分钟", "min"),"小时","h")}
							<%
							}else{
							%>
							${count.sumFormatCount}
							<%
							}
							%>，
                      		<%=lu.getLanguage(language,"phonelog.sumchargetime","总计费时长") %>：${count.sumChargingtime}<%=lu.getLanguage(language,"phonelog.minute1","分钟") %>，
                      		<%=lu.getLanguage(language,"phonelog.sumcost","总费用") %>：${count.sumCost*1.0/100 }<%=lu.getLanguage(language,"phonelog.yuan","元") %>，
                      		<%=lu.getLanguage(language,"phonelog.countlogin","总参会次数") %>：${count.countLogin }
                      		</td>
                      	</tr>
						</table></td></tr>
					</tbody>
				</table>
				<br />
				<c:if test="${fn:length(list)>0}">
				<seegle:paging action="PhoneConsumeAction.go?btime=${btime}&etime=${etime}&org=${conf.orgid}&loginFlag=${loginFlag}&method=org&orderName=${orderName }&orderType=${orderType }"/>
				</c:if>
				<div align="center" >
					<input class="sbutton" value="<%=lu.getLanguage(language,"button.export","导出") %>" type="button" onclick="javascript:window.open('PhoneConsumeAction.go?btime=${btime}&etime=${etime}&org=${conf.orgid}&loginFlag=${loginFlag}&method=export&type=org&orderName=${orderName }&orderType=${orderType }&language=<%=language %>');"/>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	  </form>
	</div>
	</body>
	<script type="text/javascript" src="js/consumeOrder.js"></script>
	<script src="js/date/WdatePicker.js"></script>
	<script>
	var consumeOrderName="${orderName}";
	var consumeOrderType="${orderType}";
	$('#consume_org').addClass("sel_tag");
	function selectBtime(){
		WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'etime\')}',isShowClear:false,readOnly:true,lang:'zh-cn'});
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
	</script>