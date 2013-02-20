function refresh() {
	DatagridFactory.createDatagrid({
		type : "Pagination",
		id : "#notchoose_data",
		action : "/zk/notChooseStuSC",
		data : {
			"d" : new Date().getTime(),
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
			width : 100
		}	] ]
	});
};