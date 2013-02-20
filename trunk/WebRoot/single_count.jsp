<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>
<script type="text/javascript">
	setTimeout(refresh_singlecount(), 20);
</script>
</head>

<body>
	<div class="easyui-accordion" id="accordion"
		style="background: #fafafa;">
		<%
			String _unitNum = (String) request.getAttribute("unit");
			int unitNum = Integer.parseInt(_unitNum);
		%>
		<div title="查询选项" id="menu">
			<div class="item">
				单元：<select id="unitId" name="unitId" onchange="unit_course();">
					<option value="">全部</option>
					<%
						for (int i = 1; i <= unitNum; i++) {
					%>
					<option value="<%=i%>">
						第<%=i%>单元
					</option>
					<%
						}
					%>
				</select>
			</div>
			<div class="item">
				课程：<select id="courseId" name="courseId" disabled="disabled">
					<option value="">--请先选择单元--</option>
				</select>
			</div>
			<input type="button" onclick="refresh();" class="input_button"
				value="查询" />
		</div>
	</div>
	<div id="singlecount_data"></div>
</body>
</html>
