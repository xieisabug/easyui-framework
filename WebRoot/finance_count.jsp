<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.zhnjy.util.db.SchoolDB"%>
<%@ page import="com.zhnjy.entity.School"%>
<%@ page import="com.zhnjy.util.db.SiteDB"%>
<%@ page import="com.zhnjy.entity.Site"%>
<%@ page import="com.zhnjy.util.db.RecordItemDB"%>
<%@ page import="com.zhnjy.entity.RecordItem"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title></title>

</head>

<body>
	<script type="text/javascript">setTimeout(refresh(), 20);</script>
	<div class="easyui-accordion" id="accordion"
		style="background: #fafafa;">

		<div title="查询选项" id="menu">
			<div id="stuInfo">
				<div class="item">
					身份证： <input type="text" id="idCard" name="idCard" class="input_text" />
				</div>
				<div class="item">
					姓名： <input type="text" id="name" name="name" class="input_text" />
				</div>
				<div class="item">
					学号： <input type="text" id="number" name="number" class="input_text" />
				</div>
				<div class="item">
					民族：<select id="nationality" name="nationality">
						<option value="" selected="selected">全部</option>
						<option value="汉族">汉族</option>
						<option value="满族">满族</option>
						<option value="藏族">藏族</option>
						<option value="怒族">怒族</option>
						<option value="仡佬族">仡佬族</option>
						<option value="朝鲜族">朝鲜族</option>
						<option value="撒拉族">撒拉族</option>
						<option value="东乡族">东乡族</option>
						<option value="白族">白族</option>
						<option value="羌族">羌族</option>
						<option value="壮族">壮族</option>
						<option value="阿昌族">阿昌族</option>
						<option value="珞巴族">珞巴族</option>
						<option value="塔吉克">塔吉克</option>
						<option value="景颇族">景颇族</option>
						<option value="侗族">侗族</option>
						<option value="畲族">畲族</option>
						<option value="回族">回族</option>
						<option value="保安族">保安族</option>
						<option value="毛南族">毛南族</option>
						<option value="塔塔尔">塔塔尔</option>
						<option value="德昂族">德昂族</option>
						<option value="京族">京族</option>
						<option value="水族">水族</option>
						<option value="独龙族">独龙族</option>
						<option value="布朗族">布朗族</option>
						<option value="仫佬族">仫佬族</option>
						<option value="土家族">土家族</option>
						<option value="赫哲族">赫哲族</option>
						<option value="黎族">黎族</option>
						<option value="土族">土族</option>
						<option value="鄂伦春">鄂伦春</option>
						<option value="基诺族">基诺族</option>
						<option value="门巴族">门巴族</option>
						<option value="锡伯族">锡伯族</option>
						<option value="维吾尔族">维吾尔族</option>
						<option value="佤族">佤族</option>
						<option value="俄罗斯">俄罗斯</option>
						<option value="拉祜族">拉祜族</option>
						<option value="蒙古族">蒙古族</option>
						<option value="裕固族">裕固族</option>
						<option value="乌孜别克">乌孜别克</option>
						<option value="傣族">傣族</option>
						<option value="瑶族">瑶族</option>
						<option value="鄂温克">鄂温克</option>
						<option value="布依族">布依族</option>
						<option value="纳西族">纳西族</option>
						<option value="哈尼族">哈尼族</option>
						<option value="柯尔克孜">柯尔克孜</option>
						<option value="苗族">苗族</option>
						<option value="彝族">彝族</option>
						<option value="高山族">高山族</option>
						<option value="傈僳族">傈僳族</option>
						<option value="普米族">普米族</option>
						<option value="哈萨克">哈萨克</option>
						<option value="达斡尔">达斡尔</option>
					</select>
				</div>
				<div class="item">
					性别： <select id="sex" name="sex" class="select">
						<option value="" selected="selected">全部</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</div>
				<div class="item">
					主联系电话： <input type="text" id="phone1" name="phone1"
						class="input_text" />
				</div>
				<div class="item">
					联系人： <input type="text" id="phone2" name="phone2"
						class="input_text" />
				</div>
				<div class="item">
					学习层次： <select id="studyLevel" name="studyLevel" class="select" 
						onchange="level_school();">
						<option value="" selected="selected">全部</option>
						<option value="zk">专科</option>
						<option value="bk">本科</option>
						<option value="td">套读</option>
					</select>
				</div>
				<div class="item">
					专科学校：<select id="zSchool" name="zSchool" class="select" 
					onclick="zschool_major();" disabled="disabled">
						<option value="">全部</option>
						<%
							List<School> zschools = SchoolDB.getzSchoolList();
							for (int i = 0; i < zschools.size(); i++) {
								School school = (School) zschools.get(i);
						%>
						<option value="<%=school.getId()%>"><%=school.getName()%></option>
						<%
							}
						%>
					</select>
				</div>
				<div class="item">
					专科专业： <select id="zMajor" name="zMajor" class="select" disabled="disabled">
						<option value="">--请先选择学校--</option>
					</select>
				</div>
				<div class="item">
					专科学籍号： <input type="text" id="zExamNumber" name="zExamNumber"
						class="input_text" disabled="disabled"/>
				</div>
				<div class="item">
					专学习形式： <select id="zStudyForm" name="zStudyForm" class="select" disabled="disabled">
						<option value="">--请先选择学校--</option>
					</select>
				</div>
				<div class="item">
					专学习类型： <select id="zStudyType" name="zStudyType" class="select" disabled="disabled">
						<option value="">--请先选择学校--</option>
					</select>
				</div>
				<div class="item">
					专入学时间： <input type="text" id="zJoinTime" name="zJoinTime"
						class="input_text" disabled="disabled"/>
				</div>
				<div class="item">
					专在籍状态： <select id="zStatus" name="zStatus" class="select" disabled="disabled">
						<option value="">--请先选择学校--</option>
					</select>
				</div>
				<div class="item">
					本科学校：<select id="bSchool" name="bSchool" 
					class="select" onclick="bschool_major();" disabled="disabled">
						<option value="">全部</option>
						<%
							List<School> bschools = SchoolDB.getbSchoolList();
							for (int i = 0; i < bschools.size(); i++) {
								School school = (School) bschools.get(i);
						%>
						<option value="<%=school.getId()%>"><%=school.getName()%></option>
						<%
							}
						%>
					</select>
				</div>
				<div class="item">
					本科专业： <select id="bMajor" name="bMajor" class="select" disabled="disabled">
						<option value="">--请先选择学校--</option>
					</select>
				</div>
				<div class="item">
					本科学籍号： <input type="text" id="bExamNumber" name="bExamNumber"
						class="input_text" disabled="disabled"/>
				</div>
				<div class="item">
					本学习形式： <select id="bStudyForm" name="bStudyForm" class="select" disabled="disabled">
						<option value="">--请先选择学校--</option>
					</select>
				</div>
				<div class="item">
					本学习类型： <select id="bStudyType" name="bStudyType" class="select" disabled="disabled">
						<option value="">--请先选择学校--</option>
					</select>
				</div>
				<div class="item">
					本入学时间： <input type="text" id="bJoinTime" name="bJoinTime"
						class="input_text" disabled="disabled"/>
				</div>
				<div class="item">
					本在籍状态： <select id="bStatus" name="bStatus" class="select" disabled="disabled">
						<option value="">--请先选择学校--</option>
					</select>
				</div>
				<div class="item">
					站点： <select id="siteId" name="siteId" class="select">
						<option value="">全部</option>
						<%
							List<Site> sites = SiteDB.query();
							for (int i = 0; i < sites.size(); i++) {
								Site site = (Site) sites.get(i);
						%>
						<option value="<%=site.getId()%>"><%=site.getName()%></option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<div id="recordInfo">
				<div class="item">
					交费项目：<select id="recordItemId" name="recordItemId" class="select" >
				<%
					List<RecordItem> items = RecordItemDB.getAll();
					for(int i = 0; i < items.size(); i++) { 
						RecordItem ri = (RecordItem)items.get(i);
				%>
					<option value="<%=ri.getId()%>" <%if(i==0) out.println("selected"); %>>
						<%=ri.getName() %>
					</option>
				<%
					} 
				%>
				</select>
				</div>
				<div class="item">
					学年： <input type="text" id="yearth" name="yearth"
						class="input_text" />
				</div>
				<div class="item">
					交费时间： <input type="text" id="date" name="date"
						class="input_text" />
				</div>
				<div class="item">
					票号： <input type="text" id="bill" name="bill"
						class="input_text" />
				</div>
				<div class="item">
					结算： <input type="text" id="goUp" name="goUp"
						class="input_text" />
				</div>
				<div class="item">
					结算票号： <input type="text" id="goUpBill" name="goUpBill"
						class="input_text" />
				</div>
				<div class="item">
					返还： <input type="text" id="goDown" name="goDown"
						class="input_text" />
				</div>
				<div class="item">
					返还票号： <input type="text" id="goDownBill" name="goDownBill"
						class="input_text" />
				</div>
				<div class="item">
					费用去向： <input type="text" id="goMoneyWhere" name="goMoneyWhere"
						class="input_text" />
				</div>
				<div class="item">
					结算去向： <input type="text" id="goUpWhere" name="goUpWhere"
						class="input_text" />
				</div>
				<div class="item">
					返还去向： <input type="text" id="goDownWhere" name="goDownWhere"
						class="input_text" />
				</div>
				<div class="item">
					备注： <input type="text" id="remark" name="remark"
						class="input_text" />
				</div>
			</div>
			<input type="button" class="input_button" value="查询"
				onclick="refresh();" style="float:left;" />
		</div>
	</div>

	<table id="financecount_data"></table>
</body>
</html>
