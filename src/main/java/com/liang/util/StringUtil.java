package com.liang.util;

public class StringUtil {

	/**
	 * 字符串为null或空串或空格时返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if ((str == null) || ("".equals(str)))
			return true;
		if (str.trim().length() == 0)
			return true;

		return false;
	}
	
	
	public static String projectName="qq";
}
