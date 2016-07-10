function Agent_load_error() {
	return false;
}

function opendiv(param) 
{
	var language = getCookie("SGlanguage");  //从cookie中获得当前语种
	var siteurl=$('#siteurl').val();
	window.open (siteurl+'/webinstall_'+language+'.html'+param,'newwindow','height=400, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
}

function GoWenInstall()
{
	ClosesSuperAlert();
	opendiv('?Mode=2&type=1');
	return false;
}

function ClosesSuperAlert()
{
		document.body.removeChild(document.getElementById('div_bgObj'));   

		document.getElementById("msgDiv").removeChild(document.getElementById('div_msgTitle'));   

		document.body.removeChild(document.getElementById("msgDiv"));  
}

function DownloadOnlineSteup()
{
	ClosesSuperAlert();
	var url = $('#download_seegleOnline').val();
	if(url.indexOf("http://")==0){
		location.href=$('#download_seegleOnline').val();
	}else{
		location.href=$('#siteurl').val()+$('#download_seegleOnline').val();
	}
	return false;
}

function OnCancelSteup()
{
	ClosesSuperAlert();
	return false;
}

function SuperAlert()
{ 

	var str=getMsg(ERR_NO_SGCONF);

	var msgw,msgh,bordercolor;   

	msgw=380;//提示窗口的宽度  

	msgh=135;//提示窗口的高度  

	titleheight=25 //提示窗口标题高度  
	
	//英文版本的提示框需要改大
	var language = getCookie("SGlanguage");  //从cookie中获得当前语种
	if(language=="en"){
		msgw=650;//提示窗口的宽度  
	}

	bordercolor="#29A4EB";//提示窗口的边框颜色  

	titlecolor="#51B1E8";//提示窗口的标题颜色  

	 

	var sWidth,sHeight;   

	sWidth=screen.width;   

	sHeight=screen.height;   

	 

	var bgObj=document.createElement("div");   

	bgObj.setAttribute('id','div_bgObj');   

	bgObj.style.position="absolute";   

	bgObj.style.top="0";   

	bgObj.style.background="#F4F8FA";   

	bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";   

	bgObj.style.opacity="0.6";   

	bgObj.style.left="0";   

	bgObj.style.width=sWidth + "px";   

	bgObj.style.height=sHeight + "px";   

	bgObj.style.zIndex = "10000";   

	document.body.appendChild(bgObj);   

 

	var msgObj=document.createElement("div")   

	msgObj.setAttribute("id","msgDiv");   

	msgObj.setAttribute("align","center");   

	msgObj.style.background="white";   

	msgObj.style.border="1px solid " + bordercolor;   

	msgObj.style.position = "absolute";   

	msgObj.style.left = "50%";   

	msgObj.style.top = "50%";   

	msgObj.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif";   

	msgObj.style.marginLeft = "-225px" ;   

	msgObj.style.marginTop = -75+document.documentElement.scrollTop+"px";   

	msgObj.style.width = msgw + "px";   

	msgObj.style.height =msgh + "px";   

	msgObj.style.textAlign = "center";   

	msgObj.style.lineHeight ="25px";   

	msgObj.style.zIndex = "10001";   

	 

	var title=document.createElement("h4");   

	title.setAttribute("id","div_msgTitle");   

	title.setAttribute("align","left");   

	title.style.margin="0";   

	title.style.padding="3px";   

	title.style.background=bordercolor;   

	title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";   

	title.style.opacity="0.75";   

	title.style.border="1px solid " + bordercolor;   

	title.style.height="18px";   

	title.style.font="12px Verdana, Geneva, Arial, Helvetica, sans-serif";   

	title.style.color="white";   

	title.style.cursor="pointer";   

	title.innerHTML=getMsg(PROMOTION);  

	title.onclick=function()
	{   

		document.body.removeChild(bgObj);   

		document.getElementById("msgDiv").removeChild(title);   

		document.body.removeChild(msgObj);   

	}

	document.body.appendChild(msgObj);   

	document.getElementById("msgDiv").appendChild(title);   

	var txt=document.createElement("p");   

	txt.style.margin="1em 0" 

	txt.setAttribute("id","msgTxt");   

	txt.innerHTML=str+"<br><br><a href='javascript:void(0)' id='web_install' onclick='GoWenInstall()' > "+getMsg(SETUP_ONLINE)+"</a>"
	+"&nbsp&nbsp&nbsp<a href='javascript:void(0)' id='download_online_setup' onclick='DownloadOnlineSteup()' > "+getMsg(DOWNLOAD_INSTALLATION_PACKAGE)+"</a>"
	+"&nbsp&nbsp&nbsp<a href='javascript:void(0)' id='Close_download_div' onclick='OnCancelSteup()' > "+getMsg(CANCEL)+" </a><br>";     

	document.getElementById("msgDiv").appendChild(txt);   

} 

function copyToClipBoard() { 
	   var confurl = $('#confurl').val();
	   if (window.clipboardData) { 
		   if(window.clipboardData.setData("Text", confurl)){
		    	  alert(getMsg(COPY_SUCCESS));
	    	  return true;
	      }
	   } 
	   else if (window.netscape) { 
		   if(netscape.security.PrivilegeManager==null){
				  alert(getMsg(ERR_WRONG_BROWSER));
				  return false;
			  }
	      netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect'); 
	      var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard); 
	      if (!clip) return; 
	      var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable); 
	      if (!trans) return; 
	      trans.addDataFlavor('text/unicode'); 
	      var str = new Object(); 
	      var len = new Object(); 
	      var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString); 
	      var copytext=confurl; 
	      str.data=copytext; 
	      trans.setTransferData("text/unicode",str,copytext.length*2); 
	      var clipid=Components.interfaces.nsIClipboard; 
	      if (!clip) return false; 
	      clip.setData(trans,null,clipid.kGlobalClipboard); 
	      return true; 
	   } else{
		   alert(getMsg(ERR_WRONG_BROWSER));
	   }
	   return false; 
} 


$(function() {
			var siteurl = $('#siteurl').val();
			$.cookie('siteurl', siteurl, {path: '/'});
			var uid = $('#userid').val();
			var upassword = $('#upassword').val();
			var ualias = $('#ualias').val();
			var apiurl = $('#apiurl').val();
			var orgid = $('#orgid').val();
			var download_seegleOnline = $('#download_seegleOnline').val();
						
			$('.geturl').click(function(){
				var cid = $(this).parent('td').prev().prev().prev().prev().text();
				var confurl = siteurl+"/ConfLogin.jsp?orgid="+orgid+"&confid="+cid;
				//var confurl = siteurl+"/GuestConfLogin.jsp?orgid="+orgid+"&confid="+cid;  //游客url直接进入会议
				var div=document.getElementById("confurl1");
				div.innerHTML=confurl;
				$('#confurl').val(confurl);
				$('#dialog_box1').dialog({
					modal: true,
					position:"20%",
					width:550,
					height:120,
					title:"<span style='color:#FFFFFF;'>"+getMsg(CONF_URL)+"</span>"
					
				});
				$("#ui-dialog-title-dialog_box1").css({"height":"25px","float":"left","padding-top":"5px","padding-left":"5px"});
				$(".ui-dialog-titlebar-close").css("float","right");
				$(".ui-dialog-titlebar-close").css("width","30px");
				$(".ui-dialog-titlebar").css("background-image","");
				$(".ui-dialog-titlebar").css("background-color","#348CD4");
				$("#dialog_box").css("border","0");
				//关闭弹层
				$('#dialog_cancel1').click(function(){
					$.modal.close();
				});
			});
			$('.join').click(function(){
				var regpath = Seegle.client_registry;
				if(g_installFlag=='0'||g_installFlag=='1'){
					regpath = "HKEY_LOCAL_MACHINE\\SOFTWARE\\视高可视协同办公平台2010";
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
						Seegle.postsgsvr(9090, 1, regpath, "InstallPath");	
					}
				}
				var cid = $(this).parent().parent().children("td:first-child").text();
				$.getJSON(siteurl+"/conf/login", { cid: cid, _: new Date().getTime() }, function(data) {
					if(data.rid !="" && data.rid != null)
					{
						if(g_installFlag=='0'||g_installFlag=='1')
						{
							$.cookie('confip', data.ips, {path: '/'});
							$.cookie('confid', data.rid, {path: '/'});
							$.cookie('orgid', orgid, {path: '/'});
							$.cookie('userid', uid, {path: '/'});
							$.cookie('userpass', upassword, {path: '/'});
							$.cookie('nickname', ualias, {path: '/'});
							$.cookie('confpass', '', {path: '/'});
							$.cookie('download_seegleOnline', download_seegleOnline, {path: '/'});
							SuperAlert();
						}
						else
						{
							var base64unick = base64encode(utf16to8(ualias)); //昵称base64编码
							var runandroid = "mtcmtc://startConference.app?ConfID="+data.rid+"&ServerAddrAndPort="+data.ips+"&UserAccount="+orgid+uid+"&Nick="+ualias+"&LoginMode=LoginByUserID&Password="+upassword;
							var runipad = "SCConferenceiPad://?user="+orgid+uid+"&pass="+upassword+"&conference="+data.rid+"&server="+data.ips+"&nick="+base64unick+"&loginMode=LoginByUserID";
							var runiphone = "SCConferenceiPhone://?user="+orgid+uid+"&pass="+upassword+"&conference="+data.rid+"&server="+data.ips+"&nick="+base64unick+"&loginMode=LoginByUserID";
							if(browser.versions.iPad){
								//alert("iPad:"+runipad);
								location.href=runipad;
							}
							else if(browser.versions.iPhone){
								//alert("iPhone:"+runiphone);
								location.href=runiphone;
							}
							else if(browser.versions.android){
								//alert("Android:"+runandroid);
								location.href=runandroid;
							}
							else{
								Seegle.startconf(data.rid, data.ips, orgid, uid, upassword, ualias,"",'2',regpath,"InstallPath",Seegle.client_confname,"0","","","","");	
							}						
						}
					} 
					else 
					{
						if(errorMap.get(data.msg+"")==null){
							alert(getMsg(NO_DIFINE_ERROR_MSG)+"("+getMsg(ERROR_CODE)+":"+data.msg+")");
						}else{
							alert(errorMap.get(data.msg+"")+"("+getMsg(ERROR_CODE)+":"+data.msg+")");
						}
					}
				    return false;
				});
				return false;
			});
			$('.joinlive').click(function(){
				var cid = $(this).parent().parent().children("td:first-child").text();
				$.getJSON(siteurl+"/conf/login", { cid: cid, _: new Date().getTime() }, function(data) {
					if(data.rid !="" && data.rid != null)
					{
						var url = siteurl+"/page/live/index.html?cid="+data.rid+"&userid="+orgid+uid+"&nname="+ualias;
						window.open(url);
					} 
					else 
					{	
						if(errorMap.get(data.msg+"")==null){
							alert(getMsg(NO_DIFINE_ERROR_MSG)+"("+getMsg(ERROR_CODE)+":"+data.msg+")");
						}else{
							alert(errorMap.get(data.msg+"")+"("+getMsg(ERROR_CODE)+":"+data.msg+")");
						}
					}
					return false;
				});
				return false;
			});
			
			$('.delete').click(function(){
				var cid = $(this).parent('td').prev().prev().text();
				if(confirm(getMsg(CONFIRM_DELETE))){
					$.post(siteurl+"/CheckRoleServlet", { _: new Date().getTime() } ,function(rs) {
						if(rs==null||rs==""){
							alert(getMsg(ERR_CURRENT_USER_UNEXIST));
							location.href = siteurl+'/logout';
						}
						else if(rs==1 || rs==2){
							$.post(siteurl+"/conf/del", { cid: cid, _: new Date().getTime() } ,function(rs) {
								if(rs==0){
									alert(getMsg(DELETE_SUCESS));
									location.href=siteurl+'/TopConfPage.go?inc=TopConfManage';
								} else {
									alert(getMsg(DELETE_FAILURE));
									return false;
								}
							});	
						} 
						else {
							alert(getMsg(ERR_NO_PERMISSION));
							location.href = siteurl+'/logout';
							//location.href = siteurl+'/TopConfPage.go?inc=TopConfList';
							return false;
						}
					});
					
				}
				return false;
			});			
			
			
			getConfOnlineNum();
			function getConfOnlineNum() {
				$.getJSON(siteurl+"/conf/online",{ _: new Date().getTime() },  function(data){
		 			var cid = 0;
					var online = 0;
					if(data!=null){
						for (var i = 0; i < data.length; i++) {
							cid = data[i].cid;
							online = data[i].online;
							$('#'+cid+' .online').text(online);
						}
					}
				});					
			}
			
		 	$.doTimeout('loop', 10*1000, function(){
		 		getConfOnlineNum();
				return true;
			});		 	
		});
