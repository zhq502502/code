function openLive() {
	var live = document.getElementsByName("open_live");
    var openlive;
    for(var i = 0; i < live.length; i++)
    {
        if(live[i].checked){
        	openlive=live[i].value;
        }
    }
    if (openlive == "0"){
        document.forms[0].live_max_num.disabled=true;
        document.forms[0].live_conf_pass.disabled=true;
        document.forms[0].is_video_f.disabled=true;
        document.forms[0].is_text_conver.disabled=true;
        document.forms[0].live_max_num.style.backgroundColor='#dddddd';
        document.forms[0].live_conf_pass.style.backgroundColor='#dddddd';
        document.forms[0].is_video_f.style.backgroundColor='#dddddd';
        document.forms[0].is_text_conver.style.backgroundColor='#dddddd';
    }
    else{
        document.getElementById("live_max_num").disabled=false;
        document.getElementById("live_conf_pass").disabled=false;
        document.getElementById("is_video_f").disabled=false;
        document.getElementById("is_text_conver").disabled=false;
        document.forms[0].live_max_num.style.backgroundColor='#ffffff';
        document.forms[0].live_conf_pass.style.backgroundColor='#ffffff';
        document.forms[0].is_video_f.style.backgroundColor='#ffffff';
        document.forms[0].is_text_conver.style.backgroundColor='#ffffff';
    }
}

function autoRecord() {
	var record = document.getElementsByName("auto_record");
    var autorecord;
    for(var i = 0; i < record.length; i++)
    {
        if(record[i].checked){
        	autorecord=record[i].value;
        }
    }
    if (autorecord == "0"){
        document.forms[0].sgRecordVideo.disabled=true;
        document.forms[0].sgRecordWbd.disabled=true;
        document.forms[0].sgRecordAppShare.disabled=true;
        document.forms[0].sgRecordMedia.disabled=true;
        document.forms[0].sgRecordText.disabled=true;
        document.forms[0].sgRecordVideo.style.backgroundColor='#dddddd';
        document.forms[0].sgRecordWbd.style.backgroundColor='#dddddd';
        document.forms[0].sgRecordAppShare.style.backgroundColor='#dddddd';
        document.forms[0].sgRecordMedia.style.backgroundColor='#dddddd';
        document.forms[0].sgRecordText.style.backgroundColor='#dddddd';
    }
    else{
        document.getElementById("sgRecordVideo").disabled=false;
        document.getElementById("sgRecordWbd").disabled=false;
        document.getElementById("sgRecordAppShare").disabled=false;
        document.getElementById("sgRecordMedia").disabled=false;
        document.getElementById("sgRecordText").disabled=false;
        document.forms[0].sgRecordVideo.style.backgroundColor='#ffffff';
        document.forms[0].sgRecordWbd.style.backgroundColor='#ffffff';
        document.forms[0].sgRecordAppShare.style.backgroundColor='#ffffff';
        document.forms[0].sgRecordMedia.style.backgroundColor='#ffffff';
        document.forms[0].sgRecordText.style.backgroundColor='#ffffff';
    }
}

function getbrowse(){ //判断当前浏览器是何种浏览器  
    var str="";   
    Agent=navigator.userAgent;  
    if(Agent.indexOf("Opera") != -1) {   
        str='Opera';   
    }   
    else if(Agent.indexOf("MSIE") != -1) {   
        str='MSIE';  
    }   
    else if(Agent.indexOf("Firefox") != -1) {   
        str='Firefox';  
    }
    else if(Agent.indexOf("Netscape") != -1) {   
        str='Netscape';  
    }   
    else if(Agent.indexOf("Safari") != -1) {   
        str='Safari';  
    }
    else{     
    }     
    return str;  
}

function createCaptcha() {
	var BrowseType="";  
	if(getbrowse()=="MSIE"){  
	    BrowseType="block";  
	}  
	else{  
	    BrowseType="table-row"; 
	}
	var flag = document.getElementsByName("captcha_flag");
    var create_flag;
    for(var i = 0; i < flag.length; i++)
    {
        if(flag[i].checked){
        	create_flag=flag[i].value;
        }
    }
    /*if (create_flag == "0"){
    	document.getElementById("captcha2").style.display='none';
    }
    else{
        document.getElementById("captcha2").style.display=BrowseType;
    }*/
}