var sjzxHref="/c/lwx/user/getUserBySjzx";
var grzxHref="/c/lwx/center/home";
var zhszHref="/c/lwx/user/queryGrzxInfo";
var xxHref="/xx-xtxx.html";
$.getJSON("/c/lwx/user/queryAUser",function(data){
	$("#li1 a").attr("href",sjzxHref);
	$("#li2 a").attr("href",grzxHref);
	$("#li3 a").attr("href",zhszHref);
	$("#li4 a").attr("href",xxHref);
	if(data.auditstatus!=2){
		$("#li1").remove();
	}
})

