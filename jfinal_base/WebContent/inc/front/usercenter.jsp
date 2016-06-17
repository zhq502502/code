<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html enabled="true" compressJavaScript="true" compressCss="true">
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="head.jsp" %>
</head>
<body style="background-color: #ddd">
<div class="main" style="background-color: #ddd">
	<div class="usercenter-info" >
		<div class="userinfo-left">
			<img alt="" src="${userimg }">
		</div>
		<div class="userinfo-right">
		<input type="text" value="${id}" hidden="hidden">
			<h3>${nickname }</h3>
			<h5 >${userstate }</h5>
			<h5 ><a href="#" id="userstate2" onclick="changestate();return false" >成为正式会员</a></h5>
		</div>
	</div>
	<ul class="usercenter-op">
		<li  class="li-click"><span class="s-left glyphicon glyphicon-shopping-cart" style="color:#FF9966 "></span><span class="s-center">消费记录</span><span class="s-right glyphicon glyphicon-chevron-right"></span></li>
		<li class="li-hide">
		 <table class="table  panel-body">
			  <tr class="">
			  	<th>消费时间</th>
			  	<th>消费金额</th>
			  	<th>是否优惠</th>
			  </tr>
			  <c:forEach items="${list}" var="skt">
			  <tr onclick="javascript:location.href='recorddetail/${skt.skf30}'">
			  	<td>${skt.skf34}</td>
			  	<td>${skt.skf33}</td>
			  	<td>${skt.skf37}</td>
			  </tr>
			  </c:forEach>
			</table>
		</li>
		<li style="display: none;" class="li-click"><span class="s-left glyphicon glyphicon-shopping-cart" style="color:#FF9966 "></span><span class="s-center" onclick="location.href='company'">企业注册</span><span class="s-right glyphicon glyphicon-chevron-right"></span></li>
	</ul>
</div>
<script type="text/javascript">



function changestate(){
	if($("#userstate2").html()=="支付进行中..."){
		alert("支付进行中...");
		return;
	}
	WeixinJSBridge.invoke(
       'getBrandWCPayRequest', {
           "appId":"${appId}",     //公众号名称，由商户传入     
           "timeStamp":"${timeStamp}",         //时间戳，自1970年以来的秒数     
           "nonceStr":"${nonceStr}", //随机串     
           "package":"${packages}",     
           "signType":"${signType}",         //微信签名方式：     
           "paySign":"${paySign}" //微信签名 
       },
       function(res){ 
    	   //alert(res.err_msg);
           if(res.err_msg == "get_brand_wcpay_request:ok") {
        	   //alert("支付完成");
        	   //alert("支付ok");
        	   //location.href="weixinlogin";
           }   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
    	   if(res.err_msg!="get_brand_wcpay_request:cancel"&&res.err_msg!="get_brand_wcpay_request:fail"){
	    	   getPayResult();
    	   }
           if(res.err_msg == "get_brand_wcpay_request:fail"){
        	   alert("支付初始化失败");
           }
       }
   ); 
}
if (typeof WeixinJSBridge == "undefined"){
   if( document.addEventListener ){
       //document.addEventListener('WeixinJSBridgeReady', changestate, false);
   }else if (document.attachEvent){
       //document.attachEvent('WeixinJSBridgeReady', changestate); 
       //document.attachEvent('onWeixinJSBridgeReady', changestate);
   }
}else{
	  //onBridgeReady();
}
function getPayResult(){
	$("#userstate2").unbind("click");
	$("#userstate2").attr("onclick","");
	$("#userstate2").html("支付进行中...");
	$.ajax({
		url:"getPayResult",
		method:'post',
		success:function(d){
			if(d.code==0){
				alert("恭喜您，成为正式会员");
				location.href="weixinlogin";
			}else if(d.code==1){
				setTimeout("getPayResult()", 1*1000);
			}else if(d.code==2){
				alert("支付失败");
				location.href="weixinlogin";
			}
		},
		error:function(){
			//history.go(0);
			alert("支付异常");
		}
	});
}


function changestate1(){
	var id='${id}';
	var data={"id":id};
	$.ajax({
		url:"update",
		method:'post',
		data:data,
		success:function(d){
			if(d.code==0){
				alert("恭喜您，成为正式会员");
				window.location.href="weixinlogin";
			}else{
				alert("对不起，提交失败");
			}
		},
		error:function(){
			alert("对不起，提交失败");
		}
	});
}
$(function(){
	var userstate='${userstate}';
	if(userstate=="正式会员"){
	  $("#userstate2").hide();
	}
	
	$(".centerupdate").css({"cursor":"pointer"});
	$(".centerupdate").bind("click",function(){
		var m = $(this).prev("input");
		var data = {
			"m":m.attr("name"),
			"telephone":m.val(),
			"address":m.val()
		}
		$.ajax({
            url: "front/userupdate",
            method: 'post',
            data:data,
            success: function(d){
            	if(d.code==0){
            		alert("修改成功");
            	}else{
            		alert("修改失败");
            	}
            },
            error: function(){
            		alert("修改失败");
            }
        });
	});
	$(".li-click").bind("click",function(){
		$(this).next("li").toggle();
		if($(this).next("li").is(":hidden")){
			$(this).find(".s-right").addClass("glyphicon-chevron-right");
			$(this).find(".s-right").removeClass("glyphicon-chevron-down");
		}else{
			$(this).find(".s-right").removeClass("glyphicon-chevron-right");
			$(this).find(".s-right").addClass("glyphicon-chevron-down");
		}
	})
});
function changesub(){
	var form = $('form[name="studentchangepass"]');
	if(form.find("input[name='newpass']").val()!=form.find("input[name='newpass1']").val()){
		alert("两次密码输入不一致");
		return false;
	}
    $.ajax({
        url: "front/userupdate",
        method: 'post',
        data: $(form).serialize(),
        success: function(d){
        	if(d.code == -1){
        		 window.location.href='login';
        	} else if(d.code == 0){					
        		alert("修改成功");
        		$('#studentchangepassModal').modal('hide');
        	}  else {
        		alert(d.msg);
        	}
        },
        error: function(){
        	
        }
    });
    return false;
}
function binduser(){
	$('#login_wechat').val("${student.account}");
	$('#studentloginModal').modal('toggle');
}
function unbinduser(){
	location.href="front/unbindstudent";
}
</script>
</body>
</html>
</compress:html>