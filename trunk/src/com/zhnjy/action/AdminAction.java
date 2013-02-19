package com.zhnjy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.User;
import com.zhnjy.util.db.AdminDB;

public class AdminAction extends ActionSupport {

	private static final long serialVersionUID = -8703179992876215177L;
	
	private int id;
	private String userName;
	private String password;
	private int level;
	private String remark;
	private String siteId;
	/**
	 * 添加管理员
	 * 
	 * @return
	 */
	public String add() {
		int _siteId = Integer.parseInt(siteId);
		User user = new User(id, userName, password, level, remark, _siteId);
		if (AdminDB.add(user) != 0) {
			return "addsuccess";
		} else {
			return "addfail";
		}
	}

	/**
	 * 删除管理员
	 * 
	 * @return
	 */
	public String delete() {
		if (AdminDB.delete(id) == 1) {
			return "deletesuccess";
		} else {
			return "deletefail";
		}

	}

	/**
	 *修改管理员信息
	 * 
	 * @return
	 */
	public String update() {
		int _siteId = Integer.parseInt(siteId);
		User user = new User(id, userName, password, level, remark, _siteId);
		if (AdminDB.update(user) != 0) {
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
		HttpSession session = ServletActionContext.getRequest().getSession();
		User u = (User) session.getAttribute("user");
		List<User> admins = AdminDB.getListByPage(u.getLevel(), u.getSiteId());
		String result = JSONSerializer.toJSON(admins).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.append(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 所有的get set方法。
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

}
