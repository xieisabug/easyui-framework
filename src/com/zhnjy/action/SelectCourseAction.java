package com.zhnjy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.SelectCourse;
import com.zhnjy.entity.Student;
import com.zhnjy.entity.User;
import com.zhnjy.util.db.CourseDB;
import com.zhnjy.util.db.ExamItemDB;
import com.zhnjy.util.db.ExamSetDB;
import com.zhnjy.util.db.SelectCourseDB;
import com.zhnjy.util.db.SelectCourseXML;
import com.zhnjy.util.db.StudentDB;

public class SelectCourseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String examDate;
	private String operatorId;

	// 该变量是用来接收考籍号分不清是专科还是本科
	private String examNumber;

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

	private int page;
	private int rows;

	// 根据学籍号得到该学生的基本信息，并且以XML的格式传给前端

	public void getXml() {
		Student student = null;
		Document doc = null;
		if (examNumber != null) {
			boolean isBK = true;
			// 根据本科考籍号得到该学生,
			student = StudentDB.getStudentBybExamNumber(examNumber);
			if (student == null) {
				student = StudentDB.getStudentByzExamNumber(examNumber);
				isBK = false;
			}

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			// 如果没有这个学生或者是用户没有这个权限就全部置空

			if (student == null) {
				doc = SelectCourseXML.selectCourse("没有该学生");
			} else {
				if (user.getLevel() == 1 || user.getLevel() == 2) {
					doc = SelectCourseXML.selectCourse(student, isBK);
				} else {
					if (student.getSiteId() != user.getSiteId()) {
						doc = SelectCourseXML.selectCourse("您没有权限给该学生选课");
					} else {
						doc = SelectCourseXML.selectCourse(student, isBK);
					}
				}
			}
		} else {
			student = StudentDB.getStudent(idCard);
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			// 如果没有这个学生或者是用户没有这个权限就全部置空

			if (student == null) {
				doc = SelectCourseXML.selectCourse("没有该学生");
			} else {
				boolean isBK = true;
				if (!student.getStudyLevel().equals("bk")) {
					isBK = false;
				}
				if (user.getLevel() == 1 || user.getLevel() == 2) {
					doc = SelectCourseXML.selectCourse(student, isBK);
				} else {
					if (student.getSiteId() != user.getSiteId()) {
						doc = SelectCourseXML.selectCourse("您没有权限给该学生选课");
					} else {
						doc = SelectCourseXML.selectCourse(student, isBK);
					}
				}
			}
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/xml;charset=utf-8");
		try {
			PrintWriter pw = response.getWriter();
			doc.write(pw);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 得到全局设置的参数
	public String getDate() {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		// /得到单元数
		resultMap.put("unitNum", ExamSetDB.getUnitNum());
		int temp = Integer.parseInt(ExamSetDB.getUnitNum());
		for (int i = 1; i <= temp; i++) {
			resultMap.put("unit" + i + "time",
					ExamSetDB.get("unit" + i + "time"));
		}
		int d;
		if (temp % 2 == 1) {
			d = temp / 2 + 1;
		} else {
			d = temp / 2;
		}
		for (int i = 1; i <= d; i++) {
			resultMap.put("day" + i, ExamSetDB.get("day" + i));
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("resultMap", resultMap);// 得到考期
		return "getdatesuccess";
	}

	/**
	 * 
	 * 根据前段传来的身份证号码和所选课程的id存入数据库
	 * 
	 * @return
	 */
	public String add() {
		// 动态接收参数 uniti courseIdi
		HttpServletRequest reqeust = ServletActionContext.getRequest();
		int unitNum = Integer.parseInt(ExamSetDB.getUnitNum());// 得到单元数

		int _operaterId = Integer.parseInt(operatorId);
		for (int i = 1; i <= unitNum; i++) {
			String number = reqeust.getParameter("unit" + i);
			if (number == null)
				continue;
			int courseId = CourseDB.getId(number);
			if (ExamItemDB.add(idCard, courseId, i, examDate, _operaterId) != 0) {
			}
		}
		return "addsuccess";
	}

	/*
	 * public String getDatas() { HttpServletRequest request =
	 * ServletActionContext.getRequest(); List<SelectCourse> selectCourses =
	 * SelectCourseDB.getListByPage(idCard, name, number, nationality, sex,
	 * phone1, phone2, siteId, studyLevel, bSchoolId, bMajorId, bExamNumber,
	 * bStudyForm, bStudyType, bStatus, bJoinTime, zSchoolId, zMajorId,
	 * zExamNumber, zStudyForm, zStudyType, zStatus, zJoinTime, page); int
	 * pageCount = SelectCourseDB.getPageCount(idCard, name, number,
	 * nationality, sex, phone1, phone2, siteId, studyLevel, bSchoolId,
	 * bMajorId, bExamNumber, bStudyForm, bStudyType, bStatus, bJoinTime,
	 * zSchoolId, zMajorId, zExamNumber, zStudyForm, zStudyType, zStatus,
	 * zJoinTime);
	 * 
	 * request.setAttribute("selectCourses", selectCourses);// 得到学生列表
	 * request.setAttribute("pageCount", pageCount);// 得到总页数 return
	 * "getSelectsSuccess"; }
	 */

	public void deleteStuAll() {
		boolean result = false;
		result = SelectCourseDB.deleteStuAll(idCard);

		JSONObject s = new JSONObject();
		s.put("result", result);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/xml;charset=utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.write(s.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void notChooseStu() {
		List<Student> students = SelectCourseDB.getNotChooseStu(page, rows);
		int pageCount = SelectCourseDB.getNotChooseStuPageCount();

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		jsonMap.put("total", pageCount);
		jsonMap.put("rows", students);

		JSONObject result = JSONObject.fromObject(jsonMap);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.append(result.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void chooseClassDatas() {
		List<SelectCourse> scs = SelectCourseDB.getListByPage(idCard, name,
				number, nationality, sex, phone1, phone2, siteId, studyLevel,
				bSchoolId, bMajorId, bExamNumber, bStudyForm, bStudyType,
				bStatus, bJoinTime, zSchoolId, zMajorId, zExamNumber,
				zStudyForm, zStudyType, zStatus, zJoinTime, page, rows);
		int pageCount = SelectCourseDB.getPageCount(idCard, name,
				number, nationality, sex, phone1, phone2, siteId, studyLevel,
				bSchoolId, bMajorId, bExamNumber, bStudyForm, bStudyType,
				bStatus, bJoinTime, zSchoolId, zMajorId, zExamNumber,
				zStudyForm, zStudyType, zStatus, zJoinTime);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		jsonMap.put("total", pageCount);
		jsonMap.put("rows", scs);

		JSONObject result = JSONObject.fromObject(jsonMap);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.append(result.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getExamNumber() {
		return examNumber;
	}

	public void setExamNumber(String examNumber) {
		this.examNumber = examNumber;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

}
