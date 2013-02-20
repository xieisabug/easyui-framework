<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhnjy.util.db.SchoolDB"%>
<%@ page import="com.zhnjy.entity.School"%>
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

	<div class="main_content">

		<div id="register_check_content">
			<h2>请选择文件核对的范围：</h2>
			<form action="checkRegister" method="post">
				<p>
					学校：<select name="schoolId" id="schoolId">
					<%
						List schools = SchoolDB.getAllSchoolList();
						for(int i = 0; i < schools.size(); i++) {
							School school = (School)schools.get(i);
							String level = school.getLevel();
					%>
					<option value="<%=school.getId()%>">
						<%=school.getName() %>(<%if(level.equals("zk")) out.println("专"); else out.println("本");%>)
					</option>
					<%
						} 
					%>
					</select>
				</p>
				<p>
					时间段<span>(格式必须为yyyy-mm-dd，如：2012-10-10)</span>： <br>
					开始：<input type="text" class="input_text" id="startTime"
						name="startTime">
					结束：<input type="text" class="input_text" id="endTime"
						name="endTime">
				</p>
				<div id="file">
					<h2>
						请找到要核对的文件：<input type="file" id="file" name="file">
					</h2>
				</div>
				<input type="submit" id="submit" name="submit" value="检查开始"
					class="input_button">
			</form>
		</div>
	</div>

</body>
</html>
