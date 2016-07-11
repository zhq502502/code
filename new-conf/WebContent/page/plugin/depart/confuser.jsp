<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.p-userwindow{
	display: none;
	position: absolute;
	width: 800px;
	height: 600px;
	margin: 0 auto;
	background-color: #ccc;
}
</style>
<div id="p-conf-bg" style="position: absolute;top: 0;left: 0;background-color:#333;width:100%;height:100%;display: none"></div>
<div id="p-conf-admin" class="p-userwindow">

</div>
<div id="p-conf-user" class="p-userwindow">

</div>
<script>
$("#p-conf-bg").css({'opacity':'0.4'});
$(function(){
	$(".ui-state-default").removeClass("ui-state-disabled");
})
function p_confcommon(){
	$("#p-conf-bg").hide();
	$("#p-conf-user").show();
}
function p_confadmin(){
	$("#p-conf-bg").hide();
	$("#p-conf-admin").show();
}
</script>