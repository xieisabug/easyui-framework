function add_admin() {
	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		$.jBox("iframe:manage_jsp/add_user.jsp", {
			title : "添加用户",
			width : 430,
			height : 400,
			top : '10%',
			draggable : false,
			buttons : {
				'取消' : false
			}
		});
	} else {
		$.jBox("iframe:manage_jsp/add_user.jsp", {
			title : "添加用户",
			width : 430,
			height : 400,
			top : '10%',
			buttons : {
				'取消' : false
			}
		});
	}
};
function update_admin(id) {
	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		$.jBox("iframe:manage_jsp/update_user.jsp?id=" + id, {
			title : "编辑用户",
			width : 430,
			height : 400,
			top : '10%',
			draggable : false,
			buttons : {
				'取消' : false
			}
		});
	} else {
		$.jBox("iframe:manage_jsp/update_user.jsp?id=" + id, {
			title : "编辑用户",
			width : 430,
			height : 400,
			top : '10%',
			buttons : {
				'取消' : false
			}
		});
	}
};

function delete_admin(id) {
	var submit = function(v, h, f) {
		if (v == 'ok') {
			$.jBox.tip("正在删除数据...", 'loading');
			// 模拟2秒后完成操作
			$.ajax({
				type : "get",
				url : "/zk/deleteAdmin",
				data : {
					"id" : id,
					"date" : new Date()
				},
				success : function(data) {
					window.setTimeout(function() {
						$.jBox.tip(data, 'success');
					}, 1000);
					refresh();
				}
			});
		} else if (v == 'cancel') {
			// 取消
		}
		return true; // close
	};

	$.jBox.confirm("确定要删除数据吗？", "提示", submit);

};

function refresh() {
	DatagridFactory.createDatagrid({
		type : "Variable",
		id : "#admin_data",
		action : "/zk/getDatasAdmin",
		data : {
			"d" : new Date().getTime()
		},
		columns : [ [ {
			field : "id",
			title : "用户编号",
			align : 'center',
			width : 60
		}, {
			field : "userName",
			title : "用户名",
			width : 100
		}, {
			field : "password",
			title : "密码",
			width : 50,
			formatter : function(val, rowData, rowIndex) {
				return "******";
			}
		}, {
			field : "levelName",
			title : "用户等级",
			width : 100
		}, {
			field : "siteName",
			title : "站点",
			width : 100
		}, {
			field : "remark",
			title : "备注",
			width : 100
		}, {
			field : "method",
			title : "操作",
			align : 'center',
			width : 100,
			formatter : function(val, rowData, rowIndex) {
				if(rowData.levelName == "超级管理员") {
					return "";
				}
				return "<a href='javascript:delete_admin("+rowData.id+");'>删除</a> <a href='javascript:update_admin("+rowData.id+");'>修改</a>";
			}
		}
		] ],
		update : function(rowIndex, rowData) {
			if(rowData.levelName != "超级管理员") update_admin(rowData.id);
		}
	});
};