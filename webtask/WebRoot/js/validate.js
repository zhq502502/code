/* 
 * 一下为该字符串出现的位置，最后一个字符串也需要带上','。
 * must:0
 * email:5
 * int:11
 * len:15
 * float:19
 * http:25
 * self:30
 * same:35
 * 验证说明：
 * len_1|5输入长度为1-5。
 * len_5输入长度最大为5
 * int_1|5输入大小为1<=n<=5
 * int_5输入大小为n<=5
 * float_1|5_2输入大小为1<=n<=5。且为2位小数的浮点数
 * float_5_2输入大小为n<=5。且为2位小数的浮点数
 * float_5输入大小为n<=5。且为浮点数
 * 
 *///所有验证标识
var validate_string="must,email,int,len,float,http,self,same,";
//验证失败时提示的错误图片
var validate_error_img="<div id='@id' style='overfloat:hidden;position:absolute;z-index:9;display:none;'><img style='float:left' src='images/101.png'/><font style='float:left'>@msg</font><div>";
//验证成功时提示的图片
var validate_ok="<div id='@id' style='overfloat:hidden;position:absolute;z-index:9;display:none;'><img style='float:left' src='images/102.png'/><div>";
//验证失败时提示信息
var validate_error_msg="<div id='validate_error_msg' style='position:absolute;height:18px;font-size:12px;z-index:10;display:none;color:red;border:1px solid red;background-color:#ffffff;padding:0 3 0 3'><div>";
$(function(){
	//页面中加入错误提示DIV
	$("body").append(validate_error_msg);
	//输入框失去焦点进行验证
	$("input[validate],textarea[validate]").bind("blur",function(){
		validate_one(this);	
	});
	//移动到验证失败的输入框提示错误信息
	$("input[validate],textarea[validate]").hover(
		function(){
			var this_width=$(this).width();
			var this_height=$(this).height();
			var this_left=$(this).position().left;
			var this_top=$(this).position().top;
			$("#validate_error_msg").css({"top":this_top-24,"left":(this_left)});
			if(!($(this).attr("errormsg")==null||$(this).attr("errormsg")=="")){
				if($(this).attr("errormsg").substring($(this).attr("errormsg").length-1,$(this).attr("errormsg").length)==","){
					$("#validate_error_msg").html($(this).attr("errormsg").substring(0,$(this).attr("errormsg").length-1));
				}else{
					$("#validate_error_msg").html($(this).attr("errormsg"));
				}
				$("#validate_error_msg").show();
			}
		},
		function(){
			$("#validate_error_msg").hide();
		}
	);
})

/**
 * 验证单个输入框
 * @param item 需要验证的对象
 * @return 验证成功与否
 */
function validate_one(item){
	//获取验证类型
	var validates = $(item).attr("validate").split(",");
	//输入框的值
	var value = $(item).val();
	//错误信息
	var msg = "";
	//返回结果
	var flag = true;
	//是否有非空验证
	var mastVali = false;
	for(var i=0;i<validates.length;i++){
		switch (validate_string.indexOf(validates[i].split("_")[0])) {
		case -1://未找到
			change_error_msg(item,false);
			msg+="验证条件有误,";
			flag = false;
			break;
		case 0://必填
			if(value==null||value==""){
				msg+="必填项,";
				mastVali = true;
				flag = false;
				break;
			}
			break;
		case 5://email验证
			var emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
			if((!(value==null||value=="")&&!value.match(emailRegex))||mastVali){
				msg+="必须为正确email格式,";
				flag = false;
				break;
			}
			break;
		case 11://整型
			var intRegex = /^([1-9]|[1-9][0-9]*|-[1-9]|-[1-9][0-9]*|[0])$/;
			var vali = validates[i];
			var leng = vali.split("_")[1].split("|");
			if((!(value==null||value=="")&&!value.match(intRegex))||mastVali){
				msg+="必须为整数,";
				flag = false;
				break;
			}
			if(leng.length==1){
				if(!(value==null||value=="")&&parseInt(value)>parseInt(leng[0])){
					msg+="输入整数最大为:"+leng[0]+",";
					flag = false;
					break;
				}
			}else if(leng.length==2){
				if(parseInt(leng[0])<=parseInt(leng[1])){
					if(!(value==null||value=="")&&(parseInt(value)>parseInt(leng[1])||parseInt(value)<parseInt(leng[0]))){
						msg+="输入整数为:"+leng[0]+"至"+leng[1]+",";
						flag = false;
						break;
					}
				}else{
					if(!(value==null||value=="")&&(parseInt(value)<parseInt(leng[1])||parseInt(value)>parseInt(leng[0]))){
						msg+="输入整数为:"+leng[1]+"至"+leng[0]+",";
						flag = false;
						break;
					}
				}
			}else{
				msg+="验证条件有误,";
				flag = false;
				break;
			}
			break;
		case 15://输入长度验证
			var vali = validates[i];
			var leng = vali.split("_")[1].split("|");
			if(leng.length==1){
				if(!(value==null||value=="")&&value.length>parseInt(leng[0])){
					msg+="输入长度最大为"+leng[0]+",";
					flag = false;
					break;
				}
			}else if(leng.length==2){
				if(parseInt(leng[0])<=parseInt(leng[1])){
					if((!(value==null||value=="")&&(value.length>parseInt(leng[1])||value.length<parseInt(leng[0])))||mastVali){
						msg+="输入长度为"+leng[0]+"至"+leng[1]+",";
						flag = false;
						break;
					}
				}else{
					if((!(value==null||value=="")&&(value.length<parseInt(leng[1])||value.length>parseInt(leng[0])))||mastVali){
						msg+="输入长度为"+leng[1]+"至"+leng[0]+",";
						flag = false;
						break;
					}
				}
			}else{
				msg+="验证条件有误,";
				flag = false;
				break;
			}
			break;
		case 19://浮点数
			var vali = validates[i];
			var leng = vali.split("_")[1].split("|");
			var xsw = vali.split("_")[2];
			var floatRegex = "";
			if(xsw==null){
				xsw="*";
				floatRegex = "^(([1-9]|[1-9][0-9]*|-[1-9]|-[1-9][0-9]*|[0])|0.|-0.).{1}[0-9]"+xsw+"$";
			}else{
				xsw="{"+xsw+"}";
				floatRegex = "^(([1-9]|[1-9][0-9]*|-[1-9]|-[1-9][0-9]*|[0])|0|-0)[.]{1}[0-9]"+xsw+"$";
			}
			//alert(value.match(floatRegex));
			if(((!(value==null||value==""))&&(!value.match(floatRegex)))||mastVali||((value+"").substring(value.length-1,value.length)==".")){
				if(xsw=="*"){
					msg+="必须为浮点数,";
				}else{
					msg+="必须保留"+vali.split("_")[2]+"位小数,";
				}
				flag = false;
				break;
			}
			if(leng.length==1){
				if(!(value==null||value=="")&&parseFloat(value)>parseFloat(leng[0])){
					msg+="输入浮点数最大为:"+leng[0]+",";
					flag = false;
					break;
				}
			}else if(leng.length==2){
				if(parseFloat(leng[0])<=parseFloat(leng[1])){
					if(!(value==null||value=="")&&(parseFloat(value)>parseFloat(leng[1])||parseFloat(value)<parseFloat(leng[0]))){
						msg+="输入浮点数为:"+leng[0]+"至"+leng[1]+",";
						flag = false;
						break;
					}
				}else{
					if(!(value==null||value=="")&&(parseFloat(value)<parseFloat(leng[1])||parseFloat(value)>parseFloat(leng[0]))){
						msg+="输入浮点数为:"+leng[1]+"至"+leng[0]+",";
						flag = false;
						break;
					}
				}
			}else{
				msg+="验证条件有误,";
				flag = false;
				break;
			}
			break;
		case 25://http验证
			var httpRegex = /^(?:https|http):\/\/[\w-\.]+$/;
			if((!(value==null||value=="")&&!value.match(httpRegex))||mastVali){
				msg+="必须为正确http地址,";
				flag = false;
				break;
			}
			break;
		case 30://self自定义验证
			var httpRegex = $(item).attr("selfregex");;
			if((!(value==null||value=="")&&!value.match(httpRegex))||mastVali){
				msg+="验证失败";
				flag = false;
				break;
			}
			break;
		case 30://same自定义验证
			var vali = validates[i];
			var ietms = $("input[validate*="+vali+"]");
			if(items.lenght==2){
				if(ietms[0].val()!=items[1].val()){
					msg+="两次输入不一致";
					flag = false;
					break;
				}
			}
			break;
		}
		
		
	}	
	change_error_msg(item,flag,msg);
	return true;
}
/**
 * 更改被验证的输入框的提示信息
 * @param item 被验证的输入框
 * @param flag 验证是否成功
 * @param msg 提示信息
 * @return 无返回信息
 */
function change_error_msg(item,flag,msg){
	var this_width=$(item).width();
	var this_height=$(item).height();
	var this_left=$(item).position().left;
	var this_top=$(item).position().top;
	var id = parseInt(Math.random()*10000000);
	var error = "";
	if($(item).attr("selfmsg")==null||$(item).attr("selfmsg")==""||flag){//没有自定义提示
		$(item).attr("errorMsg",msg);
	}else{//有自定义提示
		$(item).attr("errorMsg",$(item).attr("selfmsg"));
	}
	if(flag){//验证通过
		error = validate_ok.replace("@id", id);
		$(item).removeClass("input-error");
	}else{//验证不通过
		error = validate_error_img.replace("@id", id).replace("@msg", "");
		$(item).addClass("input-error");
	}
	if(!($(item).attr("errid")==null||$(item).attr("errid")=="")){
		$("#"+$(item).attr("errid")).remove();
	}
	$(item).attr("errid",id);	
	$("body").append(error);
	$("#"+id).css({"top":this_top+this_height-16,"left":(this_width+this_left+8)});
	$("#"+id).show();
}

/**
 * 表单提交前的验证
 * @return
 */
function validate(){
	var isok = true;
	$("input[validate],textarea[validate]").each(function(i,n){
		if(!validate_one($("input,textarea['validate']")[i])){
			isok = false;
		}
	});
	return isok;
}