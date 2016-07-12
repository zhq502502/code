function Map() {
 var struct = function(key, value) {
  this.key = key;
  this.value = value;
 }
 
 var put = function(key, value){
  for (var i = 0; i < this.arr.length; i++) {
   if ( this.arr[i].key === key ) {
    this.arr[i].value = value;
    return;
   }
  }
   this.arr[this.arr.length] = new struct(key, value);
 }
 
 var get = function(key) {
  for (var i = 0; i < this.arr.length; i++) {
   if ( this.arr[i].key === key ) {
     return this.arr[i].value;
   }
  }
  return getMsg(ERROR_NOTFIND)+key;
  //return null;
 }
 
 var remove = function(key) {
  var v;
  for (var i = 0; i < this.arr.length; i++) {
   v = this.arr.pop();
   if ( v.key === key ) {
    continue;
   }
   this.arr.unshift(v);
  }
 }
 
 var size = function() {
  return this.arr.length;
 }
 
 var isEmpty = function() {
  return this.arr.length <= 0;
 }

 this.arr = new Array();
 this.get = get;
 this.put = put;
 this.remove = remove;
 this.size = size;
 this.isEmpty = isEmpty;
}
//定义错误map类并初始化
var errorMap = new Map();
errorMap.put("0","成功");
errorMap.put("1","失败");
errorMap.put("2","服务类型不正确");
errorMap.put("3","客户类型不正确");
errorMap.put("4","不存在的用户ID");
errorMap.put("5","密码不正确");
errorMap.put("6","重复登陆");
errorMap.put("7","数据不正确");
errorMap.put("8","数据库查询为空");
errorMap.put("9","溢出");
errorMap.put("10","错误的组ID");
errorMap.put("11","离线");
errorMap.put("12","不存在的部门ID");
errorMap.put("13","不存在的职位ID");
errorMap.put("14","不存在的会议ID");
errorMap.put("15","无权限");
errorMap.put("16","请登出");
errorMap.put("17","重复");
errorMap.put("18","需加密");
errorMap.put("19","未准备");
errorMap.put("20","数据不一致");
errorMap.put("21","版本已更新");
errorMap.put("22","版本不匹配");
errorMap.put("23","请等待");
errorMap.put("24","帐号已过期");
errorMap.put("25","帐号已禁用");
errorMap.put("26","重复的EIA号码");
errorMap.put("27","未知的协议");
errorMap.put("28","数据传输出错");
errorMap.put("29","无效的连接");
errorMap.put("30","无效的号码");
errorMap.put("31","无效的地址");
errorMap.put("32","错误的用户名");
errorMap.put("33","正在运行");
errorMap.put("34","数据库操作失败");
errorMap.put("35","帐号不正确");
errorMap.put("36","过期");
errorMap.put("37","该企业不支持游客登陆");
errorMap.put("38","不足");
errorMap.put("39","数据超出限度");
errorMap.put("40","被锁定");
errorMap.put("41","电话认证失败");
errorMap.put("42","不存在的投票主题ID");
errorMap.put("43","状态错误");
errorMap.put("44","错误的卡号");
errorMap.put("1000","会议业务被禁用");
errorMap.put("1001","没有有效的业务");
errorMap.put("1002","临时会议业务人数超出限制");
errorMap.put("1003","长期会议业务人数超出限制");
errorMap.put("11011","未开通会议业务");
errorMap.put("10100","会议尚未开始");
errorMap.put("10101","会议室过期");
errorMap.put("10104","集群组被锁定");
errorMap.put("10105","集群组会议室数量超限");
errorMap.put("10106","集群组会议室人数超限");
errorMap.put("1062","数据重复");
errorMap.put("500","服务器内部异常");
