<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.zhnjy.entity.Student"%>
<%@ page import="com.zhnjy.entity.User"%>
<%@ page import="com.zhnjy.entity.School"%>
<%@ page import="com.zhnjy.entity.Major"%>
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
	<table class="easyui-datagrid" singleSelect="true" showFooter="true" rownumbers="true">
		<thead>
			<tr>
				<c:forEach items="${columnValue}" var="colV">
					<c:if test="${colV eq 'idCard'}">
						<th data-options="field:'idCard'">身份证</th>
					</c:if>
					<c:if test="${colV eq 'name'}">
						<th data-options="field:'name'">姓名</th>
					</c:if>
					<c:if test="${colV eq 'studyLevel'}">
						<th data-options="field:'studyLevel'">学习层次</th>
					</c:if>
					<c:if test="${colV eq 'number'}">
						<th data-options="field:'number'">推荐人</th>
					</c:if>
					<c:if test="${colV eq 'phone'}">
						<th data-options="field:'phone1'">联系电话1</th>
						<th data-options="field:'phone2'">联系电话2</th>
					</c:if>
					<c:if test="${colV eq 'others'}">
						<th data-options="field:'sex'">性别</th>
						<th data-options="field:'nationality'">民族</th>
						<th data-options="field:'site'">站点</th>
					</c:if>
					<c:if test="${colV eq 'bInfo'}">
						<th data-options="field:'bSchool'">本科学校</th>
						<th data-options="field:'bMajor'">本科专业</th>
						<th data-options="field:'bExamNumber'">本科考籍号</th>
						<th data-options="field:'bStudyForm'">本科学习形式</th>
						<th data-options="field:'bStudyType'">本科学习类型</th>
						<th data-options="field:'bStatus'">本科状态</th>
						<th data-options="field:'bJoinTime'">本科入学时间</th>
					</c:if>
					<c:if test="${colV eq 'zInfo'}">
						<th data-options="field:'zSchool'">专科学校</th>
						<th data-options="field:'zMajor'">专科专业</th>
						<th data-options="field:'zExamNumber'">专科考籍号</th>
						<th data-options="field:'zStudyForm'">专科学习形式</th>
						<th data-options="field:'zStudyType'">专科学习类型</th>
						<th data-options="field:'zStatus'">专科状态</th>
						<th data-options="field:'zJoinTime'">专科入学时间</th>
					</c:if>
					<c:if test="${colV eq 'remark'}">
						<th data-options="field:'remark'">备注</th>
					</c:if>
				</c:forEach>
				<th data-options="field:'method'">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${students}" var="student" begin="0" step="1">
				<tr>
				<c:forEach items="${columnValue}" var="colV" begin="0">
					<c:if test="${colV eq 'idCard'}">
						<td>${student.idCard}</td>
					</c:if>
					<c:if test="${colV eq 'name'}">
						<td>${student.name}</td>
					</c:if>
					<c:if test="${colV eq 'studyLevel'}">
						<td>${student.studyLevelName}</td>
					</c:if>
					<c:if test="${colV eq 'number'}">
						<td>${student.number}</td>
					</c:if>
					<c:if test="${colV eq 'phone'}">
						<td>${student.phone1}</td>
						<td>${student.phone2}</td>
					</c:if>
					<c:if test="${colV eq 'others'}">
						<td>${student.sex}</td>
						<td>${student.nationality}</td>
						<td>${student.siteName}</td>
					</c:if>
					<c:if test="${colV eq 'bInfo'}">
						<td>${student.bSchoolName}</td>
						<td>${student.bMajorName}</td>
						<td>${student.bExamNumber}</td>
						<td>${student.bStudyForm}</td>
						<td>${student.bStudyType}</td>
						<td>${student.bStatus}</td>
						<td>${student.bJoinTime}</td>
					</c:if>
					<c:if test="${colV eq 'zInfo'}">
						<td>${student.zSchoolName}</td>
						<td>${student.zMajorName}</td>
						<td>${student.zExamNumber}</td>
						<td>${student.zStudyForm}</td>
						<td>${student.zStudyType}</td>
						<td>${student.zStatus}</td>
						<td>${student.zJoinTime}</td>
					</c:if>
					<c:if test="${colV eq 'remark'}">
						<td>${student.remark}</td>
					</c:if>
				</c:forEach>
				<c:choose>
					<c:when test="${user.level == 2 || user.level == 4}">
						<td><a href=javascript:update_student('${student.idCard}');>修改</a></td>
					</c:when>
					<c:otherwise>
						<td><a href=javascript:delete_stu('${student.idCard}');>删除</a> | 
						<a href=javascript:update_student('${student.idCard}');>修改</a></td>
					</c:otherwise>
				</c:choose>
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
			<a href="/zk/getDatasStudent?<%=queryString%>page=1&d=<%=new Date().getTime()%>">首页</a>
			<a href="/zk/getDatasStudent?<%=queryString%>page=
				<%if(currentPage==1) out.print(currentPage); else out.print(currentPage-1);%>
				&d=<%=new Date().getTime()%>">上一页</a>
			<a href="/zk/getDatasStudent?<%=queryString%>page=
				<%if(currentPage==pageCount) out.print(currentPage); else out.print(currentPage+1);%>
				&d=<%=new Date().getTime()%>">下一页</a>
			<a href="/zk/getDatasStudent?<%=queryString%>page=<%=pageCount%>&d=<%=new Date().getTime()%>">尾页</a>
		</p>
	</div>
</body>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/zk/js/stu_manage.js"></script>
</html>
