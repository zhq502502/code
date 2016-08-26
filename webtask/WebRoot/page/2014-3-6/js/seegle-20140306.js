$(function(){
	var set = {
		mainWidth:800,//网页主窗口宽度，必须保证主窗口居中
		top:10,//悬浮窗离顶距离
		margin:20,//悬浮窗离主窗口距离
		scroll:false,//是否滚动跟随
		left_div:{//左边悬浮窗
			width:100,//悬浮窗宽度
			height:200,//高度
			imangeUrl:"img/1.jpg",//图片连接地址
			clickUrl:"http://www.baidu.com",//图片点击连接地址
			target:"_blank"//连接打开方式：_blank新窗口打开，_self:本窗口打开
		},
		right_div:{
			width:100,
			height:200,
			imangeUrl:"img/2.jpg",
			clickUrl:"http://www.baidu.com",
			target:"_blank"
		}
	};
	seegle_0306window(set);
	//设置窗口大小重置时间
	$(window).resize(function () {
		seegle_0306set(set);
    });
	if(set.scroll){
		//设置窗口滚动事件
		$(window).scroll(function () {
			seegle_0306scroll(set);
        });
	}
})
/**
 * 加入广告悬浮窗
 * @param set 参数
 * @return
 */
function seegle_0306window(set){
	var left = "<div id='see_left_0306' style='width:"+set.left_div.width+"px;height:"+set.left_div.height+"px;position: absolute;top:"+set.top+"px;z-index:250'><a href='"+set.left_div.clickUrl+"' target='"+set.left_div.target+"'><img src='"+set.left_div.imangeUrl+"' style='width:100%;height100%;border:0px;' /></a></div>";
	var right = "<div id='see_right_0306' style='width:"+set.right_div.width+"px;height:"+set.right_div.height+"px;position: absolute;top:"+(set.top)+"px;z-index:250'><a href='"+set.right_div.clickUrl+"' target='"+set.right_div.target+"'><img src='"+set.right_div.imangeUrl+"' style='width:100%;height100%;border:0px;' /></a></div>";
	$("body").append(left);
	$("body").append(right);
	seegle_0306set(set);
}
/**
 * 窗口大小改变时，设置悬浮窗位置
 * @param set
 * @return
 */
function seegle_0306set(set){
	var body_width=$("html").width() ;
	var left = body_width/2-set.mainWidth/2-set.left_div.width-set.margin;
	var right = body_width/2+set.mainWidth/2+set.margin;
	$("#see_left_0306").css({"left":left});
	$("#see_right_0306").css({"left":right});
	if((set.margin*2+set.mainWidth+set.left_div.width+set.right_div.width)>body_width){
		$("#see_left_0306,#see_right_0306").hide();
	}else{
		$("#see_left_0306,#see_right_0306").show();
	}
}
/**
 * 滚动条滚动时重置悬浮窗位置
 * @param set
 * @return
 */
function seegle_0306scroll(set){
	 $("#see_left_0306,#see_right_0306").css({ "top": parseInt($(window).scrollTop()+set.top) })
}
