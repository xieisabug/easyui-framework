function delete_sm(schoolId, majorId) {
	var submit = function(v, h, f) {
		if (v == 'ok') {
			$.jBox.tip("正在删除数据...", 'loading');
			// 模拟2秒后完成操作
			$.ajax({
				type : "get",
				url : "deleteSM",
				data : {
					"majorId" : majorId,
					"schoolId" : schoolId,
					"date" : new Date().getTime()
				},
				success : function(data) {
					window.setTimeout(function() {
						$.jBox.tip(data, 'success');
					}, 500);
					window.setTimeout(function() {
						location.href = "querySM?schoolId=" + schoolId;
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