package com.zhnjy.entity;

import com.zhnjy.util.db.MajorDB;
import com.zhnjy.util.db.SchoolDB;
import com.zhnjy.util.db.SiteDB;

public class Student {
	// 这些是不能为空的属性
	private String idCard;
	private String password;
	private String number;
	private String name;
	private String sex;
	private String nationality;
	private String phone1;
	private String studyLevel;
	private int siteId;
	// 这些是可以为空的属性
	private String phone2;
	private String remark;

	private String qq;
	private String email;
	private String address;
	private String postCode;
	private String politicalLevel;
	private String tuition;

	private int bSchoolId;
	private int bMajorId;
	private String bStudyForm;
	private String bStudyType;
	private String bExamNumber;
	private String bStatus;
	private String bJoinTime;
	private int zSchoolId;
	private int zMajorId;
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
	
	public Student() {
	}

	// 非空属性构造方法
	public Student(String idCard, String password, String number, String name,
			String sex, String nationality, String phone1, String studyLevel,
			int siteId) {
		super();
		this.idCard = idCard;
		this.password = password;
		this.number = number;
		this.name = name;
		this.sex = sex;
		this.nationality = nationality;
		this.phone1 = phone1;
		this.studyLevel = studyLevel;
		this.siteId = siteId;
	}




	public Student(String idCard, String password, String number, String name,
			String sex, String nationality, String phone1, String studyLevel,
			int siteId, String phone2, String remark, String qq, String email,
			String address, String postCode, String politicalLevel,
			String tuition, int bSchoolId, int bMajorId, String bStudyForm,
			String bStudyType, String bExamNumber, String bStatus,
			String bJoinTime, int zSchoolId, int zMajorId, String zStudyForm,
			String zStudyType, String zExamNumber, String zStatus,
			String zJoinTime) {
		super();
		this.idCard = idCard;
		this.password = password;
		this.number = number;
		this.name = name;
		this.sex = sex;
		this.nationality = nationality;
		this.phone1 = phone1;
		this.studyLevel = studyLevel;
		this.siteId = siteId;
		this.phone2 = phone2;
		this.remark = remark;
		this.qq = qq;
		this.email = email;
		this.address = address;
		this.postCode = postCode;
		this.politicalLevel = politicalLevel;
		this.tuition = tuition;
		this.bSchoolId = bSchoolId;
		this.bMajorId = bMajorId;
		this.bStudyForm = bStudyForm;
		this.bStudyType = bStudyType;
		this.bExamNumber = bExamNumber;
		this.bStatus = bStatus;
		this.bJoinTime = bJoinTime;
		this.zSchoolId = zSchoolId;
		this.zMajorId = zMajorId;
		this.zStudyForm = zStudyForm;
		this.zStudyType = zStudyType;
		this.zExamNumber = zExamNumber;
		this.zStatus = zStatus;
		this.zJoinTime = zJoinTime;
		if(bSchoolId != 0) {
			bSchoolName = SchoolDB.getSchoolNameById(bSchoolId);
		} else {
			bSchoolName = "无";
		}
		if(bMajorId != 0) {
			bMajorName = MajorDB.getMajor(bMajorId).getName();
		} else {
			bMajorName = "无";
		}
		if(zSchoolId != 0) {
			zSchoolName = SchoolDB.getSchoolNameById(zSchoolId);
		} else {
			zSchoolName = "无";
		}
		if(zMajorId != 0) {
			zMajorName = MajorDB.getMajor(zMajorId).getName();
		} else {
			zMajorName = "无";
		}
		if(siteId != 0) {
			siteName = SiteDB.getSiteNameById(siteId);
		} else {
			siteName = "无";
		}
		if(studyLevel.equals("zk")){
			studyLevelName = "专科";
		} else if(studyLevel.equals("bk")) {
			studyLevelName = "本科";
		} else {
			studyLevelName = "套读";
		}
	}

	// 得到非空属性,以String返回
	public String getMust() {
		String str = "idCard,number,name,sex,nationality,phone1,selectMajor,selectSchool,studyLevel";
		return str;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
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

	public String getStudyLevel() {
		return studyLevel;
	}

	public void setStudyLevel(String studyLevel) {
		this.studyLevel = studyLevel;
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

	

	public int getbSchoolId() {
		return bSchoolId;
	}

	public void setbSchoolId(int bSchoolId) {
		this.bSchoolId = bSchoolId;
	}

	public int getbMajorId() {
		return bMajorId;
	}

	public void setbMajorId(int bMajorId) {
		this.bMajorId = bMajorId;
	}

	public int getzSchoolId() {
		return zSchoolId;
	}

	public void setzSchoolId(int zSchoolId) {
		this.zSchoolId = zSchoolId;
	}

	public int getzMajorId() {
		return zMajorId;
	}

	public void setzMajorId(int zMajorId) {
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

	@Override
	public String toString() {
		return "Student [address=" + address + ", bExamNumber=" + bExamNumber
				+ ", bJoinTime=" + bJoinTime + ", bMajorId=" + bMajorId
				+ ", bSchoolId=" + bSchoolId + ", bStatus=" + bStatus
				+ ", bStudyForm=" + bStudyForm + ", bStudyType=" + bStudyType
				+ ", email=" + email + ", idCard=" + idCard + ", name=" + name
				+ ", nationality=" + nationality + ", number=" + number
				+ ", password=" + password + ", phone1=" + phone1 + ", phone2="
				+ phone2 + ", politicalLevel=" + politicalLevel + ", postCode="
				+ postCode + ", qq=" + qq + ", remark=" + remark + ", sex="
				+ sex + ", siteId=" + siteId + ", studyLevel=" + studyLevel
				+ ", tuition=" + tuition + ", zExamNumber=" + zExamNumber
				+ ", zJoinTime=" + zJoinTime + ", zMajorId=" + zMajorId
				+ ", zSchoolId=" + zSchoolId + ", zStatus=" + zStatus
				+ ", zStudyForm=" + zStudyForm + ", zStudyType=" + zStudyType
				+ "]";
	}

	

}
