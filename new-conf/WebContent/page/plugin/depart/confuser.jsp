<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.p-userwindow{
	display: none;
	position: absolute;
	width: 600px;
	height: 600px;
	margin: 0 auto;
	background-color: #FFF;
	border: 1px solid #ccc;
	left:20%;
	top:10%;
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
.p-suserlist{
	float: right;
	width: 280px;
	height: 100%;
	margin-top: -10px;
	border-left:1px solid #ddd; 
}
.p-u-title{
	height: 30px;
	line-height: 30px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}
</style>

<input type="hidden" id="confusertype" value="0" />
<input type="hidden" id="confuserconfid" value="0" />
<div id="p-conf-bg" style="position: absolute;top: 0;left: 0;background-color:#333;width:100%;height:100%;display: none;"></div>
<div id="p-conf-admin" class="p-userwindow">
	<div class="p-title"><span id="confuserLabel">会议管理员选择</span><a href="javascript:void(0)" class="p-close">关闭</a></div>
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
<div id="p-conf-user" class="p-userwindow">

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
token = "${token}";
userpass = "${userpass}";
username = "${userid}";
apiurl = "${apiurl}";
md5userpass = "${userpassmd5}";
orgid = ${orgid};
$("#p-conf-bg").css({'opacity':'0.4'});
$("#confuserconfid").val($("#cid").val());
$(function(){
	//$(".ui-state-default").removeClass("ui-state-disabled");
	$(".ui-state-default").css({"background-color": "#348CD4"})
	$(".ui-state-default a").css({"color": "#fff"})
	inittree();
})
function p_confcommon(){
	$("#p-conf-bg").hide();
	$("#p-conf-user").hide();
	$("#p-conf-admin").show();
	confuser($("#cid").val());
}
function p_confadmin(){
	$("#p-conf-bg").hide();
	$("#p-conf-user").hide();
	$("#p-conf-admin").show();
	confadmin($("#cid").val());
}
$(".p-close").click(function(){
	$(this).parent().parent().hide();
	$("#p-conf-bg").hide();
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
		return true;
    }else if(treeNode.type==0){//department
		return false;
    }
}
function onCheck(e, treeId, treeNode) {
	 if(treeNode.type==1){
		if($("#confusertype").val()=="0"){
			saveconfuser(treeNode);
		}else if($("#confusertype").val()=="1"){
			saveconfadmin(treeNode);
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
	var url = "${basePath}departServlet?m=dutree&r="+Math.random();
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
	conflist();
})


</script>