function unit_course(){
	var unit = $("#unitId").val();
	if(unit==""){
		$('#courseId').attr("disabled","disabled");
		$('#courseId').html("<option value=''>--请先选择单元--</option> ");
	} else {
		$.ajax({
			type : 'POST',
			url : "/zk/show_data_jsp/unit_course.jsp",
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			data : {
				"unit" : unit,
				"date" : new Date()
			},
			success : function(data) {
				$('#courseId').html(data);
				$('#courseId').removeAttr("disabled");
			}
		});
	}
};
function refresh(){
	
	var courseId = $("#courseId").val();
	$.ajax({
		type : "post",
		url : "getDateSingleCount",
		data : {
			"courseId" : courseId
		},
		success : function(data) {
			$('#singlecount_data').html(data);
		}
	});
};