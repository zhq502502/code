package com.seegle.Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seegle.data.FileOperation;
import com.seegle.data.GetConfigFile;

public class VideoManagerServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	FileOperation fo = new FileOperation();

    public VideoManagerServerlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String orgid = session.getAttribute("orgid").toString();
		String action = request.getParameter("action");
		if("delete".equalsIgnoreCase(action)){
			int flag = 1;
			String id = request.getParameter("id");
			boolean a = fo.delFile(orgid,Integer.parseInt(id));
			if(a==true){
				flag = 0;
			}
			out.print(flag);
		} 
		else if("download".equalsIgnoreCase(action)){
			System.out.println("**********************download");
			int flag = 1;			
			String downloadurl = getServletContext().getRealPath(request.getParameter("downloadurl"))+"/";
	    	String filename = request.getParameter("filename");    //得到下载文件路径
	    	String realname = new String(request.getParameter("realname").getBytes("iso8859-1"),"UTF-8");   //得到下载文件改名
	    	System.out.println(downloadurl+filename);
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
	    	  byte[] b = new byte[1024];
	    	  //把输出流写入客户端
	    	  int i = 0;
	    	  while((i = fis.read(b)) > 0){
	    	    output.write(b, 0, i);
	    	  }
	    	  output.flush();
	    	  flag = 0;
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
			out.print(flag);
		}
		else {
			out.print(7000);
		}
	}

}
