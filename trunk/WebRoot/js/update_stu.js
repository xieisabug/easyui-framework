function bschool_major() {
	var schoolId = $("#bSchool").val();
	$.ajax({
		type : 'POST',
		url : "/zk/show_data_jsp/school_major.jsp",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"schoolId" : schoolId
		},
		success : function(data) {
			$('#bMajor').html(data);
		}
	});
};
function zschool_major() {
	var schoolId = $("#zSchool").val();
	$.ajax({
		type : 'POST',
		url : "/zk/show_data_jsp/school_major.jsp",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"schoolId" : schoolId
		},
		success : function(data) {
			$('#zMajor').html(data);
		}
	});
};