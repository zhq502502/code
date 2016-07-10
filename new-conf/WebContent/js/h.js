(function() {
	window.Seegle = window.Seegle || {};
	var apisite = "http://127.0.0.1:8080/opm4j/";
	Seegle = {
		ERROR_CONF_LIST_FAIL : "获取会议列表失败",
		INFO_CONF_COUNT_FAIL: "查询会议记录失败",
		show: function (m, e, i) {
			//FlyJSONP.init({debug: true});
			switch(m){
				case "conf/list":
					FlyJSONP.get({
						url: apisite+'conf/list',
					    parameters: {
					        limit: 5
					    },
					    success: function (data) {
					    	var jd = JSON.stringify(data);
					    	console.log(jd);
					    	var html = "<table class='bordertable'><thead><tr><th>会议ID</th><th>会议名称</th><th>操作</th></tr></thead><tbody>";
					    	for (var i=0; i<data.length;++i) { 		
								html+="<tr id='"+data[i].cid+"'><td>"+data[i].cid+"</td><td>"+data[i].confname+"</td><td><a href='#' onclick='H.join(1)'>参加</a>&nbsp;<a href='#' onclick='H.edit("+data[i].cid+")'>编辑</a>&nbsp;<a href='#' onclick='H.del("+data[i].cid+")'>删除</a></td></tr>";	
							} 
		    				html += "</tbody></table>";
		    				html += "<OBJECT CODEBASE='"+apisite+"RunSgplayer.cab#version=1,0,0,5' CLASSID='CLSID:DC61AC79-C88C-42B3-87CC-41CC3AC92F4C' id='testocx' height='0' width='0'  hspace='0'>";
        					html += "<param name='_Version' value='65536' />";
					        html += "<param name='_ExtentX' value='19844' />";
					        html += "<param name='_ExtentY' value='9260' />";
					        html += "<param name='_StockProps' value='0' />";
							html += "</OBJECT>";
		    				document.getElementById(e).innerHTML=html;
					    },
					    error: function (errorMsg) {
					        document.getElementById(e).innerHTML=H.ERROR_CONF_LIST_FAIL;
					    }
					});	
				break;
				case "conf/add":
					H.includedp();
					var	html = "<form class='ym-form linearize-form ym-columnar' id='addconfform' action='#' method='post'>";
						html += "<div class='ym-fbox-text'>";
						html += "<label for='confname'>会议名称</label>";
						html += "<input type='text' size='20' id='confname' name='confname'>";
						html += "</div>";
						html += "<div class='ym-fbox-text'>";
						html += "<label for='confnum'>会议人数</label>";
						html += "<input type='text' size='20' id='confnum' name='confnum' value='100'>";
						html += "</div>";
						html += "<div class='ym-fbox-text'>";
						html += "<label for='confbtime'>会议开始时间</label>";
						html += "<input type='text' size='20' id='confbtime' name='confbtime' value='"+G.getNowTime()+"' class='Wdate' onclick='WdatePicker({dateFmt:&#39yyyy-MM-dd HH:mm:ss&#39,isShowClear:false,readOnly:true})'>";
						html += "</div>";
						html += "<div class='ym-fbox-text'>";
						html += "<label for='confetime'>会议结束时间</label>";
						html += "<input type='text' size='20' id='confetime' name='confetime' value='"+G.getNextMonth()+"' class='Wdate' onclick='WdatePicker({dateFmt:&#39yyyy-MM-dd HH:mm:ss&#39,isShowClear:false,readOnly:true})'>";
						html += "</div>";
						html += "<div class='ym-fbox-button'>";
						html += "<input type='submit' name='submit' id='submit' class='save' value='添加'>";
						html += "</div>";
						html += "</form>";
						document.getElementById(e).innerHTML=html;
						
				break;
				case "conf/edit":
					H.includedp();
					FlyJSONP.get({
						url: apisite+'conf/edit',
					    parameters: {
					        date: i
					    },
					    success: function (data) {
					    	var jd = JSON.stringify(data);
					    	console.log(jd);
					var	html = "<form class='ym-form linearize-form ym-columnar' id='addconfform' action='#' method='post'>";
						html += "<div class='ym-fbox-text'>";
						html += "<label for='confname'>会议名称</label>";
						html += "<input type='text' size='20' id='confname' name='confname' value='"+data.name+"'>";
						html += "</div>";
						html += "<div class='ym-fbox-text'>";
						html += "<label for='confnum'>会议人数</label>";
						html += "<input type='text' size='20' id='confnum' name='confnum' value='"+data.num+"'>";
						html += "</div>";
						html += "<div class='ym-fbox-text'>";
						html += "<label for='confbtime'>会议开始时间</label>";
						html += "<input type='text' size='20' id='confbtime' name='confbtime' value='"+data.btime+"' class='Wdate' onFocus='WdatePicker({dateFmt:&#39yyyy-MM-dd HH:mm:ss&#39,isShowClear:false,readOnly:true})'>";
						html += "</div>";
						html += "<div class='ym-fbox-text'>";
						html += "<label for='confetime'>会议结束时间</label>";
						html += "<input type='text' size='20' id='confetime' name='confetime' value='"+data.etime+"' class='Wdate' onFocus='WdatePicker({dateFmt:&#39yyyy-MM-dd HH:mm:ss&#39,isShowClear:false,readOnly:true})'>";
						html += "</div>";
						html += "<div class='ym-fbox-button'>";
						html += "<input type='submit' name='submit' id='submit' class='save' value='修改'>";
						html += "</div>";
						html += "</form>";
						document.getElementById("confedit").innerHTML=html;
					 },
					    error: function (errorMsg) {
					        document.getElementById(e).innerHTML=H.ERROR_CONF_LIST_FAIL;
					 }
					});
				break;
				
				case "conf/count":
					H.includedp();
					var html = "<form class='ym-form linearize-form ym-columnar' action='#' method='get' onSubmit='H.exec(&#39conf/count&#39); return false;' id='thisform' name='thisform'>";
					html +="<div class='ym-g33 ym-gl'>";
					html +="<div class='ym-fbox-text'>";
					html +="<label for='btime'>开始时间</label>";
					html +="<input type='text' size='20' id='btime' name='btime' value='"+G.getPrevMonth()+"' class='Wdate' onclick='WdatePicker({dateFmt:&#39yyyy-MM-dd HH:mm:ss&#39,isShowClear:false,readOnly:true})' />";
					html +="</div>";
					html +="</div>";
					html +="<div class='ym-g33 ym-gl'>";
					html +="<div class='ym-fbox-text'>";
					html +="<label for='etime'>结束时间</label>";
					html +="<input type='text' size='20' id='etime' name='etime' value='"+G.getNowTime()+"' class='Wdate' onclick='WdatePicker({dateFmt:&#39yyyy-MM-dd HH:mm:ss&#39,isShowClear:false,readOnly:true})' />";
					html +="</div>";
					html +="</div>";
					html +="<div class='ym-g33 ym-gr'>";
					html +="<a id='submit' class='ym-button ym-save' onclick='H.exec(&#39conf/count&#39); return false;'>查询</a>";
					html +="</div>";
					html +="</form>";
					html +="<a class='ym-button ym-next' id='exportXLS' onclick='H.exec(&#39conf/export&#39)' href='#' style='visibility:hidden'>导出EXCEL</a></div>";
					html +="<div id='countlist'></div>";
					document.getElementById(e).innerHTML=html;
				break;
				
				default:
				
			}
			//return html;
		},
		exec: function(m, e, r){
			switch(m){
				case "conf/add":
					FlyJSONP.get({
						url: apisite+m,
					    parameters: {
					        data: serialize(document.forms[0])
					    },
					    success: function (data) {
					    	var jd = JSON.stringify(data);
					    	console.log(jd);
					    	var html = "<div class='box success'>"+data.msg+"</div>";
					    	document.getElementById(e).innerHTML=html;
					    	setTimeout("window.location.replace('"+document.URL.slice(0,document.URL.lastIndexOf('/')+1)+r+"')",3000);
					    },
					    error: function (errorMsg) {
					        console.log(errorMsg);
					    }
					});	
					return false;
				break;
				case "conf/del":
					FlyJSONP.get({
						url: apisite+m,
					    parameters: {
					        data: e
					    },
					    success: function (data) {
					    	var jd = JSON.stringify(data);
					    	console.log(jd);
					    	document.getElementById(e).style.visibility = "visible"; 
					    	setTimeout("window.location.replace('"+document.URL.slice(0,document.URL.lastIndexOf('/')+1)+r+"')",3000);
					    },
					    error: function (errorMsg) {
					        console.log(errorMsg);
					    }
					});	
					return false;
				break;
				case "conf/count":
					//var sethtml = "";
					//var div = document.createElement('div');
					var etime = document.getElementById('etime').value;
					var btime = document.getElementById('btime').value;
					FlyJSONP.get({
						url: apisite+'conf/count',
					    parameters: {
					        btime: btime,
					        etime: etime
					    },
					    success: function (data) {
					    	var jd = JSON.stringify(data);
					    	console.log(jd);
					    	var html="<table class='bordertable''><thead><tr><th>会议ID</th><th>会议名称</th><th>使用次数</th><th>会议结束时间</th></tr></thead><tbody>";
					    	for(var i=0; i<data.length; ++i){
					    		html+="<tr id='123'><td>"+data[i].cid+"</td><td>"+data[i].confname+"</td><td>"+data[i].num+"</td><td>"+data[i].etime+"</td></tr>";
					    	}
					    	html+="</tbody></table>";
					    	document.getElementById('countlist').innerHTML=html;
					    	document.getElementById('exportXLS').style.visibility="visible";
					    },
					 	error: function (errorMsg) {
					        document.getElementById('countlist').innerHTML=H.INFO_CONF_COUNT_FAIL;
						}
					}); 
					//console.log(sethtml);
					//div.innerHTML = html;
					//document.getElementById('confcount').appendChild(div);
					//H.show('conf/count', 'confcount', sethtml);
					return false;
				break;
				case 'conf/export':
					console.log('export');
				break;
				default:
			}
		},
		join: function(cid){
			G.startconf(cid);
		},
		del: function(cid){
			H.exec('conf/del', cid, 'list');
		},
		edit: function(cid){
			window.location.replace(document.URL.slice(0,document.URL.lastIndexOf('/')+1)+"edit?cid="+cid);
			H.show('conf/edit', 'confedit', cid);
		},
		includedp: function(){
			if(typeof(WdatePicker) == "undefined") {
				var script = document.createElement("script");
				script.type = "text/javascript";
				script.charset = "utf-8";
				script.src = apisite+"js/date/WdatePicker.js";
				document.getElementsByTagName('head')[0].appendChild(script);
			}
			return;
		}
		
	};
	function serialize(form) {
	    if (!form || form.nodeName !== "FORM") {
	        return;
	    }
	    var i, j, q = [];
	    for (i = form.elements.length - 1; i >= 0; i = i - 1) {
	        if (form.elements[i].name === "") {
	            continue;
	        }
	        switch (form.elements[i].nodeName) {
	        case 'INPUT':
	            switch (form.elements[i].type) {
	            case 'text':
	            case 'hidden':
	            case 'password':
	            case 'button':
	            case 'reset':
	            case 'submit':
	                q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
	                break;
	            case 'checkbox':
	            case 'radio':
	                if (form.elements[i].checked) {
	                    q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
	                }                       
	                break;
	            case 'file':
	                break;
	            }
	            break;
	        case 'TEXTAREA':
	            q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
	            break;
	        case 'SELECT':
	            switch (form.elements[i].type) {
	            case 'select-one':
	                q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
	                break;
	            case 'select-multiple':
	                for (j = form.elements[i].options.length - 1; j >= 0; j = j - 1) {
	                    if (form.elements[i].options[j].selected) {
	                        q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].options[j].value));
	                    }
	                }
	                break;
	            }
	            break;
	        case 'BUTTON':
	            switch (form.elements[i].type) {
	            case 'reset':
	            case 'submit':
	            case 'button':
	                q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
	                break;
	            }
	            break;
	        }
	    }
	    return q.join("&");
	}
	
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
        if (!params.apikey) params.apikey = api_obj.apikey;
        return params;
    };
    var send = function(url, params){
        var url = buildURL(url, params);
        if (url!=null) { sendScriptRequest(url); };
    };
	
	var pp = 'start-index={startindex}&max-results={maxresults}';
    var sp = 'q={keyword}&'+pp;
    var pubp = 'published-min={publishedmin}&published-max={publishedmax}';
    var updp = 'updated-min={updatedmin}&updated-max={updatedmax}';
    var pup = pubp+'&'+updp;
    var ratp = 'rating-min={ratingmin}&rating-max={ratingmax}';
    var cp = 'apikey={apikey}&alt=xd&callback={callback}';
	var apis = {
        getConfList: {url:apisite+'conf/list'},
        searchUsers: {url:apisite+'people?'+sp},
        getBook: {url:apisite+'book/subject/{id}'},
        getISBNBook: {url:apisite+'book/subject/isbn/{isbn}'},
        searchBooks: {url:apisite+'book/subjects?tag={tag}&'+sp},
        getMovie: {url:apisite+'movie/subject/{id}'},
        searchMovies: {url:apisite+'movie/subjects?tag={tag}&'+sp},
        getMusic: {url:apisite+'music/subject/{id}'},
        searchMusic: {url:apisite+'music/subjects?tag={tag}&'+sp},
        getReview: {url:apisite+'review/{id}'},
        getUserReviews: {url:apisite+'people/{uid}/reviews?'+pup+'&'+pp},
        getBookReviews: {url:apisite+'book/subject/{sid}/reviews?'+pup+'&'+pp},
        getISBNBookReviews: {url:apisite+'book/subject/isbn/{isbn}/reviews?'+pup+'&'+pp},
        getMovieReviews: {url:apisite+'movie/subject/{sid}/reviews?'+pup+'&'+pp},
        getMusicReviews: {url:apisite+'music/subject/{sid}/reviews?'+pup+'&'+pp},
        getCollection: {url:apisite+'people/{uid}/collection/{cid}'},
        getUserCollection: {url:apisite+'people/{uid}/collection?cat={cat}&tag={tag}&status={status}&'+updp+'&'+ratp+'&'+pp},
        getBookTags: {url:apisite+'book/subject/{id}/tags?'+pp},
        getISBNBookTags: {url:apisite+'book/subject/isbn/{isbn}/tags?'+pp},
        getMovieTags: {url:apisite+'movie/subject/{id}/tags?'+pp},
        getMusicTags: {url:apisite+'music/subject/{id}/tags?'+pp},
        getUserBookTags: {url:apisite+'people/{id}/tags?cat=book&'+pp},
        getUserMovieTags: {url:apisite+'people/{id}/tags?cat=movie&'+pp},
        getUserMusicTags: {url:apisite+'people/{id}/tags?cat=music&'+pp}
    };
    for (var name in apis){
        api_obj[name] = (function(url){
            return function(params){
                send(url, formatParams(params));
            };
        })(apis[name].url);
    }
	Seegle.api = (function() {
	    return {
	        observe: function(model) {
	            /* 代码 */
	            return newModel;
	        },
	        onChange: function(callback) {
	            /* 代码 */
	        }
    	}
	}());	
	
/*
 * FlyJSONP v0.2
 * http://alotaiba.github.com/FlyJSONP
 */

var FlyJSONP = (function (global) {
    "use strict";
    /*jslint bitwise: true*/
    var self,
        addEvent,
        garbageCollectGet,
        parametersToString,
        generateRandomName,
        callError,
        callSuccessGet,
        callSuccessPost,
        callComplete;
    
    addEvent = function (element, event, fn) {
        if (element.addEventListener) {
            element.addEventListener(event, fn, false);
        } else if (element.attachEvent) {
            element.attachEvent('on' + event, fn);
        } else {
            element['on' + event] = fn;
        }
    };
    
    garbageCollectGet = function (callbackName, script) {
        self.log("Garbage collecting!");
        script.parentNode.removeChild(script);
        global[callbackName] = undefined;
        try {
            delete global[callbackName];
        } catch (e) { }
    };
    
    parametersToString = function (parameters, encodeURI) {
        var str = "",
            key,
            parameter;
            
        for (key in parameters) {
            if (parameters.hasOwnProperty(key)) {
                key = encodeURI ? encodeURIComponent(key) : key;
                parameter = encodeURI ? encodeURIComponent(parameters[key]) : parameters[key];
                str += key + "=" + parameter + "&";
            }
        }
        return str.replace(/&$/, "");
    };
	generateRandomName = function () {
        var uuid = '',
            s = [],
            hexDigits = "0123456789ABCDEF",
            i = 0;
            
        for (i = 0; i < 32; i += 1) {
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        
        s[12] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
        s[16] = hexDigits.substr((s[16] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01

        uuid = 'flyjsonp_' + s.join("");
        return uuid;
    };
    
    callError = function (callback, errorMsg) {
        self.log(errorMsg);
        if (typeof (callback) !== 'undefined') {
            callback(errorMsg);
        }
    };
    
    callSuccessGet = function (callback, data) {
        self.log("GET success");
        if (typeof (callback) !== 'undefined') {
            callback(data);
        }
        self.log(data);
    };
    
    callSuccessPost = function (callback, data) {
        self.log("POST success");
        if (typeof (callback) !== 'undefined') {
            callback(data);
        }
        self.log(data);
    };
    
    callComplete = function (callback) {
        self.log("Request complete");
        if (typeof (callback) !== 'undefined') {
            callback();
        }
    };
    self = {};
    self.options = {
        debug: false
    };
    
    self.init = function (options) {
        var key;
        self.log("Initializing!");
        
        for (key in options) {
            if (options.hasOwnProperty(key)) {
                self.options[key] = options[key];
            }
        }
        
        self.log("Initialization options");
        self.log(self.options);
        return true;
    };
    
    self.log = function (log) {
        if (global.console && self.options.debug) {
            global.console.log(log);
        }
    };
    
    self.get = function (options) {
        options = options || {};
        var url = options.url,
            callbackParameter = options.callbackParameter || 'callback',
            parameters = options.parameters || {},
            script = global.document.createElement('script'),
            callbackName = generateRandomName(),
            prefix = "?";
            
        if (!url) {
            throw new Error('URL must be specified!');
        }
        
        parameters[callbackParameter] = callbackName;
        if (url.indexOf("?") >= 0) {
            prefix = "&";
        }
        url += prefix + parametersToString(parameters, true);
        
        global[callbackName] = function (data) {
            if (typeof (data) === 'undefined') {
                callError(options.error, 'Invalid JSON data returned');
            } else {
                if (options.httpMethod === 'post') {
                    data = data.query.results;
                    if (!data || !data.postresult) {
                        callError(options.error, 'Invalid JSON data returned');
                    } else {
                        if (data.postresult.json) {
                            data = data.postresult.json;
                        } else {
                            data = data.postresult;
                        }
                        callSuccessPost(options.success, data);
                    }
                } else {
                    callSuccessGet(options.success, data);
                }
            }
            garbageCollectGet(callbackName, script);
            callComplete(options.complete);
        };
        
        self.log("Getting JSONP data");
        script.setAttribute('src', url);
        script.setAttribute('charset', 'utf-8');
        global.document.getElementsByTagName('head')[0].appendChild(script);
        
        addEvent(script, 'error', function () {
            garbageCollectGet(callbackName, script);
            callComplete(options.complete);
            callError(options.error, 'Error while trying to access the URL');
        });
    };
    
    self.post = function (options) {
        options = options || {};
        var url = options.url,
            parameters = options.parameters || {},
            yqlQuery,
            yqlURL,
            getOptions = {};
        
        if (!url) {
            throw new Error('URL must be specified!');
        }
        
        yqlQuery =  encodeURIComponent('select * from jsonpost where url="' + url + '" and postdata="' + parametersToString(parameters, false) + '"');
        yqlURL = 'http://query.yahooapis.com/v1/public/yql?q=' + yqlQuery + '&format=json' + '&env=' + encodeURIComponent('store://datatables.org/alltableswithkeys');
        
        getOptions.url = yqlURL;
        getOptions.httpMethod = 'post';
        
        if (typeof (options.success) !== 'undefined') {
            getOptions.success = options.success;
        }
        
        if (typeof (options.error) !== 'undefined') {
            getOptions.error = options.error;
        }
        
        if (typeof (options.complete) !== 'undefined') {
            getOptions.complete = options.complete;
        }
        
        self.get(getOptions);
    };
    
    return self;
}(this));
// Create a JSON object only if one does not already exist. We create the
// methods in a closure to avoid creating global variables.

var JSON;
if (!JSON) {
    JSON = {};
}

(function () {
    'use strict';

    function f(n) {
        // Format integers to have at least two digits.
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
window.G = window.G || {};
G = {
	client_registry: "SOFTWARE\\Seegle Video Conference System",
	client_confname: "sgconf.exe",
	NOWTIME: Math.floor(new Date().getTime() / 1000),
	startconf: function(i, j, k){
		var browser = BrowserDetect.browser.toLowerCase();
		if (browser=="msie"){
			ConfDirPath = testocx.GetRegKeyStringValue("HKEY_LOCAL_MACHINE",G.client_registry,"InstallPath");
			ConfDir = ConfDirPath + '\\' + G.client_confname;
			var confsrvip = "192.168.0.166:1:2810:2810";
			var confsrvloadbalance = '0';
			var uid = 'admin';
			var upass = 'admin';
			var unickname = uid; 
			var proxytype = '0';
			var proxyaddr = '';
			var proxyport = '';
			var proxyuser = '';
			var proxypass = '';
			var confid = "3516";
			var updateserverinfo='';
			
			confpara = " -U " + updateserverinfo + " -T " + proxytype + " -S " + proxyaddr + " -R " + proxyport + " -N $'" + proxyuser + "' -Y $'" + proxypass + "' -H " + confsrvip + " -u " + uid + " -p $'" + upass + "' -n $'" + unickname + "' -c "+ confid + " -k $'' -t 2 -b "+confsrvloadbalance+"";
			console.log(ConfDir+confpara);
			testocx.RunExe(ConfDir,confpara,ConfDirPath,1);
			return;
		} 
		else if(browser=="firefox") {
			netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
			try {
				var wrk = Components.classes["@mozilla.org/windows-registry-key;1"].createInstance(Components.interfaces.nsIWindowsRegKey);
				wrk.open(wrk.ROOT_KEY_LOCAL_MACHINE, G.client_registry, wrk.ACCESS_READ);
				var installpath = wrk.readStringValue("InstallPath");
				var programfile = installpath + '\\' + G.client_confname;
				wrk.close();
				
			} catch (err) {
				console.log("请先安装客户端软件！"); 
				console.log(err);
				return; 
			}
			try {
				var FileFactory = new Components.Constructor("@mozilla.org/file/local;1","nsILocalFile","initWithPath");
				var str_LocalProgram = programfile;
				var obj_Program = new FileFactory(str_LocalProgram); 
				var process = Components.classes["@mozilla.org/process/util;1"].createInstance(Components.interfaces.nsIProcess);
				process.init(obj_Program);
				var confsrvip = "192.168.0.166:1:2810:2810";
				var confsrvloadbalance = '0';
				var uid = 'admin';
				var upass = 'admin';
				var unickname = uid; 
				var proxytype = '0';
				var proxyaddr = '';
				var proxyport = '';
				var proxyuser = '';
				var proxypass = '';
				var confid = "3516";
				var updateserverinfo='';	
				var args = ["-U",updateserverinfo,"-T",proxytype,"-S",proxyaddr,"-R",proxyport,"-N","'$"+proxyuser+"'","-Y","'$"+proxypass+"'","-H", confsrvip,"-u",uid,"-p","'$"+upass+"'","-n","'$"+unickname+"'","-c",confid,"-k","'$'","-t","2","b",confsrvloadbalance];
				console.log(programfile+args);
				process.runw(false, args, args.length);
					
			} catch (err) {
				console.log(err); 
				return; 
			};			
		} else {
			console.log("不支持");	
		}
	},
	date: function(format, timestamp) {
    var that = this,
        jsdate, f, formatChr = /\\?([a-z])/gi,
        formatChrCb,
        _pad = function (n, c) {
            if ((n = n + '').length < c) {
                return new Array((++c) - n.length).join('0') + n;
            }
            return n;
        },
        txt_words = ["Sun", "Mon", "Tues", "Wednes", "Thurs", "Fri", "Satur", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    formatChrCb = function (t, s) {
        return f[t] ? f[t]() : s;
    };
    f = {
        d: function () {
            return _pad(f.j(), 2);
        },
        D: function () {
            return f.l().slice(0, 3);
        },
        j: function () {
            return jsdate.getDate();
        },
        l: function () {
            return txt_words[f.w()] + 'day';
        },
        N: function () {
            return f.w() || 7;
        },
        S: function () {
            var j = f.j();
            return j > 4 && j < 21 ? 'th' : {1: 'st', 2: 'nd', 3: 'rd'}[j % 10] || 'th';
        },
        w: function () {
            return jsdate.getDay();
        },
        z: function () {
            var a = new Date(f.Y(), f.n() - 1, f.j()),
                b = new Date(f.Y(), 0, 1);
            return Math.round((a - b) / 864e5) + 1;
        },
 
        W: function () {
            var a = new Date(f.Y(), f.n() - 1, f.j() - f.N() + 3),
                b = new Date(a.getFullYear(), 0, 4);
            return _pad(1 + Math.round((a - b) / 864e5 / 7), 2);
        },

        F: function () {
            return txt_words[6 + f.n()];
        },
        m: function () {
            return _pad(f.n(), 2);
        },
        M: function () {
            return f.F().slice(0, 3);
        },
        n: function () {
            return jsdate.getMonth() + 1;
        },
        t: function () {
            return (new Date(f.Y(), f.n(), 0)).getDate();
        },

        L: function () {
            return new Date(f.Y(), 1, 29).getMonth() === 1 | 0;
        },
        o: function () {
            var n = f.n(),
                W = f.W(),
                Y = f.Y();
            return Y + (n === 12 && W < 9 ? -1 : n === 1 && W > 9);
        },
        Y: function () {
            return jsdate.getFullYear();
        },
        y: function () {
            return (f.Y() + "").slice(-2);
        },

        a: function () {
            return jsdate.getHours() > 11 ? "pm" : "am";
        },
        A: function () {
            return f.a().toUpperCase();
        },
        B: function () {
            var H = jsdate.getUTCHours() * 36e2,
                i = jsdate.getUTCMinutes() * 60,
                s = jsdate.getUTCSeconds(); // Seconds
            return _pad(Math.floor((H + i + s + 36e2) / 86.4) % 1e3, 3);
        },
        g: function () { 
            return f.G() % 12 || 12;
        },
        G: function () {
            return jsdate.getHours();
        },
        h: function () { 
            return _pad(f.g(), 2);
        },
        H: function () { 
            return _pad(f.G(), 2);
        },
        i: function () { 
            return _pad(jsdate.getMinutes(), 2);
        },
        s: function () { 
            return _pad(jsdate.getSeconds(), 2);
        },
        u: function () { 
            return _pad(jsdate.getMilliseconds() * 1000, 6);
        },

        e: function () { 
            throw 'Not supported (see source code of date() for timezone on how to add support)';
        },
        I: function () { 
            var a = new Date(f.Y(), 0),
                c = Date.UTC(f.Y(), 0),
                b = new Date(f.Y(), 6),
                d = Date.UTC(f.Y(), 6); // Jul 1 UTC
            return 0 + ((a - c) !== (b - d));
        },
        O: function () { 
            var tzo = jsdate.getTimezoneOffset(),
                a = Math.abs(tzo);
            return (tzo > 0 ? "-" : "+") + _pad(Math.floor(a / 60) * 100 + a % 60, 4);
        },
        P: function () {
            var O = f.O();
            return (O.substr(0, 3) + ":" + O.substr(3, 2));
        },
        T: function () {
            return 'UTC';
        },
        Z: function () { // Timezone offset in seconds (-43200...50400)
            return -jsdate.getTimezoneOffset() * 60;
        },
 
        // Full Date/Time
        c: function () { // ISO-8601 date.
            return 'Y-m-d\\Th:i:sP'.replace(formatChr, formatChrCb);
        },
        r: function () { // RFC 2822
            return 'D, d M Y H:i:s O'.replace(formatChr, formatChrCb);
        },
        U: function () { // Seconds since UNIX epoch
            return jsdate.getTime() / 1000 | 0;
        }
    };
    this.date = function (format, timestamp) {
        that = this;
        jsdate = ((typeof timestamp === 'undefined') ? new Date() : // Not provided
        (timestamp instanceof Date) ? new Date(timestamp) : // JS Date()
        new Date(timestamp * 1000) // UNIX timestamp (auto-convert to int)
        );
        return format.replace(formatChr, formatChrCb);
    };
    return this.date(format, timestamp);
},
		getNowTime: function(){			
			return G.date("Y-m-d H:i:s", G.NOWTIME);
		},
		getNextMonth: function(){
			return G.date("Y-m-d H:i:s", G.NOWTIME+60*60*24*30);
		},
		getPrevMonth: function(){
			return G.date("Y-m-d H:i:s", G.NOWTIME-60*60*24*30);
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
