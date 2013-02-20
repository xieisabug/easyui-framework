function add_course() {
	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		$.jBox("iframe:manage_jsp/add_course.jsp", {
			title : "添加课程",
			width : 430,
			height : 400,
			top : '10%',
			draggable : false,
			buttons : {
				'取消' : false
			}
		});
	} else {
		$.jBox("iframe:manage_jsp/add_course.jsp", {
			title : "添加课程",
			width : 430,
			height : 400,
			top : '10%',
			buttons : {
				'取消' : false
			}
		});
	}
};
function update_course(id) {
	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		$.jBox("iframe:manage_jsp/update_course.jsp?id=" + id, {
			title : "编辑课程",
			width : 430,
			height : 400,
			top : '10%',
			draggable : false,
			buttons : {
				'取消' : function() {
					window.parent.location.reload(true);
				}
			}
		});
	} else {
		$.jBox("iframe:manage_jsp/update_course.jsp?id=" + id, {
			title : "编辑课程",
			width : 430,
			height : 400,
			top : '10%',
			buttons : {
				'取消' : function() {
					window.parent.location.reload(true);
				}
			}
		});
	}
};

function delete_course(id) {
	var submit = function(v, h, f) {
		if (v == 'ok') {
			// $.jBox.tip("正在删除数据...", 'loading');
			// 模拟2秒后完成操作
			$.ajax({
				type : "get",
				url : "/zk/deleteCourse",
				data : {
					"id" : id,
					"date" : new Date()
				},
				success : function(data) {
					// window.setTimeout(function () { $.jBox.tip(data,
					// 'success'); }, 1000);
					window.location.reload(true);
				}
			});
		} else if (v == 'cancel') {
			// 取消
			window.location.reload(true);
		}
		return true; // close
	};

	$.jBox.confirm("确定要删除数据吗？", "提示", submit);

};

function refresh() {
	var name = $("#name").val();
	var number = $("#number").val();
	var level = $("#level").val();
	DatagridFactory.createDatagrid({
		type : "Pagination",
		id : "#course_data",
		action : "/zk/getDatasCourse",
		data : {
			"name" : name,
			"number" : number,
			"level" : level,
			"d" : new Date().getTime()
		},
		columns : [ [ {
			field : "number",
			title : "课程编号",
			align : 'center',
			width : 100
		}, {
			field : "name",
			title : "课程名",
			width : 150
		}, {
			field : "level",
			title : "课程等级",
			width : 60,
			formatter : function(val, rowData, rowIndex) {
				if(val == "zk"){
					return "专科";
				} else if(val == "bk") {
					return "本科";
				}
			}
		}, {
			field : "remark",
			title : "备注",
			width : 100
		}, {
			field : "id",
			title : "操作",
			align : 'center',
			width : 100,
			formatter : function(val, rowData, rowIndex) {
				return "<a href='javascript:delete_course("+val+");'>删除</a> <a href='javascript:update_course("+val+");'>修改</a>";
			}
		}
		] ],
		update : function(rowIndex, rowData) {
			update_course(rowData.id);
		}
	});
};