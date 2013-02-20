function add_site() {

	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		$.jBox("iframe:manage_jsp/add_site.jsp", {
			title : "添加学校",
			width : 430,
			height : 270,
			top : '10%',
			draggable : false,
			buttons : {
				'取消' : false
			}
		});
	} else {
		$.jBox("iframe:manage_jsp/add_site.jsp", {
			title : "添加学校",
			width : 430,
			height : 270,
			top : '10%',
			buttons : {
				'取消' : false
			}
		});
	}
};

function update_site(id) {

	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
		$.jBox("iframe:manage_jsp/update_site.jsp?id=" + id, {
			title : "更新信息",
			width : 430,
			height : 270,
			top : '10%',
			draggable : false,
			buttons : {
				'取消' : false
			}
		});
	} else {
		$.jBox("iframe:manage_jsp/update_site.jsp?id=" + id, {
			title : "更新信息",
			width : 430,
			height : 270,
			top : '10%',
			buttons : {
				'取消' : false
			}
		});
	}
};

function delete_site(id) {
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	        $.jBox.tip("正在删除数据...", 'loading');
	        // 模拟2秒后完成操作
	        $.ajax({
	    		type : "get",
	    		url : "/zk/deleteSite",
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
	var url = "/zk/getDatasSite?page=1&d=" + new Date().getTime();
	$("#dataFrame").attr("src", url);
};