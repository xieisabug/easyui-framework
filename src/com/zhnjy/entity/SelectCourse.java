package com.zhnjy.entity;

import com.zhnjy.util.db.StudentDB;

public class SelectCourse {
	private String idCard;
	private String number;
	private String name;
	private String sex;
	private String nationality;
	private String phone1;
	// 这些是可以为空的属性
	private String remark;

	private String bStudyForm;
	private String bStudyType;
	private String bExamNumber;
	private String bStatus;
	private String bJoinTime;
	
	private String zStudyForm;
	private String zStudyType;
	private String zExamNumber;
	private String zStatus;
	private String zJoinTime;

	private String bSchoolName;
	private String bMajorName;
	private String zSchoolName;
	private String zMajorName;
	private String siteName;
	private String studyLevelName;
	
	private String firstUnit;
	private String secondUnit;
	private String thirdUnit;
	private String fourthUnit;
	private String fifthUnit;
	private String sixthUnit;
	private String examDate;

	public SelectCourse(String idCard, String firstUnit, String secondUnit, String thirdUnit,
			String fourthUnit, String fifthUnit, String sixthUnit, String examDate) {
		this.idCard = idCard;
		this.examDate = examDate;
		Student s = StudentDB.getStudent(idCard);
		this.number = s.getNumber();
		this.name = s.getName();
		this.sex = s.getSex();
		this.nationality = s.getNationality();
		this.phone1 = s.getPhone1();
		this.remark = s.getRemark();
		this.bExamNumber = s.getbExamNumber();
		this.bJoinTime = s.getbJoinTime();
		this.bMajorName = s.getbMajorName();
		this.bSchoolName = s.getbSchoolName();
		this.bStatus = s.getbStatus();
		this.bStudyForm = s.getbStudyForm();
		this.bStudyType = s.getbStudyType();
		this.zExamNumber = s.getzExamNumber();
		this.zJoinTime = s.getzJoinTime();
		this.zMajorName = s.getzMajorName();
		this.zSchoolName = s.getzSchoolName();
		this.zStatus = s.getzStatus();
		this.zStudyForm = s.getzStudyForm();
		this.zStudyType = s.getzStudyType();
		this.siteName = s.getSiteName();
		this.studyLevelName = s.getStudyLevelName();
		this.firstUnit = firstUnit;
		this.secondUnit = secondUnit;
		this.thirdUnit = thirdUnit;
		this.fourthUnit = fourthUnit;
		this.fifthUnit = fifthUnit;
		this.sixthUnit = sixthUnit;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getbSchoolName() {
		return bSchoolName;
	}

	public void setbSchoolName(String bSchoolName) {
		this.bSchoolName = bSchoolName;
	}

	public String getbMajorName() {
		return bMajorName;
	}

	public void setbMajorName(String bMajorName) {
		this.bMajorName = bMajorName;
	}

	public String getzSchoolName() {
		return zSchoolName;
	}

	public void setzSchoolName(String zSchoolName) {
		this.zSchoolName = zSchoolName;
	}

	public String getzMajorName() {
		return zMajorName;
	}

	public void setzMajorName(String zMajorName) {
		this.zMajorName = zMajorName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getStudyLevelName() {
		return studyLevelName;
	}

	public void setStudyLevelName(String studyLevelName) {
		this.studyLevelName = studyLevelName;
	}

	public String getFirstUnit() {
		return firstUnit;
	}

	public void setFirstUnit(String firstUnit) {
		this.firstUnit = firstUnit;
	}

	public String getSecondUnit() {
		return secondUnit;
	}

	public void setSecondUnit(String secondUnit) {
		this.secondUnit = secondUnit;
	}

	public String getThirdUnit() {
		return thirdUnit;
	}

	public void setThirdUnit(String thirdUnit) {
		this.thirdUnit = thirdUnit;
	}

	public String getFourthUnit() {
		return fourthUnit;
	}

	public void setFourthUnit(String fourthUnit) {
		this.fourthUnit = fourthUnit;
	}

	public String getFifthUnit() {
		return fifthUnit;
	}

	public void setFifthUnit(String fifthUnit) {
		this.fifthUnit = fifthUnit;
	}

	public String getSixthUnit() {
		return sixthUnit;
	}

	public void setSixthUnit(String sixthUnit) {
		this.sixthUnit = sixthUnit;
	}

}
