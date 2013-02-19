package com.zhnjy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.zhnjy.util.db.ExamSetDB;
import com.zhnjy.util.db.WelcomeDB;

public class WelcomeAction {
	
	private int level;
	private int siteId;
	
	public void getInfo(){
		int stuNum = WelcomeDB.getStudentNum(level, siteId);
		String examDate = ExamSetDB.getExamDate();
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("stuNum", stuNum);
		jsonMap.put("examDate", examDate);
		
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
}
