function confdebug(msg,data){//调试方法
	if(debug==1){
		console.log(msg,data);
	}
}
$(function(){
	//检查会议客户端是否安装
	/*if (g_installFlag == '0' || g_installFlag == '1')
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
	}*/
	$("#conf_download").attr("href",download_URL);
})
/**
 * 获取会议列表
 * @return
 */
function conflist(){
	url = apiurl+"/apis/conf/list?accessKey="+token+"&orgid="+orgid+"&cname="+cname+"&callback=back_conflist";
	confdebug("获取会议列表：",url);
	$.ajax({
		type : "get",
		async:false,
		url :url,
		dataType : "jsonp"
	});
}
/**
 * 获取会议列表回调方法
 * @return
 */
function back_conflist(data){
	confdebug("获取会议列表回调：",data);
	var str = "";
	for(var i = 0;i<data.length;i++){
		str += "<tr id='"+data[i].cid+"'><td>"+data[i].cid+"</td><td>"+data[i].confname+"</td><td>"+data[i].btime+"</td><td>"+data[i].etime+"</td><td><a href=\"javascript:void(0)\" onclick=\"confinfo('"+data[i].cid+"')\">参加会议</a>|<a href=\"javascript:void(0)\" onclick=\"confedit('"+data[i].cid+"')\">编辑</a>|<a href=\"javascript:void(0)\" onclick=\"confdel('"+data[i].cid+"')\">删除</a>|<a href=\"javascript:void(0)\" onclick=\"confusercommon('"+data[i].cid+"')\">默认与会者</a>|<a href=\"javascript:void(0)\" onclick=\"confuseradmin('"+data[i].cid+"')\">会议管理员</a>|<a href=\"javascript:void(0)\" onclick=\"confvcode('"+data[i].cid+"')\">会议室验证码</a></td></tr>";
	}
	var tempdata={conflist:data};
	var template = $("#temp-conflist").html();
	var compiled_template = Handlebars.compile(template);
	var rendered = compiled_template(tempdata);
	$("#conflistbody").html(rendered);
	confgroup();//会议集群初始化
}

/**
 * 获取会议集群信息
 * @return
 */
function confgroup(){
	var param={
		accessKey:token
	}
	var url = apiurl+"/apis/conf/group?callback=back_confgroup";
	$.ajax({
		type : "get",
		async:false,
		url :url,
		data:param,
		dataType : "jsonp"
	});
	confdebug("获取集群列表：",url+param);
}
/**
 * 获取集群列表回调方法
 * @param data
 * @return
 */
function back_confgroup(data){
	confdebug("获取集群列表回调：",data);
	if(data.length>0){
		groupid=data[0].id;
	}
}

/**
 * 弹出会议添加窗口
 */
function addconf(){
	$('#confmodel').modal({
		  keyboard: false
	});
	$("#confid").val("");
	$("#confname").val("");
	$("#btime").val("2016-06-01 00:00:00");
	$("#etime").val("2019-06-01 00:00:00");
	$("#confpass").val("");
	$("#managepass").val("");
	$("#maxuser").val("20");
	$("#maxspeak").val("10");
	$("#maxtourist").val("0");
	$("#cansee").val("");
}
/**
 * 保存会议
 */
function saveconf(){
	var param ;
	var url = apiurl+"/apis/conf/add?callback=back_confadd";
	var msgd = {code:1};
	if($("#confname").val()==""){
		msgd.msg="会议名称不能为空";
		msg(msgd);
		return;
	}
	if($("#maxuser").val()==""){
		msgd.msg="最大会议人数不能为空";
		msg(msgd);
		return;
	}
	if($("#maxspeak").val()==""){
		msgd.msg="最大主席人数不能为空";
		msg(msgd);
		return;
	}
	
	
	if($("#confid").val()!=""){
		param={
				accessKey:token,
				orgid:orgid,
				type:"set",
				cid:$("#confid").val(),
				confname:$("#confname").val(),
				begintime:$("#btime").val(),
				endtime:$("#etime").val(),
				grouptype:groupid,
				max_conf_user:$("#maxuser").val(),
				max_conf_spokesman:$("#maxspeak").val(),
				max_conf_tourist:$("#maxtourist").val(),
				conf_password_md5:$("#confpass").val(),
				manage_password_md5:$("#managepass").val(),
				all_can_visible:$("#cansee").val()
		}
		url = apiurl+"/apis/conf/edit?callback=back_confadd";
	}else{
		param={
				accessKey:token,
				orgid:orgid,
				confname:$("#confname").val(),
				begintime:$("#btime").val(),
				endtime:$("#etime").val(),
				grouptype:groupid,
				max_conf_user:$("#maxuser").val(),
				max_conf_spokesman:$("#maxspeak").val(),
				max_conf_tourist:$("#maxtourist").val(),
				conf_password_md5:$("#confpass").val(),
				manage_password_md5:$("#managepass").val(),
				all_can_visible:$("#cansee").val()
		}
	}
	$.ajax({
		type : "get",
		async:false,
		url :url,
		data:param,
		dataType : "jsonp"
	});
	confdebug("添加会议：",url+param);
}
/**
 * 添加会议室回调方法
 * @param data
 * @return
 */
function back_confadd(data){
	confdebug("添加会议回调：",data);
	msg({code:data.msg,msg:errorMap.get(data.msg+"")});
	if(data.msg==0){
		$('#confmodel').modal('hide');
		conflist();
	}
}
/**
 * 删除会议
 * @param id
 */
function delconf(id){
	layer.confirm('确定删除会议室吗?', function(index){
		  layer.close(index);
		  var param = {
				  accessKey:token,
				  orgid:orgid,
				  cid:id
		  }
		  var url = apiurl+"/apis/conf/del?callback=back_confdel";
		  $.ajax({
			  type : "get",
			  async:false,
			  url :url,
			  data:param,
			  dataType : "jsonp"
		  });
		  confdebug("删除会议：",url+param);
	}); 
}
/**
 * 添加会议室回调方法
 * @param data
 * @return
 */
function back_confdel(data){
	confdebug("删除会议回调：",data);
	msg({code:data.msg,msg:errorMap.get(data.msg+"")});
	if(data.msg==0){
		conflist();
	}
}
/**
 * 加入会议
 * @param id
 */
function joinconf(id){
	var param = {
			  accessKey:token,
			  cid:id
	  }
	  var url = apiurl+"/apis/conf/login?callback=back_joinconf";
	  $.ajax({
		  type : "get",
		  async:false,
		  url :url,
		  data:param,
		  dataType : "jsonp"
	  });
	  confdebug("获取会议登录参数：",url+param);
}

/**
 * 参加会议
 * @param data
 * @return
 */
function back_joinconf(data){
	confdebug("获取会议登录参数返回：",data);
	if(data.msg!=0){
		msg({code:1,msg:errorMap.get(data.msg+"")});
		return ;
	}
	if(g_installFlag=='0'||g_installFlag=='1')
	{		
		//showinstall();
		msg({code:1,msg:"未安装客户端！"});
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
			Seegle.startconf(data.rid, data.ips, orgid, data.userid, userpass, nname,"",'2',Seegle.client_registry,"InstallPath",Seegle.client_confname,proxytype,proxyip,proxyport,proxyuser,proxypass);
		}
	}
}
/**
 * 修改会议室
 * @param id
 */
function editconf(id){
	 var param = {
			  accessKey:token,
			  orgid:orgid,
			  cid:id,
			  type:"get"
	  }
	 $("#confid").val(id);
	  var url = apiurl+"/apis/conf/edit?callback=back_confedit";
	  $.ajax({
		  type : "get",
		  async:false,
		  url :url,
		  data:param,
		  dataType : "jsonp"
	  });
	  confdebug("获取单个会议信息会议：",url+param);
}
/**
 * 修改会议室回调
 * @param data
 */
function back_confedit(data){
	
	confdebug("获取单个会议信息会议回调：",data);
	$("#confname").val(data.confname);
	$("#btime").val(data.confbegintime);
	$("#etime").val(data.confendtime);
	$("#confpass").val(data.confpasswordmd5);
	$("#managepass").val(data.managepasswordmd5);
	$("#maxuser").val(data.maxconfuser);
	$("#maxspeak").val(data.maxconfspokesman);
	$("#maxtourist").val(data.maxconftourist);
	$("#cansee").val(data.all_can_visible);
	$('#confmodel').modal({
		  keyboard: false
	});
}
/**
 * 获取会议人员列表
 * @param id
 */
function confuser(id){
	$("#confusertype").val("0");
	$("#confuserconfid").val(id);
	$("#confuserLabel").html("选择默认与会者");
	/*$('#confusermodel').modal({
		  keyboard: false
	});*/
	var param = {
		accessKey : token,
		cid : id
	}
	var url = apiurl + "/apis/conf/confcommonlist?callback=back_confuser";
	$.ajax({
		type : "get",
		async : false,
		url : url,
		data : param,
		dataType : "jsonp"
	});
	confdebug("获取默认与会者列表：", url + param);
	//tree.checkAllNodes(false);
	$("#selectconfuser").empty();
}
/**
 * 默认与会者回调
 * @param users
 */
function back_confuser(users){
	confdebug("获取默认与会者列表回调：",users);
	if(users.length==0){
		return;
	}
	/*$("#selectconfuser").empty();*/
	for(var i=0;i<users.length;i++){
		var treeNode = tree.getNodeByParam("account",users[i],null);
		console.log(treeNode);
		if(treeNode==null){
			continue;
		}
		tree.checkNode(treeNode,true,true,false)
		var html = "<li id='user-" + treeNode.account + "'>";
		html += treeNode.name;
		html += "</li>";
		if (treeNode.checked) {
			$("#selectconfuser").append(html);
		} else {
			$("#user-" + treeNode.account).remove();
		}
	}
	
}
/**
 * 更新默认与会者
 * @param treeNode
 */
function saveconfuser(treeNode){
	var id = $("#confuserconfid").val();
	var param = {
		accessKey : token,
		orgid : orgid,
		cid : id,
		commonList:treeNode.account,
		adminList:""
	}
	var url = apiurl + "/apis/conf/confuseraddbyname";
	if(!treeNode.checked){
		url = apiurl + "/apis/conf/confuserdelbyname";
	}
	$.ajax({
		type : "get",
		async : false,
		url : url,
		data : param,
		dataType : "jsonp",
		success:function(data){
			if(data.msg==0){
				var html = "<li id='user-" + treeNode.account + "'>";
				html += treeNode.name;
				html += "</li>";
				if (treeNode.checked) {
					$("#selectconfuser").append(html);
				} else {
					$("#user-" + treeNode.account).remove();
				}
			}else{
				msg({code:data.msg,msg:errorMap.get(data.msg+"")})
			}
		}
	});
}
/**
 * 更新默认与会者
 * @param treeNode
 */
function saveconfuser_list(treeNode,accounts){
	var id = $("#confuserconfid").val();
	var param = {
		accessKey : token,
		orgid : orgid,
		cid : id,
		commonList:accounts,
		adminList:""
	}
	var url = apiurl + "/apis/conf/confuseraddbyname";
	if(!treeNode.checked){
		url = apiurl + "/apis/conf/confuserdelbyname";
	}
	$.ajax({
		type : "get",
		async : false,
		url : url,
		data : param,
		dataType : "jsonp",
		success:function(data){
			confuser(id);
		}
	});
}
/**
 * 添加会议管理员
 * @param id
 */
function confadmin(id){
	$("#confusertype").val("1");
	$("#confuserconfid").val(id);
	$("#confuserLabel").html("选择会议管理员");
	/*$('#confusermodel').modal({
		  keyboard: false
	});*/
	var param = {
		accessKey : token,
		cid : id
	}
	var url = apiurl + "/apis/conf/confadminlist?callback=back_confadmin";
	$.ajax({
		type : "get",
		async : false,
		url : url,
		data : param,
		dataType : "jsonp"
	});
	confdebug("获取默认与会者列表：", url + param);
	//tree.checkAllNodes(false);
	$("#selectconfuser").empty();
}
/**
 * 添加会议管理员回调
 * @param users
 */
function back_confadmin(users){
	confdebug("获取默认与会者列表回调：",users);
	if(users.length==0){
		return;
	}
	/*$("#selectconfuser").empty();*/
	for(var i=0;i<users.length;i++){
		var treeNode = tree.getNodeByParam("account",users[i],null);
		console.log(treeNode);
		if(treeNode==null){
			continue;
		}
		tree.checkNode(treeNode,true,true,false)
		var html = "<li id='user-" + treeNode.account + "'>";
		html += treeNode.name;
		html += "</li>";
		if (treeNode.checked) {
			$("#selectconfuser").append(html);
		} else {
			$("#user-" + treeNode.account).remove();
		}
	}
}
/**
 * 保存会议管理员
 * @param treeNode
 */
function saveconfadmin(treeNode){
	var id = $("#confuserconfid").val();
	var param = {
		accessKey : token,
		orgid : orgid,
		cid : id,
		commonList:"",
		adminList:treeNode.account
	}
	var url = apiurl + "/apis/conf/confuseraddbyname";
	if(!treeNode.checked){
		url = apiurl + "/apis/conf/confuserdelbyname";
	}
	$.ajax({
		type : "get",
		async : false,
		url : url,
		data : param,
		dataType : "jsonp",
		success:function(data){
			if(data.msg==0){
				var html = "<li id='user-" + treeNode.account + "'>";
				html += treeNode.name;
				html += "</li>";
				if (treeNode.checked) {
					$("#selectconfuser").append(html);
				} else {
					$("#user-" + treeNode.account).remove();
				}
			}else{
				msg({code:data.msg,msg:errorMap.get(data.msg+"")})
			}
		}
	});
}
/**
 * 保存会议管理员
 * @param treeNode
 */
function saveconfadmin_list(treeNode,account){
	var id = $("#confuserconfid").val();
	var param = {
			accessKey : token,
			orgid : orgid,
			cid : id,
			commonList:"",
			adminList:account
	}
	var url = apiurl + "/apis/conf/confuseraddbyname";
	if(!treeNode.checked){
		url = apiurl + "/apis/conf/confuserdelbyname";
	}
	$.ajax({
		type : "get",
		async : false,
		url : url,
		data : param,
		dataType : "jsonp",
		success:function(data){
			confadmin(id);
		}
	});
}


