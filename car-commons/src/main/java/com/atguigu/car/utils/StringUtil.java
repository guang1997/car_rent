package com.atguigu.car.utils;

public class StringUtil {
	public static boolean isEmpty(String s) {
		// s == null | s.equals(""); //位与,逻辑与区别,非空字符串放置在前面,避免空指针
		return s == null || "".equals(s);
	}

	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

}
