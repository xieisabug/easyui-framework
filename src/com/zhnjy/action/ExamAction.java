package com.zhnjy.action;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;

import com.zhnjy.util.db.ExamDB;
import com.zhnjy.util.db.ExamSetDB;
import com.zhnjy.util.db.PoiHelper;
import com.zhnjy.util.db.RegisterDB;
import com.zhnjy.util.db.SchoolDB;

public class ExamAction {

	private String pIdCardCol;
	private String pGradeCol;
	private int unitNum;
	// 这个要在界面上获得。
	private String schoolId;
	private File provinceFile;
	private String examDate;

	public String check() {
		int _pIdCardCol;
		int _pGradeCol;

		Sheet proSheet = PoiHelper.getSheet(provinceFile);

		// 自动寻找身份证所在列。
		_pIdCardCol = PoiHelper.findRow(proSheet, "身份证号码");
		if (pIdCardCol.equals("") || pIdCardCol == null) {
			_pIdCardCol = PoiHelper.findRow(proSheet, "身份证");
		}

		// 自动寻找第一单元所在列。
		_pGradeCol = PoiHelper.findRow(proSheet, "第一单元");
		if (pGradeCol.equals("") || pGradeCol == null) {
			_pGradeCol = PoiHelper.findRow(proSheet, "一单元");
		}

		// 得到单元个数。
		unitNum = Integer.parseInt(ExamSetDB.getUnitNum());

		HashMap proMap = PoiHelper.getMap(proSheet, _pIdCardCol, _pGradeCol,
				unitNum);
		HashMap schMap = null;
		int _schoolId = Integer.parseInt(schoolId);
		schMap = ExamDB.getMap(_schoolId, examDate);

		Iterator schIt = schMap.keySet().iterator();
		HashMap resultMap = new HashMap<String, String>();
		while (schIt.hasNext()) {
			// 如果省注册表有这个身份证号码，则比较单元值
			String key = (String) schIt.next();
			if (proMap.containsKey(key) == true) {
				List proList = (List) proMap.get(key);
				List schList = (List) schMap.get(key);
				Iterator sch = schList.iterator();
				int i = 0;
				while (sch.hasNext()) {
					String schUnit = (String) sch.next();
					String proUnit = (String) proList.get(i++);
					if (schUnit.equals(proUnit))
						continue;
					resultMap.put(key, "1");
					break;
				}
			} else {
				resultMap.put(key, "0");
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

	public File getProvinceFile() {
		return provinceFile;
	}

	public void setProvinceFile(File provinceFile) {
		this.provinceFile = provinceFile;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

}
