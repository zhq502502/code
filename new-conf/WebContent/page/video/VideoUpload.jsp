<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.seegle.util.PropUtil"%>
<%@page import="com.seegle.util.LanguageUtil"%>
<%@page import="com.seegle.data.GetCookies"%>
<% 
//获取语言
String language=""; 
Cookie cookies[]=request.getCookies(); 
GetCookies getCK = new GetCookies();
language = getCK.getCookie(cookies, "SGlanguage");  //获取cookie中的SGlanguage，zh_cn 简体中文； zh_tw 繁体中文；en 英文
if(language==null || "".equals(language)){ //如果cookie中没有SGlanguage，从配置中读取默认语言
	if(PropUtil.getInstance().getValue("language")!=null){
		language=PropUtil.getInstance().getValue("language"); //zh_cn 简体中文； zh_tw 繁体中文；en 英文	
	}
}
LanguageUtil lu = new LanguageUtil();
String siteurl = request.getSession().getAttribute("siteurl").toString();
%>
<script type="text/javascript" src="js/stringRes.js"></script>
<script type="text/javascript" src="swfupload/swfupload.js"></script>
<script type="text/javascript" src="swfupload/handlers_<%=language %>.js"></script>
<script type="text/javascript">
	var swfu;
	var filesize = "${fileSizeLimit}";
	if(filesize==""){
		filesize="2047";
	}
	$(function(){
	swfu = new SWFUpload({
		upload_url: "VideoUploadAction.go?upload=upload",
		// File Upload Settings
		file_size_limit : filesize+"MB",	// 1000MB
		file_types : "*.smvx;*.rmvb;*.wmv",//设置可上传的类型
		file_types_description : getMsg(VIDEO_FILE),
		file_upload_limit : "10",
						
		file_queue_error_handler : fileQueueError,//选择文件后出错
		file_dialog_complete_handler : fileDialogComplete,//选择好文件后提交
		file_queued_handler : fileQueued,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError,
		upload_success_handler : uploadSuccess,
		upload_complete_handler : uploadComplete,

		// Button Settings
		button_image_url : "images/SmallSpyGlassWithTransperancy_17x18.png",
		button_placeholder_id : "spanButtonPlaceholder",
		button_width: 100,
		button_height: 18,
		button_text : '<span class="button">'+getMsg(SELECT_VIDEO_FILE)+'</span>',
		button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
		button_text_top_padding: 0,
		button_text_left_padding: 18,
		button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
		button_cursor: SWFUpload.CURSOR.HAND,
		
		// Flash Settings
		flash_url : "swfupload/swfupload.swf",

		custom_settings : {
			upload_target : "divFileProgressContainer"
		},
		// Debug Settings
		debug: false  //是否显示调试窗口
	});
	})
	function startUploadFile(){
		if($("#thumbnails .red").length==0){
			alert(getMsg(ERR_NO_VIDEO_FILE));
			return false;
		}
		swfu.startUpload();
	}
	function beforsub(){
		swfu.stop();
		return true;
	}

</script>
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001"><%=lu.getLanguage(language,"confpage.menu.videoupload","上传录像") %></div>
			</div>
		<input type="hidden" id="siteurl" name="siteurl" value="<%=siteurl %>"/>
		<table width="90%" border="0" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD" class="tamnew">
		<div id="uploadTip"><span id="feedback"></span></div>  
		<form id="form" action="ConfPage.go?inc=VideoUpload" method="post" autocomplete='off' enctype="multipart/form-data" >
	      <tr>
            <td width="15%"><%=lu.getLanguage(language,"videoupload.videofile","录像文件") %>：</td>
            <td width="85%">
            <span id="spanButtonPlaceholder">
            </span>
             <%--<input id="btnCancel" type="button" value="取消" onclick="cancelUpload();" disabled="disabled" style="margin-left: 2px; height: 22px; font-size: 8pt;" />--%>  
		  </td>
	      <tr>
            <td colspan="2">
            <div id="divFileProgressContainer" style="width:200;display:none;"></div>
				<div id="thumbnails" style="display:none;">
				<table id="infoTable" border="0" width="100%" style="border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;margin-top:8px;">
				</table>
			</div>
            </td>
		  </td>
	      </tr>
	      <tr>
		    <td colspan="2">
		    <%=lu.getLanguage(language,"videoupload.uploadlimit","只能上传smvx、rmvb、wmv格式的录像文件，单个视频文件不得超过") %>${fileSizeLimit}M
		    </td>
	      </tr>
	     <tr>
		    <td colspan="2" valign="middle" style="height:40px; text-align:center;">
			<input id="sc" type="button" onclick="startUploadFile()" value="<%=lu.getLanguage(language,"button.upload","上传") %>" class="sbutton"/>
		 </td>
	   </tr>
		</form>	
		</table>				
		</div>
	</div>
</div>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#video_upload').addClass("sel_tag");
	});
	</script>