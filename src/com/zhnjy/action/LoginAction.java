package com.zhnjy.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.entity.User;
import com.zhnjy.util.db.LoginDB;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	// 用于封装用户名和密码的属性
	private String userName;
	private String password;
	private User user;

	public String execute() {
		user = LoginDB.login(userName, password);

		if (user == null) {
			return "fail";
		} else {
			Map session = ActionContext.getContext().getSession();
			session.put("user", user);
			return SUCCESS;
		}
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

}
