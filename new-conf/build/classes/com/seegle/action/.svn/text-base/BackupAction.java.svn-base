package com.seegle.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mysql.jdbc.BufferRow;
import com.seegle.util.ExcelUtil;
import com.seegle.util.LanguageUtil;
import com.seegle.util.PathUtil;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

public class BackupAction extends org.apache.struts.action.Action {
	private static final String SUCCESS = "success";
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	private final String backupPath = PathUtil.getProjectPath()+"backup\\";
	private static String mysql_home = null;
	private static String mysql_ip = null;
	private static String mysql_port = null;
	private static String mysql_name = null;
	private static String mysql_pass = null;
	private static String databases = PropUtil.getInstance().getValue("backup.databases");
	//private static String databases = "db rentconf";
	static{
		try{
			
			Process p = Runtime.getRuntime().exec("cmd /c sc qc sgmysql");
			BufferedReader br  = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;  
			while ((line = br.readLine()) != null) {
				if(line.indexOf("BINARY_PATH_NAME")>=0){
					String []mysqlstr = line.split("\"");
					String mysql_path = "";
					if(mysqlstr==null||mysqlstr.length<2){
						mysql_path = line.split(" --")[0].replace("BINARY_PATH_NAME   :", "").trim();
					}else{
						mysql_path = mysqlstr[1];
					}
					mysql_home = mysql_path.substring(0,mysql_path.length()-10);
				}
			} 
			String mysqlbaseurl = PropUtil.getInstance().getValue("url").split("\\?")[0];
			mysqlbaseurl = mysqlbaseurl.split("//")[1].split("/")[0];
			mysql_ip = mysqlbaseurl.split(":")[0];
			mysql_port = mysqlbaseurl.split(":")[1];
			mysql_name = PropUtil.getInstance().getValue("userName");
			mysql_pass = PropUtil.getInstance().getValue("password");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private LanguageUtil lu = new LanguageUtil();
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	//设置年
    	Calendar cal = Calendar.getInstance();
		request.setAttribute("nowtime", format.format(cal.getTime()));
		String method=request.getParameter("method");
		if(method==null||method.equals("")){//备份列表
			return this.backuplist(mapping, form, request, response);
		}else if(method.equals("backup")){//备份
			return this.backup(mapping, form, request, response);
		}else if(method.equals("recovery")){//还原
			return this.recovery(mapping, form, request, response);
		}else if(method.equals("delete")){//删除
			return this.delete(mapping, form, request, response);
		}else if(method.equals("download")){//删除
			return this.download(mapping, form, request, response);
		}
		return null;
    }
    /**
     * 备份
     * @author zhangqing
     * @date 2014-11-12 上午10:42:45
     */
    public ActionForward backup(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	if(mysql_home==null||mysql_ip==null||mysql_port==null){
    		response.getWriter().write("1");
    		return null;
    	}
    	String backtype=request.getParameter("backtype");
    	String backupname = format.format(Calendar.getInstance().getTime());
    	String basepath = backupPath+backupname;
		String passString = mysql_pass==null||mysql_pass.equals("")?"":" -p"+mysql_pass;
		//备份到服务器
		String runString = "cmd /c \""+mysql_home+"mysqldump.exe\""+ " -u"+mysql_name+passString+" -h"+mysql_ip+" -P"+mysql_port+" --databases "+databases; 
		
		if(backtype==null||backtype.equals("0")){
			runString +=" > "+basepath+".sql";
		}
		Process process = Runtime.getRuntime().exec(runString);  
		if(backtype==null||backtype.equals("0")){
			if(isOk(process,request.getSession().getAttribute("orgid")+"")){
				response.getWriter().write("0");
			}else{
				runString = "cmd /c del "+basepath+".sql";
				Runtime.getRuntime().exec(runString);
				response.getWriter().write("1");
			}
		}else{
			// 设置响应头和下载保存的文件名
			response.reset();
			response.setContentType("application/x-download; charset=UTF-8"); 
			if(request.getParameter("browser")=="IE"){
				response.addHeader("Content-Disposition", "attachment; filename="+backupname+".sql");
			}
			response.addHeader("Content-Disposition", "attachment; filename="+backupname+".sql");    	
			OutputStream output = response.getOutputStream();
			InputStream in = process.getInputStream();
			byte[] b = new byte[1024];
			// 把输出流写入客户端
			int i = 0;
			while ((i = in.read(b)) > 0) {
				output.write(b, 0, i);
			}
			output.flush();
			output.close();
			in.close();
			process.getOutputStream().close();
		}
		SeegleLog.getInstance().getLog(null,request.getSession().getAttribute("orgid")+"").info("备份数据："+runString);
    	return null;
    }
    /**
     * 还原
     * @author zhangqing
     * @date 2014-11-12 上午10:42:45
     */
    public ActionForward recovery(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    		throws Exception {
    	if(mysql_home==null||mysql_ip==null||mysql_port==null){
    		response.getWriter().write("1");
        	return null;
    	}
    	String backupname = request.getParameter("backupname");
    	String basepath = backupPath+backupname+"";
		String passString = mysql_pass==null||mysql_pass.equals("")?"":" -p"+mysql_pass;
		//+" -D "+databases
		String runString = "cmd /c \""+mysql_home+"mysql.exe\""+ " -u"+mysql_name+passString+" -h"+mysql_ip+" -P"+mysql_port +"  < "+basepath;
		InputStream in = Runtime.getRuntime().exec(runString).getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuffer sb = new StringBuffer();
		String line = null;  
		// 循环读取  
	    while ((line = reader.readLine()) != null) {  
	    // 循环写入  
	    	sb.append(line+"\n");
	    }  
	    System.out.println(sb);
		SeegleLog.getInstance().getLog(null,request.getSession().getAttribute("orgid")+"").info("还原数据："+runString);
    	response.getWriter().write("0");
    	return null;
    }
    /**
     * 删除
     * @author zhangqing
     * @date 2014-11-12 上午10:42:45
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    		throws Exception {
    	String backupname = request.getParameter("backupname");
    	String basepath = backupPath+backupname;
    	String runString = "cmd /c del "+basepath;
    	Runtime.getRuntime().exec(runString);
    	SeegleLog.getInstance().getLog(null,request.getSession().getAttribute("orgid")+"").info("删除数据："+runString);
    	response.getWriter().write("0");
    	return null;
    }
    /**
     * 备份列表
     * @author zhangqing
     * @date 2014-11-12 上午10:42:45
     */
    public ActionForward backuplist(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    		throws Exception {
    	List<Map<String,String>> backuplist = new ArrayList<Map<String,String>>();
    	File dir = new File(backupPath);
    	File[] dirs = dir.listFiles();
    	if(dirs==null){
    		return mapping.findForward(SUCCESS);
    	}
    	for(File f:dirs){
    		if(!f.isDirectory()){
    			Map<String,String> map = new HashMap<String, String>();
    			map.put("backname", f.getName());
    			double size = f.length();
    			DecimalFormat df=new DecimalFormat("###.##"); 
    			String unit = "B";
    			if (size > (1024 * 1024 * 1024)) {
    				unit = "GB";
    				size = size/(1024 * 1024 * 1024);
    			} else if (size > (1024 * 1024)) {
    				unit = "MB";
    				size = size/(1024 * 1024);
    			} else if (size > 1024) {
    				unit = "KB";
    				size = size/1024;
    			}  
    			String sf = df.format(size) ;
    			map.put("usablespace", sf+unit);
    			backuplist.add(map);
    		}
    	}
    	request.setAttribute("backuplist", backuplist);
    	return mapping.findForward(SUCCESS);
    }
    /**
     * 下载
     * @author zhangqing
     * @date 2014-11-12 上午10:42:45
     */
    public ActionForward download(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    		throws Exception {
    	String backupname = request.getParameter("backupname");
    	response.reset();
		response.setContentType("application/x-download; charset=UTF-8"); 
		if(request.getParameter("browser")=="IE"){
			response.addHeader("Content-Disposition", "attachment; filename="+backupname);
		}
		response.addHeader("Content-Disposition", "attachment; filename="+backupname);    	
		OutputStream output = response.getOutputStream();
		String basepath = backupPath+backupname;
		FileInputStream in = new FileInputStream(new File(basepath));
		byte[] b = new byte[1024];
		// 把输出流写入客户端
		int i = 0;
		while ((i = in.read(b)) > 0) {
			output.write(b, 0, i);
		}
		output.flush();
		output.close();
		in.close();
		return null;
    }
    
    public void test(Process process) throws IOException{
    	InputStream in = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuffer sb = new StringBuffer();
		String line = null;  
		// 循环读取  
	    while ((line = reader.readLine()) != null) {  
	    // 循环写入  
	    	sb.append(line+"\n");
	    }  
	    reader.close();
	    //process.getOutputStream().close();
	    String a = sb.toString();
	    System.out.println(sb.toString());
    }
    @SuppressWarnings("unused")
	private boolean isOk(Process process,String orgid) throws IOException{
    	InputStream in = process.getErrorStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuffer sb = new StringBuffer();
		String line = null;  
		// 循环读取  
	    while ((line = reader.readLine()) != null) {  
	    // 循环写入  
	    	sb.append(line+"\n");
	    }  
	    reader.close();
	    process.getOutputStream().close();
	    if(sb.toString().trim().equals("")){
	    	return true;
	    }
	    SeegleLog.getInstance().getLog(null,orgid).info("出错"+sb.toString());
	    return false;
    }
}
