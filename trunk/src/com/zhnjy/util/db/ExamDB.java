package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ExamDB {

	//筛选符合报考学习，符合考期的学生
	public static HashMap getMap(int schoolId,String examDate){
		List stuList = StudentDB.getStudentBySchoolId(schoolId);
		Iterator it = stuList.iterator();
		HashMap map = new HashMap<String, List>();
		while(it.hasNext()){
			String idCard = (String) it.next();
			map.put(idCard, ExamItemDB.getList(idCard, examDate));
		}
		return map;
	}
	
}
