<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhnjy.util.db.MajorCourseDB" %>
<%@ page import="com.zhnjy.entity.Course" %>
<%
	String unitId = request.getParameter("unit");
	List list = MajorCourseDB.getCourseIdByUnit(Integer.parseInt(unitId));
	for(int i = 0; i < list.size(); i++) {
		Course course = (Course)list.get(i);
%>
	<option value="<%=course.getId()%>"><%=course.getName() %></option>
<%
	}
%>