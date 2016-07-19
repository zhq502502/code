<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.p-userwindow{
	
	width: 600px;
	height: 600px;
	margin: 5% auto;
	background-color: #FFF;
	border: 1px solid #ccc;
	
}
.p-title{
	height: 39px;
	line-height: 39px;
	font-size: 15px;
	font-weight: bold;
	width: 100%;
	border-bottom: 1px solid #aaa;
	background-color: #eee;
}
.p-close{
	display: block;
	float: right;
	margin-right: 10px;
	padding: 5px;
}
.p-content{
	width: 100%;
	height: 560px;
}
.p-userlist{
	float: left;
	width: 280px;
	height: 100%;
	border-right:1px solid #ddd; 
}
#userdepart-ztree{
	height:520px;
	overflow-y: scroll;
	box-sizing:border-box!important;  
}
.p-suserlist{
	float: right;
	width: 280px;
	height: 100%;
	margin-top: -10px;
	border-left:1px solid #ddd; 
}
#selectconfuser{
	height:520px;
	overflow-y: scroll;
	box-sizing:border-box!important;  
	padding: 5px;
}
.p-u-title{
	height: 30px;
	line-height: 30px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}
#selectconfuser li{
	height: 20px;
	line-height: 20px;
}
#p-conf-admin{
	width: 100%;
	height: 100%;
	display: none;
	position: absolute;
	top: 0;
	left:0;
}
</style>

<input type="hidden" id="confusertype" value="0" />
<input type="hidden" id="confuserconfid" value="0" />
<div id="p-conf-bg" style="position: absolute;top: 0;left: 0;background-color:#333;width:100%;height:100%;display: none;"></div>
<div id="p-conf-admin">
<div class="p-userwindow">
	<div class="p-title"><span id="confuserLabel" style="padding-left: 10px;">会议管理员选择</span><a href="javascript:void(0)" class="p-close">关闭</a></div>
	<div class="p-content">
		<div class="p-userlist">
			<div class="p-u-title">组织结构</div>
			<div id="userdepart-ztree" style="width: 100%" class="ztree"></div>
		</div>
		<div class="p-suserlist">
			<div class="p-u-title">被选中人员</div>
			<div class="panel-body" id="selectconfuser">
				
			</div>
		</div>
	</div>
</div>
</div>

<script type="text/javascript">
function msg(data){
	
}
</script>
<script type="text/javascript" src="${basePath }js/jquery-1.4.4.min.js"></script>	
<script type="text/javascript" src="${basePath }js/jquery.cookie.js"></script>	
<script type="text/javascript" src="${basePath }page/plugin/depart/js/base64.js"></script>	
<script type="text/javascript" src="${basePath }page/plugin/depart/js/init.js"></script>	
<script type="text/javascript" src="${basePath }page/plugin/depart/js/jquery.json-2.2.min.js"></script>	
<script type="text/javascript" src="${basePath }page/plugin/depart/js/json2.js"></script>	
<script type="text/javascript" src="${basePath }page/plugin/depart/js/md5.js"></script>	
<script type="text/javascript" src="${basePath }page/plugin/depart/js/map.js"></script>	
<script type="text/javascript" src="${basePath }page/plugin/depart/js/conf.js"></script>	
<script type="text/javascript" src="${basePath }page/plugin/depart/js/main.js"></script>

<script type="text/javascript" src="plugin/ztree/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="plugin/ztree/zTreeStyle/zTreeStyle.css" />


<script>
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
token = "${token}";
userpass = "${userpass}";
username = "${userid}";
//apiurl = getRootPath()+"/opm4j";
apiurl = "${apiurl}";
md5userpass = "${userpassmd5}";
orgid = ${orgid};
$("#p-conf-bg").css({'opacity':'0.4'});
$("#confuserconfid").val($("#cid").val());
function p_confcommon(){
	$("#p-conf-bg").show();
	$("#p-conf-user").hide();
	$("#p-conf-admin").show();
	tree.checkAllNodes(false);
	confuser($("#cid").val());
}
function p_confadmin(){
	$("#p-conf-bg").show();
	$("#p-conf-user").hide();
	$("#p-conf-admin").show();
	tree.checkAllNodes(false);
	confadmin($("#cid").val());
}
$(".p-close").click(function(){
	$("#p-conf-admin").hide();
	$("#p-conf-bg").hide();
	$("#p-conf-user").hide();
})
</script>

<script>
var tree;//部门人员树
var setting = {
 check: { /**复选框**/
  enable: true,
  chkboxType: {"Y":"ps", "N":"ps"}
 },
 view: {                                  
  //dblClickExpand: false,
  expandSpeed: 100 //设置树展开的动画速度，IE6下面没效果，
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
  onCollapse: OnCollapse,
  beforeCheck: zTreeBeforeCheck
 }
};
function beforeClick(treeId, treeNode) {
    if(treeNode.type==1){//user
		console.log(treeNode);
    }else if(treeNode.type==0){//department
		console.log(treeNode);
    }
}
function zTreeBeforeCheck(treeId, treeNode) {
    if(treeNode.type==1){//user
    	console.log(treeNode.checked);
		return true;
    }else if(treeNode.type==0){//department
    	console.log(treeNode.checked);
		return true;
    }
}
function onCheck(e, treeId, treeNode) {
	 if(treeNode.type==1){
		if($("#confusertype").val()=="0"){
			saveconfuser(treeNode);
		}else if($("#confusertype").val()=="1"){
			saveconfadmin(treeNode);
		}
	 }else if(treeNode.type==0){//department
		 if($("#confusertype").val()=="0"){
			 f_saveconfuser_list(treeNode);
		}else if($("#confusertype").val()=="1"){
			f_saveconfadmin_list(treeNode);
		}
	 }
	/*tree.expandNode(treeNode, treeNode.checked, false, false);*/
}  
function OnExpand(event, treeId, treeNode) {
    $.cookie("user1_open_"+treeNode.id,"1",{expires: 1000});
}  
function OnCollapse(event, treeId, treeNode) {
    $.cookie("user1_open_"+treeNode.id,"0",{expires: 1000});
}     
function inittree(){//初始化部门树
	var url = "${basePath}departServlet?m=dutree&orgid="+orgid+"&r="+Math.random();
	$.getJSON(url,function(data){
		if(data.code==0){
			var departtree = data.data;
			for(var i=0;i<departtree.length;i++){
				departtree[i].open=false;
				if($.cookie("user1_open_"+departtree[i].id)=="1"){
					departtree[i].open=true;
				}
			}
			tree = $.fn.zTree.init($("#userdepart-ztree"),setting, departtree);
		}else{
			msg(data);
		}
	})
}
function showmenu(th){
	$(th).next().toggle();
}
function hidep(th){
	$(th).parent().parent().hide();
}
$("#ctitle").bind("click",function(){
	cname=$("#title").val();
	//conflist();
})
function f_saveconfuser_list(treeNode){
	var accounts = getchild(treeNode);
	console.log("accounts:"+accounts);
	if(accounts!=""){
		accounts=accounts.substring(1,accounts.length);
		saveconfuser_list(treeNode,accounts);
	}else{
		return;
	}
}
/**
 * 获取子节点的account
 */
function getchild(treeNode){
	var accounts = "";
	var nodes = treeNode.children;
	for(var i=0;i<nodes.length;i++){
		var node = nodes[i];
		if(node.isParent){
			accounts+=getchild(node);
		}else{
			accounts+=","+node.account;
		}
	}
	return accounts;
}
function f_saveconfadmin_list(treeNode){
	var accounts = getchild(treeNode);
	console.log("accounts:"+accounts);
	if(accounts!=""){
		accounts=accounts.substring(1,accounts.length);
		saveconfadmin_list(treeNode,accounts);
	}else{
		return;
	}
}
$(function(){
	//$(".ui-state-default").removeClass("ui-state-disabled");
})
$(".ui-state-default").css({"background-color": "#348CD4"})
$(".ui-state-default a").css({"color": "#fff"})
inittree();
</script>