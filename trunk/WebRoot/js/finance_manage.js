function pay(idCard) {
	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		$.jBox("iframe:finance_jsp/pay.jsp?idCard=" + idCard, {
			title : "交费",
			width : 430,
			height : 630,
			top : '5%',
			draggable : false,
			buttons : {
				'取消' : false
			}
		});
	} else {
		$.jBox("iframe:finance_jsp/pay.jsp?idCard=" + idCard, {
			title : "交费",
			width : 430,
			height : 630,
			top : '5%',
			buttons : {
				'取消' : false
			}
		});
	}
};
function refresh() {
	var idCard = $("#idCard").val();
	var name = $("#name").val();
	var number = $("#number").val();
	var nationality = $("#nationality").val();
	var sex = $("#sex").val();
	var phone1 = $("#phone1").val();
	var phone2 = $("#phone2").val();
	var siteId = $("#siteId").val();
	var studyLevel = $("#studyLevel").val();
	var bSchoolId = $("#bSchool").val();
	var bMajorId = $("#bMajor").val();
	var bExamNumber = $("#bExamNumber").val();
	var bStudyType = $("#bStudyType").val();
	var bStudyForm = $("#bStudyForm").val();
	var bJoinTime = $("#bJoinTime").val();
	var bStatus = $("#bStatus").val();
	var zSchoolId = $("#zSchool").val();
	var zMajorId = $("#zMajor").val();
	var zExamNumber = $("#zExamNumber").val();
	var zStudyForm = $("#zStudyForm").val();
	var zStudyType = $("#zStudyType").val();
	var zJoinTime = $("#zJoinTime").val();
	var zStatus = $("#zStatus").val();
	DatagridFactory.createDatagrid({
		type : "Pagination",
		id : "#finance_data",
		action : "/zk/getStudentsR",
		rownumbers : true,
		data : {
			"idCard" : idCard,
			"name" : name,
			"number" : number,
			"nationality" : nationality,
			"sex" : sex,
			"phone1" : phone1,
			"phone2" : phone2,
			"siteId" : siteId,
 			"studyLevel" : studyLevel,
			"bSchoolId" : bSchoolId,
			"bMajorId" : bMajorId,
			"bExamNumber" : bExamNumber,
			"bStudyForm" : bStudyForm,
			"bStudyType" : bStudyType,
			"bJoinTime" : bJoinTime,
			"bStatus" : bStatus,
			"zSchoolId" : zSchoolId,
			"zMajorId" : zMajorId,
			"zExamNumber" : zExamNumber,
			"zStudyForm" : zStudyForm,
			"zStudyType" : zStudyType,
			"zJoinTime" : zJoinTime,
			"zStatus" : zStatus,
			"d" : new Date().getTime()
		},
		columns : [ [ {
			field : "idCard",
			title : "身份证号",
			align : 'center',
			width : 150
		}, {
			field : "name",
			title : "姓名",
			width : 60
		}, {
			field : "number",
			title : "推荐人",
			hidden : true,
			width : 60,
		}, {
			field : "sex",
			title : "性别",
			hidden : true,
			width : 30
		}, {
			field : "siteName",
			title : "站点",
			width : 60
		}, {
			field : "phone1",
			title : "主联系电话",
			hidden : true,
			width : 100
		}, {
			field : "bSchoolName",
			title : "本科学校",
			width : 100
		}, {
			field : "bMajorName",
			title : "本科专业",
			width : 100
		}, {
			field : "bExamNumber",
			title : "本科考籍号",
			width : 80
		}, {
			field : "bJoinTime",
			title : "本科入学时间",
			hidden : true,
			width : 80
		}, {
			field : "zSchoolName",
			title : "专科学校",
			width : 100
		}, {
			field : "zMajorName",
			title : "专科专业",
			width : 100
		}, {
			field : "zExamNumber",
			title : "专科考籍号",
			width : 80
		}, {
			field : "zJoinTime",
			title : "专科入学时间",
			hidden : true,
			width : 80
		}, {
			field : "remark",
			title : "备注",
			hidden : true,
			width : 100
		}, {
			field : "method",
			title : "操作",
			align : 'center',
			width : 100,
			formatter : function(val, rowData, rowIndex) {
				return "<a href=javascript:pay('"+rowData.idCard+"');>交费</a>";
			}
		}
		] ],
		update : function(rowIndex, rowData) {
			pay(rowData.idCard);
		}
	});
};
function level_school() {
	var studyLevel = $("#studyLevel").val();//获取level
	/**
	 * 这三个变量是为了后面实现等级选择的，
	 * 因为没选的时候是没有选项的，默认清空，
	 * 如果选择了某个等级，其中的形式类型状态都要显示，
	 * 就需要用到以下三个变量。
	 */
	var studyForm = "<option value=''>无</option>"
					+ "<option value='全日制' >全日制</option>"
					+ "<option value='业余' selected>业余</option>";
	var studyType = "<option value='无'>无</option>"
					+ "<option value='自考' selected>自考</option>"
					+ "<option value='成教'>成教</option>"
					+ "<option value='电大'>电大</option>"
					+ "<option value='远程教育'>远程教育</option>";
	var status = "<option value='待注册'>待注册</option>"
					+ "<option value='无'>无</option>"
					+ "<option value='在籍' selected>在籍</option>"
					+ "<option value='毕业'>毕业</option>"
					+ "<option value='休学'>休学</option>"
					+ "<option value='退学1'>退学1</option>"
					+ "<option value='退学2'>退学2</option>"
					+ "<option value='开除'>开除</option>";
	/**
	 * 下面是处理选择了level之后的school下拉列表的显示
	 * 选择 全部 的时候，所有选项变为最原始状态，并且全部不可选
	 * 选择 专科或者本科 的时候，选择的变为可选，另一个变为最原始状态
	 * 选择 套读 的时候，所有等级学校变为可选
	 */
	if(studyLevel=="") {
		$('#bSchool').attr("disabled","disabled")
			.html("<option value=''>全部</option>");
		$('#bMajor').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		$('#bStudyForm').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		$('#bStudyType').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		$('#bExamNumber').attr("disabled","disabled").val("");
		$('#bJoinTime').attr("disabled","disabled").val("");
		$('#bStatus').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		
		$('#zSchool').attr("disabled","disabled")
			.html("<option value=''>全部</option>");
		$('#zMajor').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		$('#zStudyForm').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		$('#zStudyType').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		$('#zExamNumber').attr("disabled","disabled").val("");
		$('#zJoinTime').attr("disabled","disabled").val("");
		$('#zStatus').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		
		$('#zInfo').removeAttr("checked");
		$('#bInfo').removeAttr("checked");
	} else if(studyLevel=="zk") {
		$('#bSchool').attr("disabled","disabled")
			.html("<option value=''>全部</option>");
		$('#bMajor').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		$('#bStudyForm').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		$('#bStudyType').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		$('#bExamNumber').attr("disabled","disabled").val("");
		$('#bJoinTime').attr("disabled","disabled").val("");
		$('#bStatus').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		
		$('#zSchool').removeAttr("disabled");
		$('#zMajor').attr("disabled","disabled");
		$('#zStudyForm').removeAttr("disabled")
			.html(studyForm);
		$('#zStudyType').removeAttr("disabled")
			.html(studyType);
		$('#zExamNumber').removeAttr("disabled").val("");
		$('#zJoinTime').removeAttr("disabled").val("");
		$('#zStatus').removeAttr("disabled")
			.html(status);
		
		$('#zInfo').attr("checked","checked");
		$('#bInfo').removeAttr("checked");
	} else if(studyLevel=="bk") {
		$('#bSchool').removeAttr("disabled");
		$('#bMajor').attr("disabled","disabled");
		$('#bStudyForm').removeAttr("disabled")
			.html(studyForm);
		$('#bStudyType').removeAttr("disabled")
			.html(studyType);
		$('#bExamNumber').removeAttr("disabled").val("");
		$('#bJoinTime').removeAttr("disabled").val("");
		$('#bStatus').removeAttr("disabled")
			.html(status);
		
		$('#zSchool').attr("disabled","disabled")
			.html("<option value=''>全部</option>");
		$('#zMajor').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		$('#zStudyForm').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		$('#zStudyType').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		$('#zExamNumber').attr("disabled","disabled").val("");
		$('#zJoinTime').attr("disabled","disabled").val("");
		$('#zStatus').attr("disabled","disabled")
			.html("<option value=''>--请先选择学校--</option>");
		
		$('#bInfo').attr("checked","checked");
		$('#zInfo').removeAttr("checked");
	} else if(studyLevel=="td") {
		$('#bSchool').removeAttr("disabled");
		$('#bMajor').attr("disabled","disabled");
		$('#bStudyForm').removeAttr("disabled")
			.html(studyForm);
		$('#bStudyType').removeAttr("disabled")
			.html(studyType);
		$('#bExamNumber').removeAttr("disabled").val("");
		$('#bJoinTime').removeAttr("disabled").val("");
		$('#bStatus').removeAttr("disabled")
			.html(status);
		
		$('#zSchool').removeAttr("disabled");
		$('#zMajor').attr("disabled","disabled");
		$('#zStudyForm').removeAttr("disabled")
			.html(studyForm);
		$('#zStudyType').removeAttr("disabled")
			.html(studyType);
		$('#zExamNumber').removeAttr("disabled").val("");
		$('#zJoinTime').removeAttr("disabled").val("");
		$('#zStatus').removeAttr("disabled")
			.html(status);
		
		$('#zInfo').attr("checked","checked");
		$('#bInfo').attr("checked","checked");
	}
	/**
	 * 以下为选择专科本科套读三个状态时的数据获取
	 * 专科和本科的时候直接发送获取
	 * 套读的时候生成一个临时变量来分两次获取数据
	 */
	if(studyLevel=="zk") {
		$.ajax({
			type : 'POST',
			url : "/zk/show_data_jsp/level_school.jsp",
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			data : {
				"level" : studyLevel,
				"date" : new Date()
			},
			success : function(data) {
				$('#zSchool').html(data);
			}
		});
	} else if(studyLevel=="bk") {
		$.ajax({
			type : 'POST',
			url : "/zk/show_data_jsp/level_school.jsp",
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			data : {
				"level" : studyLevel,
				"date" : new Date()
			},
			success : function(data) {
				$('#bSchool').html(data);
			}
		});
	} else if(studyLevel=="td") {
		var level = "zk";
		$.ajax({
			type : 'POST',
			url : "/zk/show_data_jsp/level_school.jsp",
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			data : {
				"level" : level,
				"date" : new Date()
			},
			success : function(data) {
				$('#zSchool').html(data);
			}
		});
		level = "bk";
		$.ajax({
			type : 'POST',
			url : "/zk/show_data_jsp/level_school.jsp",
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			data : {
				"level" : level,
				"date" : new Date()
			},
			success : function(data) {
				$('#bSchool').html(data);
			}
		});
	}
};
function bschool_major() {
	var schoolId = $("#bSchool").val();
	$.ajax({
		type : 'POST',
		url : "/zk/show_data_jsp/school_major.jsp",
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		data : {
			"schoolId" : schoolId,
			"date" : new Date()
		},
		success : function(data) {
			$('#bMajor').html(data);
			$('#bMajor').removeAttr("disabled");
		}
	});
};
function zschool_major() {
	var schoolId = $("#zSchool").val();
	$.ajax({
		type : 'POST',
		url : "/zk/show_data_jsp/school_major.jsp",
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		data : {
			"schoolId" : schoolId,
			"date" : new Date()
		},
		success : function(data) {
			$('#zMajor').html(data);
			$('#zMajor').removeAttr("disabled");
		}
	});
};