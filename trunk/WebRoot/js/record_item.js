function add_recorditem() {
	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		$.jBox("iframe:finance_jsp/add_recorditem.jsp", {
			title : "添加项目",
			width : 430,
			height : 300,
			top : '10%',
			draggable : false,
			buttons : {
				'取消' : false
			}
		});
	} else {
		$.jBox("iframe:finance_jsp/add_recorditem.jsp", {
			title : "添加项目",
			width : 430,
			height : 300,
			top : '10%',
			buttons : {
				'取消' : false
			}
		});
	}
};
function update_recorditem(id) {
	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		$.jBox(
				"iframe:updateBeforeRecordItem?id=" + id + "&date="
						+ new Date(), {
					title : "编辑项目",
					width : 430,
					height : 300,
					top : '10%',
					draggable : false,
					buttons : {
						'取消' : false
					}
				});
	} else {
		$.jBox(
				"iframe:updateBeforeRecordItem?id=" + id + "&date="
						+ new Date(), {
					title : "编辑项目",
					width : 430,
					height : 300,
					top : '10%',
					buttons : {
						'取消' : false
					}
				});
	}
};

function delete_recorditem(id) {
	var submit = function(v, h, f) {
		if (v == 'ok') {
			$.jBox.tip("正在删除数据...", 'loading');
			// 模拟2秒后完成操作
			$.ajax({
				type : "get",
				url : "/zk/deleteRecordItem",
				data : {
					"id" : id,
					"date" : new Date()
				},
				success : function(data) {
					window.setTimeout(function() {
						$.jBox.tip(data, 'success');
					}, 1000);
					refresh_recorditem();
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
	var name = $("#name").val();
	DatagridFactory.createDatagrid({
		type : "Variable",
		id : '#recorditem_data',
		action : '/zk/getDatasRecordItem',
		data : {
			"name" : name
		},
		columns : [ [
				{
					field : 'id',
					title : 'ID',
					width : 100
				},
				{
					field : 'name',
					title : '姓名',
					width : 100
				},
				{
					field : 'method',
					title : '操作',
					formatter : function(val, rowData, rowIndex) {
						return "<a href=javascript:delete_recorditem("
								+ rowData.id
								+ ");>删除</a> <a href=javascript:update_recorditem("
								+ rowData.id + ");>修改</a>";
					},
					width : 100
				} ] ],
		update : function(rowIndex, rowData) {
			update_recorditem(rowData.id);
		}
	});
};