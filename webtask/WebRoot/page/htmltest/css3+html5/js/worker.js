var num = 0;
function count(){
	postMessage(num);
	num++;
	setTimeout(count,100);
}
count();