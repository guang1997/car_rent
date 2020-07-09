package com.atguigu.car.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
	
	private static Properties properties=new Properties();
	
	
	static {
		InputStream stream = PropertiesUtils.class.getClassLoader().getResourceAsStream("excel.properties");
		try {
			properties.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	
}
