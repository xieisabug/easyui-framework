function level_school() {
	var level = $("#level").val();
	$.ajax({
		type : 'POST',
		url : "/zk/show_data_jsp/level_school.jsp",
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		data : {
			"level" : level,
			"date" : new Date()
		},
		success : function(data) {
			$('#schoolId').html(data);
		}
	});
};

function school_major() {
	var schoolId = $("#schoolId").val();
	$.ajax({
		type : 'POST',
		url : "/zk/show_data_jsp/school_major.jsp",
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		data : {
			"schoolId" : schoolId,
			"date" : new Date()
		},
		success : function(data) {
			$('#majorId').html(data);
		}
	});
};