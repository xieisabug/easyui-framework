function add_major() {

	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		$.jBox("iframe:manage_jsp/add_major.jsp", {
			title : "添加专业",
			width : 430,
			height : 300,
			top : '10%',
			draggable : false,
			buttons : {
				'取消' : false
			}
		});
	} else {
		$.jBox("iframe:manage_jsp/add_major.jsp", {
			title : "添加专业",
			width : 430,
			height : 300,
			top : '10%',
			buttons : {
				'取消' : false
			}
		});
	}
};

function update_major(id){
	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		$.jBox("iframe:manage_jsp/update_major.jsp?id=" + id, {
			title : "编辑专业",
			width : 430,
			height : 300,
			top : '10%',
			draggable : false,
			buttons : {
				'取消' : false
			}
		});
	} else {
		$.jBox("iframe:manage_jsp/update_major.jsp?id=" + id, {
			title : "编辑专业",
			width : 430,
			height : 300,
			top : '10%',
			buttons : {
				'取消' : false
			}
		});
	}
};

function set_course(id){
	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		$.jBox("iframe:/zk/queryMC?majorId=" + id + "&date=" + new Date(), {
			title : "设置课程",
			width : 600,
			height : 400,
			top : '10%',
			draggable : false,
			buttons : {
				'取消' : false
			}
		});
	} else {
		$.jBox("iframe:/zk/queryMC?majorId=" + id + "&date=" + new Date(), {
			title : "设置课程",
			width : 600,
			height : 400,
			top : '10%',
			buttons : {
				'取消' : false
			}
		});
	}
};

function delete_major(id) {
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	        $.jBox.tip("正在删除数据...", 'loading');
	        // 模拟2秒后完成操作
	        $.ajax({
	    		type : "get",
	    		url : "/zk/deleteMajor",
	    		data : {
	    			"id" : id,
	    			"date" : new Date()
	    		},
	    		success : function(data) {
	    			window.setTimeout(function () { $.jBox.tip(data, 'success'); }, 1000);
	    			refresh();
	    		}
	    	});
	    }
	    else if (v == 'cancel') {
	        // 取消
	    }
	    return true; //close
	};

	$.jBox.confirm("确定要删除数据吗？", "提示", submit);
	
};

function refresh() {
	var name = $("#name").val();
	var number = $("#number").val();
	var level = $("#level").val();
	var url = "/zk/getDatasMajor?name=" + name + "&number=" + number
		+ "&level=" + level + "&page=1&d=" + new Date().getTime();
	$("#dataFrame").attr("src", url);
};