<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.seegle.util.PropUtil"%>
<%@page import="com.seegle.util.LanguageUtil"%>
<%@page import="com.seegle.data.GetCookies"%>
<% 
String siteurl = request.getSession().getAttribute("siteurl").toString();
response.setHeader("Pragma","No-Cache");
response.setHeader("Cache-Control","No-Cache");
response.setDateHeader("Expires", 0); 
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
%>
${msg}
<div id="wpbody-content">
	<div class="wrap">
		<div class="space_03">
			<div id="topLinkL">
				<div class="s_001"><%=lu.getLanguage(language,"changelogo.upload","上传logo") %></div>
			</div>
		<input type="hidden" id="siteurl" name="siteurl" value="<%=siteurl %>"/>
		<table width="90%" border="0" cellpadding="0" cellspacing="0" bgcolor="#DDDDDD" class="tamnew">
		<div id="uploadTip"><span id="feedback"></span></div>  
		<form id="form" action="UploadLogo?action=changelogo&language=<%=language %>" method="post" autocomplete='off' enctype="multipart/form-data" onSubmit="return validate()">
	      <tr>
            <td width="15%"><label for="alias"><%=lu.getLanguage(language,"changelogo.pic","logo图片") %></label></td>
            <td width="85%">
            	<iframe name="upload_target" id="upload_target" style="display:none"></iframe>
				<div style="position:relative"> <!--下面3个控件的位置不要更改，但是另外2个控件如何遮盖住file控件，如果在你的浏览器上有差异，自己调下style属性-->
				<input type="button" value="<%=lu.getLanguage(language,"button.browse","浏览...") %>" readonly style="position:absolute;" /><!--显示给用户看的，注意按钮大小要和file的浏览基本一致，要不点击其他这个按钮的其他区域可能无法弹出选择文件窗口-->
				<input id="logo" name="logo" type="file" style="height:25px;width:65px;position:relative;opacity:0;filter:alpha(opacity=0);cursor:pointer;" /><!--透明file控件-->
				<div style="position:absolute;height:0px;width:0px;background:#fff;top:0px;left:0px;"></div><!--这个遮盖住file控件前面的输入框，要不即使透明了点击输入部分会显示光标-->
				</div>
			</td>
	      </tr>
	      <tr>
		    <td colspan="2">
                <%=lu.getLanguage(language,"changelogo.message","为了达到最佳效果，建议上传logo图片尺寸为1102*128") %>！
		    </td>
	      </tr>
	     <tr>
		    <td colspan="2" valign="middle" style="height:40px; text-align:center;">
			<input type="submit" id="uploadlogo" value="<%=lu.getLanguage(language,"button.upload","上传") %>" class="sbutton"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="returndefault" value="<%=lu.getLanguage(language,"changelogo.returndefault","恢复默认") %>" class="sbutton" />
		 </td>
	   </tr>
		</form>	
		</table>				
		</div>
	</div>
</div>
	<script type="text/javascript">
		function validate(){
			var logo = $('#logo').val();
			var pos=logo.lastIndexOf(".");
			var logoext = logo.substring(pos+1).toLowerCase( );
			if(logo==null||logo==""){
				alert(getMsg(ERR_NO_IMAGE));
				return false;
			}
			else if(logoext!="bmp"&&logoext!="gif"&&logoext!="jpg"&&logoext!="jpeg"&&logoext!="png"){
				alert(getMsg(ERR_IMAGE_ERROR));
				return false;
			}else{
				fl.parentNode.innerHTML=fl.parentNode.innerHTML;
				$('#uploadlogo').click();
				return true;
			}
		}
		$(document).ready(function() {
			$('#orglogo').addClass("sel_tag");
			var siteurl = $('#siteurl').val();
			
			$('#returndefault').click(function(){
				$.post(siteurl+"/UploadLogo", { action:'renewlogo', _: new Date().getTime() } ,function(rs) {
					if(rs==0){
						alert(getMsg(LOGO_MOD_SUSCESS));
						location.href=siteurl+'/ConfPage.go?inc=ChangeLogo';
					} 
					else if(rs==1){
						alert(getMsg(LOGO_IS_DEFAULT));
						location.href=siteurl+'/ConfPage.go?inc=ChangeLogo';
					}else {
						alert(getMsg(LOGO_MOD_FAILURE));
						location.href=siteurl+'/ConfPage.go?inc=ChangeLogo';
					}
				});
			});
		});
	</script>