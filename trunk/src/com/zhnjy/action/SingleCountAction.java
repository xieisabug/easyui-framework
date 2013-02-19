package com.zhnjy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.Student;
import com.zhnjy.entity.User;
import com.zhnjy.util.db.ExamItemDB;
import com.zhnjy.util.db.ExamSetDB;

public class SingleCountAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private String courseId;
	private int page;
	
	public String jump(){
		String unit = ExamSetDB.getUnitNum();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("unit", unit);
		return "jumpsuccess";
	}
	/**
	 * 通过课程id找到报考了这门课程的学生。
	 * @return
	 */
	public String getDate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int _courseId = Integer.parseInt(courseId);
		
		List<Student> list = ExamItemDB.getStudentByCourseId(_courseId,user);
		//HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return "getdatesuccess";
	}
	
	public String getDatas(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int _courseId = Integer.parseInt(courseId);
		
		List<Student> list = ExamItemDB.getStudentByCourseId(_courseId,user);
		//HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return "getdatesuccess";
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
