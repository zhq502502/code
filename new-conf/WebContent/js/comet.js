//(编译框架管理jspro工程)
function AssertException(a){
    this.message=a
}
function assert(a,b){
    if(!a)throw new AssertException(b)
}
function log(a){
    top.console&&top.console.log?top.console.log(a):top.jash&&top.jash.print(a)
}
function acquire(a,b,c){
    var d=Math.random(),e=function(){
        var e=WPC.cookie.get(a);
        e==d?b&&b():c&&c()
    };
    WPC.cookie.set(a,d),setTimeout(e,40)
}
var WPC={};
AssertException.prototype.toString=function(){
    return"AssertException: "+this.message
}
,window.console||(console={
    log:function(){}
}),function(ns){
    function isArray(a){
        return Object.prototype.toString.call(a)==="[object Array]"
    }
    function textNodeValue(a){
        var b=a.firstChild;
        while(b){
            if(!b.isElementContentWhitespace)return b.textContent;
            b=b.nextSibling
        }
        return null
    }
    var ua=navigator.userAgent.toLowerCase(),domain=document.domain.substring(document.domain.indexOf(".")+1);
    ns.startXnclient=function(){
        var a;
        try{
            a=new ActiveXObject("xntalk.Application.2")
        }
        catch(b){
            return!1
        }
        if(!a)return!1;
        var c;
        try{
            c=a.login()
        }
        catch(b){
            return!1
        }
        return c==1?!1:!0
    }
    ,ns.startXnService=function(){
        var a,b=new ActiveXObject("renren.Renren.1");
        return a=b.StartUp("xntalk"),a==1?!1:!0
    }
    ,ns.getLoginUin=function(){
        var a=ns.cookie.get("id");
        return a||(a=ns.cookie.get("hostid")),a||(a=ns.cookie.get("userid")),a
    }
    ,ns.xmlEncode=function(a){
        return a=a.replace(/&/g,"&amp;"),a=a.replace(/</g,"&lt;"),a=a.replace(/>/g,"&gt;"),a=a.replace(/'/g,"&apos;"),a=a.replace(/\"/g,"&quot;"),a
    }
    ,ns.countStr=function(a,b){
        try{
            var c=a.split(b)
        }
        catch(d){
            return 0
        }
        return c.length-1
    }
    ,ns.isInArray=function(a,b){
        for(var c=0;
        c<b.length;
        c++)if(b[c]==a)return!0;
        return!1
    }
    ,ns.distinct=function(a){
        var b=[],c={};
        for(var d=0;
        d<a.length;
        d++)c[a[d]]=1;
        for(key in c)b.push(key);
        return b
    }
    ,ns.parseUserInfo=function(a){
        var b=/(\d+)@([^\/]+)\/(.*)/;
        return b.exec(a)
    }
    ,ns.logger={
        beatNum:0,heartBeat:function(){
            var a=this;
            this.timer=setTimeout(function(){
                a.beatNum++,clearTimeout(a.timer),ns.PersistMgr.report("heartBeat"),a.heartBeat()
            }
            ,3e4)
        }
        ,stopBeat:function(){
            return clearTimeout(this.timer),this.beatNum
        }
        
    }
    ,ns.browser={
        isFirefox:/gecko/.test(ua)&&/rv/.test(ua),isOpera:/opera/.test(ua),isChrome:/chrome/.test(ua),isWebkit:/webkit/.test(ua)&&!/opera/.test(ua)&&!/chrome/.test(ua),isIE:/msie/.test(ua)&&!/opera/.test(ua),browserOK:!1,type:""
    };
    var bs=["isFirefox","isOpera","isChrome","isWebkit","isIE"];
    for(var i=0;
    i<bs.length;
    i++)if(ns.browser[bs[i]]){
        ns.browser.type=bs[i];
        break
    }
    ns.cookie={
        get:function(a){
            var b=document.cookie.match("(^|;) ?"+a+"=([^;]*)(;|$)");
            return b?decodeURIComponent(b[2]):null
        }
        ,set:function(a,b,c){
            var d=a+"="+encodeURIComponent(b);
            if(c){
                c=c*1e3*60*60*24;
                var e=new Date((new Date).getTime()+c);
                d+=";expires="+e.toGMTString()
            }
            document.cookie=d
        }
        
    };
    var ck=ns.cookie;
    ns.json={
        parse:function(str){
            return eval("("+str+")")
        }
        
    }
    ,ns.event={
        addEvent:function(a,b,c){
            return a._listeners||(a._listeners={}),a._listeners[b]||(a._listeners[b]=[]),a._listeners[b].push(c),a
        }
        ,removeEvent:function(a,b,c){
            if(!a._listeners||!a._listeners[b]){
                assert(!1,"remove an event not added");
                return
            }
            for(var d=0;
            d<a._listeners[b].length;
            ++d)if(a._listeners[b][d]==c){
                var e=a._listeners[b].splice(d,1);
                assert(e[0]==c,"remove not correct")
            }
            
        }
        ,fireEvent:function(a,b,c){
            if(!a._listeners)return null;
            if(!a._listeners[b]||!a._listeners[b].length)return null;
            var d=a._listeners[b];
            if(!d)return null;
            for(var e=0;
            e<d.length;
            e++)try{
                d[e].apply(a,c)
            }
            catch(f){}return a
        }
        ,enableCustomEvent:function(a){
            var b=this;
            a.addEvent=function(c,d){
                b.addEvent(a,c,d)
            }
            ,a.removeEvent=function(c,d){
                b.removeEvent(a,c,d)
            }
            ,a.fireEvent=function(c){
                var d=Array.prototype.slice.call(arguments,1,arguments.length);
                b.fireEvent(a,c,d)
            }
            
        }
        
    }
    ,ns.net={
        ajax:function(a){
            var b=ns.net.getXhr();
            return b.open(a.method||"get",a.url,!0),b.onreadystatechange=function(){
                b.readyState==4&&(b.status>=200&&b.status<300?a.onSuccess&&a.onSuccess(b):a.onError&&a.onError(b))
            }
            ,b.send(a.data),b
        }
        ,getXhr:function(){
            try{
                return new XMLHttpRequest
            }
            catch(a){}try{
                return new ActiveXObject("microsoft.xmlhttp")
            }
            catch(a){}try{
                return new ActiveXObject("msxml2.xmlhttp")
            }
            catch(a){}return null
        }
        
    }
    ,ns.xml={
        extractMessage:function(a){
            var b=a.replace(/<title>/i,"<title><![CDATA[");
            a=b.replace(/<\/title>/i,"]]></title>"),b=a.replace(/<body>/i,"<body><![CDATA["),a=b.replace(/<\/body>/i,"]]></body>");
            var c,d=[];
            if(window.ActiveXObject){
                c=new ActiveXObject("Microsoft.XMLDOM"),c.async=!1,c.loadXML(a);
                if(0!=c.parseError.errorCode)return d;
                var e=c.getElementsByTagName("message");
                for(var f=0;
                f<e.length;
                ++f){
                    var g=e[f];
                    if(g.attributes){
                        var h={};
                        h.type=g.getAttribute("type"),h.msg_id=g.getAttribute("id");
                        if(h.type=="chat")h.from=g.getAttribute("from"),h.to=g.getAttribute("to"),h.fname=g.getAttribute("fname"),g.firstChild&&g.firstChild.firstChild&&(h.msg_content=g.firstChild.firstChild.data);
                        else if(h.type=="notify"||h.type=="notify2"){
                            h.touin=ns.getLoginUin();
                            for(var i=0;
                            i<g.childNodes.length;
                            i++)g.childNodes[i].tagName=="subject"?h.title=g.childNodes[i].childNodes[0].data:g.childNodes[i].tagName=="body"&&(h.content=g.childNodes[i].childNodes[0].data)
                        }
                        else h.from=g.getAttribute("from"),h.to=g.getAttribute("to"),h.content=g.childNodes[0].childNodes[0].data
                    }
                    d.push(h)
                }
                
            }
            else{
                var j=new DOMParser;
                c=j.parseFromString(a,"text/xml");
                var k=c.documentElement,l=k.childNodes.length;
                for(var f=0;
                f<l;
                ++f){
                    var g=k.childNodes[f];
                    if(g.hasAttributes()){
                        var h={};
                        h.type=g.getAttribute("type"),h.msg_id=g.getAttribute("id");
                        if(h.type=="chat")h.from=g.getAttribute("from"),h.to=g.getAttribute("to"),h.fname=g.getAttribute("fname"),g.childNodes&&(h.msg_content=textNodeValue(g));
                        else if(h.type=="notify"||h.type=="notify2"){
                            h.touin=ns.getLoginUin();
                            for(var i=0;
                            i<g.childNodes.length;
                            i++)g.childNodes[i].tagName=="subject"?h.title=g.childNodes[i].childNodes[0].nodeValue:g.childNodes[i].tagName=="body"&&(h.content=g.childNodes[i].childNodes[0].nodeValue)
                        }
                        else h.from=g.getAttribute("from"),h.to=g.getAttribute("to"),h.content=g.childNodes[0].childNodes[0].nodeValue;
                        d.push(h)
                    }
                    
                }
                
            }
            return d
        }
        
    }
    ,ns.sound={
        soundLoadCnt:0,init:function(){
            this.loadSound()
        }
        ,loadSound:function(){
            var a="http://wpi."+domain+"/wtalk/wpsound.swf",b='<object width="10" height="10" id="webpagersound" type="application/x-shockwave-flash" data="'+a+'">'+'<param name="allowScriptAccess" value="sameDomain" />'+'<param name="movie" value="wpsound.swf" />'+'<param name="scale" value="noscale" />'+'<param name="salign" value="lt" />'+"</object>",c=document.createElement("div");
            c.innerHTML=b,document.body.appendChild(c)
        }
        ,playSound:function(a){
            var b=this;
            if(!ns.Profile.isPlaySound())return;
            var c=document.getElementById("webpagersound");
            if(!c){
                ++this.soundLoadCnt<20&&(this.soundLoadCnt==1&&this.loadSound(),setTimeout(function(){
                    b.playSound(a)
                }
                ,200));
                return
            }
            try{
                a=="notify"?c.playNotifySound():c.playMessageSound()
            }
            catch(d){}
        }
        
    }
    ,ns.seqq={
        seqn:40,SEQ_EXPIRE_DAYS:3650,init:function(){
            this.seqn=ck.get("wpseqn")||this.seqn
        }
        ,getMsgSeq:function(a){
            var b=parseInt(ck.get(a||"wpseq"));
            return isNaN(b)?-1:b>=0?b:0
        }
        ,setMsgSeq:function(a,b){
            ck.set(b||"wpseq",a,this.SEQ_EXPIRE_DAYS)
        }
        ,getNextSeq:function(a){
            var b=this.getMsgSeq()+1;
            return this.setMsgSeq(b),a&&a>this.seqn&&(this.seqn=a,this.setSeqn(this.seqn)),"s"+b%this.seqn
        }
        ,setSeqn:function(a){
            ck.set("wpseqn",a)
        }
        
    }
    ,ns.seqq.init();
    var USE_IM_BIT=1,PLAY_SOUND_BIT=2,BLIST_TOP_BIT=4,STORE_HISTORY_BIT=8;
    ns.Profile={
        expire:3650,setting:27,init:function(){
            this.load()
        }
        ,load:function(){
            var a=ns.cookie.get("wpsetting");
            return a!=null&&(this.setting=parseInt(a)),!0
        }
        ,isUseIm:function(){
            return this.load(),this.setting&USE_IM_BIT
        }
        ,isPlaySound:function(){
            return this.setting&PLAY_SOUND_BIT
        }
        ,isStoreHistory:function(){
            return this.setting&STORE_HISTORY_BIT
        }
        ,setUseIm:function(a,b){
            a?this.setting|=USE_IM_BIT:this.setting&=~USE_IM_BIT,b&&(ck.set("wpsetting",this.setting,this.expire),this.fireEvent("profile_useIM_switch",a?"1":"0"))
        }
        ,setPlaySound:function(a,b){
            a?this.setting|=PLAY_SOUND_BIT:this.setting&=~PLAY_SOUND_BIT,b&&(ck.set("wpsetting",this.setting,this.expire),this.fireEvent("profile_sound_switch",a?"1":"0"))
        }
        ,setStoreHistory:function(a,b){
            a?this.setting|=STORE_HISTORY_BIT:this.setting&=~STORE_HISTORY_BIT,b&&(ck.set("wpsetting",this.setting,this.expire),this.fireEvent("profile_setHistory_switch",a?"1":"0"))
        }
        
    }
    ,ns.event.enableCustomEvent(ns.Profile),ns.Profile.init(),ns._report_tasks={},ns.reportExpt=function(a,b,c){
        var d=this._report_tasks;
        if(!d[a])d[a]=0;
        else if(d[a]>=5)return;
        try{
            var e=encodeURIComponent,f=top.window.location,g=top.XN.browser.IE?"IE":"noIE",h="[~]";
            c=c&&c.replace(/\n/g,h);
            var i=b.toString();
            b.description&&(i+=k.description),i=i.replace(/\n/g,h);
            var j=["http://s.renren.com/speedstats/jserror/stats.php?reporter=webpager","task="+e(a||""),"browser="+g+"-"+ns.browser.type+imVer,"expt="+e(i),"context="+e(c),"location="+e(f),"time="+(new Date-0)].join("&")
        }
        catch(k){
            var j="http://s.renren.com/speedstats/jserror/stats.php?reporter=webpager&task=reportExpt"
        }
        d[a]++;
        var l=(new Image).src=j;
        window.reportImageRequest=l
    }
    
}
(WPC),function(ns){
    ns.PersistMgr={
        write_seq:0,qLen:10,MONIT_INTERV:513,_storage:null,lastModify:{
            lastKeys:"",keys:[]
        }
        ,lastModKeys:[],init:function(){
            var a=this.initStorage();
            if(!a)return a;
            var b=this.getLastMod();
            this.timestamp=b.timestamp;
            var c=this.lastModify;
            return c.timestamp=this.timestamp,c.keys=b.keysAry,c.lastKeys=b.keys,this.storageMonit(),a
        }
        ,initStorage:function(){
            var a=!0;
            return window.localStorage?this._storage=this.localStorage:(this._storage=this.userDataStorage,this._storage.init(),a=this._storage.isAvailable()),a
        }
        ,lastModToStr:function(a){
            return a.timestamp+"\n"+a.keys.join(",")
        }
        ,pushLastModKey:function(a){
            var b=this.lastModify;
            !(b.length>20)
        }
        ,getWriteSeq:function(){
            return this.write_seq++
        }
        ,setLastMod:function(a,b){
            var c=this.getLastMod(),d=this.qLen,e=(new Date).getTime(),f=this.lastModify;
            try{
                var g={
                    keys:c.keys.split(","),lastKeys:c.keys,timestamp:e
                };
                g.keys.length>d&&(g.keys.length=d);
                var h=this.getWriteSeq()%d;
                g.keys[h]=a,g.lastKeys=g.keys.join(",");
                var i=this.lastModToStr(g)
            }
            catch(j){
                ns.reportExpt("setLastMod",j,c.keys)
            }
            this._storage.setItemTo("last"+h,e+" "+a,"webpager_control"),this._storage.setItemTo("lastMod",i,"webpager_control",b)
        }
        ,clearLastMod:function(){
            var a=(new Date).getTime(),b=this.lastModify;
            b.keys=[];
            var c=this.lastModToStr(b);
            this._storage.setItemTo("lastMod",c,"webpager_control")
        }
        ,setItemTo:function(a,b,c){
            var d=this,e=0;
            try{
                this._storage||this.initStorage();
                var f=this._storage.setItemTo(a,b,c);
                return e=1,f&&this.setLastMod(c+"."+a),f
            }
            catch(g){
                return ns.reportExpt("setItemTo",g,a+"->"+b),!1
            }
            
        }
        ,getItemFrom:function(a,b){
            try{
                this._storage||this.initStorage();
                var c=this._storage.getItemFrom(a,b);
                return c
            }
            catch(d){
                return ns.reportExpt("getItemFrom",d,a+"="+b),""
            }
            
        }
        ,clear:function(){
            this._storage.clear()
        }
        ,lock:function(){
            this._storage.setItemTo("l",!0,"webpager_control")
        }
        ,unlock:function(){
            this._storage.setItemTo("l",!1,"webpager_control")
        }
        ,isLock:function(){
            return this._storage.getItemFrom("l","webpager_control")=="true"
        }
        ,getStamp:function(){
            return this.getItemFrom("timestamp","webpager_control")
        }
        ,getLastMod:function(){
            var a={},b=this.getItemFrom("lastMod","webpager_control");
            try{
                if(b){
                    var c=b.split("\n");
                    return c&&(a.timestamp=c[0],a.keys=c[1]||"",a.keysAry=c[1]?c[1].split(","):[]),a
                }
                
            }
            catch(d){
                ns.reportExpt("getLastMod",d,b)
            }
            return a={
                timestamp:(new Date).getTime(),keys:"",keysAry:[]
            }
            ,a
        }
        ,getNewKeys:function(a,b){
            var c={},d={},e=[];
            if(a.join("")==b.join(""))return[a[0]];
            for(var f=0;
            f<b.length;
            f++)c[b[f]]===undefined?c[b[f]]=0:c[b[f]]++;
            for(f=0;
            f<a.length;
            f++)d[a[f]]===undefined?d[a[f]]=0:d[a[f]]++;
            var g;
            for(var h=0;
            h<a.length;
            h++){
                g=a[h];
                if(c[g]===undefined||d[g]>c[g])ns.isInArray(g,e)||e.push(g)
            }
            return e
        }
        ,getNewKeys2:function(a,b){
            var c=[],d=[],e;
            try{
                for(var f=0;
                f<this.qLen;
                f++){
                    e=this._storage.getItemFrom("last"+f,"webpager_control");
                    if(!e)continue;
                    e=this.getLastObj(e),e.timestamp>a&&e.timestamp<=b&&c.push(e)
                }
                c.sort(function(a,b){
                    return a.timestamp>b.timestamp
                });
                for(var g=0;
                g<c.length;
                g++)d.push(c[g].key)
            }
            catch(h){
                ns.reportExpt("getNewKeys2",h,c.join())
            }
            return d
        }
        ,getLastObj:function(a){
            var b=a.split(" ");
            return{
                timestamp:parseInt(b[0]),key:b[1]
            }
            
        }
        ,storageMonit:function(){
            var a=this.getLastMod();
            try{
                if(this.timestamp!=a.timestamp){
                    var b=a.keys.split(","),c=this.lastModify.lastKeys.split(","),d=this.getNewKeys2(this.timestamp,a.timestamp),e={
                        keys_raw:d.join(","),keys:ns.distinct(d).join(",")
                    };
                    this.lastModify.lastKeys=a.keys,this.lastModify.keys=b,this.timestamp=a.timestamp,this.fireEvent("storage",e)
                }
                
            }
            catch(f){
                ns.reportExpt("storageMonit",f,a.keys)
            }
            var g=this;
            this.monitTimer=setTimeout(function(){
                g.storageMonit()
            }
            ,this.MONIT_INTERV)
        }
        ,getNid:function(m){
            var nid=null;
            if(m)try{
                var obj=eval("("+m.content+")");
                obj&&obj.nid&&(nid=obj.nid)
            }
            catch(e){}return nid
        }
        ,reportExpt:function(a,b){
            this.report(a+"&e="+b)
        }
        ,report:function(a,b){
            return
        }
        ,localStorage:{
            setItemTo:function(a,b,c){
                try{
                    localStorage.setItem(c+"_"+a,b)
                }
                catch(d){
                    return ns.PersistMgr.reportExpt("cch01",d),!1
                }
                return!0
            }
            ,getItemFrom:function(a,b){
                return localStorage.getItem(b+"_"+a)
            }
            ,removeItem:function(a){
                localStorage.removeItem(a)
            }
            ,clear:function(){
                localStorage.clear()
            }
            ,save:function(){}
        }
        ,userDataStorage:{
            getItemFrom:function(a,b){
                return b=="webpager_control"?this.get(this.controlNode,b,a):b=="webpager_msg"?this.get(this.hisNode,b,a):this.get(this.msgNode,b,a)
            }
            ,setItemTo:function(a,b,c,d){
                return c=="webpager_control"?this.set(this.controlNode,c,a,b,d):c=="webpager_msg"?this.set(this.hisNode,c,a,b,d):this.set(this.msgNode,c,a,b,d)
            }
            ,removeItem:function(a){
                this.msgNode.removeAttribute(a)
            }
            ,clear:function(){
                var a=this.msgNode.XMLDocument,b=a.selectSingleNode("ROOTSTUB");
                for(var c=0;
                c<b.attributes.length;
                ++c){
                    var d=b.attributes[c];
                    this.msgNode.removeAttribute(d.baseName)
                }
                this.msgNode.save("webpager_control")
            }
            ,isAvailable:function(){
                try{
                    this.msgNode.save()
                }
                catch(a){
                    if(a.number&&Math.abs(parseInt(a.number))==2146827838)return!0;
                    if(!a.description||a.description.indexOf("Wrong number")==-1&&a.description.indexOf("错误的参数个数")==-1){
                        ns.PersistMgr._retry_flag||(setTimeout(function(){
                            ns.PersistMgr.init()
                        }),ns.PersistMgr._retry_flag=1);
                        var b="";
                        return ns.PersistMgr._retry_flag&&(b+="retryOnce"),a.description&&(b+=a.description),a.number&&(b+=a.number),ns.reportExpt("userDataCheck",a,b),!1
                    }
                    return ns.PersistMgr.reportExpt("cch03",a),!0
                }
                
            }
            ,save:function(a){
                this.msgNode.save(a)
            }
            ,init:function(){
                this.msgNode=this.createStorgeNode(),this.controlNode=this.createStorgeNode(),this.hisNode=this.createStorgeNode()
            }
            ,createStorgeNode:function(){
                var a=document.createElement("div");
                return a.style.display="none",a.style.behavior="url(#default#userData)",document.body.appendChild(a),a
            }
            ,set:function(a,b,c,d,e){
                var f=c;
                try{
                    a.setAttribute(c,d),a.save(b),e!==!1&&a.save(b)
                }
                catch(g){
                    return!1
                }
                return!0
            }
            ,get:function(a,b,c){
                try{
                    a.load(b)
                }
                catch(d){}return a.getAttribute(c)
            }
            
        }
        ,flashStorage:{
            getItemFrom:function(a,b){
                return this.flash.getItem(b+"_"+a)
            }
            ,setItemTo:function(a,b,c,d){
                try{
                    this.flash.setItem(c+"_"+a,b)
                }
                catch(e){
                    return ns.PersistMgr.reportExpt("cch04",e),!1
                }
                return!0
            }
            ,removeItem:function(a){
                try{
                    this.flash.removeItem(zone+"_"+a)
                }
                catch(b){
                    return ns.PersistMgr.reportExpt("cch05",b),!1
                }
                return!0
            }
            ,test:function(){
                this.init()
            }
            ,clear:function(){},init:function(){
                var a="webpager_flash_localStorage",b="http://wpi.renren.com/PObject.swf",c=document.createElement("embed");
                c.setAttribute("id",a),c.setAttribute("name",a),c.setAttribute("type","application/x-shockwave-flash"),c.setAttribute("src",b),c.setAttribute("width",1),c.setAttribute("height",1),c.setAttribute("style","position:absolute; right:0px; bottom:0px;"),document.body.appendChild(c),this.flash=c
            }
            
        }
        
    }
    ,ns.event.enableCustomEvent(ns.PersistMgr)
}
(WPC),function(a){
    var b=a.cookie,c=a.xml,d=a.Profile;
    a.Comet={
        messageId:0,connErrCnt:0,connErrCntMax:5,errTryCnt:0,pollBackNum:0,errTryCntMax:5,connTimer:null,_xhrPoll:null,_xhrConn:null,connect:function(){
            var b=this;
            this._xhrConn&&(this.connAbort(),this.abort()),this._xhrConn=new a.net.ajax({
                url:"/comet_get?mid=0&r="+Math.random()+"&ins",onSuccess:function(){
                    b.connSucc()
                }
                ,onError:function(){
                    b.connError()
                }
                
            })
        }
        ,testConnect:function(){
            var b=this;
            this._xhrConn=new a.net.ajax({
                url:"/comet_get?mid=0&r="+Math.random()+"&ins",onSuccess:function(){
                    b.connSucc()
                }
                ,onError:function(){
                    b.connError()
                }
                
            })
        }
        ,abort:function(){
            this.commonAbort=!0,this._xhrPoll&&this._xhrPoll.abort()
        }
        ,connAbort:function(){
            this._xhrConn&&this._xhrConn.abort()
        }
        ,disconnect:function(){
            return this.abort(),this.connState(0)
        }
        ,connSucc:function(a){
            this.connState(1),this.errTryCnt=0,this.connErrCnt=0,this.doLongPoll(),this.fireEvent("comet_connected",a)
        }
        ,connError:function(){
            this.connState(0),this._xhrConn=null,++this.connErrCnt;
            if(this.connErrCnt==this.connErrCntMax)this.fireEvent("comet_connect_fail");
            else if(this.connErrCnt>this.connErrCntMax)return
        }
        ,release:function(){
            this._xhrPoll&&this._xhrPoll.abort(),this._xhrConn&&this._xhrConn.abort(),this._xhrPoll=null,this._xhrConn=null
        }
        ,pollBack:function(b){
            var d=this;
            if(b.status==211){
                this.release(),this.fireEvent("comet_release");
                return
            }
            this.errTryCnt=0;
            var e=b.responseText;
            try{
                if(e){
                    var f=a.countStr(e,'type="notify2"');
                    if(f)for(var g=0;
                    g<f;
                    g++)a.PersistMgr.report("n81");
                    var h=a.countStr(e,"ugc_content");
                    if(h)for(var g=0;
                    g<h;
                    g++)a.PersistMgr.report("n51")
                }
                
            }
            catch(i){}try{
                var j=c.extractMessage(e);
                j.length&&(this.messageId=j[j.length-1].msg_id),d.fireEvent("comet_got",j),a.PersistMgr.report(21)
            }
            catch(i){
                a.PersistMgr.report("71&e="+i)
            }
            this.doLongPoll()
        }
        ,pollError:function(){
            if(this.errTryCnt>=this.errTryCntMax){
                this.connState(0),this.fireEvent("comet_failed"),this._xhrPoll=null;
                return
            }
            this.errTryCnt++;
            if(this.commonAbort){
                this.commonAbort=!1,this.fireEvent("comet_disconnected"),this._xhrPoll=null;
                return
            }
            this.doLongPoll()
        }
        ,doLongPoll:function(){
            var b=this;
            this._xhrPoll=new a.net.ajax({
                url:"/comet_get?mid="+this.messageId+"&r="+Math.random(),onSuccess:function(a){
                    b.pollBack(a)
                }
                ,onError:function(a){
                    b.pollError(a)
                }
                
            })
        }
        ,connState:function(a){
            if(a!==undefined)return b.set("wimconn",a),!0;
            var c=b.get("wimconn");
            return c!=null&&c!="0"
        }
        ,send:function(b){
            var c=this;
            new a.net.ajax({
                url:"/comet_broadcast",data:b,method:"post",onSuccess:function(a){
                    c.fireEvent("comet_send_succ")
                }
                
            })
        }
        
    };
    var e=a.Comet;
    a.event.enableCustomEvent(a.Comet),a.CometMgr={
        id:0,CONN_CHECK_INTERV:2e3,CONN_INTERV_CYCLE:60,autoReconnect:!0,curCheckTime:0,aliveCycle:65,isLocalConn:!1,lastConnState:0,init:function(){
            this.bindEvent(),this.lastConnState=a.Comet.connState(),this.connMonit()
        }
        ,tryToConnect:function(){
            if(!a.getLoginUin())return;
            var b=location.href,c=this;
            !e.connState()&&a.Profile.isUseIm()&&acquire("wimconn",function(){
                c.isLocalConn=!0,e.connect(),!e._xhrPoll
            }
            ,function(){
                e.abort(),c.isLocalConn=!1
            })
        }
        ,retryConnect:function(){
            this.isLocalConn=!1,e.disconnect(),this.tryToConnect()
        }
        ,connMonit:function(){
            if(e.isUnload){
                clearTimeout(this.connTimer);
                return
            }
            var b=this,c=e.connState();
            this.curCheckTime++,e.lastConnState!=c?0==c?(a.Profile.isUseIm()&&this.tryToConnect(),this.fireEvent("cometmgr_disconnected")):this.fireEvent("cometmgr_connected"):(this.autoReconnect&&this.isLocalConn&&this.curCheckTime==this.CONN_INTERV_CYCLE&&(this.curCheckTime=0,this.retryConnect(),this.fireEvent("monit_cycle")),this.isAlive()?this.isLocalConn&&!this.isMyConn()&&(this.isLocalConn=!1,e.release()):(this.isLocalConn=!1,e.disconnect(),this.tryToConnect())),e.lastConnState=c,this.connTimer=setTimeout(function(){
                clearTimeout(b.connTimer),b.connMonit()
            }
            ,this.CONN_CHECK_INTERV)
        }
        ,getConnState:function(){
            var a=e.connState();
            return a
        }
        ,bindEvent:function(){
            var a=this;
            e.addEvent("comet_connected",function(){
                a.isLocalConn&&(a.keepAlive(),a.autoReconnect=!0),a.fireEvent("cometmgr_connected")
            }),e.addEvent("comet_release",function(){
                a.isLocalConn=!1
            }),e.addEvent("comet_connect_fail",function(){
                a.isLocalConn=!1
            }),e.addEvent("comet_got",function(b){
                a.fireEvent("comet_got",b)
            }),e.addEvent("comet_failed",function(){
                a.isLocalConn=!1,a.autoReconnect=!1,a.fireEvent("comet_disconnected")
            }),e.addEvent("comet_send_succ",function(){
                a.fireEvent("cometmgr_send_succ")
            }),window.onbeforeunload=function(){
                WPC.Comet.isUnload=!0,a.isLocalConn&&e.disconnect()
            }
            ,window.onunload=function(){
                WPC.Comet.isUnload=!0,a.isLocalConn&&e.disconnect()
            }
            
        }
        ,send:function(a,b){
            e.send(this.buildMessage(a,b))
        }
        ,buildMessage:function(b,c){
            assert(b.length>0);
            var d={
                fromserver:"@talk.renren.com"
            };
            switch(c){
                case"groupchat":d.toserver="@group.talk.renren.com",d.type="groupchat";
                break;
                default:d.toserver="@talk.renren.com",d.type="chat"
            }
            var e=["<sendlist>\n"];
            for(var f=0;
            f<b.length;
            ++f){
                var g=b[f];
                e.push('<message type="'+d.type+'" from="'),e.push(g.from+d.fromserver),e.push('"'),e.push(' to="'),e.push(g.to+d.toserver),e.push('">\n<body>'),e.push(a.xmlEncode(g.msg_content)),e.push("</body>\n</message>\n")
            }
            return e.push("</sendlist>\n\0"),e.join("")
        }
        ,keepAlive:function(){
            this.curCheckTime=0,this.id=(new Date).getTime(),b.set("wkl",this.id)
        }
        ,isAlive:function(){
            var a=b.get("wkl");
            return a=parseInt(a),tNow=(new Date).getTime(),isNaN(a)?!1:tNow-a<this.aliveCycle*this.CONN_CHECK_INTERV?!0:!1
        }
        ,isMyConn:function(){
            var a=b.get("wkl");
            return a=parseInt(a),a=this.id
        }
        ,connect:function(){
            this.isLocalConn=!0,e.connect()
        }
        ,disconnect:function(){
            e.disconnect()
        }
        
    }
    ,a.event.enableCustomEvent(a.CometMgr)
}
(WPC),function(a){
    var b=a.PersistMgr,c=a.CometMgr,d=a.cookie;
    a.MsgMgr={
        MSG_HISRY_COUNT:32,SEQ_EXPIRE_DAYS:3650,msg_list:[],zone:{
            2:"webpager_msg",3:"webpager_notify"
        }
        ,init:function(){
            this.bindEvent()
        }
        ,bindEvent:function(){
            var c=this,d=a.CometMgr;
            d.addEvent("comet_got",function(b){
                var d=0,e="",f=b.length;
                for(var g=0;
                g<f;
                g++){
                    var h=b[g];
                    if(!h)continue;
                    var i=h.content,j=a.json.parse(i);
                    if(h.content&&h.content.indexOf('{"show":"hidden"')!=-1)continue;
                    if(h.touin&&j&&h.touin-0==j.from_id-0){
                        d++;
                        try{
                            e+=a.PersistMgr.getNid(b[g])+","
                        }
                        catch(k){}continue
                    }
                    switch(h.type){
                        case"chat":a.PersistMgr.report(61),c.addMsgHistory(h);
                        break;
                        case"notify2":a.PersistMgr.report(81,b[0]);
                        try{
                            if(b&&b[g]&&b[g].content&&b[g].content.indexOf("ugc_content")!=-1){
                                d++;
                                try{
                                    e+=a.PersistMgr.getNid(b[g])+","
                                }
                                catch(k){}
                            }
                            
                        }
                        catch(k){}c.addRealTime(h);
                        break;
                        case"groupchat":if(h.from.indexOf("-"+a.getLoginUin())!=-1)return;
                        c.addGroupChat(h,f);
                        break;
                        default:c.addOtherMsg(h)
                    }
                    
                }
                if(d)try{
                    a.PersistMgr.report("51&nids="+e+"&count="+d,b[0])
                }
                catch(k){}
            }),d.addEvent("cometmgr_send_succ",function(){
                c.msg_list.length=0
            }),b.addEvent("storage",function(a){
                var d=a.keys.split(","),e,f,g;
                for(var h=0;
                h<d.length;
                h++){
                    e=d[h],f=e.split("."),g=f[0],e=f[1];
                    if(g!="webpager_msgs")continue;
                    var i=b.getItemFrom(e,"webpager_msgs"),j;
                    j=c.getMsgType(i);
                    if(j=="group")c.fireEvent("groupmsg_got",c.strToGroupChat(b.getItemFrom(e,g)));
                    else if(j=="notify2")c.fireEvent("realTime_got",c.strToRealTime(b.getItemFrom(e,g)));
                    else if(j=="chat"){
                        var k=b.getItemFrom(e,g),l=c.strToMsg(k);
                        c.fireEvent("message_got",l)
                    }
                    else c.fireEvent("other_got",c.str2other(b.getItemFrom(e,g)))
                }
                
            })
        }
        ,getMsgType:function(a){
            var b=a.split("\n");
            return b[0]
        }
        ,getMsgSeq:function(a){
            var b=parseInt(d.get(a||"wimmseq"));
            return isNaN(b)?-1:b>=0?b:0
        }
        ,setMsgSeq:function(a,b){
            d.set(b||"wimmseq",a,this.SEQ_EXPIRE_DAYS)
        }
        ,send:function(a,b){
            var d={
                from:a.fromuin,fname:a.fromname,to:a.touin,msg_content:a.msg_content
            };
            (b===undefined||b=="chat")&&this.addMsgHistory(d),c.send([d],b)
        }
        ,loadMsgHistory:function(c){
            c+="";
            var d=this.MSG_HISRY_COUNT,e=[],f,g=a.getLoginUin();
            for(var h=0;
            h<d;
            h++)f=b.getItemFrom("m"+h,"webpager_msg"),f=this.strToMsg(f),f.timestamp||(f.timestamp=(new Date).getTime()),(f.fromuin==c&&f.touin==g||f.fromuin==g&&f.touin==c)&&e.push(f);
            return e.length&&e.sort(function(a,b){
                var c=parseInt(a.timestamp),d=parseInt(b.timestamp);
                return c<d?1:c>d?-1:0
            }),e
        }
        ,msgToStr:function(a){
            return"chat\n"+a.fromuin+"\n"+a.fromname+"\n"+a.touin+"\n"+a.msg_content+"\n"+a.timestamp
        }
        ,strToMsg:function(a){
            var b={};
            if(!a)return b;
            var c=a.split("\n");
            return c?(b.fromuin=c[1],b.fromname=c[2],b.touin=c[3],b.msg_content=c[4],b.timestamp=c[5],b):b
        }
        ,addMsgHistory:function(c){
            try{
                var d={
                    fromuin:parseInt(c.from),fromname:c.fname,touin:parseInt(c.to),msg_content:c.msg_content,timestamp:(new Date).getTime()
                }
                ,e=this.msgToStr(d),f=a.seqq.getNextSeq();
                b.setItemTo(f,e,"webpager_msgs");
                var g=this.getMsgSeq()+1;
                b.setItemTo("m"+g%this.MSG_HISRY_COUNT,e,"webpager_msg"),this.setMsgSeq(g)
            }
            catch(h){
                a.PersistMgr.reportExpt("cch06",h)
            }
            
        }
        ,addNotifyHistory:function(a){},strToRealTime:function(a){
            var b={};
            if(!a)return b;
            var c=a.split("\n");
            return c?(b.type=c[0],b.touin=c[1],b.msg_id=c[2],b.content=c[3],b):b
        }
        ,realTimeToStr:function(a){
            return a.type+"\n"+a.touin+"\n"+a.msg_id+"\n"+a.content
        }
        ,groupChatToStr:function(a){
            return"group\n"+a.from+"\n"+a.to+"\n"+a.msg_id+"\n"+a.content
        }
        ,strToGroupChat:function(a){
            var b={};
            if(!a)return b;
            var c=a.split("\n");
            return c?(b.mtype=c[0],b.mfrom=c[1],b.mto=c[2],b.msg_id=c[3],b.content=c[4],b):b
        }
        ,str2other:function(a){
            var b={};
            if(!a)return b;
            var c=a.split("\n");
            return c?(b.mtype=c[0],b.from=c[1],b.touin=c[2],b.msg_id=c[3],b.content=c[4],b):b
        }
        ,other2str:function(a){
            return a.type+"\n"+a.from+"\n"+a.to+"\n"+a.msg_id+"\n"+a.content
        }
        ,addOtherMsg:function(c){
            var d=this.other2str(c),e=a.seqq.getNextSeq();
            b.setItemTo(e,d,"webpager_msgs")
        }
        ,addRealTime:function(c){
            var d=this.realTimeToStr(c),e=a.seqq.getNextSeq();
            b.setItemTo(e,d,"webpager_msgs")
        }
        ,addGroupChat:function(c,d){
            var e=this.groupChatToStr(c),f=a.seqq.getNextSeq();
            b.setItemTo(f,e,"webpager_msgs")
        }
        
    }
    ,a.event.enableCustomEvent(a.MsgMgr)
}
(WPC),function(a){
    var b=a.CometMgr,c=a.Sound,d=a.Profile,e=a.MsgMgr,f=a.PersistMgr,g=a.cookie;
    a.PagerChannel={
        init:function(){
            this.bindEvent()
        }
        ,bindEvent:function(){
            var c=this;
            d.addEvent("profile_useIM_switch",function(b){
                b=="1"?a.CometMgr.connect():a.CometMgr.disconnect(!0)
            }),e.addEvent("message_got",function(a){
                c.fireEvent("message_got",a)
            }),e.addEvent("realTime_got",function(a){
                a.timestamp=(new Date).getTime(),a.type=56,c.fireEvent("realTime_got",a)
            }),e.addEvent("groupmsg_got",function(a){
                c.fireEvent("groupmsg_got",a)
            }),e.addEvent("other_got",function(a){
                c.fireEvent("other_got",a)
            }),b.addEvent("cometmgr_connected",function(){
                try{
                    top.$msg_proxy&&top.$msg_proxy.onConnected()
                }
                catch(b){
                    a.PersistMgr.reportExpt("cch07",b)
                }
                
            }),b.addEvent("cometmgr_disconnected",function(){
                try{
                    top.$msg_proxy&&top.$msg_proxy.onDisconnected(),c.fireEvent("channel_disconnected")
                }
                catch(b){
                    a.PersistMgr.reportExpt("cch08",b)
                }
                
            }),f.addEvent("storage",function(a){
                c.fireEvent("storage",a)
            })
        }
        ,startIM:function(){
            var b=document.domain.indexOf("renren.com")!=-1,c=top.XN,d=top.XN.cookie.get,e,f,g;
            if(d("id")&&d("t")&&d("xnsid")&&b&&c.browser.IE)try{
                var h=new ActiveXObject("renren.Renren.1");
                h&&(top.webpager.IMInstalled=!0);
                var g=h.IfNeedStart("xntalk");
                g==1?(f=h.StartUp("xntalk"),f==0?e=a.startXnclient():e=!0):e=!1
            }
            catch(i){
                e=a.startXnclient()
            }
            top.webpager.fireEvent("im_start_over",e)
        }
        ,iamReady:function(a){
            a.showNotice=!0
        }
        ,sendMessage:function(a,b){
            e.send(a,b)
        }
        ,getMessageHistory:function(a){
            var b=e.loadMsgHistory(a);
            return b
        }
        ,getNotifyHistory:function(){
            return[]
        }
        ,enableConn:function(a){
            a?d.setUseIm(!0,!0):d.setUseIm(!1,!0)
        }
        ,isLocalConnect:function(){
            return b.isLocalConn
        }
        ,showRealTime:function(b){
            e.addRealTime({
                content:b,type:"notify2",touin:a.getLoginUin(),msg_id:e.messageId
            })
        }
        ,setItem:function(b,c,d){
            setTimeout(function(){
                return f.setItemTo(b,c,"webpager_common"+(d?"":a.getLoginUin()))
            }
            ,0)
        }
        ,getItem:function(b,c){
            return f.getItemFrom(b,"webpager_common"+(c?"":a.getLoginUin()))
        }
        ,showGroupChat:function(a){
            e.addGroupChat({
                from:a.from,to:a.to,msg_id:0,content:a.content
            })
        }
        ,getConnState:function(){
            return b.getConnState()
        }
        ,setPlaySound:function(a){
            return d.setPlaySound(a,!0)
        }
        ,getShowPager:function(){
            return!0
        }
        ,notificationRequest:function(a){
            window.webkitNotifications.requestPermission(function(){
                var b=window.webkitNotifications.checkPermission();
                a(b)
            })
        }
        ,notificationShow:function(a){
            if(!window.webkitNotifications.checkPermission()){
                var b;
                b=window.webkitNotifications.createNotification(a.head,a.name,a.content),b.show(),b.onclick=function(){
                    window.focus()
                }
                ,setTimeout(function(){
                    b.cancel()
                }
                ,8e3)
            }
            
        }
        ,notificationPermission:function(){
            return window.webkitNotifications.checkPermission()
        }
        
    }
    ,a.event.enableCustomEvent(a.PagerChannel),document.domain="renren.com",top.webpager=WPC.PagerChannel
}
(WPC);
var imHelper={
    getCookie:function(a){
        return WPC.cookie.get(a)
    }
    ,setCookie:function(a,b,c){
        WPC.cookie.set(a,b,c)
    }
    ,playSound:function(){
        WPC.sound.playSound()
    }
    
}
,wpcontroller={
    onInputMessage:function(a){
        WPC.MsgMgr.send(a)
    }
    ,getMessageHistory:function(a){
        WPC.MsgMgr.loadMsgHistory(a)
    }
    
};
(function(a){
    a.CometMgr.init();
})(WPC);
