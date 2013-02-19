package com.zhnjy.entity;

import com.zhnjy.util.db.RecordItemDB;
import com.zhnjy.util.db.StudentDB;

public class Record {
	private int id;
	private String idCard;
	private String stuName;
	private String stuNumber;
	private String stuSiteName;
	private String stuBSchoolName;
	private String stuBMajorName;
	private String stuBExamNumber;
	private String stuZSchoolName;
	private String stuZMajorName;
	private String stuZExamNumber;
	private int recordItemId;
	private String recordItemName;
	private float money;
	private String bill;
	private String yearth;
	private String date;
	private float goUp;
	private String goUpBill;
	private float goDown;
	private String goDownBill;
	private String goUpWhere;
	private String goDownWhere;
	private String goMoneyWhere;
	private String remark;

	public Record() {
		super();
	}

	public Record(int id, String idCard, int recordItemId, float money,
			String bill, String yearth, String date, float goUp,
			String goUpBill, float goDown, String goDownBill, String goUpWhere,
			String goDownWhere, String goMoneyWhere, String remark) {
		super();
		this.id = id;
		this.idCard = idCard;
		Student s = StudentDB.getStudent(idCard);
		stuName = s.getName();
		stuNumber = s.getNumber();
		stuSiteName = s.getSiteName();
		stuBSchoolName = s.getbSchoolName();
		stuBMajorName = s.getbMajorName();
		stuBExamNumber = s.getbExamNumber();
		stuZSchoolName = s.getzSchoolName();
		stuZMajorName = s.getzMajorName();
		stuZExamNumber = s.getzExamNumber();
		this.recordItemId = recordItemId;
		this.recordItemName = RecordItemDB.getNameById(recordItemId);
		this.money = money;
		this.bill = bill;
		this.yearth = yearth;
		this.date = date;
		this.goUp = goUp;
		this.goUpBill = goUpBill;
		this.goDown = goDown;
		this.goDownBill = goDownBill;
		this.goUpWhere = goUpWhere;
		this.goDownWhere = goDownWhere;
		this.goMoneyWhere = goMoneyWhere;
		this.remark = remark;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public int getId() {
		return id;
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGoUpBill() {
		return goUpBill;
	}

	public void setGoUpBill(String goUpBill) {
		this.goUpBill = goUpBill;
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

	public int getRecordItemId() {
		return recordItemId;
	}

	public void setRecordItemId(int recordItemId) {
		this.recordItemId = recordItemId;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public float getGoUp() {
		return goUp;
	}

	public void setGoUp(float goUp) {
		this.goUp = goUp;
	}

	public float getGoDown() {
		return goDown;
	}

	public void setGoDown(float goDown) {
		this.goDown = goDown;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYearth() {
		return yearth;
	}

	public void setYearth(String yearth) {
		this.yearth = yearth;
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

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}

	public String getStuBSchoolName() {
		return stuBSchoolName;
	}

	public void setStuBSchoolName(String stuBSchoolName) {
		this.stuBSchoolName = stuBSchoolName;
	}

	public String getStuBMajorName() {
		return stuBMajorName;
	}

	public void setStuBMajorName(String stuBMajorName) {
		this.stuBMajorName = stuBMajorName;
	}

	public String getStuBExamNumber() {
		return stuBExamNumber;
	}

	public void setStuBExamNumber(String stuBExamNumber) {
		this.stuBExamNumber = stuBExamNumber;
	}

	public String getStuZSchoolName() {
		return stuZSchoolName;
	}

	public void setStuZSchoolName(String stuZSchoolName) {
		this.stuZSchoolName = stuZSchoolName;
	}

	public String getStuZMajorName() {
		return stuZMajorName;
	}

	public void setStuZMajorName(String stuZMajorName) {
		this.stuZMajorName = stuZMajorName;
	}

	public String getStuZExamNumber() {
		return stuZExamNumber;
	}

	public void setStuZExamNumber(String stuZExamNumber) {
		this.stuZExamNumber = stuZExamNumber;
	}

	public String getStuSiteName() {
		return stuSiteName;
	}

	public void setStuSiteName(String stuSiteName) {
		this.stuSiteName = stuSiteName;
	}

	public String getRecordItemName() {
		return recordItemName;
	}

	public void setRecordItemName(String recordItemName) {
		this.recordItemName = recordItemName;
	}

}
