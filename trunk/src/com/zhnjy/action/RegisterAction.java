package com.zhnjy.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.util.db.PoiHelper;
import com.zhnjy.util.db.RegisterDB;

public class RegisterAction extends ActionSupport{

	private String schoolId;
	private String startTime;
	private String endTime;
	
	private File file;
	
	public String check(){
		HashMap schMap = null;
		HashMap proMap = null;
		//反馈结果，学生的id号为主键，值为0或者1，0代表该学生没有注册，1代表该学生专业有错，返回的专业为他在省里面的专业，学校的专业可以自己查看。
		HashMap<String,String> resultMap = new HashMap<String, String> ();
		
		int _schoolId = Integer.parseInt(schoolId);
		//判断是专科还是本科。
		if(RegisterDB.isBK(_schoolId)){
			schMap = RegisterDB.getBkMap(_schoolId, startTime, endTime);
		}else{
			schMap = RegisterDB.getZkMap(_schoolId, startTime, endTime);
		}
		Sheet sheet = PoiHelper.getSheet(file);
		proMap = PoiHelper.getIdCardMap(sheet);
		
		//遍历学校要注册的学生
		Iterator schIt = schMap.keySet().iterator();
		while(schIt.hasNext()){
			String str = (String) schIt.next();
			if(!proMap.containsKey(str)){
				resultMap.put(str, "0");
			}else{
				if( !schMap.get(str).equals(proMap.get(str))){
					resultMap.put(str, "1");
				}
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("resultMap", resultMap);
		return "checksuccess";
	}
	
	
	


	public String getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	
	
}
