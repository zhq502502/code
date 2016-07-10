(function(){
    var isFunction = function( fn ) {
        return !!fn && typeof fn != "string" && !fn.nodeName &&
            fn.constructor != Array && /function/i.test( fn + "" );
    };
    var buildURL = function(url, params){
        var tmp = url.split("?");
        var uri = tmp[0];
        var ps = null;
        if (tmp.length > 1) ps = tmp[1].split("&");
        var pnames = uri.match(/{\w+}/g);
        if (pnames != null) {
            for (var i=0; i<pnames.length; ++i){
                var pn = pnames[i];
                var ppn = pnames[i].match(/{(\w+)}/)[1];
                if (!params[ppn]) return null;
                else uri = uri.replace(pn, params[ppn]);
            }
        }
        if (!ps) return uri;
        var re_ps = [];
        for (var i=0; i<ps.length; ++i) {
            var tmp = ps[i].match(/{(\w+)}/);
            if (tmp==null) re_ps.push(ps[i]);
            else {
                var pn = tmp[0];
                var ppn = tmp[1];
                if (params[ppn]) re_ps.push(encodeURI(ps[i].replace(pn, params[ppn])));
            }
        }
        if (re_ps.length>0) return [uri, re_ps.join("&")].join("?");
        else return uri;
    };
    var jsc = (new Date).getTime();
    var buildTempFunction = function(cb){
        var jsonp = "jsonp" + jsc++;
        window[ jsonp ] = function(data){
            cb(data);
            // Garbage collect
            window[ jsonp ] = undefined;
            try{ delete window[ jsonp ]; } catch(e){}
        };
        return jsonp;
    };
    var sendScriptRequest = function(url){
        var head = document.getElementsByTagName("head")[0];
        var script = document.createElement("script");
        script.src = url;
        script.charset = 'utf-8';
        head.appendChild(script);
    };
    var formatParams = function(params) {
        if (isFunction(params.callback)) params.callback = buildTempFunction(params.callback);
        if (!params.accessKey) params.accessKey = api_obj.accessKey;
        return params;
    };
    var send = function(url, params){
        var url = buildURL(url, params);
        if (url!=null) sendScriptRequest(url);
    };

    var accessKey = '';
    var _t = new Date().getTime();
    var namespace = 'Seegle';
    var obj = {
    	accessKey:accessKey,
        t: _t
    };
    //var baseUri = 'http://open.seegle.com/';
    var baseUri = 'http://127.0.0.1:8080/opm4j/';
    //var baseUri = 'http://www.seegle.cn/';
    var pp = 'start-index={startindex}&max-results={maxresults}';
    var sp = 'q={keyword}&'+pp;
    var pubp = 'published-min={publishedmin}&published-max={publishedmax}';
    var updp = 'updated-min={updatedmin}&updated-max={updatedmax}';
    var pup = pubp+'&'+updp;
    var ratp = 'rating-min={ratingmin}&rating-max={ratingmax}';
    var cp = 'accessKey={accessKey}&alt=xd&callback={callback}';
    var apis = {
    	getAccessToken: {url:baseUri+'apis/token?orgid={orgid}&u={u}&p={p}'},
        getConfList: {url:baseUri+'apis/conf/list?orgid={orgid}'},
        getConfLogin: {url:baseUri+'apis/conf/login?cid={cid}'},
        getGroupType: {url:baseUri+'apis/conf/group'},
        getConfOnline: {url:baseUri+'apis/conf/online'},
        getConfDept: {url:baseUri+'apis/conf/dept?orgid={orgid}'},
       	updateConf: {url:baseUri+'apis/conf/edit?cid={cid}&type={type}&orgid={orgid}&confname={confname}&begintime={begintime}&endtime={endtime}&grouptype={grouptype}&max_conf_user={max_conf_user}&max_conf_tourist={max_conf_tourist}&max_conf_spokesman={max_conf_spokesman}&conf_password_md5={conf_password_md5}&manage_password_md5={manage_password_md5}'},
        createConf: {url:baseUri+'apis/conf/add?orgid={orgid}&confname={confname}&begintime={begintime}&endtime={endtime}&grouptype={grouptype}&max_conf_user={max_conf_user}&max_conf_tourist={max_conf_tourist}&max_conf_spokesman={max_conf_spokesman}&conf_password_md5={conf_password_md5}&manage_password_md5={manage_password_md5}'},
        removeConf: {url:baseUri+'apis/conf/del?cid={cid}&orgid={orgid}'}
    };
    for (var name in apis){
        if (apis[name].url.search(/\?/)!=-1) { 
        	apis[name].url = apis[name].url + '&' + cp; 
        } else {
        	apis[name].url = apis[name].url + '?' + cp; 
        }
    }
    if (!window[namespace]) window[namespace] = {};
    var api_obj = window[namespace]; //a Temp Reference
    for (var name in obj) api_obj[name] = obj[name];
    for (var name in apis) {
        api_obj[name] = (function(url){
            return function(params){
                send(url, formatParams(params));
            };
        })(apis[name].url);
    }  

    
Seegle.startconf = function(ip, id, u, p, o, a){
		var browser = BrowserDetect.browser.toLowerCase();
		var client_registry = "SOFTWARE\\Seegle Team Office Platform";
		var client_confname = "sgconf.exe";
		
		if (browser=="msie"){
			ConfDirPath = testocx.GetRegKeyStringValue("HKEY_LOCAL_MACHINE",client_registry,"InstallPath");
			if(ConfDirPath == ""){
				ConfDirPath = testocx.GetRegKeyStringValue("HKEY_LOCAL_MACHINE", "SOFTWARE\\视高可视协同办公平台2010", "InstallPath");
			}
			ConfDir = ConfDirPath + '\\' + client_confname;
			var confsrvip = ip;
			var confsrvloadbalance = '1';
			var uid = o.toString()+u.toString();
			var upass = p;
			var unickname = a; 
			var proxytype = '0';
			var proxyaddr = '';
			var proxyport = '';
			var proxyuser = '';
			var proxypass = '';
			var confid = id;
			var updateserverinfo='';
			if(ConfDirPath == "")
			{
				if(confirm('检测没有安装客户端软件,现在安装吗?'))
				{
					location.href='http://www.seegletop.com/software/Seegletop-online-latest.exe';
					return false;
				}
			} else {
				confpara = " -U " + updateserverinfo + " -T " + proxytype + " -S " + proxyaddr + " -R " + proxyport + " -N $'" + proxyuser + "' -Y $'" + proxypass + "' -H " + confsrvip + " -u " + uid + " -p $'" + upass + "' -n $'" + unickname + "' -c "+ confid + " -k $'' -t 0 -b "+confsrvloadbalance+"";
				if(window.console){console.log(ConfDir+confpara);}
				testocx.RunExe(ConfDir,confpara,ConfDirPath,1);
				return;
			}
		} 
		else if(browser=="firefox") {
			netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
			try {
				var wrk = Components.classes["@mozilla.org/windows-registry-key;1"].createInstance(Components.interfaces.nsIWindowsRegKey);
				wrk.open(wrk.ROOT_KEY_LOCAL_MACHINE, client_registry, wrk.ACCESS_READ);
				var installpath = wrk.readStringValue("InstallPath");
				var programfile = installpath + '\\' + client_confname;
				wrk.close();
				
			} catch (err) {
				if(confirm('检测没有安装客户端软件,现在安装吗?'))
				{
					location.href='http://www.seegletop.com/software/Seegletop-online-latest.exe';
					return false;
				}
				//return false; 
			}
			try {
				var FileFactory = new Components.Constructor("@mozilla.org/file/local;1","nsILocalFile","initWithPath");
				var str_LocalProgram = programfile;
				var obj_Program = new FileFactory(str_LocalProgram); 
				var process = Components.classes["@mozilla.org/process/util;1"].createInstance(Components.interfaces.nsIProcess);
				process.init(obj_Program);
				var confsrvip = ip;
				var confsrvloadbalance = '0';
				var uid = o.toString()+u.toString();
				var upass = p;
				var unickname = a; 
				var proxytype = '0';
				var proxyaddr = '';
				var proxyport = '';
				var proxyuser = '';
				var proxypass = '';
				var confid = id;
				var updateserverinfo='';	
				var args = ["-U",updateserverinfo,"-T",proxytype,"-S",proxyaddr,"-R",proxyport,"-N","'$"+proxyuser+"'","-Y","'$"+proxypass+"'","-H", confsrvip,"-u",uid,"-p","'$"+upass+"'","-n","'$"+unickname+"'","-c",confid,"-k","'$'","-t","0","b",confsrvloadbalance];
				if(window.console){console.log(programfile+args);}
				process.runw(false, args, args.length);
					
			} catch (err) {
				//console.log(err); 
				return; 
			};			
		} else {
			alert("不支持该游览器!");
		}
	};    
    
var JSON;
if(!JSON) { JSON = {}; }
(function () {
    'use strict';

    function f(n) {
        return n < 10 ? '0' + n : n;
    }

    if (typeof Date.prototype.toJSON !== 'function') {

        Date.prototype.toJSON = function (key) {

            return isFinite(this.valueOf())
                ? this.getUTCFullYear()     + '-' +
                    f(this.getUTCMonth() + 1) + '-' +
                    f(this.getUTCDate())      + 'T' +
                    f(this.getUTCHours())     + ':' +
                    f(this.getUTCMinutes())   + ':' +
                    f(this.getUTCSeconds())   + 'Z'
                : null;
        };

        String.prototype.toJSON      =
            Number.prototype.toJSON  =
            Boolean.prototype.toJSON = function (key) {
                return this.valueOf();
            };
    }

    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        gap,
        indent,
        meta = {    // table of character substitutions
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        },
        rep;


    function quote(string) {
        escapable.lastIndex = 0;
        return escapable.test(string) ? '"' + string.replace(escapable, function (a) {
            var c = meta[a];
            return typeof c === 'string'
                ? c
                : '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
        }) + '"' : '"' + string + '"';
    }


    function str(key, holder) {
        var i,          // The loop counter.
            k,          // The member key.
            v,          // The member value.
            length,
            mind = gap,
            partial,
            value = holder[key];
        if (value && typeof value === 'object' &&
                typeof value.toJSON === 'function') {
            value = value.toJSON(key);
        }
        if (typeof rep === 'function') {
            value = rep.call(holder, key, value);
        }
        switch (typeof value) {
        case 'string':
            return quote(value);

        case 'number':
            return isFinite(value) ? String(value) : 'null';

        case 'boolean':
        case 'null':
			return String(value);
        case 'object':
            if (!value) {
                return 'null';
            }
            gap += indent;
            partial = [];
            if (Object.prototype.toString.apply(value) === '[object Array]') {
                length = value.length;
                for (i = 0; i < length; i += 1) {
                    partial[i] = str(i, value) || 'null';
                }
                v = partial.length === 0
                    ? '[]'
                    : gap
                    ? '[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']'
                    : '[' + partial.join(',') + ']';
                gap = mind;
                return v;
            }
            if (rep && typeof rep === 'object') {
                length = rep.length;
                for (i = 0; i < length; i += 1) {
                    if (typeof rep[i] === 'string') {
                        k = rep[i];
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            } else {
                for (k in value) {
                    if (Object.prototype.hasOwnProperty.call(value, k)) {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            }
            v = partial.length === 0
                ? '{}'
                : gap
                ? '{\n' + gap + partial.join(',\n' + gap) + '\n' + mind + '}'
                : '{' + partial.join(',') + '}';
            gap = mind;
            return v;
        }
    }
    if (typeof JSON.stringify !== 'function') {
        JSON.stringify = function (value, replacer, space) {
            var i;
            gap = '';
            indent = '';
            if (typeof space === 'number') {
                for (i = 0; i < space; i += 1) {
                    indent += ' ';
                }
            } else if (typeof space === 'string') {
                indent = space;
            }
            rep = replacer;
            if (replacer && typeof replacer !== 'function' &&
                    (typeof replacer !== 'object' ||
                    typeof replacer.length !== 'number')) {
                throw new Error('JSON.stringify');
            }
            return str('', {'': value});
        };
    }
    if (typeof JSON.parse !== 'function') {
        JSON.parse = function (text, reviver) {
            var j;

            function walk(holder, key) {
                var k, v, value = holder[key];
                if (value && typeof value === 'object') {
                    for (k in value) {
                        if (Object.prototype.hasOwnProperty.call(value, k)) {
                            v = walk(value, k);
                            if (v !== undefined) {
                                value[k] = v;
                            } else {
                                delete value[k];
                            }
                        }
                    }
                }
                return reviver.call(holder, key, value);
            }
            text = String(text);
            cx.lastIndex = 0;
            if (cx.test(text)) {
                text = text.replace(cx, function (a) {
                    return '\\u' +
                        ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                });
            }
            if (/^[\],:{}\s]*$/
                    .test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@')
                        .replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']')
                        .replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
                j = eval('(' + text + ')');
                return typeof reviver === 'function'
                    ? walk({'': j}, '')
                    : j;
            }
            throw new SyntaxError('JSON.parse');
        };
    }
}());
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