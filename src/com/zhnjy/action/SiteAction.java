package com.zhnjy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.Site;
import com.zhnjy.util.db.CourseDB;
import com.zhnjy.util.db.SiteDB;

public class SiteAction extends ActionSupport {

	private static final long serialVersionUID = 1L;		
	private int id;
	private String name;
	private int page;
	// 添加站点
	public String add() {
		Site site = new Site(id, name);
		if (SiteDB.add(site) != 0) {
			return "addsuccess";
		} else {
			return "addfail";
		}
	}

	public String delete() {
		if (SiteDB.delete(id) != 0) {
			return "deletesuccess";
		} else {
			return "deletefail";
		}

	}

	public String update() {
		Site site = new Site(id, name);
		if (SiteDB.update(site) != 0) {
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
		List sites = SiteDB.getListByPage(page);
		int pageCount = SiteDB.getPageCount();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("sites", sites);//得到课程列表
		request.setAttribute("pageCount", pageCount);//得到总页数
		return "getSitesSuccess";
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	
}
