<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhnjy.util.db.SchoolMajorDB" %>
<%@ page import="com.zhnjy.entity.Major" %>
<%
	String _schoolId = request.getParameter("schoolId");
	if(_schoolId.equals("")) {
		out.print("<option value=''>无</option>");
	} else {
		int schoolId = Integer.parseInt(_schoolId);
		List majors = SchoolMajorDB.getMajorlist(schoolId);
		for(int i = 0; i < majors.size(); i++) {
			Major major = (Major)majors.get(i);
%>
	<option value="<%=major.getId() %>" <%if(i==0) out.println("selected"); %>><%=major.getName() %></option>
<%
		}
	
		if(majors.size()==0){
%>
	<option value="">无</option>
<%
		}
	}
%>