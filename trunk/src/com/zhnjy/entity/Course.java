package com.zhnjy.entity;

public class Course {
	private int id;
	private String name;
	private String number;
	private String level;
	private String remark;
	// 用来标识该们课程是否已经被选
	private int flag;
	private int unit;

	public Course() {
		super();
	}

	public Course(int id, String name, String number, String level,
			String remark) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.level = level;
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", level=" + level + ", name=" + name
				+ ", number=" + number + ", remark=" + remark + "]";
	}

}
