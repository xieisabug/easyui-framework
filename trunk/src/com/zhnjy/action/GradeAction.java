package com.zhnjy.action;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.Grade;
import com.zhnjy.entity.Student;
import com.zhnjy.entity.User;
import com.zhnjy.util.db.CourseDB;
import com.zhnjy.util.db.GradeDB;
import com.zhnjy.util.db.PoiHelper;
import com.zhnjy.util.db.StudentDB;
import com.zhnjy.util.string.StringManage;

public class GradeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private File file;// 封装上传的文件
	// 这些属性是不能为空的。
	private String idCard;
	private String password;
	private String name;
	private String number;
	private String nationality;
	private String sex;
	private String phone1;
	private String studyLevel;
	private String siteId;

	// 这些属性可以为空
	private String phone2;
	private String remark;

	private String qq;
	private String email;
	private String address;
	private String postCode;
	private String politicalLevel;
	private String tuition;
	private String bSchoolId;
	private String bMajorId;
	private String bStudyForm;
	private String bStudyType;
	private String bExamNumber;
	private String bStatus;
	private String bJoinTime;
	private String zSchoolId;
	private String zMajorId;
	private String zStudyForm;
	private String zStudyType;
	private String zExamNumber;
	private String zStatus;
	private String zJoinTime;

	int page;

	public String add() {

		DecimalFormat df = new DecimalFormat("#");
		List<Grade> list = new ArrayList<Grade>();
		Sheet sheet = PoiHelper.getSheet(file);
		int gradeCol = PoiHelper.findRow(sheet, "成绩");
		int courseCol = PoiHelper.findRow(sheet, "课程");
		int stuNumberCol = PoiHelper.findRow(sheet, "考籍号");
		int lastRow = sheet.getLastRowNum();
		// tt判断是不是全部都插入成功
		// int tt = 0;
		for (int i = 0; i < lastRow; i++) {
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(gradeCol);
			if (cell == null)
				continue;

			// 得到考籍号
			Cell c = row.getCell(stuNumberCol);
			String stuNumber = PoiHelper.getCellValue(c);
			if (stuNumber.equals(""))
				continue;
			stuNumber = StringManage.removePointAtFirst(stuNumber);
			if (!StringManage.isNumeric(stuNumber))
				continue;
//System.out.println("stuNumber:" + stuNumber);
			// 得到成绩
			String gradeStr = PoiHelper.getCellValue(cell);
			if (gradeStr.equals(""))
				continue;
			if (StringManage.isNumeric(gradeStr)) {
				gradeStr = gradeStr.substring(0,gradeStr.indexOf("."));
			}

			// 通过学籍号找到身份证号码,如果身份证号码不存在就跳过这个同学。
			String idCard = GradeDB.getIdCard(stuNumber);

			if (idCard == null) {
				// tt++;
				// System.out.println("该学生不存在");
				continue;
			}
//System.out.println(idCard);
			// 得到课程编码，并且判断课程是否存在
			c = row.getCell(courseCol);
			String course = PoiHelper.getCellValue(c);
			String[] temp = course.split(" ");
			String courseNumber = temp[0];
			if (!CourseDB.isRepeat(courseNumber)) {
				// System.out.println("该课程不存在");
				continue;
			}
//System.out.println("courseNumber: " + courseNumber);
			// 伪数据
			int id = 0;
			String time = "无";
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			int operaterId = user.getId();
			Grade grade = new Grade(id, idCard, gradeStr, courseNumber, time,
					operaterId);
System.out.println("grade" + grade);
			// 判断该学生是不是已经有这门课程的成绩，如果有就更新分数，没有就插入新的成绩
			if (GradeDB.isPass(grade.getStuIdCard(), grade.getCourseNumber())) {
				// tt++;
				if (GradeDB.update(grade) == 0) {
					return "addfail";
				}
System.out.println("grade update");
			} else {
				// tt++;
				if (GradeDB.add(grade) == 0) {
					return "addfail";
				}
System.out.println("grade add");
			}

		}
		return "addsuccess";
	}

	public String getDatas() {

		HttpServletRequest request = ServletActionContext.getRequest();

		List<Student> students = StudentDB.getSomeByPage(idCard, name, number,
				nationality, sex, phone1, phone2, siteId, studyLevel,
				bSchoolId, bMajorId, bExamNumber, bStudyForm, bStudyType,
				bStatus, bJoinTime, zSchoolId, zMajorId, zExamNumber,
				zStudyForm, zStudyType, zStatus, zJoinTime, page);

		List<Grade> grades = new ArrayList<Grade>();

		int pageCount = GradeDB.getPageCount(idCard,
				name, number, nationality, sex, phone1, phone2, siteId,
				studyLevel, bSchoolId, bMajorId, bExamNumber, bStudyForm,
				bStudyType, bStatus, bJoinTime, zSchoolId, zMajorId,
				zExamNumber, zStudyForm, zStudyType, zStatus, zJoinTime);

		for (int i = 0; i < students.size(); i++) {
			Student s = students.get(i);
			grades.addAll(GradeDB.getGradesByIdCard(s.getIdCard()));
		}

		request.setAttribute("pageCount", pageCount);
		request.setAttribute("grades", grades);
		return "getgradessuccess";
	}

	public String delete() {
		System.out.println("成绩删除成功！！");
		return "deletesuccess";
	}

	public String update() {
		System.out.println("成绩修改成功！！");
		return "updatesuccess";
	}

	public String query() {

		System.out.println("成绩查看成功！！");
		return "querysuccess";
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getStudyLevel() {
		return studyLevel;
	}

	public void setStudyLevel(String studyLevel) {
		this.studyLevel = studyLevel;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPoliticalLevel() {
		return politicalLevel;
	}

	public void setPoliticalLevel(String politicalLevel) {
		this.politicalLevel = politicalLevel;
	}

	public String getTuition() {
		return tuition;
	}

	public void setTuition(String tuition) {
		this.tuition = tuition;
	}

	public String getbSchoolId() {
		return bSchoolId;
	}

	public void setbSchoolId(String bSchoolId) {
		this.bSchoolId = bSchoolId;
	}

	public String getbMajorId() {
		return bMajorId;
	}

	public void setbMajorId(String bMajorId) {
		this.bMajorId = bMajorId;
	}

	public String getbStudyForm() {
		return bStudyForm;
	}

	public void setbStudyForm(String bStudyForm) {
		this.bStudyForm = bStudyForm;
	}

	public String getbStudyType() {
		return bStudyType;
	}

	public void setbStudyType(String bStudyType) {
		this.bStudyType = bStudyType;
	}

	public String getbExamNumber() {
		return bExamNumber;
	}

	public void setbExamNumber(String bExamNumber) {
		this.bExamNumber = bExamNumber;
	}

	public String getbStatus() {
		return bStatus;
	}

	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}

	public String getbJoinTime() {
		return bJoinTime;
	}

	public void setbJoinTime(String bJoinTime) {
		this.bJoinTime = bJoinTime;
	}

	public String getzSchoolId() {
		return zSchoolId;
	}

	public void setzSchoolId(String zSchoolId) {
		this.zSchoolId = zSchoolId;
	}

	public String getzMajorId() {
		return zMajorId;
	}

	public void setzMajorId(String zMajorId) {
		this.zMajorId = zMajorId;
	}

	public String getzStudyForm() {
		return zStudyForm;
	}

	public void setzStudyForm(String zStudyForm) {
		this.zStudyForm = zStudyForm;
	}

	public String getzStudyType() {
		return zStudyType;
	}

	public void setzStudyType(String zStudyType) {
		this.zStudyType = zStudyType;
	}

	public String getzExamNumber() {
		return zExamNumber;
	}

	public void setzExamNumber(String zExamNumber) {
		this.zExamNumber = zExamNumber;
	}

	public String getzStatus() {
		return zStatus;
	}

	public void setzStatus(String zStatus) {
		this.zStatus = zStatus;
	}

	public String getzJoinTime() {
		return zJoinTime;
	}

	public void setzJoinTime(String zJoinTime) {
		this.zJoinTime = zJoinTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
