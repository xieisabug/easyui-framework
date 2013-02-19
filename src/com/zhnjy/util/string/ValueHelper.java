package com.zhnjy.util.string;

public class ValueHelper {
	public static String getNameByLevel(String level) {
		if(level.equals("zk")) {
			return "专科";
		} else if(level.equals("bk")) {
			return "本科";
		} else if(level.equals("td")) {
			return "套读";
		}
		return "未知";
	}
}
