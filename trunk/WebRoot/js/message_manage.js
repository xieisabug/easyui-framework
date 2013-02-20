function add_message() {
	window.open("/zk/manage_jsp/add_message.jsp");
};

function update_message(id) {
	window.open("/zk/manage_jsp/update_message.jsp?id=" + id);
};

function delete_message(id) {
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	        $.jBox.tip("正在删除数据...", 'loading');
	        // 模拟2秒后完成操作
	        $.ajax({
	    		type : "get",
	    		url : "/zk/deleteMessage",
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
	var url = "/zk/getDatasMessage?page=1&d=" + new Date().getTime();
	$("#dataFrame").attr("src", url);
};