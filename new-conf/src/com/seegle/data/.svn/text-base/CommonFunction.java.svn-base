package com.seegle.data;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.seegle.lang.SGDateTime;

public class CommonFunction {
	// 允许上传的文件后缀名
	private final String[] exts = new String[] { "wmv", "smvx" };

	public CommonFunction() {
		super();
	}

	public String formatSize(float size) {
		long kb = 1024;
		long mb = (kb * 1024);
		long gb = (mb * 1024);
		if (size < kb) {
			return String.format("%d B", (int) size);
		} else if (size < mb) {
			return String.format("%.2f KB", size / kb); // 保留两位小数
		} else if (size < gb) {
			return String.format("%.2f MB", size / mb);
		} else {
			return String.format("%.2f GB", size / gb);
		}
	}

	// 秒转小时、分钟、秒
	public String secondsToWords(int seconds) {
		String str = "";
		int hours = 0;
		int minutes = 0;
		hours = (int)((int)(seconds) / 3600);
		if(hours > 0)
		{
			str += hours + " " + "小时" ;
		}
		/*** get the minutes ***/
		minutes = ((int)(seconds) / 60)%60;
		if(hours > 0 || minutes > 0)
		{
			str += minutes+ " " + "分钟"+ " " ;
		} 
		/*** get the seconds ***/
		seconds = (int)(seconds)%60;
		str += seconds+ " " +"秒";
		return str;
		
	}
	
	//日期转秒
	public int dateToSecondsDiff(SGDateTime first, SGDateTime second){
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt1 = null, dt2 = null;
		try {
			dt1  = tempDate.parse(String.valueOf(first.toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			dt2 = tempDate.parse(String.valueOf(second.toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long d1 = dt1.getTime()/1000;
		long d2 = dt2.getTime()/1000;
		return (int)(d2-d1);
	}

	/**
	 * 获取文件名的方法
	 */
	public static String getNameWithoutExtension(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**
	 * 获取扩展名的方法
	 */
	public String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 判断扩展名是否允许的方法
	 */
	@SuppressWarnings({ "rawtypes" })
	public boolean extIsAllowed(String ext) {
		ext = ext.toLowerCase();
		List extList = Arrays.asList(this.exts);
		return extList.contains(ext) ? true : false;
	}

	/* // MD5函数 */
	public String md5(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	public HashMap<String, Integer> mapDiff(HashMap<String, Integer> map1,
			HashMap<String, Integer> map2) {
		for (Map.Entry<String, Integer> map : map2.entrySet()) {
			if (map1.containsKey(map.getKey())) {
				map1.remove(map.getKey());
			}
		}
		return map1;
	}

	public String convert(String str) {
		StringBuffer ostr = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if ((ch >= 0x0020) && (ch <= 0x007e)) // Does the char need to be
													// converted to unicode?
			{
				ostr.append(ch); // No.
			} else // Yes.
			{
				ostr.append("\\u"); // standard unicode format.
				String hex = Integer.toHexString(str.charAt(i) & 0xFFFF); // Get
																			// hex
																			// value
																			// of
																			// the
																			// char.
				for (int j = 0; j < 4 - hex.length(); j++)
					// Prepend zeros because unicode requires 4 digits
					ostr.append("0");
				ostr.append(hex.toLowerCase()); // standard unicode format.
				// ostr.append(hex.toLowerCase(Locale.ENGLISH));
			}
		}

		return (new String(ostr).toString()); // Return the stringbuffer cast as
												// a string.

	}

	public String convertStringToHex(String str) {

		char[] chars = str.toCharArray();

		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}

		return hex.toString();
	}

	public String convertHexToString(String hex) {

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		// 49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for (int i = 0; i < hex.length() - 1; i += 2) {

			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);

			temp.append(decimal);
		}
		// System.out.println("Decimal : " + temp.toString());

		return sb.toString();
	}

}
