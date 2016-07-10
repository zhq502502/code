package com.seegle.util;
/**
 * 项目路径管理类
 * @author zhangqing
 * @date 2013-5-28 上午11:57:48
 */
public class PathUtil {

	/**项目路径*/
	private static String projectPath;
	/**获取项目路径*/
	public static String getProjectPath() {
		return projectPath;
	}
	/**设置项目路径*/
	public static void setProjectPath(String path){
		PathUtil.projectPath = path;
	}
}
