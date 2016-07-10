<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.net.URI"%>
<%@page import="com.seegle.util.PropUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String confid = request.getParameter("cid");
String client_registry = PropUtil.getInstance().getValue("client.registry");
String client_confname = PropUtil.getInstance().getValue("client.confname");
String download = PropUtil.getInstance().getValue("constants.jsp.online_install_seegleconf_url");;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="width: 100%">
<head>
  <base href="<%=basePath%>">
  <title>进入会议室</title>
</head>
<script type="text/javascript">
var client_registry = "<%=client_registry%>";
var client_confname = "<%=client_confname%>";
</script>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/conf.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/base64.js"></script>
<script type="text/javascript">
var apiurl = "http://192.168.1.125:8080/opm4j";
var orgid = "893114"; 
var confid = "<%=confid%>";
var userid = "";
var userpass = "";
var userpassmd5 = "d41d8cd98f00b204e9800998ecf8427e";
var nname = "游客"+parseInt(Math.random()*10000);
var proxytype = "0";
var proxyip = "";
var proxyport = "";
var proxyuser = "";
var proxypass = "";
$(function(){
	$.cookie('download_meetUnOnline','<%=download%>');
	checkinstall();
})
function checkinstall(){
	var obj = new Image();
	var url = 'http://127.0.0.1:9090/?regpath=<%=client_registry%>&regkey=InstallPath&type=1?/';
    obj.src = url;
    obj.onload = function()
    {
    	obj.onreadystatechange = null;
    	obj.onerror = null;
    	g_installFlag = '9090';              // 客户端已经安装
    	obj = null;
    	token();
    }
    
    obj.onerror = function()
    {
    	obj.onload = null;
    	obj.onreadystatechange = null;
    	g_installFlag = '1';               // 客户端未安装
    	obj = null;
    	$("#install").show();
    }
}
function token(){
	var url = apiurl+"/apis/token?type=2&u="+userid+"&p="+userpassmd5+"&orgid="+orgid+"&callback=getconfid";
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
}
function getconfid(data){
	var token = data.token;
	if(confid==null||confid==""||confid=="null"){
		alert("会议ID不能为空");
		return  ;
	}
	if(token+""=="37"){
		alert("不支持游客");
		return ;
	}
	$.ajax({
		type : "get",
		async:false,
		url : apiurl+"/apis/conf/login?cid="+confid+"&accessKey="+token+"&callback=joinconf",
		dataType : "jsonp"
	});
}
function joinconf(data){
	if(data.rid !="" && data.rid != null)
	{
		
		if(g_installFlag=='0'||g_installFlag=='1')
		{		
			$("#install").show();
		}
		else            
		{
			var base64unick = base64encode(utf16to8(nname)); //昵称base64编码
			var runandroid = "mtcmtc://startConference.app?ConfID="+data.rid+"&ServerAddrAndPort="+data.ips+"&UserAccount="+orgid+data.userid+"&Nick="+nname+"&LoginMode=LoginByUserID&Password="+userpass;
			var runipad = "SCConferenceiPad://?user="+orgid+data.userid+"&pass="+userpass+"&conference="+data.rid+"&server="+data.ips+"&nick="+base64unick+"&loginMode=LoginByUserID";
			var runiphone = "SCConferenceiPhone://?user="+orgid+data.userid+"&pass="+userpass+"&conference="+data.rid+"&server="+data.ips+"&nick="+base64unick+"&loginMode=LoginByUserID";
			if(browser.versions.iPad){
				location.href=runipad;
			}
			else if(browser.versions.iPhone){
				location.href=runiphone;
			}
			else if(browser.versions.android){
				location.href=runandroid;
			}
			else{
				startconf(data.rid, data.ips, orgid, data.userid, userpass, nname,"",'2',Seegle.client_registry,"InstallPath",Seegle.client_confname,proxytype,proxyip,proxyport,proxyuser,proxypass);
				window.close();  
			}
		}
	}else{
		alert("获取会议登录参数出错,错误代码"+data.msg);
	}
}
function startconf(i, j, k, q, w, n, updateserverset, logintype, regpath, regkey, exename,ptype,paddr,pport,puser,ppass)
{
    var installFlag = 9090;                                                   
	var confsrvip = j;
	var confsrvloadbalance = '1';
	var orgid = k;
	var uid = k+q;
	var upass = w.replace("#","%23");//URL地址#后面的会被截去，手动将其转译
	var unickname = encodeURIComponent(n); 
	//var unickname = n; 
	var proxytype = ptype;
	var proxyaddr = paddr;
	var proxyport = pport;
	var proxyuser = puser;
	var proxypass = ppass;
	var confid = i;
	var updateserverinfo='';
		
	var confpara = "";		
	var url = "http://127.0.0.1:";
	url = url + installFlag;
	url = url + "/?type=2&regpath=";
	url = url + regpath;
	url = url + "&regkey=";
	url = url + regkey;
	url = url + "&exename=";
	url = url + exename;
	url = url + "&r="+Math.random();
	url = url + "&param=";
    confpara = "-U " + updateserverinfo + " -T " + proxytype + " -S " + proxyaddr + " -R " + proxyport + " -N \"$" + proxyuser + "\" -Y \"$" + proxypass + "\" -H " + confsrvip + " -u " + uid + " -p \"$" + upass + "\" -n \"$" + unickname + "\" -c "+ confid + " -t 0 -b "+confsrvloadbalance+"?/";
    url = url + confpara;
	var objLoginExe = new Image();
	//alert(url);
	objLoginExe.src = url;
	objLoginExe.onreadystatechange = function()
	{
		if (this.readyState == 'complete')
		{
			objLoginExe.onload  = null;
			objLoginExe.onerror = null;
			objLoginExe = null;
		}
	};
	objLoginExe.onload = function()
	{
		objLoginExe.onreadystatechange = null;
		objLoginExe.onerror = null;
		objLoginExe = null;
		window.opener=null;
	    window.open('','_self');
	    window.close();


	};
	objLoginExe.onerror = function()
	{
		objLoginExe.onload  = null;
		objLoginExe.onreadystatechange = null;
		objLoginExe = null;
		$("#install").show();
	};
	return true;
}
function onlineinstall(param){
	window.open ('<%=basePath%>webinstall_zh_cn.html'+param,'newwindow','height=300, width=515, top=50, left=50, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
}
function closediv(){
	$("#install").hide();
}
</script>
<body style="width: 100%">
<h1></h1>
<div style="width: 100%;text-align: center">
	<div id="install" style="width: 500px;border: 1px solid #A9C9E2;margin: 5% auto;text-align: left;display: none">
		<div style="width: 100%;height: 30px;line-height: 30px;background-color: #A9C9E2">
		&nbsp;&nbsp;提示
		</div>
		<div style="width: 100%;text-align: center;">
		<br/>未检测到客户端安装。请先安装会议客户端后再重试！<br/><br/>
		<a href="<%=download%>">下载客户端</a>
		<a href="javascript:void(0);" onclick="onlineinstall('?Mode=2&type=0')">在线安装</a>
		<a href="javascript:void(0)"  onclick="closediv()">取消</a>
		<br/>
		<br/>
		</div>
	</div>
</div>
</body>
</html>
