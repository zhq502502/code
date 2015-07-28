package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**日期格式yyyy-MM-dd HH:mm:ss*/
	public static final String DATEFORMAT_EN = "yyyy-MM-dd HH:mm:ss";
	/**日期格式yyyy年MM月dd日 HH时mm分ss秒*/
	public static final String DATEFORMAT_ZH_CN = "yyyy年MM月dd日 HH时mm分ss秒";
	private static SimpleDateFormat format = new SimpleDateFormat(DATEFORMAT_EN);
	
	public static String formatDate(Calendar cal){
		return format.format(cal.getTime());
	}
	public static Calendar getFristDayForMonth(Calendar cal){
		Calendar cal_new = (Calendar) cal.clone();
		cal_new.set(Calendar.YEAR, 2011);
		return cal_new;
	}
	
	
	
	
	public static String formatDate(Date date){
		return format.format(date);
	}
}
