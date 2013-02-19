package com.zhnjy.action;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import com.opensymphony.xwork2.ActionSupport;
import com.zhnjy.util.db.ExamSetDB;


public class ExamSetAction extends ActionSupport {
	private String examDate;
	private String unitNum;
	//设置考期
	public String examDate(){
		if(ExamSetDB.examDate(examDate) != 0){
			read();
			return "examdatesuccess";
		}else{
			return "examdatefail";
		}
}
	
	//设置单元数,单元数一更改之前所有的day和unit都要删除
	public String unitNum(){
		
		int _unitNum = Integer.parseInt(ExamSetDB.getUnitNum()); 
		ExamSetDB.deleteUnit(_unitNum);
		int dayNum;
		if(_unitNum%2 == 1){
			dayNum = _unitNum/2+1;
		}else{
			dayNum = _unitNum/2;
		}
		ExamSetDB.deleteDay(dayNum);
		if(ExamSetDB.unitNum(unitNum) != 0){
			read();
			return "unitnumsuccess";
		}else{
			return "unitnumfail";
		}
	}
	
	//设置天数
	public String unitTime(){
		
		//设置之前先删除所有天数信息；
		int _unitNum1 = Integer.parseInt(ExamSetDB.getUnitNum()); 
		ExamSetDB.deleteUnit(_unitNum1);
		int dayNum;
		if(_unitNum1%2 == 1){
			dayNum = _unitNum1/2+1;
		}else{
			dayNum = _unitNum1/2;
		}
		ExamSetDB.deleteDay(dayNum);
		
		HttpServletRequest reqeust = ServletActionContext.getRequest();
		String unitNum =ExamSetDB.getUnitNum();
		int _unitNum = Integer.parseInt(unitNum);
		int k = 1;
		for (int i = 0; i < _unitNum/2; i++) {
			int j = i + 1;
			String date = reqeust.getParameter("day" + j);
			//插入dayi
			ExamSetDB.add("day"+j, date);
			String unit1 = reqeust.getParameter("unit" + k+"time");
			ExamSetDB.add("unit"+k+"time", unit1);
			k++;
			String unit2 = reqeust.getParameter("unit" + k+"time");
			ExamSetDB.add("unit"+k+"time", unit2);
			k++;
		}
		//这个是处理奇数天的时候
		if(_unitNum % 2 ==1){
			
			int j = _unitNum/2+1;
			String date = reqeust.getParameter("day" + j);
			ExamSetDB.add("day"+j, date);
			String unit1 = reqeust.getParameter("unit" + k+"time");	
			ExamSetDB.add("unit"+k+"time", unit1);
		}
		read();
		return "unittimesuccess";

	}
	//读取全部配置参数
	public String read(){
		HashMap<String,String> resultMap = new HashMap<String, String>();
		///得到单元数
		resultMap.put("unitNum", ExamSetDB.getUnitNum());
		int temp = Integer.parseInt(ExamSetDB.getUnitNum());
		for(int i = 1 ; i <= temp;i++){
			resultMap.put("unit"+i+"time", ExamSetDB.get("unit"+i+"time"));	
		}
		int d;
		if(temp % 2 == 1){
			d = temp/2+1;
		}else{
			d= temp/2;
		}
		for(int i = 1; i <= d; i++){
			resultMap.put("day"+i, ExamSetDB.get("day"+i));
		}
			
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("resultMap", resultMap);//得到考期
		resultMap.put("examDate", ExamSetDB.getExamDate());
		
		return "readsuccess";
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getUnitNum() {
		return unitNum;
	}
	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}
	
}
