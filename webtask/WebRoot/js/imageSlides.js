$(function(){
	getImages();
})

function getImages(){
	var images = [{}];
	$("#img-hide").each(function(i,n){
		images[i].src = $(this).attr(src);
		images[i].href = $(this).attr(src);
	});
}