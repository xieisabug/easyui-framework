package com.zhnjy.entity;

import com.zhnjy.util.db.AdminDB;

public class Message {
	private int id;
	private String title;     
	private String content;
	private String time;
	private int operaterId;
	private String operaterName;
	
	public Message() {
		super();
	}
	/**
	 * 数据库字段构造函数
	 * @param id
	 * @param title
	 * @param content
	 * @param time
	 * @param operaterId
	 */
	public Message(int id, String title, String content, String time,
			int operaterId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
		this.operaterId = operaterId;
		if(operaterId != 0){
			this.operaterName = AdminDB.getNameById(operaterId);
		}else{
			this.operaterName = "无";
		}
	}
	
	
	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getOperaterName() {
		return operaterName;
	}

	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	} 

	

}
