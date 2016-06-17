$(function(){
	$(".back").bind("click",function(){
		history.go(-1);
	})
})
function urlreturn(){
	if($("#a-loginuser").attr("logined")+""=="1"){
		return true;
	}else{
		$('#studentloginModal').modal('toggle');
		return false;
	}
}