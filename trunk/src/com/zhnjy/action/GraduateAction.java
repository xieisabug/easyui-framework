package com.zhnjy.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.zhnjy.util.db.GraduateDB;
import com.zhnjy.util.db.StudentDB;

public class GraduateAction {
	private String idCard;
	private String password;
	private String name;
	private String number;
	private String nationality;
	private String sex;
	private String phone1;
	private String studyLevel;
	private String siteId;
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
	
	public String get(){
		
		List students = StudentDB.getList(idCard, name, number, nationality, sex, phone1, phone2, siteId, studyLevel, bSchoolId, bMajorId, bExamNumber, bStudyForm, bStudyType, bStatus, bJoinTime, zSchoolId, zMajorId, zExamNumber, zStudyForm, zStudyType, zStatus, zJoinTime);
		List graduates = GraduateDB.getGraduate(students);
		
		Iterator it = graduates.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("graduates",graduates);
		return "getsuccess";
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
	
	
}
