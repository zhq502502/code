package com.seegle.util;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;

/**
 * 日志输出类，能根据不同的组织ID输出到不同的文件中
 * @author zhangqing
 * @date 2013-5-2 上午11:23:05
 */
public class SeegleLog {
	//声明static的自己对象
	private static SeegleLog sLog;
	private static int dateTime= Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
	private Properties prop;
	private String logPath;
	//文件输出日志
	private String fileAppender;
	private RollingFileAppender appender;
	//文件输出日志级别
	private String level;
	//私有构造方法，不能通过new创建对象
	private Map<String,String> map;
	private SeegleLog(){
		map = new HashMap<String, String>();
		prop = new Properties();
		initProp();
		PropertyConfigurator.configure(prop);
		fileAppender = prop.getProperty("log4j.fileappender");
		level = prop.getProperty("log4j.fileappender.level");
		prop.keys();
	}
	
	/**
	 * 初始化properties参数
	 * @author zhangqing
	 * @date 2013-5-9 上午11:42:06
	 */
	private void initProp(){
		Properties prop1 = PropUtil.getInstance().getProperties();
		Set<Object> set = prop1.keySet();
		for(Object s:set){
			if(s.toString().startsWith("log4j")){
				prop.put(s, PropUtil.getInstance().getValue(s.toString()));
			}
		}
	}
	
	/**
	 * 获得SeegleLog的单例对象
	 * @author zhangqing
	 * @date 2013-5-2 上午11:16:00
	 * @return
	 */
	public static SeegleLog getInstance(){
		if(sLog==null){
			sLog = new SeegleLog();
		}
		/*if(dateTime != Calendar.getInstance().get(Calendar.DAY_OF_YEAR)){
			sLog.map = new HashMap<String, String>();
			dateTime = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
		}*/
		return sLog;
	}
	
	/**
	 * 获得Logger对象
	 * @author zhangqing
	 * @date 2013-5-2 上午11:14:54
	 * @param th 要输出日志的类
	 * @param key 企业ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Logger getLog(Class th,String key1){
		logPath = prop.getProperty("log4j.fileappender.file");
		String key = key1;
		if(key==null||key.equals("")){
			key="defual";
		}
		//Logger log=Logger.getLogger(th+"-"+key);
		Logger log = null;//=Logger.getLogger(key);
		//生成新的Appender.并将配置文件中输出到文件日志的不配拿到并生成新的日志配置，并将之前的删掉
		if(appender==null){
			appender =  (RollingFileAppender) Logger.getRootLogger().getAppender(fileAppender);
			//Logger.getRootLogger().removeAppender(fileAppender);
		}
		//将文件名进行格式化
		Calendar calendar = Calendar.getInstance();
		if(logPath.contains("@y")){//以年输出
			String year = ""+calendar.get(Calendar.YEAR);
			logPath = logPath.replace("@y", year);
		}
		if(logPath.contains("@m")){//以月
			String year = ""+calendar.get(Calendar.YEAR);
			String month = ""+(calendar.get(Calendar.MONTH)+1);
			logPath = logPath.replace("@m", year+"-"+month);
		}
		if(logPath.contains("@w")){//以周
			String year = ""+calendar.get(Calendar.YEAR);
			String month = ""+(calendar.get(Calendar.MONTH)+1);
			String week = ""+calendar.get(Calendar.WEEK_OF_MONTH);
			logPath = logPath.replace("@w", year+"-"+month+"-"+week+"week");
		}
		if(logPath.contains("@d")){//以日
			String year = ""+calendar.get(Calendar.YEAR);
			String month = ""+(calendar.get(Calendar.MONTH)+1);
			String day = ""+calendar.get(Calendar.DAY_OF_MONTH);
			logPath = logPath.replace("@d", year+"-"+month+"-"+day);
		}
		if(logPath.contains("@h")){//以小时
			String year = ""+calendar.get(Calendar.YEAR);
			String month = ""+(calendar.get(Calendar.MONTH)+1);
			String day = ""+calendar.get(Calendar.DAY_OF_MONTH);
			String h = ""+calendar.get(Calendar.HOUR_OF_DAY);
			logPath = logPath.replace("@h", year+"-"+month+"-"+day+" "+h+"h");
		}
		String logPashts[] = logPath.replace("\\", "/").split("/");
		String fileName = logPashts[logPashts.length-1].replace(".log", "");
		if(map.get(key)!=null&&map.get(key).equals(fileName)){
			return Logger.getLogger(key);			
		}
		log=Logger.getLogger(key);
		String path = PathUtil.getProjectPath()+logPath.replace("@o", key);
		RollingFileAppender appender1 = new RollingFileAppender();
		appender1.setName(key+" "+fileName);
        appender1.setLayout(appender.getLayout());
        appender1.setFile(path);
        appender1.setAppend(appender.getAppend());
        appender1.setBufferedIO(appender.getBufferedIO());
        appender1.setBufferSize(appender.getBufferSize());
        appender1.setMaxBackupIndex(appender.getMaxBackupIndex());
        appender1.setMaximumFileSize(appender.getMaximumFileSize());
        appender1.setEncoding(appender.getEncoding());
        appender1.activateOptions();
        log.removeAllAppenders();
        log.addAppender(appender1);
        log.setLevel(Level.toLevel(level));
        map.put(key,fileName);
		return log;
	}
	
	public static void main(String [] arg){
		System.out.println(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		//System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
	}
}
