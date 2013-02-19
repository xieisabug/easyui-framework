package com.zhnjy.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.School;
import com.zhnjy.util.db.CourseDB;
import com.zhnjy.util.db.MajorCourseDB;
import com.zhnjy.util.db.MajorDB;
import com.zhnjy.util.db.SchoolDB;
import com.zhnjy.util.db.SchoolMajorDB;

public class SchoolMajorAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	// 在点设置专业的链接的时候要传该学校的id过来，通过id得到该学校已有的专业。
	private String schoolId;
	// 这是设置课程的课程编码的代码串
	private String majorNos;
	private String majorId;

	public String query() {
		List majorList = null;
		School school = null;

		int _schoolId = Integer.parseInt(schoolId);
		school = SchoolDB.getSchool(_schoolId);
		majorList = SchoolMajorDB.getMajorlist(_schoolId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("majorList", majorList);
		request.setAttribute("school", school);
		return "querysuccess";
	}

	public String add() {
		String[] _majorNos = majorNos.split(" ");
		int _schoolId = Integer.parseInt(schoolId);
		int k = 0;
	//判断要设置的专业是不是已经添加了，没有添加就转到失败界面
		for (int i = 0; i < _majorNos.length; i++) {
			String number = _majorNos[i];
			if(!MajorDB.isHave(number)){
				return "addfail";
			}
		}
		
		for (int i = 0; i < _majorNos.length; i++) {
			int majorId = MajorDB.getId(_majorNos[i]);
			if (majorId == -1) {
				return "addfail";
			}
			if (SchoolMajorDB.add(_schoolId, majorId) != 0) {
				k++;
			}
		}
		if (k == _majorNos.length) {
			query();
			return "addsuccess";
		} else {
			return "addfail";
		}
	}

	public String delete() {
		int _majorId = Integer.parseInt(majorId);
		int _schoolId = Integer.parseInt(schoolId);
		if (SchoolMajorDB.delete(_schoolId, _majorId) != 0) {
			return "deletesuccess";
		} else {
			return "deletefail";
		}

	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getMajorNos() {
		return majorNos;
	}

	public void setMajorNos(String majorNos) {
		this.majorNos = majorNos;
	}

	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

}
