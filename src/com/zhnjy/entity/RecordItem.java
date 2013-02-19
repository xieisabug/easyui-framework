package com.zhnjy.entity;

/**
 * 财务的收费项目
 * 
 * @author lenovo
 * 
 */
public class RecordItem {
	private int id;
	private String name;

	public RecordItem(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public RecordItem() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
