<%@page language="java" pageEncoding="UTF-8"%>
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
String token = session.getAttribute("token").toString();
String orgid = session.getAttribute("orgid").toString();
String sorgid = request.getAttribute("sorgid").toString();
%> 
<script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
<input name="top" id="top" type="hidden" value="${top}"/>
	<div id="wpbody-content">
		<div class="wrap">
			<div class="space_03">
				<div id="topLinkL">
					<div class="s_001"><%=lu.getLanguage(language,"confpage.menu.phoneconf.chargingsetting","计费管理") %></div>
				</div>
				<div id="uploadTip">
				   <p id="l_msg" class="ui-state-highlight" style="display:none;text-align:center;color:#FF0000"></p>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD" class="tamnew6">
				<form name="form" id="form" action="#" method="post" autocomplete='off'>
					<tr class="line">
				       <td colspan="4" style="padding-top: 10px"><label for="orgid"><%=lu.getLanguage(language,"phonelog.orgid","企业ID") %>:</label>&nbsp;&nbsp;
					<%
						if(orgid.equals("90000")){
					%>
							<input name="searchorgid" id="searchorgid" maxlength="10" size="12" type="text" value="${sorgid>0?sorgid:''}"/> &nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="btnSearch1" value="<%=lu.getLanguage(language,"button.search","搜索") %>" class="sbutton" onClick="showOrgInfo()"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<%
						}else{
					%>
							<input name="orgid" id="orgid" maxlength="10" size="12" type="text" disabled value="${orgid}"/> &nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
					<%
						}
					%>
				       <%=lu.getLanguage(language,"phonecharge.orgname","企业名称") %>:${orgname}&nbsp;&nbsp;&nbsp;&nbsp;
				       <%=lu.getLanguage(language,"phonecharge.remainmoney","余额") %>:${accountinfo.remainingsum} <%=lu.getLanguage(language,"phonelog.yuan","元") %></td>
					</tr>
					<tr>
				       <td colspan="4"><font color="#3399FF"><%=lu.getLanguage(language,"phonecharge.charging","电话会议充值") %></font></td>
					</tr>
					<tr>
				       <td align="right"><%=lu.getLanguage(language,"phonecharge.paymoney","充值金额") %>:</td>
				       <td><input name="realmoney" id="realmoney" size="14" maxlength="12" type="text" value=""/> <%=lu.getLanguage(language,"phonelog.yuan","元") %></td>
				       <td align="right"><%=lu.getLanguage(language,"phonecharge.lessenmoney","优惠金额") %>: </td>
				       <td><input name="lessenmoney" id="lessenmoney" size="14" maxlength="12"  type="text" value=""/> <%=lu.getLanguage(language,"phonelog.yuan","元") %> &nbsp;&nbsp;&nbsp;&nbsp;
				       <input type="button" id="showpayhistory" value="<%=lu.getLanguage(language,"confpage.menu.phoneconf.payhistory","充值记录") %>" class="sbutton"/></td>
					</tr>
					<tr class="">
				       <td align="right"><input name="editprice" id="editprice" type="checkbox" value="" onClick="openeditprice()"/> <%=lu.getLanguage(language,"phonecharge.price","单价") %>: </td>
				       <td><input name="price" id="price" size="14" maxlength="12"  type="text" value="${priceinfo.price}" style="background-color:#ccc" readonly/> <%=lu.getLanguage(language,"phonecharge.yuanfen","元/分钟") %></td>
				       <td colspan="2"><%=lu.getLanguage(language,"phonecharge.pricenote","注意：修改单价后，当前企业电话会议以新单价计费") %>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="show_paydis()"><%=lu.getLanguage(language,"phonecharge.paydis","充值说明") %></a>
				       </td>
					</tr>
					<tr class="line">
				       <td colspan="4" align="center" ><textarea id="paydiscaption" style="display: none" rows="2" cols="80"></textarea> </td>
					</tr>
					<tr>
				       <td colspan="4" ><font color="#3399FF"><%=lu.getLanguage(language,"phonecharge.excess","超额计费") %></font></td>
					</tr>
					<tr>
				       <td colspan="4" >
				       <c:choose><c:when test="${priceinfo.allowoverfulfil==1}"><input name="startexcess" id="startexcess" type="checkbox" checked onClick="editexcessprice()"/></c:when>
				       <c:otherwise><input name="startexcess" id="startexcess" type="checkbox" onClick="editexcessprice()"/></c:otherwise></c:choose>
				       <%=lu.getLanguage(language,"phonecharge.startexcess","启用超额计费") %></font></td>
					</tr>
					<tr class="line">
				       <td  align="right"><%=lu.getLanguage(language,"phonecharge.excesssum","超额额度") %>：</td>
				       <td colspan="3"><input name="excessprice" id="excessprice" size="14" maxlength="12" type="text" value="${accountinfo.overfulfilsum}" style="background-color:#ccc" readonly/> <%=lu.getLanguage(language,"phonelog.yuan","元") %></td> 
					</tr>
					<tr>
				       <td colspan="4"><font color="#3399FF"><%=lu.getLanguage(language,"phonecharge.remind","欠费提醒") %></font></td>
					</tr>
					<tr class="line">
				       <td align="right"><%=lu.getLanguage(language,"phonecharge.remindsum","欠费提醒金额设置") %>：</td>
				       <td><input name="remindsum" id="remindsum" size="14" maxlength="12" type="text" value="${accountinfo.remindsum}"/> <%=lu.getLanguage(language,"phonelog.yuan","元") %></td>
				       <td colspan="2"><%=lu.getLanguage(language,"phonecharge.remindnote","（当余额小于等于该数值时，系统自动发送欠费提醒邮件。）") %></td>
					</tr>
					<tr>
				       <td colspan="4" ><font color="#3399FF"><%=lu.getLanguage(language,"phonecharge.remindobj","欠费提醒对象") %></font></td>
					</tr>
					<tr>
				       <td align="right"><%=lu.getLanguage(language,"phonecharge.remindname","姓名") %>:</td>
				       <input name="remindaccount1" id="remindaccount1" size="20" type="hidden" value="${remindaccount1}"/>
				       <td><input name="remindname1" id="remindname1" size="20" type="text" value="${remindname1}"/></td>
				       <td align="right"><%=lu.getLanguage(language,"phonecharge.remindmail","邮箱") %>:</td>
				       <td><input name="remindmail1" id="remindmail1" size="20" type="text" value="${remindmail1}"/></td>
					</tr>
					<tr>
				       <td align="right"><%=lu.getLanguage(language,"phonecharge.remindname","姓名") %>:</td>
				       <input name="remindaccount2" id="remindaccount2" size="20" type="hidden" value="${remindaccount2}"/>
				       <td><input name="remindname2" id="remindname2" size="20" type="text" value="${remindname2}"/></td>
				       <td align="right"><%=lu.getLanguage(language,"phonecharge.remindmail","邮箱") %>:</td>
				       <td><input name="remindmail2" id="remindmail2" size="20" type="text" value="${remindmail2}"/></td>
					</tr>
					<tr>
				       <td align="right"><%=lu.getLanguage(language,"phonecharge.remindname","姓名") %>:</td>
				       <input name="remindaccount3" id="remindaccount3" size="20" type="hidden" value="${remindaccount3}"/>
				       <td><input name="remindname3" id="remindname3" size="20" type="text" value="${remindname3}"/></td>
				       <td align="right"><%=lu.getLanguage(language,"phonecharge.remindmail","邮箱") %>:</td>
				       <td><input name="remindmail3" id="remindmail3" size="20" type="text" value="${remindmail3}"/></td>
					</tr>									
					<tr>
						<td colspan="4" valign="middle" style="height:40px; text-align:center;">
							<input type="button" id="btnSearch" value="<%=lu.getLanguage(language,"button.modify","修改") %>" onclick="validatedate()" class="sbutton" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    <%--<input type="button" id="reset_btn" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" /> --%>
						</td>
					</tr>
					</form>	
					</table>	
			</div>		
		</div>
		<div id='confirm-container' style='display: none;; outline: 0px none; width: 300px; overflow: visible;background: #F1F6FD;text-align: left;border: 0px;border: 1px solid #cccccc'>
			<div class='message' style='text-align: left; line-height:25px;font-size:13px;'>
				<font style='font-weight:bold;'><%=lu.getLanguage(language,"phonecharge.phoneconfpayconfirm","电话会议充值确认") %>:</font><br class="clear" />
				&nbsp;&nbsp;&nbsp;&nbsp;<%=lu.getLanguage(language,"phonelog.orgid","企业ID") %>: <label id="confirm_orgid"></label><br class="clear" />
				&nbsp;&nbsp;&nbsp;&nbsp;<%=lu.getLanguage(language,"phonecharge.orgname","企业名称") %>: ${orgname}<br class="clear" />
				&nbsp;&nbsp;&nbsp;&nbsp;<%=lu.getLanguage(language,"phonecharge.paymoney","充值金额") %>: <label id="confirm_realmoney"></label><%=lu.getLanguage(language,"phonelog.yuan","元") %><br class="clear" />
				&nbsp;&nbsp;&nbsp;&nbsp;<%=lu.getLanguage(language,"phonecharge.lessenmoney","优惠金额") %>: <label id="confirm_lessenmoney"></label><%=lu.getLanguage(language,"phonelog.yuan","元") %><br class="clear" />
				&nbsp;&nbsp;&nbsp;&nbsp;<%=lu.getLanguage(language,"phonecharge.price","单价") %>: <label id="confirm_price"></label><%=lu.getLanguage(language,"phonecharge.yuanfen","元/分钟") %><br class="clear" />
				&nbsp;&nbsp;&nbsp;&nbsp;<label id="confirm_excessprice"></label><br class="clear" />
				<br class="clear" />
				<div style="text-align: center; "><input type="button" value="<%=lu.getLanguage(language,"button.ok","确认") %>" class="sbutton" onclick="datasub()" id="dialog_ok" />&nbsp;&nbsp;&nbsp;
				<input type="button" id="reset_btn" value="<%=lu.getLanguage(language,"button.cancel","取消") %>" class="sbutton" />
			</div>
		</div>
	</div>
<script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
<script type="text/javascript">
function validatedate(){
	var siteurl = $('#siteurl').val();
	var sorgid = $('#searchorgid').val();
	var orgname = $('#orgname').val();
	var searchorgid = $('#searchorgid').val();
	searchorgid ="${sorgid}";
	/*if(searchorgid==null||searchorgid==""||searchorgid=="undifined"){
		searchorgid = $('#orgid').val();
	}*/
	var realmoney = $('#realmoney').val();
	var lessenmoney = $('#lessenmoney').val();
	var price = $('#price').val();
	var allowexcess = "0";
	if(document.getElementById("startexcess").checked == true){
		var allowexcess = "1";
	}
	var param = $('#paydiscaption').val();
	var excessprice = $('#excessprice').val();
	var remindsum = $('#remindsum').val();
	var remindaccount1 = $('#remindaccount1').val();
	var remindname1 = $('#remindname1').val();
	var remindmail1 = $('#remindmail1').val();
	var remindaccount2 = $('#remindaccount2').val();
	var remindname2 = $('#remindname2').val();
	var remindmail2 = $('#remindmail2').val();
	var remindaccount3 = $('#remindaccount3').val();
	var remindname3 = $('#remindname3').val();
	var remindmail3 = $('#remindmail3').val();
	var moneyRegex = /^(-)?\d+(.\d{1,2})?$/;
	var isUInt = /^[1-9]\d+$/;
	var emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
	if($.trim(searchorgid)==""){
		jQuery('#l_msg').text(getMsg(ERR_NO_ORGID));
		jQuery('#searchorgid').focus();
		jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		return false;
	}else if(!searchorgid.match(isUInt)){
			$('#l_msg').text(getMsg(ERR_ORGID_ERROR));
		$('#searchorgid').focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
		return false;
		}else if(!realmoney.match(moneyRegex)&&realmoney!=""){
			$('#l_msg').text(getMsg(PHONE_CHARGE_PAYMONEYFORMATERROR));
		$('#realmoney').focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
		return false;
		}else if(!lessenmoney.match(moneyRegex)&&lessenmoney!=""){
			$('#l_msg').text(getMsg(PHONE_CHARGE_LESSMONEYFORMATERROR));
		$('#lessenmoney').focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
		return false;
		}else if(!price.match(moneyRegex)&&price!=""){
			$('#l_msg').text(getMsg(PHONE_CHARGE_PRICEFORMATERROR));
		$('#price').focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
		return false;
		}else if(!excessprice.match(moneyRegex)&&excessprice!=""){
			$('#l_msg').text(getMsg(PHONE_CHARGE_EXCESSFORMATERROR));
		$('#excessprice').focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
		return false;
		}else if(!remindsum.match(moneyRegex)&&remindsum!=""){
			$('#l_msg').text(getMsg(PHONE_CHARGE_REMINDMONEYFORMATERROR));
		$('#remindsum').focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
		return false;
		}else if(!remindmail1.match(emailRegex)&&remindmail1!=""){
			$('#l_msg').text(getMsg(PHONE_CHARGE_EMAILFORMATERROR));
		$('#remindmail1').focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
		return false;
		}else if(!remindmail2.match(emailRegex)&&remindmail2!=""){
			$('#l_msg').text(getMsg(PHONE_CHARGE_EMAILFORMATERROR));
		$('#remindmail2').focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
		return false;
		}else if(!remindmail3.match(emailRegex)&&remindmail3!=""){
			$('#l_msg').text(getMsg(PHONE_CHARGE_EMAILFORMATERROR));
		$('#remindmail3').focus();
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
		return false;
		}else{
			var divorgid=document.getElementById("confirm_orgid");
			var divrealmoney=document.getElementById("confirm_realmoney");
			var divlessenmoney=document.getElementById("confirm_lessenmoney");
			var divprice=document.getElementById("confirm_price");
			var divexcessprice=document.getElementById("confirm_excessprice");
			divorgid.innerHTML=searchorgid;
			if(realmoney==""){
				divrealmoney.innerHTML="0";
			}else{
				divrealmoney.innerHTML=realmoney;
			}
			if(lessenmoney==""){
				divlessenmoney.innerHTML="0";
			}else{
				divlessenmoney.innerHTML=lessenmoney;
			}
			if(price==""){
				divprice.innerHTML="0";
			}else{
				divprice.innerHTML=price;
			}
			if(allowexcess=="0"){
				divexcessprice.innerHTML=getMsg(PHONE_CHARGE_NOEXCESS);
			}else{
				divexcessprice.innerHTML=getMsg(PHONE_CHARGE_STARTEXCESS)+excessprice+'<%=lu.getLanguage(language,"phonelog.yuan","元") %>';
			}
			
			$('#confirm-container').dialog({
				modal:true,
				position:"20%",
				width:350,
				height:300,
				title:"<span style='color:#FFFFFF;'><%=lu.getLanguage(language,"phonecharge.payconfirm","充值确认") %></span>"
				
			});
			$("#ui-dialog-title-confirm-container").css({"height":"25px","float":"left","padding":"6px 0 0 6px"});
			$(".ui-dialog-titlebar-close").css("float","right");
			$(".ui-dialog-titlebar-close").css({"width":"16px","height":"16px","margin":"6px 6px 0 0"});
			$(".ui-dialog-titlebar").css("background-image","");
			$(".ui-dialog-titlebar").css("background-color","#348CD4");
			$("#confirm-container").css("border","0");
		}
}
function datasub(){
	var siteurl = $('#siteurl').val();
	var sorgid = $('#searchorgid').val();
	var orgname = $('#orgname').val();
	var searchorgid = $('#searchorgid').val();
	searchorgid ="${sorgid}";
	/*if(searchorgid==null||searchorgid==""||searchorgid=="undifined"){
		searchorgid = $('#orgid').val();
	}*/
	var realmoney = $('#realmoney').val();
	var lessenmoney = $('#lessenmoney').val();
	var price = $('#price').val();
	var allowexcess = "0";
	if(document.getElementById("startexcess").checked == true){
		var allowexcess = "1";
	}
	var param = $('#paydiscaption').val();
	var excessprice = $('#excessprice').val();
	var remindsum = $('#remindsum').val();
	var remindaccount1 = $('#remindaccount1').val();
	var remindname1 = $('#remindname1').val();
	var remindmail1 = $('#remindmail1').val();
	var remindaccount2 = $('#remindaccount2').val();
	var remindname2 = $('#remindname2').val();
	var remindmail2 = $('#remindmail2').val();
	var remindaccount3 = $('#remindaccount3').val();
	var remindname3 = $('#remindname3').val();
	var remindmail3 = $('#remindmail3').val();
	$("#dialog_ok").attr("disabled",true);
	$("#dialog_ok").addClass("unclickbut");
	$("#btnSearch").attr("disabled",true);
	$("#btnSearch").addClass("unclickbut");
	$("#reset_btn").attr("disabled",true);
	$("#reset_btn").addClass("unclickbut");
	$.post(siteurl+"/charge/mod", {param:param,corgid: searchorgid, realmoney: realmoney, lessenmoney: lessenmoney, price: price, allowexcess:allowexcess,excessprice: excessprice,remindsum: remindsum, remindaccount1: remindaccount1,remindname1: remindname1, remindmail1: remindmail1, remindaccount2: remindaccount2,remindname2: remindname2, remindmail2: remindmail2, remindaccount3: remindaccount3,remindname3: remindname3, remindmail3: remindmail3, _: new Date().getTime() } ,function(rs) {
		subing = false;
		if(rs==0){
			alert(getMsg(MOD_SUCESS));
		} 
		else {
			alert(getMsg(MOD_FAILURE));
		}
		$("#dialog_ok").attr("disabled",false);
		$("#dialog_ok").removeClass("unclickbut");
 		$("#btnSearch").attr("disabled",false);
		$("#btnSearch").removeClass("unclickbut");
 		$("#reset_btn").attr("disabled",false);
		$("#reset_btn").removeClass("unclickbut");
		$('#confirm-container').dialog("close");
	});	
}
var subing = false;
$(document).ready(function() {
	$('#reset_btn').click(function(){
		$('#confirm-container').dialog("close");
	});	 
	if("${orgstatus}"=="1"){
		$("#btnSearch").attr("disabled",true);
		$("#btnSearch").addClass("unclickbut");
	}else{
		
	}
	editexcessprice();
	var siteurl = $('#siteurl').val();
	var sorgid = $('#searchorgid').val();
	/*if(sorgid==null||sorgid==""||sorgid=="undifined"){
		sorgid = $('#orgid').val();
	}*/
	var top = $('#top').val();
	$('#phoneconf_chargingedit').addClass("sel_tag");
	$("#showpayhistory").click(function(){
		if(top=="top"){
			location.href=siteurl+"/TopConfPage.go?inc=PayHistory&sorgid="+sorgid;
		}else{
			location.href=siteurl+"/ConfPage.go?inc=PayHistory&sorgid="+sorgid;
		}
	});
	$("#searchorgid").keydown(function(event){
    	if(event.keyCode==13){
    		showOrgInfo();
        	return false;
    	}
    });
	jQuery('#form').submit(function(){
		var orgname = $('#orgname').val();
		var searchorgid = $('#searchorgid').val();
		searchorgid ="${sorgid}";
		/*if(searchorgid==null||searchorgid==""||searchorgid=="undifined"){
			searchorgid = $('#orgid').val();
		}*/
		var realmoney = $('#realmoney').val();
		var lessenmoney = $('#lessenmoney').val();
		var price = $('#price').val();
		var allowexcess = "0";
		if(document.getElementById("startexcess").checked == true){
			var allowexcess = "1";
		}
		var param = $('#paydiscaption').val();
		var excessprice = $('#excessprice').val();
		var remindsum = $('#remindsum').val();
		var remindaccount1 = $('#remindaccount1').val();
		var remindname1 = $('#remindname1').val();
		var remindmail1 = $('#remindmail1').val();
		var remindaccount2 = $('#remindaccount2').val();
		var remindname2 = $('#remindname2').val();
		var remindmail2 = $('#remindmail2').val();
		var remindaccount3 = $('#remindaccount3').val();
		var remindname3 = $('#remindname3').val();
		var remindmail3 = $('#remindmail3').val();
		var moneyRegex = /^(-)?\d+(.\d{2})?$/;
		var isUInt = /^[1-9]\d+$/;
		var emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
		if($.trim(searchorgid)==""){
			jQuery('#l_msg').text(getMsg(ERR_NO_ORGID));
			jQuery('#searchorgid').focus();
			jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
			return false;
		}else if(!searchorgid.match(isUInt)){
 			$('#l_msg').text(getMsg(ERR_ORGID_ERROR));
			$('#searchorgid').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
 		}else if(!realmoney.match(moneyRegex)&&realmoney!=""){
 			$('#l_msg').text(getMsg(PHONE_CHARGE_PAYMONEYFORMATERROR));
			$('#realmoney').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
 		}else if(!lessenmoney.match(moneyRegex)&&lessenmoney!=""){
 			$('#l_msg').text(getMsg(PHONE_CHARGE_LESSMONEYFORMATERROR));
			$('#lessenmoney').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
 		}else if(!price.match(moneyRegex)&&price!=""){
 			$('#l_msg').text(getMsg(PHONE_CHARGE_PRICEFORMATERROR));
			$('#price').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
 		}else if(!excessprice.match(moneyRegex)&&excessprice!=""){
 			$('#l_msg').text(getMsg(PHONE_CHARGE_EXCESSFORMATERROR));
			$('#excessprice').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
 		}else if(!remindsum.match(moneyRegex)&&remindsum!=""){
 			$('#l_msg').text(getMsg(PHONE_CHARGE_REMINDMONEYFORMATERROR));
			$('#remindsum').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
 		}else if(!remindmail1.match(emailRegex)&&remindmail1!=""){
 			$('#l_msg').text(getMsg(PHONE_CHARGE_EMAILFORMATERROR));
			$('#remindmail1').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
 		}else if(!remindmail2.match(emailRegex)&&remindmail2!=""){
 			$('#l_msg').text(getMsg(PHONE_CHARGE_EMAILFORMATERROR));
			$('#remindmail2').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
 		}else if(!remindmail3.match(emailRegex)&&remindmail3!=""){
 			$('#l_msg').text(getMsg(PHONE_CHARGE_EMAILFORMATERROR));
			$('#remindmail3').focus();
			$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
			return false;
 		}else{
	 		
			var divorgid=document.getElementById("confirm_orgid");
			var divrealmoney=document.getElementById("confirm_realmoney");
			var divlessenmoney=document.getElementById("confirm_lessenmoney");
			var divprice=document.getElementById("confirm_price");
			var divexcessprice=document.getElementById("confirm_excessprice");
			divorgid.innerHTML=searchorgid;
			if(realmoney==""){
				divrealmoney.innerHTML="0";
			}else{
				divrealmoney.innerHTML=realmoney;
			}
			if(lessenmoney==""){
				divlessenmoney.innerHTML="0";
			}else{
				divlessenmoney.innerHTML=lessenmoney;
			}
			if(price==""){
				divprice.innerHTML="0";
			}else{
				divprice.innerHTML=price;
			}
			if(allowexcess=="0"){
				divexcessprice.innerHTML=getMsg(PHONE_CHARGE_NOEXCESS);
			}else{
				divexcessprice.innerHTML=getMsg(PHONE_CHARGE_STARTEXCESS)+excessprice+getMsg(PHONE_CHARGE_PRICEUNIT);
			}
			
			$('#confirm-container').dialog({
				modal:true,
				position:"20%",
				width:350,
				height:300,
				title:"<span style='color:#FFFFFF;'><%=lu.getLanguage(language,"phonecharge.payconfirm","充值确认") %></span>"
				
			});
			$("#ui-dialog-title-confirm-container").css({"height":"25px","float":"left","padding":"6px 0 0 6px"});
			$(".ui-dialog-titlebar-close").css("float","right");
			$(".ui-dialog-titlebar-close").css({"width":"16px","height":"16px","margin":"6px 6px 0 0"});
			$(".ui-dialog-titlebar").css("background-image","");
			$(".ui-dialog-titlebar").css("background-color","#348CD4");
			$("#confirm-container").css("border","0");
			$('#dialog_ok').click(function(){
				if(subing){
					return ;
				}else{
					subing = true;
				}
				$("#dialog_ok").attr("disabled",true);
				$("#dialog_ok").addClass("unclickbut");
				$("#btnSearch").attr("disabled",true);
				$("#btnSearch").addClass("unclickbut");
				$.post(siteurl+"/charge/mod", {param:param,corgid: searchorgid, realmoney: realmoney, lessenmoney: lessenmoney, price: price, allowexcess:allowexcess,excessprice: excessprice,remindsum: remindsum, remindaccount1: remindaccount1,remindname1: remindname1, remindmail1: remindmail1, remindaccount2: remindaccount2,remindname2: remindname2, remindmail2: remindmail2, remindaccount3: remindaccount3,remindname3: remindname3, remindmail3: remindmail3, _: new Date().getTime() } ,function(rs) {
					subing = false;
					if(rs==0){
						alert(getMsg(MOD_SUCESS));
					} 
					else {
						alert(getMsg(MOD_FAILURE));
					}
					$("#dialog_ok").attr("disabled",false);
					$("#dialog_ok").removeClass("unclickbut");
			 		$("#btnSearch").attr("disabled",false);
					$("#btnSearch").removeClass("unclickbut");
					$('#confirm-container').dialog("close");
				});	
			});
			
			
			$('#dialog_cancel').click(function(){
				$("#btnSearch").attr("disabled",false);
				$("#btnSearch").removeClass("unclickbut");
				$.modal.close();
			});	 			
 			return false;
 		}
		return false;
	});
	
});

function openeditprice(){
	var price = document.getElementById("price");  
          if (document.getElementById("editprice").checked == true) {  
          	price.readOnly = false;  
          	price.style.cssText = "backgroundColor:#fff";  
          }  
          else {  
              document.getElementById("price").readOnly = true;  
              price.style.cssText = "background-color:#cccccc";  
          }  
}

function editexcessprice(){
	var excessprice = document.getElementById("excessprice"); 
	var startexcess = document.getElementById("startexcess");
          if (startexcess.checked == true) {  
          	excessprice.readOnly = false;  
          	excessprice.style.cssText = "backgroundColor:#fff";  
          }  
          else {  
          	excessprice.readOnly = true;  
              excessprice.style.cssText = "background-color:#cccccc";  
          }  
}

function showOrgInfo(){
	var top = $('#top').val();
    sorgid = document.forms[0].searchorgid.value;
    /*if(sorgid==null||sorgid==""||sorgid=="undifined"){
		sorgid = $('#orgid').val();
	}*/
    var isUInt = /^[1-9]\d+$/;
    if($.trim(sorgid)==""){
		jQuery('#l_msg').text(getMsg(ERR_NO_ORGID));
		jQuery("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 3000).fadeOut('fast');
		return false;
	}else if(!sorgid.match(isUInt)){
			$('#l_msg').text(getMsg(ERR_ORGID_ERROR));
		$("#l_msg").fadeIn('fast').animate({opacity: 1.0}, 1000).fadeOut('fast');
		return false;
		}else{
			if(top=="top"){
			document.forms[0].action = "PhoneChargingSettingAction.go?sorgid="+sorgid+"&top=top";
		}else{
			document.forms[0].action = "PhoneChargingSettingAction.go?sorgid="+sorgid;
		}
	    document.forms[0].submit();
		} 
}
function show_paydis(){
	$("#paydiscaption").toggle();
	$("#paydiscaption").val("");
}
</script>