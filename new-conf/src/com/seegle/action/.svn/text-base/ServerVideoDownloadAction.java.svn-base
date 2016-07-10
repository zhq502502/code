package com.seegle.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ServerVideoDownloadAction extends org.apache.struts.action.Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	String downloadurl = getServlet().getServletContext().getRealPath(request.getParameter("downloadurl"))+"/";
    	String filename = request.getParameter("filename");    //得到下载文件路径
    	//String realname = request.getParameter("realname");
    	String realname = new String(request.getParameter("realname").getBytes("iso8859-1"),"UTF-8");   //得到下载文件改名
    	
    	//System.out.println(downloadurl+filename);
    	//System.out.println(realname);

    	// 设置响应头和下载保存的文件名
    	response.reset();
    	response.setContentType("application/octet-stream; charset=UTF-8"); 
    	if(request.getParameter("browser")=="IE"){
    		response.addHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(realname, "UTF-8"));
    	}
    	response.addHeader("Content-Disposition", "attachment; filename="+new String(realname.getBytes("GBK"),"iso8859-1"));    	
    	//response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(realname.getBytes("gb2312"),"iso8859-1") + "\"");   

    	OutputStream output = null;
    	FileInputStream fis = null;
    	try{
    	  //新建File对象
    	  File f = new File(downloadurl+realname);
    	  output = response.getOutputStream();
    	  fis = new FileInputStream(f);
    	  //设置每次写入缓存大小
    	  //byte[] b = new byte[(int)f.length()];
    	  byte[] b = new byte[1024];
    	  //把输出流写入客户端
    	  int i = 0;
    	  while((i = fis.read(b)) > 0){
    	    output.write(b, 0, i);
    	  }
    	  output.flush();
    	}
    	catch(Exception e){
    	  e.printStackTrace();
    	}
    	finally{
    	  if(fis != null){
    	    fis.close();
    	    fis = null;
    	  }
    	  if(output != null){
    	    output.close();
    	    output = null;
    	  }
    	}
    	
        return null;
    }
}
