package com.packages.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	public static String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat format = new SimpleDateFormat();
	public static String getNowTime(){
		format = new SimpleDateFormat(DATE_FORMAT_1);
		return format.format(Calendar.getInstance().getTime());
	}
	public static String getNextYear(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)+1);
		format = new SimpleDateFormat(DATE_FORMAT_1);
		return format.format(cal.getTime());
	}
}
