package com.packages.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

public final class StringUtil {
	public static String Base64endcode(String encode){
		byte[] b = encode.getBytes();
		new Base64();
		return Base64.encodeBase64URLSafeString(b);
	}
	
	public static String Base64endcode(int encode){
		String encodeString = Integer.toString(encode);
		byte[] b = encodeString.getBytes();
		new Base64();
		return Base64.encodeBase64URLSafeString(b);
	}
	
	public static String Base64endcode(String encode, boolean flag){
		byte[] b = encode.getBytes();
		new Base64();
		return Base64.encodeBase64String(b);
	}
	
	public static String Base64decode(String decode){
		new Base64();
		//byte[] b = decode.getBytes();
		return new String(Base64.decodeBase64(decode));
	}
	
	public static String subBeginString(String str, String suffix){
		int pos = str.indexOf(suffix);
		return str.substring(0, pos);
	}
	
	public static String subEndString(String str, String suffix){
		int pos = str.indexOf(suffix);
		return str.substring(pos+1, str.length());
	}
	
	public static Object[][] objectToObjectArray(Object obj){
		Object[][] a = null;
		if(obj instanceof List){
			//a = new Object[4][2];//不知为啥这么写
			a = new Object[((List) obj).size()][];
			List list = (List)obj;
			Iterator iter = list.iterator();		
			int m = 0;
			while(iter.hasNext()){				
				Object value = iter.next();
				if(value instanceof Object[]) {
					Object[] arr = (Object[])value;
					/*List<Object> ls = new ArrayList<Object>();
					for (int i=0; i<arr.length; i++) { 
						ls.add(arr[i]);
					}*/
					a[m] = arr;
				}
				m++;
			}
		}
		//行转列
		Object[][] b = new Object[a[0].length][a.length];;
		 for (int i=0; i<a.length; i++) {
		    for (int j=0; j<a[i].length; j++) {
		        b[j][i] = a[i][j];
		    }
		 }
		return b;
	}
	
	public static String getFileExtension(String fileName){
		String extension = "";
		int i = fileName.lastIndexOf('.');
		int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
		if (i > p) {
		    extension = fileName.substring(i+1);
		}
		return extension;
	}
	
	public static String formatDate(int dateTimeFormat, int dateTimeBasicDay, int dateTimeBasicMonth, int dateTimeBasicYear){
		String str = "";
		//yyyy-MM-dd HH:mm:ss
		//public int dateTimeFormat = 1; //1全显示 2只显示日期 3只显示时间 4只显示年 5只显示年月 6只显示年月日 7显示年月日时 8显示年月日时分
		//public int dateTimeBasicDay = 0; 
		//public int dateTimeBasicMonth = 0;
		//public int dateTimeBasicYear = 0; 
		SimpleDateFormat df =  null;
		if(dateTimeFormat == 1){
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		else if(dateTimeFormat == 2){
			df = new SimpleDateFormat("yyyy-MM-dd");
		}
		else if(dateTimeFormat == 3){
			df = new SimpleDateFormat("HH:mm:ss");		
		}
		else if(dateTimeFormat == 4){
			df = new SimpleDateFormat("yyyy");
		}
		else if(dateTimeFormat == 5){
			df = new SimpleDateFormat("yyyy-MM");
		}
		else if(dateTimeFormat == 6){
			df = new SimpleDateFormat("yyyy-MM-dd");
		}
		else if(dateTimeFormat == 7){
			df = new SimpleDateFormat("yyyy-MM-dd HH");
		}
		else if(dateTimeFormat == 8){
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
		else {
			df = new SimpleDateFormat("yyyyMMddHHmmss");
		}
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, dateTimeBasicDay);
		cal.add(Calendar.MONTH, dateTimeBasicMonth);
		cal.add(Calendar.YEAR, dateTimeBasicYear);
		Date date=cal.getTime();
		str = df.format(date);
		return str;
	}
	
	public static byte[] imageToByteArray(String filepath){
		byte[] imageInByte = null;
		BufferedImage originalImage;
		try {
			originalImage = ImageIO.read(new File(filepath));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "png", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageInByte;
	}
	
	public static String getNowtimeText(){
		String str = "";
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour >= 6 && hour < 8) {
			str = "早上好";
		} else if (hour >= 8 && hour < 12) {
			str = "上午好";
		} else if (hour >= 12 && hour < 14) {
			str = "中午好";
		} else if (hour >= 14 && hour < 18) {
			str = "下午好";
		} else if (hour >= 18 && hour < 24) {
			str = "晚上好";
		} else { str = "休息了"; }
		return str;
	}
	
	public static int getNowYear(){
		int year = 0;
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		year = cal.get(Calendar.YEAR);
		return year;
	}
	
	public static String getQuarter(){
		String str = "";
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		switch (month) {
		case 1:
		case 2:
			str = "Q1";
			break;
		case 3:
		case 4:
			str = "Q2";
			break;
		case 5:
		case 6:
			str = "Q3";
			break;
		case 7:
		case 8:
			str = "Q4";
			break;
		case 9:
		case 10:
			str = "Q5";
			break;
		case 11:
		case 12:
			str = "Q6";
			break;
		default:
			break;
		}
		return str;
	}
	
	public static String getPrevQuarter(String q){
		String str = "";
		if("Q6".equals(q)){
			str = "Q5";
		} else if("Q5".equals(q)){
			str = "Q4";
		} else if("Q4".equals(q)){
			str = "Q3";
		} else if("Q3".equals(q)){
			str = "Q2";
		} else if("Q2".equals(q)){
			str = "Q1";
		} else if("Q1".equals(q)){
			str = "";
		}
		return str;
	}
	
	public static String getNextQuarter(String q){
		String str = "";
		if("Q6".equals(q)){
			str = "";
		} else if("Q5".equals(q)){
			str = "Q6";
		} else if("Q4".equals(q)){
			str = "Q5";
		} else if("Q3".equals(q)){
			str = "Q4";
		} else if("Q2".equals(q)){
			str = "Q3";
		} else if("Q1".equals(q)){
			str = "Q2";
		}
		return str;
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try {  
		 double d = Double.parseDouble(str);  
	  }  catch(NumberFormatException nfe)  {  
	    return false;  
	  }  
	  return true;  
	}
	public static boolean isNullOrBlack(String str){
		return str==null||str.equals("");
	}
	
	public static void main(String arg[]){
		System.out.println(getNowtimeText());
	}
	
	public static String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {  
	    byte[] bytes = text.getBytes("UTF-8");  
	    ByteBuffer buffer = ByteBuffer.allocate(bytes.length);  
	    int i = 0;  
	    while (i < bytes.length) {  
	        short b = bytes[i];  
	        if (b > 0) {  
	            buffer.put(bytes[i++]);  
	            continue;  
	        }  
	        b += 256;  
	        if ((b ^ 0xC0) >> 4 == 0) {  
	            buffer.put(bytes, i, 2);  
	            i += 2;  
	        }  
	        else if ((b ^ 0xE0) >> 4 == 0) {  
	            buffer.put(bytes, i, 3);  
	            i += 3;  
	        }  
	        else if ((b ^ 0xF0) >> 4 == 0) {  
	            i += 4;  
	        }  
	    }  
	    buffer.flip();  
	    return new String(buffer.array(), "utf-8");  
	}  
	
}
