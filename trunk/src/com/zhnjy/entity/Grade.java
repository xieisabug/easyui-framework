package com.zhnjy.entity;

import com.zhnjy.util.db.AdminDB;
import com.zhnjy.util.db.CourseDB;
import com.zhnjy.util.db.StudentDB;

public class Grade {
	private int id;
	private String stuIdCard;
	private String stuName;
	private String grade;
	private String courseNumber;
	private String courseName;
	private String time;
	private int operaterId;
	private String operaterName;


	public Grade() {
		super();
	}


	public Grade(int id, String stuIdCard, String grade, String courseNumber,
			String time, int operaterId) {
		super();
		this.id = id;
		this.stuIdCard = stuIdCard;
		this.grade = grade;
		this.courseNumber = courseNumber;
		this.time = time;
		this.operaterId = operaterId;
		this.stuName = StudentDB.getStudent(stuIdCard).getName();
		this.courseName = CourseDB.getCourse(CourseDB.getId(courseNumber)).getName();
		this.operaterName = AdminDB.getNameById(operaterId);
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStuIdCard() {
		return stuIdCard;
	}

	public void setStuIdCard(String stuIdCard) {
		this.stuIdCard = stuIdCard;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStuName() {
		return stuName;
	}


	public void setStuName(String stuName) {
		this.stuName = stuName;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getOperaterName() {
		return operaterName;
	}


	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}


	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getOperaterId() {
		return operaterId;
	}

	public void setOperaterId(int operaterId) {
		this.operaterId = operaterId;
	}


	@Override
	public String toString() {
		return "Grade [courseNumber=" + courseNumber + ", grade=" + grade
				+ ", id=" + id + ", operaterId=" + operaterId + ", stuIdCard="
				+ stuIdCard + ", time=" + time + "]";
	}


}
