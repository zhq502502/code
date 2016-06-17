<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html enabled="true" compressJavaScript="true" compressCss="true">
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="head.jsp" %>
</head>
<body style="background-color: #ddd;height: 100%" >
<div class="main" style="background-color: #ddd;">
	<div class="col-xs-12 vadiolist" >
		<div class="panel panel-default" style="margin-top: 10px">
		  <div class="panel-heading">
		    <h3 class="">企业注册</h3>
		  </div>
		  <div class="panel-body">
		    <form id="uploadForm" class="form-horizontal" name="formadd" method="post" enctype="multipart/form-data">
		    
		     <div class="form-group ">
			    <label for="exampleInputEmail1" class="col-xs-4 ">企业简称</label>
			    <div class="col-xs-8">
			    	<input type="text" name="companyeasyname" class="form-control " id="companyeasyname" placeholder="企业简称" value="${skt.SKF55}">
			    </div>
		    </div>
		     <div class="form-group ">
			    <label for="exampleInputEmail1" class="col-xs-4 ">企业法人</label>
			    <div class="col-xs-8">
			    	<input type="text" name="companylegalperson" class="form-control " id="companylegalperson" placeholder="企业法人" value="${skt.SKF77}">
			    </div>
		    </div>
		   
			  <div class="form-group ">
			    <label for="exampleInputEmail1" class="col-xs-4 ">企业名称</label>
			    <div class="col-xs-8">
			    	<input type="text" name="companyname" class="form-control " id="companyname" placeholder="企业名称" value="${skt.SKF20}">
			    </div>
			  </div>
			  <div class="form-group ">
			    <label for="exampleInputEmail1" class="col-xs-4 ">企业联系人</label>
			    <div class="col-xs-8">
			    	<input type="text" name="companypeople" class="form-control " id="companypeople" placeholder="联系人" value="${skt.SKF21}">
			    </div>
			  </div>
			  <div class="form-group ">
			    <label for="exampleInputEmail1" class="col-xs-4 ">联系电话</label>
			    <div class="col-xs-8">
			    	<input type="text" name="companyphone" class="form-control " id="companyphone" placeholder="联系电话" value="${skt.SKF22}">
			    </div>
			  </div>
			  <div class="form-group ">
			    <label for="exampleInputEmail1" class="col-xs-4 ">企业地址</label>
			    <div class="col-xs-8">
			    	<input type="text" name="companyaddress" class="form-control " id="companyaddress" placeholder="企业地址" value="${skt.SKF23}">
			    </div>
			  </div>
			 <div class="form-group ">
			    <label for="exampleInputEmail1" class="col-xs-4 ">企业信息</label>
			    <div class="col-xs-8">
			    <textarea name='companyinformation'  id="companyinformation" class="form-control " style="width:190px;height:100px;vertical-align: top" data-rule="length[~500]">${skt.SKF24}</textarea>
			    </div>
			  </div>
			  <div class="text-center">
				<button type="button" onclick="addcompany()" class="btn btn-warning" style="padding-left: 30px;padding-right: 30px;">提交</button>
			  </div>
			</form>
		  </div>
		</div>
	</div>
</div>
<script type="text/javascript">

function check(){
	var value=document.getElementById("companyeasyname").value;
	var value1=document.getElementById("companylegalperson").value;
	var value2=document.getElementById("companyname").value;
	var value3=document.getElementById("companypeople").value;
	var value4=document.getElementById("companyphone").value;
	var value5=document.getElementById("companyaddress").value;
	
	
	
	if(value==null||value==""){
		alert("公司简称不能为空");
	   return false;}
	
	if(value1==null||value1==""){
		alert("公司法人不能为空");
	   return false;}
	
	if(value2==null||value2==""){
		alert("公司名称不能为空");
	   return false;}
	
	if(value3==null||value3==""){
		alert("公司联系人不能为空");
	   return false;}
	
	
	  var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
	  var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
	 // var value=$('#companyphone');
	  if(value4==null||value4==""){
		alert("公司电话不能为空");
	   return false;
	   }
    if(isPhone.test(value4)||isMob.test(value4)){
       
	}
	else{
		alert("电话或手机格式不对");
		return false;
	} 
	if(value5==null||value5==""){
		alert("公司地址不能为空");
	   return false;}
	else{
		return true;
	}
}

function addcompany(){
	if(!check()){
		return false;
	}
	var form=$('form[name="formadd"]');
   $.ajax({
	   url: "addCompany",
       method: 'post',
       data: $(form).serialize(),
		 success:function(d){
			 if(d.code==1){
				 alert("提交失败，请重新操作");
				// window.location.href='weixinlogin';
			 }else if(d.code==0){
				 alert("提交成功，等待管理员审批");
			 }else if(d.code==2){
				 alert("对不起，该企业名称已被注册");
			 }
			 else {
				 alert("提交失败，请重新操作");
			 }
	 
		 },
          error:function(){
          }
   
   });
	
}
</script>
</body>
</html>
</compress:html>