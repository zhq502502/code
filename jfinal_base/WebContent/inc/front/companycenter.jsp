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
		<div class="userinfo-left" style="display: none">
			
		</div>
		<div class="userinfo-right" style="padding-left: 0;margin-left: 0">
			<input type="text" value="${id}" hidden="hidden">
			<h3>${skt2.SKF20 }</h3>
			<h5 >${skt2.SKF26 }</h5>
		</div>
	</div>
	<ul class="usercenter-op">
		<li  class="li-click"><span class="s-left glyphicon glyphicon-shopping-cart" style="color:#FF9966 "></span><span class="s-center">消费记录</span><span class="s-right glyphicon glyphicon-chevron-right"></span></li>
		<li class="li-hide">
		 <table class="table  panel-body">
			  <tr class="">
			  	<th>消费时间</th>
			  	<th>消费金额</th>
			  	<th>付费方式</th>
			  </tr>
			  <c:forEach items="${list}" var="skt">
			  <tr onclick="javascript:location.href='recorddetail/${skt.skf30}'">
			  	<td>${skt.skf34}</td>
			  	<td>${skt.skf33}</td>
			  	<td>${skt.skf35}</td>
			  </tr>
			  </c:forEach>
			</table>
		</li>
		<li style="display: none;" class="li-click"><span class="s-left glyphicon glyphicon-shopping-cart" style="color:#FF9966 "></span><span class="s-center" onclick="location.href='company'">企业注册</span><span class="s-right glyphicon glyphicon-chevron-right"></span></li>
	</ul>
</div>
<script type="text/javascript">

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
</script>
</body>
</html>
</compress:html>