function getInfoByExamNumber() {
	var examNumber = $("#examNumber").val();
	$.jBox.tip("读取中...", 'loading');
	$.ajax({
		type : "post",
		dataType : "xml",
		url : "getXmlSC",
		data : {
			"examNumber" : examNumber,
			"date" : new Date()
		},
		success : function(data, textStatus){
			var stuInfo = $(data).find("stuInfo");
			var examInfo = $(data).find("examInfo");
			
			var status = "";
			var canSubmit = true;
			var isPay = $(data).find("isPay").text();//是否缴费
			var sStatus = $(data).find("status").text();//是否在籍
			if(isPay == "true") {
				status += "<h4>此同学已缴纳报考费用。</h4>";
			} else if(isPay == "false" && login_user.level == 1){
				status += "<h4>此同学没有缴纳报考费用。</h4>";
			} else if(isPay == "false" && login_user.level != 1){
				status += "<h4>此同学没有缴纳报考费用。</h4>";
				canSubmit = false;
			}
			if(sStatus == "true") {
				status += "<h4>此同学在籍。</h4>";
			} else {
				status += "<h4>此同学不在籍。</h4>";
				canSubmit = false;
			}
			$("#submit").attr("disabled",!canSubmit);
			$("#status").html(status);
			
			//下面是将学生信息填入表格
			$("#name").val($(stuInfo).find("name").text());
			$("#sex").val($(stuInfo).find("sex").text());
			$("#idCard").val($(stuInfo).find("idCard").text());
			$("#post_idCard").val($(stuInfo).find("idCard").text());
			$("#studyForm").val($(stuInfo).find("studyForm").text());
			$("#postCode").val($(stuInfo).find("postCode").text());
			$("#address").val($(stuInfo).find("address").text());
			$("#nationality").val($(stuInfo).find("nationality").text());
			$("#joinTime").val($(stuInfo).find("joinTime").text());
			$("#major").val($(stuInfo).find("major").text());
			$("#phone1").val($(stuInfo).find("phone1").text());
			$("#phone2").val($(stuInfo).find("phone2").text());
			$("#email").val($(stuInfo).find("email").text());
			$("#examDate").val($(stuInfo).find("examDate").text());
			$("#post_examDate").val($(stuInfo).find("examDate").text());
			
			//下面是将考试信息填入表格
			$(examInfo).find("day").each( function(index, domEle) { 
				var unit1 = $(domEle).find("unit1").text();
				var unit2 = $(domEle).find("unit2").text();
				var day = index+1;
				var t1 = "#unit" + (day*2-1);
				var t2 = "#unit" + (day*2);
				$(t1).html(unit1);
				$(t2).html(unit2);
			});
			$.jBox.tip('已完成。', 'success');
		}
	});
};
function getInfoByIdCard() {
	var idCard = $("#idCard").val();
	$.jBox.tip("读取中...", 'loading');
	$.ajax({
		type : "post",
		dataType : "xml",
		url : "getXmlSC",
		data : {
			"idCard" : idCard,
			"date" : new Date()
		},
		success : function(data, textStatus){
			var stuInfo = $(data).find("stuInfo");
			var examInfo = $(data).find("examInfo");
			
			var status = "";
			var canSubmit = true;
			var isPay = $(data).find("isPay").text();//是否缴费
			var sStatus = $(data).find("status").text();//是否在籍
		
			if(isPay == "true") {
				status += "<h4>此同学已缴纳报考费用。</h4>";
			} else if(isPay == "false" && login_user.level == 1){
				status += "<h4>此同学没有缴纳报考费用。</h4>";
			} else if(isPay == "false" && login_user.level != 1){
				status += "<h4>此同学没有缴纳报考费用。</h4>";
				canSubmit = false;
			}
			if(sStatus == "true") {
				status += "<h4>此同学在籍。</h4>";
			} else {
				status += "<h4>此同学不在籍。</h4>";
				canSubmit = false;
			}
			$("#submit").attr("disabled",!canSubmit);
			
			$("#status").html(status);
			
			//下面是将学生信息填入表格
			$("#name").val($(stuInfo).find("name").text());
			$("#sex").val($(stuInfo).find("sex").text());
			$("#idCard").val($(stuInfo).find("idCard").text());
			$("#post_idCard").val($(stuInfo).find("idCard").text());
			$("#studyForm").val($(stuInfo).find("studyForm").text());
			$("#postCode").val($(stuInfo).find("postCode").text());
			$("#address").val($(stuInfo).find("address").text());
			$("#nationality").val($(stuInfo).find("nationality").text());
			$("#joinTime").val($(stuInfo).find("joinTime").text());
			$("#major").val($(stuInfo).find("major").text());
			$("#phone1").val($(stuInfo).find("phone1").text());
			$("#phone2").val($(stuInfo).find("phone2").text());
			$("#email").val($(stuInfo).find("email").text());
			$("#examDate").val($(stuInfo).find("examDate").text());
			$("#post_examDate").val($(stuInfo).find("examDate").text());
			
			//下面是将考试信息填入表格
			$(examInfo).find("day").each( function(index, domEle) { 
				var unit1 = $(domEle).find("unit1").text();
				var unit2 = $(domEle).find("unit2").text();
				var day = index+1;
				var t1 = "#unit" + (day*2-1);
				var t2 = "#unit" + (day*2);
				$(t1).html(unit1);
				$(t2).html(unit2);
			});
			$.jBox.tip('已完成。', 'success');
		}
	});
};
function rechoose(){
	var stu_name = $("#name").val();
	if(stu_name=="" || stu_name == null){
		$.jBox.error('必须查询出一个学生', '错误');
	} else {
		var idCard = $("#idCard").val();
		$.jBox.tip("重置中...", 'loading');
		$.ajax({
			type : "post",
			url : "deleteStuAllSC",
			dataType: "json",
			data : {
				"idCard" : idCard,
				"d" : new Date().getTime()
			},
			success : function(data){
				getInfoByIdCard();
				$.jBox.tip('已完成。', 'success');
			},
			error : function(jqXHR, textStatus, errorThrown){
				$.jBox.tip('失败！', 'success');
			}
		});
	}
};