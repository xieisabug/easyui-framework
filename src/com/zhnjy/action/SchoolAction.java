package com.zhnjy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.School;
import com.zhnjy.util.db.CourseDB;
import com.zhnjy.util.db.SchoolDB;

public class SchoolAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String level;
	private int page;

	public String add() {
		School school = new School(id, name, level);
		if (SchoolDB.add(school) != 0) {
			return "addsuccess";
		} else {
			return "addfail";
		}
	}

	public String delete() {
		if (SchoolDB.delete(id) != 0) {
			return "deletesuccess";
		} else {
			return "deletefail";
		}
	}

	public String update() {
		School school = new School(id, name, level);
		if (SchoolDB.update(school) != 0) {
			return "updatesuccess";
		} else {
			return "updatefail";
		}
	}
	
	/**
	 * 通过网页传过来的各个参数的值和页码，
	 * 来查询出一系列数据，再将数据放到request
	 * 中，转到网页。
	 */
	public String getDatas() {
		List schools = SchoolDB.getListByPage(name, level, page);
		int pageCount = SchoolDB.getPageCount(name, level);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("schools", schools);//得到课程列表
		request.setAttribute("pageCount", pageCount);//得到总页数
		return "getSchoolsSuccess";
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	// get set方法

}
