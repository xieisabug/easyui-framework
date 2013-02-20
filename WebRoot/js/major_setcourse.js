function delete_mc(courseId, majorId) {
	var submit = function(v, h, f) {
		if (v == 'ok') {
			$.jBox.tip("正在删除数据...", 'loading');
			// 模拟2秒后完成操作
			$.ajax({
				type : "get",
				url : "deleteMC",
				data : {
					"majorId" : majorId,
					"courseId" : courseId,
					"date" : new Date().getTime()
				},
				success : function(data) {
					window.setTimeout(function() {
						$.jBox.tip(data, 'success');
					}, 500);
					window.setTimeout(function() {
						location.href = "queryMC?majorId=" + majorId;
					}, 2000);
				}
			});
		} else if (v == 'cancel') {
			// 取消
		}
		return true; // close
	};

	$.jBox.confirm("确定要删除数据吗？", "提示", submit);

};