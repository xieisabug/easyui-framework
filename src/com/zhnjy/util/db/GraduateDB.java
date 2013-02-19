package com.zhnjy.util.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhnjy.entity.Course;
import com.zhnjy.entity.Student;

public class GraduateDB {

	/**
	 * 统计传入的学生是否可以毕业
	 */

	public static List getGraduate(List students) {
		List<Student> graduates = new ArrayList<Student>();
		Iterator it = students.iterator();
		while (it.hasNext()) {
			Student student = (Student) it.next();
			
			if(student.getStudyLevel().equals("bk") && student.getbStudyType().equals("自考")) {
				int majorId = student.getbMajorId();
				List courses = MajorCourseDB.getCourselist(majorId);
				Iterator co = courses.iterator();
				boolean bool = true;
				while(co.hasNext()){
					Course course = (Course) co.next();
					if(!GradeDB.isPass(student.getIdCard(), course.getNumber())){
						bool = false;
						break;
					}
				}
				if(bool){
					graduates.add(student);
				}
			}else{
				if(student.getStudyLevel().equals("zk") && student.getbStudyType().equals("自考")) {
					int majorId = student.getzMajorId();
					List courses = MajorCourseDB.getCourselist(majorId);
					Iterator co = courses.iterator();
					boolean bool = true;
					while(co.hasNext()){
						Course course = (Course) co.next();
						if(!GradeDB.isPass(student.getIdCard(), course.getNumber())){
							bool = false;
							break;
						}
					}
					if(bool){
						graduates.add(student);
					}
				}else{
					if(student.getStudyLevel().equals("td") && student.getbStudyType().equals("自考")) {
						int majorId = student.getbMajorId();
						List courses = MajorCourseDB.getCourselist(majorId);
						Iterator co = courses.iterator();
						boolean bool = true;
						while(co.hasNext()){
							Course course = (Course) co.next();
							if(!GradeDB.isPass(student.getIdCard(), course.getNumber())){
								bool = false;
								break;
							}
						}
						if(bool){
							graduates.add(student);
						}
					}
				}
			}

		}
	

		return graduates;
	}
}
