package util;

import java.util.Calendar;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void 本月第一天(){
		Calendar a = DateUtil.getFristDayForMonth(Calendar.getInstance());
		System.out.println(DateUtil.formatDate(a));
	}
}
