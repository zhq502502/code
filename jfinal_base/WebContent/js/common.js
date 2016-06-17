(function ($) {
//SELECT控件设置函数
function setSelectControl(oSelect, iStart, iLength, iIndex) {
oSelect.empty();
for (var i = 0; i < iLength; i++) {
if ((parseInt(iStart) + i) == iIndex)
oSelect.append("<option selected='selected' value='" + (parseInt(iStart) + i) + "'>" + (parseInt(iStart) + i) + "</option>");
else
oSelect.append("<option value='" + (parseInt(iStart) + i) + "'>" + (parseInt(iStart) + i) + "</option>");
}
}
 
$.fn.DateSelector = function (options) {
options = options || {};
 
 //初始化
this._options = {
ctlYearId: null,
ctlMonthId: null,
ctlDayId: null,
defYear: 0,
defMonth: 0,
defDay: 0,
minYear: 1882,
maxYear: new Date().getFullYear()
};
 
for (var property in options) {
this._options[property] = options[property];
}
 
this.yearValueId = $("#" + this._options.ctlYearId);
this.monthValueId = $("#" + this._options.ctlMonthId);
this.dayValueId = $("#" + this._options.ctlDayId);
 
var dt = new Date(),
iMonth = parseInt(this._options.defMonth),
iDay = parseInt(this._options.defDay),
iMinYear = parseInt(this._options.minYear),
iMaxYear = parseInt(this._options.maxYear);
 
this.Year = parseInt(this._options.defYear) || dt.getFullYear();
this.Month = 1 <= iMonth && iMonth <= 12 ? iMonth : dt.getMonth() + 1;
this.Day = iDay > 0 ? iDay : dt.getDate();
this.minYear = iMinYear && iMinYear < this.Year ? iMinYear : this.Year;
this.maxYear = iMaxYear && iMaxYear > this.Year ? iMaxYear : this.Year;
 
//初始化控件
//设置年
setSelectControl(this.yearValueId, this.minYear, this.maxYear - this.minYear + 1, this.Year);
//设置月
setSelectControl(this.monthValueId, 1, 12, this.Month);
//设置日
var daysInMonth = new Date(this.Year, this.Month, 0).getDate(); //获取指定年月的当月天数[new Date(year, month, 0).getDate()]
if (this.Day > daysInMonth) { this.Day = daysInMonth; };
setSelectControl(this.dayValueId, 1, daysInMonth, this.Day);
 
var oThis = this;
  //绑定控件事件
this.yearValueId.change(function () {
oThis.Year = $(this).val();
setSelectControl(oThis.monthValueId, 1, 12, oThis.Month);
oThis.monthValueId.change();
});
this.monthValueId.change(function () {
oThis.Month = $(this).val();
var daysInMonth = new Date(oThis.Year, oThis.Month, 0).getDate();
if (oThis.Day > daysInMonth) { oThis.Day = daysInMonth; };
setSelectControl(oThis.dayValueId, 1, daysInMonth, oThis.Day);
});
this.dayValueId.change(function () {
oThis.Day = $(this).val();
});
};
})(jQuery);

//START OF MESSAGE SCRIPT //

var MSGTIMER = 20;
var MSGSPEED = 3;
var MSGOFFSET = 3;
var MSGHIDE = 5;

// build out the divs, set attributes and call the fade function //
function inlineMsg(target,string,autohide) {
  var msg;
  var msgcontent;
  if(!document.getElementById('msg')) {
    msg = document.createElement('div');
    msg.id = 'msg';
    msgcontent = document.createElement('div');
    msgcontent.id = 'msgcontent';
    document.body.appendChild(msg);
    msg.appendChild(msgcontent);
    msg.style.filter = 'alpha(opacity=0)';
    msg.style.opacity = 0;
    msg.alpha = 0;
  } else {
    msg = document.getElementById('msg');
    msgcontent = document.getElementById('msgcontent');
  }
  msgcontent.innerHTML = '<span class="n-yellow msg-box n-right" style="" for="'+target+'"><span role="alert" class="msg-wrap n-error"><span class="n-arrow"><b>◆</b><i>◆</i></span><span class="n-icon"></span><span class="n-msg">'+string+'</span></span></span>';
  msg.style.display = 'block';
  var msgheight = msg.offsetHeight;
  var targetdiv = document.getElementById(target);
  targetdiv.focus();
  var targetheight = targetdiv.offsetHeight;
  var targetwidth = targetdiv.offsetWidth;
  var topposition = topPosition(targetdiv) - ((msgheight - targetheight) / 2);
  var leftposition = leftPosition(targetdiv) + targetwidth + MSGOFFSET;
  msg.style.top = topposition + 'px';
  msg.style.left = leftposition + 'px';
  scrollTo(leftposition,topposition-100);
  clearInterval(msg.timer);
  msg.timer = setInterval("fadeMsg(1)", MSGTIMER);
  if(!autohide) {
    autohide = MSGHIDE;  
  }
  window.setTimeout("hideMsg()", (autohide * 1000));
}

// hide the form alert //
function hideMsg(msg) {
  var msg = document.getElementById('msg');
  if(!msg.timer) {
    msg.timer = setInterval("fadeMsg(0)", MSGTIMER);
  }
}

// face the message box //
function fadeMsg(flag) {
  if(flag == null) {
    flag = 1;
  }
  var msg = document.getElementById('msg');
  var value;
  if(flag == 1) {
    value = msg.alpha + MSGSPEED;
  } else {
    value = msg.alpha - MSGSPEED;
  }
  msg.alpha = value;
  msg.style.opacity = (value / 100);
  msg.style.filter = 'alpha(opacity=' + value + ')';
  if(value >= 99) {
    clearInterval(msg.timer);
    msg.timer = null;
  } else if(value <= 1) {
    msg.style.display = "none";
    clearInterval(msg.timer);
  }
}

// calculate the position of the element in relation to the left of the browser //
function leftPosition(target) {
  var left = 0;
  if(target.offsetParent) {
    while(1) {
      left += target.offsetLeft;
      if(!target.offsetParent) {
        break;
      }
      target = target.offsetParent;
    }
  } else if(target.x) {
    left += target.x;
  }
  return left;
}

// calculate the position of the element in relation to the top of the browser window //
function topPosition(target) {
  var top = 0;
  if(target.offsetParent) {
    while(1) {
      top += target.offsetTop;
      if(!target.offsetParent) {
        break;
      }
      target = target.offsetParent;
    }
  } else if(target.y) {
    top += target.y;
  }
  return top;
}

// preload the arrow //
if(document.images_gb) {
  arrow = new Image(7,80); 
  arrow.src = "img/msg_arrow.gif"; 
}
