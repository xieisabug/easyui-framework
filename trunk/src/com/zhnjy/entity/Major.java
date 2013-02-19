package com.zhnjy.entity;

import com.zhnjy.util.string.ValueHelper;

public class Major {

	private int id;
	private String name;
	private String number;
	private String level;
	private String levelName;

	public Major() {
		super();
	}


	public Major(int id, String name, String number, String level) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.level = level;
		levelName = ValueHelper.getNameByLevel(level);
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}


	public String getLevelName() {
		return levelName;
	}


	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}


	@Override
	public String toString() {
		return "Major [id=" + id + ", level=" + level + ", name=" + name
				+ ", number=" + number + "]";
	}


}
