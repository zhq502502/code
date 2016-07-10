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

import com.seegle.util.SeegleLog;

public class VideoDownLoadAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
//    	SeegleLog.getInstance().getLog(this.getClass(), request.getSession().getAttribute("orgid").toString()).debug("debug in VideoDownLoadAction");
    	String downloadurl = getServlet().getServletContext().getRealPath(request.getParameter("downloadurl"))+"/";
    	String filename = request.getParameter("filename");    //得到下载文件路径
    	String realname = request.getParameter("realname");   //得到下载文件改名

    	// 设置响应头和下载保存的文件名
    	response.reset();
    	response.setContentType("application/octet-stream; charset=UTF-8"); 
    	if(request.getParameter("browser")=="IE"){
    		response.addHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(realname, "UTF-8"));
    	}
    	response.addHeader("Content-Disposition", "attachment; filename="+new String(realname.getBytes("GBK"),"iso8859-1"));    	

    	OutputStream output = null;
    	FileInputStream fis = null;
    	try{
    	  //新建File对象
    	  File f = new File(downloadurl+filename);
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
