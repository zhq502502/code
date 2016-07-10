$(function(){
	//排序字段加入点击事件
	$("td[orderName]").bind("click",function(){
		var orderName = $(this).attr("orderName");
		$("form input[name='orderName']").val(orderName);
		if(consumeOrderName==orderName){
			if(consumeOrderType=="desc"){
				$("form input[name='orderType']").val("asc");
			}else{
				$("form input[name='orderType']").val("desc");
			}
		}else{
			$("form input[name='orderType']").val("desc");
		}
			
		$("form").submit();
	});
	//初始化排序字段图标
	$("td[orderName]").each(function(){
		var img = '<img src="images_gb/order.gif" style="margin-bottom: -4px;"/>';
		$(this).append(img);
		if($(this).attr("orderName")==consumeOrderName){
			if(consumeOrderType=="asc"){
				$(this).find("img").attr("src","images_gb/order_asc.gif");
			}
			if(consumeOrderType=="desc"){
				$(this).find("img").attr("src","images_gb/order_desc.gif");
			}
		}
	});
	//将排序图标加入body中，提高浏览器缓存
	$("body").append('<img src="images_gb/order.gif" style="display:none"/><img src="images_gb/order_asc.gif" style="display:none"/><img src="images_gb/order_desc.gif" style="display:none"/>');
	//月份选择时
	phoneConsumeMonth();
	$("form select[name=year]").bind("change",function(){
		phoneConsumeMonth();
	});
});

function phoneConsumeMonth(){
	var options = '';
	for(var i=1;i<=12;i++){
		options +='<option value="'+i+'">'+i+'</option>';
	}
	var selectMonth = $("form select[name=month]").val();
	$("form select[name=month]").html(options);
	$("form select[name=month]").val(selectMonth);
	var year = parseInt($("form select[name=year]").val());
	var date = new Date();
	var month = 0;
	if(year==date.getFullYear()){
		month = date.getMonth();
		$("form select[name=month] option").each(function(){
			var m = parseInt($(this).val());
			if(m>(month+1)){
				$(this).remove();
			}
		});
		if(parseInt($("form select[name=month]").val())>month+1){
			$("form select[name=month]").val(month+1);
		}
	}else{
		
	}
}
