//变量声明 
//var client_registry = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Seegle Team Office Platform";//视频会议客户端注册表地址
var client_registry = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Video conference";//视频会议客户端注册表地址
var client_confname = "sgconf.exe";//视频会议客户端名称
var client_sgplayname = "sgplayerex.exe";//播放器地址
var md5pass_none="d41d8cd98f00b204e9800998ecf8427e";//默认空串加密MD5
var pass_none = "";//默认密码空
var orgid = "800300";//企业ID
var download_URL = "http://59.173.12.220:88/download/conf.exe";
var groupid=0;//集群ID
var apiurl = "http://59.173.12.220:88/opm4j";//api地址
//var apiurl = "http://www.seegle.cn";//api地址
var username = "";//用户帐号
var userpass = "";//用户密码未加密
var cname="";
var md5userpass = "";//用户密码MD5加密后的
var token = "";//访问令牌
var debug = 1;
var nname = "";//用户昵称
var vcode = "";//验证码
//代理设置
var proxytype = "";
var proxyip = "";
var proxyport = "";
var proxyuser = "";
var proxypass = "";
var confid = 0;
