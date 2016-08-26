var client_registry = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Seegle Team Office Platform";//视频会议客户端注册表地址
//var client_registry = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Video conference";//视频会议客户端注册表地址
var client_confname = "sgconf.exe";//视频会议客户端名称
var client_sgplayname = "sgplayerex.exe";//播放器地址
var md5pass="d41d8cd98f00b204e9800998ecf8427e";//默认空串加密MD5
var pass = "";//默认密码空
var orgid = "800261";//默认企业id
//var apiurl = "http://localhost:8080/opm4j";//云会议接口地址
var apiurl = "http://116.255.142.34:81/opm4j";//云会议接口地址
var telurl = "http://meetingtel.sct.yuantel.net/SeegleService.svc/HostVerify?jsoncallback=?";//远特接口
var mute = 0;//是否静音。0静音，1不静音
var download_URL = "http://www.seegletop.com/seegletop.exe";
