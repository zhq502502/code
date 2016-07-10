function changeLanguage(language){
    setCookie("SGlanguage", language);
    location.reload(true);
}

function changeLanguageLogin(language){
    setCookie("SGlanguage", language);
    location.reload(true);
}

function getCookie(c_name)
{
	c_name=getRootPath()+"/"+c_name;
    if (document.cookie.length > 0)
    {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1)
        {
            c_start = c_start + c_name.length + 1;
            c_end   = document.cookie.indexOf(";",c_start);
            if (c_end == -1)
            {
                c_end = document.cookie.length;
            }
            return unescape(document.cookie.substring(c_start,c_end));
        }
    }
    return null
}

function setCookie(c_name,value)
{
	c_name=getRootPath()+"/"+c_name;
    var expiretime = 10 * 360 * 24 * 60 * 60 * 1000;  //有效期10年
    var exdate = new Date();
    exdate.setTime(exdate.getTime() + expiretime);

    // 使设置的有效时间正确。增加toGMTString()
    document.cookie = c_name + "=" +escape(value) + ((expiretime == null) ? "" : ";expires=" + exdate.toGMTString());
}
   
function showSelect(hld,id){
    var ele = document.getElementById(id);
    ele.style.display = 'block';
    var timer = null;
    ele.onmouseover = function(){
        if(timer){
            clearTimeout(timer);
        }
        ele.style.display = 'block';
    }
    ele.onmouseout = function(){
        timer = setTimeout(function(){ele.style.display = 'none'},500);
    }
    
    hld.onmouseover = function(){
        if(timer){
            clearTimeout(timer);
        }
    }
    hld.onmouseout = function(){
        timer = setTimeout(function(){ele.style.display = 'none'},500);
    }
}

function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(projectName);
}
