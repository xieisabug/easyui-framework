package com.zhnjy.util.db;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.zhnjy.entity.Course;
import com.zhnjy.entity.Student;

public class SelectCourseXML {
	
	/**
	 * 得到一个学生的选课信息
	 * @param student
	 * @param isBK
	 * @return
	 */
	public static Document selectCourse(Student student, boolean isBK) {
		String _examNumber;
		String _studyForm;
		String _major;
		int majorId;
		Document doc = DocumentHelper.createDocument();
		// 根节点
		Element root = doc.addElement("info");
		// 根节点下四个子节点
		Element isPay = root.addElement("isPay");
		Element status = root.addElement("status");
		Element stuInfo = root.addElement("stuInfo");
		Element examInfo = root.addElement("examInfo");
		//判断该学生是否已经交了报考费
		if(RecordDB.isPay(student.getIdCard(),ExamSetDB.getExamDate())){
			isPay.addText("true");
		}else{
			isPay.addText("false");
		}
		
		if (isBK) {
			_examNumber = student.getbExamNumber();
			_studyForm = student.getbStudyForm();
			_major = MajorDB.getNumber(student.getbMajorId()) + " "
					+ MajorDB.getName(student.getbMajorId());
			majorId = student.getbMajorId();
			// 添加是否在籍
			if (student.getbStatus().equals("在籍") || student.getbStatus().equals("待注册")) {
				status.addText("true");
			}else{
				status.addText("false");
			}

		} else {
			_examNumber = student.getzExamNumber();
			_studyForm = student.getzStudyForm();
			if(student.getzMajorId() == 0 ){
				_major = "未选择";
			}else{
			_major = MajorDB.getNumber(student.getzMajorId()) + " "
				+ MajorDB.getName(student.getzMajorId());
			}
			majorId = student.getbMajorId();
			if (student.getzStatus().equals("在籍") || student.getzStatus().equals("待注册")){
				status.addText("true");
			}else{
				status.addText("false");
			}
		}

		// 添加是否在籍
		// 第一个子节点的构建，也是学生的信息。
		Element examNumber = stuInfo.addElement("examNumber");
		examNumber.addText(_examNumber);

		Element studyForm = stuInfo.addElement("studyForm");
		studyForm.addText(_studyForm);

		Element name = stuInfo.addElement("name");
		name.addText(student.getName());

		Element sex = stuInfo.addElement("sex");
		sex.addText(student.getSex());

		Element postCode = stuInfo.addElement("postCode");
		if (student.getPostCode() != null || !student.getPostCode().equals("")) {
			postCode.addText(student.getPostCode());
		}

		Element address = stuInfo.addElement("address");
		if (student.getAddress() != null || !student.getAddress().equals("")) {
			address.addText(student.getAddress());
		}

		Element nationality = stuInfo.addElement("nationality");
		nationality.addText(student.getNationality());

		Element joinTime = stuInfo.addElement("joinTime");
		if (student.getPoliticalLevel() != null
				|| !student.getPoliticalLevel().equals("")) {
			if(student.getStudyLevel().equals("bk")){
				joinTime.addText(student.getbJoinTime());
			} else {
				joinTime.addText(student.getzJoinTime());
			}
		}

		Element major = stuInfo.addElement("major");
		major.addText(_major);

		Element idCard = stuInfo.addElement("idCard");
		idCard.addText(student.getIdCard());

		Element phone1 = stuInfo.addElement("phone1");
		phone1.addText(student.getPhone1());

		Element phone2 = stuInfo.addElement("phone2");
		if (student.getPhone2() != null || !student.getPhone2().equals("")) {
			phone2.addText(student.getPhone2());
		}

		Element email = stuInfo.addElement("email");
		if (student.getEmail() != null || !student.getEmail().equals("")) {
			email.addText(student.getEmail());
		}
		// 得到考期
		Element examDate = stuInfo.addElement("examDate");
		examDate.addText(ExamSetDB.getExamDate());

		// 接下来是eaxmInfo的构造
		// 先得到单元数
		int _unitNum = Integer.parseInt(ExamSetDB.getUnitNum());

		int k = 1;// 表示单元数

		for (int i = 1; i <= _unitNum / 2; i++) {
			Element day = examInfo.addElement("day");

			Element unit1 = day.addElement("unit1");

			Element html1 = unit1.addElement("html");
			
			List<Course> courseList = MajorCourseDB.getSelectableCourse(majorId,student.getIdCard());
			
			String html11 = "";
			for (int j = 0; j < courseList.size(); j++) {
				Course cou = (Course) courseList.get(j);
				if (cou.getUnit() == k) {
					String cname = cou.getName();
					String cnumber = cou.getNumber();
					//如果该学生已经选了该门课程，则显示为红色
					if (ExamItemDB.isSelect(student.getIdCard(), cou.getId())) {
						html11 += "<input type='radio' name='unit" + k + "' value='" 
								+ cnumber +"' class='no_width' "
								+ " checked='true'><span style='color: red'>" 
								+ cnumber + cname + "</span>";
					} else {
						html11 += "<input type='radio' name='unit" + k + "' value='" 
								+ cnumber +"' class='no_width' ><span>" 
								+ cnumber + cname + "</span>";
					}
				}
			}
			html1.addText(html11);
			k++;
			Element unit2 = day.addElement("unit2");

			Element html2 = unit2.addElement("html");
			
			
			String html22 = "";
			for (int j = 0; j < courseList.size(); j++) {
				Course cou = (Course) courseList.get(j);
				if (cou.getUnit() == k) {
					String cname = cou.getName();
					String cnumber = cou.getNumber();
					if (ExamItemDB.isSelect(student.getIdCard(), cou.getId())) {
						html22 += "<input type='radio' name='unit" + k + "' value='" 
								+ cnumber +"' class='no_width' "
								+ " checked='true'><span style='color: red'>" 
								+ cnumber + cname + "</span>";
					} else {
						html22 += "<input type='radio' name='unit" + k + "' value='" 
								+ cnumber +"' class='no_width'><span>" 
								+ cnumber + cname + "</span>";
					}
				}
			}
			html2.addText(html22);
			k++;
		}

		// 考虑单元数为奇数的时候
		if (_unitNum % 2 == 1) {
			Element day = examInfo.addElement("day");

			Element unit1 = day.addElement("unit1");

			Element html1 = unit1.addElement("html");
			
			List courseList = MajorCourseDB.getSelectableCourse(majorId,student.getIdCard());
			
			String html11 = "";
			for (int j = 0; j < courseList.size(); j++) {
				Course cou = (Course) courseList.get(j);
				if (cou.getUnit() == k) {
					String cname = cou.getName();
					String cnumber = cou.getNumber();
					if (ExamItemDB.isSelect(student.getIdCard(), cou.getId())) {
						html11 += "<input type='radio' name='unit" + k + "' value='" 
								+ cnumber +"' class='no_width' "
								+ " checked='true'><span style='color: red'>" 
								+ cnumber + cname + "</span>";
					} else {
						html11 += "<input type='radio' name='unit" + k + "' value='" 
								+ cnumber +"' class='no_width' ><span>" 
								+ cnumber + cname + "</span>";
					}
				}
			}
			html1.addText(html11);
		}
		
		return doc;
	}
	
	/**
	 * 当没有这个学生和该用户没有这个权限的时候，返回一个值全部为空的Document,并且给出提示
	 */
	public static Document selectCourse(String t) {
		Document doc = DocumentHelper.createDocument();
		// 根节点
		Element root = doc.addElement("info");
		// 根节点下四个子节点
		Element isPay = root.addElement("isPay");
		Element status = root.addElement("status");
		Element stuInfo = root.addElement("stuInfo");
		Element examInfo = root.addElement("examInfo");


		// 第一个子节点的构建，也是学生的信息。
		Element examNumber = stuInfo.addElement("examNumber");

		Element studyForm = stuInfo.addElement("studyForm");

		Element name = stuInfo.addElement("name");

		Element sex = stuInfo.addElement("sex");

		Element postCode = stuInfo.addElement("postCode");

		Element address = stuInfo.addElement("address");

		Element nationality = stuInfo.addElement("nationality");

		Element politicalLevel = stuInfo.addElement("politicalLevel");

		Element major = stuInfo.addElement("major");

		Element idCard = stuInfo.addElement("idCard");

		Element phone1 = stuInfo.addElement("phone1");

		Element phone2 = stuInfo.addElement("phone2");

		Element email = stuInfo.addElement("email");
		
		// 得到考期
		Element examDate = stuInfo.addElement("examDate");
		examDate.addText(ExamSetDB.getExamDate());

		// 接下来是eaxmInfo的构造
		// 先得到单元数
		int _unitNum = Integer.parseInt(ExamSetDB.getUnitNum());

		int k = 1;// 表示单元数

		for (int i = 1; i <= _unitNum / 2; i++) {
			Element day = examInfo.addElement("day");

			Element unit1 = day.addElement("unit1");
			unit1.addText(t);
			Element html1 = unit1.addElement("html");
			
			
			Element unit2 = day.addElement("unit2");

			Element html2 = unit2.addElement("html");
			
			
		}

		// 考虑单元数为奇数的时候
		if (_unitNum % 2 == 1) {
			Element day = examInfo.addElement("day");

			Element unit1 = day.addElement("unit1");

			Element html1 = unit1.addElement("html");
		}
		
		return doc;
	}
}
