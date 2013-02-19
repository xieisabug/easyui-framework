package com.zhnjy.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.Student;
import com.zhnjy.util.db.StudentDB;

public class StudentAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
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
	// 文件下载的文件名字
	private String fileName;

	public String add() {
		int _siteId = Integer.parseInt(siteId);
		password = idCard.substring(idCard.length() - 6);
		Student student = new Student(idCard, password, number, name, sex,
				nationality, phone1, studyLevel, _siteId);
		student.setPhone2(phone2);
		student.setRemark(remark);

		if (StudentDB.addMust(student) != 0) {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("idCard", student.getIdCard());
			request.setAttribute("studyLevel", student.getStudyLevel());

			int _bSchoolId = 0;
			if (bSchoolId != null && !bSchoolId.equals("")) {
				_bSchoolId = Integer.parseInt(bSchoolId);
			}
			int _bMajorId = 0;
			if (bMajorId != null && !bMajorId.equals("")) {
				_bMajorId = Integer.parseInt(bMajorId);
			}

			int _zSchoolId = 0;
			if (zSchoolId != null && !zSchoolId.equals("")) {
				_zSchoolId = Integer.parseInt(zSchoolId);
			}

			int _zMajorId = 0;
			if (zMajorId != null && !zMajorId.equals("")) {
				_zMajorId = Integer.parseInt(zMajorId);
			}

			student.setbSchoolId(_bSchoolId);
			student.setbMajorId(_bMajorId);
			student.setzSchoolId(_zSchoolId);
			student.setzMajorId(_zMajorId);
			StudentDB.addSelectable(student);

			return "addsuccess";
		} else {
			// System.out.println("学生添加失败！！");
			return "addfail";
		}
	}

	//
	public String delete() {
		// System.out.println("idCard"+idCard);
		if (StudentDB.deleteStudent(idCard) != 0) {
			// System.out.println("学生删除成功！！");
			return "deletesuccess";
		} else {
			// System.out.println("学生删除失败！！");
			return "deletefail";
		}

	}

	// 更新可选的信息（第二个界面的所有信息）
	public String updateSelect() {
		Student student = new Student();

		student.setIdCard(idCard);
		// System.out.println("idcard" + student.getIdCard());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("idCard", idCard);

		int _bSchoolId = 0;
		if (bSchoolId != null && !bSchoolId.equals("")) {
			_bSchoolId = Integer.parseInt(bSchoolId);
		}
		int _bMajorId = 0;
		if (bMajorId != null && !bMajorId.equals("")) {
			_bMajorId = Integer.parseInt(bMajorId);
		}

		int _zSchoolId = 0;
		if (zSchoolId != null && !zSchoolId.equals("")) {
			_zSchoolId = Integer.parseInt(zSchoolId);
		}

		int _zMajorId = 0;
		if (zMajorId != null && !zMajorId.equals("")) {
			_zMajorId = Integer.parseInt(zMajorId);
		}

		student.setQq(qq);
		student.setEmail(email);
		student.setAddress(address);
		student.setPostCode(postCode);
		student.setPoliticalLevel(politicalLevel);
		student.setTuition(tuition);
		student.setbSchoolId(_bSchoolId);
		student.setbMajorId(_bMajorId);
		student.setbStudyForm(bStudyForm);
		student.setbStudyType(bStudyType);
		student.setbExamNumber(bExamNumber);
		student.setbStatus(bStatus);
		student.setbJoinTime(bJoinTime);
		student.setzSchoolId(_zSchoolId);
		student.setzMajorId(_zMajorId);
		student.setzStudyForm(zStudyForm);
		student.setzStudyType(zStudyType);
		student.setzExamNumber(zExamNumber);
		student.setzStatus(zStatus);
		student.setzJoinTime(zJoinTime);
		// 存入数据库
		if (StudentDB.addSelectable(student) != 0) {
			return "updateselectsuccess";
		} else {
			System.out.println("第二个界面的信息更新失败！");
			return "updatefail";
		}

	}

	// 更新所有学生信息
	public String update() {
		int _siteId = Integer.parseInt(siteId);
		// System.out.println(_siteId);

		int _bSchoolId = 0;
		if (bSchoolId != null && !bSchoolId.equals("")) {
			_bSchoolId = Integer.parseInt(bSchoolId);
		}
		int _bMajorId = 0;
		if (bMajorId != null && !bMajorId.equals("")) {
			_bMajorId = Integer.parseInt(bMajorId);
		}

		int _zSchoolId = 0;
		if (zSchoolId != null && !zSchoolId.equals("")) {
			_zSchoolId = Integer.parseInt(zSchoolId);
		}

		int _zMajorId = 0;
		if (zMajorId != null && !zMajorId.equals("")) {
			_zMajorId = Integer.parseInt(zMajorId);
		}

		Student student = new Student(idCard, password, number, name, sex,
				nationality, phone1, studyLevel, _siteId, phone2, remark, qq,
				email, address, postCode, politicalLevel, tuition, _bSchoolId,
				_bMajorId, bStudyForm, bStudyType, bExamNumber, bStatus,
				bJoinTime, _zSchoolId, _zMajorId, zStudyForm, zStudyType,
				zExamNumber, zStatus, zJoinTime);
		if (StudentDB.updateStudent(student) != 0) {
			// System.out.println("学生修改成功！！");
			return "updatesuccess";
		} else {
			// System.out.println("学生修改失败！！");
			return "updatefail";
		}
	}

	// 下载学生的全部字段
	public String downloadAll() {
		List students = StudentDB.getList(idCard, name, number, nationality,
				sex, phone1, phone2, siteId, studyLevel, bSchoolId, bMajorId,
				bExamNumber, bStudyForm, bStudyType, bStatus, bJoinTime,
				zSchoolId, zMajorId, zExamNumber, zStudyForm, zStudyType,
				zStatus, zJoinTime);
		HttpServletRequest request = ServletActionContext.getRequest();

		String realPath = request.getSession().getServletContext()
				.getRealPath("/");
		StudentDB.print(students, realPath);
		return "downloadAllSuccess";
	}
	
	// 下载学生的全部字段
	public String downloadSome() {
		List students = StudentDB.getList(idCard, name, number, nationality,
				sex, phone1, phone2, siteId, studyLevel, bSchoolId, bMajorId,
				bExamNumber, bStudyForm, bStudyType, bStatus, bJoinTime,
				zSchoolId, zMajorId, zExamNumber, zStudyForm, zStudyType,
				zStatus, zJoinTime);
		HttpServletRequest request = ServletActionContext.getRequest();

		String realPath = request.getSession().getServletContext()
				.getRealPath("/");
		StudentDB.printSome(students, realPath);
		return "downloadSomeSuccess";
	}

	// 文件下载的get set 方法
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String realPath = request.getSession().getServletContext()
				.getRealPath("/");
		String dir = realPath + "resources/stuInfo.xls";
		return new FileInputStream(dir); // 如果dir是绝对路径
		// return
		// ServletActionContext.getServletContext().getResourceAsStream(dir);
		// //如果dir是Resource下的相对路径
	}

	public void getDatas() {
		List<Student> students = StudentDB.getListByPage(idCard, name, number,
				nationality, sex, phone1, phone2, siteId, studyLevel,
				bSchoolId, bMajorId, bExamNumber, bStudyForm, bStudyType,
				bStatus, bJoinTime, zSchoolId, zMajorId, zExamNumber,
				zStudyForm, zStudyType, zStatus, zJoinTime, page, rows);
		int pageCount = StudentDB.getPageCount(idCard, name, number,
				nationality, sex, phone1, phone2, siteId, studyLevel,
				bSchoolId, bMajorId, bExamNumber, bStudyForm, bStudyType,
				bStatus, bJoinTime, zSchoolId, zMajorId, zExamNumber,
				zStudyForm, zStudyType, zStatus, zJoinTime);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		//ui需要显示数据的总页数
        jsonMap.put("total", pageCount);
        //ui需要实现数据的总记录数
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
	
	public String validateStu(){
		JSONObject result = new JSONObject();
		if(idCard != null) {
			Student s = StudentDB.getStudent(idCard);
			if(s == null) {
				result.put("result", "false");
			} else {
				result.put("result", "true");
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			PrintWriter pw = response.getWriter();
			pw.append(result.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	// 以下是get set方法
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
