<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zhnjy.util.db.SchoolDB" %>
<%@ page import="com.zhnjy.entity.School" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title></title>

<link rel="stylesheet" href="/zk/css/form.css" type="text/css"></link>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/js/stu_manage.js"></script>
</head>

<body style="background:#efefef;">
<%
	String idCard = (String)request.getAttribute("idCard");
	String studyLevel = (String)request.getAttribute("studyLevel");
%>
	<script type="text/javascript">
		$(function(){
			bschool_major();
			zschool_major();
		});
	</script>
	<div style="width:300px; margin: 0 auto;">
		<form id="ff" action="updateSelectStudent" method="post">
			<input type="hidden" name="idCard" value="<%=idCard %>">
			<p>
				QQ： <input type="text" name="qq" class="input_text" />
			</p>
			<p>
				E-mail： <input type="text" name="email" class="input_text" />
			</p>
			<p>
				地址： <input type="text" name="address" class="input_text" />
			</p>
			<p>
				注册账号： <input type="text" name="postCode" class="input_text" />
			</p>
			<p>
				政治面貌： <input type="text" name="politicalLevel" class="input_text" />
			</p>
			<p>
				学费标准： <input type="text" name="tuition" class="input_text" />
			</p>
			<%
				
				if(studyLevel.equals("zk") || studyLevel.equals("td")) { 
					List schools = SchoolDB.getzSchoolList();
			%>
			<p>
				专科学校：<select id="zSchool" name="zSchoolId" class="select" onchange="zschool_major();">
				<%
					for(int i = 0; i < schools.size(); i++) {
						School school = (School)schools.get(i);
				%>
					<option value="<%=school.getId() %>" <%if(i==0) out.println("selected='selected'"); %>><%=school.getName() %></option>
				<%
					} 
				%>
				</select>
			</p>
			<p>
				专科专业： 
				<select id="zMajor" name="zMajorId" class="select">
					<option value="0">--请先选择学校--</option>
				</select>
			</p>
			<p>
				专科考籍号： 
				<input type="text" name="zExamNumber" class="input_text" value=""/>
			</p>
			<p>
				专科学习形式： 
				<select id="zStudyForm" name="zStudyForm" class="select">
					<option value="全日制">全日制</option>
					<option value="业余">业余</option>
				</select>
			</p>
			<p>
				专科学习类型：  
				<select id="zStudyType" name="zStudyType" class="select">
					<option value="无">无</option>
					<option value="自考" selected>自考</option>
					<option value="成教">成教</option>
					<option value="电大">电大</option>
					<option value="远程教育">远程教育</option>
				</select>
			</p>
			<p>
				专科在籍状态：  
				<select id="zStatus" name="zStatus" class="select">
					<option value="待注册">待注册</option>
					<option value="无">无</option>
					<option value="在籍" selected>在籍</option>
					<option value="毕业">毕业</option>
					<option value="休学">休学</option>
					<option value="退学1">退学1</option>
					<option value="退学2">退学2</option>
					<option value="开除">开除</option>
				</select>
			</p>
			<p>
				专科入学时间<span>(以第一次入学时间为准，格式为yyyy-mm-dd)</span>： 
				<input type="text" name="zJoinTime" class="input_text" value=""/>
			</p>
			<%
				}
				if(studyLevel.equals("bk") || studyLevel.equals("td")) { 
					List schools = SchoolDB.getbSchoolList();
			%>
			<p>
				本科学校：
				<select id="bSchool" name="bSchoolId" class="select" onchange="bschool_major();">
				<%
					for(int i = 0; i < schools.size(); i++) {
						School school = (School)schools.get(i);
				%>
					<option value="<%=school.getId() %>" <%if(i==0) out.println("selected='selected'"); %>><%=school.getName() %></option>
				<%
					} 
				%>
				</select>
			</p>
			<p>
				本科专业： 
				<select id="bMajor" name="bMajorId" class="select">
					<option value="0">--请先选择学校--</option>
				</select>
			</p>
			<p>
				本科考籍号： 
				<input type="text" name="bExamNumber" class="input_text" value="" />
			</p>
			<p>
				本科学习形式： 
				<select id="bStudyForm" name="bStudyForm" class="select">
					<option value="全日制" selected>全日制</option>
					<option value="业余">业余</option>
				</select>
			</p>
			<p>
				本科学习类型：  
				<select id="bStudyType" name="bStudyType" class="select">
					<option value="无">无</option>
					<option value="自考" selected>自考</option>
					<option value="成教">成教</option>
					<option value="电大">电大</option>
					<option value="远程教育">远程教育</option>
				</select>
			</p>
			<p>
				本科在籍状态：  
				<select id="bStatus" name="bStatus" class="select">
					<option value="待注册">待注册</option>
					<option value="无">无</option>
					<option value="在籍" selected>在籍</option>
					<option value="毕业">毕业</option>
					<option value="休学">休学</option>
					<option value="退学1">退学1</option>
					<option value="退学2">退学2</option>
					<option value="开除">开除</option>
				</select>
			</p>
			<p>
				本科入学时间<span>(以第一次入学时间为准，格式为yyyy-mm-dd)</span>： 
				<input type="text" name="bJoinTime" class="input_text" value=""/>
			</p>
			<%
				}
			%>
			<input type="submit" class="input_button" value="提交"></input>
		</form>
	</div>
	<br />
	<br />
</body>
</html>
