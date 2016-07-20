<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% 
%>
<script type="text/javascript" src="${basePath }js/jquery-1.4.4.min.js"></script>	
<script type="text/javascript" src="${basePath }js/jquery.cookie.js"></script>	
<script type="text/javascript" src="${basePath }plugin/ztree/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="${basePath }plugin/ztree/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="${basePath }page/plugin/depart/js/search_tree.js"></script>
<style>
.p-pagecontent{
	box-sizing:border-box!important;  
	padding: 30px 16px;
	width: 100%;
	overflow: hidden;
}
.p-user-left{
	width: 200px;
	float: left;
}
.p-user-right{
	width:500px;
	float: left;
}
.p-info{
	display: none;
}
.ztree li span.button.add{
	margin-right: 2px;
    background-position: -144px 0px;
    vertical-align: top;
}
.ztree li span.button.edit{
	margin-right: 2px;
    background-position: -110px -48px;
    vertical-align: top;
}
</style>
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div class="p-pagecontent">
				<div class="p-user-left">
					<button onclick="deleteall()">批量删除用户</button>
					<div class="p-u-title"><input id="search_text" style="width: 150px;"/><button onclick="search_ztree('userdepart-ztree', 'search_text')">搜索</button></div>
					<div id="userdepart-ztree" class=" ztree" >
						
					</div>
				</div>
				<div class="p-user-right" >
					<div id="p-userinfo" class="p-info">
						<table>
							<input id="user-id" type="hidden"/>
							<input id="user-departid" type="hidden" />
							<tr><td colspan="2" align="center" ><b class="d-title">修改用户信息</b></td></tr>
							<tr><td>帐号</td><td><input id="user-account"/></td></tr>
							<tr><td>密码</td><td><input id="user-password" type="password"/></td></tr>
							<tr><td>昵称</td><td><input id="user-alias"/></td></tr>
							<tr><td>电话</td><td><input id="user-tel"/></td></tr>
							<tr><td>邮箱</td><td><input id="user-email"/></td></tr>
							<tr><td>角色</td><td><input type="checkbox" name="user-role" value="3"/>用户管理员<input  name="user-role" type="checkbox" value="2" style="margin-left: 20px"/>会议管理员</td></tr>
							<tr><td>排序</td><td><input id="user-orders"/></td></tr>
							<tr><td colspan="2" align="center"><button onclick="saveUser()">提交</button></td></tr>
						</table>
					</div>
					<div id="p-departinfo" class="p-info">
						<table>
							<input id="depart-id" type="hidden"/>
							<input id="depart-pnum" type="hidden"/>
							<tr><td colspan="2" align="center"><b class="d-title">修改部门信息</b></td></tr>
							<tr><td>部门编号</td><td><input id="depart-num"/></td></tr>
							<tr><td>部门名称</td><td><input id="depart-name"/></td></tr>
							<tr><td>部门排序</td><td><input id="depart-orders"/></td></tr>
							<tr><td colspan="2" align="center"><button onclick="saveDepart()">提交</button></td></tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br class="clear" />
	<div class="clear"></div>
</div>
<script type="text/javascript" src="${basePath }js/jquery-1.4.4.min.js"></script>	
<script type="text/javascript" src="${basePath }js/jquery.cookie.js"></script>	
<script type="text/javascript" src="${basePath }plugin/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
var token = "${token}";
var username = "${userid}";
apiurl = getRootPath()+"/opm4j";
var orgid = ${orgid};

var departclick = true;
/**ztree的参数配置，setting主要是设置一些tree的属性，是本地数据源，还是远程，动画效果，是否含有复选框等等**/  
var tree;//部门人员树
var setting = {
 check: { /**复选框**/
  enable: true,
  chkboxType: {"Y":"ps", "N":"ps"}
 },
 edit: {
		enable: true,
		showRenameBtn: false,
		showRemoveBtn: true,
		removeTitle: "删除",
	},
 view: {                                  
  //dblClickExpand: false,
  expandSpeed: 100, //设置树展开的动画速度，IE6下面没效果，
  addHoverDom: addHoverDom,
	removeHoverDom: removeHoverDom
 },                           
 data: {                                  
  simpleData: {   //简单的数据源，一般开发中都是从数据库里读取，API有介绍，这里只是本地的                         
   enable: true,
   idKey: "id",  //id和pid，这里不用多说了吧，树的目录级别
   pIdKey: "pid",
   rootPId: 0   //根节点
  }                          
 },                         
 callback: {     /**回调函数的设置，随便写了两个**/
  beforeClick: beforeClick,                                  
  onCheck: onCheck,
  onExpand: OnExpand,                   
  onCollapse: OnCollapse   ,
  beforeRemove: zTreeBeforeRemove,//节点移除
 }
};
function beforeClick(treeId, treeNode) {
    //console.log(treeNode);
    //tree.setChkDisabled(treeNode, true);
    if(treeNode.type==1){
		getUserinfo(treeNode.realid);
    }else if(treeNode.type==0){
    	//getDepartinfo(treeNode.realid);
    }
}
function onCheck(e, treeId, treeNode) {
	/*tree.expandNode(treeNode, treeNode.checked, false, false);*/
}  
function OnExpand(event, treeId, treeNode) {
    $.cookie("user_open_"+treeNode.id,"1",{expires: 1000});
}  
function OnCollapse(event, treeId, treeNode) {
    $.cookie("user_open_"+treeNode.id,"0",{expires: 1000});
}  
function addHoverDom(treeId, treeNode) {//鼠标悬停添加新增按钮
	if(treeNode.type==1){
		return;
    }else if(treeNode.type==0){
    	departclick = true;
    }
	var aObj = $("#" + treeNode.tId + "_a");
	if ($("#diyBtn_"+treeNode.id).length>0) return;
	var addStr = "<span id='diyBtn_space_" +treeNode.id+ "' class='button add' ></span>";
	var editStr = "<span id='diyBtn_space1_" +treeNode.id+ "' class='button edit' ></span>";
		/*+ "<button type='button' class='buttom remove' id='diyBtn_" + treeNode.id
		+ "' title='"+treeNode.name+"' onfocus='this.blur();'></button>";*/
	if($("#diyBtn_space_" +treeNode.id).length<=0){
		if(treeNode.id==null){
			return;
		}
		aObj.append(editStr);
		aObj.append(addStr);
		var btn = $("#diyBtn_space_"+treeNode.id);
		if (btn) btn.bind("click", function(){
			addUser(treeNode.realid)
		});
		var btn1 = $("#diyBtn_space1_"+treeNode.id);
		if (btn1) btn1.bind("click", function(){
			getDepartinfo(treeNode.realid)
		});
	}
};
function removeHoverDom(treeId, treeNode) {
	//$("#diyBtn_"+treeNode.id).remove();
	$("#diyBtn_space_" +treeNode.id).remove();
	$("#diyBtn_space1_" +treeNode.id).remove();
};


$(function (){
	inittree();
})
function inittree(){//初始化部门树
	var url = "${basePath}departServlet?m=dutree&orgid="+orgid+"&r="+Math.random();
	$.getJSON(url,function(data){
		if(data.code==0){
			var departtree = data.data;
			for(var i=0;i<departtree.length;i++){
				departtree[i].open=false;
				if($.cookie("user_open_"+departtree[i].id)=="1"){
					departtree[i].open=true;
				}
			}
			tree = $.fn.zTree.init($("#userdepart-ztree"),setting, departtree);
		}else{
			msg(data);
		}
	})
}
/**
 * 节点是否展开
 */
function isExpand(node){
	return node.open;
}
/**
 * 一次展开节点
 */
function expandNodes(nodes){
	for(var i=0;i<nodes.length;i++){
		tree.expandNode(nodes[i], true, false, false);
	}
}
/**
 * 获得单个用户信息
 */
function getUserinfo(id){
	var url = "${basePath}departServlet?m=getuser&r="+Math.random();
	var param={
			id:id,
			orgid:orgid
	};
	$.ajax({
		url:url,
		data:param,
		type:"get",
		success:function(data){
			console.log(data);
			if(data.code==0){
				var user = data.data;
				$("#user-account").attr("readonly",true);
				$("#p-userinfo .d-title").html("修改用户信息");
				$("#p-departinfo").hide();
				$("#p-userinfo").show();
				$("#user-id").val(user.id);
				$("#user-account").val(user.username);
				$("#user-password").val(user.password);
				$("#user-alias").val(user.alias);
				$("#user-tel").val(user.telphone);
				$("#user-email").val(user.email);
				$("#user-departid").val(user.departid);
				$("#user-orders").val(user.orders);
				var role = user.role;
				$("#p-userinfo input[name='user-role']").attr("checked",false);
				if(role==1){
					$("#p-userinfo input[name='user-role']").attr("checked",true);
				}else if(role==4){
					$("#p-userinfo input[name='user-role']").attr("checked",false);
				}else{
					$("#p-userinfo input[value='"+role+"']").attr("checked",true);
				}
			}
		},error:function(){
			
		}
	});
}
/**
 * 获得单个部门信息
 */
function getDepartinfo(id){
	var url = "${basePath}departServlet?m=getdepart&r="+Math.random();
	var param={
			id:id,
			orgid:orgid
	};
	$.ajax({
		url:url,
		data:param,
		type:"get",
		success:function(data){
			console.log(data);
			if(data.code==0){
				$("#depart-num").attr("readonly",true);
				$("#p-departinfo .d-title").html("修改部门信息");
				var depart = data.data;
				$("#p-userinfo").hide();
				$("#p-departinfo").show();
				$("#depart-id").val(depart.id);
				$("#depart-num").val(depart.id);
				$("#depart-name").val(depart.name);
				$("#depart-pnum").val(depart.pid);
				$("#depart-orders").val(depart.orders);
			}
			//console.log(data);
		},error:function(){
			
		}
	});
}
function saveUser(){
	var url = "${basePath}departServlet?m=saveuser&r="+Math.random();
	var role=4;
	if($("#p-userinfo input[name='user-role']:checked").length==2){
		role=1
	}else if($("#p-userinfo input[name='user-role']:checked").length==1){
		role=$("#p-userinfo input[name='user-role']:checked").val();
	}
	var param={
			id:$("#user-id").val(),
			account:$("#user-account").val(),
			password:$("#user-password").val(),
			alias:$("#user-alias").val(),
			phone:$("#user-tel").val(),
			email:$("#user-email").val(),
			departid:$("#user-departid").val(),
			orders:$("#user-orders").val(),
			role:role,
	};
	$.ajax({
		url:url,
		data:param,
		type:"get",
		success:function(data){
			console.log(data);
			if(data.code==0){
				alert("成功");
				inittree();
			}
			//console.log(data);
		},error:function(){
			
		}
	});
}
function saveDepart(){
	var url = "${basePath}departServlet?m=savedepart&r="+Math.random();
	var param={
			id:$("#depart-id").val(),
			name:$("#depart-name").val(),
			pid:$("#depart-pnum").val(),
			orders:$("#depart-orders").val(),
	};
	$.ajax({
		url:url,
		data:param,
		type:"get",
		success:function(data){
			if(data.code==0){
				alert("成功");
				inittree();
			}
			console.log(data);
		},error:function(){
			
		}
	});
}
function addUser(departid){
	$("#user-account").attr("readonly",false);
	$("#p-userinfo .d-title").html("添加用户");
	$("#p-departinfo").hide();
	$("#p-userinfo").show();
	$("#user-id").val(0);
	$("#user-account").val("");
	$("#user-password").val("");
	$("#user-alias").val("");
	$("#user-tel").val("");
	$("#user-email").val("");
	$("#user-departid").val(departid);
	$("#user-orders").val(10000);
}
function deleteuser(userids){
	var url = "${basePath}departServlet?m=deletedu&r="+Math.random();
	var param={
			uids:userids
	};
	$.ajax({
		url:url,
		data:param,
		type:"get",
		success:function(data){
			if(data.code==0){
				alert("成功");
				inittree();
			}else{
				alert("失败");
			}
			console.log(data);
		},error:function(){
			
		}
	});
}
function zTreeBeforeRemove(treeId, treeNode) {
	if(treeNode.type==0){
		alert("部门不能被删除");
		return false;
	}
	var uids = treeNode.realid;
	var url = "${basePath}departServlet?m=deletedu&r="+Math.random();
	var param={
			uids:uids
	};
	$.ajax({
		url:url,
		data:param,
		type:"get",
		success:function(data){
			if(data.code==0){
				alert("成功");
				tree.removeNode(treeNode);
			}else{
				alert("失败");
			}
			console.log(data);
		},error:function(){
			
		}
	});
	return false;
} 
function deleteall(){
	var nodes = tree.getCheckedNodes(true);
	if(nodes==null||nodes.length==0){
		alert("没有被选中的对象");
		return;
	}
	var ids = "";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].type==1){
			ids+=","+nodes[i].realid;
		}
	}
	if(ids!=""){
		ids=ids.substring(1,ids.length);
	}
	deleteuser(ids);
}
</script>
<script>
function msg(data){
	
}
</script>