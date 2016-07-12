
$(function(){
	$("#background").css({'opacity':'0.7'});
	openlogin();
	initdebug();
	//检查会议客户端是否安装
	if (g_installFlag == '0' || g_installFlag == '1')
	{
		if(browser.versions.iPad){
			g_installFlag = "4";
		}
		else if(browser.versions.iPhone){
			g_installFlag = "5";
		}
		else if(browser.versions.android){
			g_installFlag = "3";
		}
		else{
			Seegle.postsgsvr(9090, 1, Seegle.client_registry, "InstallPath");	
		}	
	}
})
function initdebug(){
	var cookie_debug = $.cookie("cookie_debug");
	if(cookie_debug+""=="1"){
		$("#debug").attr("checked",true);
	}else{
		$("#debug").attr("checked",false);
	}
	debug = $("#debug").attr("checked")?1:0;
	$("#isdebug span").bind("click",function(){
		if($("#debug").attr("checked")){
			$("#debug").attr("checked",false);
		}else{
			$("#debug").attr("checked",true);
		}
		debug = $("#debug").attr("checked")?1:0;
		$.cookie("cookie_debug",debug,{expires: 1000});
	})
	$("#debug").bind("click",function(){
		debug = $("#debug").attr("checked")?1:0;
		$.cookie("cookie_debug",debug,{expires: 1000});
	})
}
//隐藏版权信息
function hidecopyright(){
	$("#footer").hide();
}
//显示加载信息
function openloding(msg){
	$("#background").show();
	$("#loding").html(msg);
	$("#loding").show();
}
//隐藏加载信息
function closeloding(){
	$("#background").hide();
	$("#loding").hide();
	$("#loding").html("");
}
/**
 * 初始化右边显示
 * @param data
 * @return
 */
function initdata(data){
	$("#right").html(data);
}
//重新登录
function relogin(){
	openlogin();
	initdata("");
}
/**
 * 打开登录界面
 * @return
 */
function openlogin(){
	var cookie_apiurl = $.cookie("cookie_apiurl");
	if(cookie_apiurl==null){
		cookie_apiurl = "http://www.seegle.cn";
	}
	var cookie_orgid = $.cookie("cookie_orgid");
	var cookie_username = $.cookie("cookie_username");
	var cookie_userpass = $.cookie("cookie_userpass");
	var cookie_nname = $.cookie("cookie_nname");
	$("#apiurl").val(cookie_apiurl);
	$("#orgid").val(cookie_orgid);
	$("#username").val(cookie_username);
	$("#userpass").val(cookie_userpass);
	$("#nname").val(cookie_nname);
	$("#background").show();
	$("#login").show();
}
/**
 * 关闭登录界面
 * @return
 */
function closelogin(){
	$("#background").hide();
	$("#login").hide();
}
/**
 * 显示debug信息
 * @param data
 * @return
 */
function seegledebug(data){
	if(debug==1){
		alert(JSON.stringify(data));
	}
}
/**
 * 点击登录
 * @return
 */
function click_login(){
	var url = "";
	apiurl = $("#apiurl").val();
	orgid = $("#orgid").val();
	username = $("#username").val();
	nname = $("#nname").val();
	$.cookie("cookie_nname",nname,{expires: 1000});
	userpass = $("#userpass").val();
	md5userpass = hex_md5(userpass);
	$.cookie("cookie_apiurl",apiurl,{expires: 1000});
	$.cookie("cookie_orgid",orgid,{expires: 1000});
	$.cookie("cookie_username",username,{expires: 1000});
	$.cookie("cookie_userpass",userpass,{expires: 1000});
	url = apiurl+"/apis/token?u="+username+"&p="+md5userpass+"&orgid="+orgid+"&callback=back_token";
	seegledebug(url);
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	closelogin();
	openloding("登录中...");
}
/**
 * 登录回调方法
 * @param data
 * @return
 */
function back_token(data){
	closeloding();
	seegledebug(data); 
	if(data.token.length>8){
		token = data.token;
	}else{
		openlogin();
		alert(errorMap.get(data.token+""));
	}
}
/**
 * 获取会议列表
 * @return
 */
function conflist(){
	url = apiurl+"/apis/conf/list?accessKey="+token+"&orgid="+orgid+"&callback=back_conflist";
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("正在获取会议列表...");
}
/**
 * 获取个人会议列表
 * @return
 */
function userconflist(){
	url = apiurl+"/apis/conf/list?accessKey="+token+"&orgid="+orgid+"&callback=back_conflist&conftype=3";
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("正在获取会议列表...");
}
/**
 * 获取会议列表回调方法
 * @return
 */
function back_conflist(data){
	closeloding();
	seegledebug(data);
	var str = "<table border='1'>";
	str += "<tr><td>会议id</td><td>会议名称</td><td>开始时间</td><td>结束时间</td><td>操作</td></tr>";
	for(var i = 0;i<data.length;i++){
		str += "<tr id='"+data[i].cid+"'><td>"+data[i].cid+"</td><td>"+data[i].confname+"</td><td>"+data[i].btime+"</td><td>"+data[i].etime+"</td><td><a href=\"javascript:void(0)\" onclick=\"confinfo('"+data[i].cid+"')\">参加会议</a>|<a href=\"javascript:void(0)\" onclick=\"confedit('"+data[i].cid+"')\">编辑</a>|<a href=\"javascript:void(0)\" onclick=\"confdel('"+data[i].cid+"')\">删除</a>|<a href=\"javascript:void(0)\" onclick=\"confusercommon('"+data[i].cid+"')\">默认与会者</a>|<a href=\"javascript:void(0)\" onclick=\"confuseradmin('"+data[i].cid+"')\">会议管理员</a>|<a href=\"javascript:void(0)\" onclick=\"confvcode('"+data[i].cid+"')\">会议室验证码</a></td></tr>";
	}
	str += "</table>";
	initdata(str);
}
/**
 * 点击新建会议
 * @return
 */
function confadd(){
	var str = $("#confadd").html();
	initdata(str);
	$("#right #cid").val("");
}
/**
 * 点击新建会议
 * @return
 */
function click_confadd(){
	var url = apiurl+"/apis/conf/add?accessKey="+token+"&orgid="+orgid+"&callback=back_confadd&";
	openloding("添加会议...");
	if($("#right #cid").val()+""!=""){
		var url = apiurl+"/apis/conf/edit?accessKey="+token+"&orgid="+orgid+"&callback=back_confadd&type=set&";
		openloding("修改会议...");
	}
	$("#right input").each(function(){
		url+=$(this).attr("name")+"="+$(this).val()+"&";
	})
	seegledebug(url);
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	
}
/**
 * 添加会议室回调方法
 * @param data
 * @return
 */
function back_confadd(data){
	closeloding();
	seegledebug(data);
	alert(errorMap.get(data.msg+""));
	conflist();
}
/**
 * 上传会议室
 * @param cid
 * @return
 */
function confdel(cid){
	var url = apiurl+"/apis/conf/del?accessKey="+token+"&orgid="+orgid+"&callback=back_confdel&cid="+cid;
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("删除会议...");
}
/**
 * 删除会议回调方法
 * @param data
 * @return
 */
function back_confdel(data){
	closeloding();
	seegledebug(data);
	alert(errorMap.get(data.msg+""));
	if(data.msg+""=="0"){
		conflist();
	}
}
/**
 * 获取会议集群信息
 * @return
 */
function confgroup(){
	var url = apiurl+"/apis/conf/group?accessKey="+token+"&callback=back_confgroup";
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("获取会议集群...");
}
/**
 * 获取集群列表回调方法
 * @param data
 * @return
 */
function back_confgroup(data){
	closeloding();
	seegledebug(data);
	var str = "<table border='1'>";
	str += "<tr><td>集群ID</td><td>集群名称</td></tr>";
	for(var i = 0;i<data.length;i++){
		str += "<tr id='"+data[i].cid+"'><td>"+data[i].id+"</td><td>"+data[i].name+"</td></tr>";
	}
	str += "</table>";
	initdata(str);
}
/**
 * 默认与会人员
 * @param cid
 * @return
 */
function confusercommon(cid){
	confid = cid;
	var url = apiurl+"/apis/conf/confcommonlist?accessKey="+token+"&callback=back_confusercommon&cid="+cid;
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("默认与会者...");
}
/**
 * 默认与会人员回调
 * @param data
 * @return
 */
function back_confusercommon(data){
	closeloding();
	seegledebug(data);
	var str = "<table border='1'>";
	str += "<tr><td>用户帐号</td><td>操作</td></tr>";
	for(var i = 0;i<data.length;i++){
		str += "<tr id='"+data[i]+"'><td>"+data[i]+"</td><td><a href=\"javascript:void(0)\" onclick=\"confusercommondel('"+data[i]+"')\">删除</a></td></tr>";
	}
	str += "<tr ><td><input id='confusercommons'/></td><td><a href=\"javascript:void(0)\" onclick=\"confusercommonadd()\">添加会议与会者</a></td></tr>";
	str += "</table>";
	initdata(str);
}
/**
 * 删除会议与会者
 * @param cid
 * @return
 */
function confusercommondel(commonList){
	var url = apiurl+"/apis/conf/confuserdelbyname?accessKey="+token+"&callback=back_confusercommonadd&commonList="+commonList+"&orgid="+orgid+"&adminList&cid="+confid;
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("删除会议与会者...");
}
/**
 * 添加会议与会者
 * @return
 */
function confusercommonadd(){
	var commonList = $("#confusercommons").val();
	if(commonList==null||commonList==""){
		alert("输入与会者为空");
		return ;
	}
	var url = apiurl+"/apis/conf/confuseraddbyname?accessKey="+token+"&callback=back_confusercommonadd&cid="+confid+"&orgid="+orgid+"&commonList="+commonList;
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("添加会议默认与会者...");
}
/**
 * 添加会议与会者回调
 * @param data
 * @return
 */
function back_confusercommonadd(data){
	closeloding();
	seegledebug(data);
	alert(errorMap.get(data.msg+""));
	if(data.msg+""=="0"){
		confusercommon(confid);
	}
}
/**
 * 会议管理员
 * @param cid
 * @return
 */
function confuseradmin(cid){
	confid = cid;
	var url = apiurl+"/apis/conf/confadminlist?accessKey="+token+"&callback=back_confuseradmin&cid="+cid;
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("会议管理员...");
}
/**
 * 会议管理员员回调
 * @param data
 * @return
 */
function back_confuseradmin(data){
	closeloding();
	seegledebug(data);
	var str = "<table border='1'>";
	str += "<tr><td>用户帐号</td><td>操作</td></tr>";
	for(var i = 0;i<data.length;i++){
		str += "<tr id='"+data[i]+"'><td>"+data[i]+"</td><td><a href=\"javascript:void(0)\" onclick=\"confuseradmindel('"+data[i]+"')\">删除</a></td></tr>";
	}
	str += "<tr ><td><input id='confuseradmins'/></td><td><a href=\"javascript:void(0)\" onclick=\"confuseradminadd()\">添加会议管理员</a></td></tr>";
	str += "</table>";
	initdata(str);
}
/**
 * 删除会议管理员
 * @param cid
 * @return
 */
function confuseradmindel(adminList){
	var url = apiurl+"/apis/conf/confuserdelbyname?accessKey="+token+"&callback=back_confuseradminadd&commonList&orgid="+orgid+"&adminList="+adminList+"&cid="+confid;
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("删除会议管理员...");
}
/**
 * 添加会议管理员
 * @return
 */
function confuseradminadd(){
	var adminList = $("#confuseradmins").val();
	if(adminList==null||adminList==""){
		alert("输入管理员为空");
		return ;
	}
	var url = apiurl+"/apis/conf/confuseraddbyname?accessKey="+token+"&callback=back_confuseradminadd&cid="+confid+"&orgid="+orgid+"&adminList="+adminList;
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("添加会议管理员...");
}
/**
 * 添加会议管理员回调
 * @param data
 * @return
 */
function back_confuseradminadd(data){
	closeloding();
	seegledebug(data);
	alert(errorMap.get(data.msg+""));
	if(data.msg+""=="0"){
		 confuseradmin(confid);
	}
}

/**
 * 会议验证码
 * @param cid
 * @return
 */
function confvcode(cid){
	confid = cid;
	var url = apiurl+"/apis/phoneverifycode/list?accessKey="+token+"&callback=back_vcode&codetype=1&confid="+cid;
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("会议管理员...");
}
/**
 * 会议验证码回调
 * @param data
 * @return
 */
function back_vcode(data){
	closeloding();
	seegledebug(data);
	var str = "<table border='1'>";
	str += "<tr><td>会议验证码</td><td>操作</td></tr>";
	for(var i = 0;i<data.length;i++){
		str += "<tr id='"+data[i].id+"'><td>"+data[i].code+"</td><td><a href=\"javascript:void(0)\" onclick=\"vcodedel('"+data[i].id+"')\">删除</a></td></tr>";
	}
	str += "<tr ><td></td><td><a href=\"javascript:void(0)\" onclick=\"vcodeadd()\">添加会议验证码</a></td></tr>";
	str += "</table>";
	initdata(str);
}
/**
 * 删除会议验证码
 * @param cid
 * @return
 */
function vcodedel(codes){
	var url = apiurl+"/apis/phoneverifycode/del?accessKey="+token+"&callback=back_vcodeadd&codes="+codes+"&confid="+confid;
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("删除会议验证码...");
}
/**
 * 添加会议验证码
 * @return
 */
function vcodeadd(){
	var url = apiurl+"/apis/phoneverifycode/add?accessKey="+token+"&callback=back_vcodeadd&confid="+confid+"&begintime=2014-01-01 00:00:00&endtime=2114-01-01 00:00:00&type=1";
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("添加会议验证码...");
}
/**
 * 添加会议管理员回调
 * @param data
 * @return
 */
function back_vcodeadd(data){
	closeloding();
	seegledebug(data);
	alert(errorMap.get(data.msg+""));
	if(data.msg+""=="0"){
		confvcode(confid);
	}
}
/**
 * 通过会议id获取会议参数
 * @param cid
 * @return
 */
function confinfo(cid){
	var url = apiurl+"/apis/conf/login?accessKey="+token+"&callback=back_confinfo&cid="+cid;
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("通过会议id获取会议登录参数...");
}
/**
 * 通过会议id获取会议参数回调方法
 * @param data
 * @return
 */
function back_confinfo(data){
	closeloding();
	seegledebug(data);
	if(data.msg+""=="0"){
		data.orgid = orgid;
		data.userpass = userpass;
		joinconf(data);
	}else{
		alert(errorMap.get(data.msg+""));
	}
}
/**
 * 参加会议
 * @param data
 * @return
 */
function joinconf(data){
	if(g_installFlag=='0'||g_installFlag=='1')
	{		
		showinstall();
	}
	else            
	{
		var base64unick = base64encode(utf16to8(nname)); //昵称base64编码
		var runandroid = "mtcmtc://startConference.app?ConfID="+data.rid+"&ServerAddrAndPort="+data.ips+"&UserAccount="+data.orgid+data.userid+"&Nick="+nname+"&LoginMode=LoginByUserID&Password="+data.userpass;
		var runipad = "SCConferenceiPad://?user="+data.orgid+data.userid+"&pass="+data.userpass+"&conference="+data.rid+"&server="+data.ips+"&nick="+base64unick+"&loginMode=LoginByUserID";
		var runiphone = "SCConferenceiPhone://?user="+data.orgid+data.userid+"&pass="+data.userpass+"&conference="+data.rid+"&server="+data.ips+"&nick="+base64unick+"&loginMode=LoginByUserID";
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
			Seegle.startconf(data.rid, data.ips, data.orgid, data.userid, data.userpass, nname,"",'2',Seegle.client_registry,"InstallPath",Seegle.client_confname,proxytype,proxyip,proxyport,proxyuser,proxypass);
		}
	}
}
/**
 * 显示会议客户端安装
 * @return
 */
function showinstall(){
	$("#install,#background").toggle();
	$("#background").css({'opacity':'0.4'});
}
/**
 * 下载客户端
 * @param flag
 * @return
 */
function download(flag){
	if(flag==1){
		window.open ('installonline/webinstall.html','newwindow','height=300, width=515, top=50, left=50, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
		window.history.go(0);
	}else{
		window.open (download_URL,'newwindow','height=300, width=515, top=50, left=50, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
		
	}
}
/**
 * 打开验证入会
 */
function click_openvcode(){
	var cookie_apiurl = $.cookie("cookie_apiurl");
	if(cookie_apiurl==null){
		cookie_apiurl = "http://www.seegle.cn";
	}
	var cookie_nname = $.cookie("cookie_nname");
	var cookie_vcode = $.cookie("cookie_vcode");
	$("#apiurl1").val(cookie_apiurl);
	$("#nname1").val(cookie_nname);
	$("#vcode").val(cookie_vcode);
	$("#background").show();
	$("#login").hide();
	$("#vcodelogin").show();
}
/**
 * 关闭验证码登录，返回普通登录
 * @return
 */
function click_closevcode(){
	$("#background").show();
	$("#login").show();
	$("#vcodelogin").hide();
}
/**
 * 点击验证码入会，通过验证码获取会议登录信息
 * @return
 */
function click_vcodelogin(){
	vcode  = $("#vcode").val();
	apiurl = $("#apiurl1").val();
	nname = $("#nname1").val();
	$.cookie("cookie_apiurl",apiurl,{expires: 1000});
	$.cookie("cookie_nname",nname,{expires: 1000});
	$.cookie("cookie_vcode",vcode,{expires: 1000});
	var url = apiurl+"/apis/conf/login?verifycode="+vcode+"&callback=back_vcodeconfinfo&cid=0";
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("通过验证码获取会议登录参数...");
}
/**
 * 通过验证码获取会议登录信息回调方法
 * @param data
 * @return
 */
function back_vcodeconfinfo(data){
	$("#loding").hide();
	$("#loding").html("");
	seegledebug(data);
	if(data.msg+""=="0"){
		joinconf(data);
	}else{
		alert(errorMap.get(data.msg+""));
	}
}
/**
 * 编辑会议室
 * @return
 */
function confedit(cid){
	var url = apiurl+"/apis/conf/edit?accessKey="+token+"&callback=back_confedit&cid="+cid+"&orgid="+orgid+"&type=get";
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
	openloding("通过会议id获取单个会议室信息...");
	var str = $("#confadd").html();
	initdata(str);
	$("#right #cid").val(cid);
}
function back_confedit(data){
	closeloding();
	seegledebug(data);
	$("#right #confname").val(data.confname);
	$("#right #begintime").val(data.confbegintime);
	$("#right #endtime").val(data.confendtime);
	$("#right #confgrouptype").val(data.grouptype);
	$("#right #maxconfuser").val(data.max_conf_user);
	$("#right #maxconfspokesman").val(data.max_conf_spokesman);
	$("#right #maxconftourist").val(data.max_conf_tourist);
	
}
