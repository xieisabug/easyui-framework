package com.zhnjy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.Record;
import com.zhnjy.entity.Student;
import com.zhnjy.util.db.RecordDB;
import com.zhnjy.util.db.StudentDB;

public class RecordAction extends ActionSupport {
	private String id;
	private String idCard;
	private String recordItemId;
	private String money;
	private String bill;
	private String yearth;
	private String date;

	private String goUp;
	private String goUpBill;
	private String goDown;
	private String goDownBill;
	private String goUpWhere;
	private String goDownWhere;
	private String goMoneyWhere;
	private String remark;

	// 学生的基本信息
	// 这些属性是不能为空的。
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

	public String add() {
		int _recordItemId = Integer.parseInt(recordItemId);
		float _money;
		if (!money.equals("") || money != null) {
			_money = Float.parseFloat(money);
		} else {
			_money = 0;
		}
		float _goUp;
		if (goUp.equals("") || goUp == null) {
			_goUp = 0;
		} else {
			_goUp = Float.parseFloat(goUp);
		}
		float _goDown;
		if (!goDown.equals("") || goDown != null) {
			_goDown = 0;
		} else {
			_goDown = Float.parseFloat(goDown);
		}

		// 获取当前系统时间
		// 获得系统时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new java.util.Date());

		if (yearth == null) {
			yearth = "";
		}
		Record record = new Record(0, idCard, _recordItemId, _money, bill,
				yearth, date, _goUp, goUpBill, _goDown, goDownBill, goUpWhere,
				goDownWhere, goMoneyWhere, remark);

		if (RecordDB.add(record) != 0) {
			return "addsuccess";
		} else {
			return "addfail";
		}

	}

	public String delete() {
		int _id = Integer.parseInt(id);
		if (RecordDB.delete(_id) != 0) {
			return "deletesuccess";
		} else {
			return "deletesuccess";
		}
	}

	public String update() {
		int _id = Integer.parseInt(id);
		int _recordItemId = Integer.parseInt(recordItemId);
		float _money;
		if (!money.equals("") || money != null) {
			_money = Float.parseFloat(money);
		} else {
			_money = 0;
		}
		float _goUp;
		if (goUp.equals("") || goUp == null) {
			_goUp = 0;
		} else {
			_goUp = Float.parseFloat(goUp);
		}
		float _goDown;
		if (!goDown.equals("") || goDown != null) {
			_goDown = 0;
		} else {
			_goDown = Float.parseFloat(goDown);
		}
		Record record = new Record(_id, idCard, _recordItemId, _money, bill,
				yearth, "", _goUp, goUpBill, _goDown, goDownBill, goUpWhere,
				goDownWhere, goMoneyWhere, remark);
		if (RecordDB.update(record) != 0) {
			return "updatesuccess";
		} else {
			return "updatesuccess";
		}
	}

	public String download() {
		List<Record> records = new ArrayList<Record>();
		List<Student> students = StudentDB.getList(idCard, name, number,
				nationality, sex, phone1, phone2, siteId, studyLevel,
				bSchoolId, bMajorId, bExamNumber, bStudyForm, bStudyType,
				bStatus, bJoinTime, zSchoolId, zMajorId, zExamNumber,
				zStudyForm, zStudyType, zStatus, zJoinTime);
		Iterator<Student> it = students.iterator();
		while (it.hasNext()) {
			Student student = it.next();
			List<Record> re = RecordDB.getList(student.getIdCard(),
					recordItemId, bill, yearth, date, goUp, goUpBill, goDown,
					goDownBill, goUpWhere, goDownWhere, goMoneyWhere, remark);
			Iterator<Record> r = re.iterator();
			while (r.hasNext()) {
				records.add(r.next());
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();

		String realPath = request.getSession().getServletContext()
				.getRealPath("/");
		RecordDB.print(records, realPath);

		return "downloadSuccess";
	}

	public void getStudents() {
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

		// ui需要显示数据的总页数
		jsonMap.put("total", pageCount);
		// ui需要实现数据的总记录数
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

	public void getDatas() {
		List<Record> records = RecordDB.getList(idCard, name, number,
				nationality, sex, phone1, phone2, siteId, studyLevel,
				bSchoolId, bMajorId, bExamNumber, bStudyForm, bStudyType,
				bStatus, bJoinTime, zSchoolId, zMajorId, zExamNumber,
				zStudyForm, zStudyType, zStatus, zJoinTime, recordItemId, bill,
				yearth, date, goUp, goUpBill, goDown, goDownBill, goUpWhere,
				goDownWhere, goMoneyWhere, remark, page ,rows);
		int pageCount = RecordDB.getPageCount(idCard, name, number,
				nationality, sex, phone1, phone2, siteId, studyLevel,
				bSchoolId, bMajorId, bExamNumber, bStudyForm, bStudyType,
				bStatus, bJoinTime, zSchoolId, zMajorId, zExamNumber,
				zStudyForm, zStudyType, zStatus, zJoinTime, recordItemId, bill,
				yearth, date, goUp, goUpBill, goDown, goDownBill, goUpWhere,
				goDownWhere, goMoneyWhere, remark);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		//ui需要显示数据的总页数
        jsonMap.put("total", pageCount);
        //ui需要实现数据的总记录数
        jsonMap.put("rows", records);
        
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getYearth() {
		return yearth;
	}

	public void setYearth(String yearth) {
		this.yearth = yearth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRecordItemId() {
		return recordItemId;
	}

	public void setRecordItemId(String recordItemId) {
		this.recordItemId = recordItemId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}

	public String getGoUp() {
		return goUp;
	}

	public void setGoUp(String goUp) {
		this.goUp = goUp;
	}

	public String getGoUpBill() {
		return goUpBill;
	}

	public void setGoUpBill(String goUpBill) {
		this.goUpBill = goUpBill;
	}

	public String getGoDown() {
		return goDown;
	}

	public void setGoDown(String goDown) {
		this.goDown = goDown;
	}

	public String getGoDownBill() {
		return goDownBill;
	}

	public void setGoDownBill(String goDownBill) {
		this.goDownBill = goDownBill;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getGoUpWhere() {
		return goUpWhere;
	}

	public void setGoUpWhere(String goUpWhere) {
		this.goUpWhere = goUpWhere;
	}

	public String getGoDownWhere() {
		return goDownWhere;
	}

	public void setGoDownWhere(String goDownWhere) {
		this.goDownWhere = goDownWhere;
	}

	public String getGoMoneyWhere() {
		return goMoneyWhere;
	}

	public void setGoMoneyWhere(String goMoneyWhere) {
		this.goMoneyWhere = goMoneyWhere;
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
