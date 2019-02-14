function serviceCollection(sid){
	jQuery.getJSON("/c/szy/user/queryAUser",function(data){
		if(data!=null){
			jQuery.getJSON("api/serviceCollection",{"sid":sid},function(result){
				alert(result.msg);
				location.reload();
			});
		}else{
			location.href="/fw-loginHint.html";
		}
	});

}