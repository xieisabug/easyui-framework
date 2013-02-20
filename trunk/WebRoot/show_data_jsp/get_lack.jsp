<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.zhnjy.util.db.StudentDB"%>
<%@ page import="com.zhnjy.entity.Student"%>
<%@ page import="com.zhnjy.util.db.SchoolDB"%>
<%@ page import="com.zhnjy.entity.School"%>
<%@ page import="com.zhnjy.util.db.RecordItemDB"%>
<%@ page import="com.zhnjy.util.db.RecordDB"%>
<%@ page import="com.zhnjy.entity.Record"%>
<%@ page import="com.zhnjy.util.db.MajorDB"%>
<%@ page import="com.zhnjy.entity.Major"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css"
	type="text/css"></link>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
</head>

<body>
	<%
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String idCard = request.getParameter("idCard");
		String name = request.getParameter("name");
		String number = request.getParameter("number");
		String nationality = request.getParameter("nationality");
		String sex = request.getParameter("sex");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String siteId = request.getParameter("siteId");
		String studyLevel = request.getParameter("studyLevel");
		String bSchoolId = request.getParameter("bSchoolId");
		String bMajorId = request.getParameter("bMajorId");
		String bExamNumber = request.getParameter("bExamNumber");
		String bStudyForm = request.getParameter("bStudyForm");
		String bStudyType = request.getParameter("bStudyType");
		String bJoinTime = request.getParameter("bJoinTime");
		String bStatus = request.getParameter("bStatus");
		String zSchoolId = request.getParameter("zSchoolId");
		String zMajorId = request.getParameter("zMajorId");
		String zExamNumber = request.getParameter("zExamNumber");
		String zStudyForm = request.getParameter("zStudyForm");
		String zStudyType = request.getParameter("zStudyType");
		String zJoinTime = request.getParameter("zJoinTime");
		String zStatus = request.getParameter("zStatus");
		String displayValue = request.getParameter("displayValue");
		String displayName = request.getParameter("displayName");
		String recordItemId = request.getParameter("recordItemId");
		String yearth = request.getParameter("yearth");
		String remark = request.getParameter("remark");
		String[] columnValue = displayValue.split(" ");
		String[] columnName = displayName.split(" ");
	%>
	<table class="easyui-datagrid" singleSelect="true">
		<thead>
			<tr>
				<%
					for(int i = 0; i < columnValue.length; i++) {
						if(columnValue[i].equals("phone")) {
				%>
					<th data-options="field:'phone1'">主联系电话</th>
				<%
						} else if(columnValue[i].equals("others")){
				%>
					<th data-options="field:'phone2'">推荐人</th>
					<th data-options="field:'sex'">性别</th>
					<th data-options="field:'nationality'">民族</th>
				<%
						} else if(columnValue[i].equals("bInfo")) {
				%>
					<th data-options="field:'bSchool'">本科学校</th>
					<th data-options="field:'bMajor'">本科专业</th>
					<th data-options="field:'bExamNumber'">本科考籍号</th>
				<%
						} else if(columnValue[i].equals("zInfo")) {
				%>
					<th data-options="field:'zSchool'">专科学校</th>
					<th data-options="field:'zMajor'">专科专业</th>
					<th data-options="field:'zExamNumber'">专科考籍号</th>
				<%
						} else {
				%>
					<th data-options="field:'<%=columnValue[i]%>'"><%=columnName[i]%></th>
				<%
						}
					}
				%>
			</tr>
		</thead>
		<tbody>
			<%
				List students = StudentDB.getList(idCard, name, number,
						nationality, sex, phone1, phone2, siteId, studyLevel, 
						bSchoolId, bMajorId, bExamNumber, bStudyForm,
						bStudyType, bStatus, bJoinTime, zSchoolId, zMajorId,
						zExamNumber, zStudyForm, zStudyType, zStatus, zJoinTime);
				
				for (int i = 0; i < students.size(); i++) {
					Student student = (Student) students.get(i);
					if(!RecordDB.isPay(student.getIdCard(), 
					RecordItemDB.getNameById(Integer.parseInt(recordItemId)), yearth)){
						out.println("<tr>");
						for(int j = 0; j < columnValue.length; j++) {
							if(columnValue[j].equals("idCard"))
								out.println("<td>" + student.getIdCard() + "</td>");
							else if(columnValue[j].equals("name"))
								out.println("<td>" + student.getName() + "</td>");
							else if(columnValue[j].equals("number"))
								out.println("<td>" + student.getNumber() + "</td>");
							else if(columnValue[j].equals("others")) {
								out.println("<td>" + student.getPhone2() + "</td>");
								out.println("<td>" + student.getSex() + "</td>");
								out.println("<td>" + student.getNationality() + "</td>");
							} else if(columnValue[j].equals("phone")) {
								out.println("<td>" + student.getPhone1() + "</td>");
							} else if(columnValue[j].equals("zInfo")) {
								if(student.getzSchoolId() > 0) {
									School school = SchoolDB.getSchool(student.getzSchoolId());
									out.println("<td>" + school.getName() + "</td>");
								} else
									out.println("<td>无</td>");
								if(student.getzMajorId() > 0) {
									Major major = MajorDB.getMajor(student.getzMajorId());
									out.println("<td>" + major.getName() + "</td>");
								} else
									out.println("<td>无</td>");
								out.println("<td>" + student.getzExamNumber() + "</td>");
							} else if(columnValue[j].equals("bInfo")) {
								if(student.getbSchoolId() > 0) {
									School school = SchoolDB.getSchool(student.getbSchoolId());
									out.println("<td>" + school.getName() + "</td>");
								} else
									out.println("<td>无</td>");
								if(student.getbMajorId() > 0) {
									Major major = MajorDB.getMajor(student.getbMajorId());
									out.println("<td>" + major.getName() + "</td>");
								} else
									out.println("<td>无</td>");
								out.println("<td>" + student.getbExamNumber() + "</td>");
							} else if(columnValue[j].equals("studyLevel")) {
								String level = student.getStudyLevel();
								if(level.equals("zk"))
									out.println("<td>专科</td>");
								else if(level.equals("bk"))
									out.println("<td>本科</td>");
								else if(level.equals("td"))
									out.println("<td>套读</td>");
							}
						}
						out.println("</tr>");
					}
				}
			%>
			
		</tbody>
	</table>
</body>
</html>
