package com.zhnjy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.Major;
import com.zhnjy.util.db.CourseDB;
import com.zhnjy.util.db.MajorDB;

public class MajorAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String number;
	private String level;
	private int page;
	
	public String add() {
		Major major = new Major(id, name, number, level);
		if (MajorDB.add(major) != 0) {
			return "addsuccess";
		} else {
			return "addfail";
		}
	}

	public String delete() {
		if (MajorDB.delete(id) != 0) {
			return "deletesuccess";
		} else {
			return "deletefail";
		}
	}

	public String update() {
		Major major = new Major(id, name, number, level);
		if (MajorDB.update(major) != 0) {
			return "updatesuccess";
		} else {
			return "updatefail";
		}
	}
	
	/**新！！
	 * 通过网页传过来的各个参数的值和页码，
	 * 来查询出一系列数据，再将数据放到request
	 * 中，转到网页。
	 */
	public String getDatas() {
		List majors = MajorDB.getListByPage(name, level, number, page);
		int pageCount = MajorDB.getPageCount(name, number, level);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("majors", majors);//得到课程列表
		request.setAttribute("pageCount", pageCount);//得到总页数
		return "getMajorsSuccess";
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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

}
