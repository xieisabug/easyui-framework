package com.zhnjy.entity;

import com.zhnjy.util.string.ValueHelper;

public class School {

	private int id;
	private String name;
	private String level;
	private String levelName;

	public School() {
		super();
	}

	public School(int id, String name, String level) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		levelName = ValueHelper.getNameByLevel(level);
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
		return "School [id=" + id + ", level=" + level + ", name=" + name + "]";
	}

}
