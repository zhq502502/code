<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预付款通知函</title>
<style type='text/css'>
<!--
html,body { height:100%; width:100%; background-color: #EAEAEA; font-family: "Microsoft YaHei",Helvetica,Arial,sans-serif; font-size:12px; color:#565656;}
.hr-red { height:1px; border:none; border-top:1px solid #B0004F; }
.hr-gray-dashed {height:1px; border:none;  border-top:1px dashed #aaaaaa; margin-top:10px;margin-bottom:0;}
.hr-gray { height:1px; border:none; border-top:1px solid #aaaaaa; }
#wrap {margin:0 auto; width:500px;background-color: #FFFFFF; border:1px solid #C01E54;  font-family: "Microsoft YaHei",Helvetica,Arial,sans-serif ; font-size:12px; color:#565656;}
span { display:inline-block;}
#header-line { height:10px; background-color:#BF0064;}
#footer-line { height:3px; background-color:#777777;}
#content { margin: 0 25px;  min-height:400px;}
.container { width:100%; display:block;}
.content-maintitle { font-size: 25px; color: #C01E54;} 
.content-subtitle {margin-left:15px;}
.mail-title { font-size:18px; }
.mail-body { font-size: 15px;}
.label-1 {padding-right:20px; font-size:18px;}
.text-1 { font-size:18px; color:#C01E54; font-family: Arial,Helvetica,sans-serif;}
.label-2 { font-size:18px; font-weight:bold; color:#396584; padding-right:10px;}
.text-2 { font-size:25px; color:#C01E54; font-weight:bold; font-style:italic; font-family: "Times New Roman",Helvetica,sans-serif;}
.label-3 { font-size:20px; font-weight:bold;}
.label-4,.text-3,.text-4 { font-size: 13px;width:100px;}
.label-5 { font-size: 15px;width:120px;}
.label-6{ font-size: 15px; padding:0 4px;}
.label-7{ font-size: 15px;width:160px;}
.label-8{ font-size: 12px; font-family: "simsun",Helvetica,sans-serif; color: #BF0051;}
-->
</style>
</head>
<body>

<div id='wrap'>
	<div id='header-line'></div>
	<div id='content'>
		<div class='container' style='margin-top:10px;'>
			<span class='content-maintitle'>预付款通知函</span><span class='content-subtitle'>付款将在1-3天内到达,取决于银行支付交换系统</span>
		</div>
		<div class='container'>
			<hr class='hr-red' />
		</div>
		<div class='container' style='margin-bottom:30px;'>
			<span class='mail-title'>尊敬的<%=request.getParameter("supplier") %></span>
		</div>
		<div class='container'>
			<span class='mail-body'>一封正式的邮件</span>
		</div>
		<div class='container' style='margin:30px 0;'>
			<hr class='hr-gray-dashed' />
		</div>
		<div class='container'>
			<span class='label-1'>项目:</span><span class='text-1'><%=request.getParameter("pjname") %></span>
		</div>
		<div class='container' style='margin-top:10px; margin-bottom:30px;'>
			<span class='label-1'>客户:</span><span class='text-1'><%=request.getParameter("customer") %></span>
		</div>	
		<div class='container' style='margin-top:10px;'>
			<span class='label-2'>预付款比例:</span><span class='text-2'><%=request.getParameter("percent") %></span>
		</div>
		<div class='container'>
			<span class='label-2'>预付款金额:</span><span class='text-2'><%=request.getParameter("price") %></span>
		</div>
		<div class='container' style='margin:15px 0;'>
			<span class='label-5'>付至供应商账户:</span><span class='label-6'><%=request.getParameter("bank") %></span><span class='label-7'><%=request.getParameter("banknumber") %></span>
		</div>
 		<div class='container'>
			<span class='label-8'>* 由于多笔费用一次性支付，此笔付款金额包含在一笔<%=request.getParameter("total") %>付款中，敬请查询</span>
		</div>
		<div class='container' style='margin:30px 0;'>
			<hr class='hr-gray-dashed' />
		</div>
		<div class='container'>
			<span class='label-3'>如有问题,请联系:</span>
		</div>
		<div class='container' style='margin-top:20px; margin-bottom:5px;'>
			<span class='label-4'>费用申请人:</span><span class='text-3'><%=request.getParameter("user1") %></span><span class='text-4'><%=request.getParameter("phone1") %></span>
		</div>
		<div class='container'>
			<span class='label-4'>财务联系人:</span><span class='text-3'><%=request.getParameter("user2") %></span><span class='text-4'><%=request.getParameter("phone2") %></span>
		</div>
		<div class='container' style='margin:20px 0 10px 0;'>
			<hr class='hr-gray' />
		</div>
		<div class='container'>
			<div style='margin:0 auto; width:281px;'><span><img src='cid:wink' /></span></div>
		</div>
	</div>
	<div id='footer-line'></div>
</div>

</body>
</html>