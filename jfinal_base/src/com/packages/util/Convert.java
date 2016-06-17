package com.packages.util;

public class Convert {
	
	public static String longToString(Long num){
		String str = "";
		try {
			str = Long.toString(num);
		} catch (Exception e){
			e.printStackTrace();
		}
		return str;
	}
	
}
