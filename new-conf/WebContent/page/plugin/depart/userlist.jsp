<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% 
%>
<script type="text/javascript" src="${basePath }js/jquery-1.4.4.min.js"></script>	
<script type="text/javascript" src="${basePath }js/jquery.cookie.js"></script>	
<script type="text/javascript" src="${basePath }plugin/ztree/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="${basePath }plugin/ztree/zTreeStyle/zTreeStyle.css" />
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
</style>
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div class="p-pagecontent">
				<div id="userdepart-ztree" class="p-user-left ztree" >
					
				</div>
				<div class="p-user-right" >
					<div id="p-userinfo" class="p-info">
						<table>
							<tr><td>帐号</td><td><input id="user-account"/></td></tr>
							<tr><td>密码</td><td><input id="user-password" type="password"/></td></tr>
							<tr><td>昵称</td><td><input id="user-alias"/></td></tr>
							<tr><td>电话</td><td><input id="user-tel"/></td></tr>
							<tr><td>邮箱</td><td><input id="user-email"/></td></tr>
							<tr><td>角色</td><td></td></tr>
							<tr><td>排序</td><td><input id="user-order"/></td></tr>
						</table>
					</div>
					<div id="p-departinfo" class="p-info">
						<table>
							<tr><td>部门编号</td><td><input id="depart-num"/></td></tr>
							<tr><td>部门名称</td><td><input id="depart-name"/></td></tr>
							<tr><td>上级部门</td><td><input id="depart-pnum"/></td></tr>
							<tr><td>部门排序</td><td><input id="depart-pnum"/></td></tr>
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
var token = "${token}";
var username = "${userid}";
var apiurl = "${apiurl}";
var orgid = ${orgid};


/**ztree的参数配置，setting主要是设置一些tree的属性，是本地数据源，还是远程，动画效果，是否含有复选框等等**/  
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
  onCollapse: OnCollapse                           
 }
};
function beforeClick(treeId, treeNode) {
    //console.log(treeNode);
    //tree.setChkDisabled(treeNode, true);
    if(treeNode.type==1){
		getUserinfo(treeNode.realid);
    }else if(treeNode.type==0){
    	getDepartinfo(treeNode.realid);
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
		},error:function(){
			
		}
	});
}
/**
 * 获得单个部门信息
 */
function getDepartinfo(id){
	
}
</script>
<script>
function msg(data){
	
}
</script>