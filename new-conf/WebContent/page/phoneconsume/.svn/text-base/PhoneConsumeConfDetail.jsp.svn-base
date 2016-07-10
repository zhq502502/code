<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<div class="s_001"><%=lu.getLanguage(language,"phonelog.detailbyconf","通话详情-会议") %></div>
					<div class="s_002">
						<input type="hidden" name="method" value="confDetail"/>
						<input type="hidden" name="org" value="${org }"/>
						<input type="hidden" name="orderName" value="${orderName}"/>
						<input type="hidden" name="orderType" value="${orderType}"/>
						<%=lu.getLanguage(language,"phonelog.logintype","参会方式") %>
						<select name="loginFlag" style="margin: 0px;padding: 0px;width: 80px">
							<c:forEach items="${loginFlags}" var="f">
								<option value="${f.key }" <c:if test="${f.key eq loginFlag }">selected="selected"</c:if>>${f.value }</option>
							</c:forEach>
						</select>
						<input style="width: 160px" value='${nowtime }' name="btime" type="text" id="btime" size="22" class="Wdate Input_2" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'etime\')}',readOnly:true,lang:'<%=datelang%>'})"/>
						<%=lu.getLanguage(language,"phonelog.reach","至") %><input style="width: 160px" value='${nexttime }' name="etime" type="text" id="etime" size="22" class="Wdate Input_2" readonly onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'btime\')}',readOnly:true,lang:'<%=datelang%>'})"/>
						<input style="width:100px" type="hidden" id='searchKey' name="conf" maxlength='10' value="${conf}" class='Input09'>
						<input type="submit" id='searchbutton' name='searchbutton' value="" class="searchbutton-log"/>						
					</div>
					<c:if test="${orgid eq 90000&&!empty org}">
					<div id="uploadTip"><font style="line-height:25px; font-size:14px; font-weight:bold;height:25px;">
					<%=lu.getLanguage(language,"phonelog.orgid","企业ID") %>:${org}</font></div>
					</c:if>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabnew" id='tabnew'>
					<thead>
						<tr><td colspan="6"><table width="100%" id='tabtitle' class='tabtitle' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="7%" orderName="confid"><%=lu.getLanguage(language,"phonelog.confid","会议ID") %></td>
							<td width="10%" orderName="confname"><%=lu.getLanguage(language,"phonelog.confname","会议名称") %></td>
							<td width="12%" orderName="loginFlag"><%=lu.getLanguage(language,"phonelog.logintype","参会方式") %></td>
							<td width="14%" orderName="phoneNumber"><%=lu.getLanguage(language,"phonelog.phonenumber","参会电话") %></td>
							<td width="18%" orderName="btime"><%=lu.getLanguage(language,"phonelog.btime","入会时间") %></td>
							<td width="15%" orderName="consumeCount"><%=lu.getLanguage(language,"phonelog.countlogintime","参会时长") %></td>
							<td width="14%" orderName="chargingtime"><%=lu.getLanguage(language,"phonelog.chargetime","计费时长") %>（<%=lu.getLanguage(language,"phonelog.minute","分") %>）</td>
							<td width="10%" orderName="consumeAmount"><%=lu.getLanguage(language,"phonelog.cost","费用") %>（<%=lu.getLanguage(language,"phonelog.yuan1","元") %>）</td>
						</tr>
						</table></td></tr>
					</thead>
					<tbody>				
					<tr><td colspan="6"><table width="90%" border="0" cellpadding="0" cellspacing="0" class="tabcontent" id='tabcontent'>
					   <c:if test="${fn:length(list)==0}"><tr><td colspan="6"><%=lu.getLanguage(language,"list.norecord","没有记录") %>!</td></tr></c:if>	
					   <c:forEach var="conf" items="${list}" varStatus="id">
                         <tr bgcolor="#FFFFFF" id="${conf.confid}">
                          <td width="7%" >${conf.confid}</td>
							<c:choose>
                             <c:when test="${fn:length(conf.confname)>6}"><td title="${conf.confname}" width="10%">${fn:substring(conf.confname, 0, 5)}...</td></c:when>
                             <c:otherwise><td title="${conf.confname}" width="10%">${conf.confname}</td></c:otherwise>
                            </c:choose>
							<!--<c:choose>
                             <c:when test="${fn:length(conf.nickname)>6}"><td title="${conf.nickname}" width="10%">${fn:substring(conf.nickname, 0, 5)}...</td></c:when>
                             <c:otherwise><td title="${conf.nickname}" width="10%">${conf.nickname}</td></c:otherwise>
                            </c:choose>-->
                            <td width="12%">
                            <c:choose>
                             <c:when test="${conf.loginFlag eq 0}"><%=lu.getLanguage(language,"phonelog.loginbyphone","电话加入") %></c:when>
                             <c:when test="${conf.loginFlag eq 1}"><%=lu.getLanguage(language,"phonelog.loginbynet","网络邀请") %></c:when>
                             <c:when test="${conf.loginFlag eq 2}"><%=lu.getLanguage(language,"phonelog.loginphone","电话邀请") %></c:when>
                             <c:when test="${conf.loginFlag eq 3}"><%=lu.getLanguage(language,"phonelog.conflink","视频会议link") %></c:when>
                            </c:choose>
                            </td>
							<td width="14%" >${conf.phoneNumber}</td>
							<td width="18%" >${conf.btime}</td>
							<%
							if(language.equals("zh_tw")){
							%>
							<td width="15%">${fn:replace(fn:replace(fn:replace(conf.formatCount, "分", "分鐘"),"秒","秒"),"小时","小時")}</td>
							<%
							}else if(language.equals("en")){
							%>
							<td width="15%">${fn:replace(fn:replace(fn:replace(conf.formatCount, "分", "min"),"秒","s"),"小时","h")}</td>
							<%
							}else{
							%>
							<td width="15%">${conf.formatCount}</td>
							<%
							}
							%>
                            <td width="14%">${conf.chargingtime}</td>
							<td width="10%" >${conf.consumeAmount*1.0/100}</td>
                        </tr>
                      </c:forEach>
                      	<tr <c:if test="${fn:length(list)==0}">style="display:none"</c:if>>
                      		<td colspan="8"><%=lu.getLanguage(language,"phonelog.summaryinformation","汇总信息") %>--
                      		<%=lu.getLanguage(language,"phonelog.countrecord","总记录数") %>：${count.countData }，
                      		<%=lu.getLanguage(language,"phonelog.countlogintime","总参会时长") %>：
                      		<%
							if(language.equals("zh_tw")){
							%>
							${fn:replace(fn:replace(fn:replace(count.sumFormatCount, "分", "分鐘"),"秒","秒"),"小时","小時")}
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
				<c:if test="${fn:length(list)>0}">
				<seegle:paging action="PhoneConsumeAction.go?org=${org}&loginFlag=${loginFlag}&conf=${conf}&btime=${btime}&etime=${etime}&method=confDetail&orderName=${orderName }&orderType=${orderType }"/>
				</c:if>
				<div align="center">
					<input class="sbutton" value="<%=lu.getLanguage(language,"phonelog.return","返回") %>" type="button" onclick="javascript:location.href='${url_conf}';"/>
					<input class="sbutton" value="<%=lu.getLanguage(language,"button.export","导出") %>" type="button" onclick="javascript:window.open('PhoneConsumeAction.go?org=${org}&loginFlag=${loginFlag}&conf=${conf}&btime=${btime}&etime=${etime}&method=export&type=confDetail&orderName=${orderName }&orderType=${orderType }&language=<%=language %>');"/>
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
	$('#consume_conf').addClass("sel_tag");
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
	</script>