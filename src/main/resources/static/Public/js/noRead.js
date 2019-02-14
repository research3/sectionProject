function weiduInfo(){
	var xtxxNum=0;
	var znxNum=0;
	$.getJSON("/c/szy/user/queryAllNews",function(data){
		$.each(data,function(i,obj){
			if(obj.readstate==false&&obj.newstype==1){
				xtxxNum=xtxxNum+1;
			}else if(obj.readstate==false&&obj.newstype==2){
				znxNum=znxNum+1;
			}
		})
		$("[name=xtxx]").html(xtxxNum);
		$("[name=znx]").html(znxNum);
	})
}