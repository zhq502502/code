<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.message{
	padding: 15px 15px 15px 15px;
}
.menu-0,.menu-0 td{
	background-color: ;
}

</style> 
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
					<div class="s_001">系统菜单</div>
					<div class="s_002">
					</div>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="tabnew" id='tabnew'>
					<thead>
						<tr><td colspan="6"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabtitle" id='tabtitle'>
						<tr>
							<td width="10%" class="tdTitle">ID</td>
							<td width="25%" class="tdTitle">菜单名称</td>
							<td width="30%" class="tdTitle">菜单URL</td>
							<td width="10%" class="tdTitle">菜单排序</td>
							<td width="25%" class="tdTitle">操作</td>
						</tr>
						</table></td></tr>
					</thead>
					<tbody>	
					<tr><td colspan="6"><table width="90%" border="0" cellpadding="0" cellspacing="0" class="tabcontent" id='tabcontent'>
					<c:forEach items="${list}" var="menu">
						<tr class="menu-${menu.parentId }" mid="${menu.id }" pid="${menu.parentId }" ind="${menu.menuIndex}" >
							<td width="10%">${menu.id }</td>
							<td width="25%" style="text-align: left" align="left">
							<div style="width: 100%">
							<c:choose>
								<c:when test="${menu.parentId eq '0'}">
									<div class="menu-top" style="width: 10px;height100%;float: left">&nbsp;</div>
								</c:when>
								<c:otherwise>
									<div class="menu-item" style="width: 40px;height100%;float: left">&nbsp;</div>
								</c:otherwise>
							</c:choose>
								<div style="height100%;float: left">${menu.menuName }</div>
							</div>
							</td>
							<td width="30%" style="text-align: left" align="left">${menu.menuUrl}</td>
							<td width="10%">${menu.menuIndex}</td>
							<td width="25%" >
							<c:if test="${menu.parentId eq 0}">
							<img src='images_gb/add.gif' width='16' height='16' class='img_tab' /><a class='join' href='javascript:addMenu(${menu.id});'>添加子项</a>
							</c:if>
							<img src='images_gb/d_sign.gif' width='16' height='16' class='img_tab' /><a class='join' href='javascript:editMenu(${menu.id});'>编辑</a>
							<img src='images_gb/del.gif' width='16' height='16' class='img_tab' /><a class='geturl' href='javascript:delMenu(${menu.id});'>删除</a>
							</td>
						</tr>
					</c:forEach>
					</table>
					</tbody>
					<tr>
					    <td colspan="5" valign="middle" style="height:40px; text-align:center;">
							<input type="submit" onclick="addMenu()" value="添加菜单" class="sbutton" />
						</td>
					</tr>
			</table>
		</div>
	</div>
</div>

<div id='dialog_box' style='display: none;height: 430px; outline: 0px none; width: 450px; overflow: visible;'>
<div class='message'>
	<div style="width: 400px;height: 250px; border: 1px solid #dddddd">
	<table width="93%" cellpadding="0" cellspacing="0" border="0" align="center" style=" table-layout:fixed">
		<input name="id" id="id" type="hidden"/>
		<tr height="">
			<td width="20%" align="right">菜单名称:</td>
			<td width="80%"><input name="menuName" id="menuName" style="width: 250px"/></td>			
		</tr>
		<tr height="">
			<td width="" align="right">菜单url:</td>
			<td width=""><input name="menuUrl" id="menuUrl" style="width: 250px"/></td>			
		</tr>
		<tr height="">
			<td width="" align="right">上级菜单:</td>
			<td width="">
			<select name="parentId" id="parentId">
				<option value="0">顶级菜单</option>
				<c:forEach items="${listTop}" var="menu">
					<option value="${menu.id }">${menu.menuName }[${menu.id }]</option>
				</c:forEach>
			</select>
			</td>			
		</tr>
		<tr height="">
			<td width="" align="right">权限代码:</td>
			<td width=""><input name="menuCode" readonly="readonly" id="menuCode" style="width: 80px"/></td>			
		</tr>
		<tr height="">
			<td width="" align="right">菜单排序:</td>
			<td width=""><input name="menuIndex" id="menuIndex" style="width: 40px"/></td>			
		</tr>
		<tr height="">
			<td width="" align="right">是否显示:</td>
			<td width="">
				<select name="flag" id="flag">
					<option value="0">显示</option>
					<option value="1">不显示</option>
				</select>
			</td>			
		</tr>
		<tr height="">
			<td width="" align="right">菜单描述:</td>
			<td width=""><textarea name="menuDiscription" id="menuDiscription" rows="3" cols="42"></textarea>  </td>			
		</tr>
	</table>
	</div>
	<br class="clear" />
	<div align="right">
	<input type="button" value="确定" class="sbutton" onclick="okselect()" />
	<input type="button" value="取消" class="sbutton" onclick="closeDlog()" />
	</div>
</div>
</div>

<script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
<script src="js/date/WdatePicker.js"></script>
<script type="text/javascript">
$(function(){
	$("#parentId").bind("change",function(){
		var pid = $(this).val();
		var tr = $("tr [pid='"+pid+"']");
		if(tr!=null&&tr.length>0){
			var index = parseInt($(tr[tr.length-1]).attr("ind"))+1;
			$('#menuIndex').val(index);
		}else{
			$('#menuIndex').val(0);
		}
	})
});
/*添加菜单*/
function addMenu(id){	
	$('#id').val("");
	$('#menuName').val("");
	$('#menuUrl').val("");
	$('#menuIndex').val("0");
	$('#parentId').val(id);
	$('#menuCode').val("0");
	$('#menuDiscription').val("");
	$('#flag').val("");
	if(id==null){
		var tr = $("tr [pid='0']");
		if(tr!=null&&tr.length>0){
			var index = parseInt($(tr[tr.length-1]).attr("ind"))+1;
			$('#menuIndex').val(index);
		}
	}else{
		var tr = $("tr [pid='"+id+"']");
		if(tr!=null&&tr.length>0){
			var index = parseInt($(tr[tr.length-1]).attr("ind"))+1;
			$('#menuIndex').val(index);
		}
	}
	
	$('#dialog_box').dialog({
		modal: true,
		position:"20%",
		width:455,
		height:380,
		title:"<span style='color:#FFFFFF;'>添加菜单</span>"
		
	});
	$("#ui-dialog-title-dialog_box").css({"height":"25px","float":"left","padding-top":"5px","padding-left":"5px"});
	$(".ui-dialog-titlebar-close").css("float","right");
	$(".ui-dialog-titlebar-close").css("width","30px");
	$(".ui-dialog-titlebar").css("background-image","");
	$(".ui-dialog-titlebar").css("background-color","#348CD4");
	$("#dialog_box").css("border","0");
};
/*编辑菜单*/
function editMenu(id){
	$.getJSON("${siteurl}/MenuManagerAction.go", 
  		{
  		"method":"getOne",
  		id:id},
		function(rs) {
			$('#id').val(rs.id);
			$('#menuName').val(rs.menuName);
			$('#menuUrl').val(rs.menuUrl);
			$('#menuIndex').val(rs.menuIndex);
			$('#parentId').val(rs.parentId);
			$('#menuCode').val(rs.menuCode);
			$('#menuDiscription').val(rs.menuDiscription);
			$('#flag').val(rs.flag);
			
			$('#dialog_box').dialog({
				modal: true,
				position:"20%",
				width:455,
				height:380,
				title:"<span style='color:#FFFFFF;'>添加菜单</span>"
				
			});
			$("#ui-dialog-title-dialog_box").css({"height":"25px","float":"left","padding-top":"5px","padding-left":"5px"});
			$(".ui-dialog-titlebar-close").css("float","right");
			$(".ui-dialog-titlebar-close").css("width","30px");
			$(".ui-dialog-titlebar").css("background-image","");
			$(".ui-dialog-titlebar").css("background-color","#348CD4");
			$("#dialog_box").css("border","0");
  		}
	);
};
/*编辑菜单*/
function delMenu(id){
	$.getJSON("${siteurl}/MenuManagerAction.go", 
  		{
  		"method":"del",
  		id:id},
  		function(rs1) {
			if(rs1==0){
				closeDlog();
				alert("操作成功");
				location.href='${siteurl}/ConfPage.go?inc=MenuManager';
			} 
			else {
				alert("操作失败");
				return false;
			}
  		}
	);
};
//关闭弹层
$('#dialog_box').dialog("close");
/*确定*/
function okselect(){
	var id = $('#id').val();
	var menuName = $('#menuName').val();
	var menuUrl = $('#menuUrl').val();
	var menuIndex = $('#menuIndex').val();
	var parentId = $('#parentId').val();
	var menuCode = $('#menuCode').val();
	var menuDiscription = $('#menuDiscription').val();
	var flag = $('#flag').val();
	
   	$("#l_msg").hide();
  	$.post("${siteurl}/MenuManagerAction.go", 
  		{
  		"method":"saveOrUpdate",
  		id:id,
  		menuName: menuName,
  		menuUrl: menuUrl,
  		menuIndex: menuIndex,
  		parentId: parentId,
  		menuCode: menuCode,
  		menuDiscription:menuDiscription,
  		flag: flag},
		  	function(rs1) {
			if(rs1==0){
				closeDlog();
				alert("操作成功");
				location.href='${siteurl}/ConfPage.go?inc=MenuManager';
			} 
			else {
				alert("操作失败");
				return false;
			}
  		}
	);	
}
/*关闭弹出框*/
function closeDlog(){
	$('#dialog_box').dialog("close");
}
</script>
