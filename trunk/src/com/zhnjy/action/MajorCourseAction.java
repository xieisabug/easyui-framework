package com.zhnjy.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.xslt.ArrayAdapter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.Course;
import com.zhnjy.entity.Major;
import com.zhnjy.entity.Student;
import com.zhnjy.util.db.CourseDB;
import com.zhnjy.util.db.ExamItemDB;
import com.zhnjy.util.db.ExamSetDB;
import com.zhnjy.util.db.MajorCourseDB;
import com.zhnjy.util.db.MajorDB;

public class MajorCourseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	// 在点设置课程的链接的时候要传该专业的id过来，通过id得到该专业已有的课程。
	private String majorId;
	// 这是设置课程的课程编码的代码串
	private String courseNos;
	private String courseId;
	// 课程数
	private String courseSize;
	private String unit;
	/**
	 * 通过专业Id查询该专业的所有课程
	 * @return
	 */
	public String query() {
		List courseList = null;
		int _majorId = Integer.parseInt(majorId);
		courseList = MajorCourseDB.getCourselist(_majorId);
		Major major = MajorDB.getMajor(_majorId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("courseList", courseList);
		request.setAttribute("major", major);
		request.setAttribute("unitNum", Integer.parseInt(ExamSetDB.getUnitNum()));//这个书要在配置里面取得
		return "querysuccess";
	}
	/**
	 * 增加一个专业下的一门课程
	 * @return
	 */
	public String add() {
		String[] _courseNos = courseNos.split(" ");
		
		
		//判断要设置的课程是不是存在，没有添加就转到失败界面
		for (int i = 0; i < _courseNos.length; i++) {
			String number = _courseNos[i];
			if(!CourseDB.isHave(number)){
				return "addfail";
			}
		}
		int _majorId = Integer.parseInt(majorId);
		for (int i = 0; i < _courseNos.length; i++) {
			int courseId = CourseDB.getId(_courseNos[i]);
			if(courseId == -1) {
				return "addfail";
			}
			MajorCourseDB.add(_majorId, courseId);
		}

		List courseList = null;
		courseList = MajorCourseDB.getCourselist(_majorId);
		Major major = MajorDB.getMajor(_majorId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("courseList", courseList);
		request.setAttribute("major", major);
		request.setAttribute("unitNum", Integer.parseInt(ExamSetDB.getUnitNum()));//这个书要在配置里面取得
		return "addsuccess";
	}
	/**
	 * 删除该专业下的该门课程
	 * @return
	 */
	public String delete() {
		int _majorId = Integer.parseInt(majorId);
		int _courseId = Integer.parseInt(courseId);
		if (MajorCourseDB.delete(_majorId, _courseId) != 0) {
			return "deletesuccess";
		} else {
			return "deletefail";
		}

	}
	/**
	 * 设置专业下的可报考课程
	 * @return
	 */
	public String set() {
		int _majorId = Integer.parseInt(majorId);
		// 这个是课程数
		int _courseSize = Integer.parseInt(courseSize);
		HttpServletRequest reqeust = ServletActionContext.getRequest();

		for (int i = 0; i < _courseSize; i++) {
			String courseId = reqeust.getParameter("id" + i);
			int _courseId = Integer.parseInt(courseId);
			String unit = reqeust.getParameter("unit" + i);
			int _unit = Integer.parseInt(unit);
			String valid= reqeust.getParameter("valid" + i);
			if(valid == null){
				MajorCourseDB.set(_majorId, _courseId, 0, _unit);
			}else{
				MajorCourseDB.set(_majorId, _courseId, 1, _unit);
			}
			
		}
		return "setsuccess";
	}
 
	
	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public String getCourseNos() {
		return courseNos;
	}

	public void setCourseNos(String courseNos) {
		this.courseNos = courseNos;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseSize() {
		return courseSize;
	}

	public void setCourseSize(String courseSize) {
		this.courseSize = courseSize;
	}

}
