<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zhnjy.util.db.SchoolDB"%>
<%@ page import="com.zhnjy.util.db.MajorDB"%>
<%@ page import="com.zhnjy.util.db.StudentDB"%>
<%@ page import="com.zhnjy.util.db.SiteDB"%>
<%@ page import="com.zhnjy.util.db.SchoolMajorDB" %>
<%@ page import="com.zhnjy.entity.Major"%>
<%@ page import="com.zhnjy.entity.Student"%>
<%@ page import="com.zhnjy.entity.School"%>
<%@ page import="com.zhnjy.entity.User"%>
<%@ page import="com.zhnjy.entity.Site"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title></title>
<link rel="stylesheet" href="/zk/easyui/themes/default/easyui.css"
	type="text/css"></link>
<link rel="stylesheet" href="/zk/css/form.css" type="text/css"></link>
</head>

<body style="background:#efefef;">
	<%
		String idCard = request.getParameter("idCard");
		Student s = StudentDB.getStudent(idCard);
		String level = s.getStudyLevel();
		String name = s.getName();
		String number = s.getNumber();
		String sex = s.getSex();
		String phone1 = s.getPhone1();
		String phone2 = s.getPhone2();
		String tuition = s.getTuition();
		String qq = s.getQq();
		String email = s.getEmail();
		String postCode = s.getPostCode();
		String politicalLevel = s.getPoliticalLevel();
		String address = s.getAddress();
		String nationality = s.getNationality();
		String remark = s.getRemark();
		String bExamNumber = null;
		String bStudyType = null;
		String bStudyForm = null;
		String bStatus = null;
		String bJoinTime = null;
		String zExamNumber = null;
		String zStudyType = null;
		String zStudyForm = null;
		String zStatus = null;
		String zJoinTime = null;
		int siteId = s.getSiteId();
		int bSchoolId = s.getbSchoolId();
		int bMajorId = s.getbMajorId();
		int zSchoolId = s.getzSchoolId();
		int zMajorId = s.getzMajorId();
		boolean haveZSchool = false;
		boolean haveBSchool = false;
		School bSchool = null;
		Major bMajor = null;
		School zSchool = null;
		Major zMajor = null;
		if (level.equals("zk") || level.equals("td")) {
			if(zSchoolId == 0) {
				zSchool = new School(0,"未选择","zk");
			} else {
				haveZSchool = true;
				zSchool = SchoolDB.getSchool(zSchoolId);
			}
			if(zMajorId == 0) {
				zMajor = new Major(0,"未选择专业","","zk");
			} else {
				zMajor = MajorDB.getMajor(zMajorId);
			}
			zExamNumber = s.getzExamNumber();
			zStudyType = s.getzStudyType();
			zStudyForm = s.getzStudyForm();
			zStatus = s.getzStatus();
			zJoinTime = s.getzJoinTime();
		}
		if (level.equals("bk") || level.equals("td")
				|| level.equals("derxl")) {
			if(bSchoolId == 0) {
				bSchool = new School(0,"未选择","bk");
			} else {
				haveBSchool = true;
				bSchool = SchoolDB.getSchool(bSchoolId);
			}
			if(bMajorId == 0) {
				bMajor = new Major(0,"未选择专业","","bk");
			} else {
				bMajor = MajorDB.getMajor(bMajorId);
			}
			bExamNumber = s.getbExamNumber();
			bStudyType = s.getbStudyType();
			bStudyForm = s.getbStudyForm();
			bStatus = s.getbStatus();
			bJoinTime = s.getbJoinTime();
		}
	%>
	<div style="width:550px; margin: 0 auto;">
		<form id="ff" action="updateStudent" method="post">
			<input type="hidden" name="idCard" class="input_text" value="<%=idCard%>"/>
			<table width="100%" border="0">
				<tr>
					<td rowspan="3"></td>
					<td><p>姓名：</p> <input type="text" name="name" class="input_text" id="name"
						value="<%=name%>" required/>
					</td>
					<td><p>推荐人：</p> <input type="text" name="number" class="input_text" id="number"
						value="<%=number%>" required/>
					</td>
				</tr>
				<tr>
					<td><p>性别：</p> <select id="sex" name="sex" class="select">
							<option value="男"
								<%if (sex.equals("男"))
				out.println("selected");%>>男</option>
							<option value="女"
								<%if (sex.equals("女"))
				out.println("selected");%>>女</option>
					</select></td>
					<td><p>联系电话1：</p> <input type="text" name="phone1" class="input_text" id="phone1"
						value="<%=phone1%>" required/></td>
				</tr>
				<tr>
					<td><p>学费：</p> <input type="text" name="tuition" class="input_text"
						value="<%=tuition%>" /></td>
					<td><p>联系电话2：</p> <input type="text" name="phone2"
						class="input_text" value="<%=phone2%>" /></td>
				</tr>
				<tr>
					<td><p>备注：</p><input type="text" name="remark" class="input_text"
						value="<%=remark%>" /></td>
					<td><p>QQ：</p> <input type="text" name="qq" class="input_text"
						value="<%=qq%>" /></td>
					<td><p>E-mail：</p> <input type="text" name="email" class="input_text"
						value="<%=email%>" /></td>
				</tr>
				<tr>
					<td><p>学习层次：</p> <select id="studyLevel" name="studyLevel"
						class="select">
							<option value="zk"
								<%if (level.equals("zk"))
				out.println("selected");%>>专科</option>
							<option value="bk"
								<%if (level.equals("bk"))
				out.println("selected");%>>本科</option>
							<option value="td"
								<%if (level.equals("td"))
				out.println("selected");%>>套读</option>
					</select>
					</td>
					<td><p>注册账号：</p> <input type="text" name="postCode" class="input_text"
						value="<%=postCode%>" /></td>
					<td><p>政治面貌：</p> <input type="text" name="politicalLevel"
						class="input_text" value="<%=politicalLevel%>" /></td>
				</tr>
				<tr>
					<td><p>地址：</p> <input type="text" name="address" class="input_text"
						value="<%=address%>" /></td>
					<td><p>民族：</p> <select id="nationality" name="nationality">
							<option value="汉族"
								<%if (nationality.equals("汉族"))
				out.println("selected");%>>汉族</option>
							<option value="满族"
								<%if (nationality.equals("满族"))
				out.println("selected");%>>满族</option>
							<option value="藏族"
								<%if (nationality.equals("藏族"))
				out.println("selected");%>>藏族</option>
							<option value="怒族"
								<%if (nationality.equals("怒族"))
				out.println("selected");%>>怒族</option>
							<option value="仡佬族"
								<%if (nationality.equals("仡佬族"))
				out.println("selected");%>>仡佬族</option>
							<option value="朝鲜族"
								<%if (nationality.equals("朝鲜族"))
				out.println("selected");%>>朝鲜族</option>
							<option value="撒拉族"
								<%if (nationality.equals("撒拉族"))
				out.println("selected");%>>撒拉族</option>
							<option value="东乡族"
								<%if (nationality.equals("东乡族"))
				out.println("selected");%>>东乡族</option>
							<option value="白族"
								<%if (nationality.equals("白族"))
				out.println("selected");%>>白族</option>
							<option value="羌族"
								<%if (nationality.equals("羌族"))
				out.println("selected");%>>羌族</option>
							<option value="壮族"
								<%if (nationality.equals("壮族"))
				out.println("selected");%>>壮族</option>
							<option value="阿昌族"
								<%if (nationality.equals("阿昌族"))
				out.println("selected");%>>阿昌族</option>
							<option value="珞巴族"
								<%if (nationality.equals("珞巴族"))
				out.println("selected");%>>珞巴族</option>
							<option value="塔吉克"
								<%if (nationality.equals("塔吉克"))
				out.println("selected");%>>塔吉克</option>
							<option value="景颇族"
								<%if (nationality.equals("景颇族"))
				out.println("selected");%>>景颇族</option>
							<option value="侗族"
								<%if (nationality.equals("侗族"))
				out.println("selected");%>>侗族</option>
							<option value="畲族"
								<%if (nationality.equals("畲族"))
				out.println("selected");%>>畲族</option>
							<option value="回族"
								<%if (nationality.equals("回族"))
				out.println("selected");%>>回族</option>
							<option value="保安族"
								<%if (nationality.equals("保安族"))
				out.println("selected");%>>保安族</option>
							<option value="毛南族"
								<%if (nationality.equals("毛南族"))
				out.println("selected");%>>毛南族</option>
							<option value="塔塔尔"
								<%if (nationality.equals("塔塔尔"))
				out.println("selected");%>>塔塔尔</option>
							<option value="德昂族"
								<%if (nationality.equals("德昂族"))
				out.println("selected");%>>德昂族</option>
							<option value="京族"
								<%if (nationality.equals("京族"))
				out.println("selected");%>>京族</option>
							<option value="水族"
								<%if (nationality.equals("水族"))
				out.println("selected");%>>水族</option>
							<option value="独龙族"
								<%if (nationality.equals("独龙族"))
				out.println("selected");%>>独龙族</option>
							<option value="布朗族"
								<%if (nationality.equals("布朗族"))
				out.println("selected");%>>布朗族</option>
							<option value="仫佬族"
								<%if (nationality.equals("仫佬族"))
				out.println("selected");%>>仫佬族</option>
							<option value="土家族"
								<%if (nationality.equals("土家族"))
				out.println("selected");%>>土家族</option>
							<option value="赫哲族"
								<%if (nationality.equals("赫哲族"))
				out.println("selected");%>>赫哲族</option>
							<option value="黎族"
								<%if (nationality.equals("黎族"))
				out.println("selected");%>>黎族</option>
							<option value="土族"
								<%if (nationality.equals("土族"))
				out.println("selected");%>>土族</option>
							<option value="鄂伦春"
								<%if (nationality.equals("鄂伦春"))
				out.println("selected");%>>鄂伦春</option>
							<option value="基诺族"
								<%if (nationality.equals("基诺族"))
				out.println("selected");%>>基诺族</option>
							<option value="门巴族"
								<%if (nationality.equals("门巴族"))
				out.println("selected");%>>门巴族</option>
							<option value="锡伯族"
								<%if (nationality.equals("锡伯族"))
				out.println("selected");%>>锡伯族</option>
							<option value="维吾尔族"
								<%if (nationality.equals("维吾尔族"))
				out.println("selected");%>>维吾尔族</option>
							<option value="佤族"
								<%if (nationality.equals("佤族"))
				out.println("selected");%>>佤族</option>
							<option value="俄罗斯"
								<%if (nationality.equals("俄罗斯"))
				out.println("selected");%>>俄罗斯</option>
							<option value="拉祜族"
								<%if (nationality.equals("拉祜族"))
				out.println("selected");%>>拉祜族</option>
							<option value="蒙古族"
								<%if (nationality.equals("蒙古族"))
				out.println("selected");%>>蒙古族</option>
							<option value="裕固族"
								<%if (nationality.equals("裕固族"))
				out.println("selected");%>>裕固族</option>
							<option value="乌孜别克"
								<%if (nationality.equals("乌孜别克"))
				out.println("selected");%>>乌孜别克</option>
							<option value="傣族"
								<%if (nationality.equals("傣族"))
				out.println("selected");%>>傣族</option>
							<option value="瑶族"
								<%if (nationality.equals("瑶族"))
				out.println("selected");%>>瑶族</option>
							<option value="鄂温克"
								<%if (nationality.equals("鄂温克"))
				out.println("selected");%>>鄂温克</option>
							<option value="布依族"
								<%if (nationality.equals("布依族"))
				out.println("selected");%>>布依族</option>
							<option value="纳西族"
								<%if (nationality.equals("纳西族"))
				out.println("selected");%>>纳西族</option>
							<option value="哈尼族"
								<%if (nationality.equals("哈尼族"))
				out.println("selected");%>>哈尼族</option>
							<option value="柯尔克孜"
								<%if (nationality.equals("柯尔克孜"))
				out.println("selected");%>>柯尔克孜</option>
							<option value="苗族"
								<%if (nationality.equals("苗族"))
				out.println("selected");%>>苗族</option>
							<option value="彝族"
								<%if (nationality.equals("彝族"))
				out.println("selected");%>>彝族</option>
							<option value="高山族"
								<%if (nationality.equals("高山族"))
				out.println("selected");%>>高山族</option>
							<option value="傈僳族"
								<%if (nationality.equals("傈僳族"))
				out.println("selected");%>>傈僳族</option>
							<option value="普米族"
								<%if (nationality.equals("普米族"))
				out.println("selected");%>>普米族</option>
							<option value="哈萨克"
								<%if (nationality.equals("哈萨克"))
				out.println("selected");%>>哈萨克</option>
							<option value="达斡尔"
								<%if (nationality.equals("达斡尔"))
				out.println("selected");%>>达斡尔</option>
					</select>
					</td>
					<td><p>助学点：</p> <select id="siteId" name="siteId" class="select">
							<%
								User user = (User) session.getAttribute("user");
								List<Site> sites = null;
								if (user.getLevel() != 1) {
									sites = new ArrayList<Site>();
									sites.add(SiteDB.getSite(user.getSiteId()));
								} else {
									sites = SiteDB.query();
								}
								for (int i = 0; i < sites.size(); i++) {
									Site site = (Site) sites.get(i);
							%>
							<option value="<%=site.getId()%>"
								<%if (site.getId() == siteId)
					out.println("selected");%>><%=site.getName()%></option>
							<%
								}
							%>
					</select></td>
				</tr>
				<%
					if (level.equals("bk") || level.equals("td")) {
				%>
				<tr>
					<td><p>本科学校：</p><select id="bSchool" name="bSchoolId" class="select"
						onchange="bschool_major();">
							<option value="0" <%if(!haveBSchool) out.print("selected");%>>未选择</option>
							<%
								List<School> bschools = SchoolDB.getbSchoolList();
									for (int i = 0; i < bschools.size(); i++) {
										School school = (School) bschools.get(i);
							%>
							<option value="<%=school.getId()%>"
								<%if (haveBSchool && bSchool.getId() == school.getId())
						out.println("selected");%>><%=school.getName()%></option>
							<%
								}
							%>
					</select></td>
					<td><p>本科专业： </p><select id="bMajor" name="bMajorId" class="select">
							<%
								if(bSchool.getId() == 0) {
									out.print("<option value=''>无</option>");
								} else {
									int schoolId = bSchool.getId();
									List<Major> majors = SchoolMajorDB.getMajorlist(schoolId);
									for(int i = 0; i < majors.size(); i++) {
										Major major = (Major)majors.get(i);
							%>
								<option value="<%=major.getId() %>" 
									<%if(major.getId() == bMajor.getId()) 
										out.println("selected"); %>>
											<%=major.getName() %>
								</option>
							<%
									}
								
									if(majors.size()==0){
							%>
								<option value="">无</option>
							<%
									}
								}
							%>
					</select></td>
					<td><p>本科学籍号： </p><input type="text" name="bExamNumber" id="bExamNumber"
						class="input_text" value="<%=bExamNumber %>"/></td>
				</tr>
				<tr>
					<td><p>本科学习形式： </p><select id="bStudyForm" name="bStudyForm"
						class="select">
							<option value="全日制" <%if(bStudyForm.equals("全日制")) out.println("selected");%>>全日制</option>
							<option value="业余" <%if(bStudyForm.equals("业余")) out.println("selected");%>>业余</option>
					</select></td>
					<td><p>本科学习类型：</p> <select id="bStudyType" name="bStudyType"
						class="select">
							<option value="无" <%if(bStudyType.equals("无")) out.println("selected");%>>无</option>
							<option value="自考" <%if(bStudyType.equals("自考")) out.println("selected");%>>自考</option>
							<option value="成教" <%if(bStudyType.equals("成教")) out.println("selected");%>>成教</option>
							<option value="电大" <%if(bStudyType.equals("电大")) out.println("selected");%>>电大</option>
							<option value="远程教育" <%if(bStudyType.equals("远程教育")) out.println("selected");%>>远程教育</option>
					</select></td>
					<td><p>本科在籍状态：</p> <select id="bStatus" name="bStatus" class="select">
							<option value="待注册" <%if(bStatus.equals("待注册")) out.println("selected");%>>待注册</option>
							<option value="无" <%if(bStatus.equals("无")) out.println("selected");%>>无</option>
							<option value="在籍" <%if(bStatus.equals("在籍")) out.println("selected");%>>在籍</option>
							<option value="毕业" <%if(bStatus.equals("毕业")) out.println("selected");%>>毕业</option>
							<option value="休学" <%if(bStatus.equals("休学")) out.println("selected");%>>休学</option>
							<option value="退学1" <%if(bStatus.equals("退学1")) out.println("selected");%>>退学1</option>
							<option value="退学2" <%if(bStatus.equals("退学2")) out.println("selected");%>>退学2</option>
							<option value="开除" <%if(bStatus.equals("开除")) out.println("selected");%>>开除</option>
					</select></td>
				</tr>
				<tr>
					<td><p>本科入学时间： </p><input type="text" name="bJoinTime"
						class="input_text" value="<%=bJoinTime %>"/>
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<%
					}
					if (level.equals("zk") || level.equals("td")) {
				%>
				<tr>
					<td><p>专科学校：</p><select id="zSchool" name="zSchoolId" class="select"
						onchange="zschool_major();">
							<option value="0" <%if(!haveZSchool) out.print("selected"); %>>未选择</option>
							<%
								List<School> zschools = SchoolDB.getzSchoolList();
									for (int i = 0; i < zschools.size(); i++) {
										School school = (School) zschools.get(i);
							%>
							<option value="<%=school.getId()%>"
								<%if (haveZSchool && zSchool.getId() == school.getId())
						out.println("selected");%>><%=school.getName()%></option>
							<%
								}
							%>
					</select>
					</td>
					<td><p>专科专业： </p><select id="zMajor" name="zMajorId" class="select">
					<%
						if(zSchool.getId() == 0) {
							out.print("<option value=''>无</option>");
						} else {
							int schoolId = zSchool.getId();
							List majors = SchoolMajorDB.getMajorlist(schoolId);
							for(int i = 0; i < majors.size(); i++) {
								Major major = (Major)majors.get(i);
					%>
						<option value="<%=major.getId() %>" 
							<%if(major.getId() == zMajor.getId()) 
								out.println("selected"); %>>
									<%=major.getName() %>
						</option>
					<%
							}
						
							if(majors.size()==0){
					%>
						<option value="">无</option>
					<%
							}
						}
					%>
					</select>
					</td>
					<td><p>专科学籍号： </p><input type="text" name="zExamNumber" id="zExamNumber"
						class="input_text" value="<%=zExamNumber %>" /></td>
				</tr>
				<tr>
					<td><p>专科学习形式： </p><select id="zStudyForm" name="zStudyForm"
						class="select">
							<option value="全日制" <%if(zStudyForm.equals("全日制")) out.println("selected");%>>全日制</option>
							<option value="业余" <%if(zStudyForm.equals("业余")) out.println("selected");%>>业余</option>
					</select>
					</td>
					<td><p>专科学习类型：</p> <select id="zStudyType" name="zStudyType"
						class="select">
							<option value="无" <%if(zStudyType.equals("无")) out.println("selected");%>>无</option>
							<option value="自考" <%if(zStudyType.equals("自考")) out.println("selected");%>>自考</option>
							<option value="成教" <%if(zStudyType.equals("成教")) out.println("selected");%>>成教</option>
							<option value="电大" <%if(zStudyType.equals("电大")) out.println("selected");%>>电大</option>
							<option value="远程教育" <%if(zStudyType.equals("远程教育")) out.println("selected");%>>远程教育</option>
					</select>
					</td>
					<td><p>专科在籍状态：</p> <select id="zStatus" name="zStatus"
						class="select">
							<option value="待注册" <%if(zStatus.equals("待注册")) out.println("selected");%>>待注册</option>
							<option value="无" <%if(zStatus.equals("无")) out.println("selected");%>>无</option>
							<option value="在籍" <%if(zStatus.equals("在籍")) out.println("selected");%>>在籍</option>
							<option value="毕业" <%if(zStatus.equals("毕业")) out.println("selected");%>>毕业</option>
							<option value="休学" <%if(zStatus.equals("休学")) out.println("selected");%>>休学</option>
							<option value="退学1" <%if(zStatus.equals("退学1")) out.println("selected");%>>退学1</option>
							<option value="退学2" <%if(zStatus.equals("退学2")) out.println("selected");%>>退学2</option>
							<option value="开除" <%if(zStatus.equals("开除")) out.println("selected");%>>开除</option>
					</select>
					</td>
				</tr>
				<tr>
					<td><p>专科入学时间： </p><input type="text" name="zJoinTime"
						class="input_text" value="<%=zJoinTime %>"/>
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<%
					}
				%>
			</table>
			<input type="submit" name="submit" value="更改" class="input_button" />
		</form>
	</div>
</body>
<script type="text/javascript" src="/zk/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.form.js"></script>
<script type="text/javascript" src="/zk/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/zk/js/validate.js"></script>
<script type="text/javascript" src="/zk/js/update_stu.js"></script>
<script type="text/javascript">
	$(function(){  
		equals_two('idCard',15,18,'身份证只有15和18位');
		maxLength('name',8);
		maxLength('number',11);
		mobile('phone1');
		form('ff');
	});
</script>
</html>
