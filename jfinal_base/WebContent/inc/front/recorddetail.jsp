<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="f" uri="/cktag" %>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="head.jsp" %>
<style type="text/css">
.main{
	background-image:none;
	background-color: #fff;
	min-height: 100%;
}
</style>
</head>
<body>
<div class="main">
	<div class="col-xs-12 text-center top" >
		消费详情
		<div style="" class="back"><span class="glyphicon glyphicon-arrow-left"></span></div>
	</div>
	<div class="col-xs-12" style="margin-top: 20px">
		<div class="panel panel-default goodsinfo ">
			<div class="col-xs-12 goodsdis">
				ID：${skt.skf30 }
			</div>
			<div class="col-xs-12 goodsdis">
				会员编号：${skt.skf31}
			</div>
			<div class="col-xs-12 goodsdis" >
				企业名称：${skt.skf20 }
			</div>
			<div class="col-xs-12 goodsdis" >
				消费金额：${skt.skf33 }
			</div>
			<div class="col-xs-12 goodsdis" >
				购买数量：${skt.skf130 }
			</div>
			<div class="col-xs-12 goodsdis" >
				消费时间：${skt.skf34 }
			</div>
			<div class="col-xs-12 goodsdis" style="display: none">
				付款方式：${skt.skf35 }
			</div>
			<div class="col-xs-12 goodsdis" >
				是否优惠：${skt.skf37}
			</div>
			<div class="col-xs-12 goodsdis" style="display: none">
				红包额度：${skt.skf38 }
			</div>
			<div class="col-xs-12 goodsdis" >
				红包返值：${skt.skf136 }
			</div>
			<div class="col-xs-12 goodsdis" >
				备注：${skt.skf36 }
			</div>
		</div>
	</div>
</div>
</body>
</html>