package greendog.webtask.util;

import java.util.Calendar;
/**
 * 时间操作类
 * @author zhangqing
 *
 */
public class DateUtil {

	/**
	 * 获得时间
	 * @return
	 */
	public static Long getTimeInMillis(){
		return Calendar.getInstance().getTimeInMillis();
	}
}

