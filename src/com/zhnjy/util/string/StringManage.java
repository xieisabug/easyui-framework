package com.zhnjy.util.string;

public class StringManage {
	/**
	 * 给方法是如果传入的字符串的第一个字符不是数字，则去掉第一个
	 * 
	 * @param str
	 * @return
	 */
	public static String removePointAtFirst(String str) {
		char num[] = str.toCharArray();// 把字符串转换为字符数组
		if (!Character.isDigit(num[0])) {
			str = str.substring(1);
		}
		return str;
	}
/**
 * 判断字符串是不是数字串
 * @param str
 * @return
 */
	public static boolean isNumeric(String str) {
		if (str.matches("\\d*") || str.matches("[0-9]*(\\.?)[0-9]*")) {
			return true;
		} else {
			return false;
		}
	}
}
