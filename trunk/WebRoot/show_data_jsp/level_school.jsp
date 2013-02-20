<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhnjy.util.db.SchoolDB" %>
<%@ page import="com.zhnjy.entity.School" %>
	<option value="">全部</option>
<%
	String level = request.getParameter("level");
	List list = null;
	if(level.equals("zk")) {
		list = SchoolDB.getzSchoolList();
	} else if(level.equals("bk")){
		list = SchoolDB.getbSchoolList();
	}
	for(int i = 0; i < list.size(); i++) {
		School school = (School)list.get(i);
%>
	<option value=<%=school.getId() %> <%if(i==0) out.println("selected"); %>><%=school.getName() %></option>
<%
	}
%>