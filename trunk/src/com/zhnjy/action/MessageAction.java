package com.zhnjy.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.zhnjy.entity.Message;
import com.zhnjy.util.db.CourseDB;
import com.zhnjy.util.db.MessageDB;

public class MessageAction {

	private String id;
	private String title;
	private String content;
	private String operaterId;
	private int page;
	
	public String add() {
		int _operaterId = 0;
		if (operaterId != null || !operaterId.equals("")) {
			 _operaterId = Integer.parseInt(operaterId);
		}
		
		//获得系统时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new java.util.Date());
		Message message = new Message(0, title, content, time, _operaterId);
		
		if(MessageDB.add(message) != 0){
			return "addsuccess";
		}else{
			return "addfail";
		}
	}
	

	public String delete(){
		
		int _id = Integer.parseInt(id);
		if(MessageDB.delete(_id) != 0){
			return "deletesuccess";
		}else{
			return "deletefail";
		}
	}
	
	public String update(){
		int _id = Integer.parseInt(id);
		int _operaterId = 0;
		if (operaterId != null || !operaterId.equals("")) {
			 _operaterId = Integer.parseInt("operaterId");
		}
		
		Message message = new Message(_id, title, content,"",_operaterId);
		if(MessageDB.update(message) != 0){
			return "updatesuccess";
		}else{
			return "updatefail";
		}
	}
	
	/**
	 * 通过网页传过来的各个参数的值和页码，
	 * 来查询出一系列数据，再将数据放到request
	 * 中，转到网页。
	 */
	public String getDatas() {
		List messages = MessageDB.getListByPage(page);
		int pageCount = MessageDB.getPageCount();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("messages", messages);//得到课程列表
		request.setAttribute("pageCount", pageCount);//得到总页数
		return "getMessagesSuccess";
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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


	public String getOperaterId() {
		return operaterId;
	}

	public void setOperaterId(String operaterId) {
		this.operaterId = operaterId;
	}

}
