package com.seegle.Servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seegle.data.Export2Excel;

public class ConfExportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ConfExportServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		if(request.getSession().getAttribute("token")==null){
			response.sendRedirect(webSiteUrl+"/Login.jsp");
		}
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		Object token = session.getAttribute("token");
		//Object orgid = session.getAttribute("orgid");
		if(token.toString().length()>0){			
			String btime = request.getParameter("btime");
			String etime = request.getParameter("etime");
			String language = request.getParameter("language");
			int t = 0; 
			t = Integer.parseInt(request.getParameter("t"));
			String c = request.getParameter("c");
			int id = (request.getParameter("id")==null)?0:Integer.parseInt(request.getParameter("id"));
			//System.out.println("开始时间:"+btime+"结束时间"+etime);
			String filename = "log_"+btime.replace("-", "").replace(":", "").replace(" ", "")+"-"+etime.replace("-", "").replace(":", "").replace(" ", "")+".xls";
    		response.setContentType("application/vnd.ms-excel"); 
    		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");        	
    		OutputStream os = response.getOutputStream();
			Export2Excel e = new Export2Excel(session.getAttribute("orgid").toString());
			e.createXLS(token.toString(), os, btime, etime, t, c, id,language);
		} else {
			return;
		}

	}

}
