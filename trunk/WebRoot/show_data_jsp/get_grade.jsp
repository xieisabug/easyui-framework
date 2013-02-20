<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.zhnjy.entity.Grade"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="/zk/jBox/Skins2/Gray/jbox.css" type="text/css"></link>
<link rel="stylesheet" href="/zk/css/get.css" type="text/css"></link>
</head>

<body>
	<table class="easyui-datagrid" singleSelect="true" showFooter="true">
		<thead>
			<tr>
				<th data-options="field:'id'">id</th>
				<th data-options="field:'stuName'">学生姓名</th>
				<th data-options="field:'stuIdCard'">学生身份证号码</th>
				<th data-options="field:'courseName'">课程名</th>
				<th data-options="field:'grade'">成绩</th>
				<th data-options="field:'operaterName'">录入人员</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${grades}" var="grade" begin="0" step="1">
				<tr>
					<td>${grade.id }</td>
					<td>${grade.stuName }</td>
					<td>${grade.stuIdCard }</td>
					<td>${grade.courseName }</td>
					<td>${grade.grade }</td>
					<td>${grade.operaterName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div id="pageSelect">
			<p>
				共${pageCount}页 
				<%
					int pageCount = (Integer)request.getAttribute("pageCount");
					String queryString = request.getQueryString();
					int currentPage = Integer.parseInt(queryString.substring(queryString.indexOf("&d=")-1,
										queryString.indexOf("&d=")));
					queryString = queryString.substring(0, queryString.indexOf("page"));
				%>
				<a href="/zk/getDatasGrade?<%=queryString%>page=1&d=<%=new Date().getTime()%>">首页</a>
				<a href="/zk/getDatasGrade?<%=queryString%>page=
					<%if(currentPage==1) out.print(currentPage); else out.print(currentPage-1);%>
					&d=<%=new Date().getTime()%>">上一页</a>
				<a href="/zk/getDatasGrade?<%=queryString%>page=
					<%if(currentPage==pageCount) out.print(currentPage); else out.print(currentPage+1);%>
					&d=<%=new Date().getTime()%>">下一页</a>
				<a href="/zk/getDatasGrade?<%=queryString%>page=<%=pageCount%>&d=<%=new Date().getTime()%>">尾页</a>
			</p>
		</div>
</body>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
</html>
