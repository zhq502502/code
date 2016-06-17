package com.packages.util;

import java.util.Calendar;

public class NumUtil {
	public static String getOrderNum(){
		String time = Calendar.getInstance().getTimeInMillis()+"";
		return "SK"+time+(int)(Math.random()*100000);
	}
	public static void main(String[]a){
		System.out.println(getOrderNum());
	}
	
}
