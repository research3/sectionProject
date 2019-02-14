$.getJSON("/c/szy/user/queryAUser",function(data){
	if(data.auditstatus!=2){
		$(".account_ul_a li:eq(3)").remove();
	}
})

