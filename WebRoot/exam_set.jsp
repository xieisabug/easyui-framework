<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet" href="/zk/css/form.css" type="text/css"></link>
<link rel="stylesheet" href="/zk/css/index.css" type="text/css"></link>
<style type="text/css">
p {
	font-size: 12px;
}
h2 {
	font-size: 1.5em;
}
</style>
</head>

<body bgcolor="#fafafa">
	<%
		Map properties = (Map)request.getAttribute("resultMap"); 
		String examDate = (String)properties.get("examDate");
		String _unitNum = (String)properties.get("unitNum");
		int unitNum = Integer.parseInt(_unitNum);
		request.setCharacterEncoding("utf-8");
	%>
	<div class="main_content">

		<div id="register_check_content">
			<h2 style="color: red;">下列设置为报考系统的全局设置，请勿轻易修改，以免造成数据丢失或者系统崩溃：</h2>
			<form name="examDate" action="examDateES">
				<p>
					考期设置：<input type="text" class="input_text" id="examDate"
						name="examDate" value="<%=examDate %>">
					<input type="submit" name="submit" class="input_button" value="修改">
				</p>
			</form>
			<form name="unitNum" action="unitNumES">
				<p>
					单元数设置：<input type="text" class="input_text" id="unitNum"
						name="unitNum" value="<%=unitNum %>"><span style="color: red;">(修改后需重新进入系统)</span>
					<input type="submit" name="submit" class="input_button" value="修改">
				</p>
			</form>
			<form name="unitTime" action="unitTimeES" method="post">
				<h3>单元时间设置</h3>
				<% 
					int dayNum = 0;
					if(unitNum % 2 == 0)
						dayNum = unitNum/2;
					else
						dayNum = (unitNum+1)/2;
					for(int i = 1; i <= dayNum; i++) {
				%>
				<div style="width: 436px; margin-top: 10px; ">
					<p style="text-align: left; font-weight: bold;">
						第<%=i %>天日期：<input type="text" class="input_text" id="day<%=i %>"
							name="day<%=i %>" value="<%=(String)properties.get("day" + i)%>">
					</p>
					<p>
						第<%=i*2-1 %>单元时间:<input type="text" class="input_text" id="unit<%=i*2-1 %>time"
							name="unit<%=i*2-1 %>time" value="<%=(String)properties.get("unit"+(i*2-1)+"time")%>">
						第<%=i*2 %>单元时间:<input type="text" class="input_text" id="unit<%=i*2 %>time"
							name="unit<%=i*2 %>time" value="<%=(String)properties.get("unit"+(i*2)+"time")%>">
					</p>
				</div>
				<%
					} 
				%>
				<input type="submit" id="submit" name="submit" value="确认设置"
					class="input_button">
			</form>
		</div>
	</div>

</body>
</html>
