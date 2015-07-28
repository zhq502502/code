package util;
/**
 * 字符串操作类
 * @author zhangqing
 * @date 2015-7-28 下午4:58:53
 */
public class StringUtil {
	/**
	 * 字符串是否为空
	 * @author zhangqing
	 * @date 2015-7-28 下午4:58:40
	 * @param str
	 * @return
	 */
	public static boolean isNullOrBlank(String str){
		return str==null||str.equals("");
	}
}
