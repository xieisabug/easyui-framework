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
				<th data-options="field:'stuName'">学生姓名</th>
				<th data-options="field:'stuIdCard'">学生身份证号码</th>
				<th data-options="field:'unit1', width:130">第1单元</th>
				<th data-options="field:'unit2', width:130">第2单元</th>
				<th data-options="field:'unit3', width:130">第3单元</th>
				<th data-options="field:'unit4', width:130">第4单元</th>
				<th data-options="field:'unit5', width:130">第5单元</th>
				<th data-options="field:'unit6', width:130">第6单元</th>
				<th data-options="field:'examDate'">考期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selectCourses}" var="selectCourse" begin="0" step="1">
				<tr>
					<td>${selectCourse.stuName }</td>
					<td>${selectCourse.idCard }</td>
					<td>${selectCourse.courseName[1]}</td>
					<td>${selectCourse.courseName[2]}</td>
					<td>${selectCourse.courseName[3]}</td>
					<td>${selectCourse.courseName[4]}</td>
					<td>${selectCourse.courseName[5]}</td>
					<td>${selectCourse.courseName[6]}</td>
					<td>${selectCourse.examDate }</td>
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
				<a href="/zk/getDatasSC?<%=queryString%>page=1&d=<%=new Date().getTime()%>">首页</a>
				<a href="/zk/getDatasSC<%=queryString%>page=
					<%if(currentPage==1) out.print(currentPage); else out.print(currentPage-1);%>
					&d=<%=new Date().getTime()%>">上一页</a>
				<a href="/zk/getDatasSC?<%=queryString%>page=
					<%if(currentPage==pageCount) out.print(currentPage); else out.print(currentPage+1);%>
					&d=<%=new Date().getTime()%>">下一页</a>
				<a href="/zk/getDatasSC?<%=queryString%>page=<%=pageCount%>&d=<%=new Date().getTime()%>">尾页</a>
			</p>
		</div>
</body>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
</html>
