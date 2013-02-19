package com.zhnjy.util.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.views.xslt.ArrayAdapter;

import com.zhnjy.util.string.StringManage;

public class PoiHelper {

	// 得到cell里面的值，无论是什么类型的最后都是以String返回。
	public static String getCellValue(Cell c) {
		String o = null;
		switch (c.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			o = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			o = String.valueOf(c.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			o = String.valueOf(c.getCellFormula());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			o = String.valueOf(c.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			o = c.getStringCellValue();
			break;
		default:
			o = null;
			break;
		}
		return o;
	}

	// 传入一个File，得到这个File的第一个表格
	public static Sheet getSheet(File file) {
		Workbook wb = null;
		Sheet sheet = null;
		try {
			wb = WorkbookFactory.create(file);
			sheet = wb.getSheetAt(0);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sheet;
	}

	// 得到该张表格 名字叫name所在的列,-1代表没找到，否则就是找到了
	public static int findRow(Sheet sheet, String name) {
		int col = -1;
		boolean bool = false;
		for (Row row : sheet) {
			if (bool == false) {
				for (Cell c : row) {
					if (c.getCellType() != Cell.CELL_TYPE_STRING)
						continue;
					String str = c.getStringCellValue().trim();
					if (str.equals(name)) {
						col = c.getColumnIndex();
						bool = true;
						break;
					}
				}
			} else {
				break;
			}
		}
		return col;
	}

	// 传入两张表，两个列，比较第一列的数据第二列是不是都有，第一列比第二列多的数据以字符串数组返回
	public static List compare(Sheet proSheet, Sheet schSheet, int proCol,
			int schCol) {
		int checkRows = proSheet.getLastRowNum();
		Set<String> set1 = new HashSet<String>();
		List<String> list = new ArrayList<String>();

		for (int i = 0; i <= checkRows; i++) {
			Row checkRow = proSheet.getRow(i);
			Cell cell = checkRow.getCell(proCol);
			if (cell == null)
				continue;
			String checkID = getCellValue(cell);
			if (checkID.equals(""))
				continue;
			if (checkID.length() >= 18 && checkID.length() <= 20) {
				set1.add(checkID);
			}
		}
		// 拿到学校注册表的身份证号码，不重复；
		int checkedRows = schSheet.getLastRowNum();
		Set<String> set2 = new HashSet<String>();
		for (int j = 0; j <= checkedRows; j++) {
			Row checkedRow = schSheet.getRow(j);
			Cell cell2 = checkedRow.getCell(schCol);
			if (cell2 == null)
				continue;
			String checkedID;
			checkedID = getCellValue(cell2);

			if (checkedID.equals(""))
				continue;
			if (checkedID.length() >= 18 && checkedID.length() <= 20) {
				set2.add(checkedID);
			}

		}
		Iterator<String> it2 = set2.iterator();
		while (it2.hasNext()) {
			String str = it2.next();
			if (!set1.contains(str))
				list.add(str);
		}
		return list;
	}

	// 得到表的全部的数据。
	public static HashMap getMap(Sheet sheet, int idCardCol, int gradeCol,
			int gradeCount) {
		HashMap map = new HashMap();
		int last = sheet.getLastRowNum();
		for (int i = 0; i <= last; i++) {
			List<String> list = new ArrayList<String>();
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(idCardCol);
			if (cell == null)
				continue;
			String key = getCellValue(cell);
			if (key.equals(""))
				continue;
			if (key.length() < 18 || key.length() > 20)
				continue;
			key = StringManage.removePointAtFirst(key);
			
			for (int j = gradeCol; j < gradeCol + gradeCount; j++) {
				Cell c = row.getCell(j);
				if (c == null)
					continue;
				String unit = getCellValue(c);
				unit = StringManage.removePointAtFirst(unit);
				if (unit.equals(""))
					continue;
				
				list.add(unit);
			}
			map.put(key, list);
		}

		return map;
	}

	// 得到表中学生的身份证和专业编码，以Map返回。
	public static HashMap getIdCardMap(Sheet sheet) {

		HashMap<String, String> map = new HashMap<String, String>();

		int idCardCol = findRow(sheet, "身份证号码");
		if (idCardCol == -1) {
			idCardCol = findRow(sheet, "身份证");
		}

		String majorNumber = null;
		for (Row row : sheet) {
			for (Cell c : row) {
				if (c.getCellType() != Cell.CELL_TYPE_STRING)
					continue;
				String str = c.getStringCellValue().trim();
				if (str.contains("专业名称")) {
					String[] strs = str.split(" ");
					String[] strs2 = strs[0].split("：");
					majorNumber = strs2[1];
				}
				// 如果在身份证那一行，并且长度为18到20，则认为是身份证号码
				if (c.getColumnIndex() == idCardCol && str.length() >= 18
						&& str.length() <= 20) {
					String key = StringManage.removePointAtFirst(str);
					map.put(key, majorNumber);

				}
			}
		}
		return map;
	}
}