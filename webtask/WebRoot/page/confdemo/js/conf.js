var g_installFlag = '0';
var WmvPlayer_installFlag = '0';
var RmvbPlayer_installFlag = '0';
(function() {
window.Seegle = window.Seegle || {};
Seegle = {
	client_registry:client_registry,
	client_confname: client_confname,
	//client_registry: "HKEY_LOCAL_MACHINE\\SOFTWARE\\Seegle Team Office Platform",
	//client_confname: "sgconf.exe",
	NOWTIME: Math.floor(new Date().getTime() / 1000),
	log: function(i){
		if(window.console){
			console.log(i);
		} else {
			//alert(i);
		}
		return;
	},

	
	startconf: function(i, j, k, q, w, n, updateserverset, logintype, regpath, regkey, exename,ptype,paddr,pport,puser,ppass)
	{
	    var installFlag = g_installFlag;                                                   
		var confsrvip = j;
		var confsrvloadbalance = '1';
		var orgid = k;
		var uid = k+q;
		var upass = w.replace("#","%23");//URL地址#后面的会被截去，手动将其转译
		var unickname = encodeURIComponent(n); 
		//var unickname = n; 
		var proxytype = ptype;
		var proxyaddr = paddr;
		var proxyport = pport;
		var proxyuser = puser;
		var proxypass = ppass;
		var confid = i;
		var updateserverinfo='';
			
		var confpara = "";		
		var url = "http://127.0.0.1:";
		url = url + installFlag;
		url = url + "/?type=2&regpath=";
		url = url + regpath;
		url = url + "&regkey=";
		url = url + regkey;
		url = url + "&exename=";
		url = url + exename;
		url = url + "&param=";
	    confpara = "-U " + updateserverinfo + " -T " + proxytype + " -S " + proxyaddr + " -R " + proxyport + " -N \"$" + proxyuser + "\" -Y \"$" + proxypass + "\" -H " + confsrvip + " -u " + uid + " -p \"$" + upass + "\" -n \"$" + unickname + "\" -c "+ confid + " -k \"$ \" -t 0 -b "+confsrvloadbalance+" "+Math.random()+" ?/";
	    url = url + confpara;
		this.log(url);
		var objLoginExe = new Image();
		//alert(url);
		objLoginExe.src = url;
		objLoginExe.onreadystatechange = function()
		{
			if (this.readyState == 'complete')
			{
				objLoginExe.onload  = null;
				objLoginExe.onerror = null;
				objLoginExe = null;
			}
		};
		objLoginExe.onload = function()
		{
			objLoginExe.onreadystatechange = null;
			objLoginExe.onerror = null;
			objLoginExe = null;
		};
		objLoginExe.onerror = function()
		{
			objLoginExe.onload  = null;
			objLoginExe.onreadystatechange = null;
			objLoginExe = null;
		};
		return true;
	},

	//函数说明：调用客户端播放录像
	//参数说明：
	//fileurl:       文件地址
	//ftype:         文件类型
	//exename:       客户端名
	//regpath:       注册表路径
	//regkey:        注册表键名
	startplay: function(fileurl,regpath, regkey, exename)
	{
		//alert("fileurl");
		var furl=encodeURI(fileurl);
		var installFlag = g_installFlag;
		var confpara = "";
		var url = "http://127.0.0.1:";
		var _u = " ";
		var fileuu = $.trim(fileurl);
		var fileArr = fileuu.split(".");
		if(fileArr[fileArr.length-1]=="smvx"){
			_u = " -u ";
		}
		url = url + installFlag;
		url = url + "/?type=2&regpath=";
		url = url + regpath;
		url = url + "&regkey=";
		url = url + regkey;
		url = url + "&exename=";
		url = url + exename;
		url = url + "&param=";
	    confpara = _u + furl;
		url = url + confpara+"?/";
		this.log(url);
		
		var objLoginExe = new Image();
		objLoginExe.src = url;
		objLoginExe.onreadystatechange = function()
		{
			if (this.readyState == 'complete')
			{
				objLoginExe.onload  = null;
				objLoginExe.onerror = null;
				objLoginExe = null;
			}
			window.location.reload();
		};
		objLoginExe.onload = function()
		{
			objLoginExe.onreadystatechange = null;
			objLoginExe.onerror = null;
			objLoginExe = null;
			window.location.reload();
		};
		objLoginExe.onerror = function()
		{
			objLoginExe.onload  = null;
			objLoginExe.onreadystatechange = null;
			objLoginExe = null;
			window.location.reload();
		};
		return true;
	},
	
	//函数说明：检测客户端是否安装
	//参数说明：
//	     port:          服务的监听端口
//	     regpath:       注册表路径
//	     regkey:        注册表键名 
    postCheckExe: function(port, regpath, regkey)
	{
		var obj = new Image();
		var url = "http://127.0.0.1:";
		url = url + port;
		url = url + "/?type=1&regpath=";
		url = url + regpath;
		url = url + "&regkey=";
		url = url + regkey;
		url = url + "?/";
	    obj.src = url;
	    obj.onreadystatechange = function()
	    {
	    	if (this.readyState == 'complete')
	    	{
	    		obj.onload  = null;
	    		obj.onerror = null;
	    		g_installFlag = port;           // 客户端已经安装
	    		obj = null;
	    	}
	    	else
	    	{
	    		g_installFlag = '1';            // 客户端未安装
	    	}
	    }
	    
	    obj.onload = function()
	    {
	    	obj.onreadystatechange = null;
	    	obj.onerror = null;
	    	g_installFlag = port;              // 客户端已经安装
	    	obj = null;
	    }
	    
	    obj.onerror = function()
	    {
	    	obj.onload = null;
	    	obj.onreadystatechange = null;
	    	g_installFlag = '1';               // 客户端未安装
	    	obj = null;
	    }
	},
	//函数说明：检测客户端是否安装
	//参数说明：
//	     port:          服务的监听端口
//	     regpath:       注册表路径
//	     regkey:        注册表键名 
    postCheckWmvPlayer: function(port, regpath, regkey)
	{
		var obj = new Image();
		var url = "http://127.0.0.1:";
		url = url + port;
		url = url + "/?type=1&regpath=";
		url = url + regpath;
		url = url + "&regkey=";
		url = url + regkey;
		url = url + "?/";
	    obj.src = url;
	    obj.onreadystatechange = function()
	    {
	    	if (this.readyState == 'complete')
	    	{
	    		obj.onload  = null;
	    		obj.onerror = null;
	    		WmvPlayer_installFlag = port;           // 客户端已经安装
	    		obj = null;
	    	}
	    	else
	    	{
	    		WmvPlayer_installFlag = '1';            // 客户端未安装
	    	}
	    }
	    
	    obj.onload = function()
	    {
	    	obj.onreadystatechange = null;
	    	obj.onerror = null;
	    	WmvPlayer_installFlag = port;              // 客户端已经安装
	    	obj = null;
	    }
	    
	    obj.onerror = function()
	    {
	    	obj.onload = null;
	    	obj.onreadystatechange = null;
	    	WmvPlayer_installFlag = '1';               // 客户端未安装
	    	obj = null;
	    }
	},
	//函数说明：检测客户端是否安装
	//参数说明：
//	     port:          服务的监听端口
//	     regpath:       注册表路径
//	     regkey:        注册表键名 
    postCheckRmvbPlayer: function(port, regpath, regkey)
	{
		var obj = new Image();
		var url = "http://127.0.0.1:";
		url = url + port;
		url = url + "/?type=1&regpath=";
		url = url + regpath;
		url = url + "&regkey=";
		url = url + regkey;
		url = url + "?/";
	    obj.src = url;
	    obj.onreadystatechange = function()
	    {
	    	if (this.readyState == 'complete')
	    	{
	    		obj.onload  = null;
	    		obj.onerror = null;
	    		RmvbPlayer_installFlag = port;           // 客户端已经安装
	    		obj = null;
	    	}
	    	else
	    	{
	    		RmvbPlayer_installFlag = '1';            // 客户端未安装
	    	}
	    }
	    
	    obj.onload = function()
	    {
	    	obj.onreadystatechange = null;
	    	obj.onerror = null;
	    	RmvbPlayer_installFlag = port;              // 客户端已经安装
	    	obj = null;
	    }
	    
	    obj.onerror = function()
	    {
	    	obj.onload = null;
	    	obj.onreadystatechange = null;
	    	RmvbPlayer_installFlag = '1';               // 客户端未安装
	    	obj = null;
	    }
	},

	//函数说明：检测服务和客户端
	//参数说明：
//	       port:          待检测的服务端口
//	       nCycleCount:   记录当前的检测次数
//	       regpath:       注册表路径
//	       regkey:        注册表键名 
    postsgsvr: function(port, nCycleCount, regpath, regkey)
	{
    	//alert("port"+port);
		var obj = new Image();
		var url = 'http://127.0.0.1:';
		url = url + port;
		url = url + '/?type=0?/';
	    obj.src = url;
	    obj.onreadystatechange = function()
	    {
	    	if (this.readyState == 'complete')
	    	{
	    		obj.onload  = null;
	    		obj.onerror = null;
	    		g_installFlag = '1';           // 服务启动正常
	    		obj = null;
	    		Seegle.postCheckExe(port, regpath, regkey);
	    	}
	    	else
	    	{	
	    		g_installFlag = '0';           // 服务启动异常
	    	}
	    }
	    
	    obj.onload = function()
	    {
	    	obj.onreadystatechange = null;
	    	obj.onerror = null;
	    	g_installFlag = '1';               // 服务启动正常
	    	obj = null;
	    	Seegle.postCheckExe(port, regpath, regkey);
	    }
	    
	    obj.onerror = function()
	    {
	    	obj.onload = null;
	    	obj.onreadystatechange = null;
	    	g_installFlag = '0';               // 服务启动异常
	    	obj = null;
	    	
	    	if (nCycleCount < 30)
	    	{
	    		Seegle.postsgsvr(port+1, nCycleCount+1,  regpath, regkey);
	    	}
	    }
	}			
};

var BrowserDetect = {
	init: function () {
		this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
		this.version = this.searchVersion(navigator.userAgent)
			|| this.searchVersion(navigator.appVersion)
			|| "an unknown version";
		this.OS = this.searchString(this.dataOS) || "an unknown OS";
	},
	searchString: function (data) {
		for (var i=0;i<data.length;i++)	{
			var dataString = data[i].string;
			var dataProp = data[i].prop;
			this.versionSearchString = data[i].versionSearch || data[i].identity;
			if (dataString) {
				if (dataString.indexOf(data[i].subString) != -1)
					return data[i].identity;
			}
			else if (dataProp)
				return data[i].identity;
		}
	},
	searchVersion: function (dataString) {
		var index = dataString.indexOf(this.versionSearchString);
		if (index == -1) return;
		return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
	},
	dataBrowser: [
		{
			string: navigator.userAgent,
			subString: "Chrome",
			identity: "Chrome"
		},
		{ 	string: navigator.userAgent,
			subString: "OmniWeb",
			versionSearch: "OmniWeb/",
			identity: "OmniWeb"
		},
		{
			string: navigator.vendor,
			subString: "Apple",
			identity: "Safari",
			versionSearch: "Version"
		},
		{
			prop: window.opera,
			identity: "Opera",
			versionSearch: "Version"
		},
		{
			string: navigator.vendor,
			subString: "iCab",
			identity: "iCab"
		},
		{
			string: navigator.vendor,
			subString: "KDE",
			identity: "Konqueror"
		},
		{
			string: navigator.userAgent,
			subString: "Firefox",
			identity: "Firefox"
		},
		{
			string: navigator.vendor,
			subString: "Camino",
			identity: "Camino"
		},
		{		// for newer Netscapes (6+)
			string: navigator.userAgent,
			subString: "Netscape",
			identity: "Netscape"
		},
		{
			string: navigator.userAgent,
			subString: "MSIE",
			identity: "MSIE",
			versionSearch: "MSIE"
		},
		{
			string: navigator.userAgent,
			subString: "Gecko",
			identity: "Mozilla",
			versionSearch: "rv"
		},
		{ 		// for older Netscapes (4-)
			string: navigator.userAgent,
			subString: "Mozilla",
			identity: "Netscape",
			versionSearch: "Mozilla"
		}
	],
	dataOS : [
		{
			string: navigator.platform,
			subString: "Win",
			identity: "Windows"
		},
		{
			string: navigator.platform,
			subString: "Mac",
			identity: "Mac"
		},
		{
			   string: navigator.userAgent,
			   subString: "iPhone",
			   identity: "iPhone/iPod"
	    },
		{
			string: navigator.platform,
			subString: "Linux",
			identity: "Linux"
		}
	]

};
BrowserDetect.init();	
}());

//移动客户端浏览器版本判断
var browser={
		versions:function(){
				var u = navigator.userAgent;
				return {         //移动终端浏览器版本信息
					trident: u.indexOf('Trident') > -1, //IE内核
					presto: u.indexOf('Presto') > -1, //opera内核
					webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
					gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
					mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
					ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
					android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
					iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
					iPad: u.indexOf('iPad') > -1, //是否iPad
					webApp: u.indexOf('Safari') == -1, //是否web应用程序，没有头部与底部
					windows:u.indexOf("Window")> -0 //windows
				};
			 }(),
			 language:(navigator.browserLanguage || navigator.language).toLowerCase()
		}