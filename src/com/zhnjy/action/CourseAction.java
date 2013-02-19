package com.zhnjy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.Course;
import com.zhnjy.util.db.CourseDB;

public class CourseAction extends ActionSupport {
	private int id;
	private String name;
	private String number;
	private String level;
	private String remark;
	private int page;
	private int rows;
	
	public String add() {
		Course course = new Course(id, name, number, level, remark);
		if (CourseDB.add(course) != 0) {
			return "addsuccess";
		} else {
			return "addfail";
		}
	}

	public String delete() {
		if (CourseDB.delete(id) != 0) {
			return "deletesuccess";
		} else {
			return "deletefail";
		}
	}

	public String update() {
		Course course = new Course(id, name, number, level, remark);
		if (CourseDB.update(course) != 0) {
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
	public void getDatas() {
		List<Course> courses = CourseDB.getListByPage(name, number, level, page, rows);
		int pageCount = CourseDB.getPageCount(name, number, level);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		//ui需要显示数据的总页数
        jsonMap.put("total", pageCount);
        //ui需要实现数据的总记录数
        jsonMap.put("rows", courses);
        
        JSONObject result = JSONObject.fromObject(jsonMap);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.append(result.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	
}
