/* 
 * 定义参数名称为数组的下标，实现多语言实时切换
 * 
 */
var StringRes_en = new Array();  //英文数组
var StringRes_zh_cn = new Array();  //简体中文数组
var StringRes_zh_tw = new Array();  //繁体中文数组

var TITLE = 0;  //云会议
var INPURTCODE = 1;  //请输入验证码
var CODENUMBER = 2;  //验证码必须为数字
var TOURISTS = 3;  //游客
var ERRORCODE = 4;  //登錄錯誤，錯誤代碼
var CONFNOFIND = 5;  //未能找到对应会议室，请输入有效验证码！
var GETCONFERROR = 6;  //获取会议登录参数出错,错误代码  
var INPUTCONFCODE = 7;  //请输入会议验证码 
var USERNAME = 8;  //用户显示名称 
var JOINCONF = 9;  //参加会议
var UNINSTALL = 10;  //检查到客户端未安装，安装完后请重新打开
var ONLINEINSTALL = 11;  //在线安装
var DOWNLOAD = 12;  //下载安装包
var CLOSEWINDOW = 13;  //关闭窗口
var COPYRIGHT = 14;//"会议网 版权所有 2003-2014 www.huiyiwang.net";//下方版权信息
var SELECTLANGUAGE = 15;//选择语言

StringRes_en[TITLE] = 'Cloud Conference';
StringRes_zh_cn[TITLE] = '云会议';
StringRes_zh_tw[TITLE] = '云會議';

StringRes_en[INPURTCODE] = 'Please enter the verification code';
StringRes_zh_cn[INPURTCODE] = '请输入验证码';
StringRes_zh_tw[INPURTCODE] = '請輸入驗證碼';

StringRes_en[CODENUMBER] = 'Verification code must be numeric';
StringRes_zh_cn[CODENUMBER] = '验证码必须为数字';
StringRes_zh_tw[CODENUMBER] = '驗證碼必須為數字';

StringRes_en[TOURISTS] = 'tourists';
StringRes_zh_cn[TOURISTS] = '游客';
StringRes_zh_tw[TOURISTS] = '遊客';

StringRes_en[ERRORCODE] = 'Login Error，Error code ';
StringRes_zh_cn[ERRORCODE] = '登录错误，错误代码';
StringRes_zh_tw[ERRORCODE] = '登錄錯誤，錯誤代碼';

StringRes_en[CONFNOFIND] = 'Failed to find the corresponding conference room, please enter a valid verification code！';
StringRes_zh_cn[CONFNOFIND] = '未能找到对应会议室，请输入有效验证码！';
StringRes_zh_tw[CONFNOFIND] = '未能找到對應會議室，請輸入有效驗證碼！';

StringRes_en[GETCONFERROR] = 'For meeting the login parameters error,error code ';
StringRes_zh_cn[GETCONFERROR] = '获取会议登录参数出错,错误代码 ';
StringRes_zh_tw[GETCONFERROR] = '獲取會議登陸參數出錯，錯誤代碼 ';

StringRes_en[INPUTCONFCODE] = 'Meeting verification code ';
StringRes_zh_cn[INPUTCONFCODE] = '请输入会议验证码';
StringRes_zh_tw[INPUTCONFCODE] = '請輸入回憶驗證碼 ';

StringRes_en[USERNAME] = 'User\'s display name ';
StringRes_zh_cn[USERNAME] = '用户显示名称 ';
StringRes_zh_tw[USERNAME] = '用戶顯示名稱 ';

StringRes_en[JOINCONF] = 'Join meeting ';
StringRes_zh_cn[JOINCONF] = '参加会议 ';
StringRes_zh_tw[JOINCONF] = '參加會議 ';

StringRes_en[UNINSTALL] = 'After the check to the client not installed, the installation please open again ';
StringRes_zh_cn[UNINSTALL] = '检查到客户端未安装，安装完后请重新打开 ';
StringRes_zh_tw[UNINSTALL] = '檢查到客戶端未安裝，安裝完成後請重新打開 ';

StringRes_en[ONLINEINSTALL] = 'Online installation ';
StringRes_zh_cn[ONLINEINSTALL] = '在线安装 ';
StringRes_zh_tw[ONLINEINSTALL] = '在線安裝';

StringRes_en[DOWNLOAD] = 'Download the installation package';
StringRes_zh_cn[DOWNLOAD] = '下载安装包 ';
StringRes_zh_tw[DOWNLOAD] = '下載安裝包 ';

StringRes_en[CLOSEWINDOW] = 'Close window ';
StringRes_zh_cn[CLOSEWINDOW] = '关闭窗口';
StringRes_zh_tw[CLOSEWINDOW] = '關閉窗口 ';

StringRes_en[COPYRIGHT] = 'copyright 2003-2014 www.huiyiwang.net';
StringRes_zh_cn[COPYRIGHT] = '会议网 版权所有 2003-2014 www.huiyiwang.net';
StringRes_zh_tw[COPYRIGHT] = '會議網版權所有 2003-2014 www.huiyiwang.net';

StringRes_en[SELECTLANGUAGE] = 'language';
StringRes_zh_cn[SELECTLANGUAGE] = '选择语言';
StringRes_zh_tw[SELECTLANGUAGE] = '選擇語言';



//根据当前语种选择相应文字
function getMsg(message){
    var language = $.cookie("sgjl");  //从cookie中获得当前语种
    if (language == "zh_tw"){  // 繁体中文
        return StringRes_zh_tw[message];
    }else if (language == "en"){  // English
        return StringRes_en[message];
    }else{  // 简体中文
        return StringRes_zh_cn[message];
    }
}
$(function(){
	//$.cookie("sgjl","en",{expires: 1000});
	changeshow();
})
function showSelect(){
	$("#sub_list").toggle();
}
function changeLanguageLogin(lan){
	$.cookie("sgjl",lan,{expires: 1000});
	changeshow();
}
function changeshow(){
	$(".language").each(function(){
		$(this).html(getMsg($(this).attr("code")));
	})
}






